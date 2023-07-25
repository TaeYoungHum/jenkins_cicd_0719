package com.cook.model.customVo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cook.model.jpa.TagTableJpa;



@Component
public class OnlySubPageTagVo {
	@Autowired
	TagTableJpa tableJpa;
	
	private List<TagTableJpa> tagTableJpas = new ArrayList<>(); // 이 한뭉텅이 당. uid 1개의 테그들이 들어간다. 
	
	private int tagTableJpas_size;
	
	
	
	public List<TagTableJpa> getTagTableJpas() {
		return tagTableJpas;
	}

	public void setTagTableJpas(List<TagTableJpa> tableJpas) {
		
		this.tagTableJpas.clear();
        for (TagTableJpa tagVo : tableJpas) {
            TagTableJpa clonedTagVo = new TagTableJpa();
            clonedTagVo.setTag_name(tagVo.getTag_name());
            // 필요한 다른 속성들도 복사
            this.tagTableJpas.add(clonedTagVo);
        }
		
	}
	
	public void clearTagTableJpas() {
		
		this.tagTableJpas.clear();
		this.tagTableJpas_size = 0;
		
	}

	public int getTagTableJpas_size() {
		return tagTableJpas_size;
	}

	public void setTagTableJpas_size(List<TagTableJpa> tableJpas) {
		this.tagTableJpas_size = tableJpas.size();
	}
	
	
	
	

}
