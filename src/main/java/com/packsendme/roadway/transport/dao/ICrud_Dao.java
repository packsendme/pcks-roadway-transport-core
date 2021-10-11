package com.packsendme.roadway.transport.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICrud_Dao<T> {

	public T save(T entity);

	public Optional<T> findOneById(String id);
	
	public List<T> findAll();
	
	public Boolean remove(T entity);
	
	public T update(T entity);
	
	public T findOneByName(String name);
	
	public List<T> findOneByIdAndName(String id, String name);
	
	public List<T> findEntityByParameters(Map<String,String> parameters);

}
