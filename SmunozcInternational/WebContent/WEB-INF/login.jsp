<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
        <h1>Bussiness Model</h1>
        <nav>
            <a class="btn" href="<%=request.getContextPath()+"/"%>">Home</a>
            <a class="btn" href="<%="/register"%>">Register</a>
        </nav>
    </header>
    <main>
        <form class="frm" action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" class="username" value="user1">
            <label for="password">Password:</label>
            <input type="password" name="password" class="password" value="user1">
            
            <c:set var = "auth" scope = "session" value = "${loginCorrect}"/>
            <span class="status"><c:if test="${auth == false}"> Login incorrect </c:if></span>
            
            <input class="btn" type="submit" value="Login" class="button" id="submitButton">
        </form>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>