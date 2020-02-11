package com.dl.multi_file.client;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.resource.SectionInfo;

public interface IClientServerAction {
	void requestResource(NetNode recipient, List<SectionInfo> sectionList) throws Throwable;
}
