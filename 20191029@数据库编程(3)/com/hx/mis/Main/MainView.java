package com.hx.mis.Main;

import com.hx.mis.view.StudentMisView;
import com.util.PropertiesParser;

public class MainView {

	public static void main(String[] args) {
		PropertiesParser.loadProperties("/database.cfg.properties");
		new StudentMisView().showView();
	}

}
