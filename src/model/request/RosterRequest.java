package model.request;

import model.Roster;
import model.request.Request;
import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import util.MongoHelper;

import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "rosterRequest")
public class RosterRequest extends Request {

    @Attribute
    private String login;

    public RosterRequest() {}

    public RosterRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean process(IoSession session) {
        List<String> contacts = MongoHelper.getInstance().getRoster(login);
        Roster roster = new Roster();
        for (String contact : contacts) {
            roster.addContact(contact);
        }
        session.write(roster);
        return true;
    }
}
