package socket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/websocket")
public class MyWebSocketServlet extends WebSocketServlet {

    private static final long serialVersionUID = 1L;
    private static final List<ConnectionWS> connections = new ArrayList<ConnectionWS>();

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
        String username = request.getParameter("username");
        return new ConnectionWS(username);
    }

    public static final List<ConnectionWS> getConnections() {
        return connections;
    }

    public static final void broadcast(String message) throws IOException {
        for (ConnectionWS con : MyWebSocketServlet.getConnections()) {
            con.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
            System.out.println("Enviando mensagem de texto (" + message + ")");
        }
    }
}
