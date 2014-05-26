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
                <h1>https://netbeans.org/kb/docs/javaee/maven-websocketapi.html</h1>
                <h1>http://blog.caelum.com.br/websockets-html5-em-java-com-jetty-web-em-tempo-real/</h1>
                <h1>http://www.infoq.com/br/articles/websocket-java-javaee</h1>
                
            </div>
        </div>
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript">
        $('#btnSair').click(function() {
            window.location = "getOut.jsp";
        }); 
        
        window.onload = init;
var socket = new WebSocket("ws://localhost:8080/Websocket/Websocket");
socket.onmessage = onMessage;

function onMessage(event) {
    var device = JSON.parse(event.data);
    if (device.action === "add") {
        printDeviceElement(device);
    }
    if (device.action === "remove") {
        document.getElementById(device.id).remove();
        //device.parentNode.removeChild(device);
    }
    if (device.action === "toggle") {
        var node = document.getElementById(device.id);
        var statusText = node.children[2];
        if (device.status === "On") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
        } else if (device.status === "Off") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        }
    }
}

function addDevice(name, type, description) {
    var DeviceAction = {
        action: "add",
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(DeviceAction));
}

function removeDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
    var content = document.getElementById("content");
    
    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = device.name;
    deviceDiv.appendChild(deviceName);

    var deviceType = document.createElement("span");
    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

    var deviceStatus = document.createElement("span");
    if (device.status === "On") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
    } else if (device.status === "Off") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        //deviceDiv.setAttribute("class", "device off");
    }
    deviceDiv.appendChild(deviceStatus);

    var deviceDescription = document.createElement("span");
    deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
    deviceDiv.appendChild(deviceDescription);

    var removeDevice = document.createElement("span");
    removeDevice.setAttribute("class", "removeDevice");
    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
    deviceDiv.appendChild(removeDevice);
}

function showForm() {
    document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
    document.getElementById("addDeviceForm").style.display = "none";
}

function formSubmit() {
    var form = document.getElementById("addDeviceForm");
    var name = form.elements["device_name"].value;
    var type = form.elements["device_type"].value;
    var description = form.elements["device_description"].value;
    hideForm();
    document.getElementById("addDeviceForm").reset();
    addDevice(name, type, description);
}

function init() {
    hideForm();
}
        </script> 
    </body>
</html>