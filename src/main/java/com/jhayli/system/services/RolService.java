package com.jhayli.system.services;

import java.util.List;

import com.jhayli.system.dtos.CreateRolDto;
import com.jhayli.system.models.RolModel;

public interface RolService {
	public int count(String query);

	public List<RolModel> getRoles(int limit, int offset, String query);
		
	public RolModel createRol(CreateRolDto rolDto);

	public RolModel getRol(String rolId);
		
	public RolModel updateRol(String rolId, CreateRolDto rolDto);

	public RolModel deleteRol(String rolId);

}
