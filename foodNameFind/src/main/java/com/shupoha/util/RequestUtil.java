package com.shupoha.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RequestUtil {

    public static String restRequest(String requestUrl, String name){
		String result="";
        
		//보낼 파라메터 셋팅
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", name);
        
		//헤더셋팅
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "text/plain;charset=UTF-8");
        
		//파라메터와 헤어 합치기
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		//RestTemplate 초기화
		RestTemplate rt = new RestTemplate();
        
		//전송 및 결과 처리
		ResponseEntity<String> response = rt.exchange(
				requestUrl, 
				HttpMethod.POST,
				entity,
				String.class
				);
		result = response.getBody();//리턴되는 결과의 body를 저장.
		
		return result;
	}
 }
