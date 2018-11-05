package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.WifiMonitorPointBean;


/**
 * 案件分析服务接口
 * 
 * @author dixiaofeng
 */
public interface CaseAnalysisService {

	static final String KEY_WIFIPOINTCODE = "wifiPointCode";
	static final String KEY_CASECODE = "caseCode";
	static final String KEY_FROMDATE = "fromDate";
	static final String KEY_TODATE = "toDate";
	
	/**
	 * 查找案件坐标点一定距离范围内的wifi围栏点。使用solar接口，查询wifi围栏监控点collection（wifipoint）实现。
	 * 
	 * @param longitude    经度
	 * @param latitude    纬度
	 * @param scope    范围（米）
	 * @return wifi监控点信息列表
	 */
	public List<WifiMonitorPointBean> findWifiMonitorPoint(String longitude, String latitude, int scope);

	/**
	 * 查找在时间范围内一组wifi监控点共同捕获mac地址信息。使用solar接口，查询wifi轨迹collection（wifitrack）实现。
	 *
	 * @param searchConditions 查询条件集合，map包含下面4个key
	 *      <br>caseCode	案件编码
	 * 		<br>wifiPointCode	wifi监控点编码
	 * 		<br>fromDate	   起始时间
	 * 		<br>toDate    结束时间
	 * @return  返回<mac，Set<String>>列表，key表示mac地址，value表示同时发现该mac的案件编码
	 */
	public Map<String,Set<String>> findCommonMac(List<Map<String,Object>> searchConditions);

}