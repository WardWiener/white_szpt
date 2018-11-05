package com.taiji.pubsec.szpt.ajgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ajgl.model.CriminalObject;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalObjectService;

@Service("criminalObjectService")
public class CriminalObjectServiceImpl implements ICriminalObjectService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public CriminalObject findById(String id) {
		return (CriminalObject) this.dao.findById(CriminalObject.class, id);
	}
	
}
