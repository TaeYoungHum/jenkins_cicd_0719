//23.06.16 save 함수 사용을 위한 구조변경 위해서.  id class 사용하지 않음.
/*
package com.cook.model.jpa.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class RecipeTableJpaId implements Serializable{
	
	@Column(name = "cook_cook_uid", nullable = false)
    private int cookcookuid;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_text_uid", nullable = false)
    private int recipeTextUid;

	public int getCookcookuid() {
		return cookcookuid;
	}

	public void setCookcookuid(int cookcookuid) {
		this.cookcookuid = cookcookuid;
	}

	public int getRecipeTextUid() {
		return recipeTextUid;
	}

	public void setRecipeTextUid(int recipeTextUid) {
		this.recipeTextUid = recipeTextUid;
	}

    
}
*/