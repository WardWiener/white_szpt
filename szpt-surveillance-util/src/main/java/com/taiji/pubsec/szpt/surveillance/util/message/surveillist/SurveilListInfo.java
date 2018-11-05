package com.taiji.pubsec.szpt.surveillance.util.message.surveillist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveilListInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7315987637853140858L;
	
	private String id ;
    private String num; //编号
    private String idCardNo;    //身份证号
    private String personName;  //姓名
    private String sex; //性别
    private String residenceAddress;    //户籍地址
    private Long startTime; //布控开始时间
    private Long endTime;   //布控结束时间
    private String status;  //数据字典：启用；失效
    private String note;	//备注
    private String lastModifyPerson;    //最新修改人姓名
    private Long lastModifyTime;    //最新修改时间
    /**
     * 操作状态：0新增1待审批2审批通过3审批驳回
     */
    private String operateStatus ;
    
    private List<SurveilListMobileInfo> surveilListMobileInfos = new ArrayList<SurveilListMobileInfo>() ;
    private List<SurveilListImgInfo> surveilListImgInfos = new ArrayList<SurveilListImgInfo>() ;
     
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getResidenceAddress() {
		return residenceAddress;
	}
	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLastModifyPerson() {
		return lastModifyPerson;
	}
	public void setLastModifyPerson(String lastModifyPerson) {
		this.lastModifyPerson = lastModifyPerson;
	}
	public List<SurveilListMobileInfo> getSurveilListMobileInfos() {
		return surveilListMobileInfos;
	}
	public void setSurveilListMobileInfos(List<SurveilListMobileInfo> surveilListMobileInfos) {
		this.surveilListMobileInfos = surveilListMobileInfos;
	}
	public List<SurveilListImgInfo> getSurveilListImgInfos() {
		return surveilListImgInfos;
	}
	public void setSurveilListImgInfos(List<SurveilListImgInfo> surveilListImgInfos) {
		this.surveilListImgInfos = surveilListImgInfos;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
    
    
}
