package com.jfecm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "${spring.kafka.topic.comments}", groupId = "comments_group_id")
    public void commentListener(String comment) {
        log.info("Notification service received comment {} ", comment);
    }

    @KafkaListener(topics = "${spring.kafka.topic.posts}", groupId = "posts_group_id")
    public void postListener(String post) {
        log.info("Notification service received post {} ", post);
    }

}
