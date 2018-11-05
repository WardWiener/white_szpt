package com.taiji.pubsec.scoreframework.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeRuleManage;
import com.taiji.pubsec.scoreframework.ScoreComputeRuleManageImpl;

/**
 * {@link ScoreComputeRule}的缺省实现。这个实现的process方法本身并不处理任何业务规则，只是记录实际实现的元数据【类名（type）和标识（ruleid）】，
 * 并通过{@link ScoreComputeRuleManage}获得实际的规则实现并代理给它进行规则处理。
 * 也见{@link ScoreComputeRuleManageImpl}
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scoreframework_scorerule")
public class ScoreComputeRuleImpl implements ScoreComputeRule, Serializable {

	private static final long serialVersionUID = -1365122617207243279L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "value", length = 255)
	private String value;

	@Column(name = "type", length = 200, nullable = false)
	private String type;
	
	@Column(name = "ruleid", length = 64, nullable = false)
	private String ruleId;
	
	@ManyToOne
	@JoinColumn(name = "scoreframework_scorepoint_id")
	private ScoreComputePointImpl scorePoint;
	
	@Transient
	private Listener listener;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleid) {
		this.ruleId = ruleid;
	}

	@SuppressWarnings("unchecked")
	public ScoreComputeResult<?> process(Object... params) {
		ScoreComputeRuleManage scoreRuleManage = ScoreComputeRuleManageImpl.getInstance();
		ScoreComputeRule cr = scoreRuleManage.get(type, ruleId);
		
		ScoreComputeResult<?> result = cr.process(params);
		if(null != listener){
			listener.afterProcess(this, (ScoreComputeResult<Double>) result, params);
		}
		
		return result;
	}

	public ScoreComputePointImpl getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(ScoreComputePointImpl scorePoint) {
		this.scorePoint = scorePoint;
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

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public interface Listener{
		void afterProcess(ScoreComputeRuleImpl rule, ScoreComputeResult<Double> result, Object...params);
	}
}
