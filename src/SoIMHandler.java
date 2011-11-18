import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.core.Persister;
import util.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoIMHandler extends IoHandlerAdapter {

    private static Logger logger = Logger.getLogger("soim.handler");

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        XMLLightweightParser parser = new XMLLightweightParser("UTF-8");
        session.setAttribute(Config.PARSER, parser);
        session.setAttribute(Config.SERIALIZER, new Persister());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String stanza = (String) message;
        XMLUtil.parse(stanza).process(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        String login = (String) session.getAttribute(Config.LOGIN);
        if (login != null) {
            OnlineList.getInstance().goneOffline(login);
        }
        logger.log(Level.SEVERE, login + " gone offline");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        String login = (String) session.getAttribute(Config.LOGIN);
        if (login != null) {
            OnlineList.getInstance().goneOffline(login);
        }
        logger.log(Level.SEVERE, "exception in soim handler", cause);
        logger.log(Level.SEVERE, login + " gone offline");
    }
}
