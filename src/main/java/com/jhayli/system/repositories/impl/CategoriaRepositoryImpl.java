package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.CategoriaMapper;
import com.jhayli.system.models.CategoriaModel;
import com.jhayli.system.repositories.CategoriaRepository;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public int count(String query) {
		String sql = "SELECT COUNT(*) FROM categoria WHERE deleted_at IS NULL AND categoria.nombre LIKE ?";
		return template.queryForObject(sql, Integer.class, query + "%");
	}
	
	@Override
	public List<CategoriaModel> findAll(int limit, int offset, String query) {
		String sql = "SELECT * FROM categoria WHERE deleted_at IS NULL AND categoria.nombre LIKE ? ORDER BY created_at ASC LIMIT ?, ?";

		return template.query(sql, new CategoriaMapper(),  query + "%", offset, limit);
	}

	@Override
	public CategoriaModel findById(String id) {
		try {
			String sql = "SELECT * FROM categoria WHERE id = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new CategoriaMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public CategoriaModel findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM categoria WHERE nombre = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new CategoriaMapper(), nombre);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(CategoriaModel categoria) {
		String sql = "INSERT INTO categoria(id, nombre, descripcion, created_at, updated_at) VALUES(?, ?, ?, ?, ?)";

		template.update(sql, categoria.getId(), categoria.getNombre(), categoria.getDescripcion(),
				categoria.getCreatedAt(), categoria.getUpdatedAt());
	}

	@Override
	public void update(CategoriaModel categoria) {
		String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";

		template.update(sql, categoria.getNombre(), categoria.getDescripcion(), categoria.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE categoria SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}

}
