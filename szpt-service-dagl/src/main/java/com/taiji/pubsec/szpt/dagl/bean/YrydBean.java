package com.taiji.pubsec.szpt.dagl.bean;

import java.util.ArrayList;
import java.util.List;

public class YrydBean {
	
	private String id;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 人员类别
	 */
	private String type;
	
	/**
	 * 身份证号
	 */
	private String idNum;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 曾用名
	 */
	private String usedName;
	
	/**
	 * 婚姻状况
	 */
	private String married;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 职业
	 */
	private String career;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 户主名称
	 */
	private String householder;
	
	/**
	 * 文化程度
	 */
	private String culture;
	
	/**
	 * 与户主关系
	 */
	private String relationWithHouseholder;
	
	/**
	 * 出生地
	 */
	private String birthplace;
	
	/**
	 * 户籍地址
	 */
	private String censusAddress;
	
	/**
	 * 死亡日期
	 */
	private String dateOfDeath;
	
	/**
	 * 照片
	 */
	private List<String> photoLst = new ArrayList<String>();
	
	/**
	 * 户籍信息列表
	 */
	private List<YrydCensusBean> censusInfoLst = new ArrayList<YrydCensusBean>();
	
	/**
	 * 暂住信息列表
	 */
	private List<YrydStayBean> stayInfoLst = new ArrayList<YrydStayBean>();
	
	/**
	 * 最近出现地点
	 */
	private YrydPlaceBean latestAppearPlace = new YrydPlaceBean();
	
	/**
	 * 经常出现地点（一个月内）
	 */
	private List<YrydPlaceBean> usuallyAppearPlaceLst = new ArrayList<YrydPlaceBean>();

	/**
	 * 车辆信息
	 */
	private List<YrydVehicleBean> vehicleInfoLst = new ArrayList<YrydVehicleBean>();
	
	/**
	 * 卡口信息
	 */
	private List<YrydCameraBean> cameraInfoLst = new ArrayList<YrydCameraBean>();
	
	/**
	 * 手机信息
	 */
	private List<YrydPhoneBean> phoneInfoLst = new ArrayList<YrydPhoneBean>();
	
	/**
	 * 火车出行信息
	 */
	private List<YrydTrainGoOutBean> trainGoOutInfoLst = new ArrayList<YrydTrainGoOutBean>();
	
	/**
	 * 飞机出行信息
	 */
	private List<YrydPlaneGoOutBean> planeGoOutInfoLst = new ArrayList<YrydPlaneGoOutBean>();
	
	
	/**
	 * 旅馆住宿信息
	 */
	private List<YrydHotelBean> hotelInfoLst = new ArrayList<YrydHotelBean>();
	
	/**
	 * 网吧上网信息
	 */
	private List<YrydCybercafeBean> cybercafeInfoLst = new ArrayList<YrydCybercafeBean>();
	
	/**
	 * 前科信息
	 */
	private List<YrydRecordBean> recordInfoLst = new ArrayList<YrydRecordBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHouseholder() {
		return householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getRelationWithHouseholder() {
		return relationWithHouseholder;
	}

	public void setRelationWithHouseholder(String relationWithHouseholder) {
		this.relationWithHouseholder = relationWithHouseholder;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCensusAddress() {
		return censusAddress;
	}

	public void setCensusAddress(String censusAddress) {
		this.censusAddress = censusAddress;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public List<String> getPhotoLst() {
		return photoLst;
	}

	public void setPhotoLst(List<String> photoLst) {
		this.photoLst = photoLst;
	}

	public List<YrydCensusBean> getCensusInfoLst() {
		return censusInfoLst;
	}

	public void setCensusInfoLst(List<YrydCensusBean> censusInfoLst) {
		this.censusInfoLst = censusInfoLst;
	}

	public List<YrydStayBean> getStayInfoLst() {
		return stayInfoLst;
	}

	public void setStayInfoLst(List<YrydStayBean> stayInfoLst) {
		this.stayInfoLst = stayInfoLst;
	}

	public YrydPlaceBean getLatestAppearPlace() {
		return latestAppearPlace;
	}

	public void setLatestAppearPlace(YrydPlaceBean latestAppearPlace) {
		this.latestAppearPlace = latestAppearPlace;
	}

	public List<YrydPlaceBean> getUsuallyAppearPlaceLst() {
		return usuallyAppearPlaceLst;
	}

	public void setUsuallyAppearPlaceLst(List<YrydPlaceBean> usuallyAppearPlaceLst) {
		this.usuallyAppearPlaceLst = usuallyAppearPlaceLst;
	}

	public List<YrydVehicleBean> getVehicleInfoLst() {
		return vehicleInfoLst;
	}

	public void setVehicleInfoLst(List<YrydVehicleBean> vehicleInfoLst) {
		this.vehicleInfoLst = vehicleInfoLst;
	}

	public List<YrydCameraBean> getCameraInfoLst() {
		return cameraInfoLst;
	}

	public void setCameraInfoLst(List<YrydCameraBean> cameraInfoLst) {
		this.cameraInfoLst = cameraInfoLst;
	}

	public List<YrydPhoneBean> getPhoneInfoLst() {
		return phoneInfoLst;
	}

	public void setPhoneInfoLst(List<YrydPhoneBean> phoneInfoLst) {
		this.phoneInfoLst = phoneInfoLst;
	}

	public List<YrydTrainGoOutBean> getTrainGoOutInfoLst() {
		return trainGoOutInfoLst;
	}

	public void setTrainGoOutInfoLst(List<YrydTrainGoOutBean> trainGoOutInfoLst) {
		this.trainGoOutInfoLst = trainGoOutInfoLst;
	}

	public List<YrydPlaneGoOutBean> getPlaneGoOutInfoLst() {
		return planeGoOutInfoLst;
	}

	public void setPlaneGoOutInfoLst(List<YrydPlaneGoOutBean> planeGoOutInfoLst) {
		this.planeGoOutInfoLst = planeGoOutInfoLst;
	}

	public List<YrydHotelBean> getHotelInfoLst() {
		return hotelInfoLst;
	}

	public void setHotelInfoLst(List<YrydHotelBean> hotelInfoLst) {
		this.hotelInfoLst = hotelInfoLst;
	}

	public List<YrydCybercafeBean> getCybercafeInfoLst() {
		return cybercafeInfoLst;
	}

	public void setCybercafeInfoLst(List<YrydCybercafeBean> cybercafeInfoLst) {
		this.cybercafeInfoLst = cybercafeInfoLst;
	}

	public List<YrydRecordBean> getRecordInfoLst() {
		return recordInfoLst;
	}

	public void setRecordInfoLst(List<YrydRecordBean> recordInfoLst) {
		this.recordInfoLst = recordInfoLst;
	}

	
}
