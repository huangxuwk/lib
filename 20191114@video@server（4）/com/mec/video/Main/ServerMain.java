package com.mec.video.Main;

import com.mec.rmi.core.RMIMethodFactory;
import com.mec.util.SpringContextHolder;
import com.mec.video.view.MecVideoServerMainView;

public class ServerMain {

	public static void main(String[] args) {
		SpringContextHolder holder = new SpringContextHolder("spring_config.xml");
		RMIMethodFactory rmiMethodFactory = new RMIMethodFactory();
		rmiMethodFactory.setHolder(holder);
		rmiMethodFactory.scanPackage("com.mec.video");
		
		MecVideoServerMainView view = holder.getBean(MecVideoServerMainView.class);
		view.showView();
	}

}
