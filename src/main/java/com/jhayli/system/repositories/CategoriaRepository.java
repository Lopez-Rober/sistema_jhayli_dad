package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.CategoriaModel;

public interface CategoriaRepository {
	
	public int count (String query);

	public List<CategoriaModel> findAll(int limit, int offset, String query);

	public CategoriaModel findById(String id);

	public CategoriaModel findByNombre(String nombre);

	public void save(CategoriaModel categoria);

	public void update(CategoriaModel categoria);

	public void delete(String id);

}
