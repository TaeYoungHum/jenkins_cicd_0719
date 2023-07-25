package com.cook.model.jpa.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class Category_lv3TableJpaId implements Serializable{
	
	@Column(name="category_lv2_category_lv1_category_lv1_uid", nullable = false)
	private int categoryLv2CategoryLv1CategoryLv1Uid;
	
	@Column(name="category_lv2_category_lv2_uid", nullable = false)
	private int categoryLv2CategoryLv2Uid;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="category_lv3_uid")
	private int categoryLv3Uid;

	public int getCategoryLv2CategoryLv1CategoryLv1Uid() {
		return categoryLv2CategoryLv1CategoryLv1Uid;
	}

	public void setCategoryLv2CategoryLv1CategoryLv1Uid(int categoryLv2CategoryLv1CategoryLv1Uid) {
		this.categoryLv2CategoryLv1CategoryLv1Uid = categoryLv2CategoryLv1CategoryLv1Uid;
	}

	public int getCategoryLv2CategoryLv2Uid() {
		return categoryLv2CategoryLv2Uid;
	}

	public void setCategoryLv2CategoryLv2Uid(int categoryLv2CategoryLv2Uid) {
		this.categoryLv2CategoryLv2Uid = categoryLv2CategoryLv2Uid;
	}

	public int getCategoryLv3Uid() {
		return categoryLv3Uid;
	}

	public void setCategoryLv3Uid(int categoryLv3Uid) {
		this.categoryLv3Uid = categoryLv3Uid;
	}

	
	
	
	
}
