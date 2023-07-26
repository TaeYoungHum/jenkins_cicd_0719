package com.cook.model.api;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApiKeyDto {
	
	final private String myApi="9dde6472dcc14d64a089";

	public String getMyApi() {
		return myApi;
	}
	
	

}
