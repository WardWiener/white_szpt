package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 车辆信息
 * @author huangcheng
 *
 */
@Entity
@Table(name = "t_gwry_clxx")
public class CarInfo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
	
	/**
	 * 所有人姓名
	 */
	@Column(name="syrxm")
	private String ownerName ;
	
	/**
	 * 身份证号
	 */
	@Column(name="sfzh")
	private String identy ;
	
	/**
	 * 车牌号
	 */
	@Column(name="cph")
	private String plateNum ;
	
	/**
	 * 车辆品牌
	 */
	@Column(name="clpp")
	private String carBrand ;
	
	/**
	 * 车辆型号
	 */
	@Column(name="clxh")
	private String modelOfCar ;
	
	/**
	 * 车辆用途
	 */
	@Column(name="clyt")
	private String usageOfCar ;

	/**
	 * 车辆颜色
	 */
	@Column(name="clys")
	private String colorOfCar ;
	
	/**
	 * 使用起始时间
	 */
	@Column(name="syqssj")
	private Date startDateOfUsage ;
	
	/**
	 * 检验有效期止
	 */
	@Column(name="jyyxqz")
	private Date expiryDateOfInspection ;
	
	/**
	 * 入库时间
	 */
	@Column(name="rksj") 
	private String storageDate ;
	
	/**
	 * 更新时间
	 */
	@Column(name="gxsj") 
	private String updateDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getIdenty() {
		return identy;
	}

	public void setIdenty(String identy) {
		this.identy = identy;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getModelOfCar() {
		return modelOfCar;
	}

	public void setModelOfCar(String modelOfCar) {
		this.modelOfCar = modelOfCar;
	}

	public String getUsageOfCar() {
		return usageOfCar;
	}

	public void setUsageOfCar(String usageOfCar) {
		this.usageOfCar = usageOfCar;
	}

	public String getColorOfCar() {
		return colorOfCar;
	}

	public void setColorOfCar(String colorOfCar) {
		this.colorOfCar = colorOfCar;
	}

	public Date getStartDateOfUsage() {
		return startDateOfUsage;
	}

	public void setStartDateOfUsage(Date startDateOfUsage) {
		this.startDateOfUsage = startDateOfUsage;
	}

	public Date getExpiryDateOfInspection() {
		return expiryDateOfInspection;
	}

	public void setExpiryDateOfInspection(Date expiryDateOfInspection) {
		this.expiryDateOfInspection = expiryDateOfInspection;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
