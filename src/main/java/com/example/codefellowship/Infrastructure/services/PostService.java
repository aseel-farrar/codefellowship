package com.example.codefellowship.Infrastructure.services;

import com.example.codefellowship.Infrastructure.PostRepository;
import com.example.codefellowship.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
@Autowired
    PostRepository postRepository;

    public Post createPost(String postBody) {
      return postRepository.save(new Post(postBody));
    }

    public Post savePost(Post newPost) {
        return postRepository.save(newPost);
    }
}
