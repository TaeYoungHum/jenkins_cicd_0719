package com.shupoha.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shupoha.service.FileTransferService;

@Controller
//@RequestMapping()
public class FoodFindController {

	@Autowired
	FileTransferService fileTransferService;

	@GetMapping("/uploadmain")
	public String upload(Model model) throws Exception {
		System.out.println("업로드 메인 내");
		model.addAttribute("check","사진을 넣어주세요");
	}
		return "uploadpage";
	}

	
	@PostMapping("/uploadfile")
	public String fileupload3(@RequestParam(value = "uploadfile") MultipartFile uploadfile, Model model)
			throws IllegalStateException, IOException {
		String catordog = fileTransferService.webToLocal(uploadfile); //파일 이동 및. 리스트 객체 생성 리턴.
		model.addAttribute("check", catordog); // 아직 안씀
		
		return "uploadpage2";
	}
	
	@GetMapping("check")
	public String checki() throws IllegalStateException, IOException {
		
		
		return "check";
	}
	
	

}