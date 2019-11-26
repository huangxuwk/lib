package com.dl.mmfct.resource;

public class ResourceStructInfo {
	private int fileHandle;
	private String filePath;
	private long fileSize;
	
	public ResourceStructInfo() {
	}

	public int getFileHandle() {
		return fileHandle;
	}

	public void setFileHandle(int fileHandle) {
		this.fileHandle = fileHandle;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "fileHandle=" + fileHandle + ", filePath=" + filePath + ", fileSize=" + fileSize;
	}
	
}
