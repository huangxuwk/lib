package com.mec.video.action;

import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.rmi.core.RMIInterface;
import com.mec.util.ByteString;
import com.mec.video.model.StudentBaseInfo;
import com.mec.video.model.StudentVideoModel;
import com.mec.video.service.StudentService;

@Component
@RMIInterface(rmiInterface= {IStudentAction.class})
public class StudentAction implements IStudentAction {
	@Autowired
	private StudentService studentService;

	public StudentAction() {
	}
	
	@Override
	public StudentVideoModel getStudent(String id, String password) {
		StudentVideoModel stu = studentService.getStudentById(id, password);
		if (stu == null) {
			stu = new StudentVideoModel("ERROR", "账号或密码错误!", null, null, false);
		}
		
		return stu;
	}

	@Override
	public String getStudentPhoto(String peopleId) {
		StudentBaseInfo student = studentService.getStudentBaseInfo(peopleId);
		if (student == null || student.getPhoto() == null) {
			return null;
		}
		Blob blobPhoto = student.getPhoto();
		String result = null;
		try {
			byte[] bytePhoto = blobPhoto.getBytes(1L, (int) blobPhoto.length());
			
			result = ByteString.bytesToString(bytePhoto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
