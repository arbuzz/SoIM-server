package model.request;

import model.BaseResponse;
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
    public boolean process(IoSession session) {
        if (MongoHelper.getInstance().auth(login, password)) {
            session.setAttribute(Config.LOGIN, login);
            session.write(new BaseResponse(BaseResponse.OK));
            OnlineList.getInstance().goneOnline(login, session);
            return true;
        } else {
            OnlineList.getInstance().goneOffline(login);
            session.write(new BaseResponse(BaseResponse.AUTH_ERROR));
            return false;
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
