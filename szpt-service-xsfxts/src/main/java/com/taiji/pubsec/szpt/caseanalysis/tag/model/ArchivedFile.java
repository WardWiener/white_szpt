package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 卷宗文书
 * 
 * @author dixiaofeng
 */
@Entity
@Table(name="t_xsajfx_jzws")
public class ArchivedFile implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 文号编号
	 */
	@Id
	@Column(name="wsbh", length = 50)
	private String id;
	
	/**
	 * 文书类型，标识从哪个文书表中查询
	 */
	@Id
	@Column(name="wslx", length = 100)
	private String type;
	
	/**
	 * 文书名称
	 */
	@Column(name="wsmc", length = 100)
	private String name;

	/**
	 *  文号
	 */
	@Column(name="wh", length = 100)
	private String code;
	
	/**
	 * 案件编号
	 */
	@ManyToOne
	@JoinColumn(name="ajbh")
	@Index(name="index_t_xsajfx_jzws_ajbh")
	private CriminalBasicCase basicCase;
	
	/**
	 * 执行时间
	 */
	@Column(name="zxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date excuteTime;

	/**
	 * 案管系统详情查看页面访问地址url
	 */
	@Column(name="fwdz")
	private String url;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public Date getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}