package com.hx.mis.Main;

import com.hx.mis.view.StudentMisView;
import com.mec.util.PropertiesParser;

public class MainView {

	public static void main(String[] args) {
		PropertiesParser.loadProperties("/interfaces.cfg.properties");

		new StudentMisView().showView();
	}

}
