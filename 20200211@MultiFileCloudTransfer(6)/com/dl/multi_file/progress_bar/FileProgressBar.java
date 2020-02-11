package com.dl.multi_file.progress_bar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.swing.util.FontAndColor;
import com.swing.util.ISwing;

public class FileProgressBar extends FontAndColor implements ISwing {
	public static final int DEFAULT_BAR_PANE_WIDTH = 800;
	public static final int DEFAULT_BAR_PANE_HEIGHT = 105;
	public static final int DEFAULT_BAR_WIDTH = 750;
	public static final int DEFAULT_BAR_HEIGHT = 70;
	public static final int DEFAULT_MIN_PROGRESS = 0;
	public static final int DEFAULT_MAX_PROGRESS = 100;
	
	private JFrame jfrmMain;
	private JPanel jpnlMain;
	private boolean visible;

	private InnerProgressBar  innerProgressBar;

	public FileProgressBar() {
		initSwing();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame("�ļ����ս�������");
		jfrmMain.setSize(DEFAULT_BAR_PANE_WIDTH, DEFAULT_BAR_PANE_HEIGHT);
		jfrmMain.setLocationRelativeTo(null);
		jfrmMain.setResizable(false);
		jfrmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jpnlMain = new JPanel(new BorderLayout());
		jfrmMain.add(jpnlMain);
	}
	
	/**
	 * ��ʼ��
	 * @param fileNames
	 */
	public void initProgressBars(String fileName, int length) {
		innerProgressBar = new InnerProgressBar(length);
		innerProgressBar.initProgressBar(fileName);
	}
	
	/**
	 * �����ļ���ȥ���¶�Ӧ�Ľ��������ȣ�
	 * @param fileName
	 * @param value
	 */
	public void updata(int value) {
		if (!visible) {
			showView();
			visible = true;
		}
		if (innerProgressBar != null) {
			if (!innerProgressBar.isAdd) {
				jpnlMain.add(innerProgressBar.jpanel, BorderLayout.CENTER);
				innerProgressBar.isAdd = true;
			}
			if (innerProgressBar.updataProgress(value)) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				closeView();
			}
		}
	}
	
	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
		jfrmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}

	@Override
	public void showView() {
		jfrmMain.setVisible(true);
	}

	@Override
	public void closeView() {
		jfrmMain.dispose();
	}
	
	/**
	 * �ڲ���������<br>
	 * 1���ṩ��ʼ��һ���µĽ������ķ�����<br>
	 * 2���ṩ�ı���ȵķ�����
	 * @author dl
	 *
	 */
	class InnerProgressBar {
		private JPanel jpanel;
		private JProgressBar progressBar;

		private long length;
		private long curLen;
		private boolean isAdd;
		
		public InnerProgressBar(int length) {
			this.length = length;
		}
		
		/**
		 * �ڽ��������ܽ����ʼ��һ���µĽ�����
		 * @return
		 */
		public JPanel initProgressBar(String fileName) {
			jpanel = new JPanel(new BorderLayout());
			jpanel.setBackground(Color.white);
			jpanel.setSize(DEFAULT_BAR_PANE_WIDTH , DEFAULT_BAR_PANE_HEIGHT);
			UIManager.put("ProgressBar.selectionForeground",Color.BLACK);
			progressBar = new JProgressBar();
			progressBar.setSize(DEFAULT_BAR_PANE_WIDTH, DEFAULT_BAR_HEIGHT);
			progressBar.setMinimum(DEFAULT_MIN_PROGRESS);
			progressBar.setMaximum(DEFAULT_MAX_PROGRESS);
			progressBar.setStringPainted(true);
			progressBar.setBackground(Color.white);
			progressBar.setFont(smallFont);
			progressBar.setForeground(blueColor);
			TitledBorder ttbdInfo = new TitledBorder(" " + fileName + " - ���ս��� ");
			ttbdInfo.setTitleFont(smallFont);
			ttbdInfo.setTitleColor(fontColor);
			ttbdInfo.setTitleJustification(TitledBorder.CENTER);
			progressBar.setBorder(ttbdInfo);
			progressBar.setBorder(ttbdInfo);
			
			jpanel.add(progressBar, BorderLayout.CENTER);
			return jpanel;
		}
		
		public boolean updataProgress(int len) {
			curLen += len;
			int value = (int) ((100 * curLen) / length);
			progressBar.setValue(value);
			if (value == DEFAULT_MAX_PROGRESS) {
				return true;
			}
			return false;
		}
		
	}
	
}
