package com.cook.service.insertServie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cook.model.customVo.OnlyRecipeboardPage;
import com.cook.model.jpa.Food_ingredientTableJpa;
import com.cook.model.jpa.id.Food_ingredientTableJpaId;

@Component
public class OnlyInsertFood_ingredientTableParsing {

	// 23.06.21 변경.
	public Food_ingredientTableJpa Food_ingredientTableParsingAndReturn(OnlyRecipeboardPage reciveHtml,
			int newCookTable) {

		// List<Food_ingredientTableJpa> returnFood_ingredientTableAfterParding = new
		// ArrayList<>();
		Food_ingredientTableJpa food_ingredientTableJpa = new Food_ingredientTableJpa(); // 반환자 생성

		// 1. 파싱.

		// 재료, 갯수 따로 받았음.
		List<String> trans_Strings_food = reciveHtml.getProducts();
		List<String> trans_Strings_count = reciveHtml.getGrams();

		List<String> trans_Strings_foodCount = new ArrayList<>(); // full

		// 2. 연산
		int ingredient_index = 0;
		String Onlytrans = "";
		if (trans_Strings_food.size() == trans_Strings_count.size()) {

			ingredient_index = 0;

			for (String s : trans_Strings_food) {

				Onlytrans = "";
				Onlytrans = trans_Strings_food.get(ingredient_index).replace(" ", "").replace(",", "").replace(".", "")
						+ " "
						+ trans_Strings_count.get(ingredient_index).replace(" ", "").replace(",", "").replace(".", "");

				if (ingredient_index < (trans_Strings_food.size() - 1)) {

					Onlytrans += ",";
				}

				trans_Strings_foodCount.add(Onlytrans);

				ingredient_index++;

			}

		} else if (trans_Strings_food.size() > trans_Strings_count.size()) {

			ingredient_index = 0;

			for (String s : trans_Strings_food) {

				if (ingredient_index < trans_Strings_count.size()) {

					s = s + " " + trans_Strings_count.get(ingredient_index).replace(" ", "").replace(",", "")
							.replace(".", "");

				}
				if (ingredient_index < (trans_Strings_food.size() - 1)) {

					s += ",";
				}

				ingredient_index++;
				trans_Strings_foodCount.add(s);

			}

		} else {
			ingredient_index = 0;

			for (String s : trans_Strings_food) {

				Onlytrans = "";
				Onlytrans = trans_Strings_food.get(ingredient_index).replace(" ", "").replace(",", "").replace(".", "")
						+ " "
						+ trans_Strings_count.get(ingredient_index).replace(" ", "").replace(",", "").replace(".", "");

				if (ingredient_index < (trans_Strings_food.size() - 1)) {

					Onlytrans += ",";
				}

				trans_Strings_foodCount.add(Onlytrans);

				ingredient_index++;

			}

		}

		// 3. trans_Strings_foodCount 에 재료+갯수 형식으로 다 저장됨. 이걸 객체화
		Food_ingredientTableJpaId id = new Food_ingredientTableJpaId();
		id.setCook_cook_uid(newCookTable);

		food_ingredientTableJpa.setIdKey(id);
		food_ingredientTableJpa.setIngredient(Onlytrans);

		return food_ingredientTableJpa;
	}

}
