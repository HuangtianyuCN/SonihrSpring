package com.sonihr.aop;

/**
 * 被代理的对象
 * @author yihua.huang@dianping.com
 */
public class TargetSource {

	private Class<?> targetClass;
	private Class<?>[] interfaces;

	public Class<?>[] getInterfaces() {
		return interfaces;
	}

	private Object target;

	public TargetSource() {
	}

	public TargetSource(Object target,Class<?> targetClass, Class<?>... interfaces) {
		this.targetClass = targetClass;
		this.interfaces = interfaces;
		this.target = target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTarget() {
		return target;
	}
}
