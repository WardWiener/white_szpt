package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.TrainTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;

/**
 * 人员火车轨迹接口实现
 * @author wangfx
 *
 */
@Service("trainTrackService")
@Deprecated
public class TrainTrackService implements PersonnelTrackService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode) {
		StringBuilder xql = new StringBuilder("select t from TrainTrack as t where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(null != timeStart) {
			xql.append(" and t.reachTime > :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(null != timeEnd) {
			xql.append(" and t.setoutTime < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		if(StringUtils.isNotBlank(idcode)) {
			xql.append(" and t.personIdcode = :idcode");
			xqlMap.put("idcode", idcode);
		}
		return this.dao.findAllByParams(TrainTrack.class, xql.toString(), xqlMap);
	}

}
