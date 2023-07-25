package com.cook.util.googleApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

//디펜던시는 maven repository 가서 제일 사람 많은 버전으로 설정함.
/*
<dependency>
<groupId>com.google.cloud</groupId>
<artifactId>google-cloud-texttospeech</artifactId>
<version>2.17.0</version>
</dependency>

<dependency>
<groupId>com.google.auth</groupId>
	<artifactId>google-auth-library-oauth2-http</artifactId>
	<version>1.16.0</version>
</dependency>

*/

//https://console.cloud.google.com/apis/dashboard?hl=ko&project=mrdprojecttts 에서 사용 현황을 볼 수 있고.
//GOOGLE_APPLICATION_CREDENTIALS 윈도우는 여기에 패스를 추가해서 써야한다.
//리눅스는 https://datamoney.tistory.com/341 에서 리눅스 과정을 보고 auto 등록까지 하면 된다.
//package com.cook.service.googleApi; 

@Component
public class GoogleTtsService {

	// 윈도우 경로 : "E:"
	// 맥 경로 : "/Users/jungkihun/Documents/source/python/hc_db"

	String windowBacePath = "E:";
	String macBacePath = "/Users/jungkihun/Documents/source/python/hc_db";

	public void TTSConvert(int cookUid, int index, String textToSpeak) {

		System.out.println("tts s-in");

		// 텍스트 길이 체크
		if (textToSpeak.length() > 501) {

			textToSpeak = textToSpeak.substring(0, 499); // 문자열 길이가 500을 넘을시 자름.

		}

		String choiceLanguage_ko = "ko-KR"; // 이 코드 변환 시켜서 영어로 나타낼 수 있다.
		double speakSpeed = 1.0; // 재생 속도

		// 기본적으로 다 try catch 안에 씀.

		// 서비스 계정 키 파일을 로드합니다

		/*
		 * 이 부분은 이제 정크. 인증 관련은 패스 체크를 TextToSpeechClient client =
		 * TextToSpeechClient.create() 할때 알아서 체크한다.
		 * 
		 * //String keyFilePath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
		 * //환경변수를 통한 key 로드. josn 형식의 로컬 파일. //Path credentialsPath =
		 * Paths.get(keyFilePath); // 굳이 Path 객체를 만드는 이유는. java 가 jvm으로 os 상관 없이 작동 되는것과
		 * 유사한 이유다. 이걸로 만들면 파일 경로를 os와 무관하게 사용가능하게 된다 //GoogleCredentials credentials =
		 * GoogleCredentials.fromStream(new FileInputStream(credentialsPath.toFile()));
		 * // Path변수.toFile() 매서드는, 해당 경로에 대한 File 객체를 반환한다. //FileInputStream은
		 * InputStream을 상속받은 객체로. FileInputStream(File f) 하면 주어진 File 객체가 가리키는 파일을 읽거나 쓸
		 * 수 있게 됨.
		 * 
		 */

		// Text-to-Speech 클라이언트를 생성 -> 밑에 try (TextToSpeechClient client =
		// TextToSpeechClient.create()) 부분이 실제 통신 하는 모듈 부분.
		TextToSpeechClient client = null;
		try {
			System.out.println("1" + client);
			
//			String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
//			TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
//			    .setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream(credentialsPath))))
//			    .build();

//			client = TextToSpeechClient.create(settings);
			
			client = TextToSpeechClient.create(); // 통신 시작
			System.out.println("1-1" + client);
			// API를 통해 음성 데이터를 요청하고 처리합니다
			SynthesisInput input = SynthesisInput.newBuilder().setText(textToSpeak).build(); // tts 할 문자열
			System.out.println("2");
			VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode(choiceLanguage_ko) // 랭귀지 선택
					.setSsmlGender(SsmlVoiceGender.FEMALE).build(); // 여기 부분에서 보이스 캐릭터를 바꿀 수 있다.
			System.out.println("3");
			AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3)
					.setSpeakingRate(speakSpeed).build(); // 여기서 setSpeakingRate()의 숫자를 바꿔서 재생 속도도 변경가능.
			System.out.println("4");
			SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice, audioConfig);

			// 음성 데이터를 파일로 저장하기
			StringBuilder stringBuilder_baseOutputFilePath = new StringBuilder();
			String baseOutputFilePath_window = macBacePath + File.separator + "TTS" + File.separator
					+ (Integer.toString(cookUid)); // 저장소 위치 -> 패스+파일명

			stringBuilder_baseOutputFilePath.append(baseOutputFilePath_window);
			stringBuilder_baseOutputFilePath.append(File.separator + Integer.toString(index) + ".mp3");

			// Java 7부터 도입된 try-with-resources 문법
			// try-with-resources 문법을 사용하면 개발자가 명시적으로 리소스를 닫을 필요가 없음.
			// FileOutputStream 객체인 outputStream는 try 블록을 벗어날때, 자동으로 close 됨.
			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(stringBuilder_baseOutputFilePath.toString());
				outputStream.write(response.getAudioContent().toByteArray()); // 파일 실질적으로 저장되는 부분.
				System.out.println("음성 데이터가 " + stringBuilder_baseOutputFilePath.toString() + "에 저장되었습니다!");
			} catch (IOException e) {
				System.err.println("Failed to save audio data1, why? -> " + e.getMessage());
			} finally {

				outputStream.close();

			}

		} catch (IOException e) {
			System.err.println("Failed to save audio data1, why? -> " + e.getMessage());
		} finally {
			client.close();
		}

		System.out.println("tts s-out");
	}

	// 순서대로 만드는 것.
	public void ListTts(int cookUid, List<String> returnTtsStrings) {

		// 1. 경로 생성.
		String baseDir_windowString = macBacePath + File.separator + "TTS" + File.separator
				+ (Integer.toString(cookUid));// 기본 경로

		// 폴더 생성과정

		File newFolderCheck = new File(baseDir_windowString); // 파일객체 초기화해서 생성

		// 경로 있는지 판별 후, 없으면 생성.
		if (!newFolderCheck.exists()) {
			boolean result = newFolderCheck.mkdirs(); // 경로대로 생성.
			if (result) {
				System.out.println("폴더가 생성되었습니다.");
			} else {
				System.out.println("폴더 생성에 실패하였습니다.");
			}
		} else {
			System.out.println("이미 폴더가 존재합니다.");
		}

		// 2. 기본 경로 /+ cookUid 로 폴더 만들었으니. 세부 파일들 생성.

		for (int index = 1; index <= returnTtsStrings.size(); index++) {

			TTSConvert(cookUid, index, returnTtsStrings.get(index - 1)); // int cookUid, int index,String
																			// returnTtsStrings.get(index)
		}

	}

}
