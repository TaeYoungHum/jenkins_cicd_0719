package com.cook.service.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CallApiUrlService {

	private ArrayList<String> keyType = new ArrayList<String>();

	public ArrayList<String> getKeyType() {
		return keyType;
	}

	public void setKeyType(ArrayList<String> keyType) {
		this.keyType = keyType;
	}
	
	//시작 위에 게터 세터
	public String callApi(String apiKey, int foodNumber) {
		// https://openapi.foodsafetykorea.go.kr/api/9dde6472dcc14d64a089/COOKRCP01/xml/1114/1114

		// 초기화
		this.keyType.add("json");
		this.keyType.add("xml");

		try {
			
				// 1. URL을 만들기 위한 StringBuilder.
				StringBuilder urlBuilder = new StringBuilder("https://openapi.foodsafetykorea.go.kr/api/"); // 기본 url
				urlBuilder.append(URLDecoder.decode(apiKey+"/", "UTF-8"));
				urlBuilder.append(URLDecoder.decode("COOKRCP01"+"/", "UTF-8")); // 서비스 명
				urlBuilder.append(URLDecoder.decode(keyType.get(0)+"/", "UTF-8")); // 타입결정. 0 jason, 1은 xml
				urlBuilder.append(URLDecoder.decode(Integer.toString(foodNumber)+"/", "UTF-8")); /* 페이지 시작 번호 */
				urlBuilder.append(URLDecoder.decode(Integer.toString(foodNumber)+"/", "UTF-8")); /* 페이지 끝 번호 */
	
				//확인
				System.out.println(urlBuilder.toString());
				
				// 3. URL 객체 생성.
				URL url = new URL(urlBuilder.toString());
				// 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// 5. 통신을 위한 메소드 SET.
				conn.setRequestMethod("GET");
				// 6. 통신을 위한 Content-type SET.
				conn.setRequestProperty("Content-type", "application/json");
				// 7. 통신 응답 코드 확인.
				System.out.println("Response code: " + conn.getResponseCode());
				// 8. 전달받은 데이터를 BufferedReader 객체로 저장.
				BufferedReader rd;
				if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
				
				// 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				// 10. 객체 해제.
				rd.close();
				conn.disconnect();
				// 11. 전달받은 데이터 확인.
//				System.out.println(sb.toString());
//				System.out.println("sb의 타입 = "+sb.getClass().getName());
//				System.out.println("sb.toString() 타입 = "+sb.toString().getClass().getName());
				
				return sb.toString();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("통신 에러 발생");
			return "통신 에러 발생";
		}

	}

}
