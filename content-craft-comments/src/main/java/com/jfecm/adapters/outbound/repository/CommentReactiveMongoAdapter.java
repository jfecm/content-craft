package com.jfecm.adapters.outbound.repository;

import com.jfecm.application.core.mapper.CommentMapper;
import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.ports.out.IRetrieveCommentsPort;
import com.jfecm.application.ports.out.ISaveCommentPort;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import com.jfecm.application.core.dto.response.CommentListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CommentReactiveMongoAdapter implements ISaveCommentPort, IRetrieveCommentsPort {

    private final CommentReactiveMongoRepository commentRepository;
    private final KafkaTemplate<String,Object> template;
    @Value("${kafka.topic.name}")
    public String TOPIC_NAME;

    @Autowired
    public CommentReactiveMongoAdapter(CommentReactiveMongoRepository commentRepository, KafkaTemplate<String, Object> template) {
        this.commentRepository = commentRepository;
        this.template = template;
    }

    @Override
    public Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment) {
        return comment.map(CommentMapper::createRequestToEntity)
                .flatMap(commentRepository::insert)
                .flatMap(savedComment -> sendCommentToTopic(savedComment).thenReturn(savedComment))
                .map(CommentMapper::entityToCreateResponse);
    }

    private Mono<Void> sendCommentToTopic(CommentEntity comment) {
        return Mono.fromFuture(() -> template.send(TOPIC_NAME, comment))
                .doOnSuccess(sendResult -> {
                    RecordMetadata metadata = sendResult.getRecordMetadata();
                    log.info("Comment created and saved successfully. Comment sent id details: [{}], Partition [{}], Offset [{}], Topic [{}]",
                            comment.getId(),
                            metadata.partition(),
                            metadata.offset(),
                            TOPIC_NAME
                    );
                })
                .doOnError(error -> log.error("Failed to send comment. Comment id details: [{}], Topic [{}], Error message: [{}]",
                        comment.getId(),
                        TOPIC_NAME,
                        error.getMessage()
                ))
                .then();
    }

    @Override
    public Flux<CommentListResponse> getAll() {
        return commentRepository.findAll()
                .map(CommentMapper::entityToListResponse);
    }
}