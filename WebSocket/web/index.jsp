<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>      
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">                
        <title>WebSocket - Arduino</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">      
        <link href="assets/css/style.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <form class="form-signin" role="form" id="form">                
                <input type="email" class="form-control" placeholder="Email">
                <input type="password" class="form-control" placeholder="Senha">                
                <button class="btn btn-lg btn-primary btn-block" type="button" id="login">Fazer Login</button>
            </form>
        </div>
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript">
        $('#login').click(function() {
            $("#form").attr({action: "Autenticar"});
            $("#form").submit();
        });       
    </script>  
    </body>
</html>
