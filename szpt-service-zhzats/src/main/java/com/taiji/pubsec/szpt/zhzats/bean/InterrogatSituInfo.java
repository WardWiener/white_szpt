package com.taiji.pubsec.szpt.zhzats.bean;

public class InterrogatSituInfo {
	
	private String name ;
	
	private Integer newManpowerSum ;
	private Integer newManpowerSumHB ;
	private Integer newManpowerSumTB ;

	private Integer newCarSum ;
	private Integer newCarSumHB ;
	private Integer newCarSumTB ;
	
	private Integer newCarNotKySum ;
	private Integer newCarNotKySumHB ;
	private Integer newCarNotKySumTB ;
	
	public InterrogatSituInfo(String name, Integer newManpowerSum, Integer newCarSum,Integer newCarNotKySum){
		this.name = name ;
		this.newManpowerSum = newManpowerSum ;
		this.newCarSum = newCarSum ;
		this.newCarNotKySum = newCarNotKySum ;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNewManpowerSum() {
		return newManpowerSum;
	}

	public void setNewManpowerSum(Integer newManpowerSum) {
		this.newManpowerSum = newManpowerSum;
	}

	public Integer getNewCarSum() {
		return newCarSum;
	}

	public void setNewCarSum(Integer newCarSum) {
		this.newCarSum = newCarSum;
	}

	public Integer getNewCarNotKySum() {
		return newCarNotKySum;
	}

	public void setNewCarNotKySum(Integer newCarNotKySum) {
		this.newCarNotKySum = newCarNotKySum;
	}



	public Integer getNewManpowerSumHB() {
		return newManpowerSumHB;
	}



	public void setNewManpowerSumHB(Integer newManpowerSumHB) {
		this.newManpowerSumHB = newManpowerSumHB;
	}



	public Integer getNewManpowerSumTB() {
		return newManpowerSumTB;
	}



	public void setNewManpowerSumTB(Integer newManpowerSumTB) {
		this.newManpowerSumTB = newManpowerSumTB;
	}



	public Integer getNewCarSumHB() {
		return newCarSumHB;
	}



	public void setNewCarSumHB(Integer newCarSumHB) {
		this.newCarSumHB = newCarSumHB;
	}



	public Integer getNewCarSumTB() {
		return newCarSumTB;
	}



	public void setNewCarSumTB(Integer newCarSumTB) {
		this.newCarSumTB = newCarSumTB;
	}



	public Integer getNewCarNotKySumHB() {
		return newCarNotKySumHB;
	}



	public void setNewCarNotKySumHB(Integer newCarNotKySumHB) {
		this.newCarNotKySumHB = newCarNotKySumHB;
	}



	public Integer getNewCarNotKySumTB() {
		return newCarNotKySumTB;
	}



	public void setNewCarNotKySumTB(Integer newCarNotKySumTB) {
		this.newCarNotKySumTB = newCarNotKySumTB;
	}
	
}
