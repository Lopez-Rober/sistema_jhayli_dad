package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.RolMapper;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.repositories.RolRepository;

@Repository
public class RolRepositoryImpl implements RolRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public int count(String query) {
		String sql = "SELECT COUNT(*) FROM rol WHERE deleted_at IS NULL AND rol.nombre LIKE ?";
		return template.queryForObject(sql, Integer.class, query + "%");
	}

	@Override
	public List<RolModel> findAll(int limit, int offset, String query) {
		String sql = "SELECT * FROM rol WHERE deleted_at IS NULL AND rol.nombre LIKE ? ORDER BY created_at ASC LIMIT ?, ?";

		return template.query(sql, new RolMapper(),  query + "%", offset, limit);
	}

	@Override
	public RolModel findById(String id) {
		try {
			String sql = "SELECT * FROM rol WHERE id = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new RolMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public RolModel findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM rol WHERE nombre = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new RolMapper(), nombre);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(RolModel rol) {
		String sql = "INSERT INTO rol(id, nombre, created_at, updated_at) VALUES(?, ?, ?, ?)";

		template.update(sql, rol.getId(), rol.getNombre(),
				rol.getCreatedAt(), rol.getUpdatedAt());
	}

	@Override
	public void update(RolModel rol) {
		String sql = "UPDATE rol SET nombre = ? WHERE id = ?";

		template.update(sql, rol.getNombre(), rol.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE rol SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}

}
