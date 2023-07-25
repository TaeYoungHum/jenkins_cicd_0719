package com.cook.service.api;

import java.awt.print.Printable;
import java.util.concurrent.CountDownLatch;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.stereotype.Service;

@Service
public class StringChangeService {
	//"," 단위로 찢어줌
	public String[] stringSplit(String s) {

		String[] s_array = s.split(",");

		return s_array;
	}
	//""제외하고. 실제 데이터 있는 부분만 넣음.
	public String[] stringRealData(String[] s) {

		System.out.println(s.length);
		
		String[] pure_callbackS = new String[100];
		
		int count = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i].contains("\"\"")) {
			} else {
				pure_callbackS[count] = s[i];
				count += 1;

			}

		}
		
		return pure_callbackS;

	}
	
	//null 전까지 길이를 리턴
	public int checkNull(String[] calls) {
		
		int count = 0;
		for(String call : calls) {
			
			if(call == null ) {
				System.out.println("브레이크 당시 i = "+count);
				break;
				
				
			}else {
				count+=1;
				
			}
			
			
		}
		

		
		return count;
		
	}
	
	//재조립(open api 너무 자료 적어서 ㅌㅌ)
	//public String[] reaseemble
	
	
	

}
