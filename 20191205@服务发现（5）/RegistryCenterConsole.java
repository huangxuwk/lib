package com.mec.uddi.test.registry_center;

import java.util.Scanner;

import com.mec.uddi.registry.INetListener;
import com.mec.uddi.registry.RegistryCenter;
import com.mec.util.ThreadLooker;

public class RegistryCenterConsole implements INetListener {
	private RegistryCenter center;
	
	public RegistryCenterConsole() {
		this.center = new RegistryCenter();
		this.center.addListener(this);
	}
	
	public void dealCommand() {
		Scanner in = new Scanner(System.in);
		String command = null;
		boolean finished = false;
		
		while (!finished) {
			command = in.next();
			
			if (command.equalsIgnoreCase("exit")) {
				if (center.isStartup()) {
					System.out.println("注册中心未宕机！");
					continue;
				}
				finished = true;
			} else if (command.equalsIgnoreCase("startup")
					|| command.equalsIgnoreCase("st")) {
				center.startup();
				ThreadLooker.lookThreads();
			} else if (command.equalsIgnoreCase("shutdown")
					|| command.equalsIgnoreCase("sd")) {
				center.shutdown();
			}
		}
		
		in.close();
	}
	
	@Override
	public void dealMessage(String message) {
		System.out.println("服务器:" + message);
	}
	
}
