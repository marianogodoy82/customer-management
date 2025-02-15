package com.challenge.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challenge.customermanagement.entity.User;
import com.challenge.customermanagement.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
   @Mock
   private UserRepository userRepository;
   @Mock
   private PasswordEncoder passwordEncoder;

   @InjectMocks
   private UserService userService;

   private User user;

   @BeforeEach
   void setUp() {
      user = User.builder()
          .username("username")
          .password("password")
          .build();
   }

   @Test
   void authenticate() {
      when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
      when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

      final Optional<User> userAuthenticated = userService.authenticate("username", "password");

      assertTrue(userAuthenticated.isPresent());
      assertEquals(user.getUsername(), userAuthenticated.get().getUsername());
      assertEquals(user.getPassword(), userAuthenticated.get().getPassword());
   }

   @Test
   void register() {
      when(userRepository.save(any(User.class))).thenReturn(user);
      when(passwordEncoder.encode(anyString())).thenReturn("password");
      final User userRegistered = userService.register("username", "password");

      assertNotNull(userRegistered);
      assertEquals(user.getUsername(), userRegistered.getUsername());

   }
}