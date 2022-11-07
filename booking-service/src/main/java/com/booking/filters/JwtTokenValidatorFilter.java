package com.booking.filters;

import com.booking.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Component
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       String jwt = httpServletRequest.getHeader("JWT_TOKEN");

        if(jwt != null) {
            String username = jwtUtil.extractUserName(jwt);
            if(jwtUtil.validateToken( jwt, username)) {
                Claims claims = jwtUtil.extractAllClaims(jwt);
                String role = claims.get("role", String.class);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(username, null, Arrays.asList(new SimpleGrantedAuthority(role)));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            else
                throw new BadCredentialsException("Session Expired");
        }
        else {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }


    }
}
