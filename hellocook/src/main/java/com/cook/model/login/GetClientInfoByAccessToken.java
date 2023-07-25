package com.cook.model.login;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetClientInfoByAccessToken {

//데이터 정리 위해. 디펜던시 추가.
//	<dependency>
//    	<groupId>com.fasterxml.jackson.core</groupId>
//    	<artifactId>jackson-databind</artifactId>
//    	<version>2.14.2</version>
//    </dependency>

	public void bringInfo(String token) {

		//사용자 정보
		Long id;
		String nickname;
		String email;

		//
		RestTemplate restTemplate = new RestTemplate();

		// HTTP Header 생성
		HttpHeaders headers = new HttpHeaders(); // 헤더 생성
		headers.add("Authorization", "Bearer " + token); // 토큰은 파라메터로 받아옴
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 카카오 공식문

		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoUserInfoRequest, String.class);

		String responseBody = response.getBody();

		System.out.println("카카오 사용자 정보: " + responseBody);

		// josn-data bind 디펜던시 사용
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			id = jsonNode.get("id").asLong();
			nickname = jsonNode.get("properties").get("nickname").asText();
			email = jsonNode.get("kakao_account").get("email").asText();

			System.out.println("카카오 사용자 정보: " +"id= "+ id + ", " +"nickname= "+nickname + ", "+"email= "+ email);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("사용자 정보 파싱 실패");
			e.printStackTrace();
		}

	}
}
