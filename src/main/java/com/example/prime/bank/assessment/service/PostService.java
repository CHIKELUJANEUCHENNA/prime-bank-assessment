package com.example.prime.bank.assessment.service;

import com.example.prime.bank.assessment.dto.PostDTO;
import com.example.prime.bank.assessment.model.Post;
import com.example.prime.bank.assessment.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(User user, PostDTO postDTO);
    Optional<Post> getAPost(Long id);
    Post updatePost(Long postId, Long userId, PostDTO postDTO);
    void deletePost(Long id, Long userId);
    List<Post> getAllPost();
}
