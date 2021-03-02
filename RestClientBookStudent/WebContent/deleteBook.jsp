<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Book Student webapp with REST (Jersey 2.x) API - Client side</title>
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
		<script type="text/javascript">
	          $(document).ready(function(){
	              $("#studentid").change(function(){
	                  var studentid = $(this).val();
	                  if(studentid.length >= 1){
	                      //$(".status").html("<img src='images/loading.gif'><font color=gray> Checking availability...</font>");
	                       $.ajax({
	                          type: "POST",
	                          url: 	"checkStudentId",
	                          data: "studentid="+ studentid,
	                          success: function(msg){
	                              $(document).ajaxComplete(function(event, request, settings){
	                                  //$(".status").html(msg);
	                                  //alert(msg);
	                                  if(msg=="ERROR"){
	                                	//  alert('nothing has been retrieved with student ID provided');
		                                	document.getElementById("studentid").classList.add("is-invalid");
		                                	document.getElementById("submit-button").classList.remove("btn-success");
		                                	document.getElementById("submit-button").classList.add("btn-danger");
		                                	document.getElementById("submit-button").setAttribute("disabled");
	                                  }else{
	                                	//  alert('full information has been retrieved with student ID provided');
		                                  	document.getElementById("studentid")	.classList.remove("is-invalid");
		                                  	document.getElementById("submit-button").removeAttribute("disabled");
		                                  	document.getElementById("submit-button").disabled=false;
		                                  	$("#submit-button").disabled=false;
		                                  	document.getElementById('bookid').disabled=false;
		                                  	document.getElementById('booktitle').disabled=false;
		                                  	document.getElementById("bookauthor").disabled=false;
		                                  	document.getElementById("bookisbn").disabled=false;
		                                  	document.getElementById("bookyear").disabled=false;
		                                  	document.getElementById("submit-button").classList.add("btn-success");	                                	
	                                  }
	                              });
	                          }
	                      }); 
	                  }
	                  else{
	                      //$(".status").html("<font color=red>Language should be <b>3</b> character long.</font>");
	                	document.getElementById("studentid").classList.add("is-invalid");
                      	document.getElementById("submit-button").classList.remove("btn-success");
                      	document.getElementById("submit-button").setAttribute("disabled");
	                  }
	                  
	              });
	          });
	    </script>
	</head>
	<body>
		<div class="container">
		  <h2>Delete book - DELETE method</h2>
		  <form action="deleteBook" method="post">
		    <div class="form-group">
		      <label for="text">Related student:</label>
		      <input type="number" min="0" max="10000" class="form-control" id="studentid" placeholder="Enter related student ID" name="studentid" required>
		    </div>
		    <div class="form-group">
		      <label for="text">Book id:</label>
		      <input type="text" class="form-control" id="bookid" placeholder="Enter book id" name="bookid" disabled>
		    </div>
	    <button type="submit" class="btn btn-primary" id="submit-button" disabled>Delete book</button>
  </form>
</div>

	</body>
</html>