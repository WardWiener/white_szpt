package com.taiji.pubsec.scoreframework;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.scoreframework.javassist.IAccess;
import com.taiji.pubsec.scoreframework.javassist.JavassistUtils;

/**
 * {@link ScoreComputeExecutorManage}的实现。
 * 要求每个{@link ScoreComputeExecutor}的实现必须符合一个条件：
 * 实现一个签名为getScoreExecutor(String id)的static方法，该方法返回{@link ScoreComputeExecutor}实现的一个实例对象。
 * @author yucy
 *
 */
public class ScoreComputeExecutorManageImpl implements ScoreComputeExecutorManage {
	private static Logger log = LoggerFactory.getLogger(ScoreComputeExecutorManageImpl.class);
	
	private final static ScoreComputeExecutorManageImpl executorManage = new ScoreComputeExecutorManageImpl();

	private ScoreComputeExecutorManageImpl() {
	}
	
	public static ScoreComputeExecutorManageImpl getInstance(){
		return executorManage;
	}

	public ScoreComputeExecutor get(String type, String id) {
		ScoreComputeExecutor executor = null;
		try {
//			Method method = Class.forName(type).getMethod("getScoreExecutor", String.class);
//			executor = (ScoreComputeExecutor) method.invoke("getScoreExecutor", new Object[]{id});

			//使用javassist创建编织的新类，访问该类的静态方法，加速调用过程
			Class<?>[] parameterTypes = new Class<?>[1];
			parameterTypes[0] = Class.forName(String.class.getName());
			Class<?> c = Class.forName(type);
			Method method = c.getMethod("getScoreExecutor", parameterTypes);
			IAccess access = JavassistUtils.createAccess(c, method, null, this.getClass().getClassLoader());
			
			executor = (ScoreComputeExecutor)access.invoke(null, new Object[]{id});

			return executor;
		} catch (NoSuchMethodException e) {
			log.error(type + " 必须实现静态方法 : getScoreExecutor(String id).");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		return null;
	}

//	private String buildKey(CheckExecutor executor){
//		return executor.getClass().getName() + "_" + executor.getId();
//	}
//
//	private String buildKey(String type, String id){
//		return type + "_" + id;
//	}
}
