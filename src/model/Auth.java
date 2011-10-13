package model;

import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;
import util.OnlineList;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "auth")
public class Auth extends Packet {

    @Attribute
    private String login;

    @Attribute
    private String password;

    public Auth() {}

    @Override
    public void process(IoSession session) {
        if (MongoHelper.getInstance().auth(login, password)) {
            OnlineList.goneOnline(login);
            session.write(new BaseResponse(BaseResponse.OK));
        } else {
            OnlineList.goneOffline(login);
            session.write(new BaseResponse(BaseResponse.AUTH_ERROR));
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
