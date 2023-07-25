package com.cook.service.fileUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cook.model.customVo.OnlyRecipeboardPage;

@Component
public class UploadService {

	public void MultiFileUpload(OnlyRecipeboardPage reciveHtml, int newCookUid) {

		String aswPath = "/mnt/s3storage";
		String macPacth = "/Users/jungkihun/Documents/source/python/hc_db";
		String mkdirPath = aswPath + File.separator + "downloadimg" + File.separator + (Integer.toString(newCookUid));
		File newFolderCheck = new File(mkdirPath); // 이 경로로 File 객체 생성.

		// 경로 있는지 판별 후, 없으면 생성.
		if (!newFolderCheck.exists()) {
			boolean result = newFolderCheck.mkdirs();
			if (result) {
				System.out.println("폴더가 생성되었습니다.");
			} else {
				System.out.println("폴더 생성에 실패하였습니다.");
			}
		} else {
			System.out.println("이미 폴더가 존재합니다.");
		}

		// 2.이제 이 폴더 밑에. 이미지 파일 0~xx 번 넣어주기. //
		List<String> filenames = new ArrayList<>(); // 파일 이름 담을 객체 생성. images.size() 해서 업로드한 파일 갯수 파악
		// @RequestPart("images") List<MultipartFile> images 를 이용하자. 경로는 mkdirPath +
		// \0,1,2,3,4
		int fileIndex = 0;
		for (MultipartFile m : reciveHtml.getImages()) {

			String newFileName = mkdirPath + File.separator+ + fileIndex; // 0번부터 img 이름 생성.
			
			// 파일이름 "." 여러개 있을 때 처리 방법 추가 23.06.21 - 정기훈- 
			String filename = m.getOriginalFilename(); //파일 이름 저장 
			String reversedFilename = new StringBuilder(filename).reverse().toString(); // 파일 이름 뒤집기 
			int firstDotIndex = reversedFilename.indexOf("."); // "."이 몇개 있는지 파악 
			String rightOfLastDot = reversedFilename.substring(0, firstDotIndex); // 뒤집에 넣은거 첫 번째 있는거 추출 
			String finalText = new StringBuilder(rightOfLastDot).reverse().toString(); // 다시 파일 이름 뒤집기 
			
			String endFileNameString =   finalText;//m.getOriginalFilename().split("\\.")[1]; //파일 확장자 추출
			newFileName = newFileName+"."+endFileNameString; //확장자 탑재
			
			File newPathFile = new File(newFileName); // path 새로 설정해서. 새로운 파일 객체 생성.
			fileIndex++;

			// 파일 업로드 시작.(이동 -> 저장)
			try {
				m.transferTo(newPathFile); // 파일 객체 안에 들어있는 path 로 이동 및 저장.
				System.out.println((fileIndex - 1) + "파일 업로드 완료");

			} catch (Exception e) {
				// TODO: handle exception

				System.out.println("파일 업로드 실패");
				e.printStackTrace();
			}

			

		}

		// ================================File Upload E==========================================

	}

}
