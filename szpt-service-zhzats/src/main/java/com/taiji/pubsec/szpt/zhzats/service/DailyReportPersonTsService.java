package com.taiji.pubsec.szpt.zhzats.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.zhzats.bean.DailyReportPersonInfo;
import com.taiji.pubsec.szpt.zhzats.model.DailyReportPerson;

/**
 * 报备警力服务接口
 * @author XIEHF
 *
 */
public interface DailyReportPersonTsService {
	
	/**
	 * 查询报备警力
	 * @param startDay 开始日期
	 * @param endDay 结束日期
	 * @param pcsCode 派出所编码集合
	 * @param type 分析基础类型（派出所  主格）
	 * @return 报备警力数
	 */
	public List<DailyReportPersonInfo> findDailyReportPerson(Date startDay, Date endDay, String[] pcsCodes, String type);
	
	/**
	 * 查询报备警力详情集合
	 * @param startDay 开始日期
	 * @param endDay 结束日期
	 * @param pcsCode 派出所编码集合
	 * @return DailyReportPerson  报备警力集合
	 */
	public List<DailyReportPerson> dailyReportPersonTsList(Date startDay, Date endDay, String[] pcsCode);

}
