package com.cook.controller.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cook.model.api.MyApiKeyDto;
import com.cook.service.api.CallApiUrlService;
import com.cook.service.api.DecodingApiKeyService;
import com.cook.service.api.OpenApi_praticeService;
import com.cook.service.api.StringChangeService;

@RestController
public class OpenApiController {
	
	@Autowired
	OpenApi_praticeService openApi_praticeService;
	@Autowired
	MyApiKeyDto myApiKeyDto;
	@Autowired
	DecodingApiKeyService decodingApiKeyService;
	@Autowired
	CallApiUrlService callApiUrlService;
	@Autowired
	StringChangeService stringChangeService;
	
	
	@GetMapping("/search/{id}")
	public ResponseEntity<String[]> callOpenApi(@PathVariable("id") int id) {
		
		
		StringBuilder stringBuilder = new StringBuilder();
		String callbackS ="";
		
		openApi_praticeService.print();
		String decoding_value= decodingApiKeyService.decodingApiKey(myApiKeyDto.getMyApi());
		System.out.println(decoding_value);
		System.out.println("일단 디코딩 끝");
		
		//디코딩 끝난 api 키 삽입해서 콜. jason을 str 리턴
		callbackS= callApiUrlService.callApi(decoding_value, id); //뒤에 숫자는 foodNumber
		//
		System.out.println("통신 끝");
		
//		System.out.println(callbackS); //str 덩어리
		
		
		
		//여기서 부터는 str 변환
		//splict() 사용!
		String[] splict_callbackS = stringChangeService.stringSplit(callbackS);
		System.out.println("여기서부터 스플릿 된거 출력됨.");
		System.out.println("");
		
		
		//빈 "" 만 들어간거 제외하고 스트링 배열로 만듬
		String[] pure_callbackS = stringChangeService.stringRealData(splict_callbackS);
		//null 있어서. null 들어간 인덱스까지 체크함(pure_callbackS 크기는 100)
		int count_notNull = stringChangeService.checkNull(pure_callbackS);
		//체크한 인덱스를 Arrays.copyOf()에 넣어서. 크기 새로 정의해서 반환.
		pure_callbackS = Arrays.copyOf(pure_callbackS, count_notNull);
		System.out.println("길이 새로 정의="+pure_callbackS.length);
		
		System.out.println("\n\n");
		for(String callback :pure_callbackS) {
			
				System.out.println(callback);
				stringBuilder.append(callback);
				
		}
		
		//재조립
		
		
		
		
		System.out.println();
		System.out.println();
		
		return ResponseEntity.ok(pure_callbackS);
		
	}

}
