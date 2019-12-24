package com.mec.video.model;

public class StudentDetailInformation {
	private String stuId;
	private String name;
	private String photo;
	private String course;
	
	public StudentDetailInformation() {
	}

	public StudentDetailInformation(String stuId, String name, String photo, String course) {
		this.stuId = stuId;
		this.name = name;
		this.photo = photo;
		this.course = course;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stuId == null) ? 0 : stuId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDetailInformation other = (StudentDetailInformation) obj;
		if (stuId == null) {
			if (other.stuId != null)
				return false;
		} else if (!stuId.equals(other.stuId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDetailInformation [stuId=" + stuId 
				+ ", name=" + name 
				+ ", photo=" + photo 
				+ ", course=" + course + "]";
	}

}
