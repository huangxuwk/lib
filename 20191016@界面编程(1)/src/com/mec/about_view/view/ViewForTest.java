package com.mec.about_view.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ViewForTest implements IMecView {
	private JFrame jfrmMainView;
	private JTextField jtxtStuId;
	private JTextField jtxtName;
	private JPasswordField jpswPassword;
	
	private JRadioButton jrdbSexMale;
	private JRadioButton jrdbSexFemale;
	private JComboBox<String> jcmbBirthYear;
	private JComboBox<String> jcmbBirthMonth;
	private JComboBox<String> jcmbBirthDate;
	private List<JCheckBox> jckbHobbies;
	
	private JButton jbtnHobbyAll;
	private JButton jbtnHobbyRev;
	private JButton jbtnHobbyCls;
	
	private JButton jbtnExit;
	
	private String[] hobbyNames = {
			"睡觉", "看电视", "游泳", " 爬山", "敲代码", "打游戏", "喝酒"
	};
	
	public ViewForTest() {
		initView();
	}

	@Override
	public void init() {
		jfrmMainView = new JFrame("关于控件的学习");
		jfrmMainView.setSize(610, 500);
		jfrmMainView.setLocationRelativeTo(null);
		jfrmMainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 相对布局模式
		jfrmMainView.setLayout(new BorderLayout());
		// 不可改变大小
		jfrmMainView.setResizable(false);
		
		JLabel jlblTopic = new JLabel("西邮大学生信息录入", JLabel.CENTER);
		jlblTopic.setFont(topicFont);
		jlblTopic.setForeground(topicColor);
		jfrmMainView.add(jlblTopic, BorderLayout.NORTH);
		
		// 设置左右边框，用透明标签的方式
		JPanel jpnlLeft = new JPanel();
		jpnlLeft.add(new JLabel("      "));
		jfrmMainView.add(jpnlLeft, BorderLayout.WEST);

		JPanel jpnlRight = new JPanel();
		jpnlRight.add(new JLabel("      "));
		jfrmMainView.add(jpnlRight, BorderLayout.EAST);
		
		JPanel jpnlBody = new JPanel();
		// 设置网格布局，一行三列
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
		
		JLabel jlblStuId = new JLabel("学    号");
		jlblStuId.setFont(normalFont);
		jlblStuId.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblStuId);
		
		jtxtStuId = new JTextField();
		jtxtStuId.setFont(normalFont);
		jtxtStuId.setBounds(orgLeft + PADDING, top, rightWidth, normalSize + 4);
		jpnlEditor.add(jtxtStuId);
				
		top += normalSize + 4 + PADDING;
		JLabel jlblName = new JLabel("姓    名");
		jlblName.setFont(normalFont);
		jlblName.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblName);
		
		jtxtName = new JTextField();
		jtxtName.setFont(normalFont);
		jtxtName.setBounds(orgLeft + PADDING, top, rightWidth, normalSize + 4);
		jpnlEditor.add(jtxtName);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblPassword = new JLabel("密    码");
		jlblPassword.setFont(normalFont);
		jlblPassword.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblPassword);
		
		jpswPassword = new JPasswordField();
		jpswPassword.setFont(normalFont);
		jpswPassword.setBounds(orgLeft +PADDING, top, rightWidth, normalSize + 4);
		jpnlEditor.add(jpswPassword);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblSex = new JLabel("性    别");
		jlblSex.setFont(normalFont);
		jlblSex.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblSex);
		
		jrdbSexMale = new JRadioButton("男");
		jrdbSexMale.setFont(normalFont);
		jrdbSexMale.setBounds(orgLeft + PADDING, top, 100, normalSize);
		jpnlEditor.add(jrdbSexMale);

		jrdbSexFemale = new JRadioButton("女");
		jrdbSexFemale.setFont(normalFont);
		jrdbSexFemale.setBounds(orgLeft + PADDING + 100, top, 100, normalSize);
		jpnlEditor.add(jrdbSexFemale);
		
		// 加入按钮组，防止单选操作出现多选的现象
		ButtonGroup btgpSex = new ButtonGroup();
		btgpSex.add(jrdbSexMale);
		btgpSex.add(jrdbSexFemale);
		
		top += normalSize + 4 + PADDING;
		JLabel jlblBirth = new JLabel("出生日期");
		jlblBirth.setFont(normalFont);
		jlblBirth.setBounds(0, top, lblWidth, normalSize);
		jpnlEditor.add(jlblBirth);
		
		int deltaLeft = orgLeft + PADDING;
		jcmbBirthYear = new JComboBox<>();
		jcmbBirthYear.setFont(normalFont);
		jcmbBirthYear.setBounds(deltaLeft, top, normalSize*4, normalSize+4);
		jpnlEditor.add(jcmbBirthYear);
		
		deltaLeft += jcmbBirthYear.getWidth();
		JLabel jlblBirthYear = new JLabel("年");
		jlblBirthYear.setFont(normalFont);
		jlblBirthYear.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthYear);
		
		deltaLeft += jlblBirthYear.getWidth();
		jcmbBirthMonth = new JComboBox<>();
		jcmbBirthMonth.setFont(normalFont);
		jcmbBirthMonth.setBounds(deltaLeft, top, normalSize*3, normalSize+4);
		jpnlEditor.add(jcmbBirthMonth);
		
		deltaLeft += jcmbBirthMonth.getWidth();
		JLabel jlblBirthMonth = new JLabel("月");
		jlblBirthMonth.setFont(normalFont);
		jlblBirthMonth.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthMonth);
		
		deltaLeft += jlblBirthMonth.getWidth();
		jcmbBirthDate = new JComboBox<>();
		jcmbBirthDate.setFont(normalFont);
		jcmbBirthDate.setBounds(deltaLeft, top, normalSize*3, normalSize+4);
		jpnlEditor.add(jcmbBirthDate);
		
		deltaLeft += jcmbBirthDate.getWidth();
		JLabel jlblBirthDate = new JLabel("日");
		jlblBirthDate.setFont(normalFont);
		jlblBirthDate.setBounds(deltaLeft, top, normalSize, normalSize);
		jpnlEditor.add(jlblBirthDate);
		
		top += normalSize + 4 + PADDING;
		
		JPanel jpnlHobbies = new JPanel();
		jpnlHobbies.setBounds(0, top, orgLeft+PADDING+rightWidth, normalSize*8);
		TitledBorder ttbdHobbies = new TitledBorder("爱好");
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
		
		jbtnHobbyAll = new JButton("全选");
		jbtnHobbyAll.setFont(smallFont);
		jpnlHobbyButton.add(jbtnHobbyAll);

		jbtnHobbyRev = new JButton("反选");
		jbtnHobbyRev.setFont(smallFont);
		jpnlHobbyButton.add(jbtnHobbyRev);
		
		jbtnHobbyCls = new JButton("重置");
		jbtnHobbyCls.setFont(smallFont);
		jpnlHobbyButton.add(jbtnHobbyCls);
		
		// TODO
		
		JPanel jpnlListor = new JPanel();
		TitledBorder ttbdListor = new TitledBorder("学生信息列表区");
		ttbdListor.setTitleFont(normalFont);
		ttbdListor.setTitlePosition(TitledBorder.TOP);
		ttbdListor.setTitleJustification(TitledBorder.CENTER);
		jpnlListor.setBorder(ttbdListor);
		jpnlBody.add(jpnlListor);
	
		JPanel jpnlFooter = new JPanel();
		jpnlFooter.setLayout(new GridLayout(1, 0));
		jfrmMainView.add(jpnlFooter, BorderLayout.SOUTH);
		
		jbtnExit = new JButton("退出");
		jbtnExit.setFont(smallFont);
		jpnlFooter.add(jbtnExit);
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showView() {
		jfrmMainView.setVisible(true);
	}

	@Override
	public void closeView() {
		
	}

}
