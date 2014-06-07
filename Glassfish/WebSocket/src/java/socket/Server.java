package socket;

import conexao.User;
import java.io.IOException;
import java.io.StringReader;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/WebSocket")
public class Server {

    @Inject
    private SessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) throws IOException {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                User user = new User();
                int id = (int) parseInt(jsonMessage.getString("id"));
                user.setId(id);
                user.setNome(jsonMessage.getString("name"));
                user.setEmail(jsonMessage.getString("email"));
                sessionHandler.addUser(user);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeUser(id);
            }
        }
    }

}
