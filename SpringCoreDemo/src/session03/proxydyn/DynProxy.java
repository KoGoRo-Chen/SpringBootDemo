package session03.proxydyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//動態代理
public class DynProxy {
	// 被代理的物件
		private Object object;
		
		public DynProxy(Object object) { // 注入被代理的物件
			this.object = object;
		}
		
		// 取得被代理的物件
		public Object getProxy() {
			Object proxyObj = null;
			// 1. 載入類別器
			ClassLoader loader = object.getClass().getClassLoader();
			// 2. 被代理的物件所實作的介面
			Class<?>[] interfaces = object.getClass().getInterfaces();
			// 3. 處理代理的實現
			InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
				Object result = null;
				
				// 呼叫前置通知
				MyPrintArgsAspect.before(interfaces, method, args);
				
				// 調用被代理物件的業務方法
				try {
					result = method.invoke(object, args);
				} catch (Exception e) {
					// 呼叫例外通知 
					MyPrintArgsAspect.throwing(e);
				}
				
				// 呼叫後置通知
				MyPrintArgsAspect.end();
				
				return result;
			};
			
			// 4. 得到代理物件
			proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
			return proxyObj;
		}
}
