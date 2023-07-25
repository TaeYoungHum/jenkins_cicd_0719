package com.cook.model.jpa;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//이거 이후. extends JpaRepository<CookJpa, Integer> 를 상속받는 인터페이스 만들어야 jpa 동작한다.
@Component
@Entity // jpa 임을 알린다.
@Table(name = "cook", schema = "mrd_db") // 테이블 이름 알려주기
public class CookTableJpa {

//	cook_uid	int	NO	PRI		auto_increment
//	cook_title	varchar(300)	NO			
//	preparation_time	varchar(300)	NO			
//	cook_time	varchar(300)	NO			
//	number_of_people	varchar(300)	NO			
//	representative_cook_img	varchar(500)	NO			
//	profile_id	varchar(300)	YES	MUL		
//	cook_date	datetime	NO		CURRENT_TIMESTAMP	DEFAULT_GENERATED
//	view_count	int	YES		0	
//	good_count	int	YES		0	
//	bad_count	int	YES		0	


	@Id // 프라이머리 키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name = "cook_uid") // mysql 컬럼과 매칭
	private int cook_uid;

	@Column(name = "cook_title", nullable = false, length = 300) //매핑, null, varchar 크기. 기본 255
	private String cookTitle;

	@Column(name = "preparation_time", nullable = false, length = 300)
	private String preparationTime;

	@Column(name = "cook_time", nullable = false, length = 300)
	private String cookTime;

	@Column(name = "number_of_people", nullable = false, length = 300)
	private String numberOfPeople;

	@Column(name = "representative_cook_img", nullable = false, length = 500)
	private String representativeCookImg;

	@Column(name = "profile_id", length = 300)
	private String profileId;

	@Column(name = "cook_date", nullable = false)//, nullable = false
	private LocalDateTime cookDate; //String보다 이렇게 나타내는 것이 좋다.
	
	@Column(name="view_count")
	private  int view_count;
	
	@Column(name="good_count")
	private  int good_count;
	
	@Column(name="bad_count")
	private int badCount;

	public int getCook_uid() {
		return cook_uid;
	}

	public void setCook_uid(int cook_uid) {
		this.cook_uid = cook_uid;
	}

	public String getCookTitle() {
		return cookTitle;
	}

	public void setCookTitle(String cookTitle) {
		this.cookTitle = cookTitle;
	}

	public String getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public String getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getRepresentativeCookImg() {
		return representativeCookImg;
	}

	public void setRepresentativeCookImg(String representativeCookImg) {
		this.representativeCookImg = representativeCookImg;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public LocalDateTime getCookDate() {
		return cookDate;
	}

	public void setCookDate() {
		this.cookDate = LocalDateTime.now();
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public int getGood_count() {
		return good_count;
	}

	public void setGood_count(int good_count) {
		this.good_count = good_count;
	}

	public int getBadCount() {
		return badCount;
	}

	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}
	
	

//	public int getCook_uid() {
//		return cook_uid;
//	}
//
//	public void setCook_uid(int cook_uid) {
//		this.cook_uid = cook_uid;
//	}
//
//	public String getCookTitle() {
//		return cookTitle;
//	}
//
//	public void setCookTitle(String cookTitle) {
//		this.cookTitle = cookTitle;
//	}
//
//	public String getPreparationTime() {
//		return preparationTime;
//	}
//
//	public void setPreparationTime(String preparationTime) {
//		this.preparationTime = preparationTime;
//	}
//
//	public String getCookTime() {
//		return cookTime;
//	}
//
//	public void setCookTime(String cookTime) {
//		this.cookTime = cookTime;
//	}
//
//	public String getNumberOfPeople() {
//		return numberOfPeople;
//	}
//
//	public void setNumberOfPeople(String numberOfPeople) {
//		this.numberOfPeople = numberOfPeople;
//	}
//
//	public String getRepresentativeCookImg() {
//		return representativeCookImg;
//	}
//
//	public void setRepresentativeCookImg(String representativeCookImg) {
//		this.representativeCookImg = representativeCookImg;
//	}
//
//	public String getProfileId() {
//		return profileId;
//	}
//
//	public void setProfileId(String profileId) {
//		this.profileId = profileId;
//	}
//
//	public LocalDateTime getCookDate() {
//		return cookDate;
//	}
//
//	public void setCookDate() {
//		this.cookDate = LocalDateTime.now();
//	}
	
	
	
	
	
	//LocalDateTime을 String 대신 쓰는 이유
	
//	import java.time.LocalDateTime;
//
//	public class Example {
//	    public static void main(String[] args) {
//	        // 현재의 로컬 날짜와 시간 정보를 가져옴
//	        LocalDateTime currentDateTime = LocalDateTime.now();
//	        System.out.println("Current DateTime: " + currentDateTime);
//
//	        // 특정 날짜와 시간을 생성
//	        LocalDateTime specificDateTime = LocalDateTime.of(2023, 5, 22, 10, 30, 0);
//	        System.out.println("Specific DateTime: " + specificDateTime);
//
//	        // 날짜 및 시간 연산
//	        LocalDateTime futureDateTime = currentDateTime.plusDays(3).minusHours(2);
//	        System.out.println("Future DateTime: " + futureDateTime);
//
//	        // 날짜 및 시간 비교
//	        boolean isAfter = specificDateTime.isAfter(currentDateTime);
//	        System.out.println("Is specificDateTime after currentDateTime? " + isAfter);
//	
	
}
