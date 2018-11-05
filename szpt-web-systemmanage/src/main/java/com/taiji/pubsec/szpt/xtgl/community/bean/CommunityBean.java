package com.taiji.pubsec.szpt.xtgl.community.bean;

/**
 * 社区bean
 * @author sunjd
 *
 */
public class CommunityBean {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 社区名称
	 */
	private String name;
	
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 所属单位id
	 */
	private String unitId;
	
	/**
	 * 所属单位名称
	 */
	private String unitName;
	
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	private String bigCommunityId;
	
	private String bigCommunity;
	
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return	所属单位名称
	 */
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	/**
	 * @return 大社区id
	 */
	public String getBigCommunityId() {
		return bigCommunityId;
	}
	public void setBigCommunityId(String bigCommunityId) {
		this.bigCommunityId = bigCommunityId;
	}
	
	/**
	 * @return 大社区名称
	 */
	public String getBigCommunity() {
		return bigCommunity;
	}
	public void setBigCommunity(String bigCommunity) {
		this.bigCommunity = bigCommunity;
	}
}

