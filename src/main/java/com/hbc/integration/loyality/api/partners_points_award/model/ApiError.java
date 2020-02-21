package com.hbc.integration.loyality.api.partners_points_award.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
	private String message;
	private String code;
}
