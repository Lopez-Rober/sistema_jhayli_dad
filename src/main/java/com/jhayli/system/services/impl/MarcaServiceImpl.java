package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateMarcaDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.MarcaModel;
import com.jhayli.system.repositories.MarcaRepository;
import com.jhayli.system.services.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
	
	@Autowired
    private MarcaRepository marcaRepository;

	@Override
	public int count(String query) {
		return marcaRepository.count(query);
	}
	
	
	@Override
	public List<MarcaModel> getMarcas(int limit, int offset, String query) {
		return marcaRepository.findAll(limit, offset, query);
	}

	@Override
	public MarcaModel createMarca(CreateMarcaDto marcaDto) {
		MarcaModel marcaNombreExists = marcaRepository.findByNombre(marcaDto.nombre);
		
		if (marcaNombreExists != null) {
			throw new BadRequestException("Nombre ya existe"); 
		}
		
		MarcaModel marca = new MarcaModel();
		marca.setId(UUID.randomUUID().toString());
		marca.setNombre(marcaDto.nombre);
		marca.setCreatedAt(new Date());
		marca.setUpdatedAt(new Date());

		marcaRepository.save(marca);

		return marca;
	}
	
	@Override
	public MarcaModel getMarca(String marcaId) {
		MarcaModel marca = marcaRepository.findById(marcaId);

		if (marca == null) {
			throw new NotFoundException("Marca no existe"); 
		}
		
		return marca;
	}

	@Override
	public MarcaModel updateMarca(String marcaId, CreateMarcaDto marcaDto) {
		MarcaModel marca = marcaRepository.findById(marcaId);

		if (marca == null) {
			throw new NotFoundException("Marca no existe"); 
		}
		
		boolean isNewMarcaNombre= marcaDto.nombre != null && !marcaDto.nombre.equals("")
				&& marcaDto.nombre.equals(marca.getNombre()) == false;

		if (isNewMarcaNombre) {
			MarcaModel marcaNombreExists = marcaRepository.findByNombre(marcaDto.nombre);

			if (marcaNombreExists != null) {
				throw new BadRequestException("Nombre ya existe"); 
			}
			
			marca.setNombre(marcaDto.nombre);
			marca.setUpdatedAt(new Date());
		}
		
		marcaRepository.update(marca);

		return marca;
	}

	@Override
	public MarcaModel deleteMarca(String marcaId) {
		MarcaModel marca = marcaRepository.findById(marcaId);

		if (marca == null) {
			throw new NotFoundException("Marca no existe"); 
		}
		
		marcaRepository.delete(marcaId);
		
		return marca;
	}

}
