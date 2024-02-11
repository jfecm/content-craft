package com.jfecm.application.core.service;

import com.jfecm.application.ports.out.IRetrieveCommentsPort;
import com.jfecm.application.core.dto.response.CommentListResponse;
import com.jfecm.application.ports.in.IRetrieveCommentsServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class IRetrieveCommentsServicePortImpl implements IRetrieveCommentsServicePort {

    private final IRetrieveCommentsPort commentsPort;

    @Autowired
    public IRetrieveCommentsServicePortImpl(IRetrieveCommentsPort commentsPort) {
        this.commentsPort = commentsPort;
    }

    @Override
    public Flux<CommentListResponse> getAll() {
        return commentsPort.getAll();
    }
}