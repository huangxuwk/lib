package com.dl.multi_file.progress_bar;

import java.util.List;

import com.dl.multi_file.resource.SectionInfo;

/**
 * �������ӿ�
 * 1���û�����ͨ��������Դ����Ϣ��ʼ����������
 * 2����Դ�������յ���Դ��ɶ���ı���ڽ��������ȣ�
 * 3���رձ�����Դ���յ����н�������
 * @author dl
 *
 */
public interface IProgressManager {
	void initProgressBar(List<SectionInfo> sectionList);
	void receiveNewSection(SectionInfo section);
	void closeProgressBar();
}
