package model;

import org.apache.mina.core.session.IoSession;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public abstract class Packet {

    public abstract void process(IoSession session);
}
