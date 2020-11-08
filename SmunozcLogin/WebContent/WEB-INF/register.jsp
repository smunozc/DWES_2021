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
            <a class="btn" href="<%=request.getContextPath()+"/"%>">Home</a>
            <a class="btn" href="<%=request.getContextPath()+"/login"%>">Login</a>
        </nav>
    </header>
    <main>
        <form class="frm" action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" class="username"><span class="status"></span>
            <label for="password">Password:</label>
            <input type="password" name="password">
            <label for="name">Name:</label>
            <input type="text" name="name">
            <label for="password">Surname:</label>
            <input type="text" name="surname">
            <label for="password">Email:</label>
            <input type="text" name="email">
            <label for="password">Birthday (format yyyy-mm-dd):</label>
            <input type="text" name="birthday">
            <input class="btn submitButton" type="submit" value="Register">
        </form>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>