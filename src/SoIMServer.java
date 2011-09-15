import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoIMServer {

    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
    }
}
