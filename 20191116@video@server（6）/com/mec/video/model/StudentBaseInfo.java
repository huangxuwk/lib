package com.mec.video.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=IMecInfoTableName.MEC_STUDENT_BASE_INFO)
public class StudentBaseInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int no;
	@Column(name="people_id")
	private String peopleId;
	private String name;
	private Blob photo;
	
	public StudentBaseInfo() {
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
