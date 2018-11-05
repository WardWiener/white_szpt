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

import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputePoint.Listener;

/**
 * {@link ScoreComputePointConfig}的缺省实现
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scoreframework_scorepointcfg")
public class ScoreComputePointConfigImpl implements ScoreComputePointConfig, Serializable {
	
	private static final long serialVersionUID = -2634744844138952324L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "weight", nullable = false)
	private Double weight;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "scoreframework_scorepoint_id")
	private ScoreComputePointImpl scorePoint;
	
	@ManyToOne
	@JoinColumn(name = "scoreframework_scoretask_id")
	private ScoreComputeTaskImpl scoreTask;
	
	@Transient
	private Listener listener;
	
	@Override
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public ScoreComputePointImpl getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(ScoreComputePointImpl scorePoint) {
		this.scorePoint = scorePoint;
	}

	public ScoreComputeTaskImpl getScoreTask() {
		return scoreTask;
	}

	public void setScoreTask(ScoreComputeTaskImpl scoreTask) {
		this.scoreTask = scoreTask;
	}

	@Override
	public Listener getListener() {
		return listener;
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
