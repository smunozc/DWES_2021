<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="./styles/style.css">
	<script src="js/checkLogin.js" type="text/javascript"></script>
</head>
<body>
	<header>
        <h1>Bussiness Model</h1>
        <nav>
            <a class="btn" href="<%=request.getContextPath()+"/"%>">Home</a>
            <a class="btn" href="<%=request.getContextPath()+"/register"%>">Register</a>
        </nav>
    </header>
    <main>
        <form class="frm" action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" class="username" value="user1">
            <label for="password">Password:</label>
            <input type="password" name="password" class="password" value="user1">
            <span class="status"></span>
            <input class="btn" type="submit" value="Login" class="button" id="submitButton">
        </form>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>