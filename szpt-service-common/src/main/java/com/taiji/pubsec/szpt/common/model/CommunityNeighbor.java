package com.taiji.pubsec.szpt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 村居相邻关系
 * @author sunjd
 *
 */
@Entity
@Table(name = "t_og_cjxlgx")
public class CommunityNeighbor {
	
	/**
	 * id
	 */
	@Id
	@GenericGenerator(name="uuid", strategy="uuid2")
	@GeneratedValue(generator="uuid")
	private String id;

	/**
	 * 主社区id
	 */
	@Column(name = "zsq_id", nullable = false)
	private String from;
	
	/**
	 * 相邻社区id
	 */
	@Column(name = "xlsq_id", nullable = false)
	private String to;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	
	
	
}
