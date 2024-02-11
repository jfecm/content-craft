package com.jfecm.application.core.mapper;

import com.jfecm.adapters.outbound.repository.CommentEntity;
import com.jfecm.application.core.dto.request.CommentCreateRequest;
import com.jfecm.application.core.dto.response.CommentCreateResponse;
import com.jfecm.application.core.dto.response.CommentListResponse;

import java.time.LocalDateTime;

public class CommentMapper {

    private CommentMapper() {

    }

    public static CommentEntity createRequestToEntity(CommentCreateRequest comment) {
        return new CommentEntity(
                null,
                comment.author(),
                comment.text(),
                LocalDateTime.now());
    }

    public static CommentCreateResponse entityToCreateResponse(CommentEntity commentEntity) {
        return new CommentCreateResponse(
                commentEntity.getAuthor(),
                commentEntity.getText(),
                commentEntity.getDate()
        );
    }

    public static CommentListResponse entityToListResponse(CommentEntity commentEntity) {
        return new CommentListResponse(
                commentEntity.getId(),
                commentEntity.getAuthor(),
                commentEntity.getText(),
                commentEntity.getDate()
        );
    }
}
