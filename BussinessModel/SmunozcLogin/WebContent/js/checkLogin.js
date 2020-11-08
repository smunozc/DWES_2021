$(document).ready(function(){
    $('#submitButton').on('click', function(){
            $(".status").html("<font color=gray> Checking authentication...</font>");
             $.ajax({
                type: "POST",
                url: "login",
                data: "username="+ user,
                success: function(msg){
                    $(".status").ajaxComplete(function(event, request, settings){
                         if(msg === "Available"){
                        	 $(".status").html("<p style='color:green;'>" + msg + "</p>");
                         } else {
                        	 $(".status").html("<p style='color:red;'>" + msg + "</p>");
                        	 //$(".submitButton").prop("disabled", true); //DUDA, No funciona, la conexion a la base de datos no se completa.
                         }
                        

                    });
                }
            }); 
    });
});