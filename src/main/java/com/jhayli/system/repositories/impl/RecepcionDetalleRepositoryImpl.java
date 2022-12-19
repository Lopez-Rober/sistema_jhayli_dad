package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.RecepcionDetalleMapper;
import com.jhayli.system.models.RecepcionDetalleModel;
import com.jhayli.system.repositories.RecepcionDetalleRepository;

@Repository
public class RecepcionDetalleRepositoryImpl implements RecepcionDetalleRepository {

	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<RecepcionDetalleModel> findAll(String recepcionId, int limit, int offset) {
		String sql = "SELECT * FROM recepcion_detalle INNER JOIN producto ON recepcion_detalle.producto_id = producto.id WHERE recepcion_detalle.recepcion_id = ? AND recepcion_detalle.deleted_at IS NULL ORDER BY recepcion_detalle.created_at ASC LIMIT ?, ?";

		return template.query(sql, new RecepcionDetalleMapper(), recepcionId, offset, limit);
	}

	@Override
	public RecepcionDetalleModel findById(String id) {
		try {
			String sql = "SELECT * FROM recepcion_detalle INNER JOIN producto ON recepcion_detalle.producto_id = producto.id WHERE recepcion_detalle.id = ? AND recepcion_detalle.deleted_at IS NULL";

			return template.queryForObject(sql, new RecepcionDetalleMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(RecepcionDetalleModel recepcion) {
		String sql = "INSERT INTO recepcion_detalle(id, recepcion_id, producto_id, precio, cantidad, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, recepcion.getId(), recepcion.getRecepcionId(), recepcion.getProductoId(), recepcion.getPrecio(), recepcion.getCantidad(),
				recepcion.getCreatedAt(), recepcion.getUpdatedAt());
	}

	@Override
	public void update(RecepcionDetalleModel recepcion) {
		String sql = "UPDATE recepcion_detalle SET producto_id = ?, precio = ?, cantidad = ? WHERE id = ?";

		template.update(sql, recepcion.getProductoId(), recepcion.getPrecio(), recepcion.getCantidad(), recepcion.getId());
		
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE recepcion_detalle SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
		
	}

}
