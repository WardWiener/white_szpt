package com.taiji.pubsec.szpt.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员关注信息
 * @author dixiaofeng
 * @version 1.0
 * @created 20-二月-2017 15:13:36
 */

@Entity(name="com.taiji.pubsec.szpt.dagl.model.InterestedPerson")
@Table(name="t_dagl_gzr")
public class InterestedPerson {

	/**
	 * 关注时间
	 */
	@Column(name="gzsj")
	private Date followTime;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	/**
	 * 被关注人身份证号
	 */
	@Column(name="sfzh")
	private String idCard;
	/**
	 * 是否置顶
	 */
	@Column(name="sfzd")
	private boolean isStick;
	
	/**
	 * 关注人id
	 */
	@Column(name="gzr_id")
	private String personId;
	
	/**
	 * 被关注人姓名
	 */
	@Column(name="xm")
	private String name;
	
	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean isStick() {
		return isStick;
	}

	public void setStick(boolean isStick) {
		this.isStick = isStick;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
