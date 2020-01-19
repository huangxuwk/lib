package com.dl.client.core;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swing.util.FontAndColor;

public class ClientDialog extends FontAndColor {
	private Dialog dialog;
	
	public ClientDialog() {
	}
	
	public void showMessageDialog(Frame owner, String title, String string) {
		dialog = new Dialog(owner, title, false);
		dialog.setSize(300, 180);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(owner);
		dialog.setModal(true);
		
		JPanel jpnlDlg = new JPanel(new BorderLayout());
		jpnlDlg.setBackground(backColor);
		jpnlDlg.setVisible(true);
		JLabel jlblDlg = new JLabel(string, JLabel.CENTER);
		jlblDlg.setFont(normalFont);
		jlblDlg.setForeground(fontColor);
		jpnlDlg.add(jlblDlg, BorderLayout.CENTER);
		dialog.add(jpnlDlg);
		dialog.setVisible(true);
	}
	
	public void close() {
		dialog.dispose();
	}
}
