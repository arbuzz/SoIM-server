package model.request;

import model.BaseResponse;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "deleteContact")
public class DeleteContactRequest extends Request {

    @Attribute
    private String user;
    @Attribute
    private String contact;

    public DeleteContactRequest() {}

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
        MongoHelper.getInstance().deleteContact(user, contact);
        session.write(new BaseResponse(BaseResponse.OK));
        return true;
    }
}
