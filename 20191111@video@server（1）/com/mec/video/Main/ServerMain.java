package com.mec.video.Main;

import com.mec.util.SpringContextHolder;
import com.mec.video.view.MecVideoServerMainView;

public class ServerMain {

	public static void main(String[] args) {
		SpringContextHolder holder = new SpringContextHolder("spring_config.xml");
		
		MecVideoServerMainView view = holder.getBean(MecVideoServerMainView.class);
		view.showView();
	}

}
