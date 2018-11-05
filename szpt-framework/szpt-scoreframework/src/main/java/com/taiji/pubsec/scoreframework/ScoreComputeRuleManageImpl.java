package com.taiji.pubsec.scoreframework;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.scoreframework.javassist.IAccess;
import com.taiji.pubsec.scoreframework.javassist.JavassistUtils;

/**
 * {@link ScoreComputeRuleManage}的实现。
 * 要求每个{@link ScoreComputeRule}的实现必须符合一个条件：
 * 实现一个签名为getScoreRule(String id)的static方法，该方法返回{@link ScoreComputeRule}实现的一个实例对象。
 * @author yucy
 *
 */
public class ScoreComputeRuleManageImpl implements ScoreComputeRuleManage {
	
	private static Logger log = LoggerFactory.getLogger(ScoreComputeRuleManageImpl.class);
		
	private final static ScoreComputeRuleManageImpl scoreRuleManage = new ScoreComputeRuleManageImpl();

	private ScoreComputeRuleManageImpl() {
	}
	
	public static ScoreComputeRuleManageImpl getInstance(){
		return scoreRuleManage;
	}

	public ScoreComputeRule get(String type, String id) {		
		ScoreComputeRule result = null;
		try {
//			Method method = Class.forName(type).getMethod("getScoreRule", String.class);
//			result = (ScoreComputeRule) method.invoke(null, new Object[]{id});
			
			//使用javassist创建编织的新类，访问该类的静态方法，加速调用过程
			Class<?>[] parameterTypes = new Class<?>[1];
			parameterTypes[0] = Class.forName(String.class.getName());
			Class<?> c = Class.forName(type);
			Method method = c.getMethod("getScoreRule", parameterTypes);
			IAccess access = JavassistUtils.createAccess(c, method, null, this.getClass().getClassLoader());
			
			result = (ScoreComputeRule)access.invoke(null, new Object[]{id});
			return result;
		} catch (NoSuchMethodException e) {
			log.error(type + " 必须实现静态方法 : getScoreRule(String id).");
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

}
