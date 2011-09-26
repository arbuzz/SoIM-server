package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import util.XMLUtil;

import java.nio.ByteBuffer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMPPEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object o, ProtocolEncoderOutput out) throws Exception {
        byte[] bytes = XMLUtil.serialize(o).getBytes();
        IoBuffer buffer = IoBuffer.allocate(bytes.length + 4);
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        out.write(buffer);
    }
}
