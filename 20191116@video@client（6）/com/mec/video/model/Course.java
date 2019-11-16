package com.mec.video.model;

public class Course {
	private String id;
	private String name;
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
