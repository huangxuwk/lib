package com.mec.test_cs.client.view.connection;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mec.cs_framework.core.Client;
import com.mec.cs_framework.core.ClientActionAdapter;
import com.mec.test_cs.client.view.login.LoginView;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

public class ConnectionView implements IMecView {
	private Client client;
	private JFrame jfrmConnect;
	private JLabel jlblConnectTooltip;
	private int count;

	public ConnectionView() {
		this.client = new Client();
		this.client.setIp("192.168.1.2");	// "localhost"	"127.0.0.1"
		this.client.setPort(54188);
		this.client.setClientAction(new ConnectAction());
		
		count = 1;
		
		initView();
	}
	
	@Override
	public void init() {
		jfrmConnect = new JFrame("连接服务器");
		jfrmConnect.setSize(400, 200);
		jfrmConnect.setLocationRelativeTo(null);
		jfrmConnect.setLayout(new BorderLayout());
		jfrmConnect.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jfrmConnect.setResizable(false);
		
		JLabel jlblTopic = new JLabel("连接服务器", JLabel.CENTER);
		jlblTopic.setForeground(topicColor);
		jlblTopic.setFont(topicFont);
		jfrmConnect.add(jlblTopic, BorderLayout.NORTH);
		
		JPanel jpnlBody = new JPanel();
		jpnlBody.setLayout(new BorderLayout());
		jfrmConnect.add(jpnlBody, BorderLayout.CENTER);
		
		jlblConnectTooltip = new JLabel("第" + count + "次连接服务器……", 0);
		jlblConnectTooltip.setForeground(Color.red);
		jlblConnectTooltip.setFont(normalFont);
		jpnlBody.add(jlblConnectTooltip, BorderLayout.CENTER);
	}

	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
	}

	@Override
	public void showView() {
		jfrmConnect.setVisible(true);
		client.connectionToServer();
	}

	@Override
	public void closeView() {
		jfrmConnect.dispose();
	}
	
	public class ConnectAction extends ClientActionAdapter {
		public ConnectAction() {
			super();
		}

		@Override
		public void refuseOnline(String reason) {
			ViewTool.showMessage(jfrmConnect, "服务器拒绝上线，原因：" + reason);
			closeView();
		}

		@Override
		public void afterConnectError() {
			int choice = ViewTool.choiceYesNo(jfrmConnect, 
					"连接服务器失败，是否继续连接?");
			if (choice == JOptionPane.YES_OPTION) {
				jlblConnectTooltip.setText("第" + ++count + "次连接服务器……");
				client.connectionToServer();
			} else {
				closeView();
			}
		}

		@Override
		public void successOnline() {
			// 应该关闭本界面，显示“登录”界面
			closeView();
			LoginView loginView = new LoginView();
			loginView.setClient(client);
			loginView.showView();
		}
	}

}
