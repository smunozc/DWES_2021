<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
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
            <a class="btn" href="<%=request.getContextPath()+"/"%>">Home</a>
            <a class="btn" href="<%=request.getContextPath()+"/logout"%>">Logout</a>
            <a class="btn" href="<%=request.getContextPath()+"/detailedData"%>">Detailed Data</a>
        </nav>
    </header>
    <main>
        <section class="sctn">
            <%
            
            String username = (String) session.getAttribute("username");
            boolean cookieExists = false;
            //Cuando se inicia sesión, la vista muestra que no se ha encontrado cookie. Cuando se recarga si lo muestra.
            Cookie[] cookies = request.getCookies();
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals("username")) {
					%>
						<p>cookie for username <%=cookies[i].getValue()%> found</p>
            			<h1>WELCOME <%=username%>!</h1>
					<% 
					cookieExists = true;
					break;
				}
			}
			if(!cookieExists){
				%>
					<p>cookie for username not found</p>
				<% 
			}
            
            
            %>
        </section>
        <section class="sctn">
        
        	<h2>Generate a PDF document of your information</h2>
        	<a class="btn" href="<%=request.getContextPath()+"/userDataPdf"%>">Generate PDF</a>
        
        </section>
    </main>
    <footer>
        <p>Salvador Muñoz Cordero</p>
    </footer>
</body>
</html>