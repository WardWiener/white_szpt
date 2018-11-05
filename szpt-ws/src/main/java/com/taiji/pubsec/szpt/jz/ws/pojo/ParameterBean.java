package com.taiji.pubsec.szpt.jz.ws.pojo;

public class ParameterBean {
	
	private String parameterName;
	
	private Class<?> parameterClass;
	
	private Object parameterValue;

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @return the parameterClass
	 */
	public Class<?> getParameterClass() {
		return parameterClass;
	}

	/**
	 * @return the parameterValue
	 */
	public Object getParameterValue() {
		return parameterValue;
	}

	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * @param parameterClass the parameterClass to set
	 */
	public void setParameterClass(Class<?> parameterClass) {
		this.parameterClass = parameterClass;
	}

	/**
	 * @param parameterValue the parameterValue to set
	 */
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	

}
