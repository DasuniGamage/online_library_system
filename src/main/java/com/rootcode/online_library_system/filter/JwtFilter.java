package com.rootcode.online_library_system.filter;

import com.rootcode.online_library_system.service.JWTService;
import com.rootcode.online_library_system.service.serviceIMPL.LibrarySystemUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //what we get from client side=> Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMzFAY29tIiwiaWF0IjoxNzQwMTMzMzg5LCJleHAiOjE3NDAxMzk4Njl9.ZY7DVBIyfBgKrL0uSOf0GEa1wW8jEF_M7z-6CTIp9bQ

        String authHeader= request.getHeader("Authorization");
        String token =  null;
        String username= null;

        if(authHeader!= null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username= jwtService.extractUsername(token);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = context.getBean(LibrarySystemUserDetailsService.class).loadUserByUsername(username);

            if (jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
