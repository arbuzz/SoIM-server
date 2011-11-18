package model.request;

import com.sun.xml.internal.ws.api.message.Packet;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;
import util.OnlineList;

import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "presence")
public class Presence extends Request {

    public static final int ONLINE = 1;
    public static final int OFFLINE = 9;

    @Attribute
    private String name;
    @Attribute
    private int status;

    public Presence() {}

    public Presence(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean process(IoSession session) {
        List<String> contacts = MongoHelper.getInstance().getRoster(name);
        for (String contact : contacts) {
            IoSession sessionToSend = OnlineList.getInstance().getSession(contact);
            if (sessionToSend != null) {
                sessionToSend.write(this);
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
