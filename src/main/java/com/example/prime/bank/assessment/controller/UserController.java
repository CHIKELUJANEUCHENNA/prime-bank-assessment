package com.example.prime.bank.assessment.controller;

import com.example.prime.bank.assessment.dto.LoginDTO;
import com.example.prime.bank.assessment.dto.SignUpDTO;
import com.example.prime.bank.assessment.model.User;
import com.example.prime.bank.assessment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = "application/json")
public class UserController {
    private final UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpDto) {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDto) {
        return userService.loginUser(loginDto);
    }

    @GetMapping("/findUserById/{id}")
    private ResponseEntity<User> findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
