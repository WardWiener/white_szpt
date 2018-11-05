package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.common.model.OrderCell.ORDERCELL_TYPE;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.bean.DailyReportPersonInfo;
import com.taiji.pubsec.szpt.zhzats.model.DailyReportPerson;
import com.taiji.pubsec.szpt.zhzats.service.DailyReportPersonTsService;

@Service
public class DailyReportPersonTsServiceImpl implements DailyReportPersonTsService{

	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DailyReportPersonInfo> findDailyReportPerson(Date startDay, Date endDay, String[] pcsCodes,String type) {
		
		Map<String, Object> sqlMap = new HashMap<String, Object>();                          
		
		StringBuilder sql = new StringBuilder("SELECT ty_zhdy_mc, count(*) FROM t_zhzats_jwzh_bbjl WHERE 1=1 ");
		if (null != startDay) {
			sql.append(" and bbrq >= :startDay ");
			sqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			sql.append(" and bbrq < :endDay ");
			sqlMap.put("endDay", endDay);
		}
		//TODO 为何不统一用type逻辑？指挥字典数据对单位类指挥单元没有区分派出所、大队及其他，所以这里对派出所单独处理
		if(ParamMapUtil.isNotBlank(type) && type.equals(ORDERCELL_TYPE.TYPE_ZHUGE.getValue())){
				sql.append("AND ty_zhdy_bm in( SELECT bm FROM t_ty_jwzh_zhdy WHERE lx = :type )");
				sqlMap.put("type", type);
		} else{
			if (null != pcsCodes && pcsCodes.length > 0) {
				List<String> key = Arrays.asList(pcsCodes) ;
				sql.append("AND ty_zhdy_bm in( :key )");
				sqlMap.put("key", key);
			}
		}
		
		sql.append(" GROUP BY ty_zhdy_bm");
		List<Object[]> object = sqlDao.find(sql.toString(), sqlMap);
		
		List<DailyReportPersonInfo> list = new ArrayList<DailyReportPersonInfo>();
		
		if (null != object && object.size() > 0) {
			for(Object[] obj : object) {
				if(obj[0] != null){
					list.add(new DailyReportPersonInfo(Array.get(obj,0).toString(),Integer.parseInt(Array.get(obj,1).toString())));
				}
			}
		}
		return list ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyReportPerson> dailyReportPersonTsList(Date startDay,Date endDay, String[] pcsCode) {
		
		Map<String, Object> sqlMap = new HashMap<String, Object>();        
		
		StringBuilder sql = new StringBuilder("SELECT * FROM t_zhzats_jwzh_bbjl WHERE 1=1 ");
		if (null != startDay) {
			sql.append(" and bbrq >= :startDay ");
			sqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			sql.append(" and bbrq < :endDay ");
			sqlMap.put("endDay", endDay);
		}
		if (null != pcsCode && pcsCode.length > 0) {
			List<String> key = Arrays.asList(pcsCode) ;
			sql.append("AND ty_zhdy_bm in( :key )");
			sqlMap.put("key", key);
		}
		List<Object[]> sqlResult = sqlDao.find(sql.toString(), sqlMap);
		List<DailyReportPerson> result = new ArrayList<DailyReportPerson>();
		for(Object[] rs : sqlResult) {
			DailyReportPerson drp = new DailyReportPerson();
			drp.setId(rs[0].toString());
			drp.setOrderCellCode(rs[1].toString());
			drp.setOrderCellId(rs[2] == null ? "" :rs[2].toString());
			drp.setOrderCellName(rs[3].toString());
			drp.setPersonJh(rs[4].toString());
			drp.setPersonName(rs[5].toString());
			result.add(drp);
		}
		return result;
	}

}
