package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateProveedorDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.ProveedorModel;
import com.jhayli.system.repositories.ProveedorRepository;
import com.jhayli.system.services.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	@Autowired
    private ProveedorRepository proveedorRepository;

	public int count(String query) {
		return proveedorRepository.count(query);
	}

	@Override
	public List<ProveedorModel> getProveedores(int limit, int offset , String query) {
		return proveedorRepository.findAll(limit, offset , query);
	}


	@Override
	public ProveedorModel createProveedor(CreateProveedorDto proveedorDto) {
		ProveedorModel proveedorNombreExists = proveedorRepository.findByNombre(proveedorDto.nombre);
		
		if (proveedorNombreExists != null) {
			throw new BadRequestException("Nombre ya existe"); 
		}
		
		ProveedorModel proveedor = new ProveedorModel();
		proveedor.setId(UUID.randomUUID().toString());
		proveedor.setNombre(proveedorDto.nombre);
		proveedor.setEmail(proveedorDto.email);
		proveedor.setDireccion(proveedorDto.direccion);
		proveedor.setTelefono(proveedorDto.telefono);
		proveedor.setCreatedAt(new Date());
		proveedor.setUpdatedAt(new Date());

		proveedorRepository.save(proveedor);

		return proveedor;
	}
	
	@Override
	public ProveedorModel getProveedor(String proveedorId) {
		ProveedorModel proveedor = proveedorRepository.findById(proveedorId);

		if (proveedor == null) {
			throw new NotFoundException("Proveedor no existe"); 
		}
		
		return proveedor;
	}

	@Override
	public ProveedorModel updateProveedor(String proveedorId, CreateProveedorDto proveedorDto) {		
		ProveedorModel proveedor = proveedorRepository.findById(proveedorId);

		if (proveedor == null) {
			throw new NotFoundException("Proveedor no existe"); 
		}
		
		boolean isNewProveedorNombre= proveedorDto.nombre != null && !proveedorDto.nombre.equals("")
				&& proveedorDto.nombre.equals(proveedor.getNombre()) == false;

		if (isNewProveedorNombre) {
			ProveedorModel proveedorNombreExists = proveedorRepository.findByNombre(proveedorDto.nombre);

			if (proveedorNombreExists != null) {
				throw new BadRequestException("Nombre ya existe"); 
			}
			
			proveedor.setNombre(proveedorDto.nombre);
			proveedor.setUpdatedAt(new Date());
		}
		
		if (proveedorDto.email != null && !proveedorDto.email.equals("")) {
			proveedor.setEmail(proveedorDto.email);
			proveedor.setUpdatedAt(new Date());
		}
		
		if (proveedorDto.direccion != null && !proveedorDto.direccion.equals("")) {
			proveedor.setDireccion(proveedorDto.direccion);
			proveedor.setUpdatedAt(new Date());
		}

		if (proveedorDto.telefono != null && !proveedorDto.telefono.equals("")) {
			proveedor.setTelefono(proveedorDto.telefono);
			proveedor.setUpdatedAt(new Date());
		}
		
		proveedorRepository.update(proveedor);

		return proveedor;
	}
	
	@Override
	public ProveedorModel deleteProveedor(String proveedorId) {
		ProveedorModel proveedor = proveedorRepository.findById(proveedorId);

		if (proveedor == null) {
			throw new NotFoundException("Proveedor no existe"); 
		}
		
		proveedorRepository.delete(proveedorId);
		
		return proveedor;
	}

}
