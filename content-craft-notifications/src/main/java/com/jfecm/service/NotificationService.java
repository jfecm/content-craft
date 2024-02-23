package com.jfecm.service;

import com.jfecm.adapters.outbound.repository.CommentEntity;
import com.jfecm.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "${spring.kafka.topic.comments}", containerFactory = "commentListener")
    public void commentListener(CommentEntity commentEntity) {
        log.info("Notification service received comment {} ", commentEntity);
    }

    @KafkaListener(topics = "${spring.kafka.topic.posts}", containerFactory = "postListener")
    public void postListener(Post post) {
        log.info("Notification service received post {} ", post);
    }

}
