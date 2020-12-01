$(document).ready(function(){
    $(".username").change(function(){
        var user = $(this).val(); //DUDA, $(this) que devuelve?
        if(user.length >= 3){
            $(".status").html("<font color=gray> Checking availability...</font>");
             $.ajax({
                type: "POST",
                url: "checkOccurrences",
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
        }
        else{
            $(".status").html("<font color=red>User should be at least <b>3</b> character long.</font>");
        }
        
    });
});