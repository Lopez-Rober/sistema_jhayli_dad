package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.dtos.RolDto;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.utils.DateUtil;

public class RolMapper implements RowMapper<RolModel> {

	@Override
	public RolModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RolModel rol = new RolModel();
		rol.setId(rs.getString("rol.id"));
		rol.setNombre(rs.getString("rol.nombre"));
		rol.setCreatedAt(rs.getTimestamp("rol.created_at"));
		rol.setUpdatedAt(rs.getTimestamp("rol.updated_at"));
        return rol;
	}
	
	public static RolDto toDto(RolModel rol) {
		RolDto dto = new RolDto();
		dto.id = rol.getId();
		dto.nombre = rol.getNombre();
		dto.createdAt = DateUtil.toISOString(rol.getCreatedAt());
		dto.updatedAt = DateUtil.toISOString(rol.getUpdatedAt());
		return dto;
	}
	
	public static List<RolDto> toDtos(List<RolModel> roles) {
		List<RolDto> dtos = new ArrayList<>();
		
		for (RolModel rol : roles) {
			dtos.add(RolMapper.toDto(rol));
		}
		
		return dtos;
	}
}
