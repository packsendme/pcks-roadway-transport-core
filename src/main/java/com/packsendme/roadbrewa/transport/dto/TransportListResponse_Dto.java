package com.packsendme.roadbrewa.transport.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.TransportDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class TransportListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<TransportDto> transports = new ArrayList<TransportDto>();

 

	public TransportListResponse_Dto(List<TransportDto> transports) {
		super();
		this.transports = transports;
	}

	public TransportListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
