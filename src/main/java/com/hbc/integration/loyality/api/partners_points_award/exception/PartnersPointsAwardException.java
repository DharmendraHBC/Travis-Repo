package com.hbc.integration.loyality.api.partners_points_award.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author c990072 Feb 21, 2020 This was a custom service exception
 */
@NoArgsConstructor
@Setter
@Getter
public class PartnersPointsAwardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7455692111657904770L;

	private String code;

	public PartnersPointsAwardException(String message, String code) {
		super(message);
		this.code = code;
	}

	public PartnersPointsAwardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace, String code) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}

	public PartnersPointsAwardException(String message, Throwable cause, String code) {
		super(message, cause);
		this.code = code;
	}

	public PartnersPointsAwardException(Throwable cause) {
		super(cause);
	}

}
