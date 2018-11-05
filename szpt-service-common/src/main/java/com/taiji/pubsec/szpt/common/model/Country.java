package com.taiji.pubsec.szpt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 村区
 * @author XIEHF
 */
@Entity
@Table(name="t_ty_jwzh_cq")
@Deprecated
public class Country {
	
	@Id
	private String id ;
	
	/**
	 * 村区编码
	 */
	@Column(name="bm")
	private String code ;
	
	/**
	 * 村区名称
	 */
	@Column(name="mc")
	private String name ;

	/**
	 * 社区code
	 */
	@Column(name="sqbm")
	@Index(name="index_t_ty_jwzh_cq_sqbm")
	private String communityCode ;
	
	/**
	 * 社区名称
	 */
	@Column(name="sqmc")
	private String communityName ;
	
	/**
	 * 派出所编码
	 */
	@Column(name="pcsbm")
	@Index(name="index_t_ty_jwzh_cq_pcsbm")
	private String pcsCode ;
	
	/**
	 * 派出所名称
	 */
	@Column(name="pcsmc")
	private String pcsName ;
	
	/**
	 * 层级
	 */
	@Column(name="lv")
	private Integer level ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPcsName() {
		return pcsName;
	}

	public void setPcsName(String pcsName) {
		this.pcsName = pcsName;
	}

	public String getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPcsCode() {
		return pcsCode;
	}

	public void setPcsCode(String pcsCode) {
		this.pcsCode = pcsCode;
	}

	
	
}
