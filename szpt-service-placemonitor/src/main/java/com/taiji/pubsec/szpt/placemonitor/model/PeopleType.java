package com.taiji.pubsec.szpt.placemonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * wifi出入信息和重点人类别关系
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_csjk_wificscrxx_rylx")
@Deprecated
public class PeopleType {
	
	public static final String PEOPLETYPE_DICTYPE_ID = "0000000013";
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;	//id
	
	@Column(name = "rylxbm")
	private String peopleType;	//人员类型编码
	
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;	//更新时间
	
	@ManyToOne
	@JoinColumn(name = "wfcscrxx_id")
	private WifiPlaceInAndOutInfo wifiPlaceInfo;	//WiFi出入信息
	
	/**
	 * @return id	id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return	peopleType	人员类型编码
	 */
	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return	updatedTime	更新时间
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((peopleType == null) ? 0 : peopleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeopleType other = (PeopleType) obj;
		if (peopleType == null) {
			if (other.peopleType != null)
				return false;
		} else if (!peopleType.equals(other.peopleType))
			return false;
		return true;
	}

	/**
	 * @return	wifiPlaceInfo WiFi出入信息
	 */
	public WifiPlaceInAndOutInfo getWifiPlaceInfo() {
		return wifiPlaceInfo;
	}

	public void setWifiPlaceInfo(WifiPlaceInAndOutInfo wifiPlaceInfo) {
		this.wifiPlaceInfo = wifiPlaceInfo;
	}

	
}
