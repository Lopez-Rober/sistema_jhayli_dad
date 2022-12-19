package com.jhayli.system.components;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.repositories.UsuarioRepository;
import com.jhayli.system.utils.JwtUtil;
 
 
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
 
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getAccessToken(request);
 
        if (!jwtUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        setAuthenticationContext(token, request);
        
        filterChain.doFilter(request, response);
    }
 
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
 
        return true;
    }
 
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
 
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
 
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
 
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
 
    private UserDetails getUserDetails(String token) {

        String[] jwtSubject = jwtUtil.getSubject(token).split(",");
 
        String id = jwtSubject[0];
        
        UsuarioModel usuario = usuarioRepository.findById(id);
         
        return usuario;
    }
}