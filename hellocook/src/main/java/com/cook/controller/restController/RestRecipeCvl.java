package com.cook.controller.restController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cook.model.customVo.AjaxFindVo;
import com.cook.model.customVo.OnlyRecipeboardPage;
import com.cook.model.customVo.OnlySubPageTagVo;
import com.cook.model.jpa.Category_lv1TableJpa;
import com.cook.model.jpa.Category_lv2TableJpa;
import com.cook.model.jpa.Category_lv3TableJpa;
import com.cook.model.jpa.CommentTableJpa;
import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.Food_ingredientTableJpa;
import com.cook.model.jpa.ProfileTableJpa;
import com.cook.model.jpa.RecipeTableJpa;
import com.cook.model.jpa.TagTableJpa;
import com.cook.model.jpa.repository.Category_lv1TableJpaRepository;
import com.cook.model.jpa.repository.Category_lv2TableJpaRepository;
import com.cook.model.jpa.repository.Category_lv3TableJpaRepository;
import com.cook.model.jpa.repository.CommentTableJpaRepository;
import com.cook.model.jpa.repository.CookTableJpaRepository;
import com.cook.model.jpa.repository.Food_ingredientTableJpaRepository;
import com.cook.model.jpa.repository.ProfileTableJpaRepository;
import com.cook.model.jpa.repository.RecipeTableRepository;
import com.cook.model.jpa.repository.TagTableJpaRepository;
import com.cook.service.Jpa_findService;
import com.cook.service.RestService;
import com.cook.service.deepImg.FileTransferService;
import com.cook.service.deepImg.FoodCategoryParser;
import com.cook.service.deepImg.SpringWebClientService;
import com.cook.service.fileUpload.UploadService;
import com.cook.service.insertServie.OnlyInsertCookTable;
import com.cook.service.insertServie.OnlyInsertFood_ingredientTableParsing;
import com.cook.service.insertServie.OnlyInsertRecipeTable;
import com.cook.service.insertServie.OnlyInsertTagTable;
import com.cook.util.RandomCookUid;

@RestController
@RequestMapping("/rest")
public class RestRecipeCvl {

	@Autowired
	Jpa_findService jpa_findService;

	// interface -> 특수 쿼리 짜고. jpa 기동하게 하는 부분
	@Autowired
	CookTableJpaRepository cookTableJpaRepository; // jpa_Data jpa
	@Autowired
	RecipeTableRepository recipeTableRepository;
	@Autowired
	Food_ingredientTableJpaRepository food_ingredientTableJpaRepository;
	@Autowired
	TagTableJpaRepository tagTableJpaRepository;
	@Autowired
	ProfileTableJpaRepository profileTableJpaRepository;
	@Autowired
	CommentTableJpaRepository commentTableJpaRepository;
	@Autowired
	Category_lv1TableJpaRepository category_lv1TableJpaRepository;
	@Autowired
	Category_lv2TableJpaRepository category_lv2TableJpaRepository;
	@Autowired
	Category_lv3TableJpaRepository category_lv3TableJpaRepository;

	// jpa_class => Jpa_style_vo임
	@Autowired
	CookTableJpa cookTableJpa;
	@Autowired
	RecipeTableJpa recipeTableJpa;
	@Autowired
	Food_ingredientTableJpa food_ingredientTableJpa;
	@Autowired
	TagTableJpa tagTableJpa;
	@Autowired
	ProfileTableJpa profileTableJpa;
	@Autowired
	CommentTableJpa commentTableJpa;
	@Autowired
	Category_lv1TableJpa category_lv1TableJpa;
	@Autowired
	Category_lv2TableJpa category_lv2TableJpa;
	@Autowired
	Category_lv3TableJpa category_lv3TableJpa;

	@Autowired
	OnlySubPageTagVo onlySubPageTagVo;

	@Autowired
	RandomCookUid randomCookUid;

	// 필요 선언 변수
	List<CookTableJpa> cookJpas;

	@Autowired
	AjaxFindVo ajaxFindVo;

	// 추가 리스트 선언, 23.05.26 작업중
	List<RecipeTableJpa> recipeTableJpas;
	List<Food_ingredientTableJpa> food_ingredientTableJpas;
	List<TagTableJpa> tagTableJpas;
	List<CommentTableJpa> commentTableJpas;
	List<ProfileTableJpa> profileTableJpas;
	List<Category_lv1TableJpa> category_lv1TableJpas;
	List<Category_lv2TableJpa> category_lv2TableJpas;
	List<Category_lv3TableJpa> category_lv3TableJpas;

	// 23.06.12 RestService 추가
	@Autowired
	RestService restService;
	// 23.06.15 레시피 보드용 service 추가
	@Autowired
	OnlyInsertCookTable onlyInsertCookTable;
	@Autowired
	OnlyInsertRecipeTable onlyInsertRecipeTable;
	// 23.06.16
	@Autowired
	OnlyInsertTagTable onlyInsertTagTable;
	@Autowired
	OnlyInsertFood_ingredientTableParsing onlyInsertFood_ingredientTableParsing;
	// 23.06.19
	@Autowired
	UploadService uploadService;
	
	//23.06.20
	@Autowired
	OnlyRecipeboardPage onlyRecipeboardPage;

	// 23.06.27 이미지 판별기 -기훈- 
    @Autowired
	FileTransferService fileTransferService;
	@Autowired
	SpringWebClientService springWebClientService;
	@Autowired
	FoodCategoryParser foodCategoryParser;

	HashMap<String, String> map = new HashMap<>();
	
	@GetMapping("/finCook/{cookName}")
	@ResponseBody
	public List<AjaxFindVo> findCook(@PathVariable(value = "cookName") String cookName) throws Exception {
		return restService.findCook(cookName);
	}

	@GetMapping("/findTag/{cookName}")
	@ResponseBody
	public List<AjaxFindVo> findTag(@PathVariable(value = "cookName") String cookName) throws Exception {
		return restService.findTag(cookName);
	}

	// 23.06.13 작성 확인 페이지 없어서, 대충 json 리턴
//	@PostMapping("/checkupdate")
//	@ResponseBody
//	public void insertTables() throws Exception{
//		
//		
//		
//		
//		
//		
//	}

	/*
	 
	 
	// 23.06.19 : 16일 대비 변동점 -> @RequestPart("images") List<MultipartFile> images 를
	// 사용해서 파일 업로드를 받게 설정.
	@ResponseBody
	@PostMapping("/checkupdate")
	public ResponseEntity<String> handleCheckUpdateRequest(@RequestBody Map<String, Object> requestData, HttpSession session
			) {
// @RequestPart("images") List<MultipartFile> images  230619 이미지추가 기능 
	
		System.out.println("rest in");
		// 사전 작업 부분.

		// 1. Map의 keySet을 얻고 이를 List로 변환하여 반환
		List<String> keys = new ArrayList<>(requestData.keySet());
		for (String s : keys) {

			System.out.print(s + " : ");
			System.out.println(requestData.get(s));

		}

		// ====================================s=====================================
		// 23.06.15
		// 1.cook table insert

		// insert 동작전 파싱 데이터 로드.
		cookTableJpa = onlyInsertCookTable.CookTableParsingAndReturn(requestData); // 파싱된거 리턴받고.
		// 실제CookTable insert! -> 쿼리 짜놓고. 기본 save 쓰는 이유 -> jpa 기본 save는 저장한 객체를 반환해줌. 이게
		// 강력함.
		CookTableJpa newCookTable = cookTableJpaRepository.save(cookTableJpa); // 새로 변수 만들어서. insert 한거 체크.

		
		
		// 1.5 ++++++++++ 23.06.19 -> 파일 업로드 관련
		final int newCookUid = newCookTable.getCook_uid(); // 이걸로 새로 만들 파일 이름 ex) 410 얻어옴.
		uploadService.MultiFileUpload(newCookUid, images); // 폴더 새로운 cookuid로 만들기 + 그림 파일 이동 및 저장 끝. 
		int fileCount = images.size(); // html에서 가져온 파일 갯수 저장. -> 안쓰면 지우자.
		
		// 2. recipe table insert
		// 레시피 테이블 insert 하기 위해 파싱!
		List<RecipeTableJpa> newRecipeTableJpas = onlyInsertRecipeTable.RecipeTableParsingAndReturn(requestData,
				newCookTable.getCook_uid()); //images 나주에 파라미터 추가 230619
		// System.out.println("recipe pasing end. uid =
		// "+newRecipeTableJpas.get(0).getId().getCookcookuid()+", ");
		System.out.println("recipe pasing end. uid = " + newRecipeTableJpas.get(0).getCookcookuid());

		// 파싱된 객체 파악
		int i = 0;
		for (RecipeTableJpa recipeTableJpa : newRecipeTableJpas) {

			System.out.println(i + "번째 객체 파악");
			System.out.println("ccuid=" + recipeTableJpa.getCookcookuid());
			System.out.println("recipeTextUid=" + recipeTableJpa.getRecipeTextUid());
			System.out.println("소개=" + recipeTableJpa.getCook_introduction());
			System.out.println("텍스트=" + recipeTableJpa.getRecipe_text());
			System.out.println("-----------------");
			i += 1;
		}

		// save! and return
		List<RecipeTableJpa> newList = recipeTableRepository.saveAll(newRecipeTableJpas);

		for (RecipeTableJpa recipeTableJpa : newList) {

			System.out.println("사이즈=" + newList.size());
			System.out.println("cookCookUid=" + recipeTableJpa.getCookcookuid());
			System.out.println("recipeTextUid=" + recipeTableJpa.getRecipeTextUid());
			System.out.println("소개=" + recipeTableJpa.getCook_introduction());
			System.out.println("텍스트=" + recipeTableJpa.getRecipe_text());
			System.out.println("=========================");
		}
		// =================================e=========================================

		// =================================s=========================================
		// 3.tag 테이블 insert.
		// 1.tags 들 반환(newCookTable.getCook_uid() 가 uid 값)
		List<TagTableJpa> tagTableJpasPasingReturn = onlyInsertTagTable.TagsParsing(requestData,
				newCookTable.getCook_uid());

		// 2. insert !
		List<TagTableJpa> afterInsertReturnTagTable = tagTableJpaRepository.saveAll(tagTableJpasPasingReturn);
		// =================================e=========================================

		// =================================s========================================

		// 4. ingredient Table

		// 1. 파싱!
		Food_ingredientTableJpa food_ingredientTablePasingReturn = onlyInsertFood_ingredientTableParsing
				.Food_ingredientTableParsingAndReturn(requestData, newCookTable.getCook_uid());
		// 2. save
		food_ingredientTableJpaRepository.save(food_ingredientTablePasingReturn);
		// =================================e========================================

		// =================================s========================================

		// 5. category Table -> 19일 폐기.
		
		session.setAttribute("newCookUid", newCookUid);
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@newCookUid에 ="+ newCookUid +"저장했어!!!!");
		System.out.println();
		String returnCookUid = Integer.toString(newCookUid);
		return ResponseEntity.ok(returnCookUid); // 200 OK 응답과 키값 리스트 반환
	}
	*/
	// upload 파일이 저장되는 부분  23.06.19 -기훈-
	@Value("${upload.path}") // 업로드 경로는 application.properties 파일에서 설정합니다.
	private String uploadPath;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("thumbnailloadimg") MultipartFile file) {
        if (file.isEmpty()) {
            // 파일이 선택되지 않았을 경우 처리
            return "redirect:/upload-error";
        }

        try {
            // 파일 저장 로직을 구현합니다.
        	 String fileName = file.getOriginalFilename();
             Path filePath = Paths.get(uploadPath, fileName);
             file.transferTo(filePath.toFile());
        	
//            String fileName = file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + fileName));
            // 파일 저장이 완료되었을 경우 처리
            return "redirect:/upload-success";
        } catch (IOException e) {
            // 파일 저장 중 오류 발생 시 처리
            return "redirect:/upload-error";
        }
    }
    @ResponseBody
    @PostMapping("/checkupdate1")
    public ResponseEntity<List<String>> testUpload(@RequestPart("images") List<MultipartFile> images
			, HttpSession session) {
    	
    	try {
    		Thread.sleep(5000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	System.out.println("checkupdate1 in");
		Integer value = (Integer) session.getAttribute("newCookUid");
		if (value != null) {
			int intValue = value.intValue();
			System.out.println(intValue);
			// 이후 로직 수행
		} else {
			// "newCookUid" 값이 설정되지 않은 경우에 대한 처리
			System.out.println("@@@@@@@@@@@@ 값 안넘어옴.");
			
		}
    	
    	System.out.println(value);
//      List<String> test = new ArrayList<>();
//      test.add("1");
//    	return ResponseEntity.ok(test); // 200 OK 응답과 키값 리스트 반환
    	
    	List<String> test = new ArrayList<>();
    	
    	for (MultipartFile file : images) {
            if (file.isEmpty()) {
                // 파일이 선택되지 않았을 경우 처리
                return ResponseEntity.badRequest().build(); // 파일 업로드 실패 응답
            }

            try {
                // 파일 저장 로직을 구현합니다.
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(uploadPath, fileName);
                file.transferTo(filePath.toFile());

                // 파일 저장이 완료되었을 경우 처리
                test.add(fileName);
            } catch (IOException e) {
                // 파일 저장 중 오류 발생 시 처리
                return ResponseEntity.badRequest().build(); // 파일 업로드 실패 응답
            }
        }
        
        return ResponseEntity.ok(test); // 200 OK 응답과 키값 리스트 반환
    }
    

//    @PostMapping(value = "/test", consumes = "multipart/form-data")
//    public ResponseEntity<String> testRecipeboard1( @ModelAttribute OnlyRecipeboardPage reciveHtml){
//    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ test @@@@@@@@@@@@@@@@@@@@@@@");
//    	
//    	
//    	System.out.println("1. 요리 제목 = "+ reciveHtml.getCookTitle());
//    	System.out.println("2. 요리 설명 = "+ reciveHtml.getIntroduceCook());
//    	System.out.println("3. 요리 태그 = "+ reciveHtml.getTags());
//    	System.out.println("4. 요리 인분= "+ reciveHtml.getNumber_of_people());
//    	System.out.println("5. 요리 시간 = "+ reciveHtml.getCook_time());
//    	System.out.println("6. 요리 준비 = "+ reciveHtml.getPreparation_time());
//    	
//    	for(MultipartFile m : reciveHtml.getImages()) {
//    		System.out.println("7. 파일 객체="+m.getOriginalFilename());
//    	}
//    	
//    	System.out.println("요리 과정 = "+reciveHtml.getRecipeFullTexts());
//    	
//    	
//    	return ResponseEntity.ok("200");
//    }
    
    @PostMapping(value = "/test", consumes = "multipart/form-data")
    public ResponseEntity<String> testRecipeboard1( @ModelAttribute OnlyRecipeboardPage reciveHtml){
    	
    	//성공 @ModelAttribute OnlyRecipeboardPage reciveHtml, @RequestPart(value = "images") List<MultipartFile> files
    	
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ test @@@@@@@@@@@@@@@@@@@@@@@");
    	//@RequestPart(name = "reciveHtml") OnlyRecipeboardPage reciveHtml,@RequestPart(value ="images") List<MultipartFile> files
    	//@ModelAttribute OnlyRecipeboardPage reciveHtml
    	System.out.println("1. 요리 제목 = "+ reciveHtml.getCookTitle());
    	System.out.println("2. 요리 설명 = "+ reciveHtml.getIntroduceCook());
    	System.out.println("3. 요리 태그 = "+ reciveHtml.getTags());
    	System.out.println("4. 요리 인분= "+ reciveHtml.getNumber_of_people());
    	System.out.println("5. 요리 시간 = "+ reciveHtml.getCook_time());
    	System.out.println("6. 요리 준비 = "+ reciveHtml.getPreparation_time());
    	System.out.println("7. 요리 과정상세 = "+reciveHtml.getRecipeFullTexts());
    	
    	
    	
		int multipartFile_index = 1;

		if (reciveHtml.getImages() != null) {
			for (MultipartFile multipartFile : reciveHtml.getImages()) {

				System.out.println(multipartFile_index + "번째 그림파일 이름 = " + multipartFile.getOriginalFilename());

				multipartFile_index++;
			}
		}
    	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    	//23.06.21. 파싱부분 재 작성.
		System.out.println("rest in");
		
		
		// 1.cook table insert
		// insert 동작전 파싱 데이터 로드.
		//================================ cookTableJpa insert s=========================================
		cookTableJpa = onlyInsertCookTable.CookTableParsingAndReturn(reciveHtml); // 파싱된거 리턴받고.(재작성.)
		// 실제CookTable insert! -> 쿼리 짜놓고. 기본 save 쓰는 이유 -> jpa 기본 save는 저장한 객체를 반환해줌. 이게
		// 강력함.
		CookTableJpa newCookTable = cookTableJpaRepository.save(cookTableJpa); // 새로 변수 만들어서. insert 한거 체크.
		
		final int newCookUid = newCookTable.getCook_uid(); // 상수 선언.
		//================================ cookTableJpa insert e=========================================
		
		// 1.5 ++++++++++ 23.06.19 -> 파일 업로드 관련
		uploadService.MultiFileUpload(reciveHtml, newCookUid); // 폴더 새로운 cookuid로 만들기 + 그림 파일 이동 및 저장 끝.
		
		int fileCount = reciveHtml.getImages().size(); // html에서 가져온 파일 갯수 저장. -> 안쓰면 지우자.
		
		//================================ recipe table insert s=========================================
		// 2. recipe table insert
		
		// ㄱ. 레시피 테이블 insert 하기 위해 파싱!
		List<RecipeTableJpa> newRecipeTableJpas = onlyInsertRecipeTable.RecipeTableParsingAndReturn(reciveHtml, newCookUid); // images 나주에 파라미터 추가 230619
		
		// 파싱된 객체 파악
		System.out.println("recipe pasing end. uid = " + newRecipeTableJpas.get(0).getCookcookuid());

		int i = 0;
		for (RecipeTableJpa recipeTableJpa : newRecipeTableJpas) {

			System.out.println(i + "번째 객체 파악");
			System.out.println("ccuid=" + recipeTableJpa.getCookcookuid());
			System.out.println("recipeTextUid=" + recipeTableJpa.getRecipeTextUid());
			System.out.println("소개=" + recipeTableJpa.getCook_introduction());
			System.out.println("텍스트=" + recipeTableJpa.getRecipe_text());
			System.out.println("imgPath = " + recipeTableJpa.getRecipe_img_link());
			System.out.println("-----------------");
			i += 1;
		}

		// ㄴ. save! and return
		List<RecipeTableJpa> newList = recipeTableRepository.saveAll(newRecipeTableJpas);

		//확인
		System.out.println("@@@@@@@@@recipetable 세이브 후.@@@@@@@@@");
		for (RecipeTableJpa recipeTableJpa : newList) {

			System.out.println("사이즈=" + newList.size());
			System.out.println("cookCookUid=" + recipeTableJpa.getCookcookuid());
			System.out.println("recipeTextUid=" + recipeTableJpa.getRecipeTextUid());
			System.out.println("소개=" + recipeTableJpa.getCook_introduction());
			System.out.println("텍스트=" + recipeTableJpa.getRecipe_text());
			System.out.println("=========================");
		}
		// =================================recipe table insert e=========================================
		
		// =================================tag insert s=========================================
		// 3.tag 테이블 insert. 23.06.21 변경.
		// a.tags 들 반환(newCookTable.getCook_uid() 가 uid 값)
		List<TagTableJpa> tagTableJpasPasingReturn = onlyInsertTagTable.TagsParsing(reciveHtml, newCookUid);

		// b. insert !
		List<TagTableJpa> afterInsertReturnTagTable = tagTableJpaRepository.saveAll(tagTableJpasPasingReturn);
		// ================================= tag insert e=========================================

		// =================================ingredient Table s========================================
		// 4. ingredient Table

		// a. 파싱!
		Food_ingredientTableJpa food_ingredientTablePasingReturn = onlyInsertFood_ingredientTableParsing
				.Food_ingredientTableParsingAndReturn(reciveHtml, newCookUid);
		// b. save
		food_ingredientTableJpaRepository.save(food_ingredientTablePasingReturn);
		// =================================ingredient Table e========================================

		
		
		
		
    	return ResponseEntity.ok("200");
    }
    
	
	// 23.06.27 이미지 판별기 -기훈- 
	@PostMapping("/uploadfile")
	public HashMap<String, String> fileupload3(@RequestPart(value = "uploadfile") MultipartFile uploadfile, Model model)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub

		String Window_path = null; // 파일 저장 위치와 파일 이름 들어감.
		String FoodParsing_data = null; // 판별 후 데이터 들어감

		System.out.println("r-c in path 파일 이동 시작");
		Window_path = fileTransferService.webToLocal(uploadfile);
		System.out.println("리턴 받은 패스" + Window_path);
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
