package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModel;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModelRule;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelRuleBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IIntegralModelService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.aop.IntegralModelSaveOrUpdateHandler;
import com.taiji.pubsec.szpt.util.Constant;

/**
 * 人员积分模型
 * @author sunjd
 *
 */
@Service("integralModelService")
public class IntegralModelServiceImpl implements IIntegralModelService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@AopAnno(mark=IntegralModelSaveOrUpdateHandler.MARK)
	@SuppressWarnings("unchecked")
	@Override
	public IntegralModel createIntegralModel(IntegralModelBean integarlModelBean , List<IntegralModelRuleBean> integralModelRuleBeanList) {
		IntegralModel integralModel = copyPropertyToModel(integarlModelBean);
		this.dao.save(integralModel);
		for(IntegralModelRuleBean imr :integralModelRuleBeanList){
			IntegralModelRule integralModelrule = copyIntegralModelrule(imr);
			integralModelrule.setIntegarlModel(integralModel);
			this.dao.save(integralModelrule);
			
			/**
			 * 更新integralModel的hibernate缓存，也可以
			 * this.dao.flush();this.dao.refresh(integralModel) ;
			 */
			if(integralModelrule.getId()!=null){
				integralModel.getIntegralModelRule().add(integralModelrule) ;
			}
		}
		return integralModel;
	}

	@AopAnno(mark=IntegralModelSaveOrUpdateHandler.MARK)
	@SuppressWarnings("unchecked")
	@Override
	public IntegralModel updateIntegralModel(IntegralModelBean integarlModelBean , List<IntegralModelRuleBean> integralModelRuleBeanList) {
		IntegralModel integralModel = copyPropertyToModel(integarlModelBean);
		this.dao.update(integralModel);
		
		List<IntegralModelRule> imrList = this.findIntegralModelRuleByIntegralModelId(integarlModelBean.getId());
		for(IntegralModelRule imr : imrList){
			this.dao.delete(IntegralModelRule.class,imr.getId());
		}
		for(IntegralModelRuleBean imr :integralModelRuleBeanList){
			IntegralModelRule integralModelrule = copyIntegralModelrule(imr);
			integralModelrule.setIntegarlModel(integralModel);
			this.dao.save(integralModelrule);
			
			/**
			 * 更新integralModel的hibernate缓存，也可以
			 * this.dao.flush();this.dao.refresh(integralModel) ;
			 */
			if(integralModelrule.getId()!=null){
				integralModel.getIntegralModelRule().add(integralModelrule) ;
			}
		}
		if(integralModel.getStatus().equals(Constant.DICT.DICT_ENABLED.getValue())){
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", Constant.DICT.DICT_ENABLED.getValue());
			List<IntegralModel> im = (List<IntegralModel>)this.dao.findAllByParams(IntegralModel.class, "select i from "+ IntegralModel.class.getName() +" as i where i.status = :status",map);
			if(null != im && im.size() >0){
				for(IntegralModel i :  im){
					if(!(i.getId().equals(integralModel.getId()))){
						i.setStatus(Constant.DICT.DICT_DISENABLED.getValue());
						this.dao.update(i);
					}
				}
			}
		}
		return integralModel ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IntegralModel findIntegarlModelById(String id) {
		return (IntegralModel)this.dao.findById(IntegralModel.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteIntegarlModel(String id) {
		this.dao.delete(IntegralModel.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<IntegralModel> findAllIntegarlModel(int pageNo, int pageSize) {
		String xql = "select i from IntegralModel as i where 1 = 1";
		return this.dao.findByPage(IntegralModel.class, xql, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveIntegarlModelRule(IntegralModelRule integralModelRule) {
		this.dao.save(integralModelRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteIntegralModelRule(String id) {
		this.dao.delete(IntegralModelRule.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IntegralModelRule> findIntegralModelRuleByIntegralModelId(
			String integralModelId) {
		String xql = "select i from IntegralModelRule as i where i.integarlModel.id = ?";
		return this.dao.findAllByParams(IntegralModelRule.class, xql, new Object[]{integralModelId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findMaxNum() {
		String xql = "select max(i.num) from IntegralModel as i";
		Map<String, String> m = new HashMap<String, String>();
		List<?> list = this.dao.findByXql(xql,m);
		return (String) list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IntegralModel findIntegralModelStatusOn() {
		String xql = "select i from IntegralModel as i where i.status = ?";
		List<IntegralModel> iml = this.dao.findAllByParams(IntegralModel.class, xql, new Object[]{"1"});
		if(iml.isEmpty()){
			return null;
		}else{
			return iml.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IntegralModel> findIntegarlModelByState(String state) {
		String xql = "select i from IntegralModel as i where i.status = ?";
		return this.dao.findAllByParams(IntegralModel.class, xql, new Object[]{state});
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public void updateModelStatus(String id, String status) {
		IntegralModel entity = (IntegralModel)this.dao.findById(IntegralModel.class, id);
		
		if(entity==null){
			return ;
		}
		
		entity.setStatus(status);
		this.dao.update(entity);
	}
	
	/**
	 * 人员积分模型bean转model
	 * @param integralModel
	 * @return SUCCESS
	 */
	private IntegralModel copyPropertyToModel(IntegralModelBean integralModel){
		IntegralModel imb = null;
		if(StringUtils.isBlank(integralModel.getId())){
			imb = new IntegralModel();
		}else{
			imb = this.findIntegarlModelById(integralModel.getId());
		}
		imb.setAlertPoint(Integer.valueOf(integralModel.getAlertPoint()));
		imb.setName(integralModel.getName());
		imb.setModifyPeople(integralModel.getModifyPeople());
		imb.setModifyTime(new Date());
		imb.setNote(integralModel.getNote());
		imb.setNum(integralModel.getNum());
		imb.setStatus(integralModel.getStatus());
		return imb;
	}
	
	private IntegralModelRule copyIntegralModelrule(IntegralModelRuleBean integralModelRuleBean){
		IntegralModelRule integralModelRule = new IntegralModelRule();
		integralModelRule.setKey(integralModelRuleBean.getKey());
		integralModelRule.setValue(integralModelRuleBean.getValue());
		return integralModelRule;
	}

}
