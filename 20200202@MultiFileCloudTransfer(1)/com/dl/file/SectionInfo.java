package com.dl.file;

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
	
	@Override
		public String toString() {
			return fileName + ": " + offset + ", " + size;
		}
}
