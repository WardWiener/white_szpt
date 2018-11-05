/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年12月14日 下午3:50:53
 */
package com.taiji.pubsec.scoreframework.javassist;

/**
 * 被{@link JavassistUtils}创建的编织类，用于访问被封装类的某个方法
 * 使用编织的方式调用效率高于reflect
 * 
 * @author yucy
 *
 */
public interface IAccess {

	/**
	 * 调用对象的被编织过的特定方法
	 * @param obj 被调用的对象
	 * @param args 特定方法的参数
	 * @return
	 */
	public Object invoke(Object obj, Object... args);
}
