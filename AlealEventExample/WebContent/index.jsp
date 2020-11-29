<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>¡Hi World!</h1>
	Today's date:<%=new Date()%>
	
	<p>
		<a href=<%=request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>>Web project base path</a>
		<br>
		<a href="<%=request.getContextPath()+"/languages"%>">Display the available languages ('result' view going through the servlet)</a>
		<br>
		<a href="<%=request.getContextPath()+"/results.jsp"%>">Display the available languages (direct view of the 'result' view without going through the servlet)</a>
	</p>
	
</body>
</html>