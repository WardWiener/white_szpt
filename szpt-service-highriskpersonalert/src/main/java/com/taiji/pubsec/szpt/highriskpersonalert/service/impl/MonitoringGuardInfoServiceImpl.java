package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.service.AbstractBaseService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MonitoringGuardInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IMonitoringGuardInfoService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("monitoringGuardInfoService")
public class MonitoringGuardInfoServiceImpl extends AbstractBaseService<MonitoringGuardInfo, String> implements IMonitoringGuardInfoService{

	@Autowired
	public MonitoringGuardInfoServiceImpl(Dao<MonitoringGuardInfo, String> dao) {
		setDao(dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticsInfo> findMonitoringGuardInfoByPerson(String personIdCode, Date timeStart, Date timeEnd) {
		StringBuilder xql = new StringBuilder("select new " + StatisticsInfo.class.getName() + "(m.monitoredSite, count(m.id)) from MonitoringGuardInfo as m where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(personIdCode)) {
			xql.append(" and m.monitoredPersonCode = :personIdCode");
			xqlMap.put("personIdCode", personIdCode);
		}
		if(ParamMapUtil.isNotBlank(timeStart)) {
			xql.append(" and m.monitoredTime >= :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(ParamMapUtil.isNotBlank(timeEnd)) {
			xql.append(" and m.monitoredTime <= :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		return this.findByXql(xql.toString(), xqlMap);
	}

	@Override
	public List<MonitoringGuardInfo> findMonitoringGuardInfosByPerson(String personIdCode, Date timeStart,
			Date timeEnd) {
		StringBuilder xql = new StringBuilder("select m from MonitoringGuardInfo as m where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(personIdCode)) {
			xql.append(" and m.monitoredPersonCode = :personIdCode");
			xqlMap.put("personIdCode", personIdCode);
		}
		if(ParamMapUtil.isNotBlank(timeStart)) {
			xql.append(" and m.monitoredTime >= :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(ParamMapUtil.isNotBlank(timeEnd)) {
			xql.append(" and m.monitoredTime <= :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		return findAllByParams(xql.toString(), xqlMap);
	}

}
