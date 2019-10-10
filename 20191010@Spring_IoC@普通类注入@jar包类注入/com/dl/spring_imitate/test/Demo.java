package com.dl.spring_imitate.test;

import com.dl.spring_imitate.core.BeanFactory;
import com.dl.spring_imitate.some_class.ClassOne;
import com.dl.spring_imitate.some_class.ClassTwo;

public class Demo {

	public static void main(String[] args) {
		BeanFactory.scanBeanByPackage("com.dl.spring_imitate.some_class");

		ClassTwo classTwo = new BeanFactory().getBean(ClassTwo.class);
		System.out.println(classTwo);
		
		ClassOne classOne = new BeanFactory().getBean(ClassOne.class);
		System.out.println(classOne);
	}

}
