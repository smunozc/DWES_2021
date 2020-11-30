<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
	<link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
        <h1>Bussiness Model</h1>
        <nav>
            <a class="btn" href="<%=request.getContextPath()+"/"%>">Home</a>
            <a class="btn" href="<%="/welcome.jsp"%>">Welcome Page</a>
        </nav>
    </header>
    <main>
        <form class="frm" action="detailedData" method="post">
        
            <label for="nif">NIF:</label>
            <input type="text" name="nif">
            
            <label for="weight">Weight:</label>
            <input type="text" name="weight">
            
            <label for="height">Height:</label>
            <input type="text" name="height">
            
            <label for="academicFormation">Academic Formation:</label>
            <textarea name="academicFormation"></textarea>
            
            <label for="hobbies">Hobbies:</label>
            <textarea name="hobbies"></textarea>
            
            <input class="btn" type="submit" value="Send">
            
        </form>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>