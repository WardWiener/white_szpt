package com.taiji.pubsec.scoreframework.rules.groovyrule;

import com.taiji.pubsec.scoreframework.rules.groovyrule.model.BasedGroovyRule;

public interface BasedGroovyRuleService{

	public BasedGroovyRule findById(String id); 
	
	public void save(BasedGroovyRule entity);
	
	public void update(BasedGroovyRule entity);
}
