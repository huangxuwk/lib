package com.mec.video.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mec.video.IMecInfoTableName;

@Entity
@Table(name=IMecInfoTableName.STUDENT_INFO)
public class StudentModel {
	@Id
	private String id;
	private String name;
	private String password;
	@Column(name="people_id")
	private String peopleId;
	private String status;
	
	public StudentModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return id + ":" + name 
				+ "\n" + password 
				+ "\n" + peopleId
				+ "\n" + (status == null ? "" 
					: (status.equals("0") ? "¡Ì" : "¡Á"));
	}
	
}
