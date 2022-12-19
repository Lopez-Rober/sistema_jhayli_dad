package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateMarcaDto;
import com.jhayli.system.models.MarcaModel;

public interface MarcaService {
	
	public List<MarcaModel> getMarcas(int limit, int offset, String query);
	
	public int count(String query);
	
	public MarcaModel getMarca(String marcaId);
	
	public MarcaModel createMarca(CreateMarcaDto marcaDto);
	
	public MarcaModel updateMarca(String marcaId, CreateMarcaDto marcaDto);

	public MarcaModel deleteMarca(String marcaId);
	
}
