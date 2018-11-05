package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.zhzats.bean.FloatingPopInfo;
import com.taiji.pubsec.szpt.zhzats.model.FloatingPop;
import com.taiji.pubsec.szpt.zhzats.service.FloatingPopAnalyzeTsService;

@Service
public class FloatingPopAnalyzeTsServiceImpl implements FloatingPopAnalyzeTsService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FloatingPopInfo> findFloatingPopInfosByPcsCodes(Date startDay,
			Date endDay, String[] pcsCodes) {
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(" SELECT ld.dwmc, sum(ld.sl) FROM t_zhzats_sqjw_ldrk as ld WHERE 1=1 ");
		if (null != startDay) {
			hql.append(" AND ld.ldsj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND ld.ldsj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes) ;
			hql.append("AND ld.dwbm in (:key)");
			hqlMap.put("key", key);
		}
		hql.append(" GROUP BY ld.dwbm ");
		List<Object[]> fpinfoResult = sqlDao.find(hql.toString(), hqlMap);
		
		List<FloatingPopInfo> list = new ArrayList<FloatingPopInfo>();
		
		for(Object[] obj : fpinfoResult) {
			int floatCount = 0;
			if(obj[0] != null){
				if(obj[1] != null) {
					floatCount = Integer.valueOf(obj[1].toString());
				}
				list.add(new FloatingPopInfo(Array.get(obj,0).toString(), floatCount));
			}
		}
		return list;
	}

}
