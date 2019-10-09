package com.dl.spring_imitate.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.dl.spring_imitate.annotation.Autowired;
import com.dl.spring_imitate.annotation.Component;
import com.hx.parser_reflect.util.PackageScanner;

public class BeanFactory {
	private static final Map<String, BeanDefinition> beanPool;
	
	static {
		beanPool = new HashMap<String, BeanDefinition>();
	}
	
	public BeanFactory() {
	}
	
	public static void scanBeanPackage(String pachageName) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				// 判断此类是否为需要放入容器中的类
				if (!klass.isAnnotationPresent(Component.class)) {
					return;
				}
				Object object = null;
				try {
					BeanDefinition beanDefinition = new BeanDefinition();
					object = klass.newInstance();
					beanDefinition.setKlass(klass);
					beanDefinition.setObject(object);
					
					beanPool.put(klass.getName(), beanDefinition);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.packageScanner(pachageName);
	}
	
	static BeanDefinition getBeanDefinition(String klassName) {
		return beanPool.get(klassName);
	}
	
	static BeanDefinition getBeanDefinition(Class<?> klass) {
		return getBeanDefinition(klass.getName());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String klassName) {
		BeanDefinition bd = getBeanDefinition(klassName);
		if (bd == null) {
			return null;
		}
		
		Object result = bd.getObject();
		if (!bd.isInject()) {
			// 先将注入标志改为true，可避免出现循环依赖导致无限递归的错误
			bd.setInject(true);
			injectProperties(bd);
		}
		
		return (T) result;
	}
	
	private static void injectProperties(BeanDefinition beanDefinition) {
		Class<?> klass = beanDefinition.getKlass();
		Object object = beanDefinition.getObject();
		
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Autowired.class)) {
				continue;
			}
			field.setAccessible(true);
			// 形成递归注入
			Object value = getBean(field.getType());
			if (value == null) {
				throw new HasNoBeanException("类[" + klass.getName()
						+ "]的[" + field.getName()
						+ "]成员没有对应的Bean!");
			}
			try {
				field.set(object, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
 	}
	
	public static <T> T getBean(Class<?> klass) {
		return getBean(klass.getName());
	}
	
}