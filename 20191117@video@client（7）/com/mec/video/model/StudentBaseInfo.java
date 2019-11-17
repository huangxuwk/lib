package com.mec.video.model;

import java.sql.Blob;
import java.sql.SQLException;

public class StudentBaseInfo {
	private String peopleId;
	private String name;
	private byte[] photo;
	
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		try {
			if (photo == null || photo.length() <= 0) {
				this.photo = null;
			}
			this.photo = photo.getBytes(1, (int) photo.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return peopleId + ":" + name + " " + (photo == null ? "无照片" : "有照片");
	}
	
}
