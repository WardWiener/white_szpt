package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.bean.yanpanListBean;
import com.taiji.pubsec.szpt.zhzats.model.JqVideo;
import com.taiji.pubsec.szpt.zhzats.service.YanpanService;
import com.taiji.pubsec.szpt.zhzats.util.ZhzatsDbResultSetUtil;

@Service
public class YanpanServiceImpl implements YanpanService{
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;
	
	@SuppressWarnings("unchecked")
	@Override					
	public Pager<yanpanListBean> findXsjqByPage(Map<String, Object> data, int pageNo, int pageSize) {
		
		Map<String,Object> hqlMap= new HashMap<String,Object>();
		StringBuilder sql=new StringBuilder(" SELECT distinct jq.id,jq.jqdz,jq.jjsj,jq.bjsj,jq.bjr,jq.bjrdh,jq.bm,jq.ty_cq_bm,jq.ty_cq_mc,jq.jqly,jq.jqgy,jq.ty_jqlx_bm,"
				+ "jq.ty_jqlx_mc,jq.wd,jq.jd,jq.jqmc,jq.fssj,jq.ty_dw_pcs_bm,jq.ty_dw_pcs_mc,jq.jjcd,jq.jjr FROM t_zhzats_jwzh_jq as jq, t_zhzats_jwzh_cj as cj ");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sqlcount=new StringBuilder(" SELECT count( distinct jq.id) FROM t_zhzats_jwzh_jq as jq, t_zhzats_jwzh_cj as cj ");
		StringBuilder hql = new StringBuilder (" WHERE cj.fact_jq_id=jq.id ");
		String hqlCount = "WHERE cj.fact_jq_id=jq.id";
		//研判状态
		if(ParamMapUtil.isNotBlank(data.get("yanpanState"))){
			sql.append(" ,t_zlgl_zl as zl ");
			sqlcount.append(" ,t_zlgl_zl as zl ");
			if("1".equals(data.get("yanpanState"))){
				hql.append(" AND zl.glztid=jq.id ");
				hqlCount += " AND zl.glztid=jq.id ";
			}else if("0".equals(data.get("yanpanState"))){
				hql.append(" AND zl.glztid<>jq.id ");
				hqlCount += " AND zl.glztid<>jq.id ";
			}
		}
		//派出所编码
		if(ParamMapUtil.isNotBlank(data.get("pcsCode"))){
			hql.append(" AND cj.ty_zhdy_bm = :pcsCode ");
			hqlMap.put("pcsCode",data.get("pcsCode"));
			
			hqlCount += " AND cj.ty_zhdy_bm = ? ";
			params.add(data.get("pcsCode"));
		}
		//警情名称和警情概要
		if(ParamMapUtil.isNotBlank(data.get("keyword"))){
			hql.append(" AND (jq.jqmc LIKE '%" + SQLTool.SQLSpecialChTranfer((String) data.get("keyword")) + "%'" );
			hql.append(" OR jq.jqgy LIKE '%" + SQLTool.SQLSpecialChTranfer((String) data.get("keyword")) + "%' ) ");
			
			hqlCount += " AND (jq.jqmc LIKE '%" + SQLTool.SQLSpecialChTranfer((String) data.get("keyword")) + "%' ";
			hqlCount += " OR jq.jqgy LIKE '%" + SQLTool.SQLSpecialChTranfer((String) data.get("keyword")) + "%' ) ";
		}
		//警情类型编码
		if(ParamMapUtil.isNotBlank(data.get("jqlx"))){
			List<String> jqlxLst =  (List<String>) data.get("jqlx");
			if(jqlxLst.size() >0){
				hql.append(" AND ( ");
				hqlCount += " AND ( ";
				int size = jqlxLst.size();
				for(int i = 0; i< size; i++){
					if(i < size - 1){
						hql.append(" jq.ty_jqlx_bm LIKE '"+jqlxLst.get(i)+"%' or ");
						
						hqlCount += " jq.ty_jqlx_bm LIKE '"+jqlxLst.get(i)+"%' or ";
					}else{
						hql.append(" jq.ty_jqlx_bm LIKE '"+jqlxLst.get(i)+"%' ");
						hqlCount += " jq.ty_jqlx_bm LIKE '"+jqlxLst.get(i)+"%' ";
					}
				}
				hql.append(" )");
				hqlCount += " ) ";
			}
		}
		//发生地点
		if(ParamMapUtil.isNotBlank(data.get("addr"))){
			hql.append(" AND jq.jqdz LIKE :addr ");
			SQLTool.SQLAddEscape(hql);
			hqlMap.put("addr", "%" + SQLTool.SQLSpecialChTranfer((String) data.get("addr")) + "%");
			
			hqlCount += " AND jq.jqdz LIKE ?";
			params.add("%" + SQLTool.SQLSpecialChTranfer((String) data.get("addr")) + "%");
		}
		//所属村居编码
		if(ParamMapUtil.isNotBlank(data.get("countryCode"))){
			hql.append(" AND jq.ty_cq_bm = :countryCode ");
			hqlMap.put("countryCode",data.get("countryCode"));
			
			hqlCount += " AND jq.ty_cq_bm = ?";
			params.add(data.get("countryCode"));
		}
		//发生时间--开始时间
		if(ParamMapUtil.isNotBlank(data.get("occurTimeStart"))){
			hql.append(" AND jq.fssj >= :startTime ");
			hqlMap.put("startTime",DateFmtUtil.longToDate((long)data.get("occurTimeStart")));
			
			hqlCount += " AND jq.fssj >= ?";
			params.add(DateFmtUtil.longToDate((long)data.get("occurTimeStart")));
		}
		//发生时间--结束时间
		if(ParamMapUtil.isNotBlank(data.get("occurTimeEnd"))){
			hql.append(" AND jq.fssj < :endTime ");
			hqlMap.put("endTime",DateFmtUtil.longToDate((long)data.get("occurTimeEnd")));
			
			hqlCount += " AND jq.fssj < ?";
			params.add(DateFmtUtil.longToDate((long)data.get("occurTimeEnd")));
		}
		hql.append(" ORDER BY jq.fssj DESC ");
	    int count = sqlDao.getJdbcTemplate().queryForObject(sqlcount.append(hqlCount).toString(), params.toArray(),Integer.class);
		Pager<Object[]> pagerResult = sqlDao.findByPage(sql.append(hql).toString(), hqlMap, null, pageNo*pageSize, pageSize);
	    pagerResult.setTotalNum(count);
	    return ZhzatsDbResultSetUtil.transferFactJqPagerFromResultSet(pagerResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JqVideo> queryJqVideo(String jqCode) {
		
		Map<String,Object> hqlMap= new HashMap<String,Object>();
		
		StringBuilder hql=new StringBuilder("SELECT id, jqbm, wjid, wjmc, wjlx, bfdz, yldz, lzrjh, lzrxm, lzrdw, kssj,"
				+ "jssj, lzsc, bcsc, wjdx, zt, ly, gxsj FROM t_zhzats_jwzh_jqsp WHERE zt='0' ");
		//警情编码
		if(ParamMapUtil.isNotBlank(jqCode)){
			hql.append(" AND jqbm = :jqCode ");
			hqlMap.put("jqCode", jqCode);
		}
		hql.append(" ORDER BY gxsj DESC ");
		List<Object[]> list = sqlDao.find(hql.toString(), hqlMap);
		return ZhzatsDbResultSetUtil.transferJqVideoListFromResultSet(list);
	}

}			
