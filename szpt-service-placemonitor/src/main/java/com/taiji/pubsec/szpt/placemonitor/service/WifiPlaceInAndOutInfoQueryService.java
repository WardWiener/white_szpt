package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;

/**
 * WiFi查询接口
 * @author wangfx
 *
 */
public interface WifiPlaceInAndOutInfoQueryService {
	
	/**
	 * 按条件查找所有命中记录，条件包括mac地址列表、起始时间、结束时间、场所名称。按照进入时间倒排
	 * @param paramMap 查询条件map
	 * <br>:macList mac地址列表
	 * <br>:timeStart 起始时间
	 * <br>:timeEnd 结束时间
	 * <br>:placeName 场所名称
	 * <br>:peopleTypeList 人员类型列表
	 * @return WiFi命中记录
	 */
	List<WifiPlaceInAndOutInfo> findAllByMacs(Map<String, Object> paramMap);
	
	/**
	 * 按条件查找所有命中记录，条件包括mac地址列表、起始时间、结束时间、场所名称。按照进入时间倒排
	 * @param paramMap 查询条件map
	 * <br>:macList mac地址列表
	 * <br>:timeStart 起始时间
	 * <br>:timeEnd 结束时间
	 * <br>:placeName 场所名称
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 命中记录分页信息
	 */
	Pager<WifiPlaceInAndOutInfo> findByMacs(Map<String, Object> paramMap, int pageNo, int pageSize);
	
	/**
	 * 根据条件查询统计命中的次数，结果按照count或者totalInterval倒排。条件可以包括起始时间、结束时间、人员类型。wifi分析
	 * @param paramMap 查询条件map
	 * </br>:timeStart 起始时间
	 * </br>:timeEnd 结束时间
	 * <br>:macList mac地址列表
	 * </br>:placeName 场所名称（等值匹配）
	 * </br>peopleTypeList 人员类型编码列表
	 * @return 分组统计结果
	 */
	List<PlaceSumInfo> sumupHitCountByPeopleType(Map<String, Object> paramMap);
	
	/**
	 * 根据条件按监测场所统计命中的次数，结果按次数倒序排列。条件可以包括起始时间、结束时间、场所名称
	 * @param paramMap 查询条件map
	 * </br>:timeStart 起始时间
	 * </br>:timeEnd 结束时间
	 * </br>:placeName 场所名称
	 * @return 分组统计结果
	 */
	List<PlaceSumInfo> sumupHitCountByPlace(Map<String, Object> paramMap);
	
	/**
	 * 按条件统计场所的逗留次数，条件包括mac地址列表、起始时间、结束时间。按照停留次数倒排
	 * @param paramMap 查询条件map
	 * </br>:macList mac地址列表
	 * </br>:timeStart 起始时间
	 * </br>:timeEnd 结束时间
	 * @return 分组统计结果
	 */
	List<PlaceSumInfo> sumupStayCountByMacs(Map<String, Object> paramMap);
	
	/**
	 * 按条件统计每个场所的逗留时间长，条件包括mac地址列表、起始时间、结束时间。 按照停留时长倒序
	 * @param paramMap 查询条件map
	 * <br>:macList mac地址列表
	 * </br>:timeStart 起始时间
	 * </br>:timeEnd 结束时间
	 * @return 分组统计结果
	 */
	List<PlaceSumInfo> sumupStayIntervalByMacs(Map<String, Object> paramMap);
	
	/**
	 * 根据时间和人员类型查询命中次数及驻留时长
	 * @param paramMap 查询条件map
	 * <br>:timeStart 起始时间
	 * </br>:timeEnd 结束时间
	 * </br>peopleTypeList 人员类型编码列表
	 * @return 分组统计结果
	 */
	List<PlaceSumInfo> sumupHitCountAndStayByCondition(Map<String, Object> paramMap);

	/**
	 * 根据场出入信息id查询场所出入信息
	 * @param wifiPlaceInAndOutInfoId 出入信息id
	 * @return 场所出入信息
	 */
	WifiPlaceInAndOutInfo findWifiPlaceInAndOutInfoById(String wifiPlaceInAndOutInfoId);

	/**
	 * 根据对象查询
	 * @param wifiPlaceInAndOutInfo
	 * @return
	 */
	List<WifiPlaceInAndOutInfo> queryWifiPlaceInAndOutInfo(
			WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo);

	/**
	 * 保存wifi出入数据
	 * @param wifiPlaceInAndOutInfo
	 */
	void saveWifiPlaceInAndOutInfo(WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo);

	/**
	 * 更新wifi出入数据
	 * @param wifiPlaceInAndOutInfo
	 */
	void updateWifiPlaceInAndOutInfo(WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo);

	WifiPlaceInAndOutInfo findById(String id);
	
	/**
	 * 根据身份证号、开始时间、结束时间查询场所命中次数，按次数倒叙
	 * @param idCode 身份证号
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 统计结果
	 */
	List<PlaceSumInfo> summpHitCountByPersonIdCode(String idCode, Date timeStart, Date timeEnd);
	
	/**
	 * 根据身份证号查询wifi场所出入信息
	 * @param idCode 身份证号
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return wifi场所出入信息
	 */
	List<WifiPlaceInAndOutInfo> findWifiPlaceInAndOutInfoByIdCode(String idCode, Date timeStart, Date timeEnd);

}
