package com.packsendme.roadbrewa.transport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Initials;

@Repository
public interface IInitials_Repository extends MongoRepository<Initials, String>{

	@Query("{'name' :  {$eq: ?0}}")
	Initials findInitialsByName(String name);

}
