package com.taiji.pubsec.szpt.dagl.service.impl;

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
import com.taiji.pubsec.szpt.dagl.model.InterestedPerson;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service
public class PersonInterestedServiceImpl implements  PersonInterestedService{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonInterestedService.class);
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	@Override
	public boolean updateCancelPerson(String id) {
		try {
			dao.delete(InterestedPerson.class, id);
		} catch(Exception e) {
			LOGGER.debug("取消关注失败", e);
			return false;
		}
		return true;
	}

	@Override
	public Pager<InterestedPerson> findPersonByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		Map< String , Object> paramValue = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select distinct i from " + InterestedPerson.class.getName() + " as i  ");
		StringBuilder hqlWhere = new StringBuilder("");

		if(ParamMapUtil.isNotBlank(conditions.get("rylb")) || ParamMapUtil.isNotBlank(conditions.get("xsqk")) || ParamMapUtil.isNotBlank(conditions.get("warnType"))){
			hql.append(", " + HighriskPerson.class.getName() +" as h ");
			hqlWhere.append(" and i.idCard = h.idcode ");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("rylb")) && ParamMapUtil.isNotBlank(conditions.get("xsqk"))){
			List<String> rylb = (List<String>)conditions.get("rylb");
			hql.append(" , " + HighriskPeopleType.class.getName() +" as hpt ");
			hqlWhere.append(" and hpt.highriskPerson.id = h.id ");
						
			List<String> xsqk = (List<String>)conditions.get("xsqk");
			hql.append(" left join " + HighriskCriminalRecord.class.getName() +" as hcr ");
			hql.append(" on ( hcr.highriskPeopleType.id = hpt.id ");
			
			hql.append(" ) ");
			hqlWhere.append(" and (");
			int size  = rylb.size();
			for(int i = 0;i < size; i++){
				hqlWhere.append(" hpt.peopleType like '"+rylb.get(i)+"%' or ");
			}
			int len  = xsqk.size();
			for(int i = 0;i < len; i++){
				if(i<len-1){
					hqlWhere.append(" hcr.criminalRecord like '"+xsqk.get(i)+"%' or ");
				}else{
					hqlWhere.append(" hcr.criminalRecord like '"+xsqk.get(i)+"%' ");
				}
			}
			hqlWhere.append(" ) ");
		}else if(ParamMapUtil.isNotBlank(conditions.get("rylb"))){
			List<String> rylb = (List<String>)conditions.get("rylb");
			hql.append(" , " + HighriskPeopleType.class.getName() +" as hpt ");
			hqlWhere.append(" and hpt.highriskPerson.id = h.id ");
			hqlWhere.append(" and (");
			int size  = rylb.size();
			for(int i = 0;i < size; i++){
				if(i<size-1){
					hqlWhere.append(" hpt.peopleType like '"+rylb.get(i)+"%' or ");
				}else{
					hqlWhere.append(" hpt.peopleType like '"+rylb.get(i)+"%' ");
				}
			}
			hqlWhere.append(" ) ");
		}else if(ParamMapUtil.isNotBlank(conditions.get("xsqk"))){
			hql.append(" , " + HighriskPeopleType.class.getName() +" as hpt ");
			hqlWhere.append(" and hpt.highriskPerson.id = h.id ");
			
			List<String> xsqk = (List<String>)conditions.get("xsqk");
			hql.append(" , " + HighriskCriminalRecord.class.getName() +" as hcr ");
			hqlWhere.append(" and hcr.highriskPeopleType.id = hpt.id ");
			hqlWhere.append(" and ( ");
			int size  = xsqk.size();
			for(int i = 0;i < size; i++){
				if(i<size-1){
					hqlWhere.append(" hcr.criminalRecord like '"+xsqk.get(i)+"%' or ");
				}else{
					hqlWhere.append(" hcr.criminalRecord like '"+xsqk.get(i)+"%' ");
				}
			}
			hqlWhere.append(" ) ");
		}
		
		if(ParamMapUtil.isNotBlank(conditions.get("warnType"))){
			List<String> warnType = (List<String>)conditions.get("warnType");
			hqlWhere.append(" and h.warnType in (:warnType)");
			paramValue.put("warnType", warnType);
		}
		
		if(ParamMapUtil.isNotBlank(conditions.get("gzrId"))){
			hqlWhere.append(" and i.personId = :gzrId ");
			paramValue.put("gzrId", conditions.get("gzrId"));
		}
		
		if(ParamMapUtil.isNotBlank(conditions.get("name"))){
			hqlWhere.append(" and i.name like :name");
			SQLTool.SQLAddEscape(hql);
			paramValue.put("name", "%" + SQLTool.SQLSpecialChTranfer((String)conditions.get("name")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("idCard"))){
			hqlWhere.append(" and i.idCard like :idCard");
			SQLTool.SQLAddEscape(hql);
			paramValue.put("idCard", "%" + SQLTool.SQLSpecialChTranfer((String)conditions.get("idCard")) + "%");
		}
		hqlWhere.append(" order by i.isStick desc");
		hql.append(" where 1 = 1 ").append(hqlWhere.toString());
		Pager<InterestedPerson> page  = this.dao.findByPage(InterestedPerson.class, hql.toString(), paramValue, pageNo, pageSize);
		return page;
	}

	@Override
	public boolean save(InterestedPerson interested) {
		try {
			this.dao.save(interested);
		} catch(Exception e) {
			LOGGER.debug("添加关注失败", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStickPerson(String id) {
		try {
		InterestedPerson ip = (InterestedPerson)this.dao.findById(InterestedPerson.class, id);
		ip.setStick(true);
		this.dao.update(ip);
		} catch(Exception e) {
			LOGGER.debug("置顶失败", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUnstickPerson(String id) {
		try {
			InterestedPerson ip = (InterestedPerson)this.dao.findById(InterestedPerson.class, id);
			ip.setStick(false);
			this.dao.update(ip);
			} catch(Exception e) {
				LOGGER.debug("置顶失败", e);
				return false;
			}
			return true;
	}

	@Override
	public InterestedPerson personIfOrNotInterested(String gzPersonId, String bgzPersonIdcard) {	
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("personId", gzPersonId) ;
		map.put("idCard", bgzPersonIdcard) ;
		String xql = "select i from " +InterestedPerson.class.getName()+" as i where " + " i.personId=:personId and i.idCard=:idCard" ;
		List<InterestedPerson> list = this.dao.findAllByParams(InterestedPerson.class, xql, map) ;
		if(list.size()>0){
			return list.get(0);
		}
		return null ;
	}

	

}
