package com.jhayli.system.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.jhayli.system.dtos.UsuarioDto;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.utils.DateUtil;

public class UsuarioMapper implements RowMapper<UsuarioModel> {
	@Override
	public UsuarioModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setId(rs.getString("usuario.id"));
		usuario.setNombre(rs.getString("usuario.nombre"));
		usuario.setApellidoPaterno(rs.getString("usuario.apellido_paterno"));
		usuario.setApellidoMaterno(rs.getString("usuario.apellido_materno"));
		usuario.setEmail(rs.getString("usuario.email"));
		usuario.setUsername(rs.getString("usuario.username"));
		usuario.setPassword(rs.getString("usuario.password"));
		usuario.setRolId(rs.getString("usuario.rol_id"));
		try {
			usuario.setRol(new RolMapper().mapRow(rs, 0));			
		} catch (Exception error) {	
		}
		usuario.setCreatedAt(rs.getTimestamp("usuario.created_at"));
		usuario.setUpdatedAt(rs.getTimestamp("usuario.updated_at"));
        return usuario;
	}
	
	public static UsuarioDto toDto(UsuarioModel usuario) {
		UsuarioDto dto = new UsuarioDto();
		dto.id = usuario.getId();
		dto.nombre = usuario.getNombre();
		dto.apellidoPaterno = usuario.getApellidoPaterno();
		dto.apellidoMaterno = usuario.getApellidoMaterno();
		dto.email = usuario.getEmail();
		dto.username = usuario.getUsername();
		dto.rolId = usuario.getRolId();
		if (usuario.getRol() != null) {
			dto.rol = RolMapper.toDto(usuario.getRol());
		}
		dto.updatedAt = DateUtil.toISOString(usuario.getUpdatedAt());
		dto.createdAt = DateUtil.toISOString(usuario.getCreatedAt());
		dto.updatedAt = DateUtil.toISOString(usuario.getUpdatedAt());
		return dto;
	}
	
	public static List<UsuarioDto> toDtos(List<UsuarioModel> usuarios) {
		List<UsuarioDto> dtos = new ArrayList<>();
		
		for (UsuarioModel usuario : usuarios) {
			dtos.add(UsuarioMapper.toDto(usuario));
		}
		
		return dtos;
	}
}
