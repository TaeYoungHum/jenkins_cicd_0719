package com.cook.model.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cook.model.jpa.RankTableJpa;

@Transactional
public interface RankTableJpaRepository extends JpaRepository<RankTableJpa, Integer> {
	
	// 23.06.09 weekRanking system
	@Modifying(clearAutomatically = true)
	@Query(value = "update mrd_db.rank\r\n" + "set rank_week = :rank_week\r\n" + "where no = :no", nativeQuery = true)
	public void UpdateWeekRank(int no, String rank_week);
}
