package com.cook.controller.api;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cook.model.customVo.AjaxFindVo;
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
import com.cook.util.RandomCookUid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "recipeApi")
@RequestMapping("api/")
public class ApiController {

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

	@Autowired
	RestService restService;

	@Autowired
	FileTransferService fileTransferService;

	@Autowired
	FoodCategoryParser foodCategoryParser;

//	1. 그림 업로드 관련  api(컨틀로러  리턴은 json 형식)
//	api/upload/
//
//	2. 이름을 스트링 타입으로 보냈을때 리턴을 json 형식으로 요리 제목 관련해서 받는 api
//	api/search/name/{이름}
//
//	3. 2번에서 받은 음식 이름을 클릭시 관련 레시피가 나오는 api(클릭시 cook_uid를 서버에 전송 할꺼도 서버는 그것을 json 형식으로 내려보낼것이다.)
//	api/search/cookuid/{int형 번호}

	@ApiOperation(value = "upload", notes = "imgUpload")
	@PostMapping("upload")
	public ResponseEntity<String> upload(@RequestPart(value = "file") MultipartFile singleImg) throws Exception {

		String window_path = null; // 파일 저장 위치와 파일 이름 들어감.
		String foodParsing_data = null; // 판별 후 데이터 들어감

		System.out.println("r-c in path 파일 이동 시작");
		window_path = fileTransferService.webToLocal(singleImg);
		System.out.println("리턴 받은 패스" + window_path);
		System.out.println("r-c in path 파일 이동 끝");

		// 통신시작 -> 서비스 안에 통신 내장.
		System.out.println("foodCategoryParser.foodParser(Window_path) 작동");
		foodParsing_data = foodCategoryParser.foodParser(window_path);
		System.out.println("foodCategoryParser.foodParser(Window_path) 끝");

		if (foodParsing_data == null) {
			foodParsing_data = "";
		}
		System.out.println(foodParsing_data);
		
		// header에 utf8 추가 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Charset", StandardCharsets.UTF_8.name());
        return ResponseEntity.ok().headers(headers).body(foodParsing_data);

	}

	@ApiOperation(value = "searchName", notes = "searchName")
	@GetMapping("search/name/{name}")
	public ResponseEntity<List<AjaxFindVo>> searchName(@PathVariable(value = "name") String name) throws Exception {
		
		// header에 utf8 추가 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Charset", StandardCharsets.UTF_8.name());
		
		return ResponseEntity.ok().headers(headers).body(restService.findCook(name));
	}

	@ApiOperation(value = "searchID", notes = "searchID")
	@GetMapping("search/cookuid/{id}") // RecipeTableJpa[]
	public ResponseEntity<List<RecipeTableJpa>> searchRecipe(@PathVariable(value = "id") String id) throws Exception {
		List<RecipeTableJpa> recipeTableJpas = new ArrayList<>();
		recipeTableJpas = recipeTableRepository.getAll(Integer.parseInt(id));

		return ResponseEntity.ok(recipeTableJpas);
	}

	@ApiOperation(value = "재료 찾기", notes = "재료 찾기")
	@GetMapping("search/ingredient/{id}")
	public ResponseEntity<List<String>> searchIngredient(@PathVariable(value = "id") String id) throws Exception {
		List<String> ingredients = new ArrayList<>(); // 최종 리턴할꺼
		List<Food_ingredientTableJpa> food_ingredientTableJpas; // jpa 리턴할꺼
		food_ingredientTableJpas = food_ingredientTableJpaRepository.getAll(Integer.parseInt(id));
		for (Food_ingredientTableJpa food_ingredientTableJpa : food_ingredientTableJpas) {
			for (String string : food_ingredientTableJpa.getIngredient().split(",")) { // split 사용시 [] 로 결과값 나옴.
				ingredients.add(string); // ingredient에 쫙 다 넣는다.
			}
		}
		return ResponseEntity.ok(ingredients);
	}

	// 위에껄 하고나니. cookuid로 음식 이름 검색할 필요성이 생겨서. 만듬.
	@ApiOperation(value = "음식 이름 id로 찾기", notes = "음식 이름 id로 찾기")
	@GetMapping("search/name_id/{id}")
	public ResponseEntity<String> searchName_id(@PathVariable(value = "id") String id) throws Exception {
		cookTableJpa = cookTableJpaRepository.findById(Integer.parseInt(id)).orElse(null);
		return ResponseEntity.ok(cookTableJpa.getCookTitle());
	}
	
	@GetMapping("search/name_check/{check}")
	public ResponseEntity<CookTableJpa> searchName_(@PathVariable(value = "check") String id) throws Exception {
		cookTableJpa = cookTableJpaRepository.findById(Integer.parseInt(id)).orElse(null);
		System.out.println(cookTableJpa.getCookDate());
		return ResponseEntity.ok(cookTableJpa);
	}
}
