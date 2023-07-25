package com.cook.service.insertServie;

import org.springframework.stereotype.Component;

import com.cook.model.customVo.OnlyRecipeboardPage;
import com.cook.model.jpa.CookTableJpa;


@Component
public class OnlyInsertCookTable {
	
	
	//23.06.21 컨트롤러 파라메터 변경으로 인한 함수 변경.
	//, Map<String, Object> requestData 
	public CookTableJpa CookTableParsingAndReturn(OnlyRecipeboardPage reciveHtml) {
		
		CookTableJpa cookTableJpa_trans = new CookTableJpa(); //깊은 복사
		cookTableJpa_trans.setCookDate(); // 글 생성 시간 셋업.
		
		
		//전달받은 데이타 파싱 23.06.21 변경
		String CookTitle = (String)(reciveHtml.getCookTitle()); //ObJect라서 타입 변환 강제로 명시.
		
		String number_of_people = (String)(reciveHtml.getNumber_of_people());
		String cook_time = (String)(reciveHtml.getCook_time());
		String preparation_time =(String)(reciveHtml.getPreparation_time());
		

		//데이터 전달용 객체에 위에 값들로 초기화. 아래는 구조.
//		cook_uid	int	NO	PRI		auto_increment
//		cook_title	varchar(300)	NO			
//		preparation_time	varchar(300)	NO			
//		cook_time	varchar(300)	NO			
//		number_of_people	varchar(300)	NO			
//		representative_cook_img	varchar(500)	NO			
//		profile_id	varchar(300)	YES	MUL		
//		cook_date	datetime	NO		CURRENT_TIMESTAMP	DEFAULT_GENERATED
//		view_count	int	YES		0	
//		good_count	int	YES		0	
//		bad_count	int	YES		0
		
		//전달자 셋팅
		cookTableJpa_trans.setCookTitle(CookTitle); //음식이름
		cookTableJpa_trans.setPreparationTime(preparation_time); //준비시간
		cookTableJpa_trans.setCookTime(cook_time); //조리 시간
		cookTableJpa_trans.setNumberOfPeople(number_of_people);
		cookTableJpa_trans.setRepresentativeCookImg("이거 파일 전달 받고. 컨트롤러에서 따로 받아야함.");
		//cookTableJpa_trans.setProfileId("이것도 있어야..");
		
		
		
		
		
		return cookTableJpa_trans;
		
		
		
		
		
	}

}
