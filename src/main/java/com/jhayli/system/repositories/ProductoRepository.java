package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.ProductoModel;

public interface ProductoRepository {
	
	public int count (String query);
	
	public List<ProductoModel> findAll(int limit, int offset , String query);
	
	public ProductoModel findById(String id);
	
	public ProductoModel findByNombre(String nombre);

	public ProductoModel findByCodigoBarras(String codigoBarras);

	public void save(ProductoModel producto);

	public void update(ProductoModel producto);
	
	public void delete(String id);
	
}
