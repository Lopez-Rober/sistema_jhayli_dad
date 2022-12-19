package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.ProveedorMapper;
import com.jhayli.system.models.ProveedorModel;
import com.jhayli.system.repositories.ProveedorRepository;

@Repository
public class ProveedorRepositoryImpl implements ProveedorRepository {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<ProveedorModel> findAll(int limit, int offset , String query) {
		String sql = "SELECT * FROM proveedor WHERE deleted_at IS NULL AND proveedor.nombre LIKE ? ORDER BY created_at ASC LIMIT ?, ?";

		return template.query(sql, new ProveedorMapper(),query + "%", offset, limit);
	}
	
	public int count(String query) {
		String sql = "select COUNT(*) from proveedor where deleted_at IS NULL AND proveedor.nombre LIKE ?";
		return template.queryForObject(sql,Integer.class, query+"%");
	}

	@Override
	public ProveedorModel findById(String id) {
		try {
			String sql = "SELECT * FROM proveedor WHERE id = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new ProveedorMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public ProveedorModel findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM proveedor WHERE nombre = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new ProveedorMapper(), nombre);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(ProveedorModel proveedor) {
		String sql = "INSERT INTO proveedor(id, nombre, email, direccion, telefono, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, proveedor.getId(), proveedor.getNombre(), proveedor.getEmail(), proveedor.getDireccion(), proveedor.getTelefono(),
				proveedor.getCreatedAt(), proveedor.getUpdatedAt());
	}

	@Override
	public void update(ProveedorModel proveedor) {
		String sql = "UPDATE proveedor SET nombre = ?, email = ?, direccion = ?, telefono = ? WHERE id = ?";

		template.update(sql, proveedor.getNombre(), proveedor.getEmail(), proveedor.getDireccion(), proveedor.getTelefono(), proveedor.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE proveedor SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}

}
