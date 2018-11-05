package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CollectInfoSituation;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CollectInfoSituationService;

@Service("collectInfoSituationService")
public class CollectInfoSituationServiceImpl implements CollectInfoSituationService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public CollectInfoSituation findCollectInfoSituationByIdcard(String idcard) {
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		String xql = "select c from CollectInfoSituation as c where c.identy =:idcard ORDER BY c.updatedTime DESC";
		xqlMap.put("idcard", idcard);
		List<CollectInfoSituation> cisList = dao.findAllByParams(CollectInfoSituation.class, xql, xqlMap);
		if(cisList == null || cisList.isEmpty()){
			return null;
		}else{
			return cisList.get(0);
		}
	}

}
