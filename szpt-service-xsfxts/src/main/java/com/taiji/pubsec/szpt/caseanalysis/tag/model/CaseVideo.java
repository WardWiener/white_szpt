package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;

/**
 * ��Ѻ��Ʒ��ӳ��t_xsajfx_spzl
 * @author dixiaofeng
 * @version 1.0
 * @created 20-����-2017 10:21:28
 */
@Entity(name="com.taiji.pubsec.szpt.caseanalysis.score.model.CaseVideo")
@Table(name="t_xsajfx_spzl")
public class CaseVideo {

	/**
	 * 基本案件信息
	 */
	@ManyToOne
	@JoinColumn(name = "jban_id")
	private CriminalBasicCase cs;
	/**
	 * 视频结束时间
	 */
	@Column(name="jssj")
	private Date endTime;
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	/**
	 * 视频起始时间
	 */
	@Column(name="kssj")
	private Date startTime;
	/**
	 * 视频访问url
	 */
	private String url;
	
	public CriminalBasicCase getCs() {
		return cs;
	}
	public void setCs(CriminalBasicCase cs) {
		this.cs = cs;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	


}