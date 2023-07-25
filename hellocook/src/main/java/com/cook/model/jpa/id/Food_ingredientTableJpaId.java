package com.cook.model.jpa.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class Food_ingredientTableJpaId implements Serializable{
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="ingredient_uid")
	private int ingredient_uid;
	
	
	@Column(name="cook_cook_uid", nullable = false)
	private int cook_cook_uid;


	public int getIngredient_uid() {
		return ingredient_uid;
	}


	public void setIngredient_uid(int ingredient_uid) {
		this.ingredient_uid = ingredient_uid;
	}


	public int getCook_cook_uid() {
		return cook_cook_uid;
	}


	public void setCook_cook_uid(int cook_cook_uid) {
		this.cook_cook_uid = cook_cook_uid;
	}

	
}
