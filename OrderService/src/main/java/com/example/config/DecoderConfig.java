package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.exception.CustomDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class DecoderConfig {
	
	@Bean
	public ErrorDecoder createConfig() {
		return new CustomDecoder();
	}

}
