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
@Root(name = "addContact")
public class AddContactRequest extends Request {

    @Attribute
    private String user;

    @Attribute
    private String contact;

    public AddContactRequest() {}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean process(IoSession session) {
        boolean success = MongoHelper.getInstance().addContact(user, contact);
        if (success) {
            session.write(new BaseResponse(BaseResponse.OK));
            return true;
        } else {
            session.write(new BaseResponse(BaseResponse.ADD_CONTACT_ERROR));
            return false;
        }
    }
}
