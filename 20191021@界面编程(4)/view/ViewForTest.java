package com.mec.about_view.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ViewForTest implements IMecView {
	private JFrame jfrmMainView;
	private JTextField jtxtStuId;
	private JTextField jtxtName;
	private JPasswordField jpswPassword;
	
	private JLabel jlblPhoto;
	private JRadioButton jrdbSexMale;
	private JRadioButton jrdbSexFemale;
	private JComboBox<String> jcmbBirthYear;
	private JComboBox<String> jcmbBirthMonth;
	private JComboBox<String> jcmbBirthDate;
	private List<JCheckBox> jckbHobbies;
	private JTextArea jtatIntroduce;
	
	private DefaultListModel<String> dlmStudentList;
	private JList<String> jlstStudentList;
	
	private JButton jbtnHobbyAll;
	private JButton jbtnHobbyRev;
	private JButton jbtnHobbyCls;
	
	private JButton jbtnExit;
	private JButton jbtnAdd;
	private JButton jbtnModify;
	private JButton jbtnRemove;
	
	public static final String[] hobbyNames = {
			"˯��", "������", "��Ӿ", " ��ɽ", "�ô���", "����Ϸ", "�Ⱦ�"
	};
	public static final int minAge = 13;
	public static final int maxAge = 65;
	public static final int defaultAge = 18;
	
	public ViewForTest() {
		initView();
	}

	@Override
	public void init() {
		jfrmMainView = new JFrame("���ڿؼ���ѧϰ");
		jfrmMainView.setSize(610, 500);
		jfrmMainView.setLocationRelativeTo(null);
		jfrmMainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// ��Բ���ģʽ
		jfrmMainView.setLayout(new BorderLayout());
		// ���ɸı��С
		jfrmMainView.setResizable(false);
		
		JLabel jlblTopic = new JLabel("���ʴ�ѧ����Ϣ¼��", JLabel.CENTER);
		jlblTopic.setFont(topicFont);
		jlblTopic.setForeground(topicColor);
		jfrmMainView.add(jlblTopic, BorderLayout.NORTH);
		
		// �������ұ߿���͸����ǩ�ķ�ʽ
		JPanel jpnlLeft = new JPanel();
		jpnlLeft.add(new JLabel("      "));
		jfrmMainView.add(jpnlLeft, BorderLayout.WEST);

		JPanel jpnlRight = new JPanel();
		jpnlRight.add(new JLabel("      "));
		jfrmMainView.add(jpnlRight, BorderLayout.EAST);
		
		JPanel jpnlBody = new JPanel();
		// �������񲼾֣�һ������
		jpnlBody.setLayout(new GridLayout(1, 3));
//		jpnlBody.setBackground(topicColor);
		jfrmMainView.add(jpnlBody, BorderLayout.CENTER);
		
		JPanel jpnlEditor = new JPanel();
		jpnlEditor.setLayout(null);
		jpnlBody.add(jpnlEditor);
		
		int lblWidth = normalSize*4;
		int orgLeft = lblWidth;
		int top = PADDING;
		int rightWidth = 205;
		
		JLabel jlblStuId = new JLabel("ѧ    ��");
		jlblStuId.setFont(normalFont);
		jlblStuId.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblStuId);
		
		jtxtStuId = new JTextField();
		jtxtStuId.setFont(normalFont);
		jtxtStuId.setForeground(Color.RED);
		jtxtStuId.setBounds(orgLeft + PADDING, top, rightWidth - 80, normalSize + 4);
		jpnlEditor.add(jtxtStuId);
		
		jlblPhoto = new JLabel();
		jlblPhoto.setBackground(topicColor);
		jlblPhoto.setBounds(orgLeft + PADDING + rightWidth - 80, top, 80, 98);
		// ����Ƭ���ñ߿򣬻�ɫ��1 �����ؿ��
		jlblPhoto.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		jpnlEditor.add(jlblPhoto);
	
		top += normalSize + 4 + PADDING;
		JLabel jlblName = new JLabel("��    ��");
		jlblName.setFont(normalFont);
		jlblName.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblName);
		
		jtxtName = new JTextField();
		jtxtName.setFont(normalFont);
		jtxtName.setBounds(orgLeft + PADDING, top, rightWidth - 80, normalSize + 4);
		jpnlEditor.add(jtxtName);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblPassword = new JLabel("��    ��");
		jlblPassword.setFont(normalFont);
		jlblPassword.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblPassword);
		
		jpswPassword = new JPasswordField();
		jpswPassword.setFont(normalFont);
		jpswPassword.setBounds(orgLeft +PADDING, top, rightWidth - 80, normalSize + 4);
		jpnlEditor.add(jpswPassword);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblSex = new JLabel("��    ��");
		jlblSex.setFont(normalFont);
		jlblSex.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblSex);
		
		jrdbSexMale = new JRadioButton("��");
		jrdbSexMale.setFont(normalFont);
		jrdbSexMale.setBounds(orgLeft + PADDING, top, 50, normalSize);
		jpnlEditor.add(jrdbSexMale);

		jrdbSexFemale = new JRadioButton("Ů");
		jrdbSexFemale.setFont(normalFont);
		jrdbSexFemale.setBounds(orgLeft + PADDING + 50, top, 50, normalSize);
		jpnlEditor.add(jrdbSexFemale);
		
		// ���밴ť�飬��ֹ��ѡ�������ֶ�ѡ������
		ButtonGroup btgpSex = new ButtonGroup();
		btgpSex.add(jrdbSexMale);
		btgpSex.add(jrdbSexFemale);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblBirth = new JLabel("��������");
		jlblBirth.setFont(normalFont);
		jlblBirth.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblBirth);
		
		int deltaLeft = orgLeft + PADDING;
		jcmbBirthYear = new JComboBox<>();
		jcmbBirthYear.setFont(normalFont);
		jcmbBirthYear.setBounds(deltaLeft, top, normalSize*4, normalSize+4);
		jpnlEditor.add(jcmbBirthYear);
		
		deltaLeft += jcmbBirthYear.getWidth();
		JLabel jlblBirthYear = new JLabel("��");
		jlblBirthYear.setFont(normalFont);
		jlblBirthYear.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthYear);
		
		deltaLeft += jlblBirthYear.getWidth();
		jcmbBirthMonth = new JComboBox<>();
		jcmbBirthMonth.setFont(normalFont);
		jcmbBirthMonth.setBounds(deltaLeft, top, normalSize*3, normalSize+4);
		jpnlEditor.add(jcmbBirthMonth);
		
		deltaLeft += jcmbBirthMonth.getWidth();
		JLabel jlblBirthMonth = new JLabel("��");
		jlblBirthMonth.setFont(normalFont);
		jlblBirthMonth.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthMonth);
		
		deltaLeft += jlblBirthMonth.getWidth();
		jcmbBirthDate = new JComboBox<>();
		jcmbBirthDate.setFont(normalFont);
		jcmbBirthDate.setBounds(deltaLeft, top, normalSize*3, normalSize+4);
		jpnlEditor.add(jcmbBirthDate);
		
		deltaLeft += jcmbBirthDate.getWidth();
		JLabel jlblBirthDate = new JLabel("��");
		jlblBirthDate.setFont(normalFont);
		jlblBirthDate.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthDate);
		
		top += normalSize + 4 + PADDING;
		
		JPanel jpnlHobbies = new JPanel();
		jpnlHobbies.setBounds(0, top, orgLeft+PADDING+rightWidth, normalSize*8);
		TitledBorder ttbdHobbies = new TitledBorder("����");
		ttbdHobbies.setTitleFont(normalFont);
		ttbdHobbies.setTitlePosition(TitledBorder.ABOVE_TOP);
		ttbdHobbies.setTitleJustification(TitledBorder.CENTER);
		jpnlHobbies.setBorder(ttbdHobbies);
		jpnlEditor.add(jpnlHobbies);
		
		jckbHobbies = new ArrayList<>();
		for (int i = 0; i < hobbyNames.length; i++) {
			JCheckBox jckbHobby = new JCheckBox(hobbyNames[i]);
			jckbHobby.setFont(normalFont);
			jpnlHobbies.add(jckbHobby);
			jckbHobbies.add(jckbHobby);
		}
		
		top += jpnlHobbies.getHeight();		
		JPanel jpnlHobbyButton = new JPanel();
		jpnlHobbyButton.setSize(orgLeft+PADDING+rightWidth, normalSize*2);
		jpnlHobbyButton.setLocation(0, top);
		jpnlEditor.add(jpnlHobbyButton);
		
		jbtnHobbyAll = new JButton("ȫѡ");
		jbtnHobbyAll.setFont(xsmallFont);
		jpnlHobbyButton.add(jbtnHobbyAll);

		jbtnHobbyRev = new JButton("��ѡ");
		jbtnHobbyRev.setFont(xsmallFont);
		jpnlHobbyButton.add(jbtnHobbyRev);
		
		jbtnHobbyCls = new JButton("����");
		jbtnHobbyCls.setFont(xsmallFont);
		jpnlHobbyButton.add(jbtnHobbyCls);
		
		top += jpnlHobbyButton.getHeight() + PADDING;
		jtatIntroduce = new JTextArea();
		jtatIntroduce.setFont(normalFont);
		JScrollPane jscpIntroduce = new JScrollPane(jtatIntroduce);
		jscpIntroduce.setBounds(0, top, orgLeft+PADDING+rightWidth, 92);
		TitledBorder ttbdIntroduce = new TitledBorder("���");
		ttbdIntroduce.setTitleFont(normalFont);
		ttbdIntroduce.setTitlePosition(TitledBorder.ABOVE_TOP);
		ttbdIntroduce.setTitleJustification(TitledBorder.CENTER);
		jscpIntroduce.setBorder(ttbdIntroduce);
		jpnlEditor.add(jscpIntroduce);
		
		dlmStudentList = new DefaultListModel<>();
		jlstStudentList = new JList<>(dlmStudentList);
		jlstStudentList.setFont(normalFont);
		JScrollPane jscpStudentList = new JScrollPane(jlstStudentList);
		TitledBorder ttbdListor = new TitledBorder("ѧ����Ϣ�б���");
		ttbdListor.setTitleFont(smallFont);
		ttbdListor.setTitlePosition(TitledBorder.ABOVE_TOP);
		ttbdListor.setTitleJustification(TitledBorder.CENTER);
		jscpStudentList.setBorder(ttbdListor);
		jpnlBody.add(jscpStudentList);
		
//		JPanel jpnlListor = new JPanel();
//		TitledBorder ttbdListor = new TitledBorder("ѧ����Ϣ�б���");
//		ttbdListor.setTitleFont(normalFont);
//		ttbdListor.setTitlePosition(TitledBorder.TOP);
//		ttbdListor.setTitleJustification(TitledBorder.CENTER);
//		jpnlListor.setBorder(ttbdListor);
//		jpnlBody.add(jpnlListor);
	
		JPanel jpnlFooter = new JPanel(new GridLayout(1, 2));
//		jpnlFooter.setLayout(new GridLayout(1, 0));
		jfrmMainView.add(jpnlFooter, BorderLayout.SOUTH);
		JPanel jpnlFooterLeft = new JPanel();
		jpnlFooter.add(jpnlFooterLeft);
		JPanel jpnlFooterRight = new JPanel();
		jpnlFooter.add(jpnlFooterRight);
		
		jbtnAdd = new JButton("���");
		jbtnAdd.setFont(smallFont);
		jpnlFooterLeft.add(jbtnAdd);

		jbtnModify = new JButton("�޸�");
		jbtnModify.setFont(smallFont);
		jpnlFooterLeft.add(jbtnModify);
		
		jbtnRemove = new JButton("ɾ��");
		jbtnRemove.setFont(smallFont);
		jpnlFooterLeft.add(jbtnRemove);
		
		jbtnExit = new JButton("�˳�");
		jbtnExit.setFont(smallFont);
		jpnlFooterRight.add(jbtnExit);
	}

	@Override
	public void reinit() {
		jtxtStuId.setEditable(false);
		jtxtStuId.setText(getStuIdPre4());
		jtxtStuId.setFocusable(false);
		jrdbSexMale.setSelected(true);
		
		initBirthYear();
		for (int i = 1; i <= 12; i++) {
			jcmbBirthMonth.addItem((i+100+"").substring(1));
		}
		jcmbBirthMonth.setSelectedItem(0);
		
		initBirthDate();
		setPhoto(jlblPhoto, "./bin/lib/default.jpg");
		
		setButtonStatus();
		
		jtxtName.requestFocus();
	}

	private void setButtonStatus() {
		boolean enabled = dlmStudentList.size() > 0;
		
		jbtnModify.setEnabled(enabled);
		jbtnRemove.setEnabled(enabled);
	}

	private String getStuIdPre4() {
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		
		return "03" + year % 100 + "1";
	}
	
	private void initBirthDate() {
		int year = Integer.valueOf((String) jcmbBirthYear.getSelectedItem());
		int month = Integer.valueOf((String) jcmbBirthMonth.getSelectedItem());
		
		Calendar today = Calendar.getInstance();
		today.set(year, month, 0);
		int lastDate = today.get(Calendar.DATE);

		jcmbBirthDate.removeAllItems();
		for (int i = 1; i <= lastDate; i++) {
			jcmbBirthDate.addItem((i+100+"").substring(1));
		}
	}
	
	private void initBirthYear() {
		Calendar today = Calendar.getInstance();
		int nowYear = today.get(Calendar.YEAR);
		int firstYear = nowYear - maxAge;
		int lastYear = nowYear - minAge;
		int defaultYear = nowYear - defaultAge;
		
		for (int i = firstYear; i <= lastYear; i++) {
			String context = i + "";
			jcmbBirthYear.addItem(context);
			if (i == defaultYear) {
				jcmbBirthYear.setSelectedItem(context);
			}
		}
	}
	
	private void setPhoto(JLabel photo, String photoPath) {
		int photoWidth = photo.getWidth();
		int photoHeight = photo.getHeight();
		
		ImageIcon icon = new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(
				photoWidth, photoHeight, Image.SCALE_FAST));
		photo.setIcon(icon);
	}
	
	@Override
	public void dealAction() {
		jfrmMainView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
		
		jbtnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeView();
			}
		});
		
		jbtnHobbyAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setHobbySelected(true);
			}
		});
		
		jbtnHobbyCls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setHobbySelected(false);
			}
		});
		
		jbtnHobbyRev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setHobbySelected();
			}
		});
		
		jcmbBirthYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initBirthDate();
			}
		});
		
		jcmbBirthMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initBirthDate();
			}
		});
		
		jtxtName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jpswPassword.requestFocus();
			}
		});
		
		jtxtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtxtName.selectAll();
			}
		});
		
		jpswPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jpswPassword.setText("");
			}
		});
		
	}

	private void setHobbySelected() {
		for (JCheckBox chk : jckbHobbies) {
			chk.setSelected(!chk.isSelected());
		}
	}
	
	private void setHobbySelected(boolean value) {
		for (JCheckBox chk : jckbHobbies) {
			chk.setSelected(value);
		}
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
