package com.mec.uddi.registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mec.uddi.communication.NetMessage;

public class NetCommandProcesser {
	
	public NetCommandProcesser() {
	}

	public static void dealNetCommand(Object object, 
			CommunicationNode node, NetMessage netMessage) {
		String command = netMessage.getCommand().name().toLowerCase();
		String[] splitName = command.split("_");
		String methodName = "deal";
		for (String name : splitName) {
			methodName += name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		
		Class<?> klass = object.getClass();
		try {
			Method method = klass.getDeclaredMethod(methodName, new Class<?>[] {
				CommunicationNode.class,
				NetMessage.class
			});
			method.invoke(object, node, netMessage);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
