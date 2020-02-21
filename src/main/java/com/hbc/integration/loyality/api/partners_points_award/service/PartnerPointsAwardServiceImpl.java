package com.hbc.integration.loyality.api.partners_points_award.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbc.integration.loyality.api.partners_points_award.exception.PartnersPointsAwardException;
import com.hbc.integration.loyality.api.partners_points_award.model.MerkleRecordApiPayload;
import com.hbc.integration.loyality.api.partners_points_award.model.MerkleRecordApiResponse;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardRequest;
import com.hbc.integration.loyality.api.partners_points_award.model.PartnersPointsAwardResponse;
import com.hbc.integration.loyality.common.util.MD5;

/**
 * @author c990072 Feb 21, 2020
 */
@Service
public class PartnerPointsAwardServiceImpl implements PartnerPointsAwardService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${merkle.uuid}")
	private String UUID;

	@Value("${merkle.secret_key}")
	private String SECRET_KEY;

	@Value("${merkle.merkle_version}")
	private String MERKLE_VERION;

	@Value("${merkle.merkle_end_point_url}")
	private String MERKLE_END_POINT_URL;

	private Logger LOGGER = LoggerFactory.getLogger(PartnerPointsAwardServiceImpl.class);

	/**
	 *
	 */
	@Override
	public PartnersPointsAwardResponse doService(PartnersPointsAwardRequest partnersPointsAwardRequest)
			throws PartnersPointsAwardException {

		MerkleRecordApiPayload request = new MerkleRecordApiPayload();
		request.setUuid(UUID);
		request.setSig("");
		request.setExternal_customer_id(partnersPointsAwardRequest.getRecipient_loyalty_id());
		request.setType(partnersPointsAwardRequest.getPartner_id());
		request.setEvent_id(partnersPointsAwardRequest.getSender_loyalty_id() + "_"
				+ partnersPointsAwardRequest.getRecipient_loyalty_id() + "_"
				+ partnersPointsAwardRequest.getTransaction_date());
		request.setValue(partnersPointsAwardRequest.getPoints_awarded());
		request.setOriginated_at(partnersPointsAwardRequest.getTransaction_date());
		request.setChannel("partner_transferb");
		request.setSub_channel("");
		request.setSub_channel_detail("");
		request.setDetail("nothing in detail");

//			MerkleRecordApiPayload request = new MerkleRecordApiPayload(UUID, "", "1000065", "123456578",
//					"1000006_1000065_20110814", "5", "20110814", "partner_transfer", "", "", "nothing in detail");

		String jsonStr = null, sig = "", sigString = null;

		try {
			ObjectMapper Obj = new ObjectMapper();
			jsonStr = Obj.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			LOGGER.info("ERROR IN MARSHALLING MerkleRecordApiPayload REQUEST OBJECT TO JSON {} " + e.getMessage());
			throw new PartnersPointsAwardException("PARTNERS POINT AWARD INTERNAL SERVICE ERROR " + e.getMessage(),
					"E-301");
		}
		sigString = SECRET_KEY + "/" + MERKLE_VERION + "/api/record.json?uuid=" + UUID + jsonStr;
		sig = MD5.getMd5(sigString);
		LOGGER.info("sig {} ", sig);

		HttpEntity<MerkleRecordApiPayload> entity = new HttpEntity<MerkleRecordApiPayload>(request, getHeaders());
		LOGGER.info("MERKLE RECORD API REQUEST BODY", entity.getBody());

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("MERKLE RECORD API REQUEST ENTITY {} ", entity);

		// building the uri of merkle record api
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MERKLE_END_POINT_URL).path(MERKLE_VERION)
				.path("/api/record.json").queryParam("uuid", UUID).queryParam("sig", sig);
		LOGGER.info("ENDPOINT {} ", builder.build().toUri());
		ResponseEntity<MerkleRecordApiResponse> response = null;
		try {
			// post request call to merkle record api
			response = restTemplate.postForEntity(builder.build().toUri(), entity, MerkleRecordApiResponse.class);
		} catch (HttpClientErrorException ex) {
			LOGGER.info("PARTNERS POINT AWARD CLIENT ERROR {} ", ex.getMessage());
			throw new PartnersPointsAwardException("PARTNERS POINT AWARD INTERNAL SERVICE ERROR " + ex.getMessage(),
					"E-301");
		} catch (Exception ex) {
			LOGGER.info("");
			throw new PartnersPointsAwardException("PARTNERS POINT AWARD INTERNAL SERVICE ERROR " + ex.getMessage(),
					"E-301");
		}
		LOGGER.info("MERKLE RECORD API RESPONSE BODY {} ", response.getBody());

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("MERKLE RECORD API RESPONSE ENTITY {} ", response);

		PartnersPointsAwardResponse partnersPointsAwardResponse = new PartnersPointsAwardResponse();
		partnersPointsAwardResponse.setSuccess(response.getBody().isSuccess());
		return partnersPointsAwardResponse;
	}

	/**
	 * @return HttpHeaders
	 */
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> accepts = new ArrayList<MediaType>();
		accepts.add(MediaType.APPLICATION_JSON);
		headers.setAccept(accepts);
		return headers;
	}

}
