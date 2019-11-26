package com.dl.mmfct.resource;

import java.util.List;

import com.dl.mmfct.section.SectionInfo;

public class ResourceBaseInfo {
	private String appName;
	private String id;
	private String version;
	private String absoluteRoot;
	private List<ResourceStructInfo> rsiList;
	private List<SectionInfo> siList;
	
	public ResourceBaseInfo() {
	}

	public ResourceBaseInfo(ResourceBaseInfo rbi) {
		this.appName = rbi.appName;
		this.id = rbi.id;
		this.version = rbi.version;
		this.absoluteRoot = rbi.absoluteRoot;
		this.rsiList = null;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAbsoluteRoot() {
		return absoluteRoot;
	}

	public void setAbsoluteRoot(String absoluteRoot) {
		this.absoluteRoot = absoluteRoot;
	}

	public List<ResourceStructInfo> getRsiList() {
		return rsiList;
	}

	public void setRsiList(List<ResourceStructInfo> rsiList) {
		this.rsiList = rsiList;
	}

	public List<SectionInfo> getSiList() {
		return siList;
	}

	public void setSiList(List<SectionInfo> siList) {
		this.siList = siList;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("appName=").append(appName).append('\n')
			.append("id=").append(id).append('\n')
			.append("version=").append(version).append('\n')
			.append("absoluteRoot=").append(absoluteRoot);
		
		if (rsiList != null) {
			for (ResourceStructInfo rsi : rsiList) {
				result.append("\n\t").append(rsi);
			}
		}
		
		return result.toString();
	}
}
