package com.taiji.pubsec.szpt.dagl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.dagl.bean.CaseBriefInfo;
import com.taiji.pubsec.szpt.dagl.service.CaseSearchService;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;

@Service
public class CaseSearchServiceImpl implements CaseSearchService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonInterestedService.class);
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;

	@Override
	public CaseBriefInfo findCaseBriefInfo(String caseCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<String> searchCase(String keyword, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
