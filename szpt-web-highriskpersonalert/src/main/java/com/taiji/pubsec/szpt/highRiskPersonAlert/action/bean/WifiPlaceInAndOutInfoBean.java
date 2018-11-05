package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;



public class WifiPlaceInAndOutInfoBean {

	private String id;

	private Long enterTime; // 进入时间

	private String latitude; // 纬度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示北纬（正符号省略），负符号表示南纬。例如123.23000
								// 表示北纬123.2300度；-133.00000表示南纬133.00000度

	private Long leaveTime; // 离开时间

	private String longitude; // 经度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示东经（正符号省略），负符号表示西经。例如123.23000
								// 表示东经123.23000度；-133.00000表示西经133.00000度

	private String mac; // mac地址

	private String place; // 场所名称

	private int stayInterval; // 驻留时间，秒

	private String personName; // 单个人员名称
	
	private String idCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	public Long getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(int stayInterval) {
		this.stayInterval = stayInterval;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

}
