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
		System.out.println("StudentAction");
	}

	@RequestMapping(value="login",
			method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result studentLogin(String id, String password, HttpServletRequest request) {
		String result = studentService.studentLogin(id, password);
		if (result.equalsIgnoreCase("success")) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		
		Result res = new Result();
		res.setResult(result);
		
		return res;
	}
	
	class Result {
		private String result;
		
		public Result() {
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
		
	}
	
}
