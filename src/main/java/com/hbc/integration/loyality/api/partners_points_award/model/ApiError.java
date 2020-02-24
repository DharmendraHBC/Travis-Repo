package com.hbc.integration.loyality.api.partners_points_award.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
}
