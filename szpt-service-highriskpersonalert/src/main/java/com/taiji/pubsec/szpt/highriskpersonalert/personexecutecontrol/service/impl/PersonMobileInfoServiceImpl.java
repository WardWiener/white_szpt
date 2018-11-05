package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.service.AbstractBaseService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonMobileInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonMobileInfoService;

@Service("personMobileInfoService")
public class PersonMobileInfoServiceImpl extends AbstractBaseService<PersonMobileInfo, String> implements IPersonMobileInfoService {

	 @Autowired
	    public PersonMobileInfoServiceImpl(Dao<PersonMobileInfo, String> dao) {
	        setDao(dao);
	    }

}
