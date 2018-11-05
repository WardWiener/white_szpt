package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.List;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;

/**
 * 预警信息接口
 * @author wangfx
 *
 */
public interface AlertService {
	
	/**
	 * 查询所有的预警信息
	 * @param state 状态
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 预警信息分页
	 */
	Pager<Alert> findAllAlert(String state, int pageNo, int pageSize);
	
	/**
	 * 修改预警信息
	 * @param alert
	 */
	void updateAlert(Alert alert);
	
	/**
	 * 根据id查询预警信息
	 * @param alertId 预警信息id
	 * @return 预警信息
	 */
	Alert findAlertById(String alertId);
	
	void saveAlert(Alert alert);

	List<Alert> findByAlert(Alert alert);
	/**
	 * 根据状态查询预警记录
	 * @return 指定状态预警记录条数
	 */
	int findAlertNumByState(String state);

}
