package com.jfecm.application.service;

import com.jfecm.domain.dto.requests.CommentCreateRequest;
import com.jfecm.domain.dto.response.CommentCreateResponse;
import com.jfecm.domain.dto.response.CommentListResponse;
import com.jfecm.application.usecases.CommentService;
import com.jfecm.infrastructure.adapter.out.persistence.repository.CommentRepository;
import com.jfecm.application.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment) {
        return comment.map(CommentMapper::createRequestToEntity)
                .flatMap(commentRepository::insert)
                .map(CommentMapper::entityToCreateResponse);
    }

    @Override
    public Flux<CommentListResponse> getAll() {
        return commentRepository.findAll()
                .map(CommentMapper::entityToListResponse);
    }
}
