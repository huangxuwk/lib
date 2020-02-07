package com.dl.multi_file.resource;

import java.io.Serializable;

/**
 * �ļ�������<br>
 * �����������ļ���Ҳ������Ƭ����Ϣ��
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
	 * �˷����������ַ�����������Դ�������ĵ�map�еļ���<br>
	 * ͨ�����ַ���������Դ������һ����Դ��ϵ��һ����Ψһ�ģ�
	 */
	@Override
	public String toString() {
		if (fileName == null || fileName.equals("")) {
			return " :" + offset + ":" + size;
		}
		return fileName + ":" + offset + ":" + size;
	}
}
