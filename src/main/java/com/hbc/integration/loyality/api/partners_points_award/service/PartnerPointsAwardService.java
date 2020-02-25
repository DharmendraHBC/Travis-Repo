package com.hbc.integration.loyality.api.partners_points_award.service;

import com.hbc.integration.loyality.api.partners_points_award.exception.PartnersPointsAwardException;
import com.hbc.integration.loyality.api.partners_points_award.model.MerkleRecordApiResponse;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardRequest;

/**
 * @author c990072 Feb 21, 2020
 */
public interface PartnerPointsAwardService {

	/**
	 * PartnersPointsAwardResponse c990072
	 * 
	 * @param partnersPointsAwardRequest
	 * @return PartnersPointsAwardResponse
	 * @throws PartnersPointsAwardException
	 */
	public MerkleRecordApiResponse doService(PartnersPointsAwardRequest partnersPointsAwardRequest)
			throws PartnersPointsAwardException;
}
