package com.hbc.integration.loyality.api.partners_points_award.model;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnersPointsAwardRequest {
	@NotBlank(message = "partner_id cannot be null or empty")
	private String partner_id;

	@NotBlank(message = "sender_loyalty_id cannot be null")
	private String sender_loyalty_id;

	@NotBlank(message = "recipient_loyalty_id cannot be null")
	private String recipient_loyalty_id;

	@NotBlank(message = "points_awarded cannot be null")
	private String points_awarded;

	@NotBlank(message = "transaction_date cannot be null")
	@DateTimeFormat(pattern = "YYYYMMDD")
	private String transaction_date;

	@NotBlank(message = "transaction_time cannot be null")
	private String transaction_time;

}
