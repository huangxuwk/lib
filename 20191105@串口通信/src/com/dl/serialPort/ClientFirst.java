package com.dl.serialPort;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.dl.serialException.NoSuchPort;
import com.dl.serialException.NotASerialPort;
import com.dl.serialException.PortInUse;
import com.dl.serialException.ReadDataFromSerialPortFailure;
import com.dl.serialException.SerialPortInputStreamCloseFailure;
import com.dl.serialException.SerialPortParameterFailure;
import com.dl.serialException.TooManyListeners;
import com.dl.swing.util.DialogOwner;
import com.dl.swing.util.FontAndColor;
import com.dl.swing.util.ISwing;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ClientFirst extends FontAndColor implements ISwing {
	private JFrame jfrmMain;
	private JTextArea jtxtLog;
	private JButton btnBegin;
	private JButton btnStop;
	private JButton btnClear;
	private JButton btnShow;
	
	private LineChart lineChart;
	
	private SerialPort serialPort = null;	//���洮�ڶ���
	
	private List<String> commList;
	
	public ClientFirst() {
		commList = SerialTool.findPort();
		lineChart = new LineChart(this);
		initSwing();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame("51��Ƭ����ʪ�Ȳ��");
		jfrmMain.setBounds(450, 100, 600, 600);
		jfrmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jfrmMain.setResizable(false);
		jfrmMain.setLayout(new BorderLayout());
		
		JLabel jlblWest = new JLabel("     ");
		jfrmMain.add(jlblWest, BorderLayout.WEST);
		JLabel jlblEast = new JLabel("     ");
		jfrmMain.add(jlblEast, BorderLayout.EAST);
		
		jtxtLog = new JTextArea();
		jtxtLog.setFont(normalFont);
		jtxtLog.setForeground(fontColor);
		jtxtLog.setBackground(backColor);
		JScrollPane jscpLog = new JScrollPane(jtxtLog);
		TitledBorder ttbdLog = new TitledBorder("��ʪ�Ȳ����Ϣ��ʾ��");
		ttbdLog.setTitleFont(textFont);
		ttbdLog.setTitleColor(fontColor);
		ttbdLog.setTitleJustification(TitledBorder.CENTER);
		jscpLog.setBorder(ttbdLog);
		
		jfrmMain.add(jscpLog, BorderLayout.CENTER);
		
		JPanel jpnlSouth = new JPanel(new FlowLayout());
		jpnlSouth.setOpaque(false);
		jfrmMain.add(jpnlSouth, BorderLayout.SOUTH);
		
		btnBegin = new JButton("��ʼ");
		btnBegin.setFont(normalFont);
		btnBegin.setBackground(backColor);;
		btnBegin.setForeground(fontColor);
		btnBegin.setOpaque(false);
		jpnlSouth.add(btnBegin);
		
		btnClear = new JButton("���");
		btnClear.setFont(normalFont);
		btnClear.setBackground(backColor);
		btnClear.setForeground(fontColor);
		btnClear.setOpaque(false);
		jpnlSouth.add(btnClear);
		
		btnStop = new JButton("ֹͣ");
		btnStop.setFont(normalFont);
		btnStop.setBackground(backColor);
		btnStop.setForeground(fontColor);
		btnStop.setOpaque(false);
		jpnlSouth.add(btnStop);
		
		btnShow = new JButton("ͼʾ");
		btnShow.setFont(normalFont);
		btnShow.setBackground(backColor);
		btnShow.setForeground(fontColor);
		btnShow.setOpaque(false);
		jpnlSouth.add(btnShow);
	}

	@Override
	public void reinit() {
		jtxtLog.setEditable(false);
		jtxtLog.setFocusable(false);
		
		//��ȡ��������
		if (!commList.isEmpty()) {
			String commName = commList.get(0);
			if (commName != null && !commName.equals("")) {
				DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "��ʾ", "����["+ commName +"]������!");
			}
		}
	}

	@Override
	public void dealAction() {
		jfrmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new DialogOwner() {
					@Override
					public void choose(boolean ok) {
						if (ok) {
							closeView();	
						}
					}
				}.showComfigDialog(jfrmMain, jfrmMain, "��ʾ", "�Ƿ�ر�ϵͳ��");
			}
		});
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commList.isEmpty()) {
					commList = SerialTool.findPort();
					if (commList.isEmpty()) {
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", "δ���������ڣ�");
						return;
					}
				}
				//��ȡ��������
				String commName = commList.get(0);		
				
				//��鴮�������Ƿ��ȡ��ȷ
				if (commName == null || commName.equals("")) {
					DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", "δ���������ڣ�");
				} else {
						//�������������ʾ���ȡ��ȷʱ
					int bps = 9600;
					try {
						//��ȡָ���˿����������ʵĴ��ڶ���
						serialPort = SerialTool.openPort(commName, bps);
						//�ڸô��ڶ�������Ӽ�����
						SerialTool.addListener(serialPort, new SerialListener());
						//�����ɹ�������ʾ
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "��ʾ", "�����ɹ���");
					} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse | TooManyListeners e1) {
						//��������ʱʹ��һ��Dialog��ʾ����Ĵ�����Ϣ
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", String.valueOf(e1));
					}
				}
			}
		});
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SerialTool.closePort(serialPort);
				DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "��ʾ", "����ͨ���ѹر�");
			}
		});
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				jtxtLog.setText("");
				addHumMessage("A", String.valueOf(2));
				addHumMessage("B", String.valueOf(3));
				addHumMessage("A", String.valueOf(2));
				addHumMessage("A", String.valueOf(2));
				addHumMessage("B", String.valueOf(3));
				addHumMessage("B", String.valueOf(3));
				addHumMessage("A", String.valueOf(2));
				addTemMessage("A", String.valueOf(2));
				addTemMessage("B", String.valueOf(3));
				addTemMessage("A", String.valueOf(2));
				addTemMessage("A", String.valueOf(2));
				addTemMessage("B", String.valueOf(3));
				addTemMessage("B", String.valueOf(3));
				addTemMessage("A", String.valueOf(2));
			}
		});
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lineChart.isShow()) {
					lineChart.closeView();
					setLocation(false);
				} else {
					setLocation(true);
					lineChart.showView();	
				}
			}
		});
	}
	
	void setLocation(boolean choice) {
		if (choice) {
			jfrmMain.setLocation(100, 100);
		} else {
			jfrmMain.setLocation(450, 100);
		}
	}
	
	public void addTemMessage(String type, String num) {
		lineChart.addDatasetTem(type, num);
	}
	
	public void addHumMessage(String type, String num) {
		lineChart.addDatasetHum(type, num);
	}

	@Override
	public void showView() {
		jfrmMain.setVisible(true);
	}

	@Override
	public void closeView() {
		lineChart.closeView();
		jfrmMain.dispose();
		SerialTool.closePort(serialPort);
	}
	
	private void dealMessage(String message) {
		String type = message.substring(0, 1);
		String temp =message.substring(6, 8);
		String hum = message.substring(13, 15);
		
	    Calendar cale = Calendar.getInstance();
	    // �� Calendar ����ת���� Date ����
	    Date tasktime = cale.getTime();
	    // ������������ĸ�ʽ
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jtxtLog.append(df.format(tasktime) + "  "+ message);
		jtxtLog.setCaretPosition(jtxtLog.getText().length());
		
		addTemMessage(type, temp);
		addHumMessage(type, hum);
	}
	
	private class SerialListener implements SerialPortEventListener {
		
		/**
		 * �����ص��Ĵ����¼�
		 */
	    public void serialEvent(SerialPortEvent serialPortEvent) {
	        switch (serialPortEvent.getEventType()) {

	            case SerialPortEvent.BI: // 10 ͨѶ�ж�
	            	DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", "�봮���豸ͨѶ�ж�");
	            	break;
	            case SerialPortEvent.OE: // 7 ��λ�����������

	            case SerialPortEvent.FE: // 9 ֡����

	            case SerialPortEvent.PE: // 8 ��żУ�����

	            case SerialPortEvent.CD: // 6 �ز����

	            case SerialPortEvent.CTS: // 3 �������������

	            case SerialPortEvent.DSR: // 4 ����������׼������

	            case SerialPortEvent.RI: // 5 ����ָʾ

	            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
	            	break;
	            
	            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ�������
	            	
	            	//System.out.println("found data");
					byte[] data = null;
					
					try {
						if (serialPort == null) {
							DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", "���ڶ���Ϊ�գ�");
						}
						else {
							data = SerialTool.readFromPort(serialPort);	//��ȡ���ݣ������ֽ�����
							//�Զ����������
							if (data == null || data.length < 1) {	//��������Ƿ��ȡ��ȷ
								DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", "���ݴ���");
								closeView();
							}	else {
								String dataOriginal = new String(data);	//���ֽ���������ת��λΪ������ԭʼ���ݵ��ַ���
								dealMessage(dataOriginal);
							}
						}						
					} catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "����", String.valueOf(e));
						closeView();	//������ȡ����ʱ��ʾ������Ϣ���˳�ϵͳ
					}	
					break;
	        }
	    }
	}
	
}

