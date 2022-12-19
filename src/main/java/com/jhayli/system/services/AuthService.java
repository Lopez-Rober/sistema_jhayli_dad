package com.jhayli.system.services;

import com.jhayli.system.dtos.LoginDto;
import com.jhayli.system.models.UsuarioModel;

public interface AuthService {
	public String login(LoginDto loginDto);
	
	public UsuarioModel getUsuario();
}
