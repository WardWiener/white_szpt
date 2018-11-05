package com.taiji.pubsec.szpt.score.util.hrp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.score.util.hrp.service.HrpScoreUtilService;

@Service
public class HrpScoreUtilServiceImpl implements HrpScoreUtilService{
	
	@Autowired
	private SqlDao sqlDao ;

	@Override
	public String findHrpIndentyById(String hrpId) {
		
		String sql = "SELECT h.sfzh AS identy "
				+ " FROM t_gwry_ryxx as h "
				+ " WHERE h.id=:id" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("id", hrpId);
		
		Object identy = (Object)this.sqlDao.findUnique(sql, sqlMap) ;
		
		return identy.toString();
	}

}
