package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;
import com.taiji.pubsec.szpt.highriskpersonalert.service.AlertService;

@Service("alertService")
public class AlertServiceImpl implements AlertService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Alert> findAllAlert(String state, int pageNo, int pageSize) {
		String hql = "select a from Alert as a where a.state = :state order by a.createTime desc";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("state", state);
		return this.dao.findByPage(Alert.class, hql, xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateAlert(Alert alert) {
		this.dao.update(alert);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Alert findAlertById(String alertId) {
		return (Alert) this.dao.findById(Alert.class, alertId);
	}

	@Override
	public void saveAlert(Alert alert) {
		dao.save(alert);
	}
	
	@Override
	public List<Alert> findByAlert(Alert alert) {
		StringBuilder xql = new StringBuilder(
				"select a from Alert as a where place =:place and warning=:warning and personNames=:personNames");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("place", alert.getPlace());
		xqlMap.put("warning", alert.getWarning());
		xqlMap.put("personNames", alert.getPersonNames());
		if (!StringUtils.isEmpty(alert.getKey())) {
			xql.append(" and key=:key");
			xqlMap.put("key", alert.getKey());

		}
		List<Alert> list = dao.findAllByParams(Alert.class, xql.toString(),xqlMap);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findAlertNumByState(String state) {
		String xql = "select a from Alert as a where a.state = :state";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("state", state);
		return dao.getTotalNum(xql, xqlMap);
	}

}
