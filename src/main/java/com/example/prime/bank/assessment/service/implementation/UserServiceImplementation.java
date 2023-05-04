package com.example.prime.bank.assessment.service.implementation;

import com.example.prime.bank.assessment.dto.LoginDTO;
import com.example.prime.bank.assessment.dto.SignUpDTO;
import com.example.prime.bank.assessment.exception.AppException;
import com.example.prime.bank.assessment.exception.UserRegistrationException;
import com.example.prime.bank.assessment.model.User;
import com.example.prime.bank.assessment.repository.UserRepository;
import com.example.prime.bank.assessment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<?> loginUser(LoginDTO loginDto) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        throw new AppException("Incorrect password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> signUp(SignUpDTO signUpDto) {
        Optional<User> user = userRepository.findUserByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new UserRegistrationException("User with email " + user.get().getEmail() + " already exists");
        } else {
            User newUser = new User();
            newUser.setEmail(signUpDto.getEmail());
            newUser.setAuthor(signUpDto.getAuthor());
            newUser.setPassword(signUpDto.getPassword());
            newUser.setPhoneNumber(signUpDto.getPhoneNumber());
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
