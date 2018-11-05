package com.taiji.pubsec.szpt.score.util.rules.groovy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.score.util.rules.groovy.model.SzptScoreGroovyRule;

@Service
public class SzptScoreGroovyRuleServiceImpl implements SzptScoreGroovyRuleService{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@SuppressWarnings("unchecked")
	@Override
	public SzptScoreGroovyRule findById(String id) {
		return (SzptScoreGroovyRule)this.dao.findById(SzptScoreGroovyRule.class, id) ;
	}

}
