package com.jfecm.service;

import com.jfecm.controller.request.PostRequestCreate;
import com.jfecm.controller.response.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse create(PostRequestCreate post);

    List<PostResponse> getAll();

}