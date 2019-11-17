package com.mec.video.action;

import com.mec.rmi.core.ModelDialog;
import com.mec.video.model.Course;

public interface ICourseAction {
	@ModelDialog(caption="获取课程信息中……")
	Course getCourseByStudentId(String stuId);
}
