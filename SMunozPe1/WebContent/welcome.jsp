<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
<link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
		<h1>Bussiness Model</h1>
		<nav>
			<a class="btn" href="<%=request.getContextPath() + "/"%>">Home</a> 
			<a class="btn" href="<%=request.getContextPath() + "/logout"%>">Logout</a>
		</nav>
	</header>
	<main>
		<section class="sctn">
			<h1>
				WELCOME
				<c:out value="${ user.username }"></c:out>
				!
			</h1>
		</section>
		<section class="sctn">

			<h2>Generate a PDF document of your information</h2>
			<a class="btn" href="<%=request.getContextPath() + "/userDataPdf"%>">Generate
				PDF</a>

		</section>
	</main>
	<footer>
		<p>Salvador Muñoz Cordero</p>
	</footer>
</body>
</html>