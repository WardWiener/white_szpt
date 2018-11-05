package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.common.tools.datetime.DateTimeUtil;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModel;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModelRule;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelRuleBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IIntegralModelService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * 人员积分模型
 * @author sunjd
 *
 */
@Controller("integralModelAction")
@Scope("prototype")
public class IntegralModelAction  extends PageCommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IntegralModelAction.class);
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	@Resource
	private IIntegralModelService integralModelService;// 积分模型接口
	
	// 人员接口
	@Resource(name = "personService")
	private IPersonService personService;
	
	private List<IntegralModelBean> integarlModelBeanList;
	private IntegralModelBean integralModelBean;
	private String num;	//生成的编号
	private List<DictionaryItem> dictionaryItem;	//字典项
	private String id;		//积分模型id
	private String status;	//状态
	private boolean flag;	//成功或失败
	
	/**
	 * 分页查询人员积分模型
	 * @return SUCCESS
	 */
	public String findIntegralModel(){
		Pager<IntegralModel> integarlModel = integralModelService.findAllIntegarlModel(this.getStart()/this.getLength(), this.getLength());
		this.setTotalNum(integarlModel.getTotalNum());
		List<IntegralModel> integralModel = integarlModel.getPageList();
		integarlModelBeanList = new ArrayList<IntegralModelBean>();
		for(IntegralModel im : integralModel){
			integarlModelBeanList.add(copyPropertyToBean(im));
		}
		return SUCCESS;
	}
	
	/**
	 * 保存人员积分模型及规则
	 * @return SUCCESS
	 */
	public String saveIntegralModel(){
		integralModelBean.setModifyPeople(findCurrentPerson().getId());
		//如果模板状态是启用则把以前启用的模型改为停用
		if(DICT.DICT_ENABLED.getValue().equals(integralModelBean.getStatus())){
			IntegralModel im = integralModelService.findIntegralModelStatusOn();
			if(im != null){
				integralModelService.updateModelStatus(im.getId(), DICT.DICT_DISENABLED.getValue());
			}
		}
		integralModelService.createIntegralModel(integralModelBean,integralModelBean.getIntegralModelRule());
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 删除人员积分模型
	 * @return SUCCESS
	 */
	public String delete(){
		List<IntegralModelRule> imrList = integralModelService.findIntegralModelRuleByIntegralModelId(id);
		for(IntegralModelRule imr : imrList){
			integralModelService.deleteIntegralModelRule(imr.getId());
		}
		integralModelService.deleteIntegarlModel(id);
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 更改状态
	 * @return SUCCESS
	 */
	public String updateState(){
		//如果模板状态是启用则把以前启用的模型改为停用
		if(DICT.DICT_ENABLED.getValue().equals(status)){
			IntegralModel im = integralModelService.findIntegralModelStatusOn();
			if(im != null){
				integralModelService.updateModelStatus(im.getId(), DICT.DICT_DISENABLED.getValue());
			}		
		}
		integralModelService.updateModelStatus(id,status);
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 更新人员积分模型
	 * @return SUCCESS
	 */
	public String updateIntegralModel(){
		integralModelBean.setModifyPeople(findCurrentPerson().getId());
		integralModelService.updateIntegralModel(integralModelBean,integralModelBean.getIntegralModelRule());
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 生成编号
	 * @return
	 */
	public String generateNumber(){
		computeMaxNum();
		dictionaryItem = dictionaryItemService.findDicItemsByType(DICT.DICT_TYPE_ZT_ID.getValue(), DICT.DICT_ENABLED.getValue());
		return SUCCESS ;
	}
	/**
	 * 计算生成编码
	 */
	private void computeMaxNum(){
		String nowDate = DateTimeUtil.formatDateTime(new Date(), "yyyy-MM-dd");
		nowDate = nowDate.substring(0, 4)+nowDate.substring(5, 7)+nowDate.substring(8);
		String maxNum = integralModelService.findMaxNum();
		if(StringUtils.isNoneBlank(maxNum)){
			String maxDate = maxNum.substring(0, 8);
			if(maxDate.endsWith(nowDate)){
				String max = maxNum.substring(8);
				int number =  Integer.parseInt(max)+1;
				String generateNum = String.valueOf(number);
				int index = 4-generateNum.length();
				for(int i=0;i<index;i++){
					generateNum = "0"+generateNum;
				}
				num =nowDate+generateNum;
			}else{
				num = nowDate+"0001";
			}
		}else{
			num = nowDate+"0001";
		}
	}
	
	/**
	 * 人员积分模型model转bean
	 * @param integralModel
	 * @return SUCCESS
	 */
	private IntegralModelBean copyPropertyToBean(IntegralModel integralModel){
		IntegralModelBean imb = new IntegralModelBean();
		imb.setAlertPoint(String.valueOf(integralModel.getAlertPoint()));
		imb.setId(integralModel.getId());
		imb.setName(integralModel.getName());
		Person per = personService.findById(integralModel.getModifyPeople());
		if(per!=null){
			imb.setModifyPeople(per.getId());
			imb.setModifyPeopleName(per.getName());
		}
		imb.setModifyTime(DateTimeUtil.formatDateTimeWithStandardFormat(integralModel.getModifyTime()));
		imb.setNote(integralModel.getNote());
		imb.setNum(integralModel.getNum());
		DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZT.getValue(), integralModel.getStatus(), Constant.ENABLED);
		if(di != null){
			imb.setStatus(di.getCode());
			imb.setStatusName(di.getName());
		}
		List<IntegralModelRuleBean> imrList = new ArrayList<IntegralModelRuleBean>();
		for(IntegralModelRule imr :integralModel.getIntegralModelRule()){
			IntegralModelRuleBean imrb = new IntegralModelRuleBean();
			imrb.setId(imr.getId());
			imrb.setKey(imr.getKey());
			imrb.setValue(imr.getValue());
			imrb.setIntegarlModel(imb);
			imrList.add(imrb);
		}
		imb.setIntegralModelRule(imrList);
		return imb;
	}
	

	public List<IntegralModelBean> getIntegarlModelBeanList() {
		return integarlModelBeanList;
	}

	public void setIntegarlModelBeanList(List<IntegralModelBean> integarlModelBeanList) {
		this.integarlModelBeanList = integarlModelBeanList;
	}

	public IntegralModelBean getIntegralModelBean() {
		return integralModelBean;
	}

	public void setIntegralModelBean(IntegralModelBean integralModelBean) {
		this.integralModelBean = integralModelBean;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public List<DictionaryItem> getDictionaryItem() {
		return dictionaryItem;
	}

	public void setDictionaryItem(List<DictionaryItem> dictionaryItem) {
		this.dictionaryItem = dictionaryItem;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
