package com.taiji.pubsec.szpt.ptjc.action.bean;

import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;

public class PenalConstantBean {
	
	private String pcsCode;
	private String pcsName;
	private  String blue;
	
	private  String yellow;
	private  String orange;
	private  String red;
	
	
	
	public PenalConstantBean(String pcsCode, String pcsName, String blue,
			String yellow, String orange, String red) {
		super();
		this.pcsCode = pcsCode;
		this.pcsName = pcsName;
		this.blue = blue;
		this.yellow = yellow;
		this.orange = orange;
		this.red = red;
	}

	public PenalConstantBean(){
		
	}

	public String getPcsCode() {
		return pcsCode;
	}



	public void setPcsCode(String pcsCode) {
		this.pcsCode = pcsCode;
	}



	public String getPcsName() {
		return pcsName;
	}



	public void setPcsName(String pcsName) {
		this.pcsName = pcsName;
	}



	public String getBlue() {
		return blue;
	}



	public void setBlue(String blue) {
		this.blue = blue;
	}



	public String getYellow() {
		return yellow;
	}



	public void setYellow(String yellow) {
		this.yellow = yellow;
	}



	public String getOrange() {
		return orange;
	}



	public void setOrange(String orange) {
		this.orange = orange;
	}



	public String getRed() {
		return red;
	}



	public void setRed(String red) {
		this.red = red;
	}
	
}
