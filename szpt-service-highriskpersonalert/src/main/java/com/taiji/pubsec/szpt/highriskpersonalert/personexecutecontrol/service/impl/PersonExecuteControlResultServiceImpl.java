package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlResultService;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("personExecuteControlResultService")
public class PersonExecuteControlResultServiceImpl implements IPersonExecuteControlResultService {
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonExecuteControlResultServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Pager<DefaultSurveilListResult> findPersonExecuteControlResultByPage(String surveilListNum,
			int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select p from "+DefaultSurveilListResult.class.getName()+" as p where 1=1 ");
		Map<String, Object> xqlMap = new HashMap<>();
		if(ParamMapUtil.isNotBlank(surveilListNum)) {
			xql.append(" and p.surveilListNum=:surveilListNum");
			xqlMap.put("surveilListNum", surveilListNum);
		}
		xql.append(" order by p.catchTime desc ");
		return dao.findByPage(DefaultSurveilListResult.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean saveExecuteControlResultStatus(String id, String status) {
		DefaultSurveilListResult def=(DefaultSurveilListResult) dao.findById(DefaultSurveilListResult.class, id);
		def.setResultStatus(status);
		try{
			dao.update(def);
		}catch(Exception e){
			LOGGER.debug("更新人员布控详情状态失败",e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteExecuteControlResultStatus(String id) {
		try{
			DefaultSurveilListResult def=(DefaultSurveilListResult) dao.findById(DefaultSurveilListResult.class, id);
			dao.delete(def);
		}catch(Exception e){
			LOGGER.debug("删除失败",e);
			return false;
		}
		return true;
	}

	@Override
	public DefaultSurveilListResult findById(String resultId) {
		return (DefaultSurveilListResult) dao.findById(DefaultSurveilListResult.class, resultId);
	}
}
