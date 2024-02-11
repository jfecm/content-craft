package com.jfecm;

import com.jfecm.adapters.outbound.repository.CommentEntity;
import com.jfecm.adapters.outbound.repository.CommentReactiveMongoRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Component
public class Extra implements InitializingBean {

    private static final String AUTHOR_PREFIX = "PostedBy-";

    private static final String TEXT_PREFIX = "Content-";

    private final CommentReactiveMongoRepository commentRepository;

    @Autowired
    public Extra(CommentReactiveMongoRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void afterPropertiesSet() {
        commentRepository.deleteAll()
                .thenMany(
                        Flux.range(1, 1000)
                                .map(i -> new CommentEntity(
                                        null,
                                        AUTHOR_PREFIX + i,
                                        TEXT_PREFIX + i,
                                        LocalDateTime.now()))
                                .flatMap(commentRepository::save)
                )
                .subscribe();
    }
}