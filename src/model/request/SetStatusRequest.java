package model.request;

import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.OnlineList;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "setStatus")
public class SetStatusRequest extends Request {

    @Attribute
    private String user;
    @Attribute
    private String status;

    public SetStatusRequest() {}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean process(IoSession session) {
        OnlineList.getInstance().setStatus(user, status);
        return true;
    }
}
