package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集信息
 * @author huangcheng
 *
 */
@Entity
@Table(name = "t_xsajfx_cjxxqk")
public class CollectInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
	
	@Column(name = "sjxm")
	private String collectProject;	//收集项目(指纹尿液等),选择多个用逗号隔开：指纹，血液，尿液，sis，禁毒作战平台信息
	
	@Column(name = "qtnr")
	private String collectProjectOther;	//其他的内容
	
	@Column(name = "xxrk")
	private String infoIntoString;	//信息入库，0:是；1:否
	
	@Column(name = "hcdb")
	private String inspectComparison;	//核查对比，0:是；1:否
	
	@Column(name = "sfsj")
	private String isCollect;	//是否收集，0:是；1:否
	
	@Column(name = "sfzh")
	private String identy ; //身份证号
	
	@Column(name = "zxxgsj")
	private Date updatedTime;	//最新修改时间
	
	@Column(name = "qq")
	private String qqNum;	//qq号码（，隔开多个）,也有可能是中文字符:"无"
	
	@Column(name="zxxgrxm")
	private String modifyPersonName ; //修改人姓名
	
	@Column(name = "wxh")
	private String weixinNum;	//微信号（，隔开多个）,也有可能是中文字符:"无"
	
	@Column(name = "sjxx")
	private String phoneInfo;	//手机信息（手机号，imei，mac；手机号，imei，mac）,也有可能是中文字符:"无"

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollectProject() {
		return collectProject;
	}

	public void setCollectProject(String collectProject) {
		this.collectProject = collectProject;
	}

	public String getCollectProjectOther() {
		return collectProjectOther;
	}

	public void setCollectProjectOther(String collectProjectOther) {
		this.collectProjectOther = collectProjectOther;
	}

	public String getInfoIntoString() {
		return infoIntoString;
	}

	public void setInfoIntoString(String infoIntoString) {
		this.infoIntoString = infoIntoString;
	}

	public String getInspectComparison() {
		return inspectComparison;
	}

	public void setInspectComparison(String inspectComparison) {
		this.inspectComparison = inspectComparison;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getIdenty() {
		return identy;
	}

	public void setIdenty(String identy) {
		this.identy = identy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getWeixinNum() {
		return weixinNum;
	}

	public void setWeixinNum(String weixinNum) {
		this.weixinNum = weixinNum;
	}

	public String getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(String phoneInfo) {
		this.phoneInfo = phoneInfo;
	}
	
	
}
