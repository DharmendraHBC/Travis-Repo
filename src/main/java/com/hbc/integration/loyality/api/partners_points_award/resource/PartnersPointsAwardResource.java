package com.hbc.integration.loyality.api.partners_points_award.resource;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hbc.integration.loyality.api.partners_points_award.exception.PartnersPointsAwardException;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardRequest;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardResponse;

/**
 * @author c990072 Feb 21, 2020
 */
public interface PartnersPointsAwardResource {
	/**
	 * PartnersPointsAwardResponse c990072
	 * 
	 * @param partnersPointsAwardRequest
	 * @return PartnersPointsAwardResponse
	 * @throws PartnersPointsAwardException
	 */
	@PostMapping(path = "/partners_points_award", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PartnersPointsAwardResponse doService(
			@Valid @RequestBody PartnersPointsAwardRequest partnersPointsAwardRequest)
			throws PartnersPointsAwardException;
}
