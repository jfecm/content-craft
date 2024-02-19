package com.jfecm.service.impl;

import com.jfecm.controller.request.PostRequestCreate;
import com.jfecm.controller.response.PostResponse;
import com.jfecm.model.Post;
import com.jfecm.repository.PostRepository;
import com.jfecm.service.PostService;
import com.jfecm.service.mapper.PostEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final KafkaTemplate<String,Object> template;
    @Value("${kafka.topic.name}")
    public String TOPIC_NAME;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, KafkaTemplate<String, Object> template) {
        this.postRepository = postRepository;
        this.template = template;
    }

    @Override
    public PostResponse create(PostRequestCreate post) {
        Post toSave = PostEntityMapper.toEntity(post);
        Post saved = postRepository.save(toSave);
        sendPostToTopic(saved);
        return PostEntityMapper.toResponse(saved);
    }

    private void sendPostToTopic(Post post) {
        try {
            CompletableFuture<SendResult<String, Object>> send = template.send(TOPIC_NAME, post);
            send.whenComplete((result, e) -> {
                if (e == null) {
                    log.info("Post created and saved successfully. Post sent id details: [{}], Partition [{}], Offset [{}], Topic [{}]",
                            post.getId(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset(),
                            TOPIC_NAME
                            );
                } else {
                    log.error("Failed to send post. Post id details: [{}], Topic [{}], Error message: [{}]",
                            post.getId(),
                            TOPIC_NAME,
                            e.getMessage());
                }
            });
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }

    @Override
    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(PostEntityMapper::toResponse)
                .toList();
    }
}