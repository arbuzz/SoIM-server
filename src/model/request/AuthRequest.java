package model.request;

import model.AuthResponse;
import model.request.Request;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.Config;
import util.MongoHelper;
import util.OnlineList;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "auth")
public class AuthRequest extends Request {

    @Attribute
    private String login;

    @Attribute
    private String password;

    public AuthRequest() {}

    @Override
    public void process(IoSession session) {
        if (MongoHelper.getInstance().auth(login, password)) {
            OnlineList.getInstance().goneOnline(login);
            session.setAttribute(Config.LOGIN, login);
            session.write(new AuthResponse(AuthResponse.OK));
        } else {
            OnlineList.getInstance().goneOffline(login);
            session.write(new AuthResponse(AuthResponse.AUTH_ERROR));
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
