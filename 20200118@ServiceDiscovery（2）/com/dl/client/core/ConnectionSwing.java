package com.dl.client.core;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swing.util.FontAndColor;
import com.swing.util.ISwing;

public class ConnectionSwing extends FontAndColor implements ISwing {
	public JFrame jfrmMain;
	private JLabel jlbl;
	
	public ConnectionSwing() {
		initSwing();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame();
		jfrmMain.setSize(450, 270);
		jfrmMain.setLocationRelativeTo(null);
		jfrmMain.setResizable(false);
		jfrmMain.setLayout(new BorderLayout());
		jfrmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel jpnl = new JPanel(new BorderLayout());
		jpnl.setBackground(backColor);
		jfrmMain.add(jpnl, BorderLayout.CENTER);
		
		jlbl = new JLabel("服务器连接中,请稍后！", 0);
		jlbl.setOpaque(false);
		jlbl.setForeground(fontColor);
		jlbl.setFont(normalFont);
		jpnl.add(jlbl, BorderLayout.CENTER);
	}

	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
	}

	@Override
	public void showView() {
		jfrmMain.setVisible(true);
	}

	@Override
	public void closeView() {
		jfrmMain.dispose();
	}
	
}
