package com.cook.util.googleApi;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.RecipeTableJpa;
import com.cook.model.jpa.repository.CookTableJpaRepository;
import com.cook.model.jpa.repository.RecipeTableRepository;

@Component
public class ConvertRecipeTTS {

	@Autowired
	GoogleTtsService googleTtsService;
	@Autowired
	FfmpegService ffmpegService;
	@Autowired
	CookTableJpa cookTableJpa;
	@Autowired
	CookTableJpaRepository cookTableJpaRepository;
	@Autowired
	RecipeTableJpa recipeTableJpa;
	@Autowired
	RecipeTableRepository recipeTableRepository;

	public List<String> stepAddText(int cookUid) {
		// 선언부
		final int cookUid_final = cookUid; // final 변수로 받아서 이걸 사용.
		List<RecipeTableJpa> recipeTableJpas = new ArrayList<>(); // coouid에 해당하는 1:n 레시피 테이블 call
		int stepCounter = 0;
		int StepCounter = 0;
		int totalStepCounter = 0;
		

		// 스탭으로 나눈 txt를 저장할 컨테이너 리스트 생성.
		List<String> stepTextContainers = new ArrayList<>();

		// 저기에 저장할 string 객체들을 만들기 전에. 각 uid당 step 이 몇개인지 파악. //cookUid_final 을 기준으로.
		cookTableJpa = cookTableJpaRepository.findById(cookUid_final).orElse(null); // uid에 맞는 cook table 가져옴.
		recipeTableJpas = recipeTableRepository.getAll(cookUid_final); // cookUid_final을 기반으로. 모든 recipe call

		// 가져온 걸 step체크
		for (RecipeTableJpa recipeTableJpa : recipeTableJpas) {

			if (recipeTableJpa.getRecipe_text() != null && recipeTableJpa.getRecipe_text() != "") {

				if (recipeTableJpa.getRecipe_text().contains("step")) {

					stepCounter++;

				} else if (recipeTableJpa.getRecipe_text().contains("Step")) {

					StepCounter++;

				}

			}

		}

		// 다 더함. totalStepCounter
		totalStepCounter = stepCounter + StepCounter;

		// 스텝별로 쪼갬.

		String whatStepString = ""; // 현재 스탭 파악위해서.
		String lastStepString = new String();

		for (int index = 0; index < recipeTableJpas.size(); index++) {
			
			if (recipeTableJpas.get(index).getRecipe_text() != null && !recipeTableJpas.get(index).getRecipe_text().isEmpty()) {

				if (recipeTableJpas.get(index).getRecipe_text().contains("step") || recipeTableJpas.get(index).getRecipe_text().contains("Step")) 
				{ // step을 만나면
					if (recipeTableJpas.get(index).getRecipe_text().contains("step")) {
						whatStepString = recipeTableJpas.get(index).getRecipe_text().replace("step", ""); // step을 지우고.
																											// "
																											// 숫자"만 남김.

					} else if (recipeTableJpas.get(index).getRecipe_text().contains("Step")) {

						whatStepString = recipeTableJpas.get(index).getRecipe_text().replace("Step", "");
					}
					

					whatStepString = whatStepString.trim(); // 혹시 모를 공백 제거. 이제 그럼 진짜 숫자만 남음.
					
					if (Integer.parseInt(whatStepString) < totalStepCounter) { // 현재 스탭이. 최종 스탭보다 작다면
						StringBuilder stringBuilder = new StringBuilder();// String + 대신하기위해서 사용.
						
						for (int checker = 0; index < recipeTableJpas.size(); index++) {
							
							
							if (recipeTableJpas.get(index+1).getRecipe_text().contains("step")
									|| recipeTableJpas.get(index+1).getRecipe_text().contains("Step") && checker!=0) {
								
								stepTextContainers.add(stringBuilder.toString());
								break;
							}
							stringBuilder.append(recipeTableJpas.get(index).getRecipe_text()+" ");
							checker = checker+1;

						}

					}else { //현재 스탭이. 최종 스탭이라면
						
						StringBuilder stringBuilder = new StringBuilder();// String + 대신하기위해서 사용.
						for(;index < recipeTableJpas.size(); index++) {
							
							
							stringBuilder.append(recipeTableJpas.get(index).getRecipe_text()+" ");
							lastStepString=stringBuilder.toString(); //전달자에 전달.
						}
						
						stepTextContainers.add(lastStepString);
						
						break; // 맨위 for문 나감.
						
						
					}

				} 

			}

		}
		//////////////////////////////////step1~, step2~ 식으로 stepTextContainers에 저장.
		
		//확인(없에도 됨.)
		int check_stepTextContainers = 0;
		for(String s : stepTextContainers) {
			
			System.out.println(check_stepTextContainers+"번째, 텍스트 -> "+s);
			check_stepTextContainers++;
		}
		
		
		return stepTextContainers;

	}
}
