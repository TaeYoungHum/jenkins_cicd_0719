package com.cook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cook.model.customVo.AjaxFindVo;
import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.repository.CookTableJpaRepository;
import com.cook.model.jpa.repository.TagTableJpaRepository;

@Service
public class RestService {

	@Autowired
	CookTableJpaRepository cookTableJpaRepository;

	@Autowired
	TagTableJpaRepository tagTableJpaRepository;

//	public RestService(CookTableJpaRepository cookTableJpaRepository, TagTableJpaRepository tagTableJpaRepository) {
//		this.cookTableJpaRepository = cookTableJpaRepository;
//		this.tagTableJpaRepository = tagTableJpaRepository;
//	}

	// tag search
	public List<AjaxFindVo> findTag(@PathVariable(value = "cookName") String cookName) throws Exception {

		List<AjaxFindVo> ajaxFindVos = new ArrayList<>();
		List<Integer> findCookUids = new ArrayList<>();
		
// contains #이 있는 검사하는것 있으면 TRUE 없으면 FALSE
		if(cookName.contains("#")) {
			
			cookName.replace("/#/g", "");
		}
		System.out.println("@@@@@@@@@@ " + cookName);
		List<CookTableJpa> cookJpas = cookTableJpaRepository.findCookSearch(cookName);

		cookJpas = cookTableJpaRepository.findTagSearch(cookName);
		System.out.println("restin!!!!!!!!!!!!!!!!!!!!!");
		for (CookTableJpa cookTableJpa : cookJpas) {
			findCookUids.add(cookTableJpa.getCook_uid());
		}

		for (int Cookuid : findCookUids) {
			AjaxFindVo ajaxFindVo = new AjaxFindVo();
			ajaxFindVo.setCookTableJpa(cookTableJpaRepository.findById(Cookuid).orElse(null));
			ajaxFindVo.setTagTableJpas(tagTableJpaRepository.getAll(Cookuid));

			// 깊은 복사
			AjaxFindVo ajaxFindVoCopy = new AjaxFindVo();
			ajaxFindVoCopy.setCookTableJpa(ajaxFindVo.getCookTableJpa());
			ajaxFindVoCopy.setTagTableJpas(ajaxFindVo.getTagTableJpas());

			ajaxFindVos.add(ajaxFindVoCopy);

		}

		return ajaxFindVos;
	}

	
	//cook search
	public List<AjaxFindVo> findCook(@PathVariable(value = "cookName") String cookName) throws Exception {

		List<AjaxFindVo> ajaxFindVos = new ArrayList<>();
		List<Integer> findCookUids = new ArrayList<>();
		List<CookTableJpa> cookJpas = cookTableJpaRepository.findCookSearch(cookName);
		cookJpas = cookTableJpaRepository.findCookSearch(cookName);

		for (CookTableJpa cookTableJpa : cookJpas) {
			findCookUids.add(cookTableJpa.getCook_uid());
		}

		for (int Cookuid : findCookUids) {
			AjaxFindVo ajaxFindVo = new AjaxFindVo();
			ajaxFindVo.setCookTableJpa(cookTableJpaRepository.findById(Cookuid).orElse(null));
			ajaxFindVo.setTagTableJpas(tagTableJpaRepository.getAll(Cookuid));
			

			// 깊은 복사
			AjaxFindVo ajaxFindVoCopy = new AjaxFindVo();
			ajaxFindVoCopy.setCookTableJpa(ajaxFindVo.getCookTableJpa());
			ajaxFindVoCopy.setTagTableJpas(ajaxFindVo.getTagTableJpas());

			ajaxFindVos.add(ajaxFindVoCopy);

		}

		return ajaxFindVos;
	}

}
