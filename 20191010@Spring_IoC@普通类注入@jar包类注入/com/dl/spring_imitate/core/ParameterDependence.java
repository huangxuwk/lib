 package com.dl.spring_imitate.core;

import java.lang.reflect.Method;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 此类处理bean注解的依赖关系
 * 1、形成map，保存依赖关系
 * 2、通过遍历已有的beanPool，匹配所有有关的依赖，处理map
 * 3、找到一个参数，则删除所有该键的值，并将每个BeanMethodDefinition中的count--
 * 4、当count为0时，则将该BeanMethodDefinition放入准备好的list中
 */
public class ParameterDependence {
	private static final Map<Class<?>, List<BeanMethodDefinition>> methodDefinitionMap;
	
	static {
		methodDefinitionMap = new HashMap<>();
	}
	
	void dealDependence(Method method, BeanMethodDefinition beanMethodDefinition) {
		Parameter[] parameters = method.getParameters();
		if (parameters.length == 0) {
			OnReadyBeanMethodDefinition.in(beanMethodDefinition);
		}
		for (Parameter parameter : parameters) {
			Class<?> klass = parameter.getType();
			if (!methodDefinitionMap.containsKey(klass)) {
				methodDefinitionMap.put(klass, new ArrayList<>());
			}
			methodDefinitionMap.get(klass).add(beanMethodDefinition);
		}
	}
	
	void checkAllBean(Set<String> sets) {
		Iterator<String> iterator = sets.iterator();
		while (iterator.hasNext()) {
			Class<?> klass = null;
			try {
				klass = Class.forName(iterator.next());
				matchDependence(klass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	void matchDependence(Class<?> klass) {
		List<BeanMethodDefinition> bmdList = methodDefinitionMap.get(klass);
		
		if (bmdList == null) {
			return;
		}
		// 遍历得到的list
		for (int index = 0; index < bmdList.size(); index++) {
			BeanMethodDefinition bmd = bmdList.get(index);
			// 将bmd中的parameterCount - 1，并判断是否为0，若为0，则该方法就绪
			if (bmd.decrease() == 0) {
				OnReadyBeanMethodDefinition.in(bmd);
			}
			// 将这个BeanMethodDefinition从list中删除
			bmdList.remove(bmd);
			if (bmdList.isEmpty()) {
				methodDefinitionMap.remove(klass);
			}
		}
	}
	
	String hasSurplus() {
		if (methodDefinitionMap.isEmpty()) {
			return "";
		}
		StringBuffer str = new StringBuffer();
		str.append("\n框架中有未处理的依赖关系！\n");
		Iterator<Class<?>> iterator = methodDefinitionMap.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> klass = iterator.next();
			List<BeanMethodDefinition> bmdList = methodDefinitionMap.get(klass);
			for (BeanMethodDefinition bmd : bmdList) {
				str.append(bmd.getMethod().getName()).append(" 有未注入的参数: ").append(klass.getSimpleName()).append("\n");
			}
		}
		
		return str.toString();
	}
	
}
