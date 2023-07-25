package com.cook.service.insertServie;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cook.model.customVo.OnlyRecipeboardPage;
import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.RecipeTableJpa;
import com.cook.model.jpa.repository.CookTableJpaRepository;

@Component
public class OnlyInsertRecipeTable {
	@Autowired
	CookTableJpaRepository cookTableJpaRepository;
	@Autowired
	CookTableJpa cookTableJpa;

	// List<MultipartFile> images 0619 이미지추가
	// 23.06.21 변경
	public List<RecipeTableJpa> RecipeTableParsingAndReturn(OnlyRecipeboardPage reciveHtml, int newCookTable) {
		System.out.println();
		System.out.println("OnlyInsertRecipeTable in. cookuid = " + newCookTable);
		System.out.println();

		List<RecipeTableJpa> recipeTableJpa_transList = new ArrayList<>(); // 1:N 관계
		// List<RecipeTableJpaId> recipeTableJpa_transIdList = new ArrayList<>(); // 1:N
		// 관계 2 (프라이머리 키가 여러개면 id 클래스가 1:1
		// 대응함.)

		// recipe 테이블에 맞게 파싱
		String introduceCook = reciveHtml.getIntroduceCook();

		// id 전달자

		// recipe 전달자
		RecipeTableJpa firstRecipeTableJpa = new RecipeTableJpa(); // 깊은 복사 위해서.

		firstRecipeTableJpa.setCook_introduction(introduceCook);
		firstRecipeTableJpa.setCook_cook_uid(newCookTable);

		recipeTableJpa_transList.add(firstRecipeTableJpa); // list row 추가.

		// String recipeFullTexts = ; // , 단위로 파싱해서. 쭉 다 넣어야함.

		// recipeFullTexts 파싱
		List<String> trans_Strings_List = new ArrayList<>(); // 리스트 담을 객체 생성.

		for (String s : reciveHtml.getRecipeFullTexts()) {
			trans_Strings_List.add(s); // RecipeFullTexts가 차곡차곡 리스트에 복사됨. ex) step1, 설명, step2, ....
		}

		// RecipeTableJpa로 변환.
		for (String s : trans_Strings_List) {

			// 전달자 생성
			RecipeTableJpa text_trans = new RecipeTableJpa();

			text_trans.setCook_cook_uid(newCookTable);
			text_trans.setRecipe_text(s);

			// 리스트에 쌍으로 객체 삽입
			recipeTableJpa_transList.add(text_trans);// 레시피 텍스트 쫙 다 각각의 row로 만듬.

		} // recipeFullTextsList 다 집어 넣음.

		
		
		// 06.21변경
		// 아직 이미지 파서는 구현안함.
		// 23.06.19 이미지 path 구현
		
		System.out.println("파일 패스 삽입 시작");
		List<Integer> indexSearchStep = new ArrayList<>(); // 리스트에 recipeTableJpa_transList에 Step + a 가 저장된 index 위치 얻음.
		int StepMarker = 0;
		for (RecipeTableJpa recipeTableJpa : recipeTableJpa_transList) {

			
			if (recipeTableJpa.getRecipe_text().contains("Step") && recipeTableJpa.getRecipe_text() != null) { // 현재 index에서 step 이 있는지 체크해서 있으면 좌표를 저장.

				indexSearchStep.add(StepMarker); // StepMarker가 현재 index 번호라서 저장. 이제 그럼
				// indexSearchStep 리스트엔 step 이 포함된 위치가 찍혀있음.

			}
			StepMarker++;
		}
		// indexSearchStep에 좌표가 찍혀 있으니.
		// addAll() 사용.

		int indexChange = 0; // 가중치
		int realFileExistChecker = 1; // 실제 파일이 있냐? 물어볼때 사용.
		int MultipartFile_index = 1; // 0은 대표 이미지.
		// String basePath = "E:\\downloadimg\\" + cookUid; //여기도 함수쓰자
		int onlyOneDo = 0;

		String aswPath = "/mnt/s3storage";
		String macPacth = "/Users/jungkihun/Documents/source/python/hc_db";
		String basePath = aswPath + File.separator + "downloadimg" + File.separator; // os 상관없이 쓰기위해서.

		for (int index : indexSearchStep) {

			// 0번 사진 cooktable에 update
			if (onlyOneDo == 0) { // 딱 1번만.
				
				// 파일이름 "." 여러개 있을 때 처리 방법 추가 23.06.21 - 정기훈- 
				String filename_0 = reciveHtml.getImages().get(0).getOriginalFilename(); //파일 이름 저장 
				String reversedFilename_0 = new StringBuilder(filename_0).reverse().toString(); // 파일 이름 뒤집기 
				int firstDotIndex_0 = reversedFilename_0.indexOf("."); // "."이 몇개 있는지 파악 
				String rightOfLastDot_0 = reversedFilename_0.substring(0, firstDotIndex_0); // 뒤집에 넣은거 첫 번째 있는거 추출 
				String finalText_0 = new StringBuilder(rightOfLastDot_0).reverse().toString(); // 다시 파일 이름 뒤집기 
				System.out.println("@@@@@@@@@@@@파일 확장자 확인용 : "+finalText_0);
				
				Path cookTableUpdate = Paths.get(basePath, Integer.toString(newCookTable), (Integer.toString(0) + "."
						+ finalText_0));
				System.out.println("@@@@@@@@@@@@파일 이름  확인용 : "+cookTableUpdate);
				// reciveHtml.getImages().get(0).getOriginalFilename().split("\\.")[1] // "." 여러개 있으면 안됨 
//				System.out.println(reciveHtml.getImages().get(0).getOriginalFilename().split("\\.").length);
//				for( String s : reciveHtml.getImages().get(0).getOriginalFilename().split("\\.")) {
//					System.out.println(s);
//				}
				File cookTable_img = new File(cookTableUpdate.toString());
				if (cookTable_img.exists()) { // 0.jpg 가 존재하면

					System.out.println("cook table imgPath update");
					CookTableJpa cookTableJpa_img;
					cookTableJpa_img = cookTableJpaRepository.findById(newCookTable).orElse(null); // 가져오고
					
					// 파일이름 "/" 여러개 있을 때 처리 방법 추가 23.06.21 - 정기훈- 
					String filenameDbName_0 = cookTableUpdate.toString(); //파일 이름 저장 
					String reversedFilenameDbName_0 = new StringBuilder(filenameDbName_0).reverse().toString(); // 파일 이름 뒤집기 
					int firstDotIndexDbName_0 = reversedFilenameDbName_0.indexOf("/"); // "/"이 몇개 있는지 파악 
					int secondDotIndexDbName_0 = reversedFilenameDbName_0.indexOf("/", firstDotIndexDbName_0 +1);
					int thirdDotIndexDbName_0 = reversedFilenameDbName_0.indexOf("/", secondDotIndexDbName_0 +1);
					String rightOfLastDotDbName_0 = reversedFilenameDbName_0.substring(0, thirdDotIndexDbName_0); // 뒤집에 넣은거 첫 번째 있는거 추출 
					String finalTextDbName_0 = new StringBuilder(rightOfLastDotDbName_0).reverse().toString(); // 다시 파일 이름 뒤집기
					System.out.println("@@@@@@@@@ DB에 대표 이미지 넣는 이름 : " + finalTextDbName_0);
					
					cookTableJpa_img.setRepresentativeCookImg(finalTextDbName_0); // img path 삽입
					cookTableJpaRepository.save(cookTableJpa_img); // update 기원 -> 있는 id니깐 insert 말고 update

				}

				onlyOneDo++;
			} // 딱 1번만 사용하고. 그 이후엔 ㄴ

			// 파일이름 "." 여러개 있을 때 처리 방법 추가 23.06.21 - 정기훈- 
			String filename = reciveHtml.getImages().get(MultipartFile_index).getOriginalFilename(); //파일 이름 저장 
			String reversedFilename = new StringBuilder(filename).reverse().toString(); // 파일 이름 뒤집기 
			int firstDotIndex = reversedFilename.indexOf("."); // "."이 몇개 있는지 파악 
			String rightOfLastDot = reversedFilename.substring(0, firstDotIndex); // 뒤집에 넣은거 첫 번째 있는거 추출 
			String finalText = new StringBuilder(rightOfLastDot).reverse().toString(); // 다시 파일 이름 뒤집기 
			System.out.println("@@@@@@@@@@@@파일 확장자 확인용 : "+finalText);
			
			// 파일 객체 생성
			// 이미지 풀 패스
			// images.get(MultipartFile_index).getOriginalFilename().split("\\.")[1]) -> 확장자
			// 부분
			//reciveHtml.getImages().get(MultipartFile_index).getOriginalFilename().split("\\.")[1]
			Path os_Path = Paths.get(basePath, Integer.toString(newCookTable), (Integer.toString(realFileExistChecker)
					+ "." + finalText)); // os_Path.toString()
																														// ->
																														// path
																														// 반환
			// 가능
			// ""+realFileExistChecker); // "E:\\downloadimg\\" + cookUid + \\
			// realFileExistChecker
			File check_imgExists = new File(os_Path.toString()); // 이 파일이 있냐! 물어볼 것.
			realFileExistChecker++; // 파일 이름 증가
			MultipartFile_index++; // MultipartFile용 index 증가

			if (check_imgExists.exists()) {
				index = index + indexChange; // img 패스를 넣어줘야 하는 위치 정보에 가중치 더해줌. 처음은 +0

				RecipeTableJpa AddImgPath = new RecipeTableJpa();
				AddImgPath.setCook_cook_uid(newCookTable);
				
				
				// 파일이름 "/" 여러개 있을 때 처리 방법 추가 23.06.21 - 정기훈- 
				String filenameDbName = check_imgExists.getPath(); //파일 이름 저장 
				String reversedFilenameDbName = new StringBuilder(filenameDbName).reverse().toString(); // 파일 이름 뒤집기 
				int firstDotIndexDbName = reversedFilenameDbName.indexOf("/"); // "/"이 몇개 있는지 파악 
				int secondDotIndexDbName = reversedFilenameDbName.indexOf("/", firstDotIndexDbName +1);
				int thirdDotIndexDbName = reversedFilenameDbName.indexOf("/", secondDotIndexDbName +1);
				String rightOfLastDotDbName = reversedFilenameDbName.substring(0, thirdDotIndexDbName); // 뒤집에 넣은거 첫 번째 있는거 추출 
				String finalTextDbName = new StringBuilder(rightOfLastDotDbName).reverse().toString(); // 다시 파일 이름 뒤집기
				System.out.println("@@@@@@@@@ DB에 넣는 이름 : " + finalTextDbName);
				
				AddImgPath.setRecipe_img_link(finalTextDbName); // 경로 얻어서. 이걸 Recipe_img_link 칼럼에 넣어줌.

				List<RecipeTableJpa> add_list1 = new ArrayList<>(1); // 저장용 리스트 1개 생성.
				add_list1.add(AddImgPath); // 넣어주고.

				recipeTableJpa_transList.addAll(index, add_list1); // addAll() 은 넣어줄 index + List를 파라메타로 받음. 삽입.
				indexChange++; // 가중치 증가
			}

		}

		return recipeTableJpa_transList;
	}

}
