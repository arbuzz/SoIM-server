package model.request;

import model.request.Request;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;
import util.OnlineList;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "message")
public class Message extends Request {

    @Text
    private String body;
    @Attribute
    private String to;
    @Attribute
    private String from;

    @Override
    public boolean process(IoSession session) {
        IoSession userSession = OnlineList.getInstance().getSession(to);
        if (userSession != null) {
            userSession.write(this);
        }
//        session.write(this);
        return true;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
