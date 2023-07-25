package com.cook.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.cook.model.jpa.id.Category_lv2TableJpaId;
@Component
@Entity
@Table(name="category_lv2")
public class Category_lv2TableJpa implements Serializable{
	
//	category_lv2_uid	int	NO	PRI		auto_increment
//	category_lv2_name	varchar(300)	YES			
//	category_lv1_category_lv1_uid	int	NO	PRI		
	
	@EmbeddedId
	private Category_lv2TableJpaId id;
	
	
	@Column(name="category_lv2_name",length = 300)
	private String category_lv2_name;
	

	

	public String getCategory_lv2_name() {
		return category_lv2_name;
	}

	public void setCategory_lv2_name(String category_lv2_name) {
		this.category_lv2_name = category_lv2_name;
	}

	
	

}
