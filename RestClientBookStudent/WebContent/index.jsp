<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Book Student webapp with REST (Jersey 2.x) API - Client
	side</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	
		<h2>Book Options</h2>
		<a href="createBook.jsp" class="btn btn-primary">CREATE (POST Method)</a>
		<a href="putBook.jsp" class="btn btn-primary">UPDATE (PUT Method)</a>
		<a href="patchBook.jsp" class="btn btn-primary">UPDATE (PATCH Method)</a>
		<a href="deleteBook.jsp" class="btn btn-primary">DELETE (DELETE Method)</a>
		<a href="<%=request.getContextPath()+"/showBooks"%>" class="btn btn-primary">SHOW BOOKS (GET Method)</a>
		
	</div>

</body>
</html>