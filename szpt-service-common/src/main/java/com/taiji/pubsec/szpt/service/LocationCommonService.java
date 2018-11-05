package com.taiji.pubsec.szpt.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.bean.LocationCount;
import com.taiji.pubsec.szpt.bean.TrajectoryPos;
import com.taiji.pubsec.szpt.common.model.Location;
import com.taiji.pubsec.szpt.common.model.Trajectory;

/**
 * 警力资源 位置、轨迹信息服务接口
 * @author wangfx
 *
 */
public interface LocationCommonService {
	
	/**
	 * 新增位置信息
	 * @param location 位置信息实体
	 */
	public void createLocation(Location location);
	
	/**
	 * 更新位置信息
	 * @param location 位置信息实体
	 */
	public void updateLocation(Location location);
	
	/**
	 * 根据警力标识查询位置信息
	 * @param identifier 警力标识
	 * @return 位置信息
	 */
	public Location findLocationByIdentifier(String identifier);
	
	/**
	 * 根据警力标示和类型查询位置信息
	 * @param type 车 PDA
	 * @param identifier 警力标示
	 * @return
	 */
	public Location findLocationByTypeAndIdentifier(String type, String identifier);
	
	/**
	 * 新增轨迹信息
	 * @param trajectory 轨迹信息实体
	 */
	public void createTrajectory(Trajectory trajectory);
	
	/**
	 * 查询警力资源在一段时间内的轨迹信息
	 * @param identifier 警力标示
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 轨迹信息列表
	 */
	public List<Trajectory> findTrajectoriesByIdentifier(String identifier, Date start, Date end);
	
	/**
	 * 查询传入单位的警员、警车的位置信息
	 * @return 位置信息列表
	 */
	public List<TrajectoryPos> findAllLocations(Date startDay, Date endDay, String[] pcsCodes);
	
	/**
	 * 查询警力数量
	 * @param pcsCodes 派出所编码
	 * @return LocationCount的集合，name为单位名称，count为location的数量
	 */
	public List<LocationCount> findAllLocationsByCount(Date startDay, Date endDay, String[] pcsCodes,String type) ;
	
	/**
	 * 查询距离某时间过去多少分钟内的所有位置信息
	 * @param startTime 起始时间
	 * @param minute 时间跨度
	 * @return 位置信息列表
	 */
	public List<Location> findLatestLocations(Date startTime, int minute);
	
	/**
	 * 查看警力详情
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param pcsCode 派出所编码集合
	 * @return Location 实时警力详情
	 */
	public List<Location> locationList(Date startDay, Date endDay,String[] pcsCode);
		

}
