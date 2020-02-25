package com.hbc.integration.loyality.api.partners_points_award.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbc.integration.loyality.api.partners_points_award.exception.PartnersPointsAwardException;
import com.hbc.integration.loyality.api.partners_points_award.model.MerkleRecordApiResponse;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardRequest;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardResponse;
import com.hbc.integration.loyality.api.partners_points_award.service.PartnerPointsAwardService;

@RestController
public class PartnersPointsAwardResourceImpl implements PartnersPointsAwardResource {

	private Logger LOGGER = LoggerFactory.getLogger(PartnersPointsAwardResourceImpl.class);

	@Autowired
	private PartnerPointsAwardService partnerPointsAwardService;

	/**
	 * @throws PartnersPointsAwardException
	 *
	 */
	@Override
	public PartnersPointsAwardResponse doService(PartnersPointsAwardRequest partnersPointsAwardRequest)
			throws PartnersPointsAwardException {
		PartnersPointsAwardResponse partnersPointsAwardResponse = null;
		ObjectMapper Obj = new ObjectMapper();

		try {
			LOGGER.info("PartnersPointsAwardRequest --> {} ", Obj.writeValueAsString(partnersPointsAwardRequest));
		} catch (JsonProcessingException e1) {
			LOGGER.info("ERROR IN MARSHALLING PartnersPointsAwardRequest REQUEST to json --> {} ", e1);
			throw new PartnersPointsAwardException("PARTNERS POINT AWARD INTERNAL SERVICE ERROR " + e1.getMessage(),
					"E-301");
		}
		MerkleRecordApiResponse merkleRecordApiResponse = partnerPointsAwardService
				.doService(partnersPointsAwardRequest);
		if (merkleRecordApiResponse.isSuccess()) {
			partnersPointsAwardResponse = new PartnersPointsAwardResponse();

			partnersPointsAwardResponse.setSuccess(merkleRecordApiResponse.isSuccess());
			partnersPointsAwardResponse.setPoints(merkleRecordApiResponse.getData().getPoints());
		} else {
			throw new PartnersPointsAwardException(merkleRecordApiResponse.getData().getMessage(),
					String.valueOf(merkleRecordApiResponse.getData().getCode()));
		}
		return partnersPointsAwardResponse;
	}

}
