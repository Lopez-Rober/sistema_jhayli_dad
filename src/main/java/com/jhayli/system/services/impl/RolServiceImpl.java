package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateRolDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.repositories.RolRepository;
import com.jhayli.system.services.RolService;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
    private RolRepository rolRepository;

	@Override
	public int count(String query) {
		return rolRepository.count(query);
	}

	@Override
	public List<RolModel> getRoles(int limit, int offset, String query) {
		return rolRepository.findAll(limit, offset, query);
	}

	@Override
	public RolModel createRol(CreateRolDto rolDto) {
		RolModel rolNombreExists = rolRepository.findByNombre(rolDto.nombre);
		
		if (rolNombreExists != null) {
			throw new BadRequestException("Nombre ya existe"); 
		}
		
		RolModel rol = new RolModel();
		rol.setId(UUID.randomUUID().toString());
		rol.setNombre(rolDto.nombre);
		rol.setCreatedAt(new Date());
		rol.setUpdatedAt(new Date());

		rolRepository.save(rol);

		return rol;
	}
	
	@Override
	public RolModel getRol(String rolId) {
		RolModel rol = rolRepository.findById(rolId);

		if (rol == null) {
			throw new NotFoundException("Rol no existe"); 
		}
		
		return rol;
	}

	@Override
	public RolModel updateRol(String rolId, CreateRolDto rolDto) {		
		RolModel rol = rolRepository.findById(rolId);

		if (rol == null) {
			throw new NotFoundException("Rol no existe"); 
		}
		
		boolean isNewRolNombre= rolDto.nombre != null && !rolDto.nombre.equals("")
				&& rolDto.nombre.equals(rol.getNombre()) == false;

		if (isNewRolNombre) {
			RolModel rolNombreExists = rolRepository.findByNombre(rolDto.nombre);

			if (rolNombreExists != null) {
				throw new BadRequestException("Nombre ya existe"); 
			}
			
			rol.setNombre(rolDto.nombre);
			rol.setUpdatedAt(new Date());
		}

		rolRepository.update(rol);

		return rol;
	}
	
	@Override
	public RolModel deleteRol(String rolId) {
		RolModel rol = rolRepository.findById(rolId);

		if (rol == null) {
			throw new NotFoundException("Rol no existe"); 
		}
		
		rolRepository.delete(rolId);
		
		return rol;
	}

}
