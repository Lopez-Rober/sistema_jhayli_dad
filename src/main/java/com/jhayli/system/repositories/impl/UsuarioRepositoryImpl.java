package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.UsuarioMapper;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.repositories.UsuarioRepository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

	@Autowired
	JdbcTemplate template;

	@Override
	public int count(String query) {
		String sql = "SELECT COUNT(*) FROM usuario WHERE usuario.username LIKE ?";

		return template.queryForObject(sql, Integer.class, query + "%");
	}

	
	@Override
	public List<UsuarioModel> findAll(int limit, int offset, String query) {
		String sql = "SELECT * FROM usuario INNER JOIN rol ON usuario.rol_id = rol.id WHERE usuario.deleted_at IS NULL AND usuario.username LIKE ? ORDER BY usuario.created_at ASC LIMIT ?, ?";
		return template.query(sql, new UsuarioMapper(), query + "%", offset, limit);
	}

	@Override
	public UsuarioModel findById(String id) {
		try {
			String sql = "SELECT * FROM usuario INNER JOIN rol ON usuario.rol_id = rol.id WHERE usuario.id = ? AND usuario.deleted_at IS NULL";

			return template.queryForObject(sql, new UsuarioMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public UsuarioModel findByEmail(String email) {
		try {
			String sql = "SELECT * FROM usuario WHERE email = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new UsuarioMapper(), email);
		} catch (Exception error) {
			return null;
		}
	}
	
	@Override
	public UsuarioModel findByUsername(String username) {
		try {
			String sql = "SELECT * FROM usuario WHERE username = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new UsuarioMapper(), username);
		} catch (Exception error) {
			return null;
		}
	}
	
	@Override
	public void save(UsuarioModel usuario) {
		String sql = "INSERT INTO usuario(id, nombre, apellido_paterno, apellido_materno, email, username, password, rol_id, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, usuario.getId(), usuario.getNombre(), usuario.getApellidoPaterno(), usuario.getApellidoMaterno(), usuario.getEmail(),
				usuario.getUsername(), usuario.getPassword(), usuario.getRolId(), usuario.getCreatedAt(), usuario.getUpdatedAt());
	}

	@Override
	public void update(UsuarioModel usuario) {
		String sql = "UPDATE usuario SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, email = ?, username = ?, password = ?, rol_id = ? WHERE id = ?";

		template.update(sql, usuario.getNombre(), usuario.getApellidoPaterno(), usuario.getApellidoMaterno(), usuario.getEmail(), usuario.getUsername(), usuario.getPassword(), usuario.getRolId(), usuario.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE usuario SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}

}
