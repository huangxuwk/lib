package com.dl.rmi.core;

import java.util.HashMap;

import java.util.Map;

import com.mec.orm.core.PackageScanner;

public class ClassFactory {
	private static final Map<Class<?>, ClassDefinition> classMap;
	
	static {
		classMap = new HashMap<>();
	}
	
	public ClassFactory() {
	}
	
	public void packageScanner(String packagePath) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				if (!klass.isAnnotationPresent(InterfaceAnno.class)) {
					return;
				}
				dealInterfaces(klass);
			}
		}.packageScanner(packagePath);;
	}
	
	private void dealInterfaces(Class<?> klass) {
		Class<?>[] interfacess = klass.getInterfaces();
		Object object = null;
		for (Class<?> interfaces : interfacess) {
			try {
				object = klass.newInstance();
				ClassDefinition classDefinition = new ClassDefinition(klass, object);
				classMap.put(interfaces, classDefinition);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addClassDefinition(Class<?> klass) {
		if (klass.getInterfaces().length <= 0) {
			return false;
		}
		dealInterfaces(klass);
		
		return true;
	}
	
	public ClassDefinition getClassDefinition(Class<?> interfaces) {
		return classMap.get(interfaces);
	}
	
}
