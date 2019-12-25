package com.mec.video.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mec.video.IMecInfoTableName;

@Entity
@Table(name=IMecInfoTableName.DATA_VERSION)
public class VersionModel {
	@Id
	private String id;
	private long version;
	
	public VersionModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
