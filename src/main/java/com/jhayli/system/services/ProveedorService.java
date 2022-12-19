package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateProveedorDto;
import com.jhayli.system.models.ProveedorModel;

public interface ProveedorService {
	
	public List<ProveedorModel> getProveedores(int limit, int offset , String query);
	
	public int count (String query);
	
	public ProveedorModel createProveedor(CreateProveedorDto proveedorDto);

	public ProveedorModel getProveedor(String proveedorId);
		
	public ProveedorModel updateProveedor(String proveedorId, CreateProveedorDto proveedorDto);

	public ProveedorModel deleteProveedor(String proveedorId);

}
