package com.shupoha.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCategoryParser {

	String preData = null;

	@Autowired
	SpringWebClientService springWebClientService;

	public String foodParser(String Window_path) {

		List<String> pasingDatas = new ArrayList<String>(); // 파싱된 데이터 저장

		HashMap<Integer, Double> pasingData_hashMap = new HashMap<>();
		List<Integer> key_int = new ArrayList<Integer>(); // 해쉬맵의 몇번이 큰 숫자 였는지 저장

		System.out.println("FoodCategoryParser 내부 통신 시작");
		preData = springWebClientService.springWebClientHttpCall(Window_path);
		System.out.println("FoodCategoryParser 내부 통신 끝");

		preData = preData.substring(13, preData.length() - 4); // json 으로 변환 하는 법 적용하면 변경가능.
		System.out.println(preData);
		System.out.println("---------------------");
		Map.Entry<Integer, Double> max = null;
		String FoodName = null;
		
		

		// , 단위로 스플릿
		String pasingDatas_array[] = preData.split(",");

		// 배열을 리스트<> 변환. .get 쓰려고. aslist() 쓰면되나. 이상하게 jdk 버전 확인하라는 오류떠서 for 사용.
		for (String s : pasingDatas_array) {
			pasingDatas.add(s);

		}
		System.out.println("println(pasingDatas)@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(pasingDatas);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		// 1차 필요 없는 부분 제거.
		for (int i = 0; i < pasingDatas.size(); i++) {

			if (!pasingDatas.get(i).contains("e") && !pasingDatas.get(i).contains("E-") ) {
				if (Double.parseDouble(pasingDatas.get(i)) > 0.01f) {
					pasingData_hashMap.put(i, Double.parseDouble(pasingDatas.get(i))); // 키 벨류 값 저장. index : double 형식
					key_int.add(i);// 키 값만 리스트에 저장.
					System.out.print(i + "->" + pasingData_hashMap.get(i) + ", ");
				}
			}

		}System.out.println();
	

		// 2. 숫자 비교
		System.out.println("해쉬맵 크기 = " + key_int.size()); // 키 값만 모아둔거로 크기 측정. 해쉬맵 자체에 걸어도 됨. 별 차이 없.

		if (key_int.size() > 1) {
			System.out.println("sort 시작");
			// Map.Entry<Integer, Double> max
			max = FoodCategoryParser.getMaxEntry(pasingData_hashMap);

			System.out.println("1max의 키 값 = " + max.getKey() + " , ->" + max.getValue());

		} else {
			System.out.println("%%%%%%%%%%%%%% "+key_int.get(0)+" : "+pasingData_hashMap.get(key_int.get(0)));
			// 그냥 내장 함수 쓰려고. 타입 변환 하는 것.
			max = FoodCategoryParser.getMaxEntry(pasingData_hashMap);
			

			System.out.println("1max의 키 값 = " + max.getKey() + " , ->" + max.getValue());

		}

		// 3.case 로 각각 변환.
		FoodName = null; // 오토 와이어드 하면 혹시몰라서 초기화
		FoodName = FoodCategoryParser.foodNameCaseByCase(max.getKey()); // 우린 몇번째 인덱스인지가 중요함

		return FoodName;
	}

	// 최대 구하는 함수. Map.Entry 는 인터페이스로. 실제 반환 객체는 Map.Entry 를 구현한 객체를 반환해야한다.
	private static Map.Entry<Integer, Double> getMaxEntry(HashMap<Integer, Double> inputHashMap) {

		Map.Entry<Integer, Double> maxEntry = null; // 컨테이너
		double maxValue = Double.MIN_VALUE; // 더블형의 최저 값을 대입해서 초기화.

		// .entrySet() 메서드는 해쉬 맵의 메서드로. 이걸 사용하면. {Key : value} 값들이 준비되고. 강화된 for 문을 사용하는
		// 것.
		for (Map.Entry<Integer, Double> container : inputHashMap.entrySet()) {

			System.out.println("max 함수 내부 값 체크 : "+container.getValue());
			if (container.getValue() > maxValue || maxEntry == null) {

				if (container.getValue() < 0.89f) {

					System.out.println("max 함수 내부 값 체크2 : "+container.getValue());
					//값 작을때 12번 인덱스 지정위해서
					HashMap<Integer, Double> foodCantFindCase = new HashMap<>();
					foodCantFindCase.put(12, (double)0.5f);
					
					for (Map.Entry<Integer, Double> container2 : foodCantFindCase.entrySet()) {
						System.out.println("max 함수 내부 값 체크3");
						maxValue = 0.5;
						maxEntry = container2; // 12번 인덱스. 12:0.001
						System.out.println("현재최대 index =" + maxEntry.getKey() + " value는 = " + maxEntry.getValue());
					}

				} else {

					maxValue = container.getValue();
					maxEntry = container;
					System.out.println("현재최대 index =" + maxEntry.getKey() + " value는 = " + maxEntry.getValue());
				}

			}

		}

		return maxEntry;

	}

	// 그냥 switch-case 구문임.
	private static String foodNameCaseByCase(int num) {

		switch (num) {
		case 0:

			return "비빔밥";

		case 1:

			return "도넛";

		case 2:

			return "만두";

		case 3:

			return "햄버거";

		case 4:

			return "핫도그";

		case 5:

			return "팬케이크";

		case 6:

			return "피자";

		case 7:

			return "스파게티";

		case 8:

			return "스테이크";

		

		default:

			return "Ai로 유추 실패했습니다. 새로 찍거나 직접 적어 주세요.";
		}

	}

}
