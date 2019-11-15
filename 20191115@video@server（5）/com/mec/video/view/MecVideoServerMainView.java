package com.mec.video.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.net_framework.core.INetListener;
import com.mec.net_framework.core.Server;
import com.mec.util.IMecView;
import com.mec.util.ViewTool;

@Component
public class MecVideoServerMainView implements IMecView, INetListener {
	@Autowired
	private Server server;
	
	private JFrame jfrmMain;
	private JTextArea jtatSystemMessage;
	private JTextField jtxtCommand;

	public MecVideoServerMainView() {
		initView();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame("微易码视频服务器监控端");
		jfrmMain.setMinimumSize(new Dimension(800, 600));
		jfrmMain.setLocationRelativeTo(null);
		jfrmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jfrmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jfrmMain.setLayout(new BorderLayout());
		ViewTool.setLogo(jfrmMain, "/res/MecLogo.gif");
		
		JLabel jlblTopic = new JLabel(" 微易码视频服务器-监控器 ", 0);
		jlblTopic.setForeground(topicColor);
		jlblTopic.setFont(topicFont);
		jfrmMain.add(jlblTopic, BorderLayout.NORTH);
		
		JLabel jlblLeftBlank = new JLabel("  ");
		jlblLeftBlank.setFont(normalFont);
		jfrmMain.add(jlblLeftBlank, BorderLayout.EAST);
		
		JLabel jlblRightBlank = new JLabel("  ");
		jlblRightBlank.setFont(normalFont);
		jfrmMain.add(jlblRightBlank, BorderLayout.WEST);
		
		jtatSystemMessage = new JTextArea();
		jtatSystemMessage.setFont(normalFont);
		jtatSystemMessage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		jfrmMain.add(jtatSystemMessage, BorderLayout.CENTER);
		
		JPanel jpnlCommand = new JPanel(new FlowLayout());
		jfrmMain.add(jpnlCommand, BorderLayout.SOUTH);
		
		JLabel jlblCommand = new JLabel("命令");
		jlblCommand.setFont(normalFont);
		jpnlCommand.add(jlblCommand);
		
		jtxtCommand = new JTextField(50);
		jtxtCommand.setFont(normalFont);
		jpnlCommand.add(jtxtCommand);
	}

	@Override
	public void reinit() {
		jtatSystemMessage.setEditable(false);
		jtatSystemMessage.setFocusable(false);
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
				processCommand(jtxtCommand.getText().trim());
				jtxtCommand.setText("");
			}
		});
	}
	
	private void processCommand(String command) {
		if (command.equalsIgnoreCase("startup")
				|| command.equalsIgnoreCase("st")) {
			server.startup();
		} else if (command.equalsIgnoreCase("shutdown")
				|| command.equalsIgnoreCase("sd")) {
			server.shutdown();
		} else if (command.equalsIgnoreCase("exit")
				|| command.equalsIgnoreCase("x")) {
			closeView();
		} else if (command.equalsIgnoreCase("forcedown")
				|| command.equalsIgnoreCase("fd")) {
			server.forcedown();
		}
	}

	@Override
	public void showView() {
		server.addListener(this);
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
	public void dealMessage(String message) {
		jtatSystemMessage.append(message + "\n");
		jtatSystemMessage.setCaretPosition(jtatSystemMessage.getText().length());
	}

}
