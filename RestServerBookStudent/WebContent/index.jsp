<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Book Student webapp with REST (Jersey 2.x) API</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" 
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<style>
		</style>
	</head>
	<body>
		<div class="jumbotron text-center">
			Book Student webapp with API Rest (Jersey 2.x)
			<br /> Service point at
			<span class="font-weight-bold"><a href="apirest/book-service">book-service</a></span>
		</div>
		<div class="container">
	  		<div class="row">
	    		<div class="col">
					<span class="font-weight-bold">(Read/Retrieve) GET method operations:</span>
					<br />
					<a href="apirest/book-service/book-by-isbn/17290459"> - Get
						book by 'isbn': /apirest/book-service/book-by-isbn/{isbn}</a>
					<br />
					<a href="apirest/book-service/book-by-isbn-and-year/17290459/2016"> - Get
						book by 'isbn' and 'year': /apirest/book-service/book-by-isbn-and-year/{isbn}/{year}</a>						
					<br />
					<a href="apirest/book-service/get-all-books"> - Get all book at
						catalog</a>
				</div>
				<div class="col">
					<span class="font-weight-bold">(Create) POST method operations:</span>
					<br />
					<a href="apirest/book-service/add-book"> - Add new book by given
						JSON document at catalog</a>
				</div>
				<div class="col">
					<span class="font-weight-bold">(Update - <a href="https://medium.com/backticks-tildes/restful-api-design-put-vs-patch-4a061aa3ed0b" 
							target="_blank">read more</a>) PUT/PATCH methods: 
					</span><br>
					<span class="font-weight-bold">PUT method:<br>
						(idempotent) - FULL UPDATE as many times you need
					</span><br>
					<span class="font-weight-bold">PATCH method:<br>
						(NOT idempotent) - PARTIAL/MINOR UPDATE just one time
					</span><br>
					
				</div>
				<div class="col">
					<span class="font-weight-bold">(Delete) DELETE method operations:</span>
					<br />
					<a href="apirest/book-service/remove-book"> - Remove an existing
						book by given JSON document catalog</a>
				</div>
			</div>
		</div>
	</body>
    <div class="card-footer text-muted text-center">
	    <footer>
	        <a href="#"><i class="fa fa-facebook"></i></a>
	        <a href="#"><i class="fa fa-twitter"></i></a>
	        <a href="#"><i class="fa fa-google-plus"></i></a>
	    	<a href="#"><i class="fa fa-twitch"></i></a>
	    </footer>
	    2º DAW - I.E.S. Alixar - Curso 2020/21
    </div>
</html>