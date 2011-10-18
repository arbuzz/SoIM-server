package model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import util.OnlineList;

import java.util.ArrayList;
import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "roster")
public class Roster {

    @ElementList(inline = true)
    private List<RosterElement> contacts = new ArrayList<RosterElement>();

    public Roster() {}

    public List<RosterElement> getContacts() {
        return contacts;
    }

    public void setContacts(List<RosterElement> contacts) {
        this.contacts = contacts;
    }

    public void addContact(String user) {
        contacts.add(new RosterElement(user, OnlineList.getInstance().isOnline(user)));
    }
}
