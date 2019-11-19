package com.dl.mmfct.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Resource {
	private ResourceBaseInfo rbi;
	
	public Resource() {
		rbi = new ResourceBaseInfo();
	}

	public Resource setAppName(String appName) {
		rbi.setAppName(appName);
		return this;
	}
	
	public Resource setId(String id) {
		rbi.setId(id);
		return this;
	}
	
	public Resource setVersion(String version) {
		rbi.setVersion(version);
		return this;
	}
	
	public Resource setAbsoluteRoot(String absoluteRoot) {
		rbi.setAbsoluteRoot(absoluteRoot);
		return this;
	}
	
	public Resource exploreResource() {
		exploreResource(null);
		return this;
	}
	
	public Resource exploreResource(String appRoot) {
		appRoot = appRoot == null ? rbi.getAbsoluteRoot() : appRoot;
		
		File file = new File(appRoot);
		List<ResourceStructInfo> rsiList = new ArrayList<ResourceStructInfo>();
		if (file.isDirectory()) {
			scanResourceRoot(rsiList, 1, appRoot, file);
		}
		if (file.isFile()) {
			creatResourceStructInfo(rsiList, 1, appRoot, file);
		}
		rbi.setRsiList(rsiList);
		return this;
	}
	
	private int creatResourceStructInfo(List<ResourceStructInfo> rsiList, int fileHandle
				,String absoluteRoot, File curFile) {
		ResourceStructInfo rsi = new ResourceStructInfo();
		rsi.setFileHandle(fileHandle++);
		rsi.setFilePath(curFile.getAbsolutePath().replace(absoluteRoot, ""));
		//��������·��  ���û�����
		//absoluteRoot����Ǹ�·����setFilePath������õ�������ڸ�·�������·����
		//���ɨ��Ĺ����Ƿ������ģ�ɨ��ĵ�Ȼ�Ƿ������Ĵ��̣����Ƿ�����������Щ�ļ����̷�����·��C��D֮��ģ�
		//��һ���Ϳͻ�����һ��ѽ�����Բ��ܸ�����·���ģ��������������ļ������·����һ�������Ծ�Ҫ�������·��
		//���Ը�·���������������   ����������� ��������дһЩ �͸�������  �ҵ�С������̫�ÿ��Ʋ����ҵ�С������
		
		rsi.setFileSize(curFile.length());
		rsiList.add(rsi);
		
		return fileHandle;
	}
	
	private int scanResourceRoot(List<ResourceStructInfo> rsiList, int fileHandle
				,String absoluteRoot, File curFile) {
		File[] fileList = curFile.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				fileHandle = scanResourceRoot(rsiList, fileHandle, absoluteRoot, file);
			}
			if (file.isFile()) {
				fileHandle = creatResourceStructInfo(rsiList, fileHandle, absoluteRoot, file);
			}
		}
		return fileHandle;
	}
	
	@Override
	public String toString() {
		return rbi + "";
	}
	
}
