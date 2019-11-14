package com.mec.video.selector;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mec.net_framework.core.Client;
import com.mec.net_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

public class VideoSelectorView implements IMecView {
	private Client client;
	
	private JFrame jfrmVideoSelector;
	
	public VideoSelectorView() {
		initView();
	}

	@Override
	public void init() {
		jfrmVideoSelector = new JFrame("微易码视频系统 - 视频选择");
		jfrmVideoSelector.setSize(600, 400);
		jfrmVideoSelector.setLocationRelativeTo(null);
		jfrmVideoSelector.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jfrmVideoSelector.setLayout(new BorderLayout());
		
		JLabel jlblVieowSelectorTopic = new JLabel("视频选择", 0);
		jlblVieowSelectorTopic.setFont(topicFont);
		jlblVieowSelectorTopic.setForeground(topicColor);
		jfrmVideoSelector.add(jlblVieowSelectorTopic, "North");
	}
	
	public VideoSelectorView setClient(Client client) {
		this.client = client;
		this.client.setClientAction(new VideoSelectorAction());
		
		return this;
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealAction() {
		jfrmVideoSelector.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}

	@Override
	public void showView() {
		jfrmVideoSelector.setVisible(true);
	}
	
	public void exitView() {
		jfrmVideoSelector.dispose();
	}

	@Override
	public void closeView() {
		client.offline();
	}
	
	class VideoSelectorAction extends ClientActionAdapter {

		@Override
		public void serverAbnormalDrop() {
			ViewTool.showMessage(jfrmVideoSelector, "服务器异常宕机，服务停止！");
			exitView();
		}

		@Override
		public boolean confirmOffline() {
			int choice = ViewTool.choiceYesNo(jfrmVideoSelector, "是否确认下线？");
			return choice == JOptionPane.YES_OPTION;
		}

		@Override
		public void serverForcedown() {
			ViewTool.showMessage(jfrmVideoSelector, "服务器强制宕机，服务停止！");
			exitView();
		}

		@Override
		public void beGoneByServer() {
			ViewTool.showMessage(jfrmVideoSelector, "服务器强制本机下线，服务停止！");
			exitView();
		}

		@Override
		public void afterOffline() {
			exitView();
		}
	}

}
