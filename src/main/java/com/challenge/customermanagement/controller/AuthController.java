package com.challenge.customermanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.customermanagement.dto.AuthRequest;
import com.challenge.customermanagement.dto.AuthResponse;
import com.challenge.customermanagement.entity.User;
import com.challenge.customermanagement.security.JwtUtil;
import com.challenge.customermanagement.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private final JwtUtil jwtUtil;
   private final UserService userService;

   @PostMapping("/login")
   public AuthResponse login (@RequestBody AuthRequest request) {

      return userService.authenticate(request.username(), request.password())
            .map(user -> AuthResponse.builder()
                                     .token(jwtUtil.generateToken(user.getUsername()))
                                     .build())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
   }

   @PostMapping("/signup")
   public  AuthResponse signup(@RequestBody AuthRequest request) {
      final User user = userService.register(request.username(), request.password());

      return AuthResponse.builder()
                         .token(jwtUtil.generateToken(user.getUsername()))
                         .build();
   }
}
