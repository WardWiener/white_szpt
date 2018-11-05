package com.taiji.pubsec.fullsearch.action.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.fullsearch.action.bean.CasesBean;
import com.taiji.pubsec.fullsearch.action.bean.CommandBean;
import com.taiji.pubsec.fullsearch.action.bean.EventBean;
import com.taiji.pubsec.fullsearch.action.bean.PersonBriefInfo;
import com.taiji.pubsec.fullsearch.action.bean.SiteBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Component
public class Util {
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource(name="criminalSzptPersonService")
	private CriminalPersonService criminalPersonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	private  DateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
	public  PersonBriefInfo mapTrunPersonBean(Map<String,Object> map){
		
		//map转人员bean
		 PersonBriefInfo personBriefInfo = new PersonBriefInfo();
			personBriefInfo.setIdcard(null == map.get("idcard")? "" :  String.valueOf(map.get("idcard")));
			personBriefInfo.setName(null == map.get("name")? "" :  String.valueOf(map.get("name")));
			personBriefInfo.setOldname(null == map.get("oldname")? "" :  String.valueOf(map.get("oldname")));
			personBriefInfo.setAddress(null == map.get("address")? "" :  String.valueOf(map.get("address")));
			personBriefInfo.setType(null == map.get("type")? "" :  String.valueOf(map.get("type")));
			personBriefInfo.setNation(null == map.get("nation")? "" :  String.valueOf(map.get("nation")));
			personBriefInfo.setBirthaddress(null == map.get("birthaddress")? "" :  String.valueOf(map.get("birthaddress")));
			personBriefInfo.setCulture(null == map.get("culture")? "" :  String.valueOf(map.get("culture")));
			personBriefInfo.setMarry(null == map.get("marry")? "" :  String.valueOf(map.get("marry")));
			personBriefInfo.setOccupation(null == map.get("occupation")? "" :  String.valueOf(map.get("occupation")));
			personBriefInfo.setPhone(null == map.get("phone")? "" :  String.valueOf(map.get("phone")));
			personBriefInfo.setHouseholder(null == map.get("householder")? "" : String.valueOf(map.get("householder")));
			personBriefInfo.setRelation(null == map.get("relation")? "" : String.valueOf(map.get("relation")));
			personBriefInfo.setBirthday(null == map.get("birthday") ? "" : dt.format(map.get("birthday")));
			personBriefInfo.setAlertlevel(null == map.get("alertlevel") ? "" : String.valueOf(map.get("alertlevel")));
			if(map.get("type")!= null && ("是".equals(String.valueOf(map.get("type"))) || "1".equals(String.valueOf(map.get("type"))))){
				HighriskPerson highriskPerson = highriskPersonService.findByIdCode(personBriefInfo.getIdcard());
				personBriefInfo.setLocalAddress(null == highriskPerson ? "" : highriskPerson.getLocalAddressDetail());
			}
			if(null != criminalPersonService.findCriminalPersonByIdcard(personBriefInfo.getIdcard())){
				personBriefInfo.setSfsXyy("1");
			}else{
				personBriefInfo.setSfsXyy("0");
			}
			if(null !=map.get("gender") && ("1".equals(String.valueOf(map.get("gender"))) || "男".equals(String.valueOf(map.get("gender"))))){
				personBriefInfo.setSex("男");
			}else if(null !=map.get("gender") && ("2".equals(String.valueOf(map.get("gender"))) || "女".equals(String.valueOf(map.get("gender"))))){
				personBriefInfo.setSex("女");
			}else{
				personBriefInfo.setSex("性别:未知");
			}
			
			if(ParamMapUtil.isNotBlank(map.get("persontypecode"))){
				Object persontypecodes = map.get("persontypecode");
				String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
				DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode,persontypecodes.toString(), null);
				if(null !=  dict){
					personBriefInfo.setPersontypeName(dict.getName());
				}else{
					personBriefInfo.setPersontypeName("");
				}
			}else{
				personBriefInfo.setPersontypeName("");
			}
			if(ParamMapUtil.isNotBlank(map.get("criminaltypecode"))){
				Object persontypecodes = map.get("criminaltypecode");
				String qianKeType = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_QKLX.getValue();
				DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(qianKeType,persontypecodes.toString(), null);
				if(null !=  dict){
					personBriefInfo.setQianKeType(dict.getName());
				}else{
					personBriefInfo.setQianKeType("");
				}
			}else{
				personBriefInfo.setPersontypeName("");
			}
		 return personBriefInfo;
	 }
	//map转案件bean
	public CasesBean mapTrunCasesBean(Map<String,Object> map){
		CasesBean casesBean = new CasesBean();
		casesBean.setId(null == map.get("id")? "" :  String.valueOf(map.get("id")));
		casesBean.setName(null == map.get("name")? "" :  String.valueOf(map.get("name")));
		casesBean.setDate(null == map.get("crimetime") ? "" : dt.format(map.get("crimetime")));
		casesBean.setType(null == map.get("property") ? "" : String.valueOf(map.get("property")));
		casesBean.setId(null == map.get("id")? "" :  String.valueOf(map.get("id")));
		return casesBean;
	}
	
	//map转警情bean
	public EventBean mapTrunEventBean(Map<String,Object> map){
		EventBean bean=new EventBean();
		bean.setName(null == map.get("name") ? "" : String.valueOf(map.get("name")));   //警情名称
		bean.setId(null == map.get("id") ? "" : String.valueOf(map.get("id")));//警情名称
		bean.setAnswertime(null == map.get("answertime") ? "" : dt.format(map.get("answertime")));  //接警时间
		bean.setType(null == map.get("type") ? "" : String.valueOf(map.get("type")));//警情类型
		bean.setLevel(null == map.get("level") ? "" : String.valueOf(map.get("level")));//紧急程度
		bean.setOccuraddress(null == map.get("occuraddress") ? "" : String.valueOf(map.get("occuraddress")));//发生地点
		bean.setOccurtime(null == map.get("occurtime") ? "" : dt.format(map.get("occurtime"))); //发生时间
		bean.setState(null == map.get("state") ? "" : String.valueOf(map.get("state")));//警情状态
		bean.setSource(null == map.get("source") ? "" : String.valueOf(map.get("source")));//警情来源
		return bean;
	}
	//map转场所bean
	public SiteBean mapTrunSiteBean(Map<String,Object> map){
		SiteBean bean=new SiteBean();
		bean.setName(null == map.get("name") ? "" : String.valueOf(map.get("name")));
		bean.setSiteType(null == map.get("type") ? "" :  String.valueOf(map.get("type")));
		bean.setAlertLevel(null == map.get("alertlevel") ? "" : String.valueOf(map.get("alertlevel")));
		return bean;
	}
	
	//map转指令bean
	public CommandBean mapTrunCommandBean(Map<String,Object> map){
		CommandBean bean=new CommandBean();
		bean.setId(null == map.get("id") ? "" : String.valueOf(map.get("id")));
		bean.setContent(null == map.get("createtime") ? "" : String.valueOf(map.get("content")));
		bean.setType(null == map.get("createtime") ? "" : String.valueOf(map.get("type")));
		bean.setCreatetime(null == map.get("createtime") ? "" : dt.format(map.get("createtime")));
		bean.setSendunit(String.valueOf(map.get(SolrConstant.Instruction.sendunit.getValue())));
		bean.setAskfeedbacktime(null == map.get("createtime") ? "" : dt.format(map.get("askfeedbacktime")));
		bean.setReletedcontent(null == map.get("reletedcontent") ? "" : String.valueOf(map.get("reletedcontent")));
		return bean;
	}

}
