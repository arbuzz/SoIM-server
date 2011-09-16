import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.core.Persister;
import util.*;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoIMHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        XMLLightweightParser parser = new XMLLightweightParser("UTF-8");
        session.setAttribute(Config.PARSER, parser);
        session.setAttribute(Config.SERIALIZER, new Persister());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String stanza = (String) message;
        StanzaHandler handler = new StanzaHandler(session);
        handler.handle(stanza, new Persister());
    }
}
