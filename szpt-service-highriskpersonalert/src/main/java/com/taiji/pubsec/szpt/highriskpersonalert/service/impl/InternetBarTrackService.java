package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.InternetBarTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;

/**
 * 人员网吧轨迹接口实现
 * @author wangfx
 *
 */
@Service("internetBarTrackService")
@Deprecated
public class InternetBarTrackService implements PersonnelTrackService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode) {
		StringBuilder xql = new StringBuilder("select i from InternetBarTrack as i where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(null != timeStart) {
			xql.append(" and i.offineTime > :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(null != timeEnd) {
			xql.append(" and i.onlineTime < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		if(StringUtils.isNotBlank(idcode)) {
			xql.append(" and i.personIdcode  = :idcode");
			xqlMap.put("idcode", idcode);
		}
		return this.dao.findAllByParams(InternetBarTrack.class, xql.toString(), xqlMap);
	}

}
