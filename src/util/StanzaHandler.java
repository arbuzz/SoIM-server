package util;

import model.request.Message;
import model.request.Request;
import org.apache.mina.core.session.IoSession;

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
        Class<? extends Request> clazz = null;
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
