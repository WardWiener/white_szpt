package com.taiji.pubsec.szpt.xtgl.department;

import com.taiji.pubsec.szpt.xtgl.OrganizationBean;



/**
 * 部门Bean
 * 
 * @author xinfan
 *
 */
public class DepartmentBean extends OrganizationBean {
	private String id;
	private String prop;// 属性判断是单位还是部门
	private String departmentNum;// 单位编码 code
	private String departmentName;// 单位名称
	private String departmentCode;// 组织机构代码 现在做成和code一样
	private String parDepartmentNum;// 上级单位code
	private String parDepartmentId;// 上级单位id
	private String parDepartmentType;// 上级单位类型
	private String parDepartmentName;

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

	public String getDepartmentNum() {
		return departmentNum;
	}

	public void setDepartmentNum(String departmentNum) {
		this.departmentNum = departmentNum;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getParDepartmentNum() {
		return parDepartmentNum;
	}

	public void setParDepartmentNum(String parDepartmentNum) {
		this.parDepartmentNum = parDepartmentNum;
	}

	public String getParDepartmentType() {
		return parDepartmentType;
	}

	public void setParDepartmentType(String parDepartmentType) {
		this.parDepartmentType = parDepartmentType;
	}

	public String getParDepartmentName() {
		return parDepartmentName;
	}

	public void setParDepartmentName(String parDepartmentName) {
		this.parDepartmentName = parDepartmentName;
	}

	public String getParDepartmentId() {
		return parDepartmentId;
	}

	public void setParDepartmentId(String parDepartmentId) {
		this.parDepartmentId = parDepartmentId;
	}

}
