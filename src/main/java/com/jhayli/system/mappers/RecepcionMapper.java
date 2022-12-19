package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.dtos.RecepcionDto;
import com.jhayli.system.enums.RecepcionEstadoEnum;
import com.jhayli.system.models.RecepcionModel;
import com.jhayli.system.utils.DateUtil;

public class RecepcionMapper implements RowMapper<RecepcionModel> {

	@Override
	public RecepcionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecepcionModel recepcion = new RecepcionModel();
		recepcion.setId(rs.getString("recepcion.id"));
		recepcion.setUsuarioId(rs.getString("recepcion.usuario_id"));
		try {
			recepcion.setUsuario(new UsuarioMapper().mapRow(rs, 0));
		} catch (Exception e) {
		}
		recepcion.setProveedorId(rs.getString("recepcion.proveedor_id"));
		try {
			recepcion.setProveedor(new ProveedorMapper().mapRow(rs, 0));
		} catch (Exception e) {
		}
		recepcion.setEstado(RecepcionEstadoEnum.values()[rs.getInt("recepcion.estado")]);
		recepcion.setCreatedAt(rs.getTimestamp("recepcion.created_at"));
		recepcion.setUpdatedAt(rs.getTimestamp("recepcion.updated_at"));
        return recepcion;
	}
	
	public static RecepcionDto toDto(RecepcionModel recepcion) {
		RecepcionDto dto = new RecepcionDto();
		dto.id = recepcion.getId();
		dto.usuarioId = recepcion.getUsuarioId();
		if (recepcion.getUsuario() != null) {
			dto.usuario = UsuarioMapper.toDto(recepcion.getUsuario());
		}
		dto.proveedorId = recepcion.getProveedorId();
		dto.proveedor = recepcion.getProveedor();
		dto.estado = recepcion.getEstado();
		dto.createdAt = DateUtil.toISOString(recepcion.getCreatedAt());
		dto.updatedAt = DateUtil.toISOString(recepcion.getUpdatedAt());
		return dto;
	}
	
	public static List<RecepcionDto> toDtos(List<RecepcionModel> recepciones) {
		List<RecepcionDto> dtos = new ArrayList<>();
		
		for (RecepcionModel recepcion : recepciones) {
			dtos.add(RecepcionMapper.toDto(recepcion));
		}
		
		return dtos;
	}

}
