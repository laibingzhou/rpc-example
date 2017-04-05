package cn.bingzhou.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 对数据序列化
 * @author Administrator
 *
 */
public class RpcEncoder extends MessageToByteEncoder{
	
	Class<?> serialClass ;

	public <T> RpcEncoder(Class<T> serialClass) {
		this.serialClass=serialClass;
	}

	@Override
	protected void encode(ChannelHandlerContext context, Object obj, ByteBuf byteBuf)
			throws Exception {
		
		if(serialClass.isInstance(obj)){
			byte[] serialize = SerializationUtils.serialize(obj);
			byteBuf.writeInt(serialize.length);
			byteBuf.writeBytes(serialize);
		}
	}

}
