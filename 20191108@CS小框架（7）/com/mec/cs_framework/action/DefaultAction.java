package com.mec.cs_framework.action;

import java.lang.reflect.Method;

public class DefaultAction implements IAction {

	@Override
	public Object doAction(String action, String para) throws Exception {
		ActionDefinition ad = ActionBeanFactory.getAction(action);
		if (ad == null) {
			throw new Exception("Action:()没有对应的类和方法！");
		}
		
		Object object = ad.getObject();
		Method method = ad.getMethod();
		Object result = method.invoke(object, new Object[] {});
		// 上面是无参调用method，对于带参的method必然失败！
		// 而method的参数所需要的值，来源于String para！
		
		return result;
	}

}
