package com.taiji.pubsec.szpt.caseanalysis.score.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.RobberyTheftCaseScoreTemplateBean;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 串并案分析评分模版Action
 * 
 * @author WangLei
 *
 */
@Controller("scoreTemplateAction")
@Scope("prototype")
public class ScoreTemplateAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreTemplateAction.class);
	
	@Resource
	private ScoreTemplateService scoreTemplateService;// 串并案分析评分模版接口
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用

	/**
	 * 新增串并案分析评分模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		JSONObject jsonRtcstb = JSONObject.fromObject(rqst.get("rtcstb"));
		RobberyTheftCaseScoreTemplateBean rtcstb = (RobberyTheftCaseScoreTemplateBean) JSONObject.toBean(jsonRtcstb, RobberyTheftCaseScoreTemplateBean.class);
		JSONArray jsonRtcstrs = JSONArray.fromObject(rqst.get("rtcstrs"));
		RobberyTheftCaseScoreTemplateRule[] rtcstrs = (RobberyTheftCaseScoreTemplateRule[])JSONArray.toArray(jsonRtcstrs, RobberyTheftCaseScoreTemplateRule.class);
		//验证名称和编码是否有重复
		String msg = "";
		if(verifyCodeOnly(rtcstb.getCode())){
			msg += "模版编码" ;
		}
		if(verifyNameOnly(rtcstb.getName())){
			if(StringUtils.isBlank(msg)){
				msg += "模版名称" ;
			}else{
				msg += "、模版名称" ;
			}
		}
		
		if(StringUtils.isBlank(msg)){
			if(DICT.DICT_ENABLED.getValue().equals(rtcstb.getState())){
				RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findTemplate(rtcstb.getType(),rtcstb.getFirstSort(),rtcstb.getSecondSort());
				if(rtcst != null){
					disableTemplateByRtcst(rtcst.getId());
				}
			}
			boolean flag = scoreTemplateService.createTemplate(templateBeanToTemplate(rtcstb), Arrays.asList(rtcstrs));
			resultMap.put("flag", flag);
			if(!flag){
				resultMap.put("msg", "保存失败。");
			}
		}else{
			resultMap.put("msg",msg + "已存在。");
			resultMap.put("flag", false);
		}
		return SUCCESS;
	}

	/**
	 * 修改串并案分析评分模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modify() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		JSONObject jsonRtcstb = JSONObject.fromObject(rqst.get("rtcstb"));
		RobberyTheftCaseScoreTemplateBean rtcstb = (RobberyTheftCaseScoreTemplateBean) JSONObject.toBean(jsonRtcstb, RobberyTheftCaseScoreTemplateBean.class);
		JSONArray jsonRtcstrs = JSONArray.fromObject(rqst.get("rtcstrs"));
		RobberyTheftCaseScoreTemplateRule[] rtcstrs = (RobberyTheftCaseScoreTemplateRule[])JSONArray.toArray(jsonRtcstrs, RobberyTheftCaseScoreTemplateRule.class);
		
		//验证名称和编码是否有重复
		RobberyTheftCaseScoreTemplate rtcstModel = scoreTemplateService.findTemplate(rtcstb.getId());
		String msg = "";
		if(!rtcstb.getName().equals(rtcstModel.getName()) || !rtcstb.getCode().equals(rtcstModel.getCode())){
			if(verifyCodeOnly(rtcstb.getCode())){
				msg += "模版编码" ;
			}
			if(verifyNameOnly(rtcstb.getName())){
				if(StringUtils.isBlank(msg)){
					msg += "模版名称" ;
				}else{
					msg += "、模版名称" ;
				}
			}
		}
		if(StringUtils.isBlank(msg)){
			if(DICT.DICT_ENABLED.getValue().equals(rtcstb.getState())){
				RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findTemplate(rtcstb.getType(),rtcstb.getFirstSort(),rtcstb.getSecondSort());
				if(rtcst != null){
					disableTemplateByRtcst(rtcst.getId());
				}
			}
			Map<String, RobberyTheftCaseScoreTemplateRule> rtcstrMap = new HashMap<String, RobberyTheftCaseScoreTemplateRule>();
			for(RobberyTheftCaseScoreTemplateRule rtcstr : Arrays.asList(rtcstrs)){
				rtcstrMap.put(rtcstr.getItem(), rtcstr);
			}
			boolean flag = scoreTemplateService.updateTemplate(templateBeanToTemplate(rtcstb), rtcstrMap);
			resultMap.put("flag", flag);
			if(!flag){
				resultMap.put("msg", "修改失败。");
			}
		}else{
			resultMap.put("msg",msg + "已存在。");
			resultMap.put("flag", false);
		}
		return SUCCESS;
	}

	/**
	 * 修改串并案分析评分模版状态
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyStatusByIds() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String state = (String)rqst.get("state");
		JSONArray jsonRtcstbIds = JSONArray.fromObject(rqst.get("rtcstbIds"));
		String[] rtcstbIds = (String[])JSONArray.toArray(jsonRtcstbIds, String.class);
		
		for(String rtcstbId : rtcstbIds){
			RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findTemplate(rtcstbId);
			if(rtcst == null){
				continue;
			}
			if(DICT.DICT_ENABLED.getValue().equals(state)){// 启用操作，根据案件性质停用所有模版
				RobberyTheftCaseScoreTemplate rtcst2 = scoreTemplateService.findTemplate(rtcst.getType(),rtcst.getFirstSort(),rtcst.getSecondSort());
				if(rtcst2 != null){
					disableTemplateByRtcst(rtcst2.getId());
				}
			}
			scoreTemplateService.updateTemplateState(rtcstbId, state);
		}
		resultMap.put("flag", true);
		return SUCCESS;
	}

	/**
	 * 删除串并案分析评分模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteByIds() {
		boolean flag = false;
		Map<String, Object> rqst = JSONObject.fromObject(read());
		JSONArray jsonRtcstbIds = JSONArray.fromObject(rqst.get("rtcstbIds"));
		String[] rtcstbIds = (String[])JSONArray.toArray(jsonRtcstbIds, String.class);
		
		List<String> rtcstbNames = new ArrayList<String>();
		for(String rtcstbId : rtcstbIds){
			boolean isDelete = scoreTemplateService.enableDeleted(rtcstbId);
			if(isDelete){
				scoreTemplateService.deleteTemplete(rtcstbId);
			}else{
				rtcstbNames.add(scoreTemplateService.findTemplate(rtcstbId).getName());
			}
		}
		flag = true;
		resultMap.put("flag", flag);
		if(!rtcstbNames.isEmpty()){
			resultMap.put("rtcstbNames", rtcstbNames);
		}
		return SUCCESS;
	}

	/**
	 * 分页查询串并案分析评分模版集合
	 * 
	 * @return
	 */
	public String queryList() {
		List<RobberyTheftCaseScoreTemplate> rtcsts = scoreTemplateService.findAllTemplate();
		List<RobberyTheftCaseScoreTemplateBean> rtcstbs = new ArrayList<RobberyTheftCaseScoreTemplateBean>();
		if(rtcsts == null || rtcsts.isEmpty()){
			resultMap.put("rtcstbs", rtcstbs);
			return SUCCESS;
		}
		for(RobberyTheftCaseScoreTemplate rtcst : rtcsts){
			RobberyTheftCaseScoreTemplateBean rtcstb = templateToTemplateBean(rtcst);
			if(rtcstb == null){
				continue;
			}
			rtcstbs.add(rtcstb);
		}
		resultMap.put("rtcstbs", rtcstbs);
		return SUCCESS;
	}

	/**
	 * 根据id查询串并案分析评分模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryById() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String rtcstId = (String)rqst.get("rtcstId");
		RobberyTheftCaseScoreTemplateBean rtcstb = templateToTemplateBean(scoreTemplateService.findTemplate(rtcstId));
		resultMap.put("rtcstb", rtcstb);
		resultMap.put("rtcstrs", rtcstb.getRules());
		return SUCCESS;
	}
	
	/**
	 * 验证串并案分析评分模版名称是否唯一
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String verifyNameOnly(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String name = (String)rqst.get("name");
		resultMap.put("flag", verifyNameOnly(name));
		return SUCCESS;
	}
	
	/**
	 * 验证串并案分析评分模版编码是否唯一
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String verifyCodeOnly(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String code = (String)rqst.get("code");
		resultMap.put("flag", verifyCodeOnly(code));
		return SUCCESS;
	}
	
	/**
	 * 根据编码验证，私有工具方法
	 * @param code
	 * @return 重复返回true，不重复返回false
	 */
	private boolean verifyCodeOnly(String code){
		return scoreTemplateService.hasSameCodeTemplate(code);
	}
	
	/**
	 * 根据名称验证，私有工具方法
	 * @param name
	 * @return 重复返回true，不重复返回false
	 */
	private boolean verifyNameOnly(String name){
		return scoreTemplateService.hasSameNameTemplate(name);
	}
	
	/**
	 * 修改串并案分析评分模版为停用状态
	 * 
	 * @return
	 */
	private void disableTemplateByRtcst(String id){
		scoreTemplateService.updateTemplateState(id, DICT.DICT_DISENABLED.getValue());
	}
	
	/**
	 * 串并案分析评分模版Bean To Model
	 * 
	 * @param rtcstb
	 * @return
	 */
	private RobberyTheftCaseScoreTemplate templateBeanToTemplate(RobberyTheftCaseScoreTemplateBean rtcstb){
		if(rtcstb == null){
			return null;
		}
		RobberyTheftCaseScoreTemplate rtcst = new RobberyTheftCaseScoreTemplate();
		if(StringUtils.isBlank(rtcstb.getId())){
			rtcst.setId(null);
		}else{
			if(scoreTemplateService.findTemplate(rtcstb.getId()) != null){
				rtcst = scoreTemplateService.findTemplate(rtcstb.getId());
			}
		}
		rtcst.setCode(rtcstb.getCode());
		rtcst.setName(rtcstb.getName());
		rtcst.setType(rtcstb.getType());
		rtcst.setFirstSort(rtcstb.getFirstSort());
		rtcst.setSecondSort(rtcstb.getSecondSort());
		rtcst.setState(rtcstb.getState());
		rtcst.setRemarks(rtcstb.getRemarks());
		rtcst.setMinScore(rtcstb.getMinScore());
		if(StringUtils.isBlank(rtcstb.getId())){
			rtcst.setCreatePerson(this.findCurrentPerson().getName());
			rtcst.setCreatedTime(new Date());
		}
		rtcst.setUpdatePerson(this.findCurrentPerson().getName());
		rtcst.setUpdateTime(new Date());
		return rtcst;
	}
	
	/**
	 * 串并案分析评分模版Model To Bean
	 * 
	 * @param rtcst
	 * @return
	 */
	private RobberyTheftCaseScoreTemplateBean templateToTemplateBean(RobberyTheftCaseScoreTemplate rtcst){
		if(rtcst == null){
			return null;
		}
		RobberyTheftCaseScoreTemplateBean rtcstb = new RobberyTheftCaseScoreTemplateBean();
		rtcstb.setId(rtcst.getId());
		rtcstb.setCode(rtcst.getCode());
		rtcstb.setName(rtcst.getName());
		rtcstb.setType(rtcst.getType());
		rtcstb.setTypeName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), rtcst.getType()));
		rtcstb.setFirstSort(rtcst.getFirstSort());
		rtcstb.setFirstSortName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), rtcst.getFirstSort()));
		rtcstb.setSecondSort(rtcst.getSecondSort());
		rtcstb.setSecondSortName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), rtcst.getSecondSort()));
		rtcstb.setState(rtcst.getState());
		rtcstb.setStateName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZT.getValue(), rtcst.getState()));
		rtcstb.setRemarks(rtcst.getRemarks());
		rtcstb.setMinScore(rtcst.getMinScore());
		rtcstb.setCreatePerson(rtcst.getCreatePerson());
		rtcstb.setCreatedTime(DateFmtUtil.dateToLong(rtcst.getCreatedTime()));
		rtcstb.setUpdatePerson(rtcst.getUpdatePerson());
		rtcstb.setUpdateTime(DateFmtUtil.dateToLong(rtcst.getUpdateTime()));
		rtcstb.setComputeTaskId(rtcst.getComputeTaskId());
		rtcstb.setRules(rtcst.getRules());
		return rtcstb;
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	
}
