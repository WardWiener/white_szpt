package com.taiji.pubsec.szpt.xtgl.person;


import java.util.List;

/**
 * 
 * @author xinfan
 *
 */

public class PersonAdminPageBean {
	private List<BasePersonData> sexList;// 人员性别
	private List<BasePersonData> statusList;// 人员状态
	private String unitCode;// 本单位编码
	private String unitName;// 本单位名称
	private String isXJUnit;// 是否包含下级单位
	private String unitId;

	public List<BasePersonData> getSexList() {
		return sexList;
	}

	public void setSexList(List<BasePersonData> sexList) {
		this.sexList = sexList;
	}

	public List<BasePersonData> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<BasePersonData> statusList) {
		this.statusList = statusList;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getIsXJUnit() {
		return isXJUnit;
	}

	public void setIsXJUnit(String isXJUnit) {
		this.isXJUnit = isXJUnit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}
