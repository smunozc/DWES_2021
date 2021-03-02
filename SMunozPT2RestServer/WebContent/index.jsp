<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Medical Management Project</title>
	</head>
	<body>
		<div>
			<h1>ORM1.1: surgeonsWithEqualOrGreaterNumberOfHospitals</h1><br/>	
			<c:forEach items="${surgeonsWithEqualOrGreaterNumberOfHospitals}" var="surgeon">
				<pre>
					<c:out value='${surgeon.getName()}'/>
					<c:out value='${surgeon.getSurname()}'/>
					<c:out value='${surgeon.getLicense()}'/>
					- List of associated Hospital items:
					<c:forEach items="${surgeon.getHospitals()}" var="hospital">
						<c:out value="${hospital.getName()}"/>
						<c:out value="${hospital.getAddress()}"/>
						- List of associated OperatingRoom items:
						<c:forEach items="${hospital.getOperatingRooms()}" var="operatingroom">
							<c:out value="${operatingroom.getName()}"/>
							<c:out value="${operatingroom.getFloor()}"/>
						</c:forEach>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
        <div>
			<h1>ORM1.2: hospitalsWithEqualNumberOfSurgeonsAndOperatingRooms</h1><br/>	
			<c:forEach items="${hospitalsWithEqualNumberOfSurgeonsAndOperatingRooms}" var="hospital">
				<pre>
		        	<c:out value="${hospital.getName()}"/>
					<c:out value="${hospital.getAddress()}"/>
					- List of associated Surgeon items:
					<c:forEach items="${hospital.getSurgeons()}" var="surgeon">
						<c:out value="${surgeon.getName()}"/>
						<c:out value="${surgeon.getSurname()}"/>
						<c:out value="${surgeon.getLicense()}"/>
					</c:forEach>
					- List of associated OperatingRoom items:
					<c:forEach items="${hospital.getOperatingRooms()}" var="operatingroom">
						<c:out value="${operatingroom.getName()}"/>
						<c:out value="${operatingroom.getFloor()}"/>
					</c:forEach>
				</pre>
	        </c:forEach>
        </div>
        <div>
			<h1>ORM1.3: getAllOperatingRooms()</h1><br/>	
	        <c:forEach items="${allOperatingRooms}" var="room">
				<pre>
					<c:out value='${room.getName()}'/>
					<c:out value='${room.getFloor()}'/>
					- Associated Hospital item:
					<c:out value='${room.getHospital().getName()}'/>
					<c:out value='${room.getHospital().getAddress()}'/>
					- Associated Surgeon items to Hospital item:
					<c:forEach items="${room.getHospital().getSurgeons()}" var="surgeon">
						<c:out value="${surgeon.getName()}"/>
						<c:out value="${surgeon.getSurname()}"/>
						<c:out value="${surgeon.getLicense()}"/>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
        <div>
			<h1>ORM2.1: getSurgeonsByOperatingRoomFloorByHospitalName("3","Hospital Universitario Vírgen Macarena")</h1><br/>	
			<c:forEach items="${surgeonsByOperatingRoomFloorByHospitalName}" var="surgeon">
				<pre>
					<c:out value='${surgeon.getName()}'/>
					<c:out value='${surgeon.getSurname()}'/>
					<c:out value='${surgeon.getLicense()}'/>
					- List of associated Hospital items:
					<c:forEach items="${surgeon.getHospitals()}" var="hospital">
						<c:out value="${hospital.getName()}"/>
						<c:out value="${hospital.getAddress()}"/>
						- List of associated OperatingRoom items:
						<c:forEach items="${hospital.getOperatingRooms()}" var="operatingroom">
							<c:out value="${operatingroom.getName()}"/>
							<c:out value="${operatingroom.getFloor()}"/>
						</c:forEach>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
        <div>
			<h1>ORM2.2: getHospitalByNameBySurgeonLicense("Hospital de Valme","AR-03976")</h1><br/>	
			<pre>
	        	<c:out value="${hospitalByNameBySurgeonLicense.getName()}"/>
				<c:out value="${hospitalByNameBySurgeonLicense.getAddress()}"/>
				- List of associated Surgeon items:
				<c:forEach items="${hospitalByNameBySurgeonLicense.getSurgeons()}" var="surgeon">
					<c:out value="${surgeon.getName()}"/>
					<c:out value="${surgeon.getSurname()}"/>
					<c:out value="${surgeon.getLicense()}"/>
				</c:forEach>
				- List of associated OperatingRoom items:
				<c:forEach items="${hospitalByNameBySurgeonLicense.getOperatingRooms()}" var="operatingroom">
					<c:out value="${operatingroom.getName()}"/>
					<c:out value="${operatingroom.getFloor()}"/>
				</c:forEach>
			</pre>
        </div>
        <div>
			<h1>ORM2.3: getAllOperatingRoomsByHospitalName("Hospital Universitario Virgen del Rocío")</h1><br/>	
			<c:forEach items="${getAllOperatingRoomsByHospitalName}" var="room">
				<pre>
					<c:out value='${room.getName()}'/>
					<c:out value='${room.getFloor()}'/>
	        	</pre>
	        </c:forEach>
        </div>
	</body>
</html>