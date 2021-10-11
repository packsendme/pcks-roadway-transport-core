package com.packsendme.roadway.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.cross.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.cross.common.response.Response;
import com.packsendme.roadway.commons.constants.RoadwayManagerConstants;
import com.packsendme.roadway.commons.dto.InitialsDto;
import com.packsendme.roadway.commons.entity.Initials;
import com.packsendme.roadway.transport.dao.Initials_Dao;
import com.packsendme.roadway.transport.dto.InitialsListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadway.transport.dao"})
public class Initials_Service {

	@Autowired
	private Initials_Dao initialsDAO;

	InitialsDto initialsObj = new InitialsDto();

	
	public ResponseEntity<?> findAll() {
		Response<InitialsListResponse_Dto> responseObj = null;
		InitialsListResponse_Dto initialsListDto = new InitialsListResponse_Dto();
		try {
			initialsListDto.initials = initialsObj.entityTOdto(initialsDAO.findAll());
			 responseObj = new Response<InitialsListResponse_Dto>(0,HttpExceptionPackSend.CREATED_INITIALS.getAction(), initialsListDto);
			 return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<InitialsListResponse_Dto>(0,HttpExceptionPackSend.CREATED_INITIALS.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(InitialsDto initialsDto) {
		Response<Initials> responseObj = null;
		try {
			if(initialsDAO.findOneByName(initialsDto.name) == null) {
				Initials entity = initialsObj.dtoTOentity(initialsDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				responseObj = new Response<Initials>(0,HttpExceptionPackSend.CREATED_INITIALS.getAction(), entity);
				initialsDAO.save(entity);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<Initials>(0,HttpExceptionPackSend.CREATED_INITIALS.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		try {
			Optional<Initials> initialsData = initialsDAO.findOneById(id);
			if (initialsData.isPresent()) {
				Initials entity = initialsData.get();
				if(initialsDAO.remove(entity) == true) {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_UNITY_MEASUREMENT.getAction(), id);
				}
			}
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_INITIALS.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> update(String id, InitialsDto initialsDto) {
		Response<String> responseObj = null;
		try {
			if(initialsDAO.findOneByName(initialsDto.name) == null) {
				Optional<Initials> initialsData = initialsDAO.findOneById(id);
				Initials entity = initialsObj.dtoTOentity(initialsDto, initialsData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				entity = initialsDAO.update(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_INITIALS.getAction(), entity.id);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
