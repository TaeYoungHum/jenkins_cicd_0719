package com.cook.model.jpa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.Food_ingredientTableJpa;

@Repository
public interface Food_ingredientTableJpaRepository extends JpaRepository<Food_ingredientTableJpa, Integer> {
	
	@Query(value = "SELECT * FROM mrd_db.food_ingredient WHERE cook_cook_uid = :cookcookuid", nativeQuery = true)
	 public List<Food_ingredientTableJpa> getAll(int cookcookuid);
	
}
