package com.cook.service.deepImg;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cook.model.deepImg.FileDto;

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



			//String Window_path = "E://mvcimage/" + fileDto.getFileName();
			String aswPath = "/mnt/s3storage/mvcimage/";
			String macPacth = "/Users/jungkihun/Documents/source/python/hc_db/mvcimage/";
			String Mac_path = aswPath + fileDto.getFileName();
			
			System.out.println("업로드한 파일 이름은= "+fileDto.getFileName());
			
			File newFileName = new File(Mac_path);
			// File newFileName = new File("~/mvcimage/"+dto.getFileName()); //리눅스

			// 전달된 내용을 실제 물리적인 파일로 저장해준다.
			uploadfile.transferTo(newFileName);

			System.out.println("성공1");

			return Mac_path;

		} else {

			return "파일이 없음.";
		}

	}

}
