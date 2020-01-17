package com.dl.server.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.parser_reflect.util.PackageScanner;

public class MethodFactory {
	private static final Map<Integer, MethodDefinition> methodPool;
	
	static {
		methodPool = new HashMap<>();
	}

	public MethodFactory() {
	}
	
	public static void scanPackage(String path) {
		new PackageScanner() {
			
			@Override
			public void dealClass(Class<?> klass) {
				if (klass.getAnnotation(ProxyAnntotation.class) == null) {
					return;
				}
				registry(klass);
			}
		}.packageScanner(path);
	}
	
	public static void registry(Class<?> klass) {
		Object object = null;
		try {
			object = klass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		ProxyAnntotation proxy = klass.getAnnotation(ProxyAnntotation.class);
		Class<?>[] interfaces = proxy.interfaces();
		for (Class<?> clazz : interfaces) {
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				try {
					Method meth = klass.getDeclaredMethod(method.getName(), method.getParameterTypes());
					if (meth != null) {
						MethodDefinition definition = new MethodDefinition(object, meth);
						methodPool.put(method.toString().hashCode(), definition);
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static MethodDefinition getMethodDefinition(int hashCode) {
		return methodPool.get(hashCode);
	}
}
