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

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.zhzats.bean.InterrogatSituInfo;
import com.taiji.pubsec.szpt.zhzats.service.InterrogatSituAnalyzeTsService;

@Service
public class InterrogatSituAnalyzeTsServiceImpl implements InterrogatSituAnalyzeTsService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;

	@SuppressWarnings("unchecked")
	@Override
	public List<InterrogatSituInfo> findInterrogatSituByPcsCodes(Date startDay, Date endDay, String[] pcsCodes){
		
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		
		StringBuilder hql = new StringBuilder(" SELECT pc.dwmc,sum(pc.ry),sum(pc.kycl),sum(pc.fkycl) FROM t_zhzats_hlck_pcqk as pc WHERE 1=1 ");
		if(null != startDay){
			hql.append(" AND pc.pcsj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if(null != endDay){
			hql.append(" AND pc.pcsj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			hql.append("AND pc.dwbm in (:key)");
			hqlMap.put("key", key);
		}
		hql.append(" GROUP BY pc.dwbm ");
		List<Object[]> isinfoResult = sqlDao.find(hql.toString(), hqlMap);
		
		List<InterrogatSituInfo> list = new ArrayList<InterrogatSituInfo>();
		
		for(Object[] obj : isinfoResult) {
			int personCount = 0;
			int busCount = 0;
			int vehicleCount = 0;
			if(obj[0] != null){
				if(obj[1] != null) {
					personCount = Integer.valueOf(obj[1].toString());
				}
				if(obj[2] != null) {
					busCount = Integer.valueOf(obj[2].toString());
				}
				if(obj[3] != null) {
					vehicleCount = Integer.valueOf(obj[3].toString());
				}
				list.add(new InterrogatSituInfo(Array.get(obj,0).toString(), personCount, busCount, vehicleCount));
			}
		}
		return list;
	}

}
