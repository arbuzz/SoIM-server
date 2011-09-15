package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMPPEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object o, ProtocolEncoderOutput out) throws Exception {
        String message = (String) o;
        byte[] bytes = message.getBytes("UTF-8");
        IoBuffer buffer = IoBuffer.allocate(4 + bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        out.write(buffer);
    }
}
