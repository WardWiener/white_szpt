package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.GeographicalZonePeopleInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IGeographicalZoneService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

@Controller("geographicalZoneAnalyzeAction")
@Scope("prototype")
public class GeographicalZoneAnalyzeAction extends PageCommonAction{
	
	private static final long serialVersionUID = 1L;
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	@Resource
	private IGeographicalZoneService geographicalZoneService;
	
	private List<StatisticsInfo> statisticsInfoList;
	
	private Pager<GeographicalZonePeopleInfo> pager;
	
	private String zone;
	
	public String findByGeographicalZone(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("timeStart", DateFmtUtil.longToDate(startTime));
		paramMap.put("timeEnd", DateFmtUtil.longToDate(endTime));
		statisticsInfoList = geographicalZoneService.findByGeographicalZone(paramMap);
		return SUCCESS;
	}
	
	public String findPeopleByZone(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("timeStart", DateFmtUtil.longToDate(startTime));
		paramMap.put("timeEnd", DateFmtUtil.longToDate(endTime));
		paramMap.put("zoneName", zone);
		pager = geographicalZoneService.findGeographicalZonePeopleInfoByZone(paramMap, this.getStart()/this.getLength(), this.getLength());
		return SUCCESS;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public List<StatisticsInfo> getStatisticsInfoList() {
		return statisticsInfoList;
	}

	public void setStatisticsInfoList(List<StatisticsInfo> statisticsInfoList) {
		this.statisticsInfoList = statisticsInfoList;
	}

	public Pager<GeographicalZonePeopleInfo> getPager() {
		return pager;
	}

	public void setPager(Pager<GeographicalZonePeopleInfo> pager) {
		this.pager = pager;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
}
