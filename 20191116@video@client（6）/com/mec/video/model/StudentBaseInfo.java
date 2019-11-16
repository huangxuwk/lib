package com.mec.video.model;

import java.sql.Blob;

public class StudentBaseInfo {
	private String peopleId;
	private String name;
	private Blob photo;
	
	public StudentBaseInfo() {
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return peopleId + ":" + name + " " + (photo == null ? "无照片" : "有照片");
	}
	
}
