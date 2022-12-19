package com.jhayli.system.repositories;

import java.util.List;

import com.jhayli.system.models.RecepcionModel;

public interface RecepcionRepository {
	public int count ();

	public List<RecepcionModel> findAll(int limit, int offset);
	
	public RecepcionModel findById(String id);

	public void save(RecepcionModel recepcion);

	public void update(RecepcionModel recepcion);
	
	public void delete(String id);
}
