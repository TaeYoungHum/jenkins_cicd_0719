package com.cook.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="profile", schema = "mrd_db")
public class ProfileTableJpa {
	
//	id	varchar(300)	NO	PRI
//	password	varchar(300)	NO	
//	name	varchar(300)	NO	
//	data_of_birth	varchar(300)	NO	
//	gender	varchar(300)	NO	
//	email	varchar(500)	YES	
//	phone_number	varchar(300)	YES	
//	address	varchar(500)	YES	
//	nick_name	varchar(300)	NO	
	
	@Id
	@Column(name="id", length = 300, nullable = false)
	private String id;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="data_of_birth", nullable = false)
	private String data_of_birth;

	@Column(name="gender", nullable = false)
	private String gender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="address")
	private String address;
	
	@Column(name="nick_name", nullable = false)
	private String nick_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData_of_birth() {
		return data_of_birth;
	}

	public void setData_of_birth(String data_of_birth) {
		this.data_of_birth = data_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	
	
	


}
