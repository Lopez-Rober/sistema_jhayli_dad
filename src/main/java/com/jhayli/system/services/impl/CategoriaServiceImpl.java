package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateCategoriaDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.CategoriaModel;
import com.jhayli.system.repositories.CategoriaRepository;
import com.jhayli.system.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

	@Override
	public int count(String query) {
		return categoriaRepository.count(query);
	}
	
	@Override
	public List<CategoriaModel> getCategorias(int limit, int offset, String query) {
		return categoriaRepository.findAll(limit, offset, query);
	}


	@Override
	public CategoriaModel createCategoria(CreateCategoriaDto categoriaDto) {
		CategoriaModel categoriaNombreExists = categoriaRepository.findByNombre(categoriaDto.nombre);
		
		if (categoriaNombreExists != null) {
			throw new BadRequestException("Nombre ya existe"); 
		}
		
		CategoriaModel categoria = new CategoriaModel();
		categoria.setId(UUID.randomUUID().toString());
		categoria.setNombre(categoriaDto.nombre);
		categoria.setDescripcion(categoriaDto.descripcion);
		categoria.setCreatedAt(new Date());
		categoria.setUpdatedAt(new Date());

		categoriaRepository.save(categoria);

		return categoria;
	}
	
	@Override
	public CategoriaModel getCategoria(String categoriaId) {
		CategoriaModel categoria = categoriaRepository.findById(categoriaId);

		if (categoria == null) {
			throw new NotFoundException("Categoria no existe"); 
		}
		
		return categoria;
	}

	@Override
	public CategoriaModel updateCategoria(String categoriaId, CreateCategoriaDto categoriaDto) {		
		CategoriaModel categoria = categoriaRepository.findById(categoriaId);

		if (categoria == null) {
			throw new NotFoundException("Categoria no existe"); 
		}
		
		boolean isNewCategoriaNombre= categoriaDto.nombre != null && !categoriaDto.nombre.equals("")
				&& categoriaDto.nombre.equals(categoria.getNombre()) == false;

		if (isNewCategoriaNombre) {
			CategoriaModel categoriaNombreExists = categoriaRepository.findByNombre(categoriaDto.nombre);

			if (categoriaNombreExists != null) {
				throw new BadRequestException("Nombre ya existe"); 
			}
			
			categoria.setNombre(categoriaDto.nombre);
			categoria.setUpdatedAt(new Date());
		}
		
		if (categoriaDto.descripcion != null && !categoriaDto.descripcion.equals("")) {
			categoria.setDescripcion(categoriaDto.descripcion);
			categoria.setUpdatedAt(new Date());
		}

		categoriaRepository.update(categoria);

		return categoria;
	}
	
	@Override
	public CategoriaModel deleteCategoria(String categoriaId) {
		CategoriaModel categoria = categoriaRepository.findById(categoriaId);

		if (categoria == null) {
			throw new NotFoundException("Categoria no existe"); 
		}
		
		categoriaRepository.delete(categoriaId);
		
		return categoria;
	}

}
