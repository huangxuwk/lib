package com.mec.video.Main;

import com.mec.util.SpringContextHolder;
import com.mec.video.connect.MecVideoClientConnectServer;
import com.mec.video.model.StudentVideoModel;
import com.mec.video.selector.VideoSelectorView;

public class ClientMain {

	public static void main(String[] args) {
//		SpringContextHolder holder = new SpringContextHolder("spring_config.xml");
		
//		MecVideoClientConnectServer connectView = holder.getBean(MecVideoClientConnectServer.class);
//		connectView.showView();
		new VideoSelectorView(new StudentVideoModel(
				"201410001006", "白黄九日", "", null, false)).showView();
	}

}
