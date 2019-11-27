package com.dl.progress.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class TestForProgressBar implements ITestProgressBar {
	private JFrame jfrmView;
	private JProgressBar jpgbTest;
	
	public TestForProgressBar() {
		jfrmView = new JFrame("进度条试验");
		jfrmView.setSize(500, 300);
		jfrmView.setLayout(new BorderLayout());
		jfrmView.setLocationRelativeTo(null);
		jfrmView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jlblTopic = new JLabel("进度条试验", 0);
		jlblTopic.setFont(new Font("微软雅黑", Font.BOLD, 28));
		jfrmView.add(jlblTopic, "North");
		
		JPanel jpnlProgressBar = new JPanel(new FlowLayout());
		jfrmView.add(jpnlProgressBar);
		
		jpgbTest = new JProgressBar();
		jpnlProgressBar.add(jpgbTest);
		jpgbTest.setStringPainted(true);
		jpgbTest.setMaximum(100);
	}
	
	public void showView() {
		jfrmView.setVisible(true);
	}
	
	public void closeView() {
		jfrmView.dispose();
	}

	@Override
	public void change(int value, int maxValue) {
		int per = (int) (value / (double) maxValue * 100.0);
		String strPer = "已完成" + per + "%";
		jpgbTest.setValue(value);
		jpgbTest.setString(strPer);
	}
	
}
