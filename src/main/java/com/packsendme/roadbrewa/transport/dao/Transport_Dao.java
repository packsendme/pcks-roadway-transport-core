package com.packsendme.roadbrewa.transport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	public List<Transport> findOneByIdAndName(String name, String initials) {
		try {
			List<Transport> entityModel = transport_Rep.findTransportsByNameAndInitials(name,initials);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public Transport findOneByName(String name) {
		return null;
	}

	@Override
	public List<Transport> findEntityByParameters(Map<String,String> parameters) {
		try {
			String nametransp_p = parameters.get("name").toString();
			String initials_p = parameters.get("initials").toString();
			List<Transport> entityL = null;
			
			if(!nametransp_p.equals("")) {
				entityL = transport_Rep.findTransportsByName(nametransp_p);
			}
			else if(!initials_p.equals("")) {
				entityL = transport_Rep.findTransportsByIntials(initials_p);
			}
			System.out.println(" TOTAL LIST ----------- ");
			System.out.println(" TOTAL LIST"+ entityL.size());
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	} 
}
