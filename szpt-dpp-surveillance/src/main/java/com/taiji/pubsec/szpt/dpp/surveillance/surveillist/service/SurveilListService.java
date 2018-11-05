package com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service;

import java.util.List;

import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

public interface SurveilListService {

	/**
	 * 加载所有启用、审批通过、时间段有效的布控单到内存
	 */
	public void loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate() ;

	public void unTransRenewSurveilListBySurveilListCode(String surveilListCode) ;
	
	/**
	 * 新增或更新布控单
	 * @param surveilListCode 布控单编码
	 */
	public boolean renewSurveilListBySurveilListCode(String surveilListCode) ;


	public void unTransCancelSurveilListBySurveilListCode(String surveilListCode);
	
	/**
	 * 取消布控单
	 * @param surveilListCode 布控单编码
	 */
	public boolean cancelSurveilListBySurveilListCode(String surveilListCode) ;
	/**
	 * 获得所有的未过期的并且已启用，通过审批的布控单
	 * @return 布控单信息数组
	 */
	public List<SurveilListInfo> getSurveilListInfosByNotOutOfDate() ;
	/**
	 * 按照编码获得未过期的并且已启用，通过审批的布控单
	 * @return 布控单信息
	 */
	public SurveilListInfo getSurveilListInfoByNotOutOfDateByNum(String num) ;
	/**
	 * 按照高危人身份证获得未过期的并且已启用，通过审批的布控单
	 * @return 布控单信息数组
	 */
	public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByIdCardNo(String idCardNo) ;
	/**
	 * 按照mac获得未过期的并且已启用，通过审批的布控单
	 * @return 布控单信息数组
	 */
	public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByMac(String mac) ;
	/**
	 * 按照手机号获得未过期的并且已启用，通过审批的布控单
	 * @return 布控单信息数组
	 */
	public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByPhone(String phone) ;
}
