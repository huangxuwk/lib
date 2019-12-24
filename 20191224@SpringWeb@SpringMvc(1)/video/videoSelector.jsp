<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%!
	public String getName(HttpServletRequest request, String tag) {
		return request.getParameter(tag);
	}
%>
<%
	String id = request.getParameter("id");
	String name = getName(request, "name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>视频选择</title>
</head>
<body>
	<p>学号:<%=id %></p>
	<p>姓名:<%=name %></p>
</body>
</html>