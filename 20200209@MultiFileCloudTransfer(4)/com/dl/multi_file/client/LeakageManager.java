package com.dl.multi_file.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dl.multi_file.resource.NoReceiveSection;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.sd.consumer.ClientCache;

public class LeakageManager {
	private Map<String, NoReceiveSection> noSectionMap;
	private ClientCache clientCache;

	private ResourceRequester resourceRequester;
	
	public LeakageManager(ResourceRequester resourceRequester) {
		noSectionMap = new HashMap<>();
		clientCache = new ClientCache();
		
		this.resourceRequester =resourceRequester;
	}
	
	/**
	 * ��ʼ��������Դ��Ӧ�Ķϵ���������
	 * @param sectionList
	 */
	public void init(List<SectionInfo> sectionList) {
		for (SectionInfo sectionInfo : sectionList) {
			noSectionMap.put(sectionInfo.toString(), new NoReceiveSection(sectionInfo));
		}
	}
	
	/**
	 * ���յ��µ���ԴƬ��
	 * @param sectionInfo
	 */
	public void receiverNews(SectionInfo sectionInfo) {
		NoReceiveSection noReceiveSection = noSectionMap.get(sectionInfo.toString());
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
			for (SectionInfo section : sectionList) {
				String tag = section.toString();
				resourceRequester.requestResource(clientCache.getServerList(tag), tag);
			}
			
		}
	}
	
}
