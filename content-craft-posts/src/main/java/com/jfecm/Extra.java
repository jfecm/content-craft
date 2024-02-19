package com.jfecm;

import com.jfecm.model.Post;
import com.jfecm.repository.PostRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Extra implements InitializingBean {
    private static final String AUTHOR_PREFIX = "Author-";
    private static final String TITLE_PREFIX = "Title-";
    private final PostRepository postRepository;

    @Autowired
    public Extra(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public void afterPropertiesSet() {
        postRepository.deleteAll();
        for (int i = 1; i < 50; i++) {
            Post post = new Post();
            post.setAuthor(AUTHOR_PREFIX + i);
            post.setTitle(TITLE_PREFIX + i);
            post.setDatePost(LocalDateTime.now());
            this.postRepository.save(post);
        }
    }
}
