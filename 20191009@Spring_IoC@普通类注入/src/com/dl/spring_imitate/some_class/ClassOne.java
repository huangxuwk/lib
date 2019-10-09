package com.dl.spring_imitate.some_class;

import com.dl.spring_imitate.annotation.Autowired;
import com.dl.spring_imitate.annotation.Component;

@Component
public class ClassOne {
	@Autowired
	private ClassTwo classTwo;
	private String id;
	private String name;

	public ClassOne() {
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
