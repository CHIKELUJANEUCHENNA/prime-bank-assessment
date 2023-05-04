package com.example.prime.bank.assessment.service.implementation;

import com.example.prime.bank.assessment.dto.PostDTO;
import com.example.prime.bank.assessment.exception.AppException;
import com.example.prime.bank.assessment.model.Post;
import com.example.prime.bank.assessment.model.User;
import com.example.prime.bank.assessment.repository.PostRepository;
import com.example.prime.bank.assessment.repository.UserRepository;
import com.example.prime.bank.assessment.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {

    private  final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Post createPost(User user, PostDTO postDTO) {
        Date date = Date.valueOf(LocalDate.now());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if(postDTO.getPost_messages().isBlank()) {
            throw new AppException("Post can not be created", HttpStatus.BAD_REQUEST);
        } else {
            Post post = new Post();
            post.setPost_messages(postDTO.getPost_messages());
            post.setPost_Title(postDTO.getPost_Title());
            post.setCreatedAt(date);
            post.setTimestamp(timestamp);
            Post newPost = postRepository.save(post);
            user.getPosts().add(newPost);
            userRepository.save(user);
            return newPost;

        }
    }

    @Override
    public Optional<Post> getAPost(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Long postId, Long userId, PostDTO postDTO) {
        Optional<Post> post = postRepository.findById(postId);
        Date date = Date.valueOf(LocalDate.now());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if(post.isEmpty()) {
            throw new AppException("Post not found", HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findById(userId).get();
        if(user.getPosts().contains(post.get())) {
            post.get().setPost_messages(postDTO.getPost_messages());
            post.get().setCreatedAt(date);
            post.get().setTimestamp(timestamp);
            post.get().setPost_Title(postDTO.getPost_Title());
            postRepository.save(post.get());
        } else {
            throw new AppException("Post can not be updated", HttpStatus.BAD_REQUEST);
        }
        return post.get();
    }

    @Override
    public void deletePost(Long id, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(id);
        if(user.isPresent() && post.isPresent()){
            if(user.get().getPosts().contains(post.get())) {
                postRepository.deleteById(id);
            } else {
                throw new AppException("Post can not be deleted", HttpStatus.BAD_REQUEST);
            }
        }

        else {
            throw new AppException("Post not found", HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
