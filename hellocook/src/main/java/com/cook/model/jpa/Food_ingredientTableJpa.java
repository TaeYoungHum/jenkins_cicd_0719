
package com.cook.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Component;

import com.cook.model.jpa.id.Food_ingredientTableJpaId;

@Component
@Entity // jpa 임을 알린다.
@Table(name = "food_ingredient", schema = "mrd_db") // 테이블 이름 알려주기
public class Food_ingredientTableJpa implements Serializable, Persistable<Integer> {

//	ingredient_uid	int	NO	PRI		auto_increment
//	ingredient	varchar(2000)	YES			
//	ingredient_unit	varchar(2000)	YES			
//	cook_cook_uid	int	NO	PRI		

	@EmbeddedId
	private Food_ingredientTableJpaId idKey;

	public Food_ingredientTableJpaId getIdKey() {
		return idKey;
	}

	public void setIdKey(Food_ingredientTableJpaId idKey) {
		this.idKey = idKey;
	}

	@Column(name = "ingredient", length = 2000)
	private String ingredient;

	@Column(name = "ingredient_unit", length = 2000)
	private String ingredient_unit;

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getIngredient_unit() {
		return ingredient_unit;
	}

	public void setIngredient_unit(String ingredient_unit) {
		this.ingredient_unit = ingredient_unit;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return getIdKey().getCook_cook_uid();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return getIdKey().getIngredient_uid() == 0;
	}

}
