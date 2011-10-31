import codec.XMPPCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import util.MongoHelper;

import java.net.InetSocketAddress;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoIMServer {

    public static void main(String[] args) throws Exception {
//        MessagePack.register(String.class);
        IoAcceptor acceptor = new NioSocketAcceptor();
        MongoHelper helper = MongoHelper.getInstance();
        if (helper == null) {
            return;
        }
//        helper.addNewUser("asd", "asd");
//        helper.addContact("asd", "eeeqwedd");
//        helper.addContact("asd", "rrr");
//
//        System.out.println(helper.auth("asd", "asdasd"));
//        System.out.println(helper.auth("asd", "asd"));

//        helper.deleteContact("asd", "eeeqwedd");

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new XMPPCodecFactory()));
        acceptor.setHandler(new SoIMHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.bind(new InetSocketAddress(5222));
    }
}
