package com.cook.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cook.model.customVo.WeekRanKVo;
import com.cook.model.jpa.CookTableJpa;
import com.cook.model.jpa.RankTableJpa;
import com.cook.model.jpa.repository.CookTableJpaRepository;
import com.cook.model.jpa.repository.RankTableJpaRepository;
import com.cook.util.RandomCookUid;

@Service
public class OnlyAdminWeekDoService {

	@Autowired
	CookTableJpa cookTableJpa;

	@Autowired
	CookTableJpaRepository cookTableJpaRepository;

	@Autowired
	RankTableJpa rankTableJpa;
	@Autowired
	RankTableJpaRepository rankTableJpaRepository;
	
	//랜덤 가중치
	@Autowired
	RandomCookUid randomCookUid;

	public List<WeekRanKVo> weekRankingCall() {

		// 커스텀 vo 선언부
		List<WeekRanKVo> weekRanKVos = new ArrayList<>();

		// cook table 다 가져오는 부분
		List<CookTableJpa> cookTableJpas;
		cookTableJpas = cookTableJpaRepository.findAll();

		// 점수 변환
		for (CookTableJpa cookTableJpa : cookTableJpas) {

			// 깊은 복사를 위한 객체 선언과.
			WeekRanKVo weekRanKVo = new WeekRanKVo(); // 전달자

			// 셋업
			weekRanKVo.setCookUid(cookTableJpa.getCook_uid()); // 각각 uid

			weekRanKVo.setRankScore(cookTableJpa.getGood_count() * (randomCookUid.outPutCookUid(9)+1) - (cookTableJpa.getBadCount() * (randomCookUid.outPutCookUid(4)+1))); // 좋아요 * 10 +
																											// 싫어요 * -5
																											// 에서 랭킹 시스템

			// 자료 대입
			weekRanKVos.add(weekRanKVo); // List<WeekRanKVo> weekRanKVos

		}

		// 객체 비교용 Comparator<T> 생성. 이렇게 오버라이드 해주면 객체 정렬 정의된 것. 큰순서대로 정렬
		Comparator<WeekRanKVo> comparator = new Comparator<WeekRanKVo>() {

			@Override
			public int compare(WeekRanKVo o1, WeekRanKVo o2) {
				// TODO Auto-generated method stub
				return o2.getRankScore() - o1.getRankScore();
			}

		};

		// 실제 객체 비교 시작. sort 바로 완료.
		weekRanKVos.sort(comparator);

		// 검증부 지워도 됨.
//		int i=1;
//		for(WeekRanKVo weekRanKVo : weekRanKVos){
//			
//			System.out.println(i+"번째 uid= "+weekRanKVo.getCookUid()+", 점수= "+weekRanKVo.getRankScore());
//			
//			
//		}

		return weekRanKVos.subList(0, 6); // index 0~5 즉 6위까지 반환.
	}

	public void ChangeStringAndUpdateRankTable(List<WeekRanKVo> weekRanKVos_rank6) {
		// ㄱ. uid들 String 타입으로 변환.
		String weekRnak_6 = "";

		int size_count = 1;
		for (WeekRanKVo weekRanKVo : weekRanKVos_rank6) {

			weekRnak_6 += weekRanKVo.getCookUid() + "";

			if (size_count < weekRanKVos_rank6.size()) {

				weekRnak_6 += ", ";

			} else {
				break;
			}
			size_count += 1;

		}

		// 검증
//				System.out.println("size_count= "+size_count);
//				System.out.println(weekRnak_6);

		// ㄴ. jpa 로 업데이트(미리 mysql rank_table 자료 1개는 있어야함. 그 자료를 일주일마다 update 갱신할 것.)
		rankTableJpaRepository.UpdateWeekRank(1, weekRnak_6);

	}
	
	
	

}
