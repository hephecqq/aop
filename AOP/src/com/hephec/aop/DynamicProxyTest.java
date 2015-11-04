package com.hephec.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class DynamicProxyTest {

	/**
	 * 关于代理的一些细节：
	 * 1.需要一个被代理的对象
	 * 2.一般，Proxy.newInstance()的返回值是一个被代理对象实现的接口类型
	 * 也可以是其他接口的类型
	 * 3.final使得局部变量延长生命周期
	 * 
	 * */
	
	@Test
	public void test(){
		
	  final	Calculator target=new CalculatorImpl();
		
		//Proxy.newProxyInstance(loader, interfaces, h);
		
		/**
		 * 1、ClassLoader:由动态代理产生的对象有哪些类加载器来加载，通常情况下和被代理对象使用一样的类加载器
		 * 2、Class<?>[] 由动态代理产生的对象必须实现的接口的Class属性
		 * 3、InvocationHandler:当具体调用代理对象的方法时，将产生什么行为
		 * 
		 * */
		Calculator proxy=(Calculator) Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{Calculator.class}, new InvocationHandler() {
			
			/**
			 * proxy:
			 * method:正在被调用的方法
			 * args:调用方法时传入的参数
			 * 
			 * */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object result=null;
				//System.out.println("start logging");
				try {
					//开始事务
					//前置通知
					result=method.invoke(target, args);
				} catch (Exception e) {
					//回滚事务
					//异常通知
					e.printStackTrace();
				}
				//System.out.println("end logging");
				return result;
				//后置通知
				//环绕通知
			}
		});
		
		System.out.println(proxy.add(10, 20));
	}
}
