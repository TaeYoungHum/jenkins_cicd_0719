//23.06.16 save 함수 사용을 위한 구조변경 위해서.  id class 사용하지 않음.

/*
package com.cook.model.jpa.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class TagTableJpaId implements Serializable{

	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="tag_uid")
	private int tag_uid;
	
	
	@Column(name="cook_cook_uid", nullable = false)
	private int cook_cook_uid;


	public int getTag_uid() {
		return tag_uid;
	}


	public void setTag_uid(int tag_uid) {
		this.tag_uid = tag_uid;
	}


	public int getCook_cook_uid() {
		return cook_cook_uid;
	}


	public void setCook_cook_uid(int cook_cook_uid) {
		this.cook_cook_uid = cook_cook_uid;
	}
	
	
	
}

*/
