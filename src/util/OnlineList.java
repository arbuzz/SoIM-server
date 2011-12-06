package util;

import model.request.Presence;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.util.SessionAttributeInitializingFilter;

import javax.management.monitor.StringMonitor;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public enum OnlineList {

    INSTANCE;

    private Map<String, IoSession> onlineContacts = new ConcurrentHashMap<String, IoSession>();
    private Map<String, String> statuses = new ConcurrentHashMap<String, String>();

    public static OnlineList getInstance() {
        return INSTANCE;
    }

    public void goneOnline(String user, IoSession session) {
        onlineContacts.put(user, session);

        writePresenceToContacts(new Presence(user, Presence.ONLINE));
    }

    public void goneOffline(String user) {
        onlineContacts.remove(user);
        statuses.remove(user);

        writePresenceToContacts(new Presence(user, Presence.OFFLINE));
    }

    public boolean isOnline(String user) {
        return onlineContacts.keySet().contains(user);
    }

    public IoSession getSession(String user) {
        if (onlineContacts.containsKey(user))
            return onlineContacts.get(user);
        return null;
    }

    private void writePresenceToContacts(Presence presence) {
        List<String> contacts = MongoHelper.getInstance().getRoster(presence.getName());
        if (contacts != null) {
            for (String contact : contacts) {
                IoSession sessionToSend = onlineContacts.get(contact);
                if (sessionToSend != null) {
                    sessionToSend.write(presence);
                }
            }
        }
    }

    public String getStatus(String user) {
        return statuses.get(user);
    }

    public void setStatus(String user, String status) {
        statuses.put(user, status);

        writePresenceToContacts(new Presence(user, status));
    }

}
