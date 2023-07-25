package com.cook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.repository.CookTableJpaRepository;

@Service
public class Jpa_findService {
	
	@Autowired
	CookTableJpaRepository cookJpaRepository;
	//
	List<CookTableJpa> cookJpas;
	
	
//	public List<CookJpa> find(int cook_uid){
//		
//		
//		System.out.println("서비스 들어옴");
//		cookJpas=cookJpaRepository.findfindByCook_uid(cook_uid);
//		
//		
//		return cookJpas;
//		
//	}
	

}
