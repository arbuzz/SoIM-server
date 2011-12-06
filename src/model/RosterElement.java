package model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "contact")
public class RosterElement {

    @Text
    private String name;
    @Attribute
    private boolean online;
    @Attribute(required = false)
    private String textStatus;

    public RosterElement() {}

    public RosterElement(String name, boolean online, String textStatus) {
        this.name = name;
        this.online = online;
        this.textStatus = textStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }
}
