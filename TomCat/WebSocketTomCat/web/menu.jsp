<%@page import="servlet.WebUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.HTTPService"%>
<!DOCTYPE html>
<%!
    public String userId;
    public String userName;
    public String userEmail;
%>   

<%
    if (!HTTPService.isValidated(request)) {
        session.invalidate();
        response.sendRedirect("index.jsp");
    } else {
        this.userId = (String) session.getAttribute("id");
        this.userName = (String) session.getAttribute("name");
        this.userEmail = (String) session.getAttribute("email");
    }
    WebUtil util = new WebUtil();
%>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">                
        <title>WebSocket</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">      
        <link href="assets/css/template.css" rel="stylesheet">
    </head>
    <body ng-app="websocketApp" ng-controller="websocketCtrl" ng-init="inicio('<%=this.userName%>', '<%=this.userId%>', '<%=this.userEmail%>')">        
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="text-center">                    
                    <a class="navbar-brand" href="javascript:void(0)"><%=this.userName%></a>                    
                </div>               
                <div class="text-center">                       
                    <a type="button" class="btn btn-danger navbar-right" ng-click="sair()">Sair</a>                    
                </div>               
            </div>
        </div>                
        <div class="container">
            <div class="starter-template">                 
                <div class="row">                    
                    <div class="col-sm-12">
                        <div id="messageArea"></div> 
                        <div> 
                            <form onsubmit="return false;"> 
                                <input id="iptMessage" name="message" /> 
                                <button id="btnEnviar">Salvar</button> 
                            </form> 
                        </div> 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery-1.11.1.min.js"></script>       
    <script src="assets/js/angular.min.js"></script>
    <script src="assets/js/app.js"></script>
</body>
</html>