package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AlertInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IAlertInfoService;

@Component("alertInfoService")
public class AlertInfoServiceImpl implements IAlertInfoService{
	@Resource
	private Dao<AlertInfo, String> dao;
	
	@Override
	public void saveAlertInfo(AlertInfo alertInfo) {
		dao.save(alertInfo);
	}
}
