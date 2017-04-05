package cn.bingzhou.rpc.common;

/**
 * 请求id，请求方法名，数据类型，请求参数值，返回参数类型
 * @author Administrator
 *
 */
public class RpcRequest {
	
	private String requestId;
	private String interfaceName;
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	private String methodName;
	private Class<?>[] clss;
	private Object[] params;
	private Object returnObj;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getClss() {
		return clss;
	}
	public void setClss(Class<?>[] clss) {
		this.clss = clss;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public Object getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	
	

}
