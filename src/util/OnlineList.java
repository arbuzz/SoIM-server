package util;

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

    public static OnlineList getInstance() {
        return INSTANCE;
    }

    public void goneOnline(String user, IoSession session) {
        onlineContacts.put(user, session);
    }

    public void goneOffline(String user) {
        onlineContacts.remove(user);
    }

    public boolean isOnline(String user) {
        return onlineContacts.keySet().contains(user);
    }

    public IoSession getSession(String user) {
        if (onlineContacts.containsKey(user))
            return onlineContacts.get(user);
        return null;
    }

}
