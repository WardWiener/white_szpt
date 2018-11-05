package com.taiji.pubsec.szpt.score.util.rules.groovy.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.szpt.score.util.rules.groovy.SzptScoreGroovyRuleService;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Entity
@Table(name = "t_scoreframework_szpt_groovyrule")
public class SzptScoreGroovyRule implements ScoreComputeRule{
	
	private static Logger LOGGER = LoggerFactory.getLogger(SzptScoreGroovyRule.class);

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "script", length = 3048)
	private String script;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "value", length = 255)
	private String value;
	
	public static SzptScoreGroovyRule getScoreRule(String id){
		SzptScoreGroovyRuleService ruleService = (SzptScoreGroovyRuleService) SpringContextUtil.getApplicationContext().getBean(SzptScoreGroovyRuleService.class) ;
		return ruleService.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ScoreComputeResult<?> process(Object... params) {
		
		Binding bind = new Binding();
		
		Map<String, DefaultParameter> map = getParameterMap(params) ;
		
		GroovyShell shell = new GroovyShell(bind);
		
		shell.setVariable("paramsMap", map);
		
		LOGGER.info("传入参数：" + map);
		LOGGER.info("计算脚本：\r\n" + this.getScript());
		LOGGER.info("可独立运行的脚本：\r\n" + generateScriptToTrace(map, this.getScript()));

		Map<String, Object> resultMap = (Map<String, Object>)shell.evaluate(this.getScript());
		JSONObject allResult = JSONObject.fromObject(resultMap) ;
		
		ScoreComputeResult<Double> r = new ScoreComputeResultImpl(Double.valueOf(resultMap.get("result").toString()), allResult.toString());
		
		return r;
	}
	
	private String generateScriptToTrace(Map<String, DefaultParameter> map, String script){

		String paramMapStr = JSONObject.fromObject(map).toString() ;
		
		paramMapStr = paramMapStr.replaceAll("[{]", "[");
		paramMapStr = paramMapStr.replaceAll("[}]", "]");
		
		StringBuffer sb = new StringBuffer("def paramsMap = "+paramMapStr+" ;") ;
		
		sb.append("\r\n") ;
		sb.append("\r\n") ;
		
		sb.append(script) ;
		
		return sb.toString() ;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, DefaultParameter> getParameterMap(Object... params){
		Map<String, DefaultParameter> map = new HashMap<String, DefaultParameter>() ;
		for(Object param:params){
			if(param instanceof DefaultParameter){
				DefaultParameter dp = (DefaultParameter)param ;
				map.put(dp.getTag(), dp) ;
			}
		}
		
		return JSONObject.fromObject(map) ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
