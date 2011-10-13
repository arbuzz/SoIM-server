package util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public enum OnlineList {

    INSTANCE;

    private static Map<String, Boolean> onlineContacts = new ConcurrentHashMap<String, Boolean>();

    public static OnlineList getInstance() {
        return INSTANCE;
    }

    public static void goneOnline(String user) {
        onlineContacts.put(user, true);
    }

    public static void goneOffline(String user) {
        onlineContacts.remove(user);
    }

}
