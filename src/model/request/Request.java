package model.request;

import org.apache.mina.core.session.IoSession;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public abstract class Request {

    public abstract void process(IoSession session);
}
