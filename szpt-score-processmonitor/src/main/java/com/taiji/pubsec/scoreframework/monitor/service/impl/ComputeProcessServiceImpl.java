package com.taiji.pubsec.scoreframework.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.monitor.model.ComputeProcess;
import com.taiji.pubsec.scoreframework.monitor.model.FailItem;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;

@Service
public class ComputeProcessServiceImpl implements ComputeProcessService{

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public String createComputeProcess(ComputeProcess computeProcess) {
		this.dao.save(computeProcess);
		return computeProcess.getId();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateComputeProcess(ComputeProcess computeProcess) {
		this.dao.update(computeProcess);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String createFailItem(FailItem failItem) {
		this.dao.save(failItem);
		return failItem.getId() ;
	}

	@Override
	public void increaseCompleteNumByOne(String computeprocessid) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ComputeProcess getComputeProcess(String id) {
		return (ComputeProcess)this.dao.findById(ComputeProcess.class, id);
	}
	
	
}
