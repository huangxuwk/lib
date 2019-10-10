package com.dl.spring_imitate.some_class;

import com.dl.spring_imitate.annotation.Bean;
import com.dl.spring_imitate.annotation.Component;

@Component
public class Configuration {
	
	public Configuration() {
	}
	
	@Bean 
	public ClassOne getClassOne(ClassTwo classTwo) {
		return new ClassOne(classTwo);
	}
	
	@Bean
	public ClassTwo getClassTwo(ClassOne classOne) {
		return new ClassTwo(classOne);
	}
	
}
