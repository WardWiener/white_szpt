package com.taiji.pubsec.fullsearch.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.fullsearch.action.bean.CasesBean;
import com.taiji.pubsec.fullsearch.action.bean.ClueBean;
import com.taiji.pubsec.fullsearch.action.bean.CommandBean;
import com.taiji.pubsec.fullsearch.action.bean.EventBean;
import com.taiji.pubsec.fullsearch.action.bean.HighriskpersonBean;
import com.taiji.pubsec.fullsearch.action.bean.PersonBriefInfo;
import com.taiji.pubsec.fullsearch.action.bean.SiteBean;
import com.taiji.pubsec.fullsearch.action.util.Util;
import com.taiji.pubsec.persistence.dao.Pager;

import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * 全文检索
 * @author sunjd
 *
 */
@Controller("fullSearchAction")
@Scope("prototype")
public class FullSearchAction extends PageCommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private Util util;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	private List<EventBean> eventBeanList ;	//警情beanlist
	private List<CasesBean> casesBeanList;	//案件beanlist
	private List<ClueBean> clueBeanList;	//线索beanlist
	private List<CommandBean> commandBeanList;	//指令beanlist
	private List<PersonBriefInfo> highriskpersonBeanList;	//高危人beanlist
	private List<SiteBean> siteBeanList;	//场所beanlist
	private String title;	//检索内容
	private long startTime;	//开始时间--检索首页
	private long endTime;	//结束时间--检索首页
	private String type;   //警情类别
	private long requireDateStartTime; //要求反馈开始时间&警情发生开始时间
	private long requireDateEndTime;   //要求反馈结束时间&警情发生结束时间
	private String receiveunit;
	private int start;
	private int length;	
	
	@Resource
	private FullTextSearchService fullTextSearchService ;
 	
	/**
	 * 检索警情
	 * @return
	 */
	public String searchEvent(){

		eventBeanList = new ArrayList<EventBean>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(SolrConstant.AlertSituation.answertime_start.getValue(), longToDate(startTime));
		map.put(SolrConstant.AlertSituation.answertime_end.getValue(), longToDate(endTime));
		map.put(SolrConstant.AlertSituation.occurtime_start.getValue(), longToDate(requireDateStartTime));
		map.put(SolrConstant.AlertSituation.occurtime_end.getValue(), longToDate(requireDateEndTime));
		map.put(SolrConstant.AlertSituation.text.getValue(), title);
		map.put(SolrConstant.AlertSituation.type.toString(), type);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryAlaram(map,start/length,length);
		for(int i=0;i<dataList.getPageList().size();i++){
			EventBean bean = util.mapTrunEventBean(dataList.getPageList().get(i));
			eventBeanList.add(bean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	/**
	 * 检索案件
	 * @return
	 */
	public String searchCases(){
		casesBeanList = new ArrayList<CasesBean>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(SolrConstant.Case.occurtime_start.getValue(), longToDate(startTime));
		map.put(SolrConstant.Case.occurtime_end.getValue(), longToDate(endTime));
		map.put(SolrConstant.Case.text.getValue(), title);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryCase(map,start/length,length);
		for(int i=0;i<dataList.getPageList().size();i++){
			CasesBean casesBean = util.mapTrunCasesBean(dataList.getPageList().get(i));
			casesBeanList.add(casesBean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	/**
	 * 检索高危人
	 * @return	SUCCCESS
	 */
	public String searchHighriskperson(){
		highriskpersonBeanList = new ArrayList<PersonBriefInfo>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(SolrConstant.Population.collecttime_start.getValue(), longToDate(startTime));
		map.put(SolrConstant.Population.collecttime_end.getValue(), longToDate(endTime));
		map.put(SolrConstant.Population.text.getValue(), title);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryPerson(map,start/length,length);
		
		for(int i=0;i<dataList.getPageList().size();i++){
			PersonBriefInfo bean = util.mapTrunPersonBean(dataList.getPageList().get(i));
			highriskpersonBeanList.add(bean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	/**
	 * 检索场所
	 * @return	SUCCCESS
	 */
	public String searchSite(){
		siteBeanList = new ArrayList<SiteBean>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(SolrConstant.Place.collecttime_start.getValue(), longToDate(startTime));
		map.put(SolrConstant.Place.collecttime_end.getValue(), longToDate(endTime));
		//map.put(SolrConstant.Place.text.getValue(), title);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryPlace(map,start/length,length);
		for(int i=0;i<dataList.getPageList().size();i++){
			SiteBean bean = util.mapTrunSiteBean(dataList.getPageList().get(i));
			siteBeanList.add(bean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	/**
	 * 检索指令
	 * @return	SUCCCESS
	 */
	public String searchCommand(){
		commandBeanList = new ArrayList<CommandBean>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(SolrConstant.Instruction.createtime_start.getValue(), longToDate(startTime));
		map.put(SolrConstant.Instruction.createtime_end.getValue(), longToDate(endTime));
		map.put(SolrConstant.Instruction.text.getValue(), title);
		map.put(SolrConstant.Instruction.type.getValue(), type);
		map.put(SolrConstant.Instruction.askfeedbacktime_start.getValue(), longToDate(requireDateStartTime));//反馈开始
		map.put(SolrConstant.Instruction.askfeedbacktime_end.getValue(), longToDate(requireDateEndTime));//反馈开始
		map.put(SolrConstant.Instruction.receiveunit.getValue(), receiveunit);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryInstruction(map,start/length,length);
		for(int i=0;i<dataList.getPageList().size();i++){
			CommandBean bean = util.mapTrunCommandBean(dataList.getPageList().get(i));
			commandBeanList.add(bean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	/**
	 * 检索线索
	 * @return	SUCCCESS
	 */
	public String searchClue(){
		clueBeanList = new ArrayList<ClueBean>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("createTimeStart",startTime );
		map.put("createTimeEnd",endTime );
		map.put("keyword", title);
		Pager<Map<String,Object>> dataList=fullTextSearchService.queryInstruction(map,start/length,length);
		for(int i=0;i<dataList.getPageList().size();i++){
			ClueBean bean=new ClueBean();
			bean.setName(String.valueOf(dataList.getPageList().get(i).get("name")));
			bean.setState(String.valueOf(dataList.getPageList().get(i).get("state")));
			bean.setDate(Long.valueOf(String.valueOf(dataList.getPageList().get(i).get("date"))));
			clueBeanList.add(bean);
		}
		this.setTotalNum(dataList.getTotalNumber().intValue());
		return SUCCESS;
	}
	
	private Date longToDate(Long time){
		if(time == 0l){
			return null;
		}
		Calendar cal = Calendar.getInstance() ;
		cal.setTimeInMillis(time);
		return cal.getTime() ;
	}

	public List<EventBean> getEventBeanList() {
		return eventBeanList;
	}

	public void setEventBeanList(List<EventBean> eventBeanList) {
		this.eventBeanList = eventBeanList;
	}

	public List<CasesBean> getCasesBeanList() {
		return casesBeanList;
	}

	public void setCasesBeanList(List<CasesBean> casesBeanList) {
		this.casesBeanList = casesBeanList;
	}

	public List<ClueBean> getClueBeanList() {
		return clueBeanList;
	}

	public void setClueBeanList(List<ClueBean> clueBeanList) {
		this.clueBeanList = clueBeanList;
	}

	public List<CommandBean> getCommandBeanList() {
		return commandBeanList;
	}

	public void setCommandBeanList(List<CommandBean> commandBeanList) {
		this.commandBeanList = commandBeanList;
	}

	public List<PersonBriefInfo> getHighriskpersonBeanList() {
		return highriskpersonBeanList;
	}

	public void setHighriskpersonBeanList(List<PersonBriefInfo> highriskpersonBeanList) {
		this.highriskpersonBeanList = highriskpersonBeanList;
	}

	public List<SiteBean> getSiteBeanList() {
		return siteBeanList;
	}

	public void setSiteBeanList(List<SiteBean> siteBeanList) {
		this.siteBeanList = siteBeanList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getRequireDateStartTime() {
		return requireDateStartTime;
	}

	public void setRequireDateStartTime(long requireDateStartTime) {
		this.requireDateStartTime = requireDateStartTime;
	}

	public long getRequireDateEndTime() {
		return requireDateEndTime;
	}

	public void setRequireDateEndTime(long requireDateEndTime) {
		this.requireDateEndTime = requireDateEndTime;
	}

	public String getReceiveunit() {
		return receiveunit;
	}

	public void setReceiveunit(String receiveunit) {
		this.receiveunit = receiveunit;
	}

}
