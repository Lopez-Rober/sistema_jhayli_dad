package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.RolModel;

public interface RolRepository {

	public int count (String query);
	
	public List<RolModel> findAll(int limit, int offset, String query);

	public RolModel findById(String id);

	public RolModel findByNombre(String nombre);

	public void save(RolModel categoria);

	public void update(RolModel categoria);

	public void delete(String id);
}
