package com.cook.service.insertServie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cook.model.customVo.OnlyRecipeboardPage;
import com.cook.model.jpa.TagTableJpa;

@Component
public class OnlyInsertTagTable {

	// 23.06.21 변경
	public List<TagTableJpa> TagsParsing(OnlyRecipeboardPage reciveHtml, int newCookTable) {

		// 리스트 선언.
		List<TagTableJpa> returnTagsAfterPasing = new ArrayList<>(); //반환자.
		

		// null 체크
		String tagsString = reciveHtml.getTags(); // 태그 받아오고.

		// 분절.
		String[] tagArray = tagsString.split("[,\\s]+");

		// 배열을 리스트로 변환. 밑 추가 파싱.
		for (String tag : tagArray) {

			tag = tag.replaceAll("#", "");
			tag = "#" + tag; // #일괄적으로 지우고. # 다시 붙여줌.

			TagTableJpa trans_Tag = new TagTableJpa(); // 전달자 생성.
			
			trans_Tag.setCook_cook_uid(newCookTable);
			trans_Tag.setTag_name(tag);

			returnTagsAfterPasing.add(trans_Tag);

		}

		return returnTagsAfterPasing;

	}

}
