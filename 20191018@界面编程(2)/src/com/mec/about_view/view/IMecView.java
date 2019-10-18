package com.mec.about_view.view;

import java.awt.Color;
import java.awt.Font;

public interface IMecView {
	Font topicFont = new Font("΢���ź�", Font.BOLD, 30);
	Font normalFont = new Font("����", Font.PLAIN, 16);
	int normalSize = normalFont.getSize();
	Font smallFont = new Font("����", Font.PLAIN, 14);
	Font xsmallFont = new Font("����", Font.PLAIN, 12);
	
	Color topicColor = new Color(120, 240, 113);
	
	int PADDING = 5;
	
	/**
	 * �ӿ���Ҫʵ�ַ��������� default �� static ����
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

