package com.hbc.integration.loyality.api.partners_points_award.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.hbc.integration.loyality.common.validator.CheckDateFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnersPointsAwardRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8153208976524361670L;

	@NotBlank(message = "partner_id can't be null or empty")
	private String partner_id;

	@NotBlank(message = "sender_loyalty_id can't be null or empty")
	private String sender_loyalty_id;

	@NotBlank(message = "recipient_loyalty_id can't be null or empty")
	private String recipient_loyalty_id;

	@NotBlank(message = "points_awarded can't be null or empty")
	private String points_awarded;

	@NotBlank(message = "transaction_date can't be null or empty")
	@CheckDateFormat(pattern = "YYYYMMDD", message = "invalid transaction_date format")
	private String transaction_date;

	@NotBlank(message = "transaction_time can't be null or empty")
	private String transaction_time;

}
