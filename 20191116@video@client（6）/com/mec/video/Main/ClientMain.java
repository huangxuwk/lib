package com.mec.video.Main;

import com.mec.util.SpringContextHolder;
import com.mec.video.connect.MecVideoClientConnectServer;

public class ClientMain {

	public static void main(String[] args) {
		SpringContextHolder holder = new SpringContextHolder("spring_config.xml");
		
		MecVideoClientConnectServer connectView = holder.getBean(MecVideoClientConnectServer.class);
		connectView.showView();
	}

}
