package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.models.CategoriaModel;

public class CategoriaMapper implements RowMapper<CategoriaModel> {

	@Override
	public CategoriaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoriaModel categoria = new CategoriaModel();
		categoria.setId(rs.getString("categoria.id"));
		categoria.setNombre(rs.getString("categoria.nombre"));
		categoria.setDescripcion(rs.getString("categoria.descripcion"));
		categoria.setCreatedAt(rs.getTimestamp("categoria.created_at"));
		categoria.setUpdatedAt(rs.getTimestamp("categoria.updated_at"));
        return categoria;
	}

}
