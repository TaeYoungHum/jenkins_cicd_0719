package com.cook.model.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cook.model.jpa.Category_lv1TableJpa;
@Repository
public interface Category_lv1TableJpaRepository extends JpaRepository<Category_lv1TableJpa, Integer>{

}
