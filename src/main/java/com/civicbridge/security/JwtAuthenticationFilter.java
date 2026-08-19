package com.civicbridge.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader("Authorization");

        String jwtToken = null;

        String username = null;


        // =========================================
        // READ AUTHORIZATION HEADER
        // =========================================

        if (authorizationHeader != null
                && authorizationHeader.startsWith("Bearer ")) {

            jwtToken =
                    authorizationHeader
                            .substring(7)
                            .trim();

            try {

                // =========================================
                // VALIDATE ACCESS TOKEN
                // =========================================

                if (jwtService.validateAccessToken(
                        jwtToken)) {

                    username =
                            jwtService.extractUsername(
                                    jwtToken
                            );
                }

            } catch (Exception exception) {

                System.out.println(
                        "JWT Filter Error: "
                                + exception.getMessage()
                );
            }
        }


        // =========================================
        // SET SECURITY CONTEXT
        // =========================================

        if (username != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(username);


            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );


            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );


            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication
                    );


            System.out.println(
                    "JWT Authentication Successful for: "
                            + username
            );
        }


        filterChain.doFilter(
                request,
                response
        );
    }
}