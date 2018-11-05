package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStatInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;

public interface WifiStateRecordService {

	/**
	 * 分页查询一定时间范围内mac地址轨迹信息
	 * @param macs mac地址数组
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param pageNo Integer页号
	 * @param pageSize 每页数量
	 * @return WifiStateRecord的集合
	 */
	public Pager<WifiStateRecord> findByMacs(List<String> macs, Date startDay, Date endDay, Integer pageNo, Integer pageSize) ;
	
	/**
	 * 查询一定时间范围内mac地址轨迹信息，不分页
	 * @param macs mac地址数组
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public List<WifiStateRecord> findByMacs(List<String> macs, Date startDay, Date endDay) ;
	
	/**
	 * 按照人员类型查询场所的出入数量统计
	 * @param personTypes 人员类型编码数组包含任意级别，并且编码的含义是包含子集
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return PlaceCountBean的集合，需要placeCode(场所编码)，count次数（同样的mac地址出入多次，记录多次，统计的是次数）、stayTime所有出入记录停留时间总和
	 */
	public List<PlaceCountBean> findByPersonType(List<String> personTypes, Date startDay, Date endDay) ;
	
	/**
	 * 查询出现高危人次数最多的前N个场所
	 * @param macs mac地址数组
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param descNum 统计前多少个排名
	 * @return PlaceCountBean的集合，需要placeCode(场所编码)， count人出现的次数,记录多次，经纬度
	 */
	public List<PlaceCountBean> findByMacsByTopPlaceAndTimes(List<String> macs, Date startDay, Date endDay, Integer descNum) ;
	
	/**
	 * 查询高危人停留总时长最长的前N个场所
	 * @param macs mac地址数组
	 * @param startDay 开始时间 
	 * @param endDay 结束时间
	 * @param descNum 统计前多少个排名
	 * @return PlaceCountBean的集合，需要placeCode(场所编码)， stayTime停留总时常，经纬度
	 */
	public List<PlaceCountBean> findByMacsByTopAndStayeTime(List<String> macs, Date startDay, Date endDay, Integer descNum) ;

	/**
	 * 按照mac地址查询一定时间范围内在某几个场所的出入记录
	 * @param macs mac地址数组
	 * @param placeCode 场所编码数组
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return 某几个场所对应的所有进出记录
	 */
	public List<WifiStateRecord> findDetailByPlaceByMacs(List<String> macs, List<String> placeCode, Date startDay, Date endDay) ;
	
	/**
	 * 按照派出所查询wifi的采集数量(所有数量，不仅仅指高危人)
	 * @param pcsCodes 一组派出所编码
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return WifiStatInfo的集合 包含key（派出所编码）、count(采集数量)
	 */
	public List<WifiStatInfo> findStatCountByPcs(List<String> pcsCodes, Date startDay, Date endDay) ;
	
	/**
	 * 按照派出所查询wifi的五色人采集数量(只包五色人)
	 * @param pcsCodes 一组派出所编码
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return WifiStatInfo的集合 包含key（派出所编码）、count(五色人采集数量)
	 */
	public List<WifiStatInfo> findStateByPcsByHPersonColor(List<String> pcsCodes, Date startDay, Date endDay) ;
	
	/**
	 * 按照时间范围查询某一组人员类别的记录
	 * @param personTypes 人员类型编码数组包含任意级别，并且编码的含义是包含子集
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return WifiStatInfo的集合 包含key（类别编码）、count(采集数量)
	 */
	public List<WifiStatInfo> findCountByPersonTypes(List<String> personTypes, Date startDay, Date endDay) ;
	
	/**
	 * 按照时间范围查询某个场所的记录
	 * @param placeCode 场所编码
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return WifiStateRecord
	 */
	public List<WifiStateRecord> findByPlaces(String placeCode, Date startDay, Date endDay) ;
	
	/**
	 * 按照时间范围和人员类别查询某个场所的记录
	 * @param placeCode 某个场所的编码
	 * @param personTypes 人员类型编码数组包含任意级别，并且编码的含义是包含子集
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return WifiStatInfo的集合 包含key(人员类别编码)、count(采集数量)
	 */
	public List<WifiStatInfo> findByPlaces(String placeCode, List<String> personTypes, Date startDay, Date endDay) ;
	
}
