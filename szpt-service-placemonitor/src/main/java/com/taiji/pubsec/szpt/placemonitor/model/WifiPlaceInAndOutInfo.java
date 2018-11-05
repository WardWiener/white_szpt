package com.taiji.pubsec.szpt.placemonitor.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * wifi场所出入信息
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_csjk_wificscrxx")
public class WifiPlaceInAndOutInfo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Column(name = "jrsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enterTime;	//进入时间
	
	@Column(name = "cswd")
	private String latitude;	//纬度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示北纬（正符号省略），负符号表示南纬。例如123.23000 表示北纬123.2300度；-133.00000表示南纬133.00000度

	@Column(name = "lksj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveTime;	// 离开时间
	
	@Column(name = "csjd")
	private String longitude;	//经度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示东经（正符号省略），负符号表示西经。例如123.23000 表示东经123.23000度；-133.00000表示西经133.00000度

	@Column(name = "mac", length = 17)
	private String mac;		//mac地址
	
	@Column(name = "csmc")
	private String place;	//场所名称
	
	@Column(name = "zlsc")
	private int stayInterval;	//驻留时间，秒
	
	@Column(name = "rymc", length = 50)
	private String personName;	//单个人员名称
	
	@Column(name = "sfzh")
	private String idCode;	//身份证号
	
	@OneToMany(mappedBy = "wifiPlaceInfo")
	private Set<PeopleType> peopleTypes = new HashSet<PeopleType>();
	

	/**
	 * @return	id	id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return	enterTime	进入时间
	 */
	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	/**
	 * @return	latitude	纬度，采用“正负+十进制度数”的格式表示。</br>度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示北纬（正符号省略），负符号表示南纬。例如123.23000 表示北纬123.2300度；-133.00000表示南纬133.00000度
	 */
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return	leaveTime	离开时间
	 */
	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	/**
	 * @return	longitude	经度，采用“正负+十进制度数”的格式表示。</br>度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示东经（正符号省略），负符号表示西经。例如123.23000 表示东经123.23000度；-133.00000表示西经133.00000度
	 */
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return	mac	mac地址
	 */
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return	place	场所名称
	 */
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return	stayInterval	驻留时间，秒
	 */
	public int getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(int stayInterval) {
		this.stayInterval = stayInterval;
	}

	/**
	 * @return	personName	人员名称
	 */
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return idCode 身份证号
	 */
	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Set<PeopleType> getPeopleTypes() {
		return peopleTypes;
	}

	public void setPeopleTypes(Set<PeopleType> peopleTypes) {
		this.peopleTypes = peopleTypes;
	}

	
}
