package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.MarcaMapper;
import com.jhayli.system.models.MarcaModel;
import com.jhayli.system.repositories.MarcaRepository;

@Repository
public class MarcaRepositoryImpl implements MarcaRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public int count(String query) {
		String sql = "SELECT COUNT(*) FROM marca WHERE deleted_at IS NULL AND marca.nombre LIKE ?";
		return template.queryForObject(sql, Integer.class, query + "%");
	}
	
	@Override
	public List<MarcaModel> findAll(int limit, int offset, String query) {
		String sql = "SELECT * FROM marca WHERE deleted_at IS NULL AND marca.nombre LIKE ? ORDER BY created_at ASC LIMIT ?, ?";
		return template.query(sql, new MarcaMapper(),  query + "%", offset, limit);
	}

	@Override
	public MarcaModel findById(String id) {
		try {
			String sql = "SELECT * FROM marca WHERE id = ? AND deleted_at IS NULL";
	        return template.queryForObject(sql, new MarcaMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public MarcaModel findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM marca WHERE nombre = ? AND deleted_at IS NULL";
	        return template.queryForObject(sql, new MarcaMapper(), nombre);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(MarcaModel marca) {
		String sql =  "INSERT INTO marca(id, nombre, created_at, updated_at) VALUES(?, ?, ?, ?)";
		
		template.update(sql, marca.getId(), marca.getNombre(), marca.getCreatedAt(), marca.getUpdatedAt());

		
	}

	@Override
	public void update(MarcaModel marca) {
		String sql =  "UPDATE marca SET nombre = ? WHERE id = ?";
		
        template.update(sql, marca.getNombre(), marca.getId());
		
	}

	@Override
	public void delete(String id) {
		String sql =  "UPDATE marca SET deleted_at = NOW() WHERE id = ?";
		
        template.update(sql, id);
	}

}
