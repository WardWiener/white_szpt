package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceService;

@Service
public class WifiPlaceServiceImpl implements WifiPlaceService{

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findWifiPlaceCodesByPcsCode(String pcsCode) {
		String xql = "select p.vendorOrganizationCode FROM " + PlaceBasicInfo.class.getName() + " as p, "
				+ " " + Organization.class.getName()+ " as o "
				+ " WHERE o.id=p.areaDepartmentId"
				+ " and o.code=:pcsCode";
		Map<String, Object> xqlMap = new HashMap<String, Object>() ;
		xqlMap.put("pcsCode", pcsCode);
		return this.dao.findAllByParams(String.class, xql, xqlMap) ;
	}
	
	
}
