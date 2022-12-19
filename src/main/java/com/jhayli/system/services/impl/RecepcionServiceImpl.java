package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateRecepcionDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.ProveedorModel;
import com.jhayli.system.models.RecepcionModel;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.repositories.ProveedorRepository;
import com.jhayli.system.repositories.RecepcionRepository;
import com.jhayli.system.services.RecepcionService;

@Service
public class RecepcionServiceImpl implements RecepcionService {
	
	@Autowired
    private RecepcionRepository recepcionRepository;


	@Autowired
    private ProveedorRepository proveedorRepository;
	
	@Override
	public int count() {
		return recepcionRepository.count();
	}
	
	@Override
	public List<RecepcionModel> getRecepciones(int limit, int offset) {
		return recepcionRepository.findAll(limit, offset);
	}
	
	@Override
	public RecepcionModel createRecepcion(CreateRecepcionDto recepcionDto) {
		ProveedorModel proveedor = proveedorRepository.findById(recepcionDto.proveedorId);
		
		if (proveedor == null) {
			throw new BadRequestException("Proveedor no existe");
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
		usuario.setRol(null);
		
		RecepcionModel recepcion = new RecepcionModel();
		recepcion.setId(UUID.randomUUID().toString());
		recepcion.setUsuarioId(usuario.getId());
		recepcion.setUsuario(usuario);
		recepcion.setProveedorId(recepcionDto.proveedorId);
		recepcion.setProveedor(proveedor);
		recepcion.setEstado(recepcionDto.estado);
		recepcion.setCreatedAt(new Date());
		recepcion.setUpdatedAt(new Date());

		recepcionRepository.save(recepcion);

		return recepcion;
	}

	@Override
	public RecepcionModel getRecepcion(String recepcionId) {
		RecepcionModel recepcion = recepcionRepository.findById(recepcionId);

		if (recepcion == null) {
			throw new NotFoundException("Recepcion no existe"); 
		}
		
		return recepcion;
	}

	@Override
	public RecepcionModel updateRecepcion(String recepcionId, CreateRecepcionDto recepcionDto) {
		RecepcionModel recepcion = recepcionRepository.findById(recepcionId);

		if (recepcion == null) {
			throw new NotFoundException("Recepcion no existe"); 
		}
		

		if (recepcionDto.estado != null) {
			recepcion.setEstado(recepcionDto.estado);
			recepcion.setUpdatedAt(new Date());
		}
	

		recepcionRepository.update(recepcion);

		return recepcion;
	}

	@Override
	public RecepcionModel deleteRecepcion(String recepcionId) {
		RecepcionModel recepcion = recepcionRepository.findById(recepcionId);

		if (recepcion == null) {
			throw new NotFoundException("Recepcion no existe"); 
		}
		
		recepcionRepository.delete(recepcionId);
		
		return recepcion;
	}

}
