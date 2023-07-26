package com.cook.model.jpa.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class Category_lv2TableJpaId implements Serializable{
	
	
	@Column(name="category_lv1_category_lv1_uid", nullable = false)
	private int categoryLv1CategoryLv1UId;
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="category_lv2_uid")
	private int category_lv2_uid;


	


	public int getCategoryLv1CategoryLv1UId() {
		return categoryLv1CategoryLv1UId;
	}


	public void setCategoryLv1CategoryLv1UId(int categoryLv1CategoryLv1UId) {
		this.categoryLv1CategoryLv1UId = categoryLv1CategoryLv1UId;
	}


	public int getCategory_lv2_uid() {
		return category_lv2_uid;
	}


	public void setCategory_lv2_uid(int category_lv2_uid) {
		this.category_lv2_uid = category_lv2_uid;
	}
	
	
	
	

}
