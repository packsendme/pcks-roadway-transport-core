package com.packsendme.roadbrewa.transport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.Initials;
import com.packsendme.roadbrewa.transport.repository.IInitials_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.transport.repository"})
public class Initials_Dao implements ICrud_Dao<Initials> {

	@Autowired
	IInitials_Repository initials_Rep; 
	
	
	@Override
	public Initials save(Initials entity) {
		try {
			return entity = initials_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Initials> findOneById(String id) {
		try {
			return initials_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Initials> findAll() {
		try {
			List<Initials> entityL = new ArrayList<Initials>(); 
			entityL = initials_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(Initials entity) {
		try {
			initials_Rep.delete(entity);
			return true; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return false; 
		}		
	}

	@Override
	public Initials update(Initials entity) {
		try {
			Initials entityModel = initials_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	@Override
	public List<Initials> findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Initials findOneByName(String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Initials> findEntityByParameters(Map<String,String> parameters) {
		// TODO Auto-generated method stub
		return null;
	} 
}
