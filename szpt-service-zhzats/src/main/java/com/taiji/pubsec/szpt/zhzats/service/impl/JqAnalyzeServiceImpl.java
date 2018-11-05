package com.taiji.pubsec.szpt.zhzats.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.zhzats.model.JqAnalyze;
import com.taiji.pubsec.szpt.zhzats.service.JqAnalyzeService;

@Service
public class JqAnalyzeServiceImpl implements JqAnalyzeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JqAnalyzeServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private SqlDao sqlDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JqAnalyze findJqAnalyzeById(String jqId) {
		Map<String,Object> hqlMap=new HashMap<String,Object>();
		StringBuilder hql=new StringBuilder("select id, zhzats_fact_jq_id, xyrzbsxt, shrxm, shrsfzh, sqhfs, shwptz, sfjccl, tcfx, tcfs,"
				+ "xyrnl, xyrtx, xyrsswptz, xyrzagj, xyrzafs, xyrfx, xyrsg, xyrqttz, xyrxb, xyrfs, xyrxztz, sjc from t_zhzats_jwzh_jqfxyp where 1 = 1 ");
		if(StringUtils.isNotBlank(jqId)) {
			hql.append(" and zhzats_fact_jq_id = :jqId ");
			hqlMap.put("jqId", jqId);
			List<Object[]> list = sqlDao.find(hql.toString(), hqlMap);
			if(!list.isEmpty()){
				return transferJqAnalyzeFromResultSet(list.get(0));
			}else {
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	private JqAnalyze transferJqAnalyzeFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JqAnalyze fxyp = new JqAnalyze();
		fxyp.setId(rs[0].toString());
		if(rs[1] != null) {
			fxyp.setJqId(rs[1].toString());
		}
		if(rs[2] != null) {
			fxyp.setCameras(rs[2].toString());
		}
		if(rs[3] != null) {
			fxyp.setVictimName(rs[3].toString());
		}
		if(rs[4] != null) {
			fxyp.setVictimId(rs[4].toString());
		}
		if(rs[5] != null) {
			fxyp.setHarmedWay(rs[5].toString());
		}
		if(rs[6] != null) {
			fxyp.setMaterialChara(rs[6].toString());
		}
		if(rs[7] != null) {
			fxyp.setIsDriving(rs[7].toString());
		}
		if(rs[8] != null) {
			fxyp.setRunDirect(rs[8].toString());
		}
		if(rs[9] != null) {
			fxyp.setRunWay(rs[9].toString());
		}
		if(rs[10] != null) {
			fxyp.setSuspectAge(rs[10].toString());
		}
		if(rs[11] != null) {
			fxyp.setSuspectBody(rs[11].toString());
		}
		if(rs[12] != null) {
			fxyp.setSuspectCarryItemChara(rs[12].toString());
		}
		if(rs[13] != null) {
			fxyp.setSuspectCrimeTool(rs[13].toString());
		}
		if(rs[14] != null) {
			fxyp.setSuspectCrimeWay(rs[14].toString());
		}
		if(rs[15] != null) {
			fxyp.setSuspectHair(rs[15].toString());
		}
		if(rs[16] != null) {
			fxyp.setSuspectHeight(rs[16].toString());
		}
		if(rs[17] != null) {
			fxyp.setSuspectOtherChara(rs[17].toString());
		}
		if(rs[18] != null) {
			fxyp.setSuspectSex(rs[18].toString());
		}
		if(rs[19] != null) {
			fxyp.setSuspectSkin(rs[19].toString());
		}
		if(rs[20] != null) {
			fxyp.setSuspectClothChara(rs[20].toString());
		}
		try {
			if(rs[21] != null) {
				fxyp.setTimestamp(sdf.parse(rs[21].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情分析研判t_zhzats_jwzh_jqfxyp时间戳字段sjc转换错误", e);
		}
		return fxyp;
	}

}
