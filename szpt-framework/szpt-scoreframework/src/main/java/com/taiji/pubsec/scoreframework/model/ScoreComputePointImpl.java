package com.taiji.pubsec.scoreframework.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutor;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutorManage;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutorManageImpl;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.ScoreComputePoint.Listener;

/**
 * {@link ScoreComputePoint}的实现。一个{@link ScoreComputePointImpl}记录了一个{@link ScoreComputeExecutor}实现的元数据【实现类scoreExecutorType和标识ceid】
 * 并根据元数据通过{@link ScoreComputeExecutorManage}获得其实现对象
 * 也见{@link ScoreComputeExecutorManageImpl}
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scoreframework_scorepoint")
public class ScoreComputePointImpl implements ScoreComputePoint, Serializable {
	private static final long serialVersionUID = 7750811702674390951L;

	private static Logger log = LoggerFactory.getLogger(ScoreComputePointImpl.class);
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "ceid", length = 255)
	private String scoreExecutorId;
	
	@Column(name = "cetype", length = 255, nullable = false)
	private String scoreExecutorType;
	
	@OneToMany(mappedBy = "scorePoint", cascade=CascadeType.REMOVE, targetEntity=ScoreComputeRuleImpl.class, fetch=FetchType.EAGER)
	private Set<ScoreComputeRule> scoreRules = new HashSet<ScoreComputeRule>();

	@Transient
	private Listener listener;
	
	public ScoreComputeResult<Double> compute(ScoreComputeTask task, Parameter...params) {
		log.debug("检查点(id:" + id + ", description:" + description + ", ceid:" + scoreExecutorId + ", cetype:" + scoreExecutorType + ")开始执行...");
		
		//获得Executor
		ScoreComputeExecutorManage scoreExecutorManage = ScoreComputeExecutorManageImpl.getInstance();
		ScoreComputeExecutor executor = scoreExecutorManage.get(scoreExecutorType, scoreExecutorId);
		
		//通过Executor得到业务数据结果
		Object[] objs = executor.excute(params);
		Object[] inputobjs = new Object[objs.length+params.length];
		for(int i=0; i<objs.length; i++){
			inputobjs[i] = objs[i];
		}
		for(int j=0; j<params.length; j++){
			inputobjs[objs.length + j] = params[j];
		}
		
		double score = 0;
		//将业务结果代入每个rule，获得其打分结果
		for(ScoreComputeRule rule : this.getScoreRules()){
			log.debug("获得规则[rule(id:" + rule.getId() + ", description:" + rule.getDescription() + ")]");
			@SuppressWarnings("unchecked")
			ScoreComputeResult<Double> cr = (ScoreComputeResult<Double>) rule.process(inputobjs);
			log.debug("规则返回分数:" + cr.getValue().doubleValue());
			score += cr.getValue().doubleValue();
		}
		
		ScoreComputeResultImpl result = new ScoreComputeResultImpl(score);
		if(null != listener){
			listener.afterCompute((ScoreComputeTaskImpl) task, this, result, params);
		}
		
		return result;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Set<ScoreComputeRule> getScoreRules() {
		return scoreRules;
	}

	public void setScoreRules(Set<ScoreComputeRule> scoreRules) {
		this.scoreRules = scoreRules;
	}

	public String getScoreExecutorId() {
		return scoreExecutorId;
	}

	public void setScoreExecutorId(String scoreExecutorId) {
		this.scoreExecutorId = scoreExecutorId;
	}

	public String getScoreExecutorType() {
		return scoreExecutorType;
	}

	public void setScoreExecutorType(String scoreExecutorType) {
		this.scoreExecutorType = scoreExecutorType;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Listener getListener() {
		return listener;
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}


}
