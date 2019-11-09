package com.mec.test_cs.client.view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.mec.test_cs.server.model.Complex;
import com.mec.util.ArgumentMaker;
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
		
		jbtnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = jtxtUserName.getText().trim();
				String password = String.valueOf(jpswPassword.getPassword());
				client.sendRequest("login", new ArgumentMaker()
						.addArgument("userName", userName)
						.addArgument("password", password)
						.addArgument("other", new Complex(1.2, 3.4))
						.toJson());
			}
		});
		
		jlblRegistry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userName = jtxtUserName.getText().trim();
				String password = String.valueOf(jpswPassword.getPassword());
				client.sendRequest("getFriendList", userName + "," + password);
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
		public void dealResponse(String action, String result) {
			if (action.equals("login")) {
				if (result.equals("true")) {
					
				}
			} else if (action.equals("getFriendList")) {
				// TODO 解析result，获取所有朋友的信息
			}
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
