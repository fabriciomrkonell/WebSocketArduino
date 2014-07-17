<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>WebSocket Client</title>
        <script type="text/javascript">

            var wsocket;
                    function connect() {
                    wsocket = new WebSocket("ws://localhost:8080/WS1/ratesrv");
                            wsocket.onmessage = onMessage;
                    }

            function onMessage(evt) {             
                    document.getElementById("rate").innerHTML = evt.data;
            }

            window.addEventListener("load", connect, false); 
        </script>
    </head>
    <body>

        <table>
            <tr>
                <td> <label id="rateLbl">Current Rate:</label></td>
                <td> <label id="rate">0</label></td>
            </tr>
        </table>
    </body>
</html>
