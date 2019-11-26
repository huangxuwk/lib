package com.dl.mmfct.section;

public class NoReceivedFileSection {
	private static long baseNum = 1L;
	
	private int total;
	private long[] sectionSign;
	
	public NoReceivedFileSection() {
	}
	
	public NoReceivedFileSection(int total) {
		this.total = total;
		creatArray();
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	private void creatArray() {
		if (total == 0) {
			return;
		}
		int length;
		length = total / 64;
		length = total % 64 == 0 ? length : length + 1;
		sectionSign = new long[length];		
	}
	
	public boolean setValue(int index, int remainder) {
		sectionSign[index] |= baseNum << remainder;
		if (--total == 0) {
			return true;
		}
		return false;
	}
	
	public boolean getValue(int index, int remainder) {
		return (sectionSign[index] & baseNum << remainder) == 1;
	}
	
	public boolean afterReceiveSection(SectionInfo section) {
		/*
		n		i		re
		1		0		1
		60 	0		60
		64	1 		0
				0		64
		65	1		1
		128	2		0
				1		64
		129  2		1
		 */
		int num = section.getNum();
		int index = num / 64;
		int remainder = num % 64;

		if (remainder == 0) {
			remainder = 64;
			index--;
		}
		remainder--;
		
		return setValue(index, remainder);
	}
}
