package com.taiji.pubsec.szpt.placemonitor.action.bean;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;

/**
 * 场所基础消息Bean
 * 
 * @author wangl
 *
 */
public class PlaceBasicInfoBean {

	private String id; // id

	private String vendorOrganizationCode; // 厂商组织机构代码

	private String serviceType; // 场所服务类型

	private String longitude; // 场所经度

	private String businessLegalPerson; // 场所经营法人

	private String managementNature; // 场所经营性质

	private String latitude; // 场所纬度

	private String detailedAddress; // 场所详细地址

	private String validDocumentNumberOfLegalPerson; // 场所经营法人有效证件号码

	private String validDocumentTypeOfLegalPerson; // 场所经营法人有效证件类型

	private String contactInfomation; // 联系方式

	private String internetServicePlaceCode; // 上网服务场所编码

	private String internetServicePlaceName; // 上网服务场所名称

	private Long businessStartTime; // 营业开始时间

	private Long businessEndTime; // 营业结束时间

	private int placeMonitorNum; // 场所监控人数
	
	private String areaDepartmentId; // 所属派出所
	
	private String isLive; //是否小区
	
	private List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList = new ArrayList<WifiPlaceInAndOutInfoBean>(); //场所list

	public String getAreaDepartmentId() {
		return areaDepartmentId;
	}

	public void setAreaDepartmentId(String areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}

	public String getIsLive() {
		return isLive;
	}

	public void setIsLive(String isLive) {
		this.isLive = isLive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVendorOrganizationCode() {
		return vendorOrganizationCode;
	}

	public void setVendorOrganizationCode(String vendorOrganizationCode) {
		this.vendorOrganizationCode = vendorOrganizationCode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBusinessLegalPerson() {
		return businessLegalPerson;
	}

	public void setBusinessLegalPerson(String businessLegalPerson) {
		this.businessLegalPerson = businessLegalPerson;
	}

	public String getManagementNature() {
		return managementNature;
	}

	public void setManagementNature(String managementNature) {
		this.managementNature = managementNature;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getValidDocumentNumberOfLegalPerson() {
		return validDocumentNumberOfLegalPerson;
	}

	public void setValidDocumentNumberOfLegalPerson(String validDocumentNumberOfLegalPerson) {
		this.validDocumentNumberOfLegalPerson = validDocumentNumberOfLegalPerson;
	}

	public String getValidDocumentTypeOfLegalPerson() {
		return validDocumentTypeOfLegalPerson;
	}

	public void setValidDocumentTypeOfLegalPerson(String validDocumentTypeOfLegalPerson) {
		this.validDocumentTypeOfLegalPerson = validDocumentTypeOfLegalPerson;
	}

	public String getContactInfomation() {
		return contactInfomation;
	}

	public void setContactInfomation(String contactInfomation) {
		this.contactInfomation = contactInfomation;
	}

	public String getInternetServicePlaceCode() {
		return internetServicePlaceCode;
	}

	public void setInternetServicePlaceCode(String internetServicePlaceCode) {
		this.internetServicePlaceCode = internetServicePlaceCode;
	}

	public String getInternetServicePlaceName() {
		return internetServicePlaceName;
	}

	public void setInternetServicePlaceName(String internetServicePlaceName) {
		this.internetServicePlaceName = internetServicePlaceName;
	}

	public Long getBusinessStartTime() {
		return businessStartTime;
	}

	public void setBusinessStartTime(Long businessStartTime) {
		this.businessStartTime = businessStartTime;
	}

	public Long getBusinessEndTime() {
		return businessEndTime;
	}

	public void setBusinessEndTime(Long businessEndTime) {
		this.businessEndTime = businessEndTime;
	}

	public int getPlaceMonitorNum() {
		return placeMonitorNum;
	}

	public void setPlaceMonitorNum(int placeMonitorNum) {
		this.placeMonitorNum = placeMonitorNum;
	}

	public List<WifiPlaceInAndOutInfoBean> getWifiPlaceInAndOutInfoBeanList() {
		return wifiPlaceInAndOutInfoBeanList;
	}

	public void setWifiPlaceInAndOutInfoBeanList(
			List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList) {
		this.wifiPlaceInAndOutInfoBeanList = wifiPlaceInAndOutInfoBeanList;
	}

}
