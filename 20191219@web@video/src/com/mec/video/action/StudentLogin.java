package com.mec.video.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mec.video.model.StudentInfo;
import com.mec.video.service.StudentService;

/**
 * Servlet implementation class StudentLogin
 */
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLogin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		studentService = new StudentService();
		StudentInfo student = studentService.getStudent(id, password);
		if (student == null) {
			response.sendRedirect("loginError.html");
		} else {
			String name = student.getName();
			name = URLEncoder.encode(name, "utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.sendRedirect("videoSelector.html?id=" + student.getId()
					+ "&name=" + name);
		}
//		PrintWriter out = response.getWriter();
//		String loginOkHtmlString = "<!DOCTYPE html>"
//		+ "<html>"
//		+ "<head>"
//		+ "<meta charset='UTF-8'>"
//		+ "<title>µÇÂ¼³É¹¦</title>"
//		+ "</head>"
//		+ "<body>"
//			+ "<p>Login Ok!</p>"
//		+ "</body>"
//		+ "</html>";
//		out.append(loginOkHtmlString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
