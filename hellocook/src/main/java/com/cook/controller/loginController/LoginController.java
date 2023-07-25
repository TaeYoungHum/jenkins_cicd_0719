package com.cook.controller.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cook.model.login.GetClientInfoByAccessToken;
import com.cook.model.login.KakaoToken;
import com.cook.service.login.KakaoLoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	KakaoLoginService kakaoLoginService;
	@Autowired
	GetClientInfoByAccessToken getClientInfoByAccessToken;
	

	// 코드 받고. 그 코드를 바탕으로, 토큰 받고 하는 과정. 
	@GetMapping("kakao-redirect")
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) {
		System.out.println("login c in");
		
		if (code != null) {// 카카오측에서 보내준 code가 있다면 출력합니다
			System.out.println("code = " + code);
			
			//code 받은 뒤. 추가 카카오톡 토큰 받아오는 과정(홈피에 써있다.)
			
			KakaoToken kakaoToken = kakaoLoginService.requestToken(code);
			System.out.println("반환된 토큰 확인 = "+kakaoToken);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("getAccess_token = "+kakaoToken.getAccess_token());
			System.out.println("getRefresh_token = "+kakaoToken.getRefresh_token());
			System.out.println("getScope = "+kakaoToken.getScope());
			System.out.println("getToken_type= "+kakaoToken.getToken_type());
			System.out.println("getExpires_in = "+kakaoToken.getExpires_in());
			System.out.println("getRefresh_token_expires_in"+kakaoToken.getRefresh_token_expires_in());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			//받은 토큰을 바탕으로 사용자 정보 가져오기
			getClientInfoByAccessToken.bringInfo(kakaoToken.getAccess_token());
			
			
			//연습용 access 키 재설정. 원랜 만료될때 call 하는거임. 따로 컨트롤러 매서드
			
			KakaoToken kakaoTokenNew = kakaoLoginService.refreshAccessTokenByRefreshToken(kakaoToken.getRefresh_token());
			
 		}

		return "kakao-redirect";

	}

}





