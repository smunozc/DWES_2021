<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.LanguageDAOImpl"%>
<%@ page import="model.Language"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>¡Hi World!</h1>
	Today's date:<%=new Date()%>

	<%
		List<Language> languages = (List<Language>) session.getAttribute("languages");
	%>

	<p>

		<a href="<%=request.getContextPath() + "/"%>">Web project base path
			(back to index)</a> <br>
		<br>
		
		<c:if test="${!empty sessionScope['languages']}">
			<h1>tiene datos</h1>
		</c:if>
		
		<c:if test="${empty sessionScope['languages']}">
			<h1>NO tiene datos</h1>
		</c:if>
		
		<!-- bucle para recorrer parámetros -->
		<!-- y visualización del nombre del parámetro -->
		<c:forEach var='parametro' items='${languages}'>
			<!--<c:out value='${parametro.name}' /><br/>-->
			<c:choose>
				<c:when test="${parametro.name=='Papiamento'}">
					se trata de Papiamento <br/>
				</c:when>
				<c:when test="${parametro.name=='Spanish'}">
					se trata de Spanish <br/>
				</c:when>
				<c:when test="${parametro.name=='Turkmenian'}">
					se trata de Turkmenian <br/>
				</c:when>
				<c:otherwise>
					<!--  otro idioma <br/> -->
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<br/>
		
		<!-- Toma como elemento de partida una cadena de texto en la cual los valores están  -->
		<!-- separados por un carácter delimitador concreto (en este caso de ejemplo es el  -->
		<!-- carácter guión). Eso se establece en delims. -->
		<!-- Por otra parte hay una variable que controla el número de iteraciones realizadas -->
		<!-- por esta estructura de naturaleza repetitiva. Eso se establece en varStatus. -->
		<c:forTokens items="${values}" delims="-" var="item" varStatus="iteration">
			<c:if test="${iteration.first}">
				<table border="1">
					<tr>
						<td> position </td>
						<td> value </td>
					</tr>
			</c:if>
			<tr>
				<td>
					<c:out value="${iteration.count}" />
				</td>
				<td>
					<c:out value="${item}" />
				</td>
			</tr>
			<c:if test="${iteration.last}">
				</table>
			</c:if>
		</c:forTokens>


		<br>
		<br> 
	</p>

</body>
</html>