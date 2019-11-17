package com.mec.video.action;

import com.mec.video.model.StudentBaseInfo;
import com.mec.video.model.StudentVideoModel;

public interface IStudentAction {
	StudentVideoModel getStudent(String id, String password);
	StudentBaseInfo getStudentPhoto(String peopleId);
}
