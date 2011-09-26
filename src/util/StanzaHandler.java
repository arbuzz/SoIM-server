package util;

import model.Message;
import model.Packet;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Serializer;

import java.util.logging.Logger;

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

    public void handle(String stanza) {
        Class<? extends Packet> clazz = null;
        if (stanza.startsWith("<message")) {
            clazz = Message.class;
        }
        try {
            Message msg = (Message) XMLUtil.parse(stanza);
            msg.process(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
