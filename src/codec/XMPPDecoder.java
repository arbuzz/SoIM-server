package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import util.Config;
import util.XMLLightweightParser;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMPPDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        XMLLightweightParser parser = (XMLLightweightParser) session.getAttribute(Config.PARSER);
        parser.read(in);
        if (parser.areThereMsgs()) {
            for (String stanza : parser.getMsgs()) {
                out.write(stanza);
            }
        }
        return !in.hasRemaining();
    }
}
