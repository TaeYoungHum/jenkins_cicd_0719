package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cook.model.jpa.RecipeTableJpa;
@Transactional
@Repository
public interface RecipeTableRepository extends JpaRepository<RecipeTableJpa, Integer> {
	
	//public RecipeTableJpa findByCookcookuid(int cookcookuid);
	
	//@Query(value = "SELECT * FROM mrd_db.recipe WHERE cook_cook_uid = :cookcookuid", nativeQuery = true)
	//public List<RecipeTableJpa> findAllByCookcookuid(int cookcookuid);
	
//	public List<RecipeTableJpa> findAllByCookcookuid(int cookcookuid);
	
	 @Query(value = "SELECT * FROM mrd_db.recipe WHERE cook_cook_uid = :cookcookuid", nativeQuery = true)
	 public List<RecipeTableJpa> getAll(int cookcookuid);
	 
	//save 함수가 잘 안먹혀서(1:n 관계서 update insert 지 맘대로 함.)
	@Modifying(clearAutomatically = true)
	@Query(value = "insert into mrd_db.recipe (cook_introduction, recipe_img_link, recipe_text, cook_cook_uid) "
			+ "values(:#{#recipeTableJpa.cook_introduction}, :#{#recipeTableJpa.recipe_img_link},"
			+ ":#{#recipeTableJpa.recipe_text},:#{#recipeTableJpa.id.cookcookuid} )", nativeQuery = true)
	public void insertRecipeTable(RecipeTableJpa recipeTableJpa);

}
