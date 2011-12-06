package util;

import model.request.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMLUtil {
    private static Serializer serializer;

    private static Serializer getSerializer() {
        if (serializer == null) {
            serializer = new Persister();
        }
        return serializer;
    }

    public static Request parse(String message) throws Exception {
        Class<? extends Request> clazz = null;
        if (message.startsWith("<message"))
            clazz = Message.class;
        else if (message.startsWith("<auth"))
            clazz = AuthRequest.class;
        else if (message.startsWith("<rosterRequest"))
            clazz = RosterRequest.class;
        else if (message.startsWith("<register"))
            clazz = RegistrationRequest.class;
        else if (message.startsWith("<find"))
            clazz = FindRequest.class;
        else if (message.startsWith("<addContact"))
            clazz = AddContactRequest.class;
        else if (message.startsWith("<deleteContact"))
            clazz = DeleteContactRequest.class;
        else if (message.startsWith("<setStatus"))
            clazz = SetStatusRequest.class;
        return getSerializer().read(clazz, message);
    }

    public static String serialize(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        getSerializer().write(obj, writer);
        return writer.toString();
    }

}
