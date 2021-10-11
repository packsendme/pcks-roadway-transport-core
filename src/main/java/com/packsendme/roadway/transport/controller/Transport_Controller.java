package com.packsendme.roadway.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packsendme.roadway.commons.dto.InitialsDto;
import com.packsendme.roadway.commons.dto.TransportDto;
import com.packsendme.roadway.transport.service.Initials_Service;
import com.packsendme.roadway.transport.service.Transport_Service;


@RestController
@RequestMapping("/roadway/transport")
public class Transport_Controller {

	
	@Autowired
	private Transport_Service transportService;	
	
	@Autowired
	private Initials_Service initialsService;	

	/***************************************
	 TRANSPORT <--> GET | POST | DELETE 
	***************************************/
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/type/{name}")
	public ResponseEntity<?> getTransport(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @PathVariable("name") String name) {	
		return transportService.findTransporteByName(name);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/")
	public ResponseEntity<?> getTransport(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return transportService.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/")
	public ResponseEntity<?> postTransport(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, 
			@Validated  @RequestBody TransportDto transport)
	{	
		return transportService.save(transport);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/")
	public ResponseEntity<?> deleteTransport(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return transportService.delete(id);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/")
	public ResponseEntity<?> updateTransport(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id,
			@Validated  @RequestBody TransportDto transport)
	{	
		return transportService.prepareUpdate(id, transport);
	}
	
	
	/***************************************
	 INITIALS :: GET | POST | DELETE 
	***************************************/

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/initials")
	public ResponseEntity<?> getInitials(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return initialsService.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/initials")
	public ResponseEntity<?> postInitials(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @RequestBody InitialsDto initialsDto)
	{	
		return initialsService.save(initialsDto);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/initials")
	public ResponseEntity<?> deleteInitials(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return initialsService.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/initials")
	public ResponseEntity<?> putInitials(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id, 
			@Validated  @RequestBody InitialsDto initialsDto)
	{	
		return initialsService.update(id, initialsDto);
	}


}
