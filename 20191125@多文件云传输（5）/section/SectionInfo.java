package com.dl.mmfct.section;

import com.util.BytesToString;

public class SectionInfo {
	public static final int SECTION_INFO_LENGTH = 16;
	
	private int fileHandle;
	// TODO
	private int num;
	private long offset;
	private int size;
	
	public SectionInfo() {
	}
	
	public SectionInfo(SectionInfo section) {
		this.fileHandle = section.fileHandle;
		this.offset = section.offset;
		this.size = section.size;
	}
	
	public SectionInfo(byte[] value) {
		if (value.length != SECTION_INFO_LENGTH) {
			return;
		}
		
		byte[] bFileHandle = BytesToString.getBytesAt(value, 0, 4);
		byte[] bOffset = BytesToString.getBytesAt(value, 4, 8);
		byte[] bSize = BytesToString.getBytesAt(value, 12, 4);
		
		this.fileHandle = BytesToString.bytesToInt(bFileHandle);
		this.offset = BytesToString.bytesToInt(bOffset);
		this.size = BytesToString.bytesToInt(bSize);
	}
	
	public SectionInfo(int fileHandle, long offset, int size) {
		this.fileHandle = fileHandle;
		this.offset = offset;
		this.size = size;
	}

	public int getFileHandle() {
		return fileHandle;
	}

	public void setFileHandle(int fileHandle) {
		this.fileHandle = fileHandle;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public byte[] toBytes() {
		byte[] bFileHandle = BytesToString.intToBytes(fileHandle);
		byte[] bOffset = BytesToString.longToBytes(offset);
		byte[] bSize = BytesToString.intToBytes(size);
		
		byte[] result = new byte[16];
		BytesToString.setBytesAt(result, 0, bFileHandle);
		BytesToString.setBytesAt(result, 4, bOffset);
		BytesToString.setBytesAt(result, 12, bSize);
		
		return result;
	}
	
	public boolean isRightSection(long offset, int size) {
		return this.offset <= offset && (this.offset + this.size) >= (offset + size);
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("fileHandle:");
		res.append(fileHandle).append("\n")
		.append("offset:").append(offset).append("\n")
		.append("size:").append(size);
		
		return res.toString();
	}
}
