package com.packsendme.roadbrewa.transport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.Transport;
import com.packsendme.roadbrewa.transport.repository.ITransport_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.transport.repository"})
public class Transport_Dao implements ICrud_Dao<Transport> {

	@Autowired
	ITransport_Repository transport_Rep; 
	
	
	@Override
	public Transport save(Transport entity) {
		try {
			return entity = transport_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Transport> findOneById(String id) {
		try {
			return transport_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transport> findAll() {
		try {
			List<Transport> entityL = new ArrayList<Transport>(); 
			entityL = transport_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(Transport entity) {
		try {
			transport_Rep.delete(entity);
			return true; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return false; 
		}		
	}

	@Override
	public Transport update(Transport entity) {
		try {
			Transport entityModel = transport_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	@Override
	public Transport findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Transport findOneByName(String name) {
		try {
			return transport_Rep.findTransportByName(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transport> findEntityByParameters(String name) {
		// TODO Auto-generated method stub
		return null;
	} 
}
