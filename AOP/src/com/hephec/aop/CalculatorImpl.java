package com.hephec.aop;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		System.out.println(a+b);
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		System.out.println(a-b);
		return a-b;
	}

	@Override
	public int mul(int a, int b) {
		System.out.println(a*b);
		return a*b;
	}

	@Override
	public int div(int a, int b) {
		System.out.println(a/b);
		return a/b;
	}

}
