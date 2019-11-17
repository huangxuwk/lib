package com.mec.video.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.rmi.core.RMIInterface;
import com.mec.video.model.Course;
import com.mec.video.service.CourseService;

@Component
@RMIInterface(rmiInterface= {ICourseAction.class})
public class CourseAction implements ICourseAction {
	@Autowired
	private CourseService courseService;

	public CourseAction() {
	}
	
	@Override
	public Course getCourseByStudentId(String stuId) {
		String courseId = stuId.substring(0, 0+9);
		Course course = courseService.getCourseById(courseId);

		return course == null ? new Course(null, "课程未找到", null, true) : course;
	}

}
