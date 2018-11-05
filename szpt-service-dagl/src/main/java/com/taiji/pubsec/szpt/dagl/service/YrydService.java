package com.taiji.pubsec.szpt.dagl.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.dagl.bean.YrydCybercafeBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydHotelBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydLocusBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydPlaneGoOutBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydTrainGoOutBean;

public interface YrydService {
	
	/**
	 * 根据人员id查询火车出行信息。
	 * 
	 * @param idcard 身份证号,startDate 查询开始时间, endDate 查询结束时间
	 */
	public List<YrydTrainGoOutBean> findTrainGoOutInfoByIdcard(String idcard,Date startDate,Date endDate);
	
	/**
	 * 根据人员id查询航班出行信息。
	 * 
	 * @param idcard    身份证号, startDate 查询开始时间, endDate 查询结束时间 
	 */
	public List<YrydPlaneGoOutBean> findPlaneGoOutInfoByIdcard(String idcard ,Date startDate,Date endDate);
	
	/**
	 * 根据人员id查询网吧上网信息。
	 * 
	 * @param idcard    身份证号 ,startDate 查询开始时间, endDate 查询结束时间
	 */
	public List<YrydCybercafeBean> findCybercafeInfoByIdcard(String idcard ,Date startDate,Date endDate);
	
	/**
	 * 根据人员id查询旅馆住宿信息。
	 * 
	 * @param idcard    身份证号 ,startDate 查询开始时间, endDate 查询结束时间
	 */
	public List<YrydHotelBean> findHotelInfoByIdcard(String idcard ,Date startDate,Date endDate);
	
	/**
	 * 根据人员手机号或mac地址查询wifi轨迹信息。
	 * 
	 * @param macOrPhoneNum    mac地址或手机号 ,startDate 查询开始时间, endDate 查询结束时间
	 */
	public List<YrydLocusBean> findWifiInfoByIdcard(String macOrPhoneNum,Date startDate,Date endDate);
	
}
