package com.taiji.pubsec.szpt.highriskpersonalert.service.aop;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service
public class HighRiskPersonSaveOrUpdateHandler extends AbstractAopAnnoHandler{
	
	public static final String MARK = "HighRiskPersonSaveOrUpdateHandler" ;
	
	/**
	 *高危人新增和更新增加solr索引 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp){
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		HighriskPerson hp = (HighriskPerson) jp.getArgs()[0];
		if(hp == null) return;
		String collecttime = sdfdst.format(hp.getCreatedTime());
		
		Map map = new HashMap();
		map.put("id", hp.getIdcode());
		map.put("idcard", hp.getIdcode());
		map.put("name", hp.getName());
		map.put("oldname", hp.getUsedName());
		map.put("gender", hp.getSex());
		map.put("collecttime", collecttime);
		map.put("type", null);
		map.put("persontype", null);
		map.put("criminaltype", null);
		map.put("alertlevel", hp.getWarnType());
		map.put("nation", hp.getEthnicGroup());
		map.put("birthday", hp.getBirthday());
		map.put("birthaddress", hp.getRegisterAddressDetail());
		map.put("address", hp.getRegisterAddress());
		map.put("culture", hp.getEducation());
		map.put("marry", hp.getMarriageStatus());
		map.put("occupation", hp.getEmploymentStatus());
		map.put("phone", hp.getMobilePhoneInfos());
		map.put("householder", null);
		map.put("relation", null);
		
		Map textMap = new HashMap();
		textMap.put("name", hp.getName());
		textMap.put("oldname", hp.getUsedName());
		textMap.put("address", hp.getRegisterAddress());
		textMap.put("occupation", hp.getEmploymentStatus());
		textMap.put("phone", hp.getMobilePhoneInfos());
		textMap.put("birthaddress", hp.getRegisterAddressDetail());
		textMap.put("householder", null);
		
		map.put("text", textMap);
		
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex(SolrConstant.COLLECTION_POPULATION, "id", map);
	}
	
	@Override
	public String getMark() {
		return MARK;
	}

}
