package com.jfecm.infrastructure.adapter.in.rest;

import com.jfecm.application.usecases.CommentService;
import com.jfecm.domain.dto.requests.CommentCreateRequest;
import com.jfecm.domain.dto.response.CommentCreateResponse;
import com.jfecm.domain.dto.response.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Mono<CommentCreateResponse> saveComment(@RequestBody Mono<CommentCreateRequest> comment) {
        return commentService.save(comment);
    }

    @GetMapping
    public Flux<CommentListResponse> getAllComments() {
        return commentService.getAll();
    }

}
