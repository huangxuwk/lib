package com.mec.test_cs.server.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mec.cs_framework.core.INetListener;
import com.mec.cs_framework.core.Server;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

public class ServerView implements IMecView, INetListener {
	private Server server;
	private JFrame jfrmMain;
	private JTextArea jtatSystemInformation;
	private JTextField jtxtCommand;

	public ServerView() {
		server = new Server();
		server.addListener(this);
		
		initView();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame("大微易码C-S框架检测");
		jfrmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jfrmMain.setMinimumSize(new Dimension(800, 600));
		jfrmMain.setLocationRelativeTo(null);
		jfrmMain.setLayout(new BorderLayout());
		jfrmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel jlblTopic = new JLabel("大微易码C-S框架检测 - 服务器", JLabel.CENTER);
		jlblTopic.setFont(topicFont);
		jlblTopic.setForeground(topicColor);
		jfrmMain.add(jlblTopic, BorderLayout.NORTH);
		
		jtatSystemInformation = new JTextArea();
		jtatSystemInformation.setFont(normalFont);
		JScrollPane jscpSystemInformation = new JScrollPane(jtatSystemInformation);
		TitledBorder ttbdSystemInformation = new TitledBorder("系统信息");
		ttbdSystemInformation.setTitleFont(smallFont);
		ttbdSystemInformation.setTitleColor(Color.RED);
		ttbdSystemInformation.setTitleJustification(TitledBorder.CENTER);
		ttbdSystemInformation.setTitlePosition(TitledBorder.ABOVE_TOP);
		jscpSystemInformation.setBorder(ttbdSystemInformation);
		jfrmMain.add(jscpSystemInformation, BorderLayout.CENTER);
		
		JPanel jpnlCommand = new JPanel();
		jpnlCommand.setLayout(new FlowLayout());
		jfrmMain.add(jpnlCommand, BorderLayout.SOUTH);
		
		JLabel jlblCommand = new JLabel("命令");
		jlblCommand.setFont(normalFont);
		jpnlCommand.add(jlblCommand, BorderLayout.SOUTH);
		
		jtxtCommand = new JTextField(40);
		jtxtCommand.setFont(normalFont);
		jpnlCommand.add(jtxtCommand);
	}

	@Override
	public void reinit() {
		jtatSystemInformation.setEditable(false);
		jtatSystemInformation.setFocusable(false);
	}

	@Override
	public void dealAction() {
		jfrmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
		
		jtxtCommand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = jtxtCommand.getText().trim();
				dealCommand(command);
				jtxtCommand.setText("");
			}
		});
	}
	
	private void dealCommand(String command) {
		if (command.equalsIgnoreCase("startup")
				|| command.equalsIgnoreCase("st")
				|| command.equals("启动")) {
			server.startup();
		} else if (command.equalsIgnoreCase("shutdown")
				|| command.equalsIgnoreCase("sd")
				|| command.equals("宕机")) {
			server.shutdown();
		} else if (command.equalsIgnoreCase("exit")
				|| command.equalsIgnoreCase("x")
				|| command.equals("退出")) {
			closeView();
		}
	}

	@Override
	public void showView() {
		jfrmMain.setVisible(true);
		server.startup();
	}

	@Override
	public void closeView() {
		if (server.isStartup()) {
			ViewTool.showMessage(jfrmMain, "服务器尚未宕机！");
			return;
		}
		jfrmMain.dispose();
		// System.exit(0);
	}

	@Override
	public void parseMessage(String message) {
		jtatSystemInformation.append(message + "\n");
		jtatSystemInformation.setCaretPosition(
				jtatSystemInformation.getText().length());
	}
}
