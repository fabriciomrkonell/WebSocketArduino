package socket;

import conexao.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

@ApplicationScoped
public class SessionHandler {

    private final ArrayList<Session> sessions = new ArrayList<Session>();
    private final ArrayList<User> users = new ArrayList<User>();

    public void addSession(Session session) throws IOException {
        sessions.add(session);
        for (User user : users) {
            JsonObject addMessage = createAddMessage(user);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public List getUsers() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) throws IOException {        
        removeUser(user.getId());
        users.add(user);
        JsonObject addMessage = createAddMessage(user);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeUser(int id) throws IOException {
        User user = getUserById(id);
        if (user != null) {
            users.remove(user);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    private User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(User user) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", user.getId())
                .add("name", user.getNome())
                .add("email", user.getEmail())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) throws IOException {
        for (Object session : sessions) {
            sendToSession((Session) session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) throws IOException {
        session.getBasicRemote().sendText(message.toString());
    }

}
