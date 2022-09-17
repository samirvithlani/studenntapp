package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//to define this class as persistance class
@Entity
@Table(name = "students")
public class StudentBean {

	@Id
	@GeneratedValue
	private int id;

	private String sName;
	private String sEmail;
	private String sPassowrd;
	private int sAge;
	private String sPhone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsPassowrd() {
		return sPassowrd;
	}

	public void setsPassowrd(String sPassowrd) {
		this.sPassowrd = sPassowrd;
	}

	public int getsAge() {
		return sAge;
	}

	public void setsAge(int sAge) {
		this.sAge = sAge;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

}
