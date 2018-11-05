package com.taiji.pubsec.szpt.dagl.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;
import com.taiji.pubsec.szpt.dagl.action.bean.WdgzBean;
import com.taiji.pubsec.szpt.dagl.model.InterestedPerson;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.dagl.service.PersonSearchService;
import com.taiji.pubsec.szpt.dagl.service.YrydService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.LoginInfoAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

import net.sf.json.JSONObject;

@Controller("wdgzAction")
@Scope("prototype")
public class WdgzAction extends LoginInfoAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String queryStr;
	
	private String idCode;
	private String id;
	
	private String name;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private YrydService yrydService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private PersonInterestedService personInterestedService;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private PersonSearchService personSearchService;		
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 重点人接口
	
	@Resource(name="criminalSzptPersonService")
	private CriminalPersonService criminalPersonService;
	
	/**
	 * 查询我的关注列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findInterestedPersonList(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		rqst.put("gzrId", this.findCurrentPerson().getId());
		Pager<InterestedPerson> pager = personInterestedService.findPersonByConditions(rqst,0,Integer.MAX_VALUE );
		List<WdgzBean> wdgzLst = new ArrayList();
		if( null != pager && null != pager.getPageList() && pager.getPageList().size() > 0){
			for(InterestedPerson interestedPerson : pager.getPageList()){
				wdgzLst.add(InterestedPersonTrunWdgzBean(interestedPerson));
			}
		}	
		resultMap.put("result", wdgzLst);
		return SUCCESS;
	}
	
	private WdgzBean InterestedPersonTrunWdgzBean(InterestedPerson ip){
		WdgzBean wdgz = new WdgzBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wdgz.setId(ip.getId());
		wdgz.setName(ip.getName());
		wdgz.setIdCard(ip.getIdCard());
		wdgz.setStick(ip.isStick());
		wdgz.setFollowTime(sdf.format(ip.getFollowTime()));
		HighriskPerson hp = highriskPersonService.findByIdCode(ip.getIdCard());
		wdgz.setGwr(false);
		if(null != hp){
			wdgz.setGwr(true);
			if(null != hp.getAccumulatePoints()){
				wdgz.setJf(hp.getAccumulatePoints().toString());
			}else{
				wdgz.setJf("0");
			}
			Set<HighriskPeopleType> highriskPeopleTypes = hp.getHighriskPeopleTypes();
			if(highriskPeopleTypes.size()>0){
				String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
				String personType = "";
				for(HighriskPeopleType hpt : highriskPeopleTypes){
					DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, hpt.getPeopleType(), null);
					if(null != dict){
						personType += dict.getName() + ",";
					}
				}
				if(personType.length()>0){
					personType = personType.substring(0,personType.length()-1);
				}
				wdgz.setRylb(personType);
			}
			if(null != hp.getWarnType()){
				wdgz.setYjlx(hp.getWarnType());
			}
		}
		if(null != criminalPersonService.findCriminalPersonByIdcard(ip.getIdCard())){
			wdgz.setXyr(true);
			wdgz.setGwr(true);
		}else{
			wdgz.setXyr(false);
		}
		return wdgz;
	}
	
	/**
	 * 创建关注人
	 * @return
	 */
	public String followInterestedPerson(){
		InterestedPerson entity = new InterestedPerson();
		entity.setIdCard(idCode);
		entity.setStick(false);
		entity.setPersonId(this.findCurrentPerson().getId());
		entity.setFollowTime(new Date());
		entity.setName(name);
		boolean flag = personInterestedService.save(entity);
		resultMap.put("result", flag);
		return SUCCESS;
	}
	
	/**
	 * 取消关注
	 * @return
	 */
	public String cancelInterestedPerson(){
		personInterestedService.updateCancelPerson(id);
		return SUCCESS;
	}
	
	/**
	 * 一人一档详情页取消关注
	 * 
	 */
	public String yrydDetailCancelInterestedPerson(){
		InterestedPerson interestedPerson = personInterestedService.personIfOrNotInterested(this.findCurrentPerson().getId(), idCode);
		personInterestedService.updateCancelPerson(interestedPerson.getId());
		return SUCCESS;
	}
	
	/**
	 * 置顶
	 * @return
	 */
	public String stickInterestedPerson(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String id = String.valueOf(rqst.get("id"));
		personInterestedService.updateStickPerson(id);
		return SUCCESS;
	}
	
	/**
	 * 取消置顶
	 * @return
	 */
	public String unstickInterestedPerson(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String id = String.valueOf(rqst.get("id"));
		personInterestedService.updateUnstickPerson(id);
		return SUCCESS;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
