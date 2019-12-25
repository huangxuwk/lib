package com.mec.video.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mec.video.IMecInfoTableName;

@Entity
@Table(name=IMecInfoTableName.STUDENT_BASE_INFO)
public class StudentPhotoModel {
	@Id
	private int no;
	@Column(name="people_id")
	private String peopleId;
	private Blob photo;
	
	public StudentPhotoModel() {
	}

	public StudentPhotoModel(String peopleId, Blob photo) {
		this.peopleId = peopleId;
		this.photo = photo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	
}
