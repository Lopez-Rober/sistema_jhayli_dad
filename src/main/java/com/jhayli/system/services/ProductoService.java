package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateProductoDto;
import com.jhayli.system.models.ProductoModel;

public interface ProductoService {
	
	public List<ProductoModel> getProductos(int limit, int offset , String query);
	public int count (String query);
	
	public ProductoModel createProducto(CreateProductoDto productoDto);

	public ProductoModel getProducto(String productoId);
		
	public ProductoModel updateProducto(String productoId, CreateProductoDto productoDto);

	public ProductoModel deleteProducto(String productoId);
	
}
