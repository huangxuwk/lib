package com.mec.about_view.model;

public class HobbyModel {
	private String hobby;
	
	public HobbyModel() {
	}
	
	public HobbyModel(String hobby) {
		this.hobby = hobby;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return hobby;
	}
	
}
