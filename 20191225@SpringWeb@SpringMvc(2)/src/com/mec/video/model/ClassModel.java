package com.mec.video.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mec.video.IMecInfoTableName;

@Entity
@Table(name=IMecInfoTableName.CLASS_INFO)
public class ClassModel {
	@Id
	private String id;
	private String name;
	@Column(name="video_path")
	private String videoPath;
	private String status;
	
	public ClassModel() {
	}

	public ClassModel(String id, String name, String videoPath, String status) {
		this.id = id;
		this.name = name;
		this.videoPath = videoPath;
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

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
