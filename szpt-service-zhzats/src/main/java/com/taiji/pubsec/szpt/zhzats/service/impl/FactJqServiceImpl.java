package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.common.model.OrderCell.ORDERCELL_TYPE;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.service.FactJqService;
import com.taiji.pubsec.szpt.zhzats.util.ZhzatsDbResultSetUtil;

@Service
public class FactJqServiceImpl implements FactJqService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FactJqServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;
	
	@Deprecated
	@SuppressWarnings("unchecked")
	@Override
	public List<FactJq> findJqByDateAndType(List<String> jqTypes, Date timeStart, Date timeEnd) {
		StringBuilder xql = new StringBuilder("select * from t_zhzats_jwzh_jq where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(jqTypes!= null && !jqTypes.isEmpty()) {
			xql.append(" and ty_jqlx_bm in (:jqTypes)");
			xqlMap.put("jqTypes", jqTypes);
		}
		if(null != timeStart) {
			xql.append(" and jjsj >= :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(null != timeEnd) {
			xql.append(" and jjsj < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		List<Object[]> alarmResult = sqlDao.find(xql.toString(), xqlMap);
		return ZhzatsDbResultSetUtil.transferFactJqListFromResultSet(alarmResult);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FactJq findFactJqById(String jqId) {
		Map<String,Object> hqlMap=new HashMap<String,Object>();
		StringBuilder hql=new StringBuilder(" select id, jqdz, jjsj, bjsj, bjr, bjrdh, bm, ty_cq_bm, ty_cq_mc, jqly, jqgy, ty_jqlx_bm, ty_jqlx_mc, wd, jd, jqmc, fssj, ty_dw_pcs_bm, ty_dw_pcs_mc, jjcd, jjr from t_zhzats_jwzh_jq where 1 = 1 ");
		if(jqId.length()>0) {
			hql.append(" and id= :jqId ");
			hqlMap.put("jqId", jqId);
		}
		List<Object[]> alarmResult = sqlDao.find(hql.toString(), hqlMap);
		if(!alarmResult.isEmpty()){
			return ZhzatsDbResultSetUtil.transferFactJqFromResultSet(alarmResult.get(0));
		}else {
			return null;
		}
	}
	
	@Override
	public FactJq findFactJqByCode(String code) {
		Map<String,Object> hqlMap=new HashMap<String,Object>();
		StringBuilder hql=new StringBuilder(" select id, jqdz, jjsj, bjsj, bjr, bjrdh, bm, ty_cq_bm, ty_cq_mc, jqly, jqgy, ty_jqlx_bm, ty_jqlx_mc, wd, jd, jqmc, fssj, ty_dw_pcs_bm, ty_dw_pcs_mc, jjcd, jjr from t_zhzats_jwzh_jq where 1 = 1 ");
		if(code.length()>0) {
			hql.append(" and bm= :code ");
			hqlMap.put("code", code);
		}
		List<Object[]> alarmResult = sqlDao.find(hql.toString(), hqlMap);
		if(!alarmResult.isEmpty()){
			return ZhzatsDbResultSetUtil.transferFactJqFromResultSet(alarmResult.get(0));
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FactJq> factJqList(Date startDay, Date endDay,
			String[] jqlxCodes, String[] pcsCodes,String analyseBaseType) {
		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				" SELECT jq.id,jq.jqdz,jq.jjsj,jq.bjsj,jq.bjr,jq.bjrdh,jq.bm,jq.ty_cq_bm,jq.ty_cq_mc,jq.jqly,jq.jqgy,jq.ty_jqlx_bm,"
				+ "jq.ty_jqlx_mc,jq.wd,jq.jd,jq.jqmc,jq.fssj,jq.ty_dw_pcs_bm,jq.ty_dw_pcs_mc,jq.jjcd,jq.jjr FROM t_zhzats_jwzh_jq as jq ");
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			if(analyseBaseType.equals(ORDERCELL_TYPE.TYPE_ZHUGE.getValue())){
				hql.append(", t_zhzats_jwzh_cj as cj WHERE cj.fact_jq_id=jq.id ");
				hql.append("AND cj.ty_zhdy_bm in (:pcsCode)");
			}
			else{
				hql.append(" where 1=1 ");
				hql.append("AND jq.ty_dw_pcs_bm in (:pcsCode)");
			}
			hqlMap.put("pcsCode", key);
		}
		if (null != startDay) {
			hql.append(" AND jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		
//		if (null != jqlxCodes && jqlxCodes.length > 0) {
//			List<String> key = Arrays.asList(jqlxCodes);
//			hql.append("AND jq.ty_jqlx_bm in (:key)");
//			hqlMap.put("key", key);
//		}
		
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			int count = 0;
			for (String key : jqlxCodes) {
				 if(count == 0){
					 hql.append(" AND (ty_jqlx_bm like :jqlxCode"+count);
					 SQLTool.SQLAddEscape(hql);
					hqlMap.put("jqlxCode"+count, SQLTool.SQLSpecialChTranfer(key) + "%");
				 }else{
					 hql.append(" OR ty_jqlx_bm like :jqlxCode"+count);
					 SQLTool.SQLAddEscape(hql);
					hqlMap.put("jqlxCode"+count, SQLTool.SQLSpecialChTranfer(key) + "%");
				 }
				count++;
			}
			hql.append(" )");
		}
		List<Object[]> alarmResult = sqlDao.find(hql.toString(), hqlMap);
		return ZhzatsDbResultSetUtil.transferFactJqListFromResultSet(alarmResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CjFeedback> findCjFeedbackByJqId(String jqId) {
		Map<String,Object> hqlMap=new HashMap<String,Object>();
		StringBuilder hql=new StringBuilder(" select fk.Id, fk.fknr, fk.fact_cj_id, fk.fksj, fk.ty_zhdy_bm, fk.ty_zhdy_id, fk.ty_zhdy_mc, fk.fkrxm, fk.fklx from t_zhzats_jwzh_cj as cj, t_zhzats_jwzh_fk as fk where fk.fact_cj_id=cj.id ");
		if(ParamMapUtil.isNotBlank(jqId)) {
			hql.append(" and cj.fact_jq_id= :jqId ");
			hqlMap.put("jqId", jqId);
			hql.append(" ORDER BY fk.fksj DESC ");
			List<Object[]> fkResult = sqlDao.find(hql.toString(), hqlMap);
			return ZhzatsDbResultSetUtil.transferCjFeedbackListFromResultSet(fkResult);
		}else{
			return null;
		}
	}


}
