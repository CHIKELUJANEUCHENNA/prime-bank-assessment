package com.example.prime.bank.assessment.controller;

import com.example.prime.bank.assessment.dto.PostDTO;
import com.example.prime.bank.assessment.model.Post;
import com.example.prime.bank.assessment.model.User;
import com.example.prime.bank.assessment.service.PostService;
import com.example.prime.bank.assessment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api",produces = "application/json")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/allPost")
    private ResponseEntity<List<Post>> allPost () {
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping("/createPost/{userId}")
    public Post savePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long userId) {
        User user = userService.findUserById(userId).get();
        return postService.createPost(user, postDTO);
    }

    @GetMapping("/findPost/{id}")
    private ResponseEntity<Post> findPost(@PathVariable("id") Long id){
        Post post = postService.getAPost(id).get();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/updatePost/{id}/{userId}")
    private ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO, @PathVariable("userId") Long userId) {
        Post updatedPost = postService.updatePost(id, userId, postDTO);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        postService.deletePost(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
