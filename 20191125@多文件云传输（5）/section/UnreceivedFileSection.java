package com.dl.mmfct.section;

import java.util.LinkedList;
import java.util.List;

public class UnreceivedFileSection {
	private int fileHandle;
	private List<SectionInfo> sections;
	
	public UnreceivedFileSection() {
	}
	
	public UnreceivedFileSection(int fileHandle, int size) {
		this.fileHandle = fileHandle;
		sections = new LinkedList<>();
		SectionInfo section = new SectionInfo(fileHandle, 0, size); 
		sections.add(section);
	}
	
	public boolean isReceiveCompleted() {
		return sections.isEmpty();
	}
	
	private int getRightSection(SectionInfo section) throws Exception {
		long offset = section.getOffset();
		int size = section.getSize();
		int index;
		for (index = 0; index < sections.size(); index++) {
			SectionInfo orgSection = sections.get(index);
			if (orgSection.isRightSection(offset, size)) {
				return index;
			}
		}
		
		throw new Exception("Æ¬¶Î" + section + "Òì³££¡");
	}
	
	public void afterReceiveSection(SectionInfo section) {
		
		try {
			int index = getRightSection(section);
			SectionInfo org = sections.get(index);
			
			long orgOffset = org.getOffset();
			int orgSize = org.getSize();
			long curOffset = section.getOffset();
			int curSize = section.getSize();
			
			long leftOffset = orgOffset;
			int leftSize = (int) (curOffset - orgOffset);
			
			long rightOffset = curOffset + curSize;
			int rightSize = (int) (orgOffset + orgSize - rightOffset);
			
			sections.remove(index);
			if (leftSize > 0) {
				sections.add(new SectionInfo(fileHandle, leftOffset, leftSize));
			}
			if (rightSize > 0) {
				sections.add(new SectionInfo(fileHandle, rightOffset, rightSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
