package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.aop.SavePersonExecuteControlStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.persistence.service.AbstractBaseService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.szpt.generatenum.service.IGenerateNumService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonMobileInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonMobileInfoService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

/**
 * Created by wangfx on 2016/10/13.
 */
@Service("personExecuteControlService")
public class PersonExecuteControlServiceImpl extends AbstractBaseService<PersonExecuteControl, String> implements IPersonExecuteControlService {

    @Autowired
    public PersonExecuteControlServiceImpl(Dao<PersonExecuteControl, String> dao) {
        setDao(dao);
    }
    
    @Resource
    private IPersonMobileInfoService personMobileInfoService;
    
    @Resource
	private IOperationRecordService operationRecordService;
    
    @Resource
   	private IGenerateNumService generateNumService;
    
	@Override
	public PersonExecuteControl findById(String id) {
		return super.findById(id);
	}

	public void updatePersonExecuteControlStatus(PersonExecuteControl entity){
		super.update(entity);
	}

	@Override
	public Pager<PersonExecuteControl> findPersonExecuteControlByCondition(Map<String, Object> paramMap, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select p from PersonExecuteControl as p where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<>();
		if(ParamMapUtil.isNotBlank(paramMap.get("code"))) {
			xql.append(" and p.num like :code");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("code", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("code")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart")) && ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))){
			xql.append(" and (( p.startTime > :timeStart");
			xql.append(" and  p.startTime < :timeEnd ) or");
			xql.append(" ( p.endTime > :timeStart ");
			xql.append(" and p.endTime < :timeEnd ) )");
			
			xqlMap.put("timeStart", paramMap.get("timeStart"));
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
			
		}else if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and ( p.endTime > :timeStart or");
			xql.append("  p.startTime > :timeStart )");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}else if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and ( p.endTime < :timeEnd or");
			xql.append("  p.startTime < :timeEnd )");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("personName"))) {
			xql.append(" and p.personName like :personName");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("personName", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("personName")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("personIdcode"))) {
			xql.append(" and p.idCardNo like :personIdcode");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("personIdcode", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("personIdcode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("note"))) {
			xql.append(" and p.note like :note");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("note", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("note")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("isShowInvalid")) && !((boolean) paramMap.get("isShowInvalid"))) {
			xql.append(" and p.status = :status");
			xqlMap.put("status", paramMap.get("status"));
		}
//		if (ParamMapUtil.isNotBlank(paramMap.get("operateStatusList"))) {
//			xql.append(" and p.operateStatus in (:operateStatusList)");
//			xqlMap.put("operateStatusList", paramMap.get("operateStatusList"));
//		}
		if (ParamMapUtil.isNotBlank(paramMap.get("operateStatus"))) {
			xql.append(" and p.operateStatus = :operateStatus");
			xqlMap.put("operateStatus", paramMap.get("operateStatus"));
		}
		xql.append(" order by p.lastModifyTime desc");
		return this.findByPage(xql.toString(), xqlMap, pageNo, pageSize);
	}

	@Override
	public void savePersonExecuteControl(PersonExecuteControl pec, List<PersonMobileInfo> pmil) {
		this.save(pec);
		for (PersonMobileInfo pmi : pmil) {
			pmi.setPersonExecuteControl(pec);
			pmi.setId(null);
			personMobileInfoService.save(pmi);
			
		}
	}
	
	@Override
	public String saveOperationRecord(OperationRecord operationRecord) {
		return operationRecordService.saveOperationRecord(operationRecord);
	}
	
	@Override
	public List<OperationRecord> findOperationRecordByPersonExecuteControl(String personExecuteControlId) {
		return operationRecordService.findOperationRecordByTarget(personExecuteControlId, PersonExecuteControl.class.getName(), null);
	}

	public String acquireNum(){
		String code = "BK";
		String num = generateNumService.acquireNum(code);
		int iNum = Integer.parseInt(num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String nowYear = sdf.format(new Date());
		return code + nowYear + String.format("%05d", iNum);
	}
}
