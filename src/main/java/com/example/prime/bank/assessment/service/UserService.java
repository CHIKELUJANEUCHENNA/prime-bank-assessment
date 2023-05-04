package com.example.prime.bank.assessment.service;

import com.example.prime.bank.assessment.dto.LoginDTO;
import com.example.prime.bank.assessment.dto.SignUpDTO;
import com.example.prime.bank.assessment.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ResponseEntity<?> loginUser(LoginDTO loginDto);
    ResponseEntity<?> signUp(SignUpDTO signUpDto);
    Optional<User> findUserById(Long id);
}
