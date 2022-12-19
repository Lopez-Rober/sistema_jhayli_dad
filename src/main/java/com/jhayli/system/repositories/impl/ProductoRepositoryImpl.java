package com.jhayli.system.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jhayli.system.mappers.ProductoMapper;
import com.jhayli.system.models.ProductoModel;
import com.jhayli.system.repositories.ProductoRepository;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public int count (String query) {
		String sql = "select COUNT(*) from producto where deleted_at IS NULL AND producto.nombre LIKE ?";
		return template.queryForObject(sql,Integer.class,query +"%");
		
	}

	@Override
	public List<ProductoModel> findAll(int limit, int offset , String query) {
		String sql = "SELECT * FROM producto INNER JOIN categoria ON producto.categoria_id = categoria.id INNER JOIN marca ON producto.marca_id = marca.id WHERE producto.deleted_at IS NULL AND producto.nombre LIKE ? ORDER BY producto.created_at ASC LIMIT ?, ?";

		return template.query(sql, new ProductoMapper(),query + "%", offset, limit);

	}

	@Override
	public ProductoModel findById(String id) {
		try {
			String sql = "SELECT * FROM producto INNER JOIN categoria ON producto.categoria_id = categoria.id INNER JOIN marca ON producto.marca_id = marca.id WHERE producto.id = ? AND producto.deleted_at IS NULL";

			return template.queryForObject(sql, new ProductoMapper(), id);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public ProductoModel findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM producto WHERE nombre = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new ProductoMapper(), nombre);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public ProductoModel findByCodigoBarras(String codigoBarras) {
		try {
			String sql = "SELECT * FROM producto WHERE codigo_barras = ? AND deleted_at IS NULL";

			return template.queryForObject(sql, new ProductoMapper(), codigoBarras);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public void save(ProductoModel producto) {
		String sql = "INSERT INTO producto(id, nombre, descripcion, precio, codigo_barras, marca_id, categoria_id, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
				producto.getCodigoBarras(), producto.getMarcaId(), producto.getCategoriaId(), producto.getCreatedAt(),
				producto.getUpdatedAt());
	}

	@Override
	public void update(ProductoModel producto) {
		String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?";

		template.update(sql, producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE producto SET deleted_at = NOW() WHERE id = ?";

		template.update(sql, id);
	}

	
}
