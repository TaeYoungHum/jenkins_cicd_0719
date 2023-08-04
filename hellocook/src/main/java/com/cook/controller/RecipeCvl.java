package com.cook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cook.model.customVo.OnlySubPageTagVo;
import com.cook.model.customVo.WeekRanKVo;
import com.cook.model.jpa.Category_lv1TableJpa;
import com.cook.model.jpa.Category_lv2TableJpa;
import com.cook.model.jpa.Category_lv3TableJpa;
import com.cook.model.jpa.CommentTableJpa;
import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.Food_ingredientTableJpa;
import com.cook.model.jpa.ProfileTableJpa;
import com.cook.model.jpa.RankTableJpa;
import com.cook.model.jpa.RecipeTableJpa;
import com.cook.model.jpa.TagTableJpa;
import com.cook.model.jpa.repository.Category_lv1TableJpaRepository;
import com.cook.model.jpa.repository.Category_lv2TableJpaRepository;
import com.cook.model.jpa.repository.Category_lv3TableJpaRepository;
import com.cook.model.jpa.repository.CommentTableJpaRepository;
import com.cook.model.jpa.repository.CookTableJpaRepository;
import com.cook.model.jpa.repository.Food_ingredientTableJpaRepository;
import com.cook.model.jpa.repository.ProfileTableJpaRepository;
import com.cook.model.jpa.repository.RankTableJpaRepository;
import com.cook.model.jpa.repository.RecipeTableRepository;
import com.cook.model.jpa.repository.TagTableJpaRepository;
import com.cook.service.Jpa_findService;
import com.cook.service.OnlyAdminWeekDoService;
import com.cook.service.RestService;
import com.cook.util.RandomCookUid;
import com.cook.util.googleApi.ConvertRecipeTTS;
import com.cook.util.googleApi.GoogleTtsService;

@Controller("recipeController") // Mysterious Recipe Dictionary
//@RequestMapping("/")
public class RecipeCvl implements RecipeController {

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
	
	// 23.06.09 검새부분 
	List<CookTableJpa> findCooks;
	List<CookTableJpa> findTags;

	// 추가 리스트 선언, 23.05.26 작업중
	List<RecipeTableJpa> recipeTableJpas;
	List<Food_ingredientTableJpa> food_ingredientTableJpas;
	List<TagTableJpa> tagTableJpas;
	List<CommentTableJpa> commentTableJpas;
	List<ProfileTableJpa> profileTableJpas;
	List<Category_lv1TableJpa> category_lv1TableJpas;
	List<Category_lv2TableJpa> category_lv2TableJpas;
	List<Category_lv3TableJpa> category_lv3TableJpas;
	

	// 23.06.09
	@Autowired
	OnlyAdminWeekDoService onlyAdminWeekDoService;
	@Autowired
	RankTableJpa rankTableJpa;
	@Autowired
	RankTableJpaRepository rankTableJpaRepository;

	// 23.06.12 RestService 추가
	@Autowired
	RestService restService;
	
	//23.06.26 tts 부분 추가
	@Autowired
	ConvertRecipeTTS convertRecipeTTS;
	@Autowired
	GoogleTtsService googleTtsService;

	@Override
	@GetMapping("/")
	public String viewMain(Model model) throws Exception {
		// TODO Auto-generated method stub
		
		//1. 23.06.09 6개 파싱부분.
		rankTableJpa=rankTableJpaRepository.findById(1).orElse(null);
		
		String rank6 =""+rankTableJpa.getRank_week();
		
		System.out.println("==============================");
		System.out.println(rank6);
		
		//2. 받아왔으니 실제 파싱
		List<Integer> rank6_parsings_list = new ArrayList<>();
		
		String[] rank6_parsings = rank6.split(",");
		
		//다 숫자로 변환. rank6_parsings_list
		for(String s : rank6_parsings) {
			
			rank6_parsings_list.add(Integer.parseInt(s.trim()));
			
		}
		
		//파싱 끝 rank6_parsings_list 에 다 담겨있다.
		
		//2. 파싱된 6개의 cook uid를 바탕으로 그것만의 cookjpa 빼오기, 반복문 안쓸꺼면!? 하드코딩!???(지금 main html보니깐 반복문 쓰면 무너질 것 같음 ㅠ)
		CookTableJpa cookTableJpa1 = new CookTableJpa();
		CookTableJpa cookTableJpa2 = new CookTableJpa();
		CookTableJpa cookTableJpa3 = new CookTableJpa();
		CookTableJpa cookTableJpa4 = new CookTableJpa();
		CookTableJpa cookTableJpa5 = new CookTableJpa();
		CookTableJpa cookTableJpa6 = new CookTableJpa();
		
		cookTableJpa1=cookTableJpaRepository.findById(rank6_parsings_list.get(0)).orElse(null);
		cookTableJpa2=cookTableJpaRepository.findById(rank6_parsings_list.get(1)).orElse(null);
		cookTableJpa3=cookTableJpaRepository.findById(rank6_parsings_list.get(2)).orElse(null);
		cookTableJpa4=cookTableJpaRepository.findById(rank6_parsings_list.get(3)).orElse(null);
		cookTableJpa5=cookTableJpaRepository.findById(rank6_parsings_list.get(4)).orElse(null);
		cookTableJpa6=cookTableJpaRepository.findById(rank6_parsings_list.get(5)).orElse(null);
		//23.06.12 추가 recipe table 내리기.(음식 설명부분 발췌)
		List<String> foodDescribes = new ArrayList<>();
		
		for(int cookCookuid : rank6_parsings_list) {
			foodDescribes.add(recipeTableRepository.getAll(cookCookuid).get(0).getCook_introduction());
			
		}
		//model.addAttribute("foodDescribes",foodDescribes); 로 html에 내릴 것.
		
		
		
		//23.06.12
		//3. 맨처음 광고부분 3개 큰 화면용 자료 추출 부.->rank table no=2, row를 참조할 것. 거기에 uid 담겨있다.
		//현재 "345, 30, 275" 가 담겨있다.
		
		//3.1 cookTable 관련
		// ㄱ. no=2 받아오자
		rankTableJpa = rankTableJpaRepository.findById(2).orElse(null);
		String ad3 = "" + rankTableJpa.getRank_week();
		// ㄴ. 받아온 번호들 파싱
		List<Integer> ad3_parsings_list = new ArrayList<>();
		String[] ad3_parsings = ad3.split(",");
		// ㄷ. 다 숫자로 변환. ad3_parsings_list
		for (String s : ad3_parsings) {

			ad3_parsings_list.add(Integer.parseInt(s.trim()));

		}
		//파싱 끝 ad3_parsings_list 에 다 담겨있다.
		
		//ㄹ. 실제 모델로 내리기 위해 값 대입.
		List<CookTableJpa> adCookTableJpas = new ArrayList<>(3);
		//유지 보수를 위해서 강화된 for문 사용.
		for(int ad3_parsing : ad3_parsings_list ) {
			
			adCookTableJpas.add(cookTableJpaRepository.findById(ad3_parsing).orElse(null));
		} 
		
		//이제 model.addAttribute("adCookTableJpas",adCookTableJpas); 로 html에 내릴 것.
		
		//3.2 Tag관련
		//위에서 가져온 uid를 사용한다.(ad3_parsings_list)
		//ㄱ. Tag 테이블 List 생성 -> 1:N 관계라, OnlySubPageTagVo를 사용해서. 각 uid에 맞는 tag들을 다 담아줄 것이다.
		List<OnlySubPageTagVo> adTagTableJpas = new ArrayList<>(3);
		//ㄴ. 그러기 위해서. 일단 List<TagTable> 을 생성하자.
		List<TagTableJpa> tableJpas = new ArrayList<>();
		
		
		for(int ad3_parsing : ad3_parsings_list) {
			tableJpas=tagTableJpaRepository.getAll(ad3_parsing);
			OnlySubPageTagVo deepCopy = new OnlySubPageTagVo(); //깊은 복사용. 전달자 생성.(생성자로 객체 생성.)
			deepCopy.setTagTableJpas(tableJpas);
			deepCopy.setTagTableJpas_size(tableJpas); 
			
			adTagTableJpas.add(deepCopy);
		
		}
		//이제 model.addAttribute("adTagTableJpas",adTagTableJpas); 로 html에 내릴 것.
		
		// 4. 랜덤을 사용해서 3개 짜리 2쌍. 페이지 구성용.
		List<Integer> randomCookUids = randomCookUid.outPutCookUid_list6(377); // 6개의 랜덤 숫자 리스트.
		//체크용
		for(int i : randomCookUids ) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(i);
			
		}

		// random cook table list
		List<CookTableJpa> ranCookTableJpas = new ArrayList<>();
		// 6개 다 리스트 형식으로 저장.
		for (int cookUid : randomCookUids) {

			ranCookTableJpas.add(cookTableJpaRepository.findById(cookUid).orElse(null));

		} // 이제 model.addAttribute("ranCookTableJpas",ranCookTableJpas); 로 html에 내릴 것

		// random tag Table list
		// 위에서 가져온 uid를 사용한다.(randomCookUids)
		// ㄱ. Tag 테이블 List 생성 -> 1:N 관계라, OnlySubPageTagVo를 사용해서. 각 uid에 맞는 tag들을 다 담아줄
		// 것이다.
		List<OnlySubPageTagVo> ranTagTableJpas = new ArrayList<>(6);
		// ㄴ. 그러기 위해서. 일단 List<TagTable> 을 생성하자.
		

		for (int randomCookuid : randomCookUids) {
			List<TagTableJpa> rantableJpas = new ArrayList<>();
			rantableJpas = tagTableJpaRepository.getAll(randomCookuid);
			OnlySubPageTagVo deepCopy2 = new OnlySubPageTagVo(); // 깊은 복사용2. 전달자 생성.(생성자로 객체 생성.)
			deepCopy2.setTagTableJpas(rantableJpas);
			deepCopy2.setTagTableJpas_size(rantableJpas);

			ranTagTableJpas.add(deepCopy2);

		}
		// 이제 model.addAttribute("ranTagTableJpas",ranTagTableJpas); 로 html에 내릴 것.
		
		//16일 확인용 randomCookUids
		int index_i = 0;
		for(OnlySubPageTagVo onlySubPageTagVo : ranTagTableJpas) {
			
			System.out.print("uid="+randomCookUids.get(index_i)+", "+"index "+index_i+"번째 tags = ");
			for(TagTableJpa tag : onlySubPageTagVo.getTagTableJpas()) {
				System.out.print(tag.getTag_name()+",");
				
			}System.out.println();
			index_i++;
		}
		
		
		//5.최신순 5개 뽑기.
		List<CookTableJpa> cookTableJpas_newSort5 = new ArrayList<>();
		cookTableJpas_newSort5=cookTableJpaRepository.findTop5ByOrderByCookDateDesc();
		//이제 model.addAttribute("cookTableJpas_newSort5",cookTableJpas_newSort5); 로 html에 내릴 것.
		//최신순 5개의 tag
		List<Integer> tag_newSort5_uids = new ArrayList<>();
		
		//최신의 uids 저장
		for(CookTableJpa cookTableJpa : cookTableJpas_newSort5) {
			
			tag_newSort5_uids.add(cookTableJpa.getCook_uid());
			
		}//tag_newSort5_uids 
		
		//이걸 tag로
		List<TagTableJpa> tags_newSort5 = new ArrayList<>();
		List<OnlySubPageTagVo> newTagTableJpas = new ArrayList<>(5);
		for (int CookCookUid : tag_newSort5_uids) {
			tags_newSort5 = tagTableJpaRepository.getAll(CookCookUid);
			
			int tags_newSort5Size = tags_newSort5.size();
			
			if( tags_newSort5Size < 2){
				for(int i = 0; i < 2 - tags_newSort5Size; i++) {
					TagTableJpa deepCopyTagTable = new TagTableJpa();
					deepCopyTagTable.setCook_cook_uid(CookCookUid);
					deepCopyTagTable.setTag_name("");
					tags_newSort5.add(deepCopyTagTable);
				}
			}
			OnlySubPageTagVo deepCopy3 = new OnlySubPageTagVo(); // 깊은 복사용2. 전달자 생성.(생성자로 객체 생성.)
			deepCopy3.setTagTableJpas(tags_newSort5);
			deepCopy3.setTagTableJpas_size(tags_newSort5);

			newTagTableJpas.add(deepCopy3);

		}
		// 이제 model.addAttribute("newTagTableJpas",newTagTableJpas); 로 html에 내릴 것.
		
		

		//===============================================================================
		// 선언부
		// cook table
		CookTableJpa cookJpa = cookTableJpaRepository.findById(1).get();
		// tag table
		tagTableJpas = tagTableJpaRepository.getAll(1);
		// recipe table
		recipeTableJpas = recipeTableRepository.getAll(1);
		// food_ingredient_table
		food_ingredientTableJpas = food_ingredientTableJpaRepository.getAll(1);
		// comment_table
		commentTableJpas = commentTableJpaRepository.findAll();

		// List<String> cook_introduction = new ArrayList<>();

		// 연산부분

		// 1. profile id null 체크
		if (cookJpa.getProfileId() == null) {
			cookJpa.setProfileId("기본 data_base 입니다.");

		}

		// html로 내려보내는 부분
		model.addAttribute("cookJpa", cookJpa);
		model.addAttribute("tagTableJpas", tagTableJpas);
		// model.addAttribute("cook_introduction", cook_introduction);
		model.addAttribute("recipeTableJpas", recipeTableJpas);
		model.addAttribute("food_ingredientTableJpas", food_ingredientTableJpas);
		
		//23.06.09
		model.addAttribute("cookTableJpa1",cookTableJpa1);
		model.addAttribute("cookTableJpa2",cookTableJpa2);
		model.addAttribute("cookTableJpa3",cookTableJpa3);
		model.addAttribute("cookTableJpa4",cookTableJpa4);
		model.addAttribute("cookTableJpa5",cookTableJpa5);
		model.addAttribute("cookTableJpa6",cookTableJpa6);
		
		//23.06.12
		
		//추가로 음식 설명 6개 내리기
		model.addAttribute("foodDescribes",foodDescribes);
		
		//(3개) 광고(처음 큰 스위프트 화면)
		model.addAttribute("adCookTableJpas",adCookTableJpas); //html에서 adCookTableJpas[] 로 사용하자. rank table 2번 row 참조중.
		model.addAttribute("adTagTableJpas",adTagTableJpas); //html에서 adTagTableJpas[] -> 이거 필요 없나봄?(23.06.12) 
		
		//아래 3개 랜덤으로. 2쌍. (6개)
		model.addAttribute("ranCookTableJpas",ranCookTableJpas); //ranCookTableJpas[]
		model.addAttribute("ranTagTableJpas",ranTagTableJpas); // ranCookTableJpas[]
		
		//최신순 5개
		model.addAttribute("cookTableJpas_newSort5",cookTableJpas_newSort5);
		model.addAttribute("newTagTableJpas",newTagTableJpas);
		
		
		
		
		
		
		
	
		
		// mainpage 관련 컨트롤러 입니다.
		
		
		return "mainpage";
	}

	@Override
	@GetMapping("/mrd/subpage") 
	public String callSubPage(Model model) throws Exception {
		// TODO Auto-generated method stub
		// 0.
		List<CookTableJpa> cookTableJpas_2 = new ArrayList<>(); // add 위해서 객체 투입
		List<OnlySubPageTagVo> onlySubPageTagVos = new ArrayList<>(); // 마찬가지로. 객체 바로 투입

		// 1. cook uid pick up

		// 임의의 숫자를 대입해보자
		int maxIndex = 377; // 이걸 바꿔주면 인덱스 검색 최대치 증가
		int imgNum = 20; // 서브 페이지 이미지 갯수 설정 부분
		List<Integer> RandomCookUids = new ArrayList<>(); // add 함수 바로 사용하기 위해 객체 투입.
		for (int i = 1; i <= imgNum; i++) {

			RandomCookUids.add(randomCookUid.outPutCookUid(maxIndex)); // 0~maxIndex까지 랜덤한 숫자 1개를 뽑아서. list에 저장. 이부분은
																		// 나중에 서비스 할때 다른거로 대체해야함.

		} // 필요한 uid 받음.

		// uid list consol check. 확인용임 지워도 되는 부분.
		System.out.println("cookUid 크기는 = " + RandomCookUids.size());
		System.out.println("+++++++++++++++++");
		for (int cookUid : RandomCookUids) {

			System.out.print(cookUid + ", ");

		}
		System.out.println();
		System.out.println("+++++++++++++++++");

		// 2. pick up num ->make list(위에서 리스트로 만듬)

		// 3.find tables. (성능 최적화 위해서는 (ㄱ, ㄴ) for문 통합해야함. 가능하고. 지금은 연습용)

		// ㄱ. cook table
		for (int index = 0; index < RandomCookUids.size(); index++) {

			// 확인용
			System.out.println("RandomCookUids.get(index) = " + RandomCookUids.get(index));
			System.out.println();

			cookTableJpas_2.add(cookTableJpaRepository.findById(RandomCookUids.get(index)).get()); // 이러면 cooktable
																									// 리스트에, 저장됨.

		}

		// ㄴ. tag table
		for (int index = 0; index < RandomCookUids.size(); index++) {

			// a.
			tagTableJpas = tagTableJpaRepository.getAll(RandomCookUids.get(index)); // RandomCookUids list에 저장된. cook
																					// cook uid를 for 돌때마다 1개씩 꺼내서, 그
																					// cook uid에 해당하는 모든 tag 끌어와서
																					// list<tagTableJpas> 에 저장하고.
			// 체크용(여긴 문제 없음.)
			System.out.print("[" + index + "]" + "'s tag = ");
			for (TagTableJpa tagVo : tagTableJpas) {
				System.out.print(tagVo.getTag_name() + ", ");

			}
			System.out.println();

			// ****** 가장 중요한 부분. deep copy 부분. 이렇게 새로운 객체를 생성자로 만들어야함. 특히 List<> 같이
			// 배열 속성이 있는 것들은. 반복문 중간 중간에 dto나 vo를 초기화 해줘야한다면. 이렇게 같은 타입의 변수를 생성자를 call해서 객체
			// 투입을 하고
			// 이렇게 다른 객체들을 목표 list 같은것에 넣어줘야함. 이러지 않으면 객체를 초기화할때 list 값 자체가 다 초기화 되던가. 다른
			// list도 같은 객체를 공유하게 되서.
			// 얕은 복사가 일어나서 자료가 제대로 전달이 안됨. 그래서 메모리 누수는 있지만. for 중간에 계속 새로운 객체를 선언하게 만드는 것.
			OnlySubPageTagVo onlySubPageTagVo_deep = new OnlySubPageTagVo();

			List<TagTableJpa> tagTableJpasChange = new ArrayList<>(3);
			
			int tagIndex = 0;
		
			for(TagTableJpa a : tagTableJpas ) {
				if(tagIndex < 3) {
					tagTableJpasChange.add(a);
				}
				tagIndex ++;
			}
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@태그테이블사이즈: " + tagTableJpasChange.size());
			// b. 이 tag list를, onlySubPageTagVo의 리스트 필드에 저장
			onlySubPageTagVo_deep.setTagTableJpas(tagTableJpasChange); // private List<TagTableJpa> tagTableJpas
			// + 각각의 tag list 사이즈를 전달
			onlySubPageTagVo_deep.setTagTableJpas_size(tagTableJpasChange);

			// 체크용(지워도 됨)
//			System.out.print("2번째 -> "+"[" + index + "]" + "'s tag(size = "+onlySubPageTagVo_deep.getTagTableJpas_size()
//			+")"+ "= ");
//
//			for (TagTableJpa tagVo : onlySubPageTagVo_deep.getTagTableJpas()) {
//				System.out.print(tagVo.getTag_name() + ", ");
//
//			}
//			System.out.println();

			// c. 이 onlySubPageTagVo 뭉치를 차곡 차곡 저장
			onlySubPageTagVos.add(onlySubPageTagVo_deep);

//			//d.clear
			tagTableJpas.clear(); // 초기화(dto(vo) 초기화. 이래야 다음 자료 넣을때. autowired 된 객체를 깔끔히 쓸 수 있다.)
			// 여기서 OnlySubPageTagVo 타입의 변수(List)는 초기화 안시켜 주는 이유는. 어차피 새로운 객체가 만들어져서 투입될 것이라.
			// 따로 초기화 해줄 필요가 없다.

			// 확인용2(쌓인 tag들 보여주는 부분. 지워도 됨.)
//			for(OnlySubPageTagVo onlyVo : onlySubPageTagVos) {
//				
//				System.out.print("clear after tag -> ");
//				for(TagTableJpa tagVo : onlyVo.getTagTableJpas()) {
//					System.out.print(tagVo.getTag_name()+", ");
//					
//				}System.out.println();
//				
//			}

		}
		// 이제 onlySubPageTagVos(html로 내려보내기 위해서. 만들어준 커스텀 vo) 에 모든 태그들이 있다. 이걸 타임리프
		// index 문법으로 처리할 것.

		// 4. model
		model.addAttribute("RandomCookUids", RandomCookUids); // 이건 필요 없을 수도. 확인 후 삭제
		model.addAttribute("RandomCookUids_size", RandomCookUids.size());
		model.addAttribute("cookTableJpas_2", cookTableJpas_2);
		model.addAttribute("onlySubPageTagVos", onlySubPageTagVos);
		model.addAttribute("CookuidSize", RandomCookUids.size() ); // CookuidSize = 12 
		return "subpageAll_test"; //연습용
	}

	@Override
	@GetMapping("/mrd/recipe")
	public String callRecipePage(Model model, String cookUid) throws Exception {

		// 선언부
		// cook table
		CookTableJpa cookJpa = cookTableJpaRepository.findById(Integer.parseInt(cookUid)).get();
		// tag table
		tagTableJpas = tagTableJpaRepository.getAll(Integer.parseInt(cookUid));
		// recipe table
		recipeTableJpas = recipeTableRepository.getAll(Integer.parseInt(cookUid));
		// food_ingredient_table
		food_ingredientTableJpas = food_ingredientTableJpaRepository.getAll(Integer.parseInt(cookUid));
		// comment_table
		commentTableJpas = commentTableJpaRepository.findAll();

		// List<String> cook_introduction = new ArrayList<>();

		// 연산부분

		// 1. profile id null 체크
		if (cookJpa.getProfileId() == null) {
			cookJpa.setProfileId("기본 data_base 입니다.");

		}

		int commentTableJpasSize = commentTableJpas.size();

		// html로 내려보내는 부분
		model.addAttribute("cookJpa", cookJpa);
		model.addAttribute("tagTableJpas", tagTableJpas);
		// model.addAttribute("cook_introduction", cook_introduction);
		model.addAttribute("recipeTableJpas", recipeTableJpas);
		model.addAttribute("food_ingredientTableJpas", food_ingredientTableJpas);

		model.addAttribute("commentTableJpas", commentTableJpas);
		model.addAttribute("commentTableJpasSize", commentTableJpasSize);

		return "recipepage_test02";
	}

	@Override
	public String callWritePage() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String callMyPage() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/mrd/login")
	public String callLoginPage() throws Exception {
		// TODO Auto-generated method stub
		return "login";
	}

	@Override
	@GetMapping("/mrd/signup")
	public String callSignUpPage() throws Exception {
		// TODO Auto-generated method stub
		return "signuppage_test";
	}

	@Override // 테스트 검색창
	@GetMapping("/mrd/search/{findName}")
	public String viewSearch(@PathVariable(value = "findName") String cookName) throws Exception {
		// TODO Auto-generated method stub

		String test = cookName;
		System.out.println(test);
		cookJpas = cookTableJpaRepository.findCookSearch(cookName);
		for (CookTableJpa cookTableJpa : cookJpas) {
			System.out.print(cookTableJpa.getCook_uid() + " -> ");
			System.out.println(cookTableJpa.getCookTitle());

		}
		return "cooksearch";
	}

	
	// 검색창 페이지 
	@GetMapping("/mrd/ajax")
	public String ajax() throws Exception {



		return "search";
	}
	

	// 23.06.14 header 테스트용 -정기훈-
	@GetMapping("/mrd/header")
	public String headertest() throws Exception {

		return "fragment/header";
	}
	

	// 23.06.14 nav 테스트용 -정기훈-
		@GetMapping("/mrd/nav")
		public String navtest() throws Exception {

			return "fragment/nav";
		}
	
	// 23.06.14 footer 테스트용 -정기훈-
		@GetMapping("/mrd/footer")
		public String footertest() throws Exception {

			return "fragment/footer";
		}

	
	// 작성 페이지 
	@GetMapping("/mrd/recipeboard1")
	public String board1() throws Exception {

	

		return "testRecopeboard";
	}
	
	@GetMapping("/mrd/recipeboard2")
	public String board2() throws Exception {
    
   	return "recipeboard2(0614)";
	}

	// 23.06.14 마이페이지 -제현
	@GetMapping("/mrd/mypage")
	public String callMypage() throws Exception {

		return "mypage";
	}
	
	//23.06.15 빠진 week 추가. 관리자가 들어가서 주간 랭킹 바꿔는 부분
	@GetMapping("/mrd/week") // week rank 부분 업데이트 해주는 부분.
	public String weekRankingChange() {

		// 1. 점수 높은순으로 6개 가져오기.
		List<WeekRanKVo> weekRanKVos_rank6 = new ArrayList<>(6);

		weekRanKVos_rank6 = onlyAdminWeekDoService.weekRankingCall();

		// 검증부
//		int i =1;
//		for(WeekRanKVo weekRanKVo :weekRanKVos_rank6 ) {
//			
//			System.out.println(i+"번째 uid= "+weekRanKVo.getCookUid()+", 점수= "+weekRanKVo.getRankScore());
//			i += 1;
//			
//		}

		// 2. 가져온 객체의 uid들를 string type으로 변환하고. 이걸 쿼리문으로 update해서 rank table의 rank_week
		// 컬럼에 저장.
		onlyAdminWeekDoService.ChangeStringAndUpdateRankTable(weekRanKVos_rank6);

		// 3. 업데이트 끝. 이제 파싱은 메인화면.

		return "check"; // 그냥 화면 없으면 아쉬워서 띄움.
	}
	

	//딥러닝 음식 판별기 
	@GetMapping("/mrd/uploadmain")
	public String upload(Model model) throws Exception {
		System.out.println("업로드 메인 내");
		model.addAttribute("check","사진을 넣어주세요");
		return "uploadpage";
	}

	
	//23.06.26 레시피 페이지 각 step별 tts 하기위해서. step1 ~, step2~로 나누는 부분.
	@GetMapping("steptts")
	public String StepString() {
		System.out.println("tts c-in");
		//반환자
		List<String> returnTtsStrings = new ArrayList<>();
		
		//uid 지정
		int uid_check = 175; //실제론 미리 만들거나. 어떻게 요청할지 정해야함.
		//mp3 파일로 변환할 String 목록.
		returnTtsStrings=convertRecipeTTS.stepAddText(uid_check);
		System.out.println("returnTtsStrings size = "+returnTtsStrings.size());
		
		//실제변환과정
		googleTtsService.ListTts(uid_check, returnTtsStrings);
		
		
		
		return "check";
	}
	@GetMapping("/check")
	public String check() {
		
		return "check";
	}
}
