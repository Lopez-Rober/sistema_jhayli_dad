package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.UsuarioModel;

public interface UsuarioRepository {

	public int count(String query);
	
	public List<UsuarioModel> findAll(int limit, int offset, String query);

	public UsuarioModel findById(String id);

	public UsuarioModel findByEmail(String email);
	
	public UsuarioModel findByUsername(String username);

	public void save(UsuarioModel usuario);

	public void update(UsuarioModel usuario);

	public void delete(String id);
	
}
