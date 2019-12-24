package com.mec.video.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mec.video.IMecInfoTableName;

@Entity
@Table(name=IMecInfoTableName.BASE_SUBJECT_INFO)
public class SubjectModel {
	@Id
	private String id;
	private String name;
	private String status;
	private String complex;
	
	public SubjectModel() {
	}

	public SubjectModel(String id, String name, String status, String complex) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.complex = complex;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

	@Override
	public String toString() {
		return "(" + id + ") " 
				+ (status.equals("0") ? "√ " : "× ") 
				+ (complex.equals("0") ? "单 " : "复 ")
				+ name;
	}
	
}
