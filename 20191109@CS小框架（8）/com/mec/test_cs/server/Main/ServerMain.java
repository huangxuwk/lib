package com.mec.test_cs.server.Main;

import com.mec.cs_framework.action.ActionBeanFactory;
import com.mec.test_cs.server.view.ServerView;

public class ServerMain {

	public static void main(String[] args) {
		ActionBeanFactory.scanActionMapping("/action.mapping.xml");
		new ServerView().showView();
	}

}
