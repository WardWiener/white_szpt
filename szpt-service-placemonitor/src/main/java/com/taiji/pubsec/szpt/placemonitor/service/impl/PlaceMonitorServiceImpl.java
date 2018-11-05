package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceMonitorService;
import com.taiji.pubsec.szpt.redisson.RedissonHelper;
import com.taiji.pubsec.szpt.redisson.util.RedisConstant;
import com.taiji.pubsec.szpt.util.DateFmtUtil;

import net.sf.json.JSONObject;

@Service("placeMonitorService")
public class PlaceMonitorServiceImpl implements PlaceMonitorService {

	// 用于记录当前批次的所有wifi轨迹数据的缓存
	private final static String REDIS_KEY_CURRENT_MAC_WIFITRACK = "current_mac_wifitrack";
	// 用于记录当前批次中每个wifi监控点的所有mac的缓存key的前缀
	private final static String REDIS_KEY_CURRENT_POINT_MACS_PREFIX = "current_point_";

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Override
	public List<WifiPlaceInAndOutInfoBean> findAllByMacs(List<String> macs){
		
		Set<String> set= new HashSet<String>();
		set.addAll(macs);
		
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(RedisConstant.CURRENT_MAC_WIFITRACK.RMAP_KEY.getValue());
		
		List<WifiPlaceInAndOutInfoBean> list = new ArrayList<WifiPlaceInAndOutInfoBean>() ;
		for(Entry<String, String> entry : maptrack.getAll(set).entrySet()){
			WifiData data = (WifiData)JSONObject.toBean(JSONObject.fromObject(entry.getValue()), WifiData.class) ;
			
			WifiPlaceInAndOutInfoBean bean = new WifiPlaceInAndOutInfoBean() ;
			BeanCopier copier = BeanCopier.create(data.getClass(), bean.getClass(), false);
			copier.copy(data, bean, null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			bean.setCreateTime(data.getCreateTime() == null ? "" : sdf.format(data.getCreateTime()));
			list.add(bean); 
		}

		return list ;
	}


	@SuppressWarnings("unchecked")
	@Override
	public PlaceBasicInfo findPlaceBasicInfoByPlaceName(String placeName) {
		if (StringUtils.isBlank(placeName))
			return null;
		String xql = "select p from PlaceBasicInfo as p where p.internetServicePlaceName = :placeName";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("placeName", placeName);
		List<PlaceBasicInfo> list = dao.findAllByParams(PlaceBasicInfo.class, xql, xqlMap);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int sumupMonitorDeviceCount() {
//		String xql = "select sum(t.num) from PlaceMonitor as t";
//		Number num = (Number) this.dao.findByXql(xql, new HashMap<String, Object>()).get(0);
//		return num == null ? 0 : num.intValue();
		RMapCache<String, String> map = RedissonHelper.getClient().getMapCache(REDIS_KEY_CURRENT_MAC_WIFITRACK);
		return map.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> sumupDeviceaByPlace() {
//		String xql = "select new com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo(p.internetServicePlaceName, p.placeMonitor.num, p.longitude, p.latitude) from PlaceBasicInfo as p order by p.placeMonitor.num desc";
//		return this.dao.findByXql(xql, new HashMap<String, Object>());

		String xql = "select new com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo(p.internetServicePlaceName, p.internetServicePlaceCode, p.longitude, p.latitude) from PlaceBasicInfo as p group by p.internetServicePlaceCode";
		List<PlaceSumInfo> result  = this.dao.findByXql(xql, new HashMap<String, Object>());
		for(PlaceSumInfo ps : result) {
			/**
			 * modify by genganpeng
			 * modifyTime : 2017-02-20
			 * 修改原因：只需要查询redis中各个场所的当前mac数量
			 */
			// 获得该场所mac地址缓存
			RSetCache<String> macs = RedissonHelper.getClient().getSetCache(REDIS_KEY_CURRENT_POINT_MACS_PREFIX + ps.getGroupCode());
			
			ps.setCount(macs.size());
		}
		Collections.sort(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer sumupPlaceNum() {
		String xql = "select count(distinct p.internetServicePlaceCode) from PlaceBasicInfo as p";
		return ((Number) this.dao.findByXql(xql, new HashMap<String, Object>()).get(0)).intValue();
	}

	@Override
	public List<Map<String, Object>> findWifiRecordInRealTimeByMac(String mac) {
		List<Map<String, Object>> result = new ArrayList<>();

		RMapCache<String, String> map = RedissonHelper.getClient().getMapCache(REDIS_KEY_CURRENT_MAC_WIFITRACK);
		String wifitrackStr = map.get(mac);
		if (StringUtils.isNoneBlank(wifitrackStr)) {
			JSONObject jsonObj = JSONObject.fromObject(wifitrackStr);
			WifiData wifitrack = (WifiData) JSONObject.toBean(jsonObj, WifiData.class);
			if (null != wifitrack) {
				HashMap<String, Object> record = new HashMap<>();
				record.put("mac", wifitrack.getMac());
				record.put("entertime", wifitrack.getEnterTime());
				record.put("leavetime", wifitrack.getLeaveTime());
				record.put("placecode", wifitrack.getPlaceId());
				result.add(record);
			}
		}

		return result;
	}

	@Override
	public List<Map<String, Object>> findWifiRecordInRealTimeByPlace(String placeCode) {
		List<Map<String, Object>> result = new ArrayList<>();

		// 获得该场所mac地址缓存
		RSetCache<String> macs = RedissonHelper.getClient()
				.getSetCache(REDIS_KEY_CURRENT_POINT_MACS_PREFIX + placeCode);
		// 获得所有wifi轨迹缓存
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(REDIS_KEY_CURRENT_MAC_WIFITRACK);

		for (String mac : macs.readAll()) {
			String wifitrackStr = maptrack.get(mac);

			if (StringUtils.isBlank(wifitrackStr)) {
				continue;
			}
			JSONObject jsonObj = JSONObject.fromObject(wifitrackStr);
			WifiData wifitrack = (WifiData) JSONObject.toBean(jsonObj, WifiData.class);
			HashMap<String, Object> record = new HashMap<>();
			record.put("mac", wifitrack.getMac());
			record.put("entertime", wifitrack.getEnterTime());
			record.put("leavetime", wifitrack.getLeaveTime());
			record.put("placecode", wifitrack.getPlaceId());
			result.add(record);
		}

		return result;
	}
	

}
