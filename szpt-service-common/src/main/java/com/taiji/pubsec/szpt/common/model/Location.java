package com.taiji.pubsec.szpt.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.taiji.pubsec.szpt.util.ConstantEnum;
import com.taiji.pubsec.szpt.util.ConstantEnumUtil;


/**
 * 实时警力
 * @author wangfx
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_wzxx")
public class Location {
	
	public enum LOCATION_TYPE implements ConstantEnum{
		
		PDA("pda"),
		GPS("gps"),
		TEJING_CAR("巡逻车");
		
		private String value ;
		
		private LOCATION_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(LOCATION_TYPE.values()) ;
		}

	}

	public enum STATE implements ConstantEnum{
		
		LEISURE("0"),
		INPROCESS("1"),
		RECEIVED("2");
		
		private String value ;
		
		private STATE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(LOCATION_TYPE.values()) ;
		}
	}
	
	@Id
	private String id;
	
	/**
	 * 名字
	 */
	@Column(name = "xm")
	private String personName;
	
	/**
	 * 警号
	 */
	@Column(name = "ryjh")
	private String personJh;
	
	/**
	 * 类型，区分设备      pda、gps
	 */
	@Column(name = "lx")
	private String type;
	
	/**
	 * 定位设备标识，车 PDA的标示
	 */
	@Column(name = "dwsbbs")
	private String identifier;
	
	/**
	 * 经度
	 */
	@Column(name = "jd")
	private Double longitude;
	
	/**
	 * 纬度
	 */
	@Column(name = "wd")
	private Double latitude;
	
	/**
	 * 状态  空闲：0；正在处理警情：1
	 */
	@Column(name = "zt")
	private String state;
	
	/**
	 * 指挥单元编码
	 */
	@Column(name = "zhdybm")
	@Index(name="index_t_zhzats_jwzh_wzxx_zhdybm")
	private String orderCellCode;
	
	/**
	 * 指挥单元名称
	 */
	@Column(name = "zhdymc")
	private String orderCellName;
	
	/**
	 * 更新时间
	 */
	@Column(name="gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	@Index(name="index_t_zhzats_jwzh_wzxx_gxsj")
	private Date updateTime  ;
	
	public Location() {
		
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonJh() {
		return personJh;
	}

	public void setPersonJh(String personJh) {
		this.personJh = personJh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderCellCode() {
		return orderCellCode;
	}

	public void setOrderCellCode(String orderCellCode) {
		this.orderCellCode = orderCellCode;
	}

	public String getOrderCellName() {
		return orderCellName;
	}

	public void setOrderCellName(String orderCellName) {
		this.orderCellName = orderCellName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
