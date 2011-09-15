import codec.XMPPCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoIMServer {

    public static void main(String[] args) throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new XMPPCodecFactory()));
        acceptor.setHandler(new SoIMHandler());
        acceptor.bind(new InetSocketAddress(5222));
    }
}
