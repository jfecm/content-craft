package com.jfecm.adapters.outbound.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactiveMongoRepository extends ReactiveMongoRepository<CommentEntity, String> {
}