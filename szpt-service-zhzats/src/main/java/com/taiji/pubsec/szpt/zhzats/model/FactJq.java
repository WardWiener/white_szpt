package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 警情
 * @author 
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_jq")
public class FactJq {
	
	@Id
	private String id ;

	/**
	 * 警情编码
	 * 索引
	 */
	@Column(name="bm")
	@Index(name="index_t_zhzats_jwzh_jq_bm")
	private String code ;
	
	/**
	 * 警情名称
	 */
	@Column(name="jqmc")
	private String name ;
	
	/**
	 * 警情地址
	 */
	@Column(name="jqdz")
	private String addr ;

	/**
	 * 警情类型名称
	 * 索引
	 */
	@Column(name="ty_jqlx_mc")
	private String jqlxName ;
	/**
	 * 警情类型编码
	 * 索引
	 */
	@Column(name="ty_jqlx_bm")
	@Index(name="index_t_zhzats_jwzh_jq_ty_jqlx_bm")
	private String jqlxCode ;
	
	/**
	 * 警情来源
	 */
	@Column(name="jqly")
    private String jqSource ;	

	/**
	 * 警情概要
	 */
	@Column(name="jqgy")
	private String jqSummary ;
	
	/**
	 * 紧急程度
	 */
	@Column(name="jjcd")
    private String urgencyLevel  ;
	
	/**
	 * 报警人
	 */
	@Column(name="bjr")
	private String callingPerson ;
	
	/**
	 * 接警人
	 */
	@Column(name="jjr")
	private String jjr ;
	
	/**
	 * 报警人电话
	 */
	@Column(name="bjrdh")
	private String callingPersonPhone ;
	
	/**
	 * 报警时间
	 */
	@Column(name="bjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date callingDate ;
	
	/**
	 * 接警时间
	 */
	@Column(name="jjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerAlarmDate  ;
	
	/**
	 * 派出所编码
	 * 索引
	 */
	@Column(name="ty_dw_pcs_bm")
	@Index(name="index_t_zhzats_jwzh_jq_ty_dw_pcs_bm")
	private String pcsCode ;
	
	/**
	 * 派出所名称
	 * 索引
	 */
	@Column(name="ty_dw_pcs_mc")
	private String pcsName ;
	
	/**
	 * 村区编码
	 * 索引
	 */
	@Column(name="ty_cq_bm")
	@Index(name="index_t_zhzats_jwzh_jq_ty_cq_bm")
	private String countryCode ;
	
	/**
	 * 村区名称
	 * 索引
	 */
	@Column(name="ty_cq_mc")
	private String countryName ;
	
	/**
	 * 经度
	 */
	@Column(name="jd")
	private Double longitude ;
	
	/**
	 * 纬度
	 */
	@Column(name="wd")
	private Double latitude ;
	
	/**
	 * 发生时间
	 * 索引
	 */
	@Column(name="fssj")
	@Temporal(TemporalType.TIMESTAMP)
	@Index(name="index_t_zhzats_jwzh_jq_fssj")
	private Date occurrenceTime  ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJqSource() {
		return jqSource;
	}

	public void setJqSource(String jqSource) {
		this.jqSource = jqSource;
	}

	public String getJqSummary() {
		return jqSummary;
	}

	public void setJqSummary(String jqSummary) {
		this.jqSummary = jqSummary;
	}

	public String getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(String urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}
	public String getCallingPerson() {
		return callingPerson;
	}

	public void setCallingPerson(String callingPerson) {
		this.callingPerson = callingPerson;
	}

	public Date getAnswerAlarmDate() {
		return answerAlarmDate;
	}

	public void setAnswerAlarmDate(Date answerAlarmDate) {
		this.answerAlarmDate = answerAlarmDate;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCallingPersonPhone() {
		return callingPersonPhone;
	}

	public void setCallingPersonPhone(String callingPersonPhone) {
		this.callingPersonPhone = callingPersonPhone;
	}

	public Date getCallingDate() {
		return callingDate;
	}

	public void setCallingDate(Date callingDate) {
		this.callingDate = callingDate;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getJqlxName() {
		return jqlxName;
	}

	public void setJqlxName(String jqlxName) {
		this.jqlxName = jqlxName;
	}

	public String getJqlxCode() {
		return jqlxCode;
	}

	public void setJqlxCode(String jqlxCode) {
		this.jqlxCode = jqlxCode;
	}

	public String getPcsCode() {
		return pcsCode;
	}

	public void setPcsCode(String pcsCode) {
		this.pcsCode = pcsCode;
	}

	public String getPcsName() {
		return pcsName;
	}

	public void setPcsName(String pcsName) {
		this.pcsName = pcsName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getJjr() {
		return jjr;
	}

	public void setJjr(String jjr) {
		this.jjr = jjr;
	}
	
}	
