package com.shupoha.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shupoha.service.FileTransferService;
import com.shupoha.service.FoodCategoryParser;
import com.shupoha.service.SpringWebClientService;

@RestController
@RequestMapping("/rest")
public class RestServiceController {

	@Autowired
	FileTransferService fileTransferService;
	@Autowired
	SpringWebClientService springWebClientService;
	@Autowired
	FoodCategoryParser foodCategoryParser;

	HashMap<String, String> map = new HashMap<>();

	@PostMapping("/uploadfile")
	public HashMap<String, String> fileupload3(@RequestPart(value = "uploadfile") MultipartFile uploadfile, Model model)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub

		String Window_path = null; // 파일 저장 위치와 파일 이름 들어감.
		String FoodParsing_data = null; // 판별 후 데이터 들어감

		System.out.println("r-c in path 파일 이동 시작");
		Window_path = fileTransferService.webToLocal(uploadfile);
		System.out.println("r-c in path 파일 이동 끝");

		// 통신시작 -> 서비스 안에 통신 내장.
		System.out.println("foodCategoryParser.foodParser(Window_path) 작동");
		FoodParsing_data = foodCategoryParser.foodParser(Window_path);
		System.out.println("foodCategoryParser.foodParser(Window_path) 끝");
		

		System.out.println(FoodParsing_data);
		System.out.println("=====================================================");
		map.put("FoodParsing_data", FoodParsing_data);

		return map;
	}
}
