package com.cook.service.deepImg;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SpringWebClientService {

	public String springWebClientHttpCall(String path) {
		
		System.out.println("1");
		 // Create a WebClient instance
        WebClient client = WebClient.create();
        System.out.println("2");
        // form을 만들어서. {"id" : path} 를 추가한다.
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", path); 
       
        System.out.println("3");
        // Send a POST request to a URL with the form data and retrieve the response as a Mono<String>
        Mono<String> responseMono = client.post()
                .uri("http://172.30.1.85:8000/catdogd/image") //프로젝트 명 바꿔주기
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)) // .fromFormData(formData) 메서드를 통해 body에 MultiValueMap 타입 객체 formData를 body로 변환
                .retrieve() //통신 요청보냄
                .bodyToMono(String.class); //Mono<String> 형태로 반환 해달라는 의미.
        System.out.println("4");
        // Block until the response is available and print the response content. https://thalals.tistory.com/379
        String response = responseMono.block(); //block 메서드를 사용해서 응답 기다림.
        //System.out.println(response);
        

        

        return response;
	}

}
