package com.shupoha.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shupoha.model.FileDto;

@Service
public class FileTransferService {

	@Autowired
	FileDto fileDto;

	@Autowired
	SpringWebClientService catdogWebClientService;

	public String webToLocal(MultipartFile uploadfile) throws IOException {

		
		if (!uploadfile.isEmpty()) {
			// UUID를 이용해 unique한 파일 이름을 만들어준다.
			fileDto = new FileDto(uploadfile.getOriginalFilename(), uploadfile.getContentType());

			System.out.println("파일 타입은 = "+fileDto.getContentType());

			String Window_path = "E://mvcimage/" + fileDto.getFileName();
			
			System.out.println("업로드한 파일 이름은= "+uploadfile.getOriginalFilename().split("\\.")[1]);
			
			File newFileName = new File(Window_path);
			// File newFileName = new File("~/mvcimage/"+dto.getFileName()); //리눅스

			// 전달된 내용을 실제 물리적인 파일로 저장해준다.
			uploadfile.transferTo(newFileName);

			System.out.println("성공1");

			return Window_path;

		} else {

			return "파일이 없음.";
		}

	}

}
