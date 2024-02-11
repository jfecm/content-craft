package com.jfecm.adapters.inbound.rest;

import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.ports.in.IRetrieveCommentsServicePort;
import com.jfecm.application.ports.in.ISaveCommentServicePort;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import com.jfecm.application.core.dto.response.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final ISaveCommentServicePort commentServicePort;
    private final IRetrieveCommentsServicePort commentsServicePort;

    @Autowired
    public CommentController(ISaveCommentServicePort commentServicePort,
                             IRetrieveCommentsServicePort commentsServicePort) {
        this.commentServicePort = commentServicePort;
        this.commentsServicePort = commentsServicePort;
    }

    @PostMapping
    public Mono<CommentCreateResponse> saveComment(@RequestBody Mono<CommentCreateRequest> comment) {
        return commentServicePort.save(comment);
    }

    @GetMapping
    public Flux<CommentListResponse> getAllComments() {
        return commentsServicePort.getAll();
    }
}