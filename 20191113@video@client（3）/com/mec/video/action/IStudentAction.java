package com.mec.video.action;

import com.mec.rmi.core.ModelDialog;
import com.mec.video.model.StudentVideoModel;

public interface IStudentAction {
	@ModelDialog(caption="获取学生信息中……")
	StudentVideoModel getStudent(String id, String password);
}
