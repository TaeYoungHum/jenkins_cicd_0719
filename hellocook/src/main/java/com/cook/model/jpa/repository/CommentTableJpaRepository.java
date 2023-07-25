package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.CommentTableJpa;

@Repository
public interface CommentTableJpaRepository extends JpaRepository<CommentTableJpa, Integer>{
	
	@Query(value = "SELECT * FROM mrd_db.comment WHERE cook_cook_uid = :cookcookuid", nativeQuery = true)
	 public List<CommentTableJpa> getAll(int cookcookuid);

}
