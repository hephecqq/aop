package com.hephec.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class DynamicProxyTest {

	/**
	 * ���ڴ����һЩϸ�ڣ�
	 * 1.��Ҫһ��������Ķ���
	 * 2.һ�㣬Proxy.newInstance()�ķ���ֵ��һ�����������ʵ�ֵĽӿ�����
	 * Ҳ�����������ӿڵ�����
	 * 3.finalʹ�þֲ������ӳ���������
	 * 
	 * */
	
	@Test
	public void test(){
		
	  final	Calculator target=new CalculatorImpl();
		
		//Proxy.newProxyInstance(loader, interfaces, h);
		
		/**
		 * 1��ClassLoader:�ɶ�̬��������Ķ�������Щ������������أ�ͨ������ºͱ��������ʹ��һ�����������
		 * 2��Class<?>[] �ɶ�̬��������Ķ������ʵ�ֵĽӿڵ�Class����
		 * 3��InvocationHandler:��������ô������ķ���ʱ��������ʲô��Ϊ
		 * 
		 * */
		Calculator proxy=(Calculator) Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{Calculator.class}, new InvocationHandler() {
			
			/**
			 * proxy:
			 * method:���ڱ����õķ���
			 * args:���÷���ʱ����Ĳ���
			 * 
			 * */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object result=null;
				//System.out.println("start logging");
				try {
					//��ʼ����
					//ǰ��֪ͨ
					result=method.invoke(target, args);
				} catch (Exception e) {
					//�ع�����
					//�쳣֪ͨ
					e.printStackTrace();
				}
				//System.out.println("end logging");
				return result;
				//����֪ͨ
				//����֪ͨ
			}
		});
		
		System.out.println(proxy.add(10, 20));
	}
}
