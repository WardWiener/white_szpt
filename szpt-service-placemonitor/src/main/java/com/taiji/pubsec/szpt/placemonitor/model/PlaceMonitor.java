package com.taiji.pubsec.szpt.placemonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;

/**
 * wifi场所监控人数
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_csjk_wificsjkrs")
@Deprecated
public class PlaceMonitor {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;	//id
	
	@Column(name = "jkrs")
	private Integer num;	//当天监控人数
	
	@Column(name = "jksj")
	private Date monitorTime;	//监控时间

	@OneToOne
	@JoinColumn(name = "cs_id")
	private PlaceBasicInfo placeBasicInfo;	//监控场所
	
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
	 * @return num	当天监控人数
	 */
	public Integer getNum() {
		return num == null ? 0 : num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return	placeBasicInfo	监控场所
	 */
	public PlaceBasicInfo getPlaceBasicInfo() {
		return placeBasicInfo;
	}

	public void setPlaceBasicInfo(PlaceBasicInfo placeBasicInfo) {
		this.placeBasicInfo = placeBasicInfo;
	}

}
