package com.devveri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="members") 
public class Member
{
	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="_id")  
	private String id;  
	
	private String fullName;
	private String email;
	private Integer age;
	private Double balance;
	
	public Member() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", fullName=" + fullName + ", email="
				+ email + ", age=" + age + ", balance=" + balance + "]";
	}
}	
