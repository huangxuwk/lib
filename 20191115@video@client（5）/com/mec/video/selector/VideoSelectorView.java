package com.mec.video.selector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mec.net_framework.core.Client;
import com.mec.net_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;
import com.mec.video.config.IVideoData;
import com.mec.video.model.StudentVideoModel;

public class VideoSelectorView implements IMecView {
	public static final Font boldFont = new Font("黑体", Font.BOLD, 28);
	
	private Client client;
	private StudentVideoModel student;
	
	private JFrame jfrmVideoSelector;
	
	private JLabel jlblStudentPhoto;
	private JLabel jlblStudentName;
	private JLabel jlblStudentId;
	private JLabel jlblCourse;
	
	private JButton jbtnExit;
	
	public VideoSelectorView(StudentVideoModel student) {
		this.student = student;
		initView();
	}

	@Override
	public void init() {
		jfrmVideoSelector = new JFrame("微易码视频系统 - 视频选择");
		jfrmVideoSelector.setMinimumSize(new Dimension(840, 560));
		jfrmVideoSelector.setLocationRelativeTo(null);
		jfrmVideoSelector.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jfrmVideoSelector.setLayout(new BorderLayout());
		ViewTool.setLogo(jfrmVideoSelector, IVideoData.logoPath);
		
		// 标题区域
		JLabel jlblVieowSelectorTopic = new JLabel("视频选择", 0);
		jlblVieowSelectorTopic.setFont(topicFont);
		jlblVieowSelectorTopic.setForeground(topicColor);
		jfrmVideoSelector.add(jlblVieowSelectorTopic, "North");
		
		// 学生信息区域
		JPanel jpnlStudentInfo = new JPanel(new GridLayout(0, 1));
		jpnlStudentInfo.setBackground(Color.pink);
		jfrmVideoSelector.add(jpnlStudentInfo, "West");

		jlblStudentName = new JLabel("", 0);
		jlblStudentName.setFont(boldFont);
		jpnlStudentInfo.add(jlblStudentName);
		
		JPanel jpnlStudentPhoto = new JPanel();
		jpnlStudentPhoto.setLayout(null);
		jpnlStudentInfo.add(jpnlStudentPhoto);
		
		jlblStudentPhoto = new JLabel("", 0);
		jlblStudentPhoto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		jlblStudentPhoto.setSize(100, 130);
		jpnlStudentPhoto.add(jlblStudentPhoto);
		
		jlblStudentId = new JLabel(student.getId(), 0);
		jlblStudentId.setFont(normalFont);
		jpnlStudentInfo.add(jlblStudentId);
		
		// 按钮区域
		JPanel jpnlFooter = new JPanel(new BorderLayout());
		jfrmVideoSelector.add(jpnlFooter, "South");
		
		jbtnExit = new JButton("退出");
		jbtnExit.setFont(btnFont);
		jpnlFooter.add(jbtnExit, "East");
	}
	
	public VideoSelectorView setClient(Client client) {
		this.client = client;
		this.client.setClientAction(new VideoSelectorAction());
		
		return this;
	}

	@Override
	public void reinit() {
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
		jlblStudentName.setText(student.getName());
		ViewTool.setPhoto(jlblStudentPhoto, IVideoData.defaultPhotoPath);
		int parentWidth = jlblStudentPhoto.getParent().getWidth();
		jlblStudentPhoto.setLocation(
				(parentWidth - jlblStudentPhoto.getWidth()) / 2, 0);
	}
	
	public void exitView() {
		jfrmVideoSelector.dispose();
	}

	@Override
	public void closeView() {
		exitView();
		// client.offline();
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
