package com.taiji.pubsec.szpt.xtgl.unit;

import com.taiji.pubsec.szpt.xtgl.OrganizationBean;



/**
 * 单位Bean
 * 
 * @author xinfan
 *
 */
public class UnitBean extends OrganizationBean {
	private String id;
	private String prop;// 属性判断是单位还是部门
	private String unitNum;//组织机构代码
	private String unitName;// 单位名称
	private String unitCode;// 单位code 现在做成和code一样
	private String parUnitNum;// 上级单位code
	private String parUnitId;// 上级单位id
	private String parUnitType;// 上级单位类型
	private String parUnitName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getParUnitNum() {
		return parUnitNum;
	}

	public void setParUnitNum(String parUnitNum) {
		this.parUnitNum = parUnitNum;
	}

	public String getParUnitId() {
		return parUnitId;
	}

	public void setParUnitId(String parUnitId) {
		this.parUnitId = parUnitId;
	}

	public String getParUnitType() {
		return parUnitType;
	}

	public void setParUnitType(String parUnitType) {
		this.parUnitType = parUnitType;
	}

	public String getParUnitName() {
		return parUnitName;
	}

	public void setParUnitName(String parUnitName) {
		this.parUnitName = parUnitName;
	}
}
