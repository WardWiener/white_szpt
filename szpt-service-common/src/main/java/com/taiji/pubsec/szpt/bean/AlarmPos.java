package com.taiji.pubsec.szpt.bean;

public class AlarmPos {
	
	private String name ;
	private String code ;
	private Double longitude ;
	private Double latitude ;
	private String typeCode ;
	private String alarmName ;
	
	public AlarmPos(Double longitude, Double latitude){
		this.longitude = longitude ;
		this.latitude = latitude ;
	}
	
	public AlarmPos(String name, String code, Double longitude, Double latitude, String typeCode, String alarmName){
		this.longitude = longitude ;
		this.latitude = latitude ;
		this.name = name ;
		this.code = code ;
		this.typeCode = typeCode ;
		this.alarmName = alarmName ;
	}
	
	public AlarmPos(String name, String code, Double longitude, Double latitude, String typeCode){
		this.longitude = longitude ;
		this.latitude = latitude ;
		this.name = name ;
		this.code = code ;
		this.typeCode = typeCode ;
	}
	
	public AlarmPos(String name, String code, Double longitude, Double latitude){
		this.longitude = longitude ;
		this.latitude = latitude ;
		this.name = name ;
		this.code = code ;
	}
	
	public AlarmPos(String name, Double longitude, Double latitude){
		this.name = name ;
		this.longitude = longitude ;
		this.latitude = latitude ;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alarmName == null) ? 0 : alarmName.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmPos other = (AlarmPos) obj;
		if (alarmName == null) {
			if (other.alarmName != null)
				return false;
		} else if (!alarmName.equals(other.alarmName))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	
}
