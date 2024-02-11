package com.jfecm.application.ports.out;

import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import reactor.core.publisher.Mono;

public interface ISaveCommentPort {
    Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment);
}
