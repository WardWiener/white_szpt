package com.taiji.pubsec.scoreframework.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



import org.hibernate.annotations.GenericGenerator;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.scoreframework.ComputeStrategy;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeObject;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;

import org.slf4j.Logger;

/**
 * {@link ScoreComputeTask}的缺省实现，具有父子关系，一个task可以有多个配置了系数的{@link ScoreComputePoint}
 * 也见{@link ScoreComputePointConfigImpl}
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scoreframework_scoretask")
public class ScoreComputeTaskImpl implements ScoreComputeTask, Serializable {

	private static final long serialVersionUID = 7117912408021472072L;

	private static Logger LOGGER = LoggerFactory.getLogger(ScoreComputeTaskImpl.class);
			
	@Id
	private String id;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "warnscore")
	private Double warnScore;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private ScoreComputeTaskImpl parent;
	
	@OneToMany(mappedBy = "parent", cascade=CascadeType.REMOVE, targetEntity=ScoreComputeTaskImpl.class)
	private Set<ScoreComputeTask> children = new HashSet<ScoreComputeTask>();
	
	@OneToMany(mappedBy = "scoreTask", cascade=CascadeType.REMOVE, targetEntity=ScoreComputePointConfigImpl.class, fetch=FetchType.EAGER)
	private Set<ScoreComputePointConfig> scorePointConfigs = new HashSet<ScoreComputePointConfig>();
	
	@Transient
	private Listener listener;
	@Transient
	private ComputeStrategy computeStrategy = new DefaultComputeStrategy();
	
	public String getId() {
		return this.id;
	}

	public ScoreComputeTask getParent() {
		return this.parent;
	}

	public Set<ScoreComputeTask> getChildren() {
		return this.children;
	}

	@SuppressWarnings("unchecked")
	public ScoreComputeResult<Double> run(Parameter...params) {
		LOGGER.debug("ScoreTask(id:" + id + ", description:" + description + ") 开始运行...");
		
		Map<ScoreComputeObject, ScoreComputeResult<?>> map = new HashMap<ScoreComputeObject, ScoreComputeResult<?>>();
		
		//对此计算任务的检查点进行计算
		for(ScoreComputePointConfig cpc : this.getScorePointConfigs()){
			ScoreComputePoint cp = cpc.getScorePoint();
			
			LOGGER.debug("获得积分点配置[ScorePoint(id:" + cp.getId() + ", description:" + cp.getDescription() + "),task weight:" + weight + "]");
			ScoreComputeResult<Double> cr = (ScoreComputeResult<Double>) cp.compute(this, params);
			map.put(cpc, cr);
		}
		
		//对此检查任务的子任务进行计算
		for(ScoreComputeTask scoreTask : this.getChildren()){
			ScoreComputeResult<Double> cr = (ScoreComputeResult<Double>) scoreTask.run(params);
			map.put(scoreTask, cr);
		}
		
		//计算此任务的分数
		ScoreComputeResult<Double> result = (ScoreComputeResult<Double>)this.computeStrategy.compute(map, params);
		
		LOGGER.debug("scoreTask(id:" + id + ", description:" + description + ") 计算结果 : " + result);
		
		if(null != listener){
			listener.afterRun(this, result, params);
		}
		
		return result;
	}

	@Override
	public Set<ScoreComputePointConfig> getScorePointConfigs() {
		return scorePointConfigs;
	}
	
	public List<ScoreComputePointConfig> getScorePointConfigsAsArrayList(){
		List<ScoreComputePointConfig> list = new ArrayList<ScoreComputePointConfig>() ;
		list.addAll(this.getScorePointConfigs()) ;
		return list;
	}

	public void setScorePointConfigs(Set<ScoreComputePointConfig> scorePointConfigs) {
		this.scorePointConfigs = scorePointConfigs;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWarnScore() {
		return warnScore;
	}

	public void setWarnScore(Double warnScore) {
		this.warnScore = warnScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(ScoreComputeTaskImpl parent) {
		this.parent = parent;
	}

	public void setChildren(Set<ScoreComputeTask> children) {
		this.children = children;
	}

	public Listener getListener() {
		return listener;
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public ComputeStrategy getComputeStrategy() {
		return computeStrategy;
	}

	public void setComputeStrategy(ComputeStrategy computeStrategy) {
		this.computeStrategy = computeStrategy;
	}

	/**
	 * {@link ComputeStrategy}的缺省实现，将{@link ScoreComputePointConfigImpl}或者{@link ScoreComputeTaskImpl}的执行分数与系数相乘得到最后的得分
	 * @author yucy
	 *
	 */
	private static class DefaultComputeStrategy implements ComputeStrategy{
		private static final Logger strategyLog = LoggerFactory.getLogger(DefaultComputeStrategy.class);

		@SuppressWarnings("unchecked")
		@Override
		public ScoreComputeResult<Double> compute(Map<ScoreComputeObject, ScoreComputeResult<?>> map, Parameter...params) {
			strategyLog.debug("开始计算任务分数...");
			double score = 0;
			
			for(Map.Entry<ScoreComputeObject, ScoreComputeResult<?>> entry : map.entrySet()){
				ScoreComputeObject obj = entry.getKey();
				ScoreComputeResult<Double> r = (ScoreComputeResult<Double>)entry.getValue();
				
				if(obj instanceof ScoreComputePointConfigImpl){
					ScoreComputePointConfigImpl config = ((ScoreComputePointConfigImpl)obj) ;
					Double alpha = config.getWeight();
					double ts = alpha * r.getValue().doubleValue();
					if(null != config.getListener()){
						config.getListener().afterCompute(config.getScoreTask(), config, r, new ScoreComputeResultImpl(ts), params);
					}
					score += ts;
					strategyLog.debug("分数 : " + r.getValue().doubleValue() + ", 系数 : " + alpha + ", 得分 : " + ts);
				}else if(obj instanceof ScoreComputeTaskImpl){
					Double alpha = ((ScoreComputeTaskImpl)obj).getWeight();
					double ts = alpha * r.getValue().doubleValue();
					score += ts;
					strategyLog.debug("分数 : " + r.getValue().doubleValue() + ", 系数 : " + alpha + ", 得分 : " + ts);
				}
			}
			strategyLog.debug("策略计算结果 : " + score);
			return new ScoreComputeResultImpl(score);
		}
	}
}
