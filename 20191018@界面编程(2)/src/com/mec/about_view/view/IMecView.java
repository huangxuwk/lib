package com.mec.about_view.view;

import java.awt.Color;
import java.awt.Font;

public interface IMecView {
	Font topicFont = new Font("微软雅黑", Font.BOLD, 30);
	Font normalFont = new Font("宋体", Font.PLAIN, 16);
	int normalSize = normalFont.getSize();
	Font smallFont = new Font("宋体", Font.PLAIN, 14);
	Font xsmallFont = new Font("宋体", Font.PLAIN, 12);
	
	Color topicColor = new Color(120, 240, 113);
	
	int PADDING = 5;
	
	/**
	 * 接口中要实现方法必须用 default 或 static 修饰
	 */
	 default void initView() {
		init();
		reinit();
		dealAction();
	}
	
	void init();
	void reinit();
	void dealAction();
	void showView();
	void closeView();
}

