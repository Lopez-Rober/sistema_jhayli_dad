package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.RecepcionDetalleModel;

public interface RecepcionDetalleRepository {

	public List<RecepcionDetalleModel> findAll(String recepcionId, int limit, int offset);
	
	public RecepcionDetalleModel findById(String id);

	public void save(RecepcionDetalleModel recepcion);

	public void update(RecepcionDetalleModel recepcion);
	
	public void delete(String id);
}
