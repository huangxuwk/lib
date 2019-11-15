package com.mec.video.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mec.net_framework.core.Client;
import com.mec.net_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;
import com.mec.video.action.IStudentAction;
import com.mec.video.config.IVideoData;
import com.mec.video.model.StudentVideoModel;
import com.mec.video.selector.VideoSelectorView;

public class MecVideoLoginView implements IMecView {
	private Client client;
	
	private JFrame jfrmLogin;
	private JTextField jtxtStuId;
	private JPasswordField jpswPassword;
	private JLabel jlblRegistry;
	private JButton jbtnLogin;
	
	public MecVideoLoginView(Client client) {
		this.client = client;
		this.client.setClientAction(new LoginAction());
	}

	@Override
	public void init() {
		jfrmLogin = new JFrame("微易码视频系统 - 登录");
		jfrmLogin.setSize(new Dimension(360, 250));
		jfrmLogin.setLocationRelativeTo(null);
		jfrmLogin.setLayout(new BorderLayout());
		jfrmLogin.setResizable(false);
		jfrmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ViewTool.setLogo(jfrmLogin, IVideoData.logoPath);
		
		JLabel jlblLoginTopic = new JLabel("微易码视频系统 - 登录", 0);
		jlblLoginTopic.setForeground(topicColor);
		jlblLoginTopic.setFont(topicFont);
		jfrmLogin.add(jlblLoginTopic, BorderLayout.NORTH);
		
		JPanel jpnlLogin = new JPanel(new BorderLayout());
		jfrmLogin.add(jpnlLogin, BorderLayout.CENTER);
		
		JLabel jlblLoginHead = new JLabel(" ");
		jlblLoginHead.setFont(topicFont);
		jpnlLogin.add(jlblLoginHead, BorderLayout.NORTH);
		
		JPanel jpnlLoginContext = new JPanel(new GridLayout(3, 0));
		jpnlLogin.add(jpnlLoginContext, BorderLayout.CENTER);
		
		JPanel jpnlStuId = new JPanel(new FlowLayout());
		jpnlLoginContext.add(jpnlStuId, 0);
		
		JPanel jpnlPassword = new JPanel(new FlowLayout());
		jpnlLoginContext.add(jpnlPassword, 1);
		
		JLabel jlblBlank1 = new JLabel(" ");
		jpnlLoginContext.add(jlblBlank1, 2);
		
		JLabel jlblStuId = new JLabel("学号 ");
		jlblStuId.setFont(normalFont);
		jpnlStuId.add(jlblStuId);

		jtxtStuId = new JTextField(20);
		jtxtStuId.setFont(normalFont);
		jpnlStuId.add(jtxtStuId);
		
		JLabel jlblPassword = new JLabel("密码 ");
		jlblPassword.setFont(normalFont);
		jpnlPassword.add(jlblPassword);

		jpswPassword = new JPasswordField(20);
		jpswPassword.setFont(normalFont);
		jpnlPassword.add(jpswPassword);
		
		JPanel jpnlFooter = new JPanel(new FlowLayout());
		jpnlLogin.add(jpnlFooter, BorderLayout.SOUTH);
		
		jlblRegistry = new JLabel(" 注册 ");
		jlblRegistry.setFont(normalFont);
		jlblRegistry.setForeground(Color.GRAY);
		jlblRegistry.setCursor(cursorHand);
		jpnlFooter.add(jlblRegistry, BorderLayout.SOUTH);
		
		jbtnLogin = new JButton(" 登录 ");
		jbtnLogin.setFont(btnFont);
		jpnlFooter.add(jbtnLogin);
	}

	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
		jfrmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
		
		jtxtStuId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtxtStuId.selectAll();
			}
		});
		
		jpswPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jpswPassword.setText("");
			}
		});
		
		jbtnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = jtxtStuId.getText().trim();
				char[] chPassword = jpswPassword.getPassword();
				String stPassword = String.valueOf(chPassword);
				stPassword = String.valueOf(stPassword.hashCode());
				
				IStudentAction studentAction = 
						client.getProxy(IStudentAction.class, jfrmLogin);
				StudentVideoModel stu = studentAction.getStudent(id, stPassword);
				
				if (stu.getId().equals("ERROR")) {
					ViewTool.showWarning(jfrmLogin, stu.getName());
					jpswPassword.setText("");
					jtxtStuId.selectAll();
					jtxtStuId.requestFocus();
					return;
				}
				exitView();
				new VideoSelectorView(stu).setClient(client).showView();
			}
		});
	}

	@Override
	public void showView() {
		jfrmLogin.setVisible(true);
	}

	public void exitView() {
		jfrmLogin.dispose();
	}
	
	@Override
	public void closeView() {
		client.offline();
	}
	
	public class LoginAction extends ClientActionAdapter {
	
		public LoginAction() {
		}

		@Override
		public void serverAbnormalDrop() {
			ViewTool.showMessage(jfrmLogin, "服务器异常宕机，服务停止！");
			exitView();
		}

		@Override
		public boolean confirmOffline() {
			int choice = ViewTool.choiceYesNo(jfrmLogin, "是否确认下线？");
			return choice == JOptionPane.YES_OPTION;
		}

		@Override
		public void serverForcedown() {
			ViewTool.showMessage(jfrmLogin, "服务器强制宕机，服务停止！");
			exitView();
		}

		@Override
		public void beGoneByServer() {
			ViewTool.showMessage(jfrmLogin, "服务器强制本机下线，服务停止！");
			exitView();
		}

		@Override
		public void afterOffline() {
			exitView();
		}

	}
	
}
