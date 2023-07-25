package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.Category_lv2TableJpa;
import com.cook.model.jpa.Food_ingredientTableJpa;


@Repository
public interface Category_lv2TableJpaRepository extends JpaRepository<Category_lv2TableJpa, Integer>{
	
	
	@Query(value = "SELECT * FROM mrd_db.category_lv2 WHERE category_lv1_category_lv1_uid = :categoryLv1CategoryLv1UId", nativeQuery = true)
	 public List<Category_lv2TableJpa> getAll(int categoryLv1CategoryLv1UId);

}
