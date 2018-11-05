package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HotelTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;

/**
 * 人员旅馆酒店轨迹接口 实现
 * @author wangfx
 *
 */
@Service("hotelTrackService")
@Deprecated
public class HotelTrackService implements PersonnelTrackService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode) {
		StringBuilder xql = new StringBuilder("select h from HotelTrack as h where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(null != timeStart) {
			xql.append(" and h.leaveTime > :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(null != timeEnd) {
			xql.append(" and h.enterTime < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		if(StringUtils.isNotBlank(idcode)) {
			xql.append(" and h.personIdcode  = :idcode");
			xqlMap.put("idcode", idcode);
		}
		return this.dao.findAllByParams(HotelTrack.class, xql.toString(), xqlMap);
	}

}
