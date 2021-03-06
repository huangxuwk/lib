package com.mec.video.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=IMecInfoTableName.STUDENT_INFO)
public class StudentVideoModel {
	@Id
	private String id;
	private String name;
	@Column(name="people_id")
	private String peopleId;
	private String password;
	private boolean status;
	
	public StudentVideoModel() {
	}

	public StudentVideoModel(String id, String name, String peopleId, String password, boolean status) {
		this.id = id;
		this.name = name;
		this.peopleId = peopleId;
		this.password = password;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", peopleId=" + peopleId + ", password=" + password
				+ ", status=" + status + "]";
	}
	
}
