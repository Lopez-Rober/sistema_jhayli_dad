package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateCategoriaDto;
import com.jhayli.system.models.CategoriaModel;

public interface CategoriaService {
	
	public List<CategoriaModel> getCategorias(int limit, int offset, String query);
	
	public int count(String query);
	
	public CategoriaModel createCategoria(CreateCategoriaDto categoriaDto);

	public CategoriaModel getCategoria(String categoriaId);
		
	public CategoriaModel updateCategoria(String categoriaId, CreateCategoriaDto categoriaDto);

	public CategoriaModel deleteCategoria(String categoriaId);

}
