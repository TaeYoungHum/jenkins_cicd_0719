package com.cook.service.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.cook.model.login.KakaoToken;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KakaoLoginService {

	public KakaoToken requestToken(String code) {

		KakaoToken kakaoToken = new KakaoToken(); // 반환자 생성. 응답을 받을 객체(공식 사이트 보고 객체를 생성 해야한다.)
		String strUrl = "https://kauth.kakao.com/oauth/token"; // request를 보낼 주소를 지정한다.

		// post 로 통신하기 위한 코드 작성 시작.
		try {

			// 1. 연결 수립
			URL url = new URL(strUrl); // url 생성.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 지정한 url로, Http연결 생성.
			// 2. post 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

			// 3. 파라미터 셋업.
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			// 3.5 파라메터 삽입 시작.(homepage)
			// a. grant_type set, "authorization" hold
			stringBuilder.append("grant_type=authorization_code");
			// b. client_id = 자신의 api key 입력.
			stringBuilder.append("&client_id=");
			// c. 파라미터 redirect_uri = 자기꺼 redirect uri를 입력.
			stringBuilder.append("&redirect_uri=http://localhost:8080/login/kakao-redirect");
			// d. 처음 과정에서 받아온 code를 파라메타로 받아서 삽입.
			stringBuilder.append("&code=" + code);
			// e. KOE010 에러 시 추가부분 https://yanoos.tistory.com/104
			stringBuilder.append("&client_secret=PZo0db9RNkZpC0Z7xjeNuLT87C6Nx6ew");

			// sb 만들고 나서. BufferedWriter에 탑재
			bufferedWriter.write(stringBuilder.toString());
			bufferedWriter.flush();// 필수.

			// 실제 요청(200을 반환하면 성공)
			int responseCode = conn.getResponseCode();
			System.out.println("@@@@반환 코드 = " + responseCode);

			// request를 통해, 반환받은 JSON타입의 Response 메시지를 읽어오기
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}

			System.out.println("@@@response body= " + result);

			// Jackson 라이브러리를 사용해서, json 파싱 ObjectMapper를 사용해서. java <-> json 을 사용가능
			ObjectMapper objectMapper = new ObjectMapper();
			// kakaoToken에 result를 KakaoToken.class 형식으로 변환하여 저장(?)
			kakaoToken = objectMapper.readValue(result, KakaoToken.class);

			// api호출용 access token
			String access_Token = kakaoToken.getAccess_token();
			// access 토큰 만료되면 refresh token사용(유효기간 더 김)
			String refresh_Token = kakaoToken.getRefresh_token();

			// 토큰 확인
			System.out.println("@@@@ access_Token= " + access_Token);
			System.out.println("@@@@ refresh_Token= " + refresh_Token);

			// 통신 종료 부분. 중요.
			bufferedReader.close();
			bufferedWriter.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("카카오 토큰 생성 완료 = " + kakaoToken);

		return kakaoToken;
	}

	public KakaoToken refreshAccessTokenByRefreshToken(String refreshToken) {

		//
		KakaoToken kakaoToken = new KakaoToken(); // 반환자 생성

		// 기본 api 주소 생성.
		// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#refresh-token
		// 참조
		String strUrl = "https://kauth.kakao.com/oauth/token";

		//

		try {
			URL url = new URL(strUrl); // url 생성.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 지정한 url로, Http연결 생성.
			// 2. post 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

			// 3. 파라미터 셋업.
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			// 3.5 파라메터 삽입 시작.(homepage)
			// a. grant_type set, "authorization" hold
			stringBuilder.append("grant_type=refresh_token");
			// b. client_id = 자신의 api key 입력.
			stringBuilder.append("&client_id=");
			// c. 파라미터로 입력받은. 클라이언트의 refreshToken 대입
			stringBuilder.append("&refresh_token=" + refreshToken);
			// d. KOE010 에러 시 추가부분 https://yanoos.tistory.com/104
			stringBuilder.append("&client_secret=PZo0db9RNkZpC0Z7xjeNuLT87C6Nx6ew");

			// sb 만들고 나서. BufferedWriter에 탑재
			bufferedWriter.write(stringBuilder.toString());
			bufferedWriter.flush();// 필수.

			// 실제 요청(200을 반환하면 성공)
			int responseCode = conn.getResponseCode();
			System.out.println("@@@@액세스 코드 재발급, 반환 코드 = " + responseCode);

			// request를 통해, 반환받은 JSON타입의 Response 메시지를 읽어오기
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}

			System.out.println("@@@response body= " + result);

			// Jackson 라이브러리를 사용해서, json 파싱 ObjectMapper를 사용해서. java <-> json 을 사용가능
			ObjectMapper objectMapper = new ObjectMapper();
			// kakaoToken에 result를 KakaoToken.class 형식으로 변환하여 저장(?)
			kakaoToken = objectMapper.readValue(result, KakaoToken.class);

			// api호출용 access token
			String access_Token = kakaoToken.getAccess_token();
			// access 토큰 만료되면 refresh token사용(유효기간 더 김)
			String refresh_Token = kakaoToken.getRefresh_token();

			// 토큰 확인
			System.out.println("@@@@ new access_Token= " + access_Token);
			System.out.println("@@@@ refresh_Token(그대로?)= " + refresh_Token);

			// 통신 종료 부분. 중요.
			bufferedReader.close();
			bufferedWriter.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println(
				"access token 갱신 완료= " + kakaoToken.getAccess_token() + ", 만료 = " + kakaoToken.getExpires_in());

		return kakaoToken;
	}

}
