package util;

import model.Message;
import model.Packet;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Serializer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class StanzaHandler {

    private IoSession session;

    public StanzaHandler(IoSession session) {
        this.session = session;
    }

    public void handle(String stanza, Serializer parser) {
        Class<? extends Packet> clazz = null;
        if (stanza.startsWith("<message")) {
            clazz = Message.class;
        }
        try {
            parser.read(clazz, stanza).process(session);
        } catch (Exception e) {
            // TODO catch here
        }
    }
}
