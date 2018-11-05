package com.taiji.pubsec.szpt.caseanalysis.suspect.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseMacMatchingResultBean;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.WifiMonitorPointBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseAnalysisService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 嫌疑人Mac地址分析Action
 * 
 * @author WangLei
 *
 */
@Controller("suspectMacAnalysisAction")
@Scope("prototype")
public class SuspectMacAnalysisAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CaseAnalysisService caseAnalysisService;// 嫌疑人MAC分析服务接口
	
	@Resource
	private CaseService caseService;// 案件服务接口
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人接口
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;//TODO 测试用--完后删除
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 前台用
	
	
	/**
	 * 查询所有场所信息
	 * @return
	 */
	public String queryAllPlaceBasicInfo(){//TODO 测试用--完后删除
		List<PlaceBasicInfo> pbis = placeBasicInfoService.findAll();
		List<WifiMonitorPointBean> wmpbs = new ArrayList<WifiMonitorPointBean>();
		if(pbis == null || pbis.isEmpty()){
			resultMap.put("wmpbs", wmpbs);
			return SUCCESS;
		}
		
		for(PlaceBasicInfo pbi : pbis){
			WifiMonitorPointBean wmpb = new WifiMonitorPointBean();
			wmpb.setCode(pbi.getInternetServicePlaceCode());
			wmpb.setName(pbi.getInternetServicePlaceName());
			wmpb.setLongitude(pbi.getLongitude());
			wmpb.setLatitude(pbi.getLatitude());
			wmpbs.add(wmpb);
		}
		resultMap.put("wmpbs", wmpbs);
		return SUCCESS;
	}
	
	
	/**
	 * 根据案发经纬度查询wifi点信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryWifiPointByCaseLonlat(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String scope = (String)rqst.get("scope");
		String hour = (String)rqst.get("hour");
		JSONArray kcaJson = JSONArray.fromObject(rqst.get("knownCaseArray"));
		CriminalBasicCaseBean[] cbcbArray = (CriminalBasicCaseBean[])JSONArray.toArray(kcaJson, CriminalBasicCaseBean.class);
		
		//wifi点详细信息集合
		List<WifiMonitorPointBean> wmpbs = new ArrayList<WifiMonitorPointBean>();
		//前端传入的案件集合不符合要求，返回空的wifi点集合
		if(!(cbcbArray instanceof CriminalBasicCaseBean[]) || cbcbArray.length < 1){
			resultMap.put("wmpbs", wmpbs);
			return SUCCESS;
		}
		//查询条件集合
		List<Map<String, Object>> searchConditions = new ArrayList<Map<String, Object>>();
		//wifi点和案件编号关系集合
		Map<String, List<String>> wifiCaseCodeGxMap = new HashMap<String, List<String>>();
		//循环案件，根据案件经纬度查询周边x米内wifi点
		for(CriminalBasicCaseBean cbcb : cbcbArray){
			if(StringUtils.isBlank(cbcb.getLongitude()) || StringUtils.isBlank(cbcb.getLatitude())){
				continue;
			}
			List<WifiMonitorPointBean> wmpbList = caseAnalysisService.findWifiMonitorPoint(cbcb.getLongitude(), cbcb.getLatitude(), Integer.valueOf(scope));
			if(wmpbList == null || wmpbList.isEmpty()){
				continue;
			}
			
			for(WifiMonitorPointBean wmpb : wmpbList){
				//记录wificode和casecode的对应关系
				String wifiCode = wmpb.getCode();
				if(wifiCaseCodeGxMap.containsKey(wifiCode)){//当前WifiCode已经关联过案件编号
					wifiCaseCodeGxMap.get(wifiCode).add(cbcb.getCaseCode());
				}else{//当前WifiCode还未关联过案件编号
					List<String> ccs = new ArrayList<String>();
					ccs.add(cbcb.getCaseCode());
					wifiCaseCodeGxMap.put(wifiCode, ccs);
				}
				
				//对wifi点去重
				if(!wmpbs.contains(wmpb)){
					wmpbs.add(wmpb);
					//设置添加查询条件
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("wifiPointCode", wmpb.getCode());
					Long caseStartTime = cbcb.getCaseTimeStart();
					int hourTime = 1000*60*60*Integer.valueOf(hour);
					map.put("fromDate", new Date(caseStartTime - hourTime + (1000*60*60*8)));
					map.put("toDate", new Date(caseStartTime + hourTime + (1000*60*60*8)));
					map.put("caseCode", cbcb.getCaseCode());
					searchConditions.add(map);
				}
			}
		}
		queryMatchingResult(searchConditions, cbcbArray.length, wifiCaseCodeGxMap);
		resultMap.put("wmpbs", wmpbs);
		return SUCCESS;
	}
	
	/**
	 * 查询案件分析匹配结果
	 * 
	 * @param wmpsbs wifi监控点查询Bean
	 * @param caseSum 案件总数
	 * @param wifiCaseCodeGxMap wifi点和案件编号关系集合
	 */
	@SuppressWarnings("rawtypes")
	private void queryMatchingResult(List<Map<String, Object>> wmpsbs, int caseSum, Map<String, List<String>> wifiCaseCodeGxMap){
		Map<String, Set<String>> map = caseAnalysisService.findCommonMac(wmpsbs);
		
		//匹配全部案件
		List<CaseMacMatchingResultBean> allMatchingList = new ArrayList<CaseMacMatchingResultBean>();
		//匹配部分案件
		List<CaseMacMatchingResultBean> portionMatchingList = new ArrayList<CaseMacMatchingResultBean>();
		
		for (String mk : map.keySet()) {
			Set<String> caseCodeSet = map.get(mk);
			
			CaseMacMatchingResultBean cmmrb = new CaseMacMatchingResultBean();
			cmmrb.setMac(mk);
			List<String> matchingCaseCodes = new ArrayList(caseCodeSet);
			cmmrb.setMatchingCaseCodes(matchingCaseCodes);
			cmmrb.setMatchingCaseNameStr(findCaseNameByCaseCodes(matchingCaseCodes));
		
			//根据mac地址查询高危人
			HighriskPerson hp = highriskPersonService.findByMac(mk);
			if(hp != null){
				cmmrb.setIdcode(hp.getIdcode());
				cmmrb.setLocalAddressDetail(hp.getLocalAddressDetail());
				cmmrb.setName(hp.getName());
			}
			if(caseCodeSet.size() == caseSum){
				allMatchingList.add(cmmrb);
			}else{
				portionMatchingList.add(cmmrb);
			}
		}
		
		resultMap.put("allMatchingList", allMatchingList);
		resultMap.put("portionMatchingList", portionMatchingList);
	}
	
	/**
	 * 案件编号数组转案件名称字符串
	 * 
	 * @param caseCodes 案件编号数组
	 * @return caseName 案件名称字符串
	 */
	private String findCaseNameByCaseCodes(List<String> caseCodes){
		if(caseCodes == null || caseCodes.isEmpty()){
			return "";
		}
		
		String caseName = "";
		for(int i=0;i<caseCodes.size();i++){
			CriminalBasicCase cbc = caseService.findCaseByCode(caseCodes.get(i));
			if(cbc == null){
				continue;
			}
			caseName += cbc.getCaseName();
			if(i < caseCodes.size() - 1){
				caseName += "、";
			}
		}
		return caseName;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
