package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.models.ProductoModel;

public class ProductoMapper implements RowMapper<ProductoModel> {

	@Override
	public ProductoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductoModel producto = new ProductoModel();
		producto.setId(rs.getString("producto.id"));
		producto.setNombre(rs.getString("producto.nombre"));
		producto.setDescripcion(rs.getString("producto.descripcion"));
		producto.setPrecio(rs.getDouble("producto.precio"));
		producto.setCodigoBarras(rs.getString("producto.codigo_barras"));
		producto.setCategoriaId(rs.getString("producto.categoria_id"));
		try {
			producto.setCategoria(new CategoriaMapper().mapRow(rs, 0));
		} catch (Exception e) {
		}
		producto.setMarcaId(rs.getString("producto.marca_id"));
		try {
			producto.setMarca(new MarcaMapper().mapRow(rs, 0));
		} catch (Exception e) {
		}
		producto.setCreatedAt(rs.getTimestamp("producto.created_at"));
		producto.setUpdatedAt(rs.getTimestamp("producto.updated_at"));		
        return producto;
	}
}
