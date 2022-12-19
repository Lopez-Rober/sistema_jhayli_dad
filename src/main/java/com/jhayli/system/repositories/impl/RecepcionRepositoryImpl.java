package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.RecepcionMapper;
import com.jhayli.system.models.RecepcionModel;
import com.jhayli.system.repositories.RecepcionRepository;

@Repository
public class RecepcionRepositoryImpl implements RecepcionRepository {

	@Autowired
	JdbcTemplate template;

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM recepcion WHERE deleted_at IS NULL";
		return template.queryForObject(sql, Integer.class);
	}
	
	@Override
	public List<RecepcionModel> findAll(int limit, int offset) {
		String sql = "SELECT * FROM recepcion INNER JOIN usuario ON recepcion.usuario_id = usuario.id INNER JOIN proveedor ON recepcion.proveedor_id = proveedor.id WHERE recepcion.deleted_at IS NULL ORDER BY recepcion.created_at ASC LIMIT ?, ?";

		return template.query(sql, new RecepcionMapper(), offset, limit);
	}

	@Override
	public RecepcionModel findById(String id) {
		try {
			String sql = "SELECT * FROM recepcion INNER JOIN usuario ON recepcion.usuario_id = usuario.id INNER JOIN proveedor ON recepcion.proveedor_id = proveedor.id WHERE recepcion.id = ? AND recepcion.deleted_at IS NULL";

			return template.queryForObject(sql, new RecepcionMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(RecepcionModel recepcion) {
		String sql = "INSERT INTO recepcion(id, usuario_id, proveedor_id, estado, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?)";
		
		template.update(sql, recepcion.getId(), recepcion.getUsuarioId(), recepcion.getProveedorId(), recepcion.getEstado().ordinal(),
				recepcion.getCreatedAt(), recepcion.getUpdatedAt());
		
	}

	@Override
	public void update(RecepcionModel recepcion) {
		String sql = "UPDATE recepcion SET usuario_id = ?, proveedor_id= ?, estado = ? WHERE id = ?";

		template.update(sql, recepcion.getUsuarioId(), recepcion.getProveedorId(), recepcion.getEstado().ordinal(), recepcion.getId());
		
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE recepcion SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}
}
