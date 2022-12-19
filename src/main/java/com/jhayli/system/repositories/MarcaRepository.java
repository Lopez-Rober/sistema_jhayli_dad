package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.MarcaModel;

public interface MarcaRepository {
	
	public int count (String query);
	
	public List<MarcaModel> findAll(int limit, int offset, String query);
	
	public MarcaModel findById(String id);
	
	public MarcaModel findByNombre(String nombre);

	public void save(MarcaModel marca);

	public void update(MarcaModel marca);
	
	public void delete(String id);

}
