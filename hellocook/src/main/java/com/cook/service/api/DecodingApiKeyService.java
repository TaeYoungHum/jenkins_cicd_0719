package com.cook.service.api;

import java.net.URLDecoder;

import org.springframework.stereotype.Service;

@Service
public class DecodingApiKeyService {

	String apiKey;
	String deCoding;

	public String decodingApiKey(String apiKey) {

		try {

			this.apiKey = apiKey;
			this.deCoding = URLDecoder.decode(this.apiKey, "utf-8");
			
			System.out.println("url decoding - > "+this.deCoding);
			
			return this.deCoding;

		} catch (Exception e) {
			System.out.println("url decoding 에러 발생.");
			return "url decoding 에러 발생";
		}

	}

}
