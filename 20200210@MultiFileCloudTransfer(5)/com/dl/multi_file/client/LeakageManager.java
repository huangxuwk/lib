package com.dl.multi_file.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dl.multi_file.resource.NoReceiveSection;
import com.dl.multi_file.resource.SectionInfo;

public class LeakageManager {
	private Map<String, NoReceiveSection> noSectionMap;

	private ResourceRequester resourceRequester;
	
	public LeakageManager(ResourceRequester resourceRequester) {
		noSectionMap = new HashMap<>();
		this.resourceRequester =resourceRequester;
	}
	
	/**
	 * ��ʼ��������Դ��Ӧ�Ķϵ���������
	 * @param sectionList
	 */
	public void init(List<SectionInfo> sectionList) {
		for (SectionInfo sectionInfo : sectionList) {
			String[] str = sectionInfo.toString().split(":");
			noSectionMap.put(str[0], new NoReceiveSection(sectionInfo));
		}
	}
	
	/**
	 * ���յ��µ���ԴƬ��
	 * @param sectionInfo
	 */
	public void receiverNews(SectionInfo sectionInfo) {
		String[] str = sectionInfo.toString().split(":");
		NoReceiveSection noReceiveSection = noSectionMap.get(str[0]);
		if (noReceiveSection != null) {
			noReceiveSection.receiveNewSection(sectionInfo);
		}
	}
	
	/**
	 * �������߽�����ɺ�Ҫ����Ƿ����û�н��յ���Ƭ�Σ����У����������룻
	 */
	public void checkReceiveCompleted() {
		List<SectionInfo> sectionList = new ArrayList<SectionInfo>();
		
		Collection<NoReceiveSection> collection = noSectionMap.values();
		for (NoReceiveSection noReceiveSection : collection) {
			if (!noReceiveSection.isCompleted()) {
				List<SectionInfo> sList = noReceiveSection.getSectionList();
				sectionList.addAll(sList);
			}
		}
		if (!sectionList.isEmpty()) {
			List<String> resourceHandles = new ArrayList<>();
			for (SectionInfo section : sectionList) {
				resourceHandles.add(section.toString());
			}
			resourceRequester.requestResource(resourceHandles);
		}
	}
	
}
