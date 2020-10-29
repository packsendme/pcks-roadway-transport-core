package com.packsendme.roadbrewa.transport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.roadbrewa.component.RoadwayManagerConstants;
import com.packsendme.roadbrewa.dto.TransportDto;
import com.packsendme.roadbrewa.entity.Transport;
import com.packsendme.roadbrewa.transport.dao.Transport_Dao;
import com.packsendme.roadbrewa.transport.dto.TransportListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadbrewa.transport.dao"})
public class Transport_Service {
	
	@Autowired
	private Transport_Dao transportDAO;
	
	TransportDto transportObj = new TransportDto();  

	public ResponseEntity<?> findAll() {
		Response<TransportListResponse_Dto> responseObj = null;
		TransportListResponse_Dto transportListDTO = new TransportListResponse_Dto();
		try {
			transportListDTO.transports = transportObj.entityTOdto(transportDAO.findAll());
			responseObj = new Response<TransportListResponse_Dto>(0,HttpExceptionPackSend.CREATE_TRANSPORT.getAction(), transportListDTO);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<TransportListResponse_Dto>(0,HttpExceptionPackSend.CREATED_BODYWORK.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(TransportDto transportDto) {
		Response<String> responseObj = null;
		try {
			Map<String, String> parametersMap = new HashMap<String, String>();
			parametersMap.put("name", transportDto.name_transport);
			parametersMap.put("initials", transportDto.initials);
			
			if(transportDAO.findEntityByParameters(parametersMap) == null) {
				Transport entity = transportObj.dtoTOentity(transportDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				entity = transportDAO.save(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.CREATE_TRANSPORT.getAction(), entity.id);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.CREATE_TRANSPORT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		try {
			Optional<Transport> transportData = transportDAO.findOneById(id);
			if(transportData.isPresent()) {
				Transport entity = transportData.get();
				if(transportDAO.remove(entity) == true) {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_TRANSPORT.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
				else {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_TRANSPORT.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_TRANSPORT.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<?> prepareUpdate(String id, TransportDto transportDto) {
		Response<String> responseObj = null;
		try {
			Map<String, String> parametersMap = new HashMap<String, String>();
			parametersMap.put("name", transportDto.name_transport);
			parametersMap.put("initials", transportDto.initials);
			
			List<Transport> transport_L = transportDAO.findEntityByParameters(parametersMap);
			if(transport_L != null) {
				for(Transport t : transport_L) {
					if(t.id == id) {
						return update(id, transportDto);
					}
				}
			}
			else if(transport_L == null) {
				return update(id, transportDto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.CREATE_TRANSPORT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	
	public ResponseEntity<?> update(String id, TransportDto transportDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same Transport in Database
			Transport transportFindName = transportDAO.findOneByIdAndName(id, transportDto.name_transport);

			if(transportFindName == null) {
				Optional<Transport> transportData = transportDAO.findOneById(id);
				if(transportData.isPresent()) {
					Transport entity = transportData.get();
					entity = transportObj.dtoTOentity(transportDto, entity, RoadwayManagerConstants.UPDATE_OP_ROADWAY);
					transportDAO.update(entity);
					responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_TRANSPORT.getAction(), entity.name_transport);
					return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
				}
				else {
					responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_TRANSPORT.getAction(), null);
					return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
				}
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_TRANSPORT.getAction(), transportDto.name_transport);
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_TRANSPORT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	

}
