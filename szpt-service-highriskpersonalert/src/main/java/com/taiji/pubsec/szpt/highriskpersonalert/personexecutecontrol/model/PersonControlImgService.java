package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;


@Service
class PersonControlImgService {

	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	@SuppressWarnings("unchecked")
	public void save(PersonControlImg entity){
		this.dao.save(entity);
	}
}
