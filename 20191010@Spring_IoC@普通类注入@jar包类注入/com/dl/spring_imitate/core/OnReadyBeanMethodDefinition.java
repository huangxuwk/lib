package com.dl.spring_imitate.core;

import java.util.ArrayList;
import java.util.List;

/*
 * 作用：保存所有可以处理的method，并且可以添加，取得
 */
public class OnReadyBeanMethodDefinition {
	private static final List<BeanMethodDefinition> beanMethodDefinitionList;
	
	static {
		beanMethodDefinitionList = new ArrayList<BeanMethodDefinition>();
	}
	
	public OnReadyBeanMethodDefinition() {
	}
	
	static void in(BeanMethodDefinition beanMethodDefinition) {
		if (beanMethodDefinition == null) {
			return;
		}
		beanMethodDefinitionList.add(beanMethodDefinition);
	}
	
	static boolean hasNext() {
		return !beanMethodDefinitionList.isEmpty();
	}
	
	static BeanMethodDefinition next() {
		return beanMethodDefinitionList.remove(0);
	}
 	
}
