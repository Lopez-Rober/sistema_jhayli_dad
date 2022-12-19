package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.models.RecepcionDetalleModel;

public class RecepcionDetalleMapper implements RowMapper<RecepcionDetalleModel> {

	@Override
	public RecepcionDetalleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecepcionDetalleModel recepcionDetalle = new RecepcionDetalleModel();
		recepcionDetalle.setId(rs.getString("recepcion_detalle.id"));
		recepcionDetalle.setRecepcionId(rs.getString("recepcion_detalle.recepcion_id"));
		recepcionDetalle.setProductoId(rs.getString("recepcion_detalle.producto_id"));
		try {
			recepcionDetalle.setProducto(new ProductoMapper().mapRow(rs, 0));
		} catch (Exception e) {
		}
		recepcionDetalle.setPrecio(rs.getDouble("recepcion_detalle.precio"));
		recepcionDetalle.setCantidad(rs.getInt("recepcion_detalle.cantidad"));
		recepcionDetalle.setCreatedAt(rs.getTimestamp("recepcion_detalle.created_at"));
		recepcionDetalle.setUpdatedAt(rs.getTimestamp("recepcion_detalle.updated_at"));
        return recepcionDetalle;
	}

}