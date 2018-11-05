package com.taiji.pubsec.szpt.ptjc.service;

import java.util.List;

import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;

public interface PenalConstantService {
	
	
	/**
	 * 通过 日期格式查询刑事常量 
	 * @param type 年月周日
	 * @return 封装过的 AlertThresholdInfo集合
	 */
	public List<AlertThresholdInfo> findPcsAlertThresholdInfosByType(String type);

	/**
	 * 通过单位code查询刑事常量
	 * @param unitCodeLst 单位编码
	 * @param type 年月周日
	 * @return
	 */
	public List<PenalConstant> findPenalConstantByUnitCodeAndType(List<String> unitCodeLst, String type);
	
	/**
	 * 通过单位code删除刑事常量
	 * @param unitCodeLst 单位编码
	 * @param type 年月周日
	 * @return
	 */
	public void deletePenalConstantByUnitCode(List<String> unitCodeLst, String type);
	
	/**
	 * 保存刑事常量
	 * @param entity
	 */
	public void savePenalConstant(PenalConstant entity);
}
