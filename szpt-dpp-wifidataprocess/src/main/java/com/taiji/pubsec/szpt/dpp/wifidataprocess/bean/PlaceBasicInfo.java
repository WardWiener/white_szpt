package com.taiji.pubsec.szpt.dpp.wifidataprocess.bean;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 场所基础消息
 */
public class PlaceBasicInfo extends com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo implements Serializable{
	public PlaceBasicInfo() {
		super();
	}
	
	public PlaceBasicInfo(String internetServicePlaceCode,
			String internetServicePlaceName, String detailedAddress,
			String longitude, String latitude, String serviceType,
			String managementNature, String businessLegalPerson,
			String validDocumentTypeOfLegalPerson,
			String validDocumentNumberOfLegalPerson, String contactInfomation,
			String businessStartTime, String businessEndTime, String accessWay,
			String accessProvider, String accessAccount,
			String vendorOrganizationCode) {
		super();
		setId(UUID.randomUUID().toString());
		setInternetServicePlaceCode(internetServicePlaceCode);
		setInternetServicePlaceName(internetServicePlaceName);
		setDetailedAddress(detailedAddress);
		setLongitude(longitude);
		setLatitude(latitude);
		setServiceType(serviceType);
		setManagementNature(managementNature);
		setBusinessLegalPerson(businessLegalPerson);
		setValidDocumentTypeOfLegalPerson(validDocumentTypeOfLegalPerson);
		setValidDocumentNumberOfLegalPerson(validDocumentNumberOfLegalPerson);
		setContactInfomation(contactInfomation);
								
		
		SimpleDateFormat format = new SimpleDateFormat("hh:mm");// 格式化类型
        try {
			setBusinessStartTime(new Timestamp(
					businessStartTime
					.equals("") ? new Date().getTime()
					: format.parse(businessStartTime).getTime()));
			setBusinessEndTime(new Timestamp(
					businessEndTime
					.equals("") ? new Date()
					.getTime()
					: format.parse(businessEndTime).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
		setVendorOrganizationCode(vendorOrganizationCode);
	}

	@Override
	public String toString() {
		return "PlaceBasicInfo [getId()=" + getId()
				+ ", getVendorOrganizationCode()="
				+ getVendorOrganizationCode() + ", getServiceType()="
				+ getServiceType() + ", getLongitude()=" + getLongitude()
				+ ", getBusinessLegalPerson()=" + getBusinessLegalPerson()
				+ ", getManagementNature()=" + getManagementNature()
				+ ", getLatitude()=" + getLatitude()
				+ ", getDetailedAddress()=" + getDetailedAddress()
				+ ", getValidDocumentNumberOfLegalPerson()="
				+ getValidDocumentNumberOfLegalPerson()
				+ ", getValidDocumentTypeOfLegalPerson()="
				+ getValidDocumentTypeOfLegalPerson()
				+ ", getContactInfomation()=" + getContactInfomation()
				+ ", getInternetServicePlaceCode()="
				+ getInternetServicePlaceCode()
				+ ", getInternetServicePlaceName()="
				+ getInternetServicePlaceName() + ", getBusinessStartTime()="
				+ getBusinessStartTime() + ", getBusinessEndTime()="
				+ getBusinessEndTime() 
//				+ ", getPlaceMonitor()=" + getPlaceMonitor()
				+ ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	
	
	
}
