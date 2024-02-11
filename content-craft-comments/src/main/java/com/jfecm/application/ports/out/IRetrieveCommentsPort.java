package com.jfecm.application.ports.out;

import com.jfecm.application.core.dto.response.CommentListResponse;
import reactor.core.publisher.Flux;

public interface IRetrieveCommentsPort {
    Flux<CommentListResponse> getAll();
}