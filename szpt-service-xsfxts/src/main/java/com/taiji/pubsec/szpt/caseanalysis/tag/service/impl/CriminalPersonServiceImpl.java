package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;

@Service("criminalSzptPersonService")
public class CriminalPersonServiceImpl implements CriminalPersonService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public CriminalPerson findCriminalPersonByIdcard(String idcard) {
		if(StringUtils.isBlank(idcard)){
			return null ;
		}
		String xql = "select c from CriminalPerson as c where c.idcardNo = ?";
		return (CriminalPerson) dao.findByParams(CriminalPerson.class, xql, new Object[]{idcard});
	}

}
