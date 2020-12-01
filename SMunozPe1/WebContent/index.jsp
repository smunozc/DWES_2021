<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setBundle basename="interface" />
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
		<h1>Bussiness Model</h1>
		<nav>
			<a class="btn" href="<%=request.getContextPath() + "/login"%>">Login</a>
			<a class="btn" href="<%=request.getContextPath()+"/logout"%>">Logout</a>
		</nav>
	</header>
	<main>
		<section class="sctn">
			<h2><fmt:message key="welcomeMsg"/></h2>
			<p><%=new Date()%></p>
		</section>
	</main>
	<footer>
		<p>Salvador Muñoz Cordero</p>
	</footer>
</body>
</html>