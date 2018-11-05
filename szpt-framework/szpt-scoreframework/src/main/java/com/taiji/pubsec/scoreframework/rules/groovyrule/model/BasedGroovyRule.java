package com.taiji.pubsec.scoreframework.rules.groovyrule.model;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.rules.groovyrule.BasedGroovyRuleService;

/**
 * 基于Groovy脚本的规则实现。
 * 其process方法的第一个参数在脚本中以x表示。通过写脚本可以实现各种规则，如区间规则：
 * if(x<0) return 0 
 * else if(0<=x && x<100) return 50
 * else if(100<=x) return 100
 * else return 200
 * 
 * 注意：方法getScoreRule依赖{@link SpringContextUtil}，该类需要在Spring容器中初始化以便获得Spring上下文对象：
 * <bean id="springContextUtil" class="com.taiji.pubsec.scoreframework.SpringContextUtil"></bean>
 * 其id属性可以任意指定。
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scoreframework_groovyrule")
public class BasedGroovyRule implements ScoreComputeRule {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "script", length = 1024)
	private String script;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "value", length = 255)
	private String value;
	
	public String getId() {
		return this.id;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static BasedGroovyRule getScoreRule(String id){
		BasedGroovyRuleService ruleService = (BasedGroovyRuleService) SpringContextUtil.getBean("basedGroovyRuleService");
		return ruleService.findById(id);
	}
	
	@Override
	public ScoreComputeResult<?> process(Object... params) {
		
		Binding bind = new Binding();
		for(int i=0; i<params.length; i++){
			bind.setVariable("x"+i, params[i]);
		}
		GroovyShell shell = new GroovyShell(bind);

		Integer result = (Integer)shell.evaluate(script);
		ScoreComputeResult<Double> r = new ScoreComputeResultImpl(result.doubleValue());
		
		return r;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
