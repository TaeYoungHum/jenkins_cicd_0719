package com.cook.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.cook.model.jpa.id.Category_lv3TableJpaId;
@Component
@Entity
@Table(name="category_lv3", schema = "mrd_db")
public class Category_lv3TableJpa implements Serializable{
	//	category_lv3_uid	int	NO	PRI		auto_increment
	//	category_lv3_name	varchar(300)	YES			
	//	category_lv2_category_lv2_uid	int	NO	PRI		
	//	category_lv2_category_lv1_category_lv1_uid	int	NO	PRI		

	
	
	@Column(name="category_lv3_name", length = 300)
	private String category_lv3_name;
	
	@EmbeddedId
	private Category_lv3TableJpaId id;
	

	public String getCategory_lv3_name() {
		return category_lv3_name;
	}

	public void setCategory_lv3_name(String category_lv3_name) {
		this.category_lv3_name = category_lv3_name;
	}

	
	
	

}
