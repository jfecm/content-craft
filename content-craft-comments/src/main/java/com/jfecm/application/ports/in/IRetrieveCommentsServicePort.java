package com.jfecm.application.ports.in;

import com.jfecm.application.core.dto.response.CommentListResponse;
import reactor.core.publisher.Flux;

public interface IRetrieveCommentsServicePort {
    Flux<CommentListResponse> getAll();
}