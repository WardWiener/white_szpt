package com.taiji.pubsec.szpt.xtgl.community;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.organization.service.UnitServiceImpl;
import com.taiji.pubsec.common.tools.datetime.DateTimeUtil;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.service.ICommunityService;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.PageCommonAction;
import com.taiji.pubsec.szpt.xtgl.community.bean.CommunityBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryItemBean;

/**
 * 社区信息维护action
 * @author sunjd
 *
 */
@Controller("communityAction")
@Scope("prototype")
public class CommunityAction extends PageCommonAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UnitServiceImpl.class);
	
	
	
	/**
	 * 社区服务
	 */
	@Resource(name = "communityService")
	private ICommunityService communityService;
	
	/**
	 * 单位服务
	 */
	@Resource(name = "unitService")
	private IUnitService unitService;
	//字典项
	@Resource(name = "dictionaryItemService")
	private IDictionaryItemService dictionaryItemService;

	private String msg;				//返回信息
	private boolean flag = false;			//返回成功、失败状态
	private String communityId ;		//删除时用的id
	private List<CommunityBean> communityBeanList;	//社区BeanList
	private CommunityBean commBean ;	//社区Bean
	private String name;
	private String unitId;
	private List<DictionaryItemBean> dictionaryItemBeanList;
	
	/**
	 * 分页查询社区
	 * @return
	 */
	public String findAllCommunity(){
		Pager<Community> c = communityService.findAllCommunity(name, unitId, this.getStart()/this.getLength(), this.getLength());
		communityBeanList = new ArrayList<CommunityBean>();
		for(Community comm : c.getPageList()){
			communityBeanList.add(this.CommunityTransitionToCommunityBean(comm));
		}
		this.setTotalNum(c.getTotalNum());
		return SUCCESS;
	}
	
	/**
	 * 创建社区
	 * @return
	 */
	public String creadCommunity(){
		Community code = communityService.findCommunityByCode(commBean.getCode());
		Community name = communityService.findCommunityByName(commBean.getName());
		if(code != null){
			msg = "社区编码重复";
			return SUCCESS;
		}
		if(name != null){
			msg = "社区名称重复";
			return SUCCESS;
		}
		communityService.createCommunity(this.communityBeanTransitionToCommunity(commBean));
		msg = "新建社区信息成功";
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 更新社区
	 * @return
	 */
	public String uodateCommunity(){
		Community community = communityService.findCommunityById(commBean.getId());
		Community code = communityService.findCommunityByCode(commBean.getCode());
		Community name = communityService.findCommunityByName(commBean.getName());
		if(code != null && !community.getCode().equals(commBean.getCode())){
			msg = "社区编码重复";
			return SUCCESS;
		}
		if(name != null && !community.getName().equals(commBean.getName())){
			msg = "社区名称重复";
			return SUCCESS;
		}
		communityService.updateCommunity(this.communityBeanTransitionToCommunity(commBean));
		msg = "更新社区信息成功";
		flag = true;
		return SUCCESS;
	}
	
	/**
	 * 根据社区id查询社区实体
	 * @return
	 */
	public String findCommunityById(){
		this.CommunityTransitionToCommunityBean(communityService.findCommunityById(commBean.getId()));
		return SUCCESS;
	}
	
	/**
	 * 删除社区
	 * @return
	 */
	public String deleteCommunityById(){
		try {
			communityService.deleteCommunity(communityId);
			msg = "删除成功";
			flag = true;
		} catch (DataIntegrityViolationException e) {
			LOGGER.debug("删除社区时外键异常：", e);
			msg = "删除失败，社区正在使用不可以删除";
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化大社区
	 * @return	SUCCESS
	 */
	public String initSelect(){
		List<DictionaryItem> dictionaryItem = dictionaryItemService.findDicItemsByTypeCode(Constant.SQBM, Constant.STATUS_START);
		dictionaryItemBeanList = new ArrayList<DictionaryItemBean>();
		for(DictionaryItem d : dictionaryItem){
			DictionaryItemBean dictionaryItemBean = new DictionaryItemBean();
			dictionaryItemBean.setId(d.getId());
			dictionaryItemBean.setName(d.getName());
			dictionaryItemBeanList.add(dictionaryItemBean);
		}
		return SUCCESS;
	}
	
	/**
	 * 私有方法  社区Bean转换为社区实体
	 * @param communityBean 社区bean
	 * @return	community 社区实体
	 */
	private Community communityBeanTransitionToCommunity(CommunityBean communityBean){
		Community community = new Community();
		if(StringUtils.isNoneEmpty(communityBean.getId())){
			community.setId(communityBean.getId());
		}
		community.setCode(communityBean.getCode());
		community.setName(communityBean.getName());
		community.setUnitId(communityBean.getUnitId());
		community.setUpdateTime(new Date());
		community.setBigCommunityId(communityBean.getBigCommunityId());
		return community;
	}
	
	/**
	 * 私有方法  社区实体转换为社区bean
	 * @param community 社区实体
	 * @return	communityBean 社区bean
	 */
	private CommunityBean CommunityTransitionToCommunityBean(Community community){
		CommunityBean communityBean = new CommunityBean();
		communityBean.setCode(community.getCode());
		communityBean.setId(community.getId());
		communityBean.setName(community.getName());
		communityBean.setUnitId(community.getUnitId());
		communityBean.setUpdateTime(DateTimeUtil.formatDateTime(community.getUpdateTime(), Constant.DATE_FORMAT));
		Unit unit = unitService.findById(community.getUnitId());
		if(unit != null){
			communityBean.setUnitName(unit.getShortName());
		}
		if(StringUtils.isNotEmpty(community.getBigCommunityId())){
			DictionaryItem dictionaryItem = dictionaryItemService.findById(community.getBigCommunityId());
			if(dictionaryItem != null){
				communityBean.setBigCommunity(dictionaryItem.getName());
				communityBean.setBigCommunityId(dictionaryItem.getId());
			}
		}
		return communityBean;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<CommunityBean> getCommunityBeanList() {
		return communityBeanList;
	}

	public void setCommunityBeanList(List<CommunityBean> communityBeanList) {
		this.communityBeanList = communityBeanList;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommunityBean getCommBean() {
		return commBean;
	}
	
	public void setCommBean(CommunityBean commBean) {
		this.commBean = commBean;
	}

	public List<DictionaryItemBean> getDictionaryItemBeanList() {
		return dictionaryItemBeanList;
	}

	public void setDictionaryItemBeanList(
			List<DictionaryItemBean> dictionaryItemBeanList) {
		this.dictionaryItemBeanList = dictionaryItemBeanList;
	}
	
	
	

}

