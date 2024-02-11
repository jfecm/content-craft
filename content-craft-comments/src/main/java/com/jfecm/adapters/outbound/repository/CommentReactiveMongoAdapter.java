package com.jfecm.adapters.outbound.repository;

import com.jfecm.application.core.mapper.CommentMapper;
import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.ports.out.IRetrieveCommentsPort;
import com.jfecm.application.ports.out.ISaveCommentPort;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import com.jfecm.application.core.dto.response.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CommentReactiveMongoAdapter implements ISaveCommentPort, IRetrieveCommentsPort {

    private final CommentReactiveMongoRepository commentRepository;

    @Autowired
    public CommentReactiveMongoAdapter(CommentReactiveMongoRepository commentRepository) {
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