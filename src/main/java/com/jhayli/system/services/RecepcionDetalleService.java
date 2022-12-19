package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateRecepcionDetalleDto;
import com.jhayli.system.models.RecepcionDetalleModel;

public interface RecepcionDetalleService {
	
	public List<RecepcionDetalleModel> getRecepcionDetalles(String recepcionId, int limit, int offset);
	
	public RecepcionDetalleModel createRecepcionDetalle(String recepcionId, CreateRecepcionDetalleDto recepcionDetalleDto);

	public RecepcionDetalleModel getRecepcionDetalle(String recepcionDetalleId);
		
	public RecepcionDetalleModel updateRecepcionDetalle(String recepcionDetalleId, CreateRecepcionDetalleDto recepcionDetalleDto);

	public RecepcionDetalleModel deleteRecepcionDetalle(String recepcionDetalleId);
	
}
