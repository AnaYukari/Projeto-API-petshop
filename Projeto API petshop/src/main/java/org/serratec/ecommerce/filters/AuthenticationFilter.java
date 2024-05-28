package org.serratec.ecommerce.filters;

import java.io.IOException;

import org.serratec.ecommerce.petshop.entities.User;
import org.serratec.ecommerce.petshop.entities.UserDetailImpl;
import org.serratec.ecommerce.petshop.repositories.UserRepository;
import org.serratec.ecommerce.petshop.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recoveryToken(request);
        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token);

            User user = userRepository.findByEmail(subject).get();

            UserDetailImpl userDetails = new UserDetailImpl(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUser().getEmail(), null,
                    userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
