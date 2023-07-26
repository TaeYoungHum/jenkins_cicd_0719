package com.cook.model.jpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="comment", schema = "mrd_db")
public class CommentTableJpa {
	
//	comment_uid	int	NO	PRI		auto_increment
//	comment_date	datetime	NO		CURRENT_TIMESTAMP	DEFAULT_GENERATED
//	comment_content	varchar(500)	YES			
//	cook_cook_uid	int	NO	MUL		
//	profile_id	varchar(300)	NO	MUL		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	@Column(name="comment_uid")
	private int comment_uid;
	
	@Column(name="comment_date", nullable = false)
	private LocalDateTime comment_date; //setter 함수 추가해야함. LocalDateTime.now();
	
	@Column(name="comment_content", length = 500)
	private String comment_content;
	
	@Column(name="cook_cook_uid", nullable = false)
	int cook_cook_uid;
	
	@Column(name="profile_id", nullable = false, length = 300)
	private String profile_id;

	public int getComment_uid() {
		return comment_uid;
	}

	public void setComment_uid(int comment_uid) {
		this.comment_uid = comment_uid;
	}

	

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getCook_cook_uid() {
		return cook_cook_uid;
	}

	public void setCook_cook_uid(int cook_cook_uid) {
		this.cook_cook_uid = cook_cook_uid;
	}

	public String getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	public LocalDateTime getComment_date() {
		return comment_date;
	}

	public void setComment_date(LocalDateTime comment_date) {
		this.comment_date = LocalDateTime.now();
	}
	
	
	
	
	
	
	
	


}
