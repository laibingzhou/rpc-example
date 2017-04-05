package cn.bingzhou.rpc.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisSerializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class SerializationUtils {
	
	
	
	private  static Map<Class<?>,Schema<?> > cacheSchema=new ConcurrentHashMap<Class<?>, Schema<?>>();
	
	private static Objenesis objnew=new ObjenesisSerializer(true);
	
	
	private  SerializationUtils() {
	}
	
	/**
	 * 序列化
	 * @param obj 对对象进行序列化
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serialize(T obj) {
		Class<T> cls = (Class<T>)obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema=getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(),e);
		}finally{
			buffer.clear();
		}
	}
	/**
	 * 获取类的schema
	 * @param clazz
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static  <T> Schema<T> getSchema(Class<T> clazz){
		Schema<T> schema = (Schema<T>) cacheSchema.get(clazz);
		if(schema==null){
			schema = RuntimeSchema.createFrom(clazz);
			if(schema!=null){
				cacheSchema.put(clazz,schema);
			}
		}
		return schema;
		
	}
	
	/**
	 * 反序列化
	 * @param data
	 * @param cls
	 * @return
	 */
	public static <T> T deserial(byte[] data,Class<T> cls){
		try{
			T message = objnew.newInstance(cls);
			Schema<T> schema = getSchema(cls);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		}catch(Exception e){
			throw new IllegalStateException(e.getMessage(), e);
		}
	}


}
