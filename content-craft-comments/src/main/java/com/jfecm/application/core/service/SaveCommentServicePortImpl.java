package com.jfecm.application.core.service;

import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import com.jfecm.application.ports.in.ISaveCommentServicePort;
import com.jfecm.application.ports.out.ISaveCommentPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaveCommentServicePortImpl implements ISaveCommentServicePort {

    private final ISaveCommentPort saveCommentPort;

    @Autowired
    public SaveCommentServicePortImpl(ISaveCommentPort saveCommentPort) {
        this.saveCommentPort = saveCommentPort;
    }

    @Override
    public Mono<CommentCreateResponse> save(Mono<CommentCreateRequest> comment) {
        return saveCommentPort.save(comment);
    }

}