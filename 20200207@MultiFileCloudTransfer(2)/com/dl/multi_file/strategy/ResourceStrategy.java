package com.dl.multi_file.strategy;

import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.resource.SectionInfo;

/**
 * ��Դ�������ʵ����<br>
 * 1��ʵ���˰��ļ��������ֵķ�����ԣ�<br>
 * 2��ʵ���˰��ļ���С���ֵķ�����ԣ�<br>
 * 3������Դ�б�������ڷ��͸���ʱ���ò���1��<br>
 * 4������Դ�б����С�ڷ��͸���ʱ���ò���2��
 * @author dl
 *
 */
public class ResourceStrategy implements IResourceStrategy {
	
	@Override
	public List<List<SectionInfo>> doResourceStrategy(List<SectionInfo> sectionList, int sendCount) {
		List<List<SectionInfo>> sectionLList = new ArrayList<>();
		for (int i = 0; i < sendCount; i++) {
			sectionLList.add(i, new ArrayList<>());
		}
		return sectionList.size() >= sendCount 
					? resourceCountStrategy(sectionList, sectionLList)
					: resourceSizeStrategy(sectionList, sectionLList);														
	}
	
	/*
	 * ����1��������Դ������ƽ�����䣻
	 */
	private List<List<SectionInfo>> resourceCountStrategy(List<SectionInfo> sectionList, List<List<SectionInfo>> sectionLList) {
		int sendCount = sectionLList.size();
		int index = 0;
		for (SectionInfo section : sectionList) {
			List<SectionInfo> sList = sectionLList.get(index);
			sList.add(section);
			index = (index + 1) % sendCount;
		}
		return sectionLList;
	}
	
	/*
	 * ����2��������Դ��С��ƽ�����䣻
	 */
	private List<List<SectionInfo>> resourceSizeStrategy(List<SectionInfo> sectionList, List<List<SectionInfo>> sectionLList) {
		long resourceSize = 0;
		for (SectionInfo section : sectionList) {
			resourceSize += section.getSize();
		}
		int sendCount = sectionLList.size();
		int sectionCount = sectionList.size();
		// ƽ����Դ��С
		int deSectionSize = (int) (resourceSize / sendCount);
		// �����Դ��С
		int maxSectionSize = (int) (deSectionSize + resourceSize % sendCount);
		int index = 0;
		// ÿ����㷢�͵��ۼ���Դ��С
		int CumulativeSize = 0;
		// �ý������ʣ���С
		int SurplusSize = 0;
		// ��������ԴƬ�θ���һ�����
		List<SectionInfo> firstList = sectionLList.get(0);
		while (CumulativeSize < maxSectionSize && index < sectionCount) {
			SectionInfo section = sectionList.get(index);
			int size = section.getSize();
			SurplusSize = maxSectionSize - CumulativeSize;
			// ���Ƭ�δ�СС��ʣ���С��ֱ����ӣ����ж���һƬ��
			if (size <= SurplusSize) {
				firstList.add(section);
				CumulativeSize += size;
				index++;
			} else {
				// ���Ƭ�δ�С����ʣ���С���ȷָ����Ƭ�ηָ�����Σ�
				// ��һ������һ���ʣ���С��Ƭ�Σ�
				// �ڶ����Ǹ�Ƭ��ʣ���Ƭ�Σ�
				String fileName = section.getFileName();
				int offset = section.getOffset();
				firstList.add(new SectionInfo(fileName, offset, SurplusSize));
				sectionList.set(index, new SectionInfo(fileName, offset + SurplusSize, size - SurplusSize));
				CumulativeSize += SurplusSize;
			}
		}
		
		for (int i = 1; i < sendCount && index < sectionCount; i++) {
			CumulativeSize = 0;
			SurplusSize = 0;
			List<SectionInfo> list = sectionLList.get(i);
			while (CumulativeSize < deSectionSize && index < sectionCount) {
				SectionInfo section = sectionList.get(index);
				int size = section.getSize();
				SurplusSize = maxSectionSize - CumulativeSize;
				if (size <= SurplusSize) {
					list.add(section);
					CumulativeSize += size;
					index++;
				} else {
					String fileName = section.getFileName();
					int offset = section.getOffset();
					list.add(new SectionInfo(fileName, offset, SurplusSize));
					sectionList.set(index, new SectionInfo(fileName, offset + SurplusSize, size - SurplusSize));
					CumulativeSize += SurplusSize;
				}
			}
		}
		
		return sectionLList;
	}
	
}
