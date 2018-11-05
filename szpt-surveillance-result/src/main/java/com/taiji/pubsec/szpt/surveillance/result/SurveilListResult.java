package com.taiji.pubsec.szpt.surveillance.result;

public interface SurveilListResult {

	/**
	 * 用于判断是DefaultSurveilListResult还是别的实现
	 * DefaultSurveilListResult的type值默认为DefaultSurveilListResult的包名和类名
	 * @param type
	 */
	public void setType(String type) ;
	
	public String getId() ;
	
	public String getClueType() ;
}
