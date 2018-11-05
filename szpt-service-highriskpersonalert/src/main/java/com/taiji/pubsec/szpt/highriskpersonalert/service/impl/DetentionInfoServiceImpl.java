package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.service.DetentionInfoService;

@Service("detentionInfoService")
public class DetentionInfoServiceImpl implements DetentionInfoService{

	private Logger LOGGER = LoggerFactory.getLogger(DetentionInfoServiceImpl.class) ;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@Override
	public Integer findNumByIdenty(String identy) {
		String xql = "select count(d) from DetentionInfo as d where d.identy=:identy" ;
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("identy", identy) ;
		List list = this.dao.findAllByParams(Integer.class, xql, map) ;
		
		if(list.size()>0){
			return Integer.valueOf(list.get(0).toString()) ;
		}
		return 0;
	}
}
