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
@Entity // jpa 임을 알린다.
@Table(name = "recipe", schema = "mrd_db") // 테이블 이름 알려주기
public class RecipeTableJpa implements Serializable, Persistable<Integer> { // Serializable

//	recipe_text_uid	int	NO	PRI		auto_increment
//	recipe_text	varchar(2000)	YES			
//	cook_cook_uid	int	NO	PRI		
//	recipe_img_link	varchar(300)	YES			
//	cook_introduction	varchar(5000)	YES			

	// 06.16 -> 성공.
	@Column(name = "cook_cook_uid", nullable = false)
	private int cookcookuid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_text_uid", nullable = false)
	private Integer recipeTextUid;

//	@EmbeddedId
//    private RecipeTableJpaId idKey;

//	public RecipeTableJpa()
//	{
//		
//		this.idKey = new RecipeTableJpaId(); // 내장 키 객체 초기화
//	}

	// 23.06.15 insert 위해서 getter setter 생성
//	public RecipeTableJpaId getIdKey() {	
//		return idKey;
//	}
//
//	public void setId(RecipeTableJpaId id) {
//		this.idKey = id;
//	}

//	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
//	@Column(name="recipe_text_uid")
//	private int recipe_text_uid;

	@Column(name = "recipe_text", length = 2000)
	private String recipe_text;

//	@EmbeddedId
//	@Column(name="cook_cook_uid", nullable = false)
//	private int cookcookuid;

	@Column(name = "recipe_img_link", length = 300)
	private String recipe_img_link;

	@Column(name = "cook_introduction", length = 5000)
	private String cook_introduction;

//	public int getRecipe_text_uid() {
//		return recipe_text_uid;
//	}
//
//	public void setRecipe_text_uid(int recipe_text_uid) {
//		this.recipe_text_uid = recipe_text_uid;
//	}

	public String getRecipe_text() {
		return recipe_text != null ? recipe_text : ""; // null인 경우에 빈 문자열 반환
	}

	public void setRecipe_text(String recipe_text) {
		this.recipe_text = recipe_text;
	}

//
	public int getCook_cook_uid() {
		return cookcookuid;
	}

	public void setCook_cook_uid(int cook_cook_uid) {
		this.cookcookuid = cook_cook_uid;
	}

	public String getRecipe_img_link() {
		return recipe_img_link;
	}

	public void setRecipe_img_link(String recipe_img_link) {
		this.recipe_img_link = recipe_img_link;
	}

	public String getCook_introduction() {
		return cook_introduction;
	}

	public void setCook_introduction(String cook_introduction) {
		this.cook_introduction = cook_introduction;
	}

//	public int getCookcookuid() {
//		return cookcookuid;
//	}
//
//	public void setCookcookuid(int cookcookuid) {
//		this.cookcookuid = cookcookuid;
//	}
//
//	public int getRecipeTextUid() {
//		return recipeTextUid;
//	}
//
//	public void setRecipeTextUid(int recipeTextUid) {
//		this.recipeTextUid = recipeTextUid;
//	}

	// 06.16
//	@Override
//	public Integer getId() {
//		// TODO Auto-generated method stub
//		return idKey.getRecipeTextUid();
//	}
//	@Override
//	public boolean isNew() {
//		// TODO Auto-generated method stub
//
//		return idKey == null || idKey.getRecipeTextUid() == 0;
//	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return getCookcookuid();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub

		return getRecipeTextUid() == 0;
	}

	public int getCookcookuid() {
		return cookcookuid;
	}

	public void setCookcookuid(int cookcookuid) {
		this.cookcookuid = cookcookuid;
	}

	public Integer getRecipeTextUid() {
		return recipeTextUid != null ? recipeTextUid : 0;
	}

	public void setRecipeTextUid(Integer recipeTextUid) {
		this.recipeTextUid = recipeTextUid;
	}

}
