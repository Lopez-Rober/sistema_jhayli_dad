package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateRecepcionDto;
import com.jhayli.system.models.RecepcionModel;

public interface RecepcionService {
	public int count();

	public List<RecepcionModel> getRecepciones(int limit, int offset);
	
	public RecepcionModel createRecepcion(CreateRecepcionDto recepcionDto);

	public RecepcionModel getRecepcion(String recepcionId);
		
	public RecepcionModel updateRecepcion(String recepcionId, CreateRecepcionDto recepcionDto);

	public RecepcionModel deleteRecepcion(String recepcionId);
	
}
