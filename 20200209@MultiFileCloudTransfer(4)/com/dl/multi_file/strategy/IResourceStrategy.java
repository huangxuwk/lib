package com.dl.multi_file.strategy;

import java.util.List;

import com.dl.multi_file.resource.SectionInfo;

/**
 * ��Դ������Խӿ�
 * @author dl
 *
 */

public interface IResourceStrategy {
	
	/**
	 * ����Դ����ķ���<br>
	 * ͨ����������Դ�б�ͷ��͸�����������Դ��
	 * @param sectionList ��Դ�б�
	 * @param sendCount ���͸���
	 * @return ��������Դ�б�
	 */
	List<List<SectionInfo>> doResourceStrategy(List<SectionInfo> sectionList, int sendCount);
}
