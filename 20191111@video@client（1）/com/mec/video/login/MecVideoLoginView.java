package com.mec.video.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mec.net_framework.core.Client;
import com.mec.net_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

public class MecVideoLoginView implements IMecView {
	private Client client;
	
	private JFrame jfrmLogin;
	
	public MecVideoLoginView(Client client) {
		this.client = client;
		this.client.setClientAction(new LoginAction());
	}

	@Override
	public void init() {
		jfrmLogin = new JFrame("微易码视频系统 - 登录");
		jfrmLogin.setSize(new Dimension(400, 300));
		jfrmLogin.setLocationRelativeTo(null);
		jfrmLogin.setLayout(new BorderLayout());
		jfrmLogin.setResizable(false);
		jfrmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
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
