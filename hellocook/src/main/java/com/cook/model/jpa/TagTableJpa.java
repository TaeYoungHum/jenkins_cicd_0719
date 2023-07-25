package com.cook.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "tag", schema = "mrd_db") // 테이블 이름 알려주기
public class TagTableJpa implements Serializable, Persistable<Integer> {

//	tag_uid	int	NO	PRI		auto_increment
//	tag_name	varchar(45)	YES			
//	cook_cook_uid	int	NO	MUL		

	// 23.06.16 -> save 함수 사용위해. 구조변경.(id class 잠금)
//	@EmbeddedId
//	private TagTableJpaId id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name = "tag_uid")
	private Integer tag_uid;

	@Column(name = "cook_cook_uid", nullable = false)
	private int cook_cook_uid;

	@Column(name = "tag_name", length = 45)
	private String tag_name;

	public String getTag_name() {
		return tag_name != null ? tag_name : "";
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public Integer getTag_uid() {
		return tag_uid != null ? tag_uid : 0;
	}

	public void setTag_uid(Integer tag_uid) {
		this.tag_uid = tag_uid;
	}

	public int getCook_cook_uid() {
		return cook_cook_uid;
	}

	public void setCook_cook_uid(int cook_cook_uid) {
		this.cook_cook_uid = cook_cook_uid;
	}

	// 06.16
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return getCook_cook_uid();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return getTag_uid() == 0;
	}

}
