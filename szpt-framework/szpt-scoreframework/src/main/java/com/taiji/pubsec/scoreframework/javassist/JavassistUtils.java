/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年12月15日 下午12:46:17
 */
package com.taiji.pubsec.scoreframework.javassist;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.NotFoundException;

/**
 * @author yucy
 *
 */
public class JavassistUtils {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JavassistUtils.class);

	/**
	 * 创建访问对象
	 * @param targetclass 被调用的类型
	 * @param method 被调用的方法
	 * @param newclassname 创建的代理类名称，如果为空，则用缺省规则创建
	 * @param classloder 类装载器
	 * @return
	 */
	public static IAccess createAccess(Class<?> targetclass, Method method,
			String newclassname, ClassLoader classloder) {
		String targetclassname = targetclass.getName();

		// 如果没有指定代理类名称，则用缺省规则创建
		if(null == newclassname || "".equals(newclassname)){
			newclassname = new StringBuffer().append("IAccess$impl_")
					.append(targetclassname.replace('.', '_')).append("_").append(method.getName())
					.toString();
		}
		
		ClassPool pool = ClassPool.getDefault();
		CtClass newclass = null;
		try {
			newclass = pool.get(newclassname);
			LOGGER.debug("代理访问类[ {} ]已经被构建过了", newclassname);
			return (IAccess) Class.forName(newclassname).newInstance();
		} catch (NotFoundException e1) {
			LOGGER.debug("代理访问类[ {} ]还没有被创建，开始创建新类...", newclassname);
			newclass = pool.makeClass(newclassname);
		} catch (InstantiationException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		}

		try {
			ClassClassPath classPath = new ClassClassPath(IAccess.class);
			pool.insertClassPath(classPath);
			// 创建新的访问代理类
			newclass.addInterface(pool.get(IAccess.class.getName()));
			newclass.addConstructor(CtNewConstructor
					.defaultConstructor(newclass));

			// 添加方法
			CtClass[] parameters = new CtClass[2];
			parameters[0] = pool.get(Object.class.getName());
			parameters[1] = pool.get(Object[].class.getName());
			CtMethod invoke = new CtMethod(pool.get(Object.class.getName()),
					"invoke", parameters, newclass);
			// 构建方法体
			int methodnum = method.getParameterTypes().length;
			StringBuffer sb = new StringBuffer();
			sb.append("{ \n");
			sb.append("  Object[] params = (Object[])$args[1];\n");
			sb.append("  return ((").append(targetclassname)
					.append(")$args[0]).\n             ")
					.append(method.getName()).append("(\n");
			for (int i = 0; i < methodnum; i++) {
				if (i < methodnum - 1) {
					sb.append("               (")
							.append(method.getParameterTypes()[i].getName())
							.append(")").append("params[")
							.append(String.valueOf(i)).append("],\n");
				} else {
					sb.append("               (")
							.append(method.getParameterTypes()[i].getName())
							.append(")").append("params[")
							.append(String.valueOf(i)).append("]\n");
				}
			}
			sb.append("             ); \n");
			sb.append("}");
			String invokebody = sb.toString();
			LOGGER.debug("构建invoke的方法体：\n{}", invokebody);
			invoke.setBody(invokebody);

			newclass.addMethod(invoke);
			LOGGER.debug("代理访问类[ {} ]构建成功", newclassname);
		} catch (NotFoundException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		} catch (CannotCompileException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		}

		try {
			@SuppressWarnings("deprecation")
			IAccess r = (IAccess) newclass.toClass(classloder).newInstance();
			return r;
		} catch (InstantiationException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		} catch (CannotCompileException e) {
			LOGGER.error("", e);
			throw new RuntimeException(e);
		}
	}
}
