package util;

import model.Message;
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

    public static Object parse(String message) throws Exception {
        Class clazz = null;
        if (message.startsWith("<message"))
            clazz = Message.class;
        return getSerializer().read(clazz, message);
    }

    public static String serialize(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        getSerializer().write(obj, writer);
        return writer.toString();
    }

}
