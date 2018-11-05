package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.GeographicalZonePeopleInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IGeographicalZoneService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("geographicalZoneService")
public class GeographicalZoneServiceImpl implements IGeographicalZoneService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticsInfo> findByGeographicalZone(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select new " + StatisticsInfo.class.getName() + "(g.geographicalZones, count(g.id)) from GeographicalZonePeopleInfo as g where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if (ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and g.lastEntertime >= :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if (ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and g.lastEntertime <= :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		xql.append(" group by g.geographicalZones");
		return this.dao.findAllByParams(GeographicalZonePeopleInfo.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<GeographicalZonePeopleInfo> findGeographicalZonePeopleInfoByZone(Map<String, Object> paramMap,
			int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select g from GeographicalZonePeopleInfo as g where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if (ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and g.lastEntertime >= :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if (ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and g.lastEntertime <= :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if (ParamMapUtil.isNotBlank(paramMap.get("zoneName"))) {
			xql.append(" and g.geographicalZones = :zoneName");
			xqlMap.put("zoneName", paramMap.get("zoneName"));
		}
		xql.append(" order by g.lastEntertime desc");
		return this.dao.findByPage(GeographicalZonePeopleInfo.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

}
