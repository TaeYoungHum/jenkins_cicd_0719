package com.cook.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity // jpa 임을 알린다.
@Table(name = "`rank`", schema = "mrd_db") // rank는 rank는 MySQL에서 예약어로 사용되는 키워드이므로, 테이블 이름으로 직접 사용할 수 없어서 저리 표시해서 키워드가 아니라고 표시한 것.
public class RankTableJpa {
	/*
	CREATE TABLE mrd_db.rank_table 
	(
		no        INT NOT NULL AUTO_INCREMENT,
		rank_week varchar(50),
		PRIMARY KEY(no)
	)
	ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;
	*/
	
	@Id // 프라이머리 키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name = "no") // mysql 컬럼과 매칭
	private int no;
	
	
	@Column(name = "rank_week", length = 50) //매핑, null, varchar 크기. 기본 255
	private String rank_week;


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getRank_week() {
		return rank_week;
	}


	public void setRank_week(String rank_week) {
		this.rank_week = rank_week;
	}
	
	
	

}
