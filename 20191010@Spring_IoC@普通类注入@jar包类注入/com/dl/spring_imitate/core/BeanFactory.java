package com.dl.spring_imitate.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import com.dl.spring_imitate.annotation.Autowired;
import com.dl.spring_imitate.annotation.Bean;
import com.dl.spring_imitate.annotation.Component;
import com.mec.orm.core.PackageScanner;

public class BeanFactory {
	private static final Map<String, BeanDefinition> beanPool;
	private static final ParameterDependence pd;
	private static boolean firstGetBean;
	
	static {
		beanPool = new HashMap<String, BeanDefinition>();
		pd = new ParameterDependence();
		firstGetBean = false;
	}
	
	public BeanFactory() {
	}
	
	public static void scanBeanByPackage(String packageName) {
		new PackageScanner() {
			@Override
			public void dealClass(Class<?> klass) {
				if (klass.isPrimitive()
						|| klass.isInterface()
						|| klass.isAnnotation()
						|| klass.isEnum()
						|| klass.isArray()
						|| !klass.isAnnotationPresent(Component.class)) {
					return;
				}
				
				Object object = null;
				try {
					object = klass.newInstance();
					BeanDefinition bd = new BeanDefinition();
					bd.setKlass(klass);
					bd.setObject(object);
					
					beanPool.put(klass.getName(), bd);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				// 查找并处理Bean注解的方法
				// 将所有的beanPool中的bean传给ParameterDependence
				pd.checkAllBean(beanPool.keySet());
				collectBeanMethod(klass, object);
			}
		}.packageScanner(packageName);
		// 处理OnReady的方法
		processBeanMethod();
	}
	
	private static void processBeanMethod() {
			// 可能存在没有参数的方法需要先处理
			while (OnReadyBeanMethodDefinition.hasNext()) {
				BeanDefinition beanDefinition = dealDependence(OnReadyBeanMethodDefinition.next());		
				// pd 为ParameterDependence的对象
				pd.matchDependence(beanDefinition.getKlass());
			}
	}
	
	private static Object[] getParametersArray(Method method) {
		Parameter[] parameters = method.getParameters();
		Object[] objects = new Object[parameters.length];
		
		int index = 0;
		for (Parameter parameter : parameters) {
			// 通过参数类型从BeanPool中获取对象
			objects[index++] = getABean(parameter.getType().getName());
		}
		return objects;
	}
	
	private static BeanDefinition dealDependence(BeanMethodDefinition bmd) {
		Method method = bmd.getMethod();
		Object[] objects = getParametersArray(method);
		
		Object object = null;
		BeanDefinition beanDefinition = null;
		try {
			object = method.invoke(bmd.getObject(), objects);
			beanDefinition = new BeanDefinition();
			beanDefinition.setKlass(object.getClass());
			beanDefinition.setObject(object);
			
			beanPool.put(object.getClass().getName(), beanDefinition);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return beanDefinition;
	}
	
	private static void collectBeanMethod(Class<?> klass, Object object) {
		Method[] methods = klass.getDeclaredMethods();
		for (Method method : methods) {
			if (!method.isAnnotationPresent(Bean.class)) {
				continue;
			}
			BeanMethodDefinition bmd = new BeanMethodDefinition();
			bmd.setKlass(method.getReturnType());
			bmd.setObject(object);
			bmd.setMethod(method);
			
			pd.dealDependence(method, bmd);
		}
	}
	
	BeanDefinition getBeanDefinition(String klassName) {
		return beanPool.get(klassName);
	}
	
	BeanDefinition getBeanDefinition(Class<?> klass) {
		return getBeanDefinition(klass.getName());
	}
	
	private void injectProperties(BeanDefinition beanDefinition) throws RuntimeException {
		Class<?> klass = beanDefinition.getKlass();
		Object object = beanDefinition.getObject();
		
		beanDefinition.setInject(true);
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Autowired.class)) {
				continue;
			}
			field.setAccessible(true);
			Object value = getBean(field.getType());
			if (value == null) {
				throw new HasNoBeanException("类[" + klass.getName()
						+ "]的[" + field.getName()
						+ "]成员没有对应的Bean！");
			}
			try {
				field.set(object, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		beanDefinition.setInject(true);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getABean(String klassName) {
		return (T) beanPool.get(klassName).getObject();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String klassName) throws RuntimeException {
		if (firstGetBean == false) {
			firstGetBean = true;
			String str = pd.hasSurplus();
			if (!str.equals("")) {
				throw new HasSurplusBeanMethodException(str);
			}
		}
		
		BeanDefinition bd = getBeanDefinition(klassName);
		if (bd == null) {
			return null;
		}

		Object result = bd.getObject();
		if (!bd.isInject()) {
			injectProperties(bd);
		}
		
		return (T) result;
	}
	
	public <T> T getBean(Class<?> klass) throws RuntimeException {
		return getBean(klass.getName());
	}
	
}
