package codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMPPCodecFactory implements ProtocolCodecFactory {

    private final XMPPDecoder decoder;
    private final XMPPEncoder encoder;

    public XMPPCodecFactory() {
        decoder = new XMPPDecoder();
        encoder = new XMPPEncoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
