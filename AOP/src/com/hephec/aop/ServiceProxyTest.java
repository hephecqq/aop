package com.hephec.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ServiceProxyTest {

	
	
	
	@Test
	public void test(){
		Service target=new ServiceImpl();
		Service proxy=(Service) Proxy.newProxyInstance(Service.class.getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object  result=null;
				System.out.println("开始事务");
				try {
				result=method.invoke(target, args);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("回滚事务");
				}
				
				return result;
			}
		});
		
		proxy.addNew(new Person(3,"wangwu"));
		proxy.delete(10);
	}
}
