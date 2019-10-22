package com.mec.about_view.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mec.about_view.view.ViewForTest;

public class StudentModel {
	private String stuId;
	private String name;
	private String password;
	private boolean sex;
	private Date birth;
	private List<HobbyModel> hobbies;
	private String introduce;
	public static final HobbyModel[] HOBBIES = new HobbyModel[ViewForTest.hobbyNames.length];	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	
	public StudentModel() {
		hobbies = new ArrayList<>();
	}
	
	public void initArray() {
		for (int i = 0; i < ViewForTest.hobbyNames.length; i++) {
			HOBBIES[i] = new HobbyModel(ViewForTest.hobbyNames[i]);
		}
	}
	
	public void clearHobbies() {
		hobbies.clear();
	}
	
	public void addHobby(HobbyModel hobby) {
		if (hobbies.contains(hobby)) {
			return;
		}
		hobbies.add(hobby);
	}
	
	public List<Integer> getHobbies() {
		List<Integer> res = new ArrayList<>();
		int studentHobbyIndex = 0;
		
		for (int index = 0; index < HOBBIES.length 
				&& studentHobbyIndex < hobbies.size(); index++) {
			if (HOBBIES[index].equals(hobbies.get(studentHobbyIndex))) {
				res.add(index);
				++studentHobbyIndex;
			}
		}
			
		return res;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Override
	public String toString() {
		return stuId + (sex ? " ÄÐ " : " Å® ")
				+ "[" + sdf.format(birth) 
				+ "] " + name;
	}
	
}


