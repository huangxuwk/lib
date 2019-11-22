package com.swing.util;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class DialogOwner extends FontAndColor {
	
	public DialogOwner() {
	}
	
	public void showComfigDialog(Frame jitlfrMain, Component component, String title, String string) {
		final Dialog dialog = new Dialog(jitlfrMain, title, false);
		dialog.setSize(300, 180);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(component);
		
		JPanel jpnlDlg = new JPanel(new BorderLayout());
		jpnlDlg.setBackground(backColor);
		JLabel jlblDlg = new JLabel(string, JLabel.CENTER);
		jlblDlg.setFont(normalFont);
		jlblDlg.setForeground(fontColor);
		JButton btnOk = new JButton("确定");
		btnOk.setBackground(Color.WHITE);
		btnOk.setFont(smallFont);
		btnOk.setForeground(fontColor);
		JButton btnCancel = new JButton("取消");
		btnCancel.setBackground(Color.white);
		btnCancel.setFont(smallFont);
		btnCancel.setForeground(fontColor);
		jpnlDlg.add(jlblDlg, BorderLayout.CENTER);
		JPanel jpnlChoice = new JPanel();
		jpnlChoice.setBackground(backColor);
		jpnlDlg.add(jpnlChoice, BorderLayout.SOUTH);
		jpnlChoice.add(btnOk);
		jpnlChoice.add(btnCancel);
		dialog.add(jpnlDlg);
		
		btnOk.requestFocus();
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				choose(true);
			}
		});
		btnOk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					dialog.dispose();
					choose(true);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				choose(false);
			}
		});
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
				choose(false);
			}
		});
		dialog.setVisible(true);
	}
	
	abstract public void choose(boolean ok);
	
	public static void showMessageDialog(Frame owner, Component component, String title, String string) {
		final Dialog dialog = new Dialog(owner, title, false);
		dialog.setSize(300, 180);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(component);
		
		JPanel jpnlDlg = new JPanel(new BorderLayout());
		jpnlDlg.setBackground(backColor);
		jpnlDlg.setVisible(true);
		JLabel jlblDlg = new JLabel(string, JLabel.CENTER);
		jlblDlg.setFont(normalFont);
		jlblDlg.setForeground(fontColor);
		JPanel jpnlBtn = new JPanel(new FlowLayout());
		jpnlBtn.setBackground(backColor);
		JButton btnOk = new JButton("确定");
		btnOk.setBackground(Color.WHITE);
		btnOk.setFont(smallFont);
		btnOk.setForeground(fontColor);
		jpnlBtn.add(btnOk);
		jpnlDlg.add(jlblDlg, BorderLayout.CENTER);
		jpnlDlg.add(jpnlBtn, BorderLayout.SOUTH);
		dialog.add(jpnlDlg);
		
		btnOk.requestFocus();
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		btnOk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					dialog.dispose();
				}
			}
		});
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
			}
		});
		dialog.setVisible(true);
	}
	
	public static void setLogo(JFrame frame, String picPath) {
		if(picPath == null || picPath.length() <= 0) {
			return;
		}
		URL url = Class.class.getResource(picPath);
		if(url == null) {
			return;
		}
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		if(image == null) {
			return;
		}
		frame.setIconImage(image);
	}
	
	public static void setPhoto(JLabel photo, String photoPath) {
		int photoWidth = photo.getWidth();
		int photoHeight = photo.getHeight();
		ImageIcon icon = new ImageIcon(new ImageIcon(photoPath)
				.getImage().getScaledInstance(photoWidth, photoHeight,
				Image.SCALE_FAST));
		photo.setIcon(icon);
	}
	
	public static void setLabelImage(JLabel jlbl, byte[] image) {
		int width = jlbl.getWidth();
		int height = jlbl.getHeight();
		ImageIcon icon = new ImageIcon(new ImageIcon(image)
				.getImage().getScaledInstance(width, height,
				Image.SCALE_FAST));
		jlbl.setIcon(icon);
	}
}
