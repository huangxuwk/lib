package com.dl.rmi.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.dl.parser_reflect.util.PackageScanner;

public class RMIMethodFactory {
	private static final Map<String, RMIMethodDefinition> methodPool;
	
	static {
		methodPool = new HashMap<>();
	}
	
	// 包扫描找到有指定注解的类
	public void scanPackage(String packagePath) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				if (!klass.isAnnotationPresent(RMIIterfaceAnno.class)) {
					return;
				}
				RMIIterfaceAnno anno = klass.getAnnotation(RMIIterfaceAnno.class);
				// 一个类可能有多个实现的接口，需要将每个接口中的方法都放入methodPool
				Class<?>[] interfaces = anno.interfaces();
				for (Class<?> inter : interfaces) {
					register(inter, klass);
				}
			}
 		}.packageScanner(packagePath);
	}
	
	// 这个方法为public的原因是：可以给工具使用者提供手动注册的方式
	// 直接调用这个方法可以不用给指定类加注解
	public void register(Class<?> interfaces, Class<?> klass) {
		// 对两个类型做最基本的判断，最重要的一点是两个类有继承关系
		if (interfaces == null 
				|| klass == null 
				|| !interfaces.isInterface()
				|| klass.isInterface()
				|| !interfaces.isAssignableFrom(klass)) {
			return;
		}
		/*
		 * 将给定接口的所有方法找到对应实现类的方法，并形成键值对
		 * 以便于在服务器得到接口方法的hashCode时，找到对应实现类的方法
		 */
		Method[] methods = interfaces.getDeclaredMethods();
		for (Method me : methods) {
			try {
				String methodName = String.valueOf(me.toString().hashCode());
				Method method = klass.getMethod(me.getName(), me.getParameterTypes());
				Object object = klass.newInstance();
				
				RMIMethodDefinition methodDefinition = new RMIMethodDefinition(object, method);
				methodPool.put(methodName, methodDefinition);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	RMIMethodDefinition getMethodDefinition(String methodName) {
		return methodPool.get(methodName);
	}
}
