package model.request;

import model.FindResponse;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "find")
public class FindRequest extends Request {

    @Attribute(required = false)
    private String like;

    public FindRequest() {}

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    @Override
    public boolean process(IoSession session) {
        FindResponse response = new FindResponse(MongoHelper.getInstance().findUsers(like));
        session.write(response);
        return true;
    }
}
