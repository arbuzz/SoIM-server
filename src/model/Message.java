package model;

import org.apache.mina.core.session.IoSession;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class Message extends Packet {

    @Element
    private String body;
    @Attribute
    private String to;
    @Attribute
    private String from;

    @Override
    public void process(IoSession session) {
        session.write("adasdasd");
    }
}
