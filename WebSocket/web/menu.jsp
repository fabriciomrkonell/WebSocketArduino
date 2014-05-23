<%@page import="servlet.WebUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.HTTPService"%>
<!DOCTYPE html>
<%!
    public String userId;
    public String userName;
%>   

<%
    if (!HTTPService.isValidated(request)) {
        session.invalidate();
        response.sendRedirect("index.jsp");
    } else {
        this.userId = (String) session.getAttribute("id");
        this.userName = (String) session.getAttribute("name");
    }
    WebUtil util = new WebUtil();
%>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">                
        <title>WebSocket - Arduino</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">      
        <link href="assets/css/template.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="text-center">                    
                    <a class="navbar-brand" href="javascript:void(0)"><%=this.userName%></a>
                </div>               
                <div class="text-center">                    
                    <a class="navbar-brand navbar-right" href="javascript:void(0)" id="btnSair">Sair</a>
                </div>               
            </div>
        </div>
        <div class="container">
            <div class="starter-template">
                <h1>WebSocket</h1>                
            </div>
        </div>
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript">
        $('#btnSair').click(function() {
            window.location = "getOut.jsp";
        });       
        </script> 
    </body>
</html>