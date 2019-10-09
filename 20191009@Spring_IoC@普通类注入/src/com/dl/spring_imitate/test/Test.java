package com.dl.spring_imitate.test;

import com.dl.spring_imitate.core.BeanFactory;
import com.dl.spring_imitate.some_class.ClassOne;
import com.dl.spring_imitate.some_class.ClassTwo;

public class Test {

	public static void main(String[] args) {
		BeanFactory.scanBeanPackage("com.dl.spring_imitate.some_class");
		
		ClassOne classOne = BeanFactory.getBean(ClassOne.class);
		System.out.println("classOne: " + classOne);
		System.out.println("classOneÖÐµÄClassTwo : " + classOne.getClassTwo());
		ClassTwo classTwo = BeanFactory.getBean(ClassTwo.class);
		System.out.println("classTwo: " + classTwo);
	}

}
