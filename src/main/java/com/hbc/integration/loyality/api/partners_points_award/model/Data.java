package com.hbc.integration.loyality.api.partners_points_award.model;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	private int id;
	private int points;
}
