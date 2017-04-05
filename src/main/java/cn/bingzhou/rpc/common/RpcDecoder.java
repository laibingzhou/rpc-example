package cn.bingzhou.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


public class RpcDecoder  extends ByteToMessageDecoder{
	
	private Class<?> genericClass;

	public <T> RpcDecoder(Class<T> class1) {
		this.genericClass=class1;
	}

	/**
	 * 对数据解码
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx,
			ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes()<4){
			return;
		}
		in.markReaderIndex();
		int datalength=in.readInt();
		if(datalength<0){
			ctx.close();
		}
		if(in.readableBytes()<datalength){
			in.resetReaderIndex();
		}
		byte[] data=new byte[datalength];
		in.readBytes(data);
		Object deserial = SerializationUtils.deserial(data, genericClass);
		out.add(deserial);
	}		
}
