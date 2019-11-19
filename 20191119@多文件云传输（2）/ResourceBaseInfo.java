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

	String getAppName() {
		return appName;
	}

	void setAppName(String appName) {
		this.appName = appName;
	}

	String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	String getVersion() {
		return version;
	}

	void setVersion(String version) {
		this.version = version;
	}

	String getAbsoluteRoot() {
		return absoluteRoot;
	}

	void setAbsoluteRoot(String absoluteRoot) {
		this.absoluteRoot = absoluteRoot;
	}

	List<ResourceStructInfo> getRsiList() {
		return rsiList;
	}

	void setRsiList(List<ResourceStructInfo> rsiList) {
		this.rsiList = rsiList;
	}

	List<SectionInfo> getSiList() {
		return siList;
	}

	void setSiList(List<SectionInfo> siList) {
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
