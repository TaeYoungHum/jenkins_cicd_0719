package com.cook.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="category_lv1", schema = "mrd_db")
public class Category_lv1TableJpa {
	
//	category_lv1_uid	int	NO	PRI		auto_increment
//	category_lv1_name	varchar(300)	YES			
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="category_lv1_uid")
	private int category_lv1_uid;
	
	@Column(name="category_lv1_name", length = 300)
	private String category_lv1_name;

	public int getCategory_lv1_uid() {
		return category_lv1_uid;
	}

	public void setCategory_lv1_uid(int category_lv1_uid) {
		this.category_lv1_uid = category_lv1_uid;
	}

	public String getCategory_lv1_name() {
		return category_lv1_name;
	}

	public void setCategory_lv1_name(String category_lv1_name) {
		this.category_lv1_name = category_lv1_name;
	}
	
	
	

}
