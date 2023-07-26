package com.cook.model.xml;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface Point_recipeDao {
	
	public List<RecipeVo> getRecipeById(int cook_cook_uid);
	

}
