package com.packsendme.roadbrewa.transport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Transport;

@Repository
public interface ITransport_Repository extends MongoRepository<Transport, String>{

	@Query("{'name_transport' :  {$eq: ?0}}")
	Transport findTransportByName(String name_transport);
	
}
