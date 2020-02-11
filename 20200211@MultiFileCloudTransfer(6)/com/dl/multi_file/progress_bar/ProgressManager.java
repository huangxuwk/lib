package com.dl.multi_file.progress_bar;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dl.multi_file.resource.SectionInfo;

public class ProgressManager implements IProgressManager {
	private Map<String, FileProgressBar> progressMap;
	
	public ProgressManager() {
		progressMap = new HashMap<>();
	}

	@Override
	public void initProgressBar(List<SectionInfo> sectionList) {
		for (SectionInfo sectionInfo : sectionList) {
			String fileName = sectionInfo.getFileName();
			String simpleName = getSimpleName(fileName);
			FileProgressBar progress = new FileProgressBar();
			progress.initProgressBars(simpleName, sectionInfo.getSize());
			progressMap.put(simpleName, progress);
		}
	}

	private String getSimpleName(String fileName) {
		String[] strs = fileName.split("//");
		String simpleName = strs[strs.length - 1];
		return simpleName.substring(1);
	}
	
	@Override
	public void receiveNewSection(SectionInfo section) {
		String fileName = section.getFileName();
		String simpleName = getSimpleName(fileName);
		FileProgressBar progress = progressMap.get(simpleName);
		if (progress != null) {
			progress.updata(section.getSize());
		}
	}

	@Override
	public void closeProgressBar() {
		Collection<FileProgressBar> collection = progressMap.values();
		for (FileProgressBar fileProgressBar : collection) {
			fileProgressBar.closeView();
		}
	}

}
