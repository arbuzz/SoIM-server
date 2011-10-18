package util;

import model.request.AuthRequest;
import model.request.Message;
import model.request.Request;
import model.request.RosterRequest;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;

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
        return getSerializer().read(clazz, message);
    }

    public static String serialize(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        getSerializer().write(obj, writer);
        return writer.toString();
    }

}
