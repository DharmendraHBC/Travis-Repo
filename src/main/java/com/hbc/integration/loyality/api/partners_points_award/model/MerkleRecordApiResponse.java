package com.hbc.integration.loyality.api.partners_points_award.model;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class MerkleRecordApiResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5308054426229278587L;
	
	private boolean success;
	private Data data;
}
