package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.models.MarcaModel;

public class MarcaMapper implements RowMapper<MarcaModel> {

	@Override
	public MarcaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		MarcaModel marca = new MarcaModel();
		marca.setId(rs.getString("marca.id"));
		marca.setNombre(rs.getString("marca.nombre"));
		marca.setCreatedAt(rs.getTimestamp("marca.created_at"));
		marca.setUpdatedAt(rs.getTimestamp("marca.updated_at"));
		
        return marca;
	}
}
