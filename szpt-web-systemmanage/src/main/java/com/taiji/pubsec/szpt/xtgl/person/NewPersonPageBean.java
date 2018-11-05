package com.taiji.pubsec.szpt.xtgl.person;


import java.util.List;

/**
 * 
 * @author xinfan
 *
 */
public class NewPersonPageBean {

	private List<BasePersonData> sexList;// 性别
	private List<BasePersonData> jobList;// 职务
	private List<BasePersonData> nationalityList;// 民族
	private List<BasePersonData> politicsTypeList;// 政治面貌
	private List<BasePersonData> diplomaList;// 学历
	private List<BasePersonData> statusList;// 状态

	public List<BasePersonData> getSexList() {
		return sexList;
	}

	public void setSexList(List<BasePersonData> sexList) {
		this.sexList = sexList;
	}

	public List<BasePersonData> getJobList() {
		return jobList;
	}

	public void setJobList(List<BasePersonData> jobList) {
		this.jobList = jobList;
	}

	public List<BasePersonData> getNationalityList() {
		return nationalityList;
	}

	public void setNationalityList(List<BasePersonData> nationalityList) {
		this.nationalityList = nationalityList;
	}

	public List<BasePersonData> getPoliticsTypeList() {
		return politicsTypeList;
	}

	public void setPoliticsTypeList(List<BasePersonData> politicsTypeList) {
		this.politicsTypeList = politicsTypeList;
	}

	public List<BasePersonData> getDiplomaList() {
		return diplomaList;
	}

	public void setDiplomaList(List<BasePersonData> diplomaList) {
		this.diplomaList = diplomaList;
	}

	public List<BasePersonData> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<BasePersonData> statusList) {
		this.statusList = statusList;
	}

}
