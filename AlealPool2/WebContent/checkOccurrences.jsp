<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
	<head>
	
	<title>Language Availability</title>
	
	<style type="text/css">
	.flable {
		color: gray;
	}
	
	.status {
		font-family: verdana;
		font-size: 12px;
	}
	
	.language {
		color: blue;
	}
	</style>
	
	<script src="js/jquery.js" type="text/javascript"></script>
	
	<script type="text/javascript">
          $(document).ready(function(){
              $(".language").change(function(){
                  var language = $(this).val();
                  if(language.length >= 3){
                      $(".status").html("<img src='images/loading.gif'><font color=gray> Checking availability...</font>");
                       $.ajax({
                          type: "POST",
                          url: "checkOccurrences",
                          data: "language="+ language,
                          success: function(msg){

                              $(".status").ajaxComplete(function(event, request, settings){
                                   
                                  $(".status").html(msg);

                              });
                          }
                      }); 
                  }
                  else{
                      $(".status").html("<font color=red>Language should be <b>3</b> character long.</font>");
                  }
                  
              });
          });
    </script>
	</head>

	<body>
	
		<h1>¡Hi World!</h1>
		Today's date:<%=new Date()%>
	
		<div>
			<label class="flable">Language :</label> <input type="text"
				class="language" />&nbsp;<span class="status"></span>
		</div>
	
	</body>
	
</html>