package com.example.exception;

import java.io.IOException;

import com.example.utility.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomDecoder implements ErrorDecoder {
	
	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new OrderNotFoundException(errorResponse.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new OrderNotFoundException("ServiceInternalException");
	}

}
