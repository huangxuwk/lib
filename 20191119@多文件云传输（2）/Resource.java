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
		//这是设置路径  这儿没懂大黄
		//absoluteRoot这个是根路径，setFilePath这个设置的是相对于根路径的相对路径，
		//这个扫描的工作是服务器的，扫描的当然是服务器的磁盘，但是服务器保存这些文件的盘符（根路径C、D之类的）
		//不一定和客户机的一样呀，所以不能给绝对路径的，但是里面所有文件的相对路径都一样，所以就要保存相对路径
		//绝对根路径可以因机器而异   明白了真聪明 等再往后写一些 就更清晰了  我的小鼠标好像不太好控制不了我的小恐龙了
		
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
