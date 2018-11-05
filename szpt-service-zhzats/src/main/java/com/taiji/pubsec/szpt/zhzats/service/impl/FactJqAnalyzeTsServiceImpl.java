package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.math.BigInteger;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.bean.AlarmPos;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.model.FactCj;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.service.FactJqAnalyzeTsService;

@Service
public class FactJqAnalyzeTsServiceImpl implements FactJqAnalyzeTsService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao ;
	
	@Resource
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmInfo> findAlarmInfosByPcsCodes(Date startDay, Date endDay, String[] pcsCodes, String[] jqlxCodes) {
		
		Map<String, Object> sqlMap = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder(" SELECT ty_dw_pcs_mc,COUNT(*) FROM t_zhzats_jwzh_jq WHERE 1=1 ");
		if (null != startDay) {
			sql.append(" AND fssj >= :startDay ");
			sqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			sql.append(" AND fssj < :endDay ");
			sqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes) ;
			sql.append("AND ty_dw_pcs_bm in( :key )");
			sqlMap.put("key", key);
		}
		//TODO 警情类型 条件 是否合适？
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			sql.append("AND (");
			int i = 0;
			for (Object lx : jqlxCodes) {
				sql.append(" ty_jqlx_bm like :jqlxCode" + i);
				SQLTool.SQLAddEscape(sql);
				sqlMap.put("jqlxCode" + i, SQLTool.SQLSpecialChTranfer(lx.toString()) + "%");
				i++;
				if (i < (jqlxCodes.length)) {
					sql.append(" OR ");
				}
			}
			sql.append(")");
		}
		sql.append(" GROUP BY ty_dw_pcs_bm");
		List<Object[]> object = sqlDao.find(sql.toString(), sqlMap);

		List<AlarmInfo> list = new ArrayList<AlarmInfo>() ;
		//查询派出所集合
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		StringBuilder xql = new StringBuilder(" SELECT mc FROM t_ty_jwzh_zhdy WHERE 1=1 ");
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes) ;
			xql.append("AND bm in( :code )");
			xqlMap.put("code", key);
		}
		List<Object[]> temp = this.sqlDao.find(xql.toString(), xqlMap);
		if(null != temp && temp.size() > 0){
			for(int i=0;i<temp.size();i++){
				Object b=temp.get(i);
				list.add(new AlarmInfo(b.toString(),0));
			}
		}
		
		if (null != object && object.size() > 0) {
			for(int j=0;j<object.size();j++){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getName().equals(object.get(j)[0].toString())){
						list.get(i).setCount(Integer.parseInt(object.get(j)[1].toString()));
					}
				}
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmInfo> findAlarmInfosByJqlxCodesByPcsCodes(Date startDay,
			Date endDay, String[] jqlxCodes, String[] pcsCodes,String method) {
	
		// 去重(去掉子项)
		jqlxCodes = ParamMapUtil.removeChildCodeForQueryLike(jqlxCodes);
				
		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(" SELECT COUNT(*) FROM t_zhzats_jwzh_jq WHERE 1=1 ");
		if (null != startDay) {
			hql.append(" AND fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		
		if (null != pcsCodes && pcsCodes.length > 0) {
			hql.append(" and ty_dw_pcs_bm in ("+inParamFormatForQuery(pcsCodes)+") ");
		}
		
		List<AlarmInfo> list = new ArrayList<AlarmInfo>() ;
		
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			
			for (String lx : jqlxCodes) {
				StringBuilder mql=new StringBuilder(hql.toString());
				mql.append(" and ty_jqlx_bm like :jqlxCode ");
				SQLTool.SQLAddEscape(mql);
				hqlMap.put("jqlxCode", SQLTool.SQLSpecialChTranfer(lx) + "%");
				Object alarmCountResult = sqlDao.findUnique(mql.toString(), hqlMap);
				int alarmCount = ((BigInteger)alarmCountResult).intValue();
				
				StringBuilder xql=new StringBuilder(" select * from t_ty_jwzh_jqlx where bm = :code ");
				Map<String, Object> xqlMap = new HashMap<String, Object>();
				xqlMap.put("code", lx.toString() );
				List<Object[]> alarmTypeResult = sqlDao.find(xql.toString(), xqlMap);
				String typeName = alarmTypeResult.get(0)[3].toString();
				list.add(new AlarmInfo(typeName, lx, alarmCount));
				
				//TODO 为什么判断‘welcome’
//				if (null != alarmCountResult && alarmCountResult.size() > 0) {
//					if(alarmTypeResult != null && alarmTypeResult.size() > 0){
//						for(Object[] obj : alarmCountResult) {
//							if(method.equals("welcome")){
//								if(obj.length > 0){
//									list.add(new AlarmInfo(alarmTypeResult.get(0)[3].toString(), lx, ((Integer) obj[0]).intValue()));
//								}
//							}else{
//								list.add(new AlarmInfo(alarmTypeResult.get(0)[3].toString(),lx, ((Integer) obj[0]).intValue()));
//							}
//						}
//					}
//				}
			}
		}
	
		return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmPos> findAlarmPos(Date startDay, Date endDay, String[] pcsCodes) {
		
		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(" SELECT jd, wd FROM t_zhzats_jwzh_jq WHERE 1=1 ");
		if (null != startDay) {
			hql.append(" AND fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes) ;
			hql.append("AND ty_dw_pcs_bm in (:key)");
			hqlMap.put("key", key);
		}

		List<Object[]> posResult = sqlDao.find(hql.toString(), hqlMap);
		List<AlarmPos> list = new ArrayList<AlarmPos>() ;
		for(Object[] obj : posResult) {
			if(obj[0] != null && obj[1] != null){
				list.add(new AlarmPos(Double.parseDouble(Array.get(obj,0).toString()) , Double.parseDouble(Array.get(obj,1).toString())));
			}
		}

		return list ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer findAlarmInfosByJqlxCodesByPcsCodesByTotal(Date startDay,
			Date endDay, String[] jqlxCodes, String[] pcsCodes) {
		// 去重(去掉子项)
		jqlxCodes = ParamMapUtil.removeChildCodeForQueryLike(jqlxCodes);

		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(" SELECT COUNT(*) FROM t_zhzats_jwzh_jq as jq, t_ty_jwzh_jqlx as lx WHERE lx.bm=jq.ty_jqlx_bm ");
		if (null != startDay) {
			hql.append(" AND jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			hql.append("AND jq.ty_dw_pcs_bm in (:key)");
			hqlMap.put("key", key);
		}
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			hql.append("AND (");
			int i = 0;
			for (Object lx : jqlxCodes) {
				hql.append(" lx.bm like :jqlxCode" + i);
				SQLTool.SQLAddEscape(hql);
				hqlMap.put("jqlxCode" + i, SQLTool.SQLSpecialChTranfer(lx.toString()) + "%");
				i++;
				if (i < (jqlxCodes.length)) {
					hql.append(" OR ");
				}
			}
			hql.append(")");
		}

		Object countResult = sqlDao.findUnique(hql.toString(), hqlMap);

		return ((BigInteger)countResult).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmInfo> findAlarmInfosByPcsCodesBuShiDuan(Date startDay, Date endDay, String[] pcsCodes) {
		
		Map<String, Object> hqlMap = new HashMap<String, Object>() ;
	
		StringBuilder hql = new StringBuilder("select jq.fssj, jq.ty_dw_pcs_mc from t_zhzats_jwzh_jq as jq where 1=1 ");

		if(null != startDay){
			hql.append(" and jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);  
		}
		if(null != endDay){
			hql.append(" and jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		} 
		
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			hql.append("AND jq.ty_dw_pcs_bm in (:key)");
			hqlMap.put("key", key);
		}
		
		List<Object[]> result = sqlDao.find(hql.toString(), hqlMap);
			
		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
		
		Map< String , AlarmInfo > map = new HashMap<>();
		
		SimpleDateFormat df= new SimpleDateFormat("HH");
		for(Object[] obj : result){
			for(int i = 0 ; i < Constant.SHI_DUAN.values().length ; i++){
					
				String key = Constant.SHI_DUAN.values()[i].getKey();
				if( null == map.get(key +"")){
					map.put( key +"", new AlarmInfo(key, 0));
				}
				int occurrenceTime = Integer.parseInt(df.format((Date)obj[0]));
		
				if( occurrenceTime >= Integer.parseInt(key.substring(0,key.indexOf("~"))) && occurrenceTime < Integer.parseInt(key.substring(key.indexOf("~")+1))){
					map.get(key + "").setCount(map.get(key + "").getCount()+1);
				}
			}
		}
			
		if(null != map && map.size() > 0){
			for(Entry<String,AlarmInfo> entry : map.entrySet()){
				list.add(entry.getValue());
			}
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmPos> findAlarmPos(Date startDay, Date endDay,
			String[] jqlxCodes, String[] pcsCodes) {

		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				" SELECT ty_dw_pcs_mc, ty_dw_pcs_bm, jd, wd, ty_jqlx_bm, jqmc FROM t_zhzats_jwzh_jq WHERE 1=1 ");
		if (null != startDay) {
			hql.append(" AND fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			hql.append("AND ty_dw_pcs_bm in (:pcsCode)");
			hqlMap.put("pcsCode", key);
		}
		Set<AlarmPos> set = new HashSet<AlarmPos>();
		
		//TODO 为什么警情类型编码不去除子项？=还是like
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			List<String> jqlxList = Arrays.asList(jqlxCodes);
			for (String key : jqlxList) {
				StringBuilder xql =  new StringBuilder(hql.toString());	
				xql.append(" and ty_jqlx_bm like :jqlxCode ");
				SQLTool.SQLAddEscape(xql);
				hqlMap.put("jqlxCode", SQLTool.SQLSpecialChTranfer(key) + "%");
			
				List<Object[]> object = sqlDao.find(xql.toString(), hqlMap);
	
				for (Object[] obj : object) {
					if (obj[0] != null && obj[1] != null && obj[2] != null && obj[3] != null && obj[4] != null && obj[5] != null) {
						set.add(new AlarmPos(Array.get(obj, 0).toString(), 
								Array.get(obj, 1).toString(),
								Double.parseDouble(Array.get(obj, 2).toString()),
								Double.parseDouble(Array.get(obj, 3).toString()),
								Array.get(obj, 4).toString(),
								Array.get(obj, 5).toString()));
					}
				}
			}
		}
		List<AlarmPos> list = new ArrayList<AlarmPos>(set);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmInfo> findAlarmInfosByPcsCodesBuShiDuanByJqlx(
			Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes) {
		
		// 去重(去掉子项)
		jqlxCodes = ParamMapUtil.removeChildCodeForQueryLike(jqlxCodes);
		
		List<AlarmInfo> infoList = new ArrayList<AlarmInfo>();
		Map<String, Object> hqlMap = new HashMap<String, Object>() ;
		StringBuilder hql = new StringBuilder("select jq.fssj, jq.ty_dw_pcs_mc from t_zhzats_jwzh_jq as jq ");
		
		StringBuilder whereHql = new StringBuilder(" where 1=1 ");
		
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			
			hql.append( " , t_ty_jwzh_jqlx as lx ");
			whereHql.append(" and lx.bm=jq.ty_jqlx_bm ");
			
//			jqlxCodes = ParamMapUtil.removeChildCodeForQueryLike(jqlxCodes);
			whereHql.append("and (");
			int i = 0;
			for (String lx : jqlxCodes) {
				whereHql.append(" lx.bm like :jqlxCode"+i);
				SQLTool.SQLAddEscape(whereHql);
				hqlMap.put("jqlxCode"+i, SQLTool.SQLSpecialChTranfer(lx) + "%");
				i++;
				if (i < (jqlxCodes.length)) {
					whereHql.append(" OR ");
				}
			}
			whereHql.append(")");
		}
		//添加条件
		if(null != startDay){
			whereHql.append(" and jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);  
		}
		if(null != endDay){
			whereHql.append(" and jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		} 
		
		if (null != pcsCodes && pcsCodes.length > 0) {
			whereHql.append(" and jq.ty_dw_pcs_bm in ("+inParamFormatForQuery(pcsCodes)+") ");
		}
		hql=hql.append(whereHql);
		List<Object[]> list = sqlDao.find(hql.toString(), hqlMap);
			
		Map< String , AlarmInfo > alarmInfoMap = new HashMap<>();
		if(null != list && list.size() > 0){
			
			SimpleDateFormat df=new SimpleDateFormat("HH");
			for(Object[] obj : list){
				for(int i = 0 ; i < Constant.SHI_DUAN.values().length ; i++){
					
					String key = Constant.SHI_DUAN.values()[i].getKey();
					String code = obj[1].toString() ;
					if( null == alarmInfoMap.get(code + key +"")){
						alarmInfoMap.put( code + key +"", new AlarmInfo(key, code, 0) );
					}
					int occurrenceTime = Integer.parseInt(df.format((Date)obj[0]));
		
					if( occurrenceTime >= Integer.parseInt(key.substring(0,key.indexOf("~"))) && occurrenceTime < Integer.parseInt(key.substring(key.indexOf("~")+1))){
						alarmInfoMap.get(code + key + "").setCount(alarmInfoMap.get(code + key + "").getCount()+1);
					}
				}
			}
			
			if(null != alarmInfoMap && alarmInfoMap.size() > 0){
	
				for(Entry<String,AlarmInfo> entry : alarmInfoMap.entrySet()){
					infoList.add(entry.getValue());
				}
			}
		}
		
		Map<String, Object> xqlMap=new HashMap<String,Object>();
		StringBuilder xql=new StringBuilder(" select zh from "+OrderCell.class.getName()+" as zh where 1=1 ");
		xql.append(" and zh.code in (:code) ");
		List<String> temp = Arrays.asList(pcsCodes);
		xqlMap.put("code",temp);
		List<OrderCell> object = this.dao.findAllByParams(OrderCell.class, xql.toString(), xqlMap);
		
		List<AlarmInfo> info = new ArrayList<AlarmInfo>();
		for(int i = 0 ; i < Constant.SHI_DUAN.values().length ; i++){
			for(OrderCell obj : object) {
				if(obj.getName() != null){
					info.add(new AlarmInfo( Constant.SHI_DUAN.values()[i].getKey(), obj.getName(), 0));
				}
			}
		}
		if(infoList!=null && infoList.size()>0){
			for (AlarmInfo Info1 : info) {
				for (AlarmInfo Info2 : infoList) {
					if(Info1.getName().equals(Info2.getName()) && Info1.getNameAdd1().equals(Info2.getNameAdd1())){
						Info1.setCount(Info2.getCount());
					}
				}
			}
		}
		if(info != null && info.size()>0){
			info=this.listSort(info);
		}
		return info;
	}
	
	//按时段进行排序
	private List<AlarmInfo> listSort(List<AlarmInfo> infoList) {
		for(int i=0; i<infoList.size(); i++){
			for(int j = infoList.size()-1; j>i; j--){
			    int s = Integer.parseInt(infoList.get(i).getName().substring(0,infoList.get(i).getName().indexOf("~")));
				int m = Integer.parseInt(infoList.get(j).getName().substring(0,infoList.get(j).getName().indexOf("~")));
				if(s > m){
					AlarmInfo r = infoList.get(j);
					infoList.set(j, infoList.get(i));
					infoList.set(i, r);
				}
			}
		}
		return infoList;
	}


	/**
	 * 组装查询语句中IN参数。
	 * 
	 * @param Object[] formatInParmArr  IN参数数组
	 * @param 
	 * @return 返回数据形式：'a1','a2','a3'
	*/
	private String inParamFormatForQuery(Object[] formatInParmArr) {
		String formatInParm = "' '";
		if(null != formatInParmArr && formatInParmArr.length > 0) {
			for(int i = 0; i < formatInParmArr.length; i++) {
				if(null != formatInParmArr[i] && !"".equals(formatInParmArr[i].toString().trim())) {
					if(i == 0) {
						formatInParm = "";
						formatInParm += "'" + formatInParmArr[i].toString() + "'";
					}else {
						formatInParm += ",'" + formatInParmArr[i].toString() + "'";
					}
				}
			}
		}
		return formatInParm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmPos> findGriddJq(Date startDay, Date endDay,String[] jqlxCodes, String type) {
		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				" SELECT cj.ty_zhdy_mc,cj.ty_zhdy_bm,jq.jd,jq.wd FROM t_zhzats_jwzh_jq as jq, t_zhzats_jwzh_cj as cj WHERE cj.fact_jq_id=jq.id ");
		if (null != startDay) {
			hql.append(" AND jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			List<String> key = Arrays.asList(jqlxCodes);
			hql.append("AND jq.ty_jqlx_bm in (:key)");
			hqlMap.put("key", key);
		}
		if (ParamMapUtil.isNotBlank(type)) {
			hql.append("AND cj.ty_zhdy_bm in( SELECT zh.bm FROM t_ty_jwzh_zhdy zh WHERE zh.lx = :type )");
			hqlMap.put("type", type);
		}

		List<Object[]> result = sqlDao.find(hql.toString(), hqlMap);

		List<AlarmPos> list = new ArrayList<AlarmPos>();


		for (Object[] obj : result) {
			if (obj[0] != null && obj[1] != null && obj[2] != null) {
				list.add(new AlarmPos(Array.get(obj, 0).toString(),Array.get(obj, 1).toString(), Double
							.parseDouble(Array.get(obj, 2).toString()), Double
							.parseDouble(Array.get(obj, 3).toString())));
			}
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmInfo> findAlarmInfosByCommunityCodesBuShiDuanByJqlx(Date startDay, Date endDay, String[] jqlxCodes, String[] pcsCodes) {
		// 去重(去掉子项)
		jqlxCodes = ParamMapUtil.removeChildCodeForQueryLike(jqlxCodes);
		List<AlarmInfo> alarmInfo = new ArrayList<AlarmInfo>();
		Map<String, Object> hqlMap = new HashMap<String, Object>() ;
		StringBuilder hql = new StringBuilder("select jq.fssj, jq.ty_cq_mc  from t_zhzats_jwzh_jq as jq where 1=1 ");
		if (null != startDay) {
			hql.append(" AND jq.fssj >= :startDay ");
			hqlMap.put("startDay", startDay);
		}
		if (null != endDay) {
			hql.append(" AND jq.fssj < :endDay ");
			hqlMap.put("endDay", endDay);
		}
		if (null != pcsCodes && pcsCodes.length > 0) {
			List<String> key = Arrays.asList(pcsCodes);
			hql.append("AND jq.ty_dw_pcs_bm in (:pcsCode)");
			hqlMap.put("pcsCode", key);
		}
		
		if (null != jqlxCodes && jqlxCodes.length > 0) {
			
			for (String lx : jqlxCodes) {
				StringBuilder mql=new StringBuilder(hql.toString());
				mql.append(" and jq.ty_jqlx_bm like :jqlxCode ");
				SQLTool.SQLAddEscape(mql);
				hqlMap.put("jqlxCode", SQLTool.SQLSpecialChTranfer(lx) + "%");
				List<Object[]> result = sqlDao.find(mql.toString(), hqlMap);
				Map< String , AlarmInfo > alarmInfoMap = new HashMap<>();
				
				SimpleDateFormat df=new SimpleDateFormat("HH");
				for(Object[] obj : result){
					for(int i = 0 ; i < Constant.SHI_DUAN.values().length ; i++){
						String key = Constant.SHI_DUAN.values()[i].getKey();
						
						String code = obj[1] == null ? "" : obj[1].toString() ;
						if( null == alarmInfoMap.get(code + key +"")){
							alarmInfoMap.put( code + key +"", new AlarmInfo(key, code, 0) );
						}
						int occurrenceTime = Integer.parseInt(df.format((Date)obj[0]));
						if( occurrenceTime >= Integer.parseInt(key.substring(0,key.indexOf("~"))) && occurrenceTime < Integer.parseInt(key.substring(key.indexOf("~")+1))){
							alarmInfoMap.get(code + key + "").setCount(alarmInfoMap.get(code + key + "").getCount()+1);
						}
					}
				}
					
				if(null != alarmInfoMap && alarmInfoMap.size() > 0){
					for(Entry<String,AlarmInfo> entry : alarmInfoMap.entrySet()){
						alarmInfo.add(entry.getValue());
					}
				}
			}
		}
		
		Map<String, Object> xqlMap=new HashMap<String,Object>();
		StringBuilder xql=new StringBuilder(" select cj from "+Unit.class.getName()+" as dw, "+Community.class.getName()+" as cj where cj.unitId=dw.id ");
		xql.append(" and dw.code in (:code) ");
		List<String> temp = Arrays.asList(pcsCodes);
		xqlMap.put("code",temp);
		List<Community> object = this.dao.findAllByParams(Community.class, xql.toString(), xqlMap);
		
		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
		for(int i = 0 ; i < Constant.SHI_DUAN.values().length ; i++){
			for(Community obj : object) {
				if(obj.getName() != null){
					list.add(new AlarmInfo( Constant.SHI_DUAN.values()[i].getKey(), obj.getName(), 0));
				}
			}
		}
		if(alarmInfo!=null && alarmInfo.size()>0){
			for (AlarmInfo Info1 : list) {
				for (AlarmInfo Info2 : alarmInfo) {
					if(Info1.getName().equals(Info2.getName()) && Info1.getNameAdd1().equals(Info2.getNameAdd1())){
						Info1.setCount(Info2.getCount());
					}
				}
			}
		}
		if(list != null && list.size()>0){
			list = this.listSort(list);
		}
		return list;
	}
}
