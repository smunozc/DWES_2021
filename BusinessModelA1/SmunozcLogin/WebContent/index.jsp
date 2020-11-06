<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="./styles/style.css">
</head>
<body>
	<header>
        <h1>Bussiness Model</h1>
        <nav>
            <a class="btn" href="<%=request.getContextPath()+"/login"%>">Login</a>
        </nav>
    </header>
    <main>
        <section class="sctn">
            <h2>Today's date and time</h2>
            <p><%=new Date()%></p>
        </section>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>