package com.taiji.pubsec.szpt.caseanalysis.welcome.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.TagCountResultBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseStatisticService;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * 刑事分析首页分析Action
 * 
 * @author WangLei
 *
 */
@Controller("welcomeAction")
@Scope("prototype")
public class WelcomeAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CaseStatisticService caseStatisticService;// 统计查询接口
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用
	
	/**
	 * 根据时间段查询盗窃案（饼图）
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryLarcenyCaseByTime(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Date nowDate = new Date();
		Date startDate = JSONNull.getInstance().equals(rqst.get("startTime")) ? new Date(nowDate.getTime() - 1000*60*60*24) :new Date((Long)rqst.get("startTime"));
		Date endDate = JSONNull.getInstance().equals(rqst.get("endTime"))? nowDate :new Date((Long)rqst.get("endTime"));
		List<Map<String,Integer>> pcsLarcenyCaseNum =  caseStatisticService.countRipoffCaseByRegion(null, startDate, endDate);
		
		resultMap.put("pcsLarcenyCaseNum", pcsLarcenyCaseNum);
		return SUCCESS;
	}
	
	/**
	 * 根据时间段查询盗窃案（饼图）
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryRobberyCaseByTime(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Date nowDate = new Date();
		Date startDate = JSONNull.getInstance().equals(rqst.get("startTime")) ? new Date(nowDate.getTime() - 1000*60*60*24) :new Date((Long)rqst.get("startTime"));
		Date endDate = JSONNull.getInstance().equals(rqst.get("endTime"))? nowDate :new Date((Long)rqst.get("endTime"));
		List<Map<String,Integer>> pcsRobberyCaseNum =  caseStatisticService.countRobberyCaseByRegion(null, startDate, endDate);
		
		resultMap.put("pcsRobberyCaseNum", pcsRobberyCaseNum);
		return SUCCESS;
	}
	
	/**
	 * 查询案发处所统计数量
	 * 
	 * @return
	 */
	public String queryOccurPlaceCount(){
		List<TagCountResultBean> cpcs = caseStatisticService.countByTagOccurPlace(setQueryConditionMap());
		resultMap.put("cpcs", cpcs);
		return SUCCESS;
	}
	
	/**
	 * 查询作案特点统计数量
	 * 
	 * @return
	 */
	public String queryCaseFeatureCount(){
		List<TagCountResultBean> cpcs = caseStatisticService.countByTagCaseFeature(setQueryConditionMap());
		resultMap.put("cpcs", cpcs);
		return SUCCESS;
	}
	
	/**
	 * 查询作案时段统计数量
	 * 
	 * @return
	 */
	public String queryCasePeriodCount(){
		List<TagCountResultBean> cpcs = caseStatisticService.countByTagCasePeriod(setQueryConditionMap());
		resultMap.put("cpcs", cpcs);
		return SUCCESS;
	}
	/**
	 * 设置查询条件map
	 * 
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> setQueryConditionMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Date nowDate = new Date();
		map.put("region", JSONNull.getInstance().equals(rqst.get("region"))?null:(String)rqst.get("region"));
		map.put("fromDate", JSONNull.getInstance().equals(rqst.get("startTime")) ? new Date(nowDate.getTime() - 1000*60*60*24) : new Date((Long)rqst.get("startTime")));
		map.put("toDate", JSONNull.getInstance().equals(rqst.get("endTime")) ? nowDate : new Date((Long)rqst.get("endTime")));
		map.put("type", JSONNull.getInstance().equals(rqst.get("type"))?null:(String)rqst.get("type"));
		map.put("firstSort", JSONNull.getInstance().equals(rqst.get("firstSort"))?null:(String)rqst.get("firstSort"));
		map.put("secondSort", JSONNull.getInstance().equals(rqst.get("secondSort"))?null:(String)rqst.get("secondSort"));
		return map;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	

}
