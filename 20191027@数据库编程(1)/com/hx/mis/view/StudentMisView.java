package com.hx.mis.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mec.util.IMecView;

public class StudentMisView implements IMecView {
	
	private JFrame jfrmMainView;
	
	private JLabel jlblTopic;
	
	private JPanel jpnlButton;
	private JPanel jpnlList;
	private JPanel jpnlBaseInfo;
	
	private JTextField jtxtId;
	private JTextField jtxtStuId;
	private JTextField jtxtName;
	private JLabel jlblSexCaption;
	private JComboBox<String> jcmbNation;
	private JComboBox<String> jcmbNative;
	private JComboBox<String> jcmbSchool;
	private JComboBox<String> jcmbDepartment;
	private JComboBox<String> jcmbMajor;
	
//	private DefaultListModel<String> dlmStudentList;
//	private JList<String> jlstStudentList;
//	private JScrollPane jscpStudentList;
	
	private JButton jbtnExit;
	
	private static final int FRM_WIDTH = 600;
	private static final int FRM_HEIGHT = 363;
//	private static final double LEFT_WIDTH = 0.6;
	private static final int lblWidth = normalFont.getSize() * 4;
	private static final int normalFontHeight = normalFont.getSize();
	
	public StudentMisView() {
		initView();
	}

	@Override
	public void init() {
		jfrmMainView = new JFrame("ѧ����Ϣ����ϵͳ");
		jfrmMainView.setSize(FRM_WIDTH, FRM_HEIGHT);
		jfrmMainView.setLayout(new BorderLayout());
		jfrmMainView.setLocationRelativeTo(null);
		jfrmMainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jlblTopic = new JLabel("ѧ����Ϣ����ϵͳ", JLabel.CENTER);
		jlblTopic.setFont(topicFont);
		jlblTopic.setForeground(topicColor);
		jfrmMainView.add(jlblTopic, BorderLayout.NORTH);
		
		JLabel jlblWestSpace = new JLabel(" ");
		jlblWestSpace.setFont(normalFont);
		
		JLabel jlblEastSpace = new JLabel(" ");
		jlblEastSpace.setFont(normalFont);
		
		JPanel jpnlWest = new JPanel();
		jpnlWest.add(jlblWestSpace);
		jfrmMainView.add(jpnlWest, BorderLayout.WEST);
		
		JPanel jpnlEast = new JPanel();
		jpnlEast.add(jlblEastSpace);
		jfrmMainView.add(jpnlEast, BorderLayout.EAST);
		
		JPanel jpnlView = new JPanel();
		jpnlView.setLayout(new GridLayout(1, 2));
		jfrmMainView.add(jpnlView, BorderLayout.CENTER);
		
		// ѧ��������Ϣ������
		jpnlBaseInfo = new JPanel();
		jpnlBaseInfo.setLayout(null);
		jpnlView.add(jpnlBaseInfo);
		
		// ѧ���б�������
		jpnlList = new JPanel();
		jpnlList.setLayout(null);
		jpnlView.add(jpnlList);

		// ���ť������
		jpnlButton = new JPanel();
		jpnlButton.setLayout(new FlowLayout());
		jpnlButton.setBackground(Color.yellow);
		jfrmMainView.add(jpnlButton, BorderLayout.SOUTH);
		
		int txtWidth = normalFontHeight * 13;
		int txtHeight = normalFontHeight + 4;
		int lblHeight = normalFontHeight + 2;
		int top = 0;
		int left = lblWidth;
		
		JLabel jlblId = new JLabel("���֤��");
		jlblId.setFont(normalFont);
		jlblId.setBounds(0, 0, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblId);
		
		jtxtId = new JTextField();
		jtxtId.setFont(normalFont);
		jtxtId.setBounds(left, top+2, txtWidth, txtHeight);
		jpnlBaseInfo.add(jtxtId);
		
		top += lblHeight + PADDING;
		JLabel jlblStuId = new JLabel("ѧ    ��");
		jlblStuId.setFont(normalFont);
		jlblStuId.setBounds(0, top, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblStuId);
		
		jtxtStuId = new JTextField();
		jtxtStuId.setFont(normalFont);
		jtxtStuId.setBounds(left, top+2, txtWidth, txtHeight);
		jpnlBaseInfo.add(jtxtStuId);
		
		top += lblHeight + PADDING;
		JLabel jlblName = new JLabel("��    ��");
		jlblName.setFont(normalFont);
		jlblName.setBounds(0, top, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblName);
		
		jtxtName = new JTextField();
		jtxtName.setFont(normalFont);
		jtxtName.setBounds(left, top+2, txtWidth, txtHeight);
		jpnlBaseInfo.add(jtxtName);
		
		top += lblHeight + PADDING;
		JLabel jlblSex = new JLabel("��    ��");
		jlblSex.setFont(normalFont);
		jlblSex.setBounds(0, top, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblSex);
		
		jlblSexCaption = new JLabel("", JLabel.CENTER);
		jlblSexCaption.setFont(normalFont);
		jlblSexCaption.setBounds(left, top+2, txtWidth, txtHeight);
		jpnlBaseInfo.add(jlblSexCaption);
		
		top += lblHeight + PADDING;
		JLabel jlblNative = new JLabel("��    ��");
		jlblNative.setFont(normalFont);
		jlblNative.setBounds(0, top, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblNative);
		
		jcmbNative = new JComboBox<>();
		jcmbNative.setFont(normalFont);
		jcmbNative.setBounds(left, top-1, txtWidth, txtHeight+2);
		jpnlBaseInfo.add(jcmbNative);
		
		top += lblHeight + PADDING;
		JLabel jlblNation = new JLabel("��    ��");
		jlblNation.setFont(normalFont);
		jlblNation.setBounds(0, top, lblWidth, lblHeight);
		jpnlBaseInfo.add(jlblNation);
		
		jcmbNation = new JComboBox<>();
		jcmbNation.setFont(normalFont);
		jcmbNation.setBounds(left, top-1, txtWidth, txtHeight+2);
		jpnlBaseInfo.add(jcmbNation);
		
		top += lblHeight + PADDING;
		JLabel jlblSchool = new JLabel("Ժ    У");
		jlblSchool.setFont(normalFont);
		jlblSchool.setBounds(0, top, lblWidth,  lblHeight);
		jpnlBaseInfo.add(jlblSchool);
		
		jcmbSchool = new JComboBox<>();
		jcmbSchool.setFont(normalFont);
		jcmbSchool.setBounds(left, top-1, txtWidth, txtHeight+2);
		jpnlBaseInfo.add(jcmbSchool);
		
		top += lblHeight + PADDING;
		JLabel jlblDepartment = new JLabel("Ժ    ϵ");
		jlblDepartment.setFont(normalFont);
		jlblDepartment.setBounds(0, top, lblWidth,  lblHeight);
		jpnlBaseInfo.add(jlblDepartment);
		
		jcmbDepartment = new JComboBox<>();
		jcmbDepartment.setFont(normalFont);
		jcmbDepartment.setBounds(left, top-1, txtWidth, txtHeight+2);
		jpnlBaseInfo.add(jcmbDepartment);
		
		top += lblHeight + PADDING;
		JLabel jlblMajor = new JLabel("ר    ҵ");
		jlblMajor.setFont(normalFont);
		jlblMajor.setBounds(0, top, lblWidth,  lblHeight);
		jpnlBaseInfo.add(jlblMajor);
		
		jcmbMajor = new JComboBox<>();
		jcmbMajor.setFont(normalFont);
		jcmbMajor.setBounds(left, top-1, txtWidth, txtHeight+2);
		jpnlBaseInfo.add(jcmbMajor);
//		
//		TitledBorder ttbdStudentList = new TitledBorder("ѧ���б�");
//		ttbdStudentList.setTitleFont(normalFont);
//		ttbdStudentList.setTitlePosition(TitledBorder.ABOVE_TOP);
//		ttbdStudentList.setTitleJustification(TitledBorder.CENTER);
//		
//		dlmStudentList = new DefaultListModel<>();
//		jlstStudentList = new JList<>(dlmStudentList);
//		jlstStudentList.setFont(normalFont);
//		jscpStudentList = new JScrollPane(jlstStudentList);
//		jscpStudentList.setBorder(ttbdStudentList);
//		jscpStudentList.setBounds(0, 0, 0, 0);
//		jpnlList.add(jscpStudentList);
//		
		jbtnExit = new JButton("�˳�");
		jbtnExit.setFont(btnFont);
		jpnlButton.add(jbtnExit);
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealAction() {
		jfrmMainView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}

	@Override
	public void showView() {
		jfrmMainView.setVisible(true);
	}

	@Override
	public void closeView() {
		jfrmMainView.dispose();
	}
	
}
