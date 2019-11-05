package com.mec.test_cs.client.view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mec.cs_framework.core.Client;
import com.mec.cs_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

public class LoginView implements IMecView {
	private Client client;
	
	private JFrame jfrmLogin;
	private JLabel jlblRegistry;
	private JButton jbtnLogin;
	private JTextField jtxtUserName;
	private JPasswordField jpswPassword;

	public LoginView() {
	}
	
	public void setClient(Client client) {
		this.client = client;
		this.client.setClientAction(new LoginAction());
		
		initView();
	}
	
	@Override
	public void init() {
		jfrmLogin = new JFrame("用户登录");
		jfrmLogin.setLayout(new BorderLayout());
		jfrmLogin.setSize(300, 200);
		jfrmLogin.setLocationRelativeTo(null);
		jfrmLogin.setResizable(false);
		jfrmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel jlblTopic = new JLabel("用户登录", 0);
		jlblTopic.setFont(topicFont);
		jlblTopic.setForeground(topicColor);
		jfrmLogin.add(jlblTopic, BorderLayout.NORTH);
		
		JPanel jpnlContxt = new JPanel();
		jpnlContxt.setLayout(null);
		jfrmLogin.add(jpnlContxt, BorderLayout.CENTER);
		
		int normalSize = normalFont.getSize();
		int left = 8*PADDING;
		int top = 5*PADDING;
		int lblWidth = 2 * normalSize;
		int lblHeight = normalSize + 2;
		JLabel jlblUserName = new JLabel("账号");
		jlblUserName.setFont(normalFont);
		jlblUserName.setBounds(left, top, lblWidth, lblHeight);
		jpnlContxt.add(jlblUserName);
		
		jtxtUserName = new JTextField();
		jtxtUserName.setFont(normalFont);
		jtxtUserName.setBounds(left + lblWidth + PADDING, top, 11*normalSize, lblHeight+4);
		jpnlContxt.add(jtxtUserName);
		
		top += lblHeight + 3*PADDING;
		JLabel jlblPassword = new JLabel("密码");
		jlblPassword.setFont(normalFont);
		jlblPassword.setBounds(left, top, lblWidth, lblHeight);
		jpnlContxt.add(jlblPassword);
		
		jpswPassword = new JPasswordField();
		jpswPassword.setFont(normalFont);
		jpswPassword.setBounds(left + lblWidth + PADDING, top, 11*normalSize, lblHeight + 4);
		jpnlContxt.add(jpswPassword);
		
		JPanel jpnlButton = new JPanel();
		jpnlButton.setLayout(new FlowLayout());
		jfrmLogin.add(jpnlButton, BorderLayout.SOUTH);
		
		jlblRegistry = new JLabel("注册");
		jlblRegistry.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jlblRegistry.setForeground(new Color(5,98,44));
		jlblRegistry.setCursor(cursorHand);
		jpnlButton.add(jlblRegistry);
		
		JLabel jlblBlank = new JLabel("      ");
		jlblBlank.setFont(normalFont);
		jpnlButton.add(jlblBlank);
		
		jbtnLogin = new JButton("登录");
		jbtnLogin.setFont(btnFont);
		jpnlButton.add(jbtnLogin);
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
	}

	@Override
	public void showView() {
		jfrmLogin.setVisible(true);
	}

	private void exitView() {
		jfrmLogin.dispose();
	}
	
	@Override
	public void closeView() {
		client.offline();
	}
	
	class LoginAction extends ClientActionAdapter {
		public LoginAction() {
			super();
		}

		@Override
		public void serverAbnormalDrop() {
			ViewTool.showMessage(jfrmLogin, "服务器异常宕机，访问停止！");
			exitView();
		}

		@Override
		public boolean confirmOffline() {
			int choice = ViewTool.choiceYesNo(jfrmLogin, "亲，真要狠心下线嚒！");
			return choice == JOptionPane.YES_OPTION;
		}

		@Override
		public void afterOffline() {
			exitView();
		}
		
	}

}
