package com.hbc.integration.loyality.api.partners_points_award.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author c990072 Feb 21, 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerkleRecordApiPayload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String sig;
	private String external_customer_id;
	private String type;
	private String event_id;
	private String value;
	private String originated_at;
	private String channel;
	private String sub_channel;
	private String sub_channel_detail;
	private String detail;

}
