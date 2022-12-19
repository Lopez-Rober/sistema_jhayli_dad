package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.components.Encoder;
import com.jhayli.system.dtos.CreateUsuarioDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.repositories.RolRepository;
import com.jhayli.system.repositories.UsuarioRepository;
import com.jhayli.system.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

	@Autowired
    private RolRepository rolRepository;
	
	@Autowired
	private Encoder encoder;

	@Override
	public int count(String query) {
		return usuarioRepository.count(query);
	}
	
	@Override
	public List<UsuarioModel> getUsuarios(int limit, int offset, String query) {
		return usuarioRepository.findAll(limit, offset, query);
	}

	@Override
	public UsuarioModel createUsuario(CreateUsuarioDto usuarioDto) {
		UsuarioModel usuarioEmailExists = usuarioRepository.findByEmail(usuarioDto.email);
		
		if (usuarioEmailExists != null) {
			throw new BadRequestException("Email ya existe");
		}
		
		RolModel rol = rolRepository.findById(usuarioDto.rolId);
		
		if (rol == null) {
			throw new BadRequestException("Rol no existe");
		}
		
		UsuarioModel usuario = new UsuarioModel();
		usuario.setId(UUID.randomUUID().toString());
		usuario.setNombre(usuarioDto.nombre);
		usuario.setApellidoPaterno(usuarioDto.apellidoPaterno);
		usuario.setApellidoMaterno(usuarioDto.apellidoMaterno);
		usuario.setEmail(usuarioDto.email);
		usuario.setUsername(usuarioDto.username);
		usuario.setPassword(encoder.encodePassword(usuarioDto.password));
		usuario.setRolId(usuarioDto.rolId);
		usuario.setRol(rol);
		usuario.setCreatedAt(new Date());
		usuario.setUpdatedAt(new Date());

		usuarioRepository.save(usuario);

		return usuario;
	}

	@Override
	public UsuarioModel getUsuario(String usuarioId) {
		UsuarioModel usuario = usuarioRepository.findById(usuarioId);

		if (usuario == null) {
			throw new NotFoundException("Usuario no existe"); 
		}
		
		return usuario;
	}

	@Override
	public UsuarioModel updateUsuario(String usuarioId, CreateUsuarioDto usuarioDto) {
		UsuarioModel usuario = usuarioRepository.findById(usuarioId);

		if (usuario == null) {
			throw new NotFoundException("Usuario no existe");
		}

		if (usuarioDto.nombre != null && !usuarioDto.nombre.equals("")) {
			usuario.setNombre(usuarioDto.nombre);
			usuario.setUpdatedAt(new Date());
		}
		
		if (usuarioDto.apellidoPaterno != null && !usuarioDto.apellidoPaterno.equals("")) {
			usuario.setApellidoPaterno(usuarioDto.apellidoPaterno);
			usuario.setUpdatedAt(new Date());
		}
		
		if (usuarioDto.apellidoMaterno != null && !usuarioDto.apellidoMaterno.equals("")) {
			usuario.setApellidoMaterno(usuarioDto.apellidoMaterno);
			usuario.setUpdatedAt(new Date());
		}
		
		boolean isNewUsuarioEmail = usuarioDto.email != null && !usuarioDto.email.equals("")
				&& usuarioDto.email.equals(usuario.getEmail()) == false;

		if (isNewUsuarioEmail) {
			UsuarioModel usuarioEmailExists = usuarioRepository.findByEmail(usuarioDto.email);
			
			if (usuarioEmailExists != null) {
				throw new BadRequestException("Email ya existe");
			}
			
			usuario.setEmail(usuarioDto.email);
			usuario.setUpdatedAt(new Date());
		}
		
		boolean isNewUsuarioUsername = usuarioDto.username != null && !usuarioDto.username.equals("")
				&& usuarioDto.username.equals(usuario.getUsername()) == false;

		if (isNewUsuarioUsername) {
			UsuarioModel usuarioUsernameExists = usuarioRepository.findByEmail(usuarioDto.username);
			
			if (usuarioUsernameExists != null) {
				throw new BadRequestException("Username ya existe");
			}
			
			usuario.setUsername(usuarioDto.username);
			usuario.setUpdatedAt(new Date());
		}
		
		
		if (usuarioDto.password != null && !usuarioDto.password.equals("")) {
			usuario.setPassword(encoder.encodePassword(usuarioDto.password));
			usuario.setUpdatedAt(new Date());
		}
		
		if (usuarioDto.rolId != null && !usuarioDto.rolId.equals("")) {
			RolModel rol = rolRepository.findById(usuarioDto.rolId);
			
			if (rol == null) {
				throw new BadRequestException("Rol no existe");
			}
			
			usuario.setRolId(usuarioDto.rolId);
			usuario.setRol(rol);
			usuario.setUpdatedAt(new Date());
		}
		
		
		usuarioRepository.update(usuario);

		return usuario;
	}

	@Override
	public UsuarioModel deleteUsuario(String usuarioId) {
		UsuarioModel usuario = usuarioRepository.findById(usuarioId);

		if (usuario == null) {
			throw new NotFoundException("Usuario no existe"); 
		}
		
		usuarioRepository.delete(usuarioId);
		
		return usuario;
	}
	
}
