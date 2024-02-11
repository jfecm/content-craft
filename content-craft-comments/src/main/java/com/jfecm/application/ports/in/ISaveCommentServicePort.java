package com.jfecm.application.ports.in;

import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import reactor.core.publisher.Mono;

public interface ISaveCommentServicePort {
    Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment);
}