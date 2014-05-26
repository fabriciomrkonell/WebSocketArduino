package socket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;

@ApplicationScoped
public class Session {

    private final Set sessions = new HashSet<>();
    private final Set devices = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List getDevices() {
        return new ArrayList<>(devices);
    }

    public void addDevice(Device device) {
    }

    public void removeDevice(int id) {
    }

    public void toggleDevice(int id) {
    }

    private Device getDeviceById(int id) {
        return null;
    }

    private JsonObject createAddMessage(Device device) {
        return null;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
