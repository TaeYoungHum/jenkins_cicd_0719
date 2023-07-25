package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.ProfileTableJpa;

@Repository
public interface ProfileTableJpaRepository extends JpaRepository<ProfileTableJpa, String>{
	
	@Query(value = "SELECT * FROM mrd_db.profile WHERE id = :id", nativeQuery = true)
	 public List<ProfileTableJpa> getAll(String id);

}
