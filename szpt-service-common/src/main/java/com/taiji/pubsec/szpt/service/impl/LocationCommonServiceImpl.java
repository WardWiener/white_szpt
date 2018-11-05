package com.taiji.pubsec.szpt.service.impl;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.bean.LocationCount;
import com.taiji.pubsec.szpt.bean.TrajectoryPos;
import com.taiji.pubsec.szpt.common.model.Location;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.OrderCell.ORDERCELL_TYPE;
import com.taiji.pubsec.szpt.common.model.Trajectory;
import com.taiji.pubsec.szpt.service.LocationCommonService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;


@Service
public class LocationCommonServiceImpl implements LocationCommonService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LocationCommonServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Resource
	private SqlDao sqlDao ;

	@SuppressWarnings("unchecked")
	@Override
	public void createLocation(Location location) {
		this.dao.save(location);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateLocation(Location location) {
		this.dao.update(location);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Location findLocationByIdentifier(String identifier) {
		String xql = "select id, dwsbbs, wd, jd, zhdybm, zhdymc, ryjh, xm, zt, lx, gxsj from t_zhzats_jwzh_wzxx  where dwsbbs = :identifier order by gxsj desc";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("identifier", identifier);
		List<Object[]> list = sqlDao.find(xql, xqlMap);
		if(!list.isEmpty()){
			return transferLocationFromResultSet(list.get(0));
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTrajectory(Trajectory trajectory) {
		this.dao.save(trajectory);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajectory> findTrajectoriesByIdentifier(String identifier,
			Date start, Date end) {
		StringBuilder xql = new StringBuilder();
		xql.append("select id, dwsbbs, wd, jd, lx, zhdybm, gxsj from t_zhzats_jwzh_gjxx where dwsbbs = :identifier ");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0) ;
		xqlMap.put("identifier", identifier);
		if (start != null){
			xql.append(" and gxsj >= :start");
			xqlMap.put("start", start);
		}
		if (end != null){
			xql.append(" and gxsj <= :end");
			xqlMap.put("end", end);
		}
		xql.append(" order by gxsj");
		List<Object[]> list = sqlDao.find(xql.toString(), xqlMap);
		return transferTrajectoryListFromResultSet(list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Location> findLatestLocations(Date startTime, int minute) {
		Date nowdate = startTime;
		Calendar c = Calendar.getInstance();
		c.setTime(nowdate);
		c.add(Calendar.MINUTE, -minute);
		Date lastdate = c.getTime();
		LOGGER.debug("now time is {}, last time is {}", nowdate, lastdate);
		String xql = "select id, dwsbbs, wd, jd, zhdybm, zhdymc, ryjh, xm, zt, lx, gxsj from t_zhzats_jwzh_wzxx as l where gxsj >= :lastdate and gxsj < :nowdate";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("lastdate", lastdate);
		xqlMap.put("nowdate", nowdate);
		List<Object[]> list = sqlDao.find(xql, xqlMap);
		return transferLocationListFromResultSet(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Location findLocationByTypeAndIdentifier(String type, String identifier) {
		String xql = "select id, dwsbbs, wd, jd, zhdybm, zhdymc, ryjh, xm, zt, lx, gxsj from t_zhzats_jwzh_wzxx where lx = :type and dwsbbs = :identifier order by gxsj desc";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("type", type);
		xqlMap.put("identifier", identifier);
		List<Object[]> list = sqlDao.find(xql, xqlMap);
		if(!list.isEmpty()){
			return transferLocationFromResultSet(list.get(0));
		}else{
			return null;	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrajectoryPos> findAllLocations(Date startDay,Date endDay, String[] pcsCodes) {	
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(" SELECT jd, wd FROM t_zhzats_jwzh_gjxx WHERE 1=1 ");
		if(null != startDay){
			hql.append(" AND gxsj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if(null != endDay){
			hql.append(" AND gxsj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes) ;
			hql.append("AND zhdybm in (:key)");
			hqlMap.put("key", key);
		}
		List<Object[]> list = sqlDao.find(hql.toString(), hqlMap);
		List<TrajectoryPos> trajectories = new ArrayList<TrajectoryPos>() ;
		for(Object[] obj : list) {
			if(obj[0] != null && obj[1] != null){
				trajectories.add(new TrajectoryPos(Double.parseDouble(Array.get(obj,0).toString()), Double.parseDouble(Array.get(obj,1).toString())));
			}
		}

		return trajectories ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationCount> findAllLocationsByCount(Date startDay, Date endDay, String[] pcsCodes, String type) {
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(" SELECT sj.zhdymc, sj.zhdybm, sj.jd, sj.wd FROM t_zhzats_jwzh_wzxx as sj WHERE 1=1 ");
		
		if(ParamMapUtil.isNotBlank(type) && type.equals(ORDERCELL_TYPE.TYPE_ZHUGE.getValue())){
			hql.append("AND sj.zhdybm in( SELECT zh.bm FROM t_ty_jwzh_zhdy zh WHERE zh.lx = :type )");
			hqlMap.put("type", type);
		} else{
			if (null != pcsCodes && pcsCodes.length > 0) {
				List<String> key = Arrays.asList(pcsCodes) ;
				hql.append(" AND sj.zhdybm in (:key)");
				hqlMap.put("key", key);
			}
		}
		if(null != startDay){
			hql.append(" AND sj.gxsj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if(null != endDay){
			hql.append(" AND sj.gxsj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		List<Object[]> locResult = sqlDao.find(hql.toString(), hqlMap);
		List<LocationCount> list = new ArrayList<LocationCount>() ;
		for(Object[] rs : locResult) {
			LocationCount lc = new LocationCount();
			if(rs[0] != null){
				lc.setName(rs[0].toString());
			}
			if(rs[1] != null){
				lc.setCode(rs[1].toString());
			}
			if(rs[2] != null){
				lc.setLongitude(Double.valueOf(rs[2].toString()));
			}
			if(rs[3] != null){
				lc.setLatitude(Double.valueOf(rs[3].toString()));
			}
			list.add(lc);
		}
		return list ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> locationList(Date startDay, Date endDay, String[] pcsCode) {
		
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		
		StringBuilder hql = new StringBuilder(" SELECT id, dwsbbs, wd, jd, zhdybm, zhdymc, ryjh, xm, zt, lx, gxsj FROM t_zhzats_jwzh_wzxx WHERE 1=1 ");
		if(null != startDay){
			hql.append(" AND gxsj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if(null != endDay){
			hql.append(" AND gxsj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCode && pcsCode.length > 0) {
			List<String> key = Arrays.asList(pcsCode) ;
			hql.append(" AND zhdybm in (:key)");
			hqlMap.put("key", key);
		}
		List<Object[]> locResult = sqlDao.find(hql.toString(), hqlMap);
		return transferLocationListFromResultSet(locResult);
	}
	
	/**
	 * 查询t_zhzats_jwzh_wzxx行结果转换成Location对象
	 * @param rs 行结果集，元素按顺序对应字段id, dwsbbs, wd, jd, zhdybm, zhdymc, ryjh, xm, zt, lx, gxsj
	 * @return Location对象
	 */
	private Location transferLocationFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Location loc = new Location();
		loc.setId(rs[0].toString());
		if(rs[1] != null) {
			loc.setIdentifier(rs[1].toString());
		}
		if(rs[2] != null) {
			loc.setLatitude(Double.valueOf(rs[2].toString()));
		}
		if(rs[3] != null) {
			loc.setLongitude(Double.valueOf(rs[3].toString()));
		}
		if(rs[4] != null) {
			loc.setOrderCellCode(rs[4].toString());
		}
		if(rs[5] != null) {
			loc.setOrderCellName(rs[5].toString());
		}
		if(rs[6] != null) {
			loc.setPersonJh(rs[6].toString());
		}
		if(rs[7] != null) {
			loc.setPersonName(rs[7].toString());
		}
		if(rs[8] != null) {
			loc.setState(rs[8].toString());
		}
		if(rs[9] != null) {
			loc.setType(rs[9].toString());
		}
		try {
			if(rs[10] != null) {
				loc.setUpdateTime(sdf.parse(rs[10].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("位置信息t_zhzats_jwzh_wzxx更新时间字段gxsj转换错误", e);
		}
		return loc;
	}
	
	private List<Location> transferLocationListFromResultSet(List<Object[]> rsList) {
		List<Location> locs = new ArrayList<Location>();
		for(Object[] rs : rsList) {
			locs.add(transferLocationFromResultSet(rs));
		}
		return locs;
	}
	
	/**
	 * 查询t_zhzats_jwzh_gjxx行结果转换成Trajectory对象
	 * @param rs 行结果集，元素按顺序对应字段id, dwsbbs, wd, jd, lx, gxsj
	 * @return Location对象
	 */
	private Trajectory transferTrajectoryFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Trajectory loc = new Trajectory();
		loc.setId(rs[0].toString());
		if(rs[1] != null) {
			loc.setIdentifier(rs[1].toString());
		}
		if(rs[2] != null) {
			loc.setLatitude(Double.valueOf(rs[2].toString()));
		}
		if(rs[3] != null) {
			loc.setLongitude(Double.valueOf(rs[3].toString()));
		}
		if(rs[4] != null) {
			loc.setType(rs[4].toString());
		}
		if(rs[5] != null) {
			loc.setOrderCellCode(rs[5].toString());
		}
		try {
			if(rs[6] != null) {
				loc.setUpdateTime(sdf.parse(rs[6].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("位置信息t_zhzats_jwzh_gjxx更新时间字段gxsj转换错误", e);
		}
		return loc;
	}
	
	private List<Trajectory> transferTrajectoryListFromResultSet(List<Object[]> rsList) {
		List<Trajectory> locs = new ArrayList<Trajectory>();
		for(Object[] rs : rsList) {
			locs.add(transferTrajectoryFromResultSet(rs));
		}
		return locs;
	}


}
