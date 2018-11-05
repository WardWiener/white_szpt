package com.taiji.pubsec.szpt.ajgl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ajgl.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.ajgl.model.CriminalPerson;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalPersonService;

@Service("criminalPersonService")
public class CriminalPersonServiceImpl implements ICriminalPersonService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public CriminalPerson findById(String id) {
		return (CriminalPerson) this.dao.findById(CriminalPerson.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseSupectRelation findCaseSupectRelationByPerson(String personId) {
		String xql = "select csr from CaseSupectRelation as csr where csr.person_id = ?";
		return (CaseSupectRelation) this.dao.findByParams(CaseSupectRelation.class, xql, new Object[]{personId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public SufferCaseRelation findSufferCaseRelationByPerson(String personId) {
		String xql = "select csr from SufferCaseRelation as csr where csr.person_id = ?";
		return (SufferCaseRelation) this.dao.findByParams(SufferCaseRelation.class, xql, new Object[]{personId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseSupectRelation> findCaseSupectRelationsByPerson(String personId) {
		String xql = "select csr from CaseSupectRelation as csr where csr.person_id = ?";
		return this.dao.findAllByParams(CaseSupectRelation.class, xql, new Object[]{personId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SufferCaseRelation> findSufferCaseRelationsByPerson(String personId) {
		String xql = "select s from SufferCaseRelation as s where s.person_id = ?";
		return this.dao.findAllByParams(SufferCaseRelation.class, xql, new Object[]{personId});
	}

}
