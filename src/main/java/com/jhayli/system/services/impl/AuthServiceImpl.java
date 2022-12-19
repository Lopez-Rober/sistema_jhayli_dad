package com.jhayli.system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.LoginDto;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.services.AuthService;
import com.jhayli.system.utils.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private JwtUtil jwtUtil;
	
	public String login(LoginDto loginDto) {
    	try {
    		Authentication autenticaicon = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password));
    		
    		UsuarioModel usuario = (UsuarioModel) autenticaicon.getPrincipal();

    		String token = jwtUtil.generate(usuario);

    		return token;
    	} catch (Exception e) {
    		return "dadw";
    	}
	}
	
	public UsuarioModel getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();

		return usuario;
	}
}
