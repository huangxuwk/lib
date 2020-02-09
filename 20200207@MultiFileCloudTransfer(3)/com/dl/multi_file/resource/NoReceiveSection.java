package com.dl.multi_file.resource;

import java.util.LinkedList;
import java.util.List;

public class NoReceiveSection {
	private List<SectionInfo> sectionList;
	
	public NoReceiveSection(SectionInfo sectionInfo) {
		sectionList = new LinkedList<>();
		sectionList.add(sectionInfo);
	}
	
	public void receiveNewSection(SectionInfo sectionInfo) {
		SectionInfo section = getSection(sectionInfo);
		if (section == null) {
			return;
		}
		int offset = section.getOffset();
		int size = section.getSize();
		int newOffset = sectionInfo.getOffset();
		int newSize = section.getSize();
		
		int leftOffset = offset;
		int leftSize = newOffset - offset;
		
		int rightOffset = newOffset + newSize;
		int rightSize = size - rightOffset;
		
		sectionList.remove(section);
		String fileName = section.getFileName();
		if (leftSize > 0) {
			sectionList.add(new SectionInfo(fileName, leftOffset, leftSize));
		}
		if (rightSize > 0) {
			sectionList.add(new SectionInfo(fileName, rightOffset, rightSize));
		}
	}
	
	private SectionInfo getSection(SectionInfo sectionInfo) {
		int offset = sectionInfo.getOffset();
		int size = sectionInfo.getSize();
		for (SectionInfo section : sectionList) {
			if (section.isRight(offset, size)) {
				return section;
			}			
		}
		return null;
	}
	
	public boolean isCompleted() {
		return sectionList.isEmpty();
	}
	
}
