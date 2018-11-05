package com.taiji.pubsec.scoreframework.rules.groovyrule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.rules.groovyrule.model.BasedGroovyRule;

@Service("basedGroovyRuleService")
public class BasedGroovyRuleServiceImpl implements BasedGroovyRuleService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@Override
	public BasedGroovyRule findById(String id) {
		return (BasedGroovyRule)this.dao.findById(BasedGroovyRule.class, id) ;
	}

	@Override
	public void save(BasedGroovyRule entity) {
		dao.save(entity);
		
	}

	@Override
	public void update(BasedGroovyRule entity) {
		dao.update(entity);
	}

	
}
