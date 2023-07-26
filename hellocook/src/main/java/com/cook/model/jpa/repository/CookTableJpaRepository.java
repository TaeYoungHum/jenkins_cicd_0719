package com.cook.model.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cook.model.jpa.CookTableJpa;

public interface CookTableJpaRepository extends JpaRepository<CookTableJpa, Integer> {

	// extends JpaRepository<CookJpa, Integer> 에서 JpaRepository<T,T> 에서 앞엔 @entity
	// 어노테이션 붙여준 class
	// 뒤엔 @id 붙여준. 프라이머리 키의 type을 명시해줘야한다.

	// 그리고 여기엔 특수 쿼리 선언 가능하다.

	// 상속을 받거나. 내가 직접 선언

//	@Query(value = "SELECT * FROM mrd_db.cook WHERE cook_uid = :cook_uid", nativeQuery = true)
//	public List<CookJpa> findfindByCook_uid(int cook_uid);
//	
	@Query(value = "select * from mrd_db.cook where cook_uid in (select distinct c.cook_uid\r\n"
			+ "from mrd_db.cook c \r\n" + "join mrd_db.food_ingredient i on c.cook_uid = i.cook_cook_uid\r\n"
			+ "join mrd_db.tag t on c.cook_uid = t.cook_cook_uid\r\n"
			+ "where cook_title Like %:keyword%)", nativeQuery = true)
	public List<CookTableJpa> findCookSearch(String keyword);

	@Query(value = "select * from mrd_db.cook where cook_uid in (select distinct c.cook_uid\r\n"
			+ "from mrd_db.cook c \r\n" + "join mrd_db.food_ingredient i on c.cook_uid = i.cook_cook_uid\r\n"
			+ "join mrd_db.tag t on c.cook_uid = t.cook_cook_uid\r\n"
			+ "where t.tag_name like %:keyword%)", nativeQuery = true)
	public List<CookTableJpa> findTagSearch(String keyword);

	public List<CookTableJpa> findTop5ByOrderByCookDateDesc(); // jpa 강력한점. 이렇게 양식 지켜서 쓰면 알아서 쿼리 생성.
	// Top -> 최상위, OrderBy[****] -> **** 필드를 기준으로 정렬. Desc -> 내림차순. Asc -> 오름 차순

	// 23.06.15 insert into
//	@Modifying(clearAutomatically = true)
//	@Query(value = "insert into mrd_db.cook (cook_title, preparation_time, cook_time, number_of_people,representative_cook_img, profile_id) "
//	        + "values(:cookTableJpa.cookTitle, :cookTableJpa.preparationTime, :cookTableJpa.cookTime, "
//	        + ":cookTableJpa.numberOfPeople, :cookTableJpa.representativeCookImg, :cookTableJpa.profileId)")
//	public void insertIntoCookTable(CookTableJpa cookTableJpa);
	//일단 cook은 그냥 save 해도 문제 없음.
	@Modifying(clearAutomatically = true)
	@Query(value = "insert into mrd_db.cook (cook_title, preparation_time, cook_time, number_of_people,representative_cook_img) "
			+ "values(:#{#cookTableJpa.cookTitle}, :#{#cookTableJpa.preparationTime}, :#{#cookTableJpa.cookTime}, "
			+ ":#{#cookTableJpa.numberOfPeople}, :#{#cookTableJpa.representativeCookImg})", nativeQuery = true)
	public void insertCookTable(CookTableJpa cookTableJpa);

}
