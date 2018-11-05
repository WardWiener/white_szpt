package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("placeBasicInfoService")
public class PlaceBasicInfoServiceImpl implements PlaceBasicInfoService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;


	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceBasicInfo> findAll() {
		String xql = "select p from PlaceBasicInfo as p left join p.placeMonitor as pm order by pm.num desc";
		return this.dao.findByXql(xql, new HashMap<String, Object>());
	}

	@Override
	public void updatePlaceBasicInfo(PlaceBasicInfo placeBasicInfo) {
			dao.save(placeBasicInfo);
	}

	@Override
	public PlaceBasicInfo findById(String id) {
		PlaceBasicInfo placeBasicInfo = (PlaceBasicInfo) dao.findById(PlaceBasicInfo.class, id);
		return placeBasicInfo;
	}
	
	@Override
	public PlaceBasicInfo findByCode(String code) {
		String xql = "select p from PlaceBasicInfo as p where p.internetServicePlaceCode=:code" ;
		Map<String, Object> xqlMap = new HashMap<>() ;
		xqlMap.put("code", code) ;
		List<PlaceBasicInfo> list = this.dao.findAllByParams(PlaceBasicInfo.class, xql, xqlMap) ;
		if(list.size()==0){
			return null ;
		}

		return list.get(0);
	}
	/**
	 * 通过条件查找WiFi场所信息集合
	 * name:场所名称
	 * code:场所编码
	 * address:场所地址
	 * isLoca:是否是本区域(默认 经开区,区域编码520114开头)
	 * @return 符合要求的WiFi场所信息集合
	 */
	@Override
	public Pager<PlaceBasicInfo> queryPlaceBasicInfoByCondition(Map<String, Object> conditions) {
		List<PlaceBasicInfo> result = new ArrayList<PlaceBasicInfo>();;
		StringBuilder xql = new StringBuilder("select p from PlaceBasicInfo as p where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(conditions.get("name"))){
			xql.append(" and p.internetServicePlaceName like :name ");
			String name = conditions.get("name").toString() ;
			xqlMap.put("name", "%"+name+"%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("code"))){
			xql.append(" and p.internetServicePlaceCode like :code ");
			String code = conditions.get("code").toString();
			xqlMap.put("code", "%"+code+"%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("address"))){
			xql.append(" and p.detailedAddress like :address ");
			String address = conditions.get("address").toString() ;
			xqlMap.put("address", "%"+address+"%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("isLoca"))){
			if(conditions.get("isLoca").equals(true)){
				xql.append(" and p.internetServicePlaceCode like :isLoca ");
				xqlMap.put("isLoca", "520114%");
			}
		}
		Pager<PlaceBasicInfo> pager = dao.findByPage(PlaceBasicInfo.class, xql.toString(), xqlMap,Integer.valueOf(conditions.get("pageNo").toString()), Integer.valueOf(conditions.get("pageSize").toString()));
		return pager;
	}

}
