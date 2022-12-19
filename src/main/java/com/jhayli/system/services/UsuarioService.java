package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateUsuarioDto;
import com.jhayli.system.models.UsuarioModel;

public interface UsuarioService {
	
	public int count(String query);

	public List<UsuarioModel> getUsuarios(int limit, int offset, String query);
	
	public UsuarioModel createUsuario(CreateUsuarioDto usuarioDto);

	public UsuarioModel getUsuario(String usuarioId);
		
	public UsuarioModel updateUsuario(String usuarioId, CreateUsuarioDto usuarioDto);

	public UsuarioModel deleteUsuario(String usuarioId);

}
