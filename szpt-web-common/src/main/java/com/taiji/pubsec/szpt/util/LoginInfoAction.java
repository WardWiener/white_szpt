package com.taiji.pubsec.szpt.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.operationrecord.service.StandardLogRecordService;
import com.taiji.pubsec.szpt.util.Constant.DICT;

public class LoginInfoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private IOrganizationService organizationService;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ; // 字典项接口
	
	@Resource
	private StandardLogRecordService standardLogRecordService ;// 警综平台操作日志接口
	
	@SuppressWarnings("unchecked")
	protected Person findCurrentPerson(){
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		Map<String, String> mPerson = new HashMap<String, String>(0) ;
		if(userMap.get("person")!=null){
			mPerson = (Map<String, String>)userMap.get("person");
		}
		return personService.findById(mPerson.get("id"));
	}
	@SuppressWarnings("unchecked")
	protected Organization findCurrentOrganization(){
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		Map<String, String> mOrg = new HashMap<String, String>(0) ;
		if(userMap.get("org")!=null){
			mOrg = (Map<String, String>)userMap.get("org");
		}
		return organizationService.findOrganizationById(mOrg.get("id"));
	}
	
	/**
	 * 根据字典类型code和字典项code查询字典项名称
	 * 
	 * @param dictTypeCode 字典类型编码
	 * @param dictItemCode 字典项编码
	 * @return 字典项名称，没有返回""
	 */
	protected String findDictNameByDictTypeCodeAndDictItemCode(String dictTypeCode, String dictItemCode){
		String dictName = "";
		DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(dictTypeCode, dictItemCode, DICT.DICT_ENABLED.getValue());
		if(di != null){
			dictName = di.getName();
		}
		return dictName;
	}
	
	/**
	 * 创建警综平台操作日志
	 * 
	 * @param operationType 操作类型
	 * @param result 操作结果
	 * @param errorCode  错误代码
	 * @param functionModule  功能模块名称
	 * @param condition  查询条件
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	protected void createStandardLog(int operationType, String result, String errorCode, String functionModule, String condition){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		Map<String, String> mPerson = new HashMap<String, String>(0) ;
		mPerson = (Map<String, String>)userMap.get("person");
		Map<String, String> mOrg = new HashMap<String, String>(0) ;
		mOrg = (Map<String, String>)userMap.get("org");
		
		StandardLogRecord slr = new StandardLogRecord();
		if(mPerson != null){
			slr.setUserId(mPerson.get("idNumber"));
			slr.setOperator(mPerson.get("name"));
		}
		if(mOrg != null){
			slr.setUnitName(mOrg.get("shortName"));
			slr.setUnitCode(mOrg.get("code"));
		}
		slr.setOperateTime(sdf.format(new Date()));
		slr.setTerminalId(request.getRemoteAddr());
		slr.setOperationType(operationType);
		slr.setResult(result);
		slr.setErrorCode(errorCode);
		slr.setFunctionModule(functionModule);
		slr.setCondition(condition);
		
		standardLogRecordService.save(slr);
	}
}
