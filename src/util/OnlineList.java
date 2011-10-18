package util;

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

    private Set<String> onlineContacts = new LinkedHashSet<String>();

    public static OnlineList getInstance() {
        return INSTANCE;
    }

    public void goneOnline(String user) {
        onlineContacts.add(user);
    }

    public void goneOffline(String user) {
        onlineContacts.remove(user);
    }

    public boolean isOnline(String user) {
        return onlineContacts.contains(user);
    }

}
