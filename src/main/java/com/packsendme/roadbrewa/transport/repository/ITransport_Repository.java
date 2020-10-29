package com.packsendme.roadbrewa.transport.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Transport;

@Repository
public interface ITransport_Repository extends MongoRepository<Transport, String>{

	@Query("{'$or' : [{'name_transport' : {$eq : ?0}}, {'initials' : {$eq : ?1}}]}")
	List<Transport> findTransportsByParameters(String name_transport, String initials);
	
}
