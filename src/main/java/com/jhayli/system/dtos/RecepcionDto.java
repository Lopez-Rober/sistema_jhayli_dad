package com.jhayli.system.dtos;

import com.jhayli.system.enums.RecepcionEstadoEnum;
import com.jhayli.system.models.ProveedorModel;

public class RecepcionDto {
	public String id;
	
	public String usuarioId;
	
	public UsuarioDto usuario;
	
	public String proveedorId;
	
	public ProveedorModel proveedor;
	
	public RecepcionEstadoEnum estado;
	
	public String createdAt;
	
	public String updatedAt;
}
