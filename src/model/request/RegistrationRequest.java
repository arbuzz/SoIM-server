package model.request;

import model.BaseResponse;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "register")
public class RegistrationRequest extends Request {

    @Attribute
    private String login;

    @Attribute
    private String password;

    public RegistrationRequest() {}

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

    @Override
    public boolean process(IoSession session) {
        boolean success = MongoHelper.getInstance().addNewUser(login, password);
        if (success)
            session.write(new BaseResponse(BaseResponse.OK));
        else
            session.write(new BaseResponse(BaseResponse.REGISTRATION_ERROR));
        return success;
    }
}
