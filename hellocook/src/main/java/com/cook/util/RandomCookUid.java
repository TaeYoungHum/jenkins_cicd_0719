package com.cook.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

//랜덤하게 uid 뽑기위해서 유틸 클래스 생성함.
@Component
public class RandomCookUid {

	public int outPutCookUid(int maxIndexNum) {

		int out_cookUid = 0;
		Random random = new Random();

		out_cookUid = random.nextInt(maxIndexNum) + 1; // 0~376 +1

		return out_cookUid;
	}

	public List<Integer> outPutCookUid_list6(int maxIndexNum) {

		List<Integer> cookUids = new ArrayList<>();
		int out_cookUid = 0;
		Random random = new Random();

		for (int i = 0; i < 6; i++) {

			out_cookUid = random.nextInt(maxIndexNum) + 1;
			cookUids.add(out_cookUid);

		}

		return cookUids;
	}

}
