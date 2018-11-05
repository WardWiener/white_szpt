package com.taiji.pubsec.bigdata.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author genganpeng
 *
 */
public class WifiData implements Serializable{
	private String id;
	private String mac;//mac地址
	private String enterTime;//上线时间
	private String leaveTime;//下线时间
	private String gatherTime;//当前wifi围栏的时间
	private Date createTime;//数据的创建时间
	private String placeId;//场所编号
	private String placeName;//场所编号
	private String latitude;	//纬度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示北纬（正符号省略），负符号表示南纬。例如123.23000 表示北纬123.2300度；-133.00000表示南纬133.00000度
	private String longitude;	//经度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示东经（正符号省略），负符号表示西经。例如123.23000 表示东经123.23000度；-133.00000表示西经133.00000度
	
	private String idcode;//身份证号码
	private String fiveColorPersonName;//五色人员姓名
	private List<String> peopleType;//人员类型，字典项
	private String warnType;//预警类型，字典项
	
	private boolean residentialArea = false;
	private boolean theft = false;

	
	public WifiData() {
		super();
	}

	/**
	 * 
	 * @param mac  mac地址
	 * @param enterTime 上线时间
	 * @param leaveTime 下线时间
	 * @param placeId 场所编号
	 * @param longitude 经度
	 * @param latitude 纬度
	 */
	public WifiData(String mac, String enterTime, String leaveTime,
			String placeId, String longitude, String latitude) {
		super();
		this.mac = mac;
		this.enterTime = enterTime;
		this.leaveTime = leaveTime;
		//进入时间就是当前wifi围栏的服务器时间
		this.gatherTime = enterTime;
		this.placeId = placeId;
		this.createTime = new Date();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getFiveColorPersonName() {
		return fiveColorPersonName;
	}

	public void setFiveColorPersonName(String fiveColorPersonName) {
		this.fiveColorPersonName = fiveColorPersonName;
	}

	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	public List<String> getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(List<String> peopleType) {
		this.peopleType = peopleType;
	}

	public String getGatherTime() {
		return gatherTime;
	}

	public void setGatherTime(String gatherTime) {
		this.gatherTime = gatherTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isResidentialArea() {
		return residentialArea;
	}

	public void setResidentialArea(boolean residentialArea) {
		this.residentialArea = residentialArea;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isTheft() {
		return theft;
	}

	public void setTheft(boolean theft) {
		this.theft = theft;
	}

	@Override
	public String toString() {
		return "WifiData [id=" + id + ", mac=" + mac + ", enterTime="
				+ enterTime + ", leaveTime=" + leaveTime + ", gatherTime="
				+ gatherTime + ", createTime=" + createTime + ", placeId="
				+ placeId + ", placeName=" + placeName + ", latitude="
				+ latitude + ", longitude=" + longitude + ", idcode=" + idcode
				+ ", fiveColorPersonName=" + fiveColorPersonName
				+ ", peopleType=" + peopleType + ", warnType="
				+ warnType + ", residentialArea=" + residentialArea
				+ ", theft=" + theft + "]";
	}

	
	
}
