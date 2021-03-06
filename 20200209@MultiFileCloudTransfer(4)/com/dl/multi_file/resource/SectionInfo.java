package com.dl.multi_file.resource;

import java.io.Serializable;

/**
 * 文件描述类<br>
 * 可描述整个文件，也可描述片段信息；
 * @author dl
 *
 */
public class SectionInfo implements Serializable {
	private static final long serialVersionUID = 3417961581440292164L;

	private String fileName;
	private int offset;
	private int size;
	
	public SectionInfo() {
	}

	public SectionInfo(String sectionInfoStr) {
		String[] info = sectionInfoStr.split(":");
		if (info.length == 3) {
			this.fileName = info[0];
			this.offset = Integer.valueOf(info[1]);
			this.size = Integer.valueOf(info[2]);
		}
	}
	
	public SectionInfo(String fileName, int offset, int size) {
		this.fileName = fileName;
		this.offset = offset;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isRight(int offset, int size) {
		return this.offset <= offset && (this.offset + this.size) >= (offset + size);
	}
	
	/**
	 * 此方法产生的字符串将用作资源管理中心的map中的键；<br>
	 * 通过此字符串进行资源请求；在一套资源体系中一定是唯一的；
	 */
	@Override
	public String toString() {
		if (fileName == null || fileName.equals("")) {
			return " :" + offset + ":" + size;
		}
		return fileName + ":" + offset + ":" + size;
	}
}
