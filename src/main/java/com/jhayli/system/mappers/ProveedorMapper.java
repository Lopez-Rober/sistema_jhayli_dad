package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.models.ProveedorModel;

public class ProveedorMapper implements RowMapper<ProveedorModel> {

	@Override
	public ProveedorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProveedorModel proveedor = new ProveedorModel();
		proveedor.setId(rs.getString("proveedor.id"));
		proveedor.setNombre(rs.getString("proveedor.nombre"));
		proveedor.setEmail(rs.getString("proveedor.email"));
		proveedor.setDireccion(rs.getString("proveedor.direccion"));
		proveedor.setTelefono(rs.getString("proveedor.telefono"));
		proveedor.setCreatedAt(rs.getTimestamp("proveedor.created_at"));
		proveedor.setUpdatedAt(rs.getTimestamp("proveedor.updated_at"));
		
        return proveedor;
	}
}
