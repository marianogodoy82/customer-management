package com.challenge.customermanagement.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.customermanagement.entity.User;
import com.challenge.customermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   public Optional<User> authenticate(String username, String password) {
      return userRepository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(password, user.getPassword()));
   }

   public User register(String username, String password) {
      return userRepository.save(User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .build());
   }
}
