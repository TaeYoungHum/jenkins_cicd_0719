package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.Category_lv3TableJpa;
@Repository
public interface Category_lv3TableJpaRepository extends JpaRepository<Category_lv3TableJpa, Integer>{
	
	@Query(value = "SELECT * FROM mrd_db.category_lv3 WHERE category_lv2_category_lv1_category_lv1_uid = :categoryLv2CategoryLv1CategoryLv1Uid "
			+ "And category_lv2_category_lv2_uid =:categoryLv2CategoryLv2Uid", nativeQuery = true)
	public List<Category_lv3TableJpa> getAll(int categoryLv2CategoryLv1CategoryLv1Uid, int categoryLv2CategoryLv2Uid);

}
