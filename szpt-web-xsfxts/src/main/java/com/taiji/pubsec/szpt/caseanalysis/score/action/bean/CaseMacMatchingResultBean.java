package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件mac地址匹配结果Bean
 * 
 * @author WangLei
 *
 */
public class CaseMacMatchingResultBean {

	private String mac;// 相同mac地址
	
	private List<String> matchingCaseCodes = new ArrayList<String>();// 匹配的案件编码
	
	private String matchingCaseNameStr;// 匹配的案件编码名称
	
	private String name;// mac地址对应人员姓名
	
	private String idcode;// 人员身份证
	
	private String localAddressDetail;// 人员住址

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<String> getMatchingCaseCodes() {
		return matchingCaseCodes;
	}

	public void setMatchingCaseCodes(List<String> matchingCaseCodes) {
		this.matchingCaseCodes = matchingCaseCodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getLocalAddressDetail() {
		return localAddressDetail;
	}

	public void setLocalAddressDetail(String localAddressDetail) {
		this.localAddressDetail = localAddressDetail;
	}

	public String getMatchingCaseNameStr() {
		return matchingCaseNameStr;
	}

	public void setMatchingCaseNameStr(String matchingCaseNameStr) {
		this.matchingCaseNameStr = matchingCaseNameStr;
	}

	
	
}
