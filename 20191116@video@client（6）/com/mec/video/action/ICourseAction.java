package com.mec.video.action;

import com.mec.video.model.Course;

public interface ICourseAction {
	Course getCourseByStudentId(String stuId);
}
