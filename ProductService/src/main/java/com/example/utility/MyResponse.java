package com.example.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyResponse<T> {
	
	private String status;
	private Integer statusCode;
	private String remarks;
	private Integer size;
	private T data;

}
