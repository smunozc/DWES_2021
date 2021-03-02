<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Book Student webapp with REST (Jersey 2.x) API - Client side</title>
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
		  <h2>Show books - GET method</h2>
		  
		  <a href="index.jsp" class="btn btn-primary">Home</a>
		  
		  <table class="table" style="margin-top: 50px;">
			<thead class="thead-dark">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Title</th>
					<th scope="col">Author</th>
					<th scope="col">ISBN</th>
					<th scope="col">Year</th>
					<th scope="col">Student</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${listBooks}">
					<tr class="class_tr">
						<th scope="row"><c:out value="${book.get('id')}" /></th>
						<td><c:out value="${book.get('title')}" /></td>
						<td><c:out value="${book.get('author')}" /></td>
						<td><c:out value="${book.get('title')}" /></td>
						<td><c:out value="${book.get('year')}" /></td>
						<td><c:out value="${book.get('student').get('name')}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		  
		</div>

	</body>
</html>