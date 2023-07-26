package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.TagTableJpa;

@Repository
public interface TagTableJpaRepository extends JpaRepository<TagTableJpa, Integer>{

	@Query(value = "SELECT * FROM mrd_db.tag WHERE cook_cook_uid = :cookcookuid", nativeQuery = true)
	 public List<TagTableJpa> getAll(int cookcookuid);
}
