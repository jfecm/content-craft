package com.jfecm.service.impl;

import com.jfecm.controller.request.PostRequestCreate;
import com.jfecm.controller.response.PostResponse;
import com.jfecm.model.Post;
import com.jfecm.repository.PostRepository;
import com.jfecm.service.PostService;
import com.jfecm.service.mapper.PostEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponse create(PostRequestCreate post) {
        Post toSave = PostEntityMapper.toEntity(post);
        Post saved = postRepository.save(toSave);
        return PostEntityMapper.toResponse(saved);
    }

    @Override
    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(PostEntityMapper::toResponse)
                .toList();
    }
}