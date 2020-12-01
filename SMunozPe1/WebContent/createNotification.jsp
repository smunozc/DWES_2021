<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/checkOcurrences.js" type="text/javascript"></script>
<link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
		<h1>Bussiness Model</h1>
		<nav>
			<a class="btn" href="<%=request.getContextPath() + "/"%>">Home</a> <a
				class="btn" href="<%=request.getContextPath() + "/login"%>">Login</a>
		</nav>
	</header>
	<main>
		<form class="frm" action="notification" method="post">
			<label for="province">Province:</label> 
			<input type="text" name="province">
			
			<label for="municipality">Municipality:</label> 
			<input type="text" name="municipality"> 
			
			<label for="notifier">Notifier:</label> 
			<input type="text" name="notifier"> 
			
			<label for="title">Title:</label>
			<input type="text" name="title">
			
			<label for="body">Body:</label>
			<input type="text" name="body">
			
			<label for="link">Link:</label>
			<input type="text" name="link">
			 
			<input class="btn submitButton" type="submit" value="Submit">
		</form>
	</main>
	<footer>
		<p>Salvador Muñoz Cordero</p>
	</footer>
</body>
</html>