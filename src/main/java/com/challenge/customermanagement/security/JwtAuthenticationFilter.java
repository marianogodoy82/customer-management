package com.challenge.customermanagement.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtUtil jwtUtil;
   private final UserDetailsService userDetailsService;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
      Optional.ofNullable(request.getHeader("Authorization"))
            .filter(authHeader -> authHeader.startsWith("Bearer "))
            .ifPresent(authHeader -> {
               final String token = authHeader.substring(7);
               Optional.ofNullable(jwtUtil.extractUsername(token))
                     .filter(username -> SecurityContextHolder.getContext().getAuthentication() == null)
                     .ifPresent(username -> {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
                           UsernamePasswordAuthenticationToken authToken =
                                 new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                           authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                           SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                     });
            });
      filterChain.doFilter(request, response);
   }
}
