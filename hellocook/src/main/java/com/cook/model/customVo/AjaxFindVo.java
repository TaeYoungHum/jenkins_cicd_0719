package com.cook.model.customVo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.TagTableJpa;

@Component
public class AjaxFindVo {
	 
	
	private CookTableJpa cookTableJpa;
	private List<TagTableJpa> tagTableJpas;
	
	public CookTableJpa getCookTableJpa() {
		return cookTableJpa;
	}
	public void setCookTableJpa(CookTableJpa cookTableJpa) {
		this.cookTableJpa = cookTableJpa;
	}
	public List<TagTableJpa> getTagTableJpas() {
		return tagTableJpas;
	}
	public void setTagTableJpas(List<TagTableJpa> tagTableJpas) {
		this.tagTableJpas = tagTableJpas;
	}
	
	
	
	
}
