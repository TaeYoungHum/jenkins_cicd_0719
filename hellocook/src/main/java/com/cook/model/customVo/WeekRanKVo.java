package com.cook.model.customVo;

import org.springframework.stereotype.Component;

@Component
public class WeekRanKVo {
	
	private int cookUid;
	
	private int rankScore=0; //좋아요 * 10 + 싫어요 * -5 에서 랭킹 시스템

	public int getCookUid() {
		return cookUid;
	}

	public void setCookUid(int cookUid) {
		this.cookUid = cookUid;
	}

	public int getRankScore() {
		return rankScore;
	}

	public void setRankScore(int rankScore) {
		this.rankScore = rankScore;
	}
	
	
	
	

	
}
