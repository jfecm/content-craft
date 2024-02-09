package com.jfecm.infrastructure.adapter.out.persistence.repository;

import com.jfecm.infrastructure.adapter.out.persistence.entity.CommentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<CommentEntity, String> {

}
