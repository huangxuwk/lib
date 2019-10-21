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
	
	// ��ɨ���ҵ���ָ��ע�����
	public void scanPackage(String packagePath) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				if (!klass.isAnnotationPresent(RMIIterfaceAnno.class)) {
					return;
				}
				RMIIterfaceAnno anno = klass.getAnnotation(RMIIterfaceAnno.class);
				// һ��������ж��ʵ�ֵĽӿڣ���Ҫ��ÿ���ӿ��еķ���������methodPool
				Class<?>[] interfaces = anno.interfaces();
				for (Class<?> inter : interfaces) {
					register(inter, klass);
				}
			}
 		}.packageScanner(packagePath);
	}
	
	// �������Ϊpublic��ԭ���ǣ����Ը�����ʹ�����ṩ�ֶ�ע��ķ�ʽ
	// ֱ�ӵ�������������Բ��ø�ָ�����ע��
	public void register(Class<?> interfaces, Class<?> klass) {
		// ��������������������жϣ�����Ҫ��һ�����������м̳й�ϵ
		if (interfaces == null 
				|| klass == null 
				|| !interfaces.isInterface()
				|| klass.isInterface()
				|| !interfaces.isAssignableFrom(klass)) {
			return;
		}
		/*
		 * �������ӿڵ����з����ҵ���Ӧʵ����ķ��������γɼ�ֵ��
		 * �Ա����ڷ������õ��ӿڷ�����hashCodeʱ���ҵ���Ӧʵ����ķ���
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
