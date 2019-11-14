package com.mec.video.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.net_framework.core.Client;
import com.mec.net_framework.core.ClientActionAdapter;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;
import com.mec.video.login.MecVideoLoginView;

@Component
public class MecVideoClientConnectServer implements IMecView {
	private JFrame jfrmConnectView;
	private JLabel jlblConnectContext;
	private int count;
	
	@Autowired
	private Client client;

	public MecVideoClientConnectServer() {
		this.count = 1;
		initView();
	}
	
	@Override
	public void init() {
		jfrmConnectView = new JFrame("连接服务器");
		jfrmConnectView.setSize(400, 200);
		jfrmConnectView.setLayout(new BorderLayout());
		jfrmConnectView.setLocationRelativeTo(null);
		jfrmConnectView.setResizable(false);
		jfrmConnectView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel jlblConnectTopic = new JLabel("连接服务器", 0);
		jlblConnectTopic.setFont(topicFont);
		jlblConnectTopic.setForeground(topicColor);
		jfrmConnectView.add(jlblConnectTopic, "North");
		
		jlblConnectContext = new JLabel("", 0);
		jlblConnectContext.setForeground(new Color(3, 59, 8));
		jlblConnectContext.setFont(normalFont);
		jfrmConnectView.add(jlblConnectContext, "Center");
	}

	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
		jfrmConnectView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}

	@Override
	public void showView() {
		client.setClientAction(new ConnectServerAction());
		jfrmConnectView.setVisible(true);
		jlblConnectContext.setText("第" + this.count++ + "次连接服务器中……");
		while (!client.connectToServer()) {
			int choice = ViewTool.choiceYesNo(jfrmConnectView, 
					"连接服务器失败，是否继续连接？");
			if (choice == JOptionPane.YES_OPTION) {
				jlblConnectContext.setText("第" + this.count++ + "次连接服务器中……");
				continue;
			}
			closeView();
			break;
		}
	}

	@Override
	public void closeView() {
		jfrmConnectView.dispose();
	}
	
	class ConnectServerAction extends ClientActionAdapter {

		public ConnectServerAction() {
		}

		@Override
		public void outOfRoom() {
			ViewTool.showMessage(jfrmConnectView, "服务器已满，请稍后尝试连接！");
			closeView();
		}

		@Override
		public void connectSuccess() {
			MecVideoLoginView loginView = new MecVideoLoginView(client);
			loginView.initView();
			loginView.showView();
			closeView();
		}
		
	}

}
