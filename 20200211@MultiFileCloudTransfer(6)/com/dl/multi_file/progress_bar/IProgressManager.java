package com.dl.multi_file.progress_bar;

import java.util.List;

import com.dl.multi_file.resource.SectionInfo;

/**
 * 进度条接口
 * 1、用户可以通过请求资源的信息初始化进度条；
 * 2、资源接收者收到资源后可定向改变对于进度条进度；
 * 3、关闭本次资源接收的所有进度条；
 * @author dl
 *
 */
public interface IProgressManager {
	void initProgressBar(List<SectionInfo> sectionList);
	void receiveNewSection(SectionInfo section);
	void closeProgressBar();
}
