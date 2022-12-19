package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.ProveedorModel;

public interface ProveedorRepository {
	
	public List<ProveedorModel> findAll(int limit, int offset , String query);
	
	public int count (String query);
	
	public ProveedorModel findById(String id);
	
	public ProveedorModel findByNombre(String nombre);

	public void save(ProveedorModel proveedor);

	public void update(ProveedorModel proveedor);
	
	public void delete(String id);

}
