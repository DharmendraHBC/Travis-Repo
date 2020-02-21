package com.hbc.integration.loyality.api.partners_points_award.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hbc.integration.loyality.api.partners_points_award.model.ApiError;

@ControllerAdvice
public class PartnersPointsAwardExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PartnersPointsAwardException.class)
	public ResponseEntity<ApiError> handlePartnersPointsAwardException(PartnersPointsAwardException ex) {

		ApiError error = new ApiError();
		error.setMessage(ex.getMessage());
		error.setCode(ex.getCode());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiError> handleRunTime(RuntimeException ex) {

		ApiError error = new ApiError();
		error.setMessage(ex.getMessage());
		error.setCode("RN-108");
		return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 *
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}

}
