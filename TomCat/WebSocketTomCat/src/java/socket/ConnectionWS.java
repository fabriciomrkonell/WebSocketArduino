package socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public final class ConnectionWS extends MessageInbound {

    private final String username;

    public ConnectionWS(String username) {
        this.username = username;
    }

    @Override
    protected void onOpen(WsOutbound outbound) { 
        MyWebSocketServlet.getConnections().add(this); 
        String message = String.format("\"%s\" se conectou.", username); 
        try { 
            MyWebSocketServlet.broadcast(message);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Override 
    protected void onBinaryMessage(ByteBuffer arg0) throws IOException { throw new RuntimeException("Metodo não aceito"); 
    } 
    
    @Override protected void onTextMessage(CharBuffer msg) throws IOException { 
        String message = String.format("\"%s\": %s", username, msg.toString()); 
        MyWebSocketServlet.broadcast(message); 
    } 
    
}

