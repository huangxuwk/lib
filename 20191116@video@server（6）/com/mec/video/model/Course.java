package com.mec.video.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=IMecInfoTableName.CLASS_INFO)
public class Course {
	@Id
	private String id;
	private String name;
	@Column(name="video_path")
	private String path;
	private boolean status;
	
	public Course() {
	}

	public Course(String id, String name, String path, boolean status) {
		this.id = id;
		this.name = name;
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", path=" + path + ", status=" + status + "]";
	}
	
}
