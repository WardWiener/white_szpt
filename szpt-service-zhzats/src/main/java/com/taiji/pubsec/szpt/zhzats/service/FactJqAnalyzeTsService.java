package com.taiji.pubsec.szpt.zhzats.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.bean.AlarmPos;

public interface FactJqAnalyzeTsService {
	
	/**
	 * 查询警情数量
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param pcsCodes 派出所编码
	 * @param jqlxCodes 警情类型编码
	 * @return AlarmInfo的list，AlarmInfo中需要name（派出所名称），count（本期的数量）
	 */
	public List<AlarmInfo> findAlarmInfosByPcsCodes(Date startDay, Date endDay, String[] pcsCodes, String[] jqlxCodes) ;

	/**
	 * 查询警情数量
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码(警情类型编码用like去查包含子项，所以集合要去重)
	 * @param pcsCodes 派出所编码
	 * @param method 调用的方法
	 * @return AlarmInfo的list，AlarmInfo中需要name（警情类型名称），nameCode（警情类型名称对应的编码），count（本期的数量）
	 */
	public List<AlarmInfo> findAlarmInfosByJqlxCodesByPcsCodes(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes, String method) ;
	/**
	 * 查询警情坐标点
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param pcsCodes 派出所编码
	 * @return AlarmPos的集合
	 */
	public List<AlarmPos> findAlarmPos(Date startDay, Date endDay, String[] pcsCodes) ;
	
	/**
	 * 查询警情坐标点
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码
	 * @param pcsCodes 派出所编码
	 * @return AlarmPos的集合 AlarmPos中 name为派出所名称，其他两个参数分别为经纬度
	 */
	public List<AlarmPos> findAlarmPos(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes) ;
	
	/**
	 * 查询警情数量总数
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码(警情类型编码用like去查包含子项，所以集合要去重)
	 * @param pcsCodes 派出所编码
	 * @return 总和
	 */
	public Integer findAlarmInfosByJqlxCodesByPcsCodesByTotal(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes) ;
	
	/**
	 * 按照时段查询警情数量（时段参见com.taiji.pubsec.szpt.util.Constant中的枚举SHI_DUAN）
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param pcsCodes 派出所编码
	 * @return AlarmInfo的list，AlarmInfo中需要name（时段名称，对应枚举SHI_DUAN的key值），count（本期的数量）
	 */
	public List<AlarmInfo> findAlarmInfosByPcsCodesBuShiDuan(Date startDay, Date endDay, String[] pcsCodes) ;
	
	/**
	 * 按照时段和派出所查询警情数量（时段参见com.taiji.pubsec.szpt.util.Constant中的枚举SHI_DUAN）
	 * 返回的集合，以派出所名称和时段名称作为两个维度
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码(警情类型编码用like去查包含子项，所以集合要去重)
	 * @param pcsCodes 派出所编码
	 * @return AlarmInfo的list，AlarmInfo中需要name（时段名称，对应枚举SHI_DUAN的key值），nameAdd1(派出所名称), count（本期的数量）
	 */
	public List<AlarmInfo> findAlarmInfosByPcsCodesBuShiDuanByJqlx(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes) ;
	
	/**
	 * 查询主格警情数量
	 * @param startDay
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码
	 * @param type (派出所  或  主格)
	 * @return  AlarmPos的集合 AlarmPos中 name为派出所名称，其他两个参数分别为经纬度
	 */
	public List<AlarmPos> findGriddJq(Date startDay, Date endDay, String[] jqlxCodes, String type);
	
	/**
	 * 按照时段和派出所查询警情数量（时段参见com.taiji.pubsec.szpt.util.Constant中的枚举SHI_DUAN）
	 * 返回的集合，以村居名称和时段名称作为两个维度
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param jqlxCodes 警情类型编码(警情类型编码用like去查包含子项，所以集合要去重)
	 * @param pcsCodes 派出所编码
	 * @return AlarmInfo的list，AlarmInfo中需要name（时段名称，对应枚举SHI_DUAN的key值），nameAdd1(村居名称), count（本期的数量）
	 */
	public List<AlarmInfo> findAlarmInfosByCommunityCodesBuShiDuanByJqlx(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes);
}
