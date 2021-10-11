package com.packsendme.roadway.transport.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadway.commons.dto.InitialsDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class InitialsListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<InitialsDto> initials = new ArrayList<InitialsDto>();

 

	public InitialsListResponse_Dto(List<InitialsDto> initials) {
		super();
		this.initials = initials;
	}

	public InitialsListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
