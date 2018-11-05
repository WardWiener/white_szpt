package com.taiji.pubsec.szpt.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 村居
 * @author sunjd
 *
 */
@Entity
@Table(name = "t_og_cj")
public class Community {

	/**
	 * id
	 */
	@Id
	@GenericGenerator(name="uuid", strategy="uuid2")
	@GeneratedValue(generator="uuid")
	private String id;
	
	/**
	 * 村居名称
	 */
	@Column(name = "mc", nullable = false)
	private String name;
	
	/**
	 * 村居编码
	 */
	@Column(name = "bm", nullable = false)
	private String code;
	
	/**
	 * 单位id
	 */
	@Column(name = "dwid", nullable = false)
	@Index(name="index_t_og_cj_dwid")
	private String unitId;
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj", nullable = false)
	private Date updateTime;
	
	/**
	 * 社区id,字典项
	 */
	@Column(name = "dsq_id")
	private String bigCommunityId;
	
	/**
	 * @return id 
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return name 社区名称
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return code 编码
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return	unitId 所属单位id
	 */
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	/**
	 * @return	updateTime 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return	大社区id
	 */
	public String getBigCommunityId() {
		return bigCommunityId;
	}
	public void setBigCommunityId(String bigCommunityId) {
		this.bigCommunityId = bigCommunityId;
	}

	
}
