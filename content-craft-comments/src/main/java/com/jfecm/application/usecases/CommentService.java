package com.jfecm.application.usecases;

import com.jfecm.domain.dto.requests.CommentCreateRequest;
import com.jfecm.domain.dto.response.CommentCreateResponse;
import com.jfecm.domain.dto.response.CommentListResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment);

    Flux<CommentListResponse> getAll();
}
