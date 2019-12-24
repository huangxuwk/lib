package com.mec.video.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mec.video.service.StudentService;

@Controller
public class StudentAction {
	@Autowired
	private StudentService studentService;

	public StudentAction() {
		System.out.println("ππ‘ÏStudentAction");
	}
	
	@RequestMapping(value="login", 
			method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result userLogin(String id, String password, HttpServletRequest request) {
		System.out.println("id:" + id + ", password:" + password);
		String result = studentService.studentLogin(id, password);
		
		if (result.equals("SUCCESS")) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		
		return new Result(result);
	}
	
	class Result {
		private String result;
		
		public Result() {
		}

		public Result(String result) {
			this.result = result;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

	}
}
