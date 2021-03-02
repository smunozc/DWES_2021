<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vehicle Management Project</title>
	</head>
	<body>
		<div>
			<h1>Get all drivers:</h1><br/>	
			<c:forEach items="${allDrivers}" var="driver">
				<pre>
					<c:out value='${driver.getName()}'/>
					<c:out value='${driver.getSurname()}'/>
					<c:out value='${driver.getLicense()}'/><br/>
					- Associated Dealer items:
					<c:forEach items="${driver.getDealers()}" var="dealer">
						<c:out value="${dealer.getName()}"/>
						<c:out value="${dealer.getAddress()}"/><br/>
						- List of associated Garage items:
						<c:forEach items="${dealer.getGarages()}" var="garage">
							<c:out value="${garage.getName()}"/>
							<c:out value="${garage.getSlot()}"/>
						</c:forEach>
					</c:forEach>
	        	</pre>
	        </c:forEach>
		</div>
		
		<div>
			<h1>Get all dealers:</h1><br/>	
			<c:forEach items="${allDealers}" var="dealer">
				<pre>
		        	<c:out value="${dealer.getName()}"/>
					<c:out value="${dealer.getAddress()}"/>
					- Associated Driver items:
					<c:forEach items="${dealer.getDrivers()}" var="driver">
						<c:out value="${driver.getName()}"/>
						<c:out value="${driver.getSurname()}"/>
						<c:out value="${driver.getLicense()}"/>
					</c:forEach>
					- Associated Garage items:
					<c:forEach items="${dealer.getGarages()}" var="garage">
						<c:out value="${garage.getName()}"/>
						<c:out value="${garage.getSlot()}"/>
					</c:forEach>
				</pre>
	        </c:forEach>
        </div>
		
		<div>
			<h1>Get all garages:</h1><br/>	
	        <c:forEach items="${allGarages}" var="garage">
				<pre>
					<c:out value='${garage.getName()}'/>
					<c:out value='${garage.getSlot()}'/>
					- Associated Dealer item:
					<c:out value='${garage.getDealer().getName()}'/>
					<c:out value='${garage.getDealer().getAddress()}'/>
					- Associated Driver items to Garage item:
					<c:forEach items="${garage.getDealer().getDrivers()}" var="driver">
						<c:out value="${driver.getName()}"/>
						<c:out value="${driver.getSurname()}"/>
						<c:out value="${driver.getLicense()}"/>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
		
		<div>
			<h1>Drivers with 2 or more dealers:</h1><br/>	
			<c:forEach items="${driversWith2OrMoreDealers}" var="driver">
				<pre>
					<c:out value='${driver.getName()}'/>
					<c:out value='${driver.getSurname()}'/>
					<c:out value='${driver.getLicense()}'/><br/>
					- List of associated Dealer items:
					
					<c:forEach items="${driver.getDealers()}" var="dealer">
						<c:out value="${dealer.getName()}"/>
						<c:out value="${dealer.getAddress()}"/><br/>
						- List of associated Garage items:
						<c:forEach items="${dealer.getGarages()}" var="garage">
							<c:out value="${garage.getName()}"/>
							<c:out value="${garage.getSlot()}"/>
						</c:forEach>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
        
        <div>
			<h1>Dealers with equal number of drivers and garages:</h1><br/>	
			<c:forEach items="${getDealersWithEqualNumberOfDriversAndGarages}" var="dealer">
				<pre>
		        	<c:out value="${dealer.getName()}"/>
					<c:out value="${dealer.getAddress()}"/>
					- List of associated Driver items:
					<c:forEach items="${dealer.getDrivers()}" var="driver">
						<c:out value="${driver.getName()}"/>
						<c:out value="${driver.getSurname()}"/>
						<c:out value="${driver.getLicense()}"/>
					</c:forEach>
					- List of associated Garage items:
					<c:forEach items="${dealer.getGarages()}" var="garage">
						<c:out value="${garage.getName()}"/>
						<c:out value="${garage.getSlot()}"/>
					</c:forEach>
				</pre>
	        </c:forEach>
        </div>
        
        <div>
			<h1>getDriversByGarageSlotByDealerName("3","Dealer 3 full name")</h1><br/>	
			<c:forEach items="${driversByGarageSlotByDealerName}" var="driver">
				<pre>
					<c:out value='${driver.getName()}'/>
					<c:out value='${driver.getSurname()}'/>
					<c:out value='${driver.getLicense()}'/>
					- List of associated Dealer items:
					<c:forEach items="${driver.getDealers()}" var="dealer">
						<c:out value="${dealer.getName()}"/>
						<c:out value="${dealer.getAddress()}"/>
						- List of associated Garage items:
						<c:forEach items="${dealer.getGarages()}" var="garage">
							<c:out value="${garage.getName()}"/>
							<c:out value="${garage.getSlot()}"/>
						</c:forEach>
					</c:forEach>
	        	</pre>
	        </c:forEach>
        </div>
        <div>
			<h1>getDealerByNameByDriverLicense("Dealer 2 full name","2345-ABC")</h1><br/>	
			<pre>
	        	<c:out value="${dealerByNameByDriverLicense.getName()}"/>
				<c:out value="${dealerByNameByDriverLicense.getAddress()}"/>
				- List of associated Driver items:
				<c:forEach items="${dealerByNameByDriverLicense.getDrivers()}" var="driver">
					<c:out value="${driver.getName()}"/>
					<c:out value="${driver.getSurname()}"/>
					<c:out value="${driver.getLicense()}"/>
				</c:forEach>
				- List of associated Garage items:
				<c:forEach items="${dealerByNameByDriverLicense.getGarages()}" var="garage">
					<c:out value="${garage.getName()}"/>
					<c:out value="${garage.getSlot()}"/>
				</c:forEach>
			</pre>
        </div>
        <div>
			<h1>getAllGaragesByDealerName("Dealer 1 full name")</h1><br/>	
			<c:forEach items="${garagesByDealerName}" var="garage">
				<pre>
					<c:out value='${garage.getName()}'/>
					<c:out value='${garage.getSlot()}'/>
	        	</pre>
	        </c:forEach>
        </div>
	</body>
</html>