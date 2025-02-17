package com.challenge.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.challenge.customermanagement.entity.User;
import com.challenge.customermanagement.repository.UserRepository;

class UserDetailsServiceImplTest {

   private UserRepository userRepository = mock(UserRepository.class);
   @InjectMocks
   private UserDetailsServiceImpl userDetailsServiceImpl;

   private User user;

   @BeforeEach
   void setUp() {
      userRepository = mock(UserRepository.class);
      userDetailsServiceImpl = new UserDetailsServiceImpl(userRepository);
      user = User.builder()
          .id(UUID.randomUUID())
          .username("username")
          .password("password")
          .build();
   }

   @Test
   void loadUserByUsername() {
      when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
      final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("username");

      assertNotNull(userDetails);
      assertEquals(user.getUsername(), userDetails.getUsername());
      assertEquals(user.getPassword(), userDetails.getPassword());
   }

   @Test
   void loadNonexistentUserByUsername() {
      when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
      assertThrows(UsernameNotFoundException.class, () -> userDetailsServiceImpl.loadUserByUsername("username"));
   }
}