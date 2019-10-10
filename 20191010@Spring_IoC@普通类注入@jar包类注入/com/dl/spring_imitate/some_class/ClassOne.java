package com.dl.spring_imitate.some_class;

public class ClassOne {
	private ClassTwo classTwo;
	private String id;
	private String name;

	public ClassOne() {
	}
	
	public ClassOne(ClassTwo classTwo) {
	}

	public ClassTwo getClassTwo() {
		return classTwo;
	}

	public void setClassTwo(ClassTwo classTwo) {
		this.classTwo = classTwo;
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
	
	
}
