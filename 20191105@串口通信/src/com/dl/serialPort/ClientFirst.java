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
	
	private SerialPort serialPort = null;	//保存串口对象
	
	private List<String> commList;
	
	public ClientFirst() {
		commList = SerialTool.findPort();
		lineChart = new LineChart(this);
		initSwing();
	}
	
	@Override
	public void init() {
		jfrmMain = new JFrame("51单片机温湿度测控");
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
		TitledBorder ttbdLog = new TitledBorder("温湿度测控信息显示栏");
		ttbdLog.setTitleFont(textFont);
		ttbdLog.setTitleColor(fontColor);
		ttbdLog.setTitleJustification(TitledBorder.CENTER);
		jscpLog.setBorder(ttbdLog);
		
		jfrmMain.add(jscpLog, BorderLayout.CENTER);
		
		JPanel jpnlSouth = new JPanel(new FlowLayout());
		jpnlSouth.setOpaque(false);
		jfrmMain.add(jpnlSouth, BorderLayout.SOUTH);
		
		btnBegin = new JButton("开始");
		btnBegin.setFont(normalFont);
		btnBegin.setBackground(backColor);;
		btnBegin.setForeground(fontColor);
		btnBegin.setOpaque(false);
		jpnlSouth.add(btnBegin);
		
		btnClear = new JButton("清空");
		btnClear.setFont(normalFont);
		btnClear.setBackground(backColor);
		btnClear.setForeground(fontColor);
		btnClear.setOpaque(false);
		jpnlSouth.add(btnClear);
		
		btnStop = new JButton("停止");
		btnStop.setFont(normalFont);
		btnStop.setBackground(backColor);
		btnStop.setForeground(fontColor);
		btnStop.setOpaque(false);
		jpnlSouth.add(btnStop);
		
		btnShow = new JButton("图示");
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
		
		//获取串口名称
		if (!commList.isEmpty()) {
			String commName = commList.get(0);
			if (commName != null && !commName.equals("")) {
				DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "提示", "串口["+ commName +"]已连接!");
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
				}.showComfigDialog(jfrmMain, jfrmMain, "提示", "是否关闭系统？");
			}
		});
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commList.isEmpty()) {
					commList = SerialTool.findPort();
					if (commList.isEmpty()) {
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", "未搜索到串口！");
						return;
					}
				}
				//获取串口名称
				String commName = commList.get(0);		
				
				//检查串口名称是否获取正确
				if (commName == null || commName.equals("")) {
					DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", "未搜索到串口！");
				} else {
						//串口名、波特率均获取正确时
					int bps = 9600;
					try {
						//获取指定端口名及波特率的串口对象
						serialPort = SerialTool.openPort(commName, bps);
						//在该串口对象上添加监听器
						SerialTool.addListener(serialPort, new SerialListener());
						//监听成功进行提示
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "提示", "监听成功！");
					} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse | TooManyListeners e1) {
						//发生错误时使用一个Dialog提示具体的错误信息
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", String.valueOf(e1));
					}
				}
			}
		});
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SerialTool.closePort(serialPort);
				DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "提示", "串口通信已关闭");
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
	    // 将 Calendar 类型转换成 Date 类型
	    Date tasktime = cale.getTime();
	    // 设置日期输出的格式
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jtxtLog.append(df.format(tasktime) + "  "+ message);
		jtxtLog.setCaretPosition(jtxtLog.getText().length());
		
		addTemMessage(type, temp);
		addHumMessage(type, hum);
	}
	
	private class SerialListener implements SerialPortEventListener {
		
		/**
		 * 处理监控到的串口事件
		 */
	    public void serialEvent(SerialPortEvent serialPortEvent) {
	        switch (serialPortEvent.getEventType()) {

	            case SerialPortEvent.BI: // 10 通讯中断
	            	DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", "与串口设备通讯中断");
	            	break;
	            case SerialPortEvent.OE: // 7 溢位（溢出）错误

	            case SerialPortEvent.FE: // 9 帧错误

	            case SerialPortEvent.PE: // 8 奇偶校验错误

	            case SerialPortEvent.CD: // 6 载波检测

	            case SerialPortEvent.CTS: // 3 清除待发送数据

	            case SerialPortEvent.DSR: // 4 待发送数据准备好了

	            case SerialPortEvent.RI: // 5 振铃指示

	            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
	            	break;
	            
	            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
	            	
	            	//System.out.println("found data");
					byte[] data = null;
					
					try {
						if (serialPort == null) {
							DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", "串口对象为空！");
						}
						else {
							data = SerialTool.readFromPort(serialPort);	//读取数据，存入字节数组
							//自定义解析过程
							if (data == null || data.length < 1) {	//检查数据是否读取正确
								DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", "数据错误！");
								closeView();
							}	else {
								String dataOriginal = new String(data);	//将字节数组数据转换位为保存了原始数据的字符串
								dealMessage(dataOriginal);
							}
						}						
					} catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
						DialogOwner.showMessageDialog(jfrmMain, jfrmMain, "错误", String.valueOf(e));
						closeView();	//发生读取错误时显示错误信息后退出系统
					}	
					break;
	        }
	    }
	}
	
}

