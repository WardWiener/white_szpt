package com.taiji.pubsec.szpt.placemonitor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * WiFi场所信息
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_csjk_wificsxx")
public class PlaceBasicInfo implements Serializable{

	private static final long serialVersionUID = 6730448845951601871L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;	//id
	
	@Column(name = "cszzjgdm")
	private String vendorOrganizationCode;	//厂商组织机构代码
	
	@Column(name = "csfwlx")
	private String serviceType;	//场所服务类型
	
	@Column(name = "csjd")
	private String longitude;	//场所经度
	
	@Column(name = "csjyfr")
	private String businessLegalPerson;	//场所经营法人
	
	@Column(name = "csjyxz")
	private String managementNature;	//场所经营性质
	
	@Column(name = "cswd")
	private String latitude;	//场所纬度
	
	@Column(name = "csxxdz")
	private String detailedAddress;	//场所详细地址
	
	@Column(name = "csjyfryxzjhm")
	private String validDocumentNumberOfLegalPerson;	//场所经营法人有效证件号码 
   
	@Column(name = "csjyfryxzjlx")
	private String validDocumentTypeOfLegalPerson;	//场所经营法人有效证件类型
	
	@Column(name = "lxfs")
	private String contactInfomation;	//联系方式
	
	@Column(name = "swfwcsbm")
	private String internetServicePlaceCode;	//上网服务场所编码
	
	@Column(name = "swfwcsmc")
	private String internetServicePlaceName;	//上网服务场所名称
	
	@Column(name = "yykssj")
	private Date businessStartTime;	//营业开始时间
	
	@Column(name = "yyjssj")
	private Date businessEndTime;	//营业结束时间
	
	@Column(name="sfjjzxq")
	private Integer isLive;	//是否居住小区,sfjzxq 0表示不是1表示是
	
	@Column(name="xqpcs_id")
	private String areaDepartmentId; //场所所属区域派出所
	
//	@OneToOne(mappedBy = "placeBasicInfo")
//	private PlaceMonitor placeMonitor;	//场所监控人数
	
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
	 * @return	vendorOrganizationCode	厂商组织机构代码
	 */
	public String getVendorOrganizationCode() {
		return vendorOrganizationCode;
	}

	public void setVendorOrganizationCode(String vendorOrganizationCode) {
		this.vendorOrganizationCode = vendorOrganizationCode;
	}

	/**
	 * @return	serviceType	场所服务类型
	 */
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return	longitude	场所经度
	 */
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return	businessLegalPerson	场所经营法人
	 */
	public String getBusinessLegalPerson() {
		return businessLegalPerson;
	}

	public void setBusinessLegalPerson(String businessLegalPerson) {
		this.businessLegalPerson = businessLegalPerson;
	}

	/**
	 * @return	managementNature	场所经营性质
	 */
	public String getManagementNature() {
		return managementNature;
	}

	public void setManagementNature(String managementNature) {
		this.managementNature = managementNature;
	}

	/**
	 * @return	latitude	场所纬度
	 */
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return	detailedAddress	场所详细地址
	 */
	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	/**
	 * @return	validDocumentNumberOfLegalPerson	场所经营法人有效证件号码 
	 */
	public String getValidDocumentNumberOfLegalPerson() {
		return validDocumentNumberOfLegalPerson;
	}

	public void setValidDocumentNumberOfLegalPerson(String validDocumentNumberOfLegalPerson) {
		this.validDocumentNumberOfLegalPerson = validDocumentNumberOfLegalPerson;
	}

	/**
	 * @return	validDocumentTypeOfLegalPerson	场所经营法人有效证件类型
	 */
	public String getValidDocumentTypeOfLegalPerson() {
		return validDocumentTypeOfLegalPerson;
	}

	public void setValidDocumentTypeOfLegalPerson(String validDocumentTypeOfLegalPerson) {
		this.validDocumentTypeOfLegalPerson = validDocumentTypeOfLegalPerson;
	}

	/**
	 * @return	contactInfomation	联系方式
	 */
	public String getContactInfomation() {
		return contactInfomation;
	}

	public void setContactInfomation(String contactInfomation) {
		this.contactInfomation = contactInfomation;
	}

	/**
	 * @return	internetServicePlaceCode	上网服务场所编码
	 */
	public String getInternetServicePlaceCode() {
		return internetServicePlaceCode;
	}

	public void setInternetServicePlaceCode(String internetServicePlaceCode) {
		this.internetServicePlaceCode = internetServicePlaceCode;
	}

	/**
	 * @return	internetServicePlaceName	上网服务场所名称
	 */
	public String getInternetServicePlaceName() {
		return internetServicePlaceName;
	}

	public void setInternetServicePlaceName(String internetServicePlaceName) {
		this.internetServicePlaceName = internetServicePlaceName;
	}

	/**
	 * @return	businessStartTime		营业开始时间
	 */
	public Date getBusinessStartTime() {
		return businessStartTime;
	}

	public void setBusinessStartTime(Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}

	/**
	 * @return	businessEndTime	营业结束时间
	 */
	public Date getBusinessEndTime() {
		return businessEndTime;
	}

	public void setBusinessEndTime(Date businessEndTime) {
		this.businessEndTime = businessEndTime;
	}

	/**
	 * @return	placeMonitorNum	场所监控人数
	 */
//	public PlaceMonitor getPlaceMonitor() {
//		return placeMonitor;
//	}
//
//	public void setPlaceMonitor(PlaceMonitor placeMonitor) {
//		this.placeMonitor = placeMonitor;
//	}

	/**
	 * @return	isLive	是否居住小区,sfjzxq 0表示不是1表示是
	 */
	public Integer getIsLive() {
		return isLive;
	}

	public void setIsLive(Integer isLive) {
		this.isLive = isLive;
	}

	/**
	 * @return	areaDepartmentId	辖区派出所id
	 */
	public String getAreaDepartmentId() {
		return areaDepartmentId;
	}

	public void setAreaDepartmentId(String areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}

}
