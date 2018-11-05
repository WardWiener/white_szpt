package com.taiji.pubsec.szpt.dagl.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.dagl.bean.PersonBriefInfo;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.dagl.service.PersonSearchService;
import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service
public class PersonSearchServiceImpl implements PersonSearchService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonInterestedService.class);
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private FullTextSearchService fullTextSearchService;

	@Override
	public Map<String, Object> searchPerson(String idcard) {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put(SolrConstant.Population.text.getValue(), idcard);
		Map<String, Object> map;
		Pager<Map<String, Object>> pager = fullTextSearchService.queryPerson(conditions, 0, 1);
		return pager.getPageList().get(0);
	}

	@Override
	public Pager<Map<String, Object>> searchPersonGetPager(Map<String,Object> conditions, int pageNo, int pageSize) {
		Pager<Map<String, Object>> pager = fullTextSearchService.queryPerson(conditions, pageNo, pageSize);
		return pager;
	}

}
