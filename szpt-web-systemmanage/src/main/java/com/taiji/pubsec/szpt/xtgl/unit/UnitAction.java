package com.taiji.pubsec.szpt.xtgl.unit;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.organization.service.UnitServiceImpl;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
//import com.taiji.pubsec.szpt.jz.wsclient.organization.OrganizationServiceClient;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.ZTreeBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryItemBean;


/**
 * 单位管理
 * 
 * @author xinfan
 *
 */
@Controller("unitAction")
@Scope("prototype")
public class UnitAction extends PageCommonSysManageAction {
	private static final long serialVersionUID = 1L;
	private static final String UNIT_LOGO = "../images/xtgl_icon/ztree-icon_dw.png";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnitServiceImpl.class);
	@Resource(name = "unitService")
	private IUnitService unitService;
	@Resource
	private IOrganizationService organizationServiceImpl;
	@Resource
	private IDictionaryTypeService dictionaryTypeServiceImpl;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IPersonService personService;
	
//	@Resource
//	private OrganizationServiceClient organizationServiceClient;
	
	private List<DictionaryItemBean> typeList;
	private List<ZTreeBean> unitTree;
	private UnitBean unitBean;
	private String unitId;
	
//	/**
//	 * 同步警综里的单位
//	 * 
//	 * @return
//	 */
//	public String synchronousUnit(){
//		String flag = "false";
//		try {
//			organizationServiceClient.syncWithIncrements();
//			flag = "true";
//		} catch (Exception e) {
//			flag = "false";
//			LOGGER.debug("同步单位异常",e);
//		}
//		this.setFlag(flag);
//		return SUCCESS;
//	}

	/**
	 * 初始化单位树
	 * 
	 * @param unitId
	 *            单位Id
	 * @return "success" 成功时返回unitTree 单位树
	 * 
	 */
	public String initUnitTree() {
		unitTree = new ArrayList<ZTreeBean>();
		String currentUnitId = "";
		if (unitId == null) {
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			Map<String, String> orgMap = new HashMap<String, String>(0);
			if (userMap.get("org") != null) {
				orgMap = (Map<String, String>) userMap.get("org");
				ZTreeBean ztbRoot = new ZTreeBean();
				currentUnitId = orgMap.get("id");
				ztbRoot.setId(currentUnitId);
				ztbRoot.setParentId(null);
				ztbRoot.setName(orgMap.get("shortName"));
				ztbRoot.setOpen("true");
				ztbRoot.setIsParent(Constant.UNIT_ISPAR_FALSE);
				ztbRoot.setIcon(UNIT_LOGO);
				unitTree.add(ztbRoot);
			}
		}else{
			currentUnitId = unitId;
		}
		initSubUnitTree(currentUnitId);
		return SUCCESS;
	}

	/**
	 * 查询具体单位信息
	 * 
	 * @param unitBean
	 *            单位bean
	 * @return "success" unitBean 单位Bean
	 */
	public String findUnitById() {
		if (unitBean.getId().equals(Constant.ORGANIZATION_INIT)) {
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			if (userMap.get("org") != null) {
				Map<String, String> mOrg = (Map<String, String>) userMap
						.get("org");
				Unit unit = unitService.findById(mOrg.get("id"));
				unitBean = ModelToBean.unitToUnitBean(unit);
			}
		} else {
			Unit unit = unitService.findById(unitBean.getId());
			unitBean = ModelToBean.unitToUnitBean(unit);
		}
		return SUCCESS;
	}

	/**
	 * 初始化单位页面下拉列表
	 * 
	 * @return "success" 成功时返回 stateList 状态列表 、 typeList 单位属性列表
	 */
	public String initInfo() {
		// 状态初始化
		DictionaryType statusDictionaryType = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.STATUS);
		
		this.setStateList(dictionaryItemService.findDicItemsByType(statusDictionaryType.getId(), null));
		List<DictionaryItem> typeDictionaryItemList = new ArrayList<DictionaryItem>();
		typeDictionaryItemList = dictionaryItemService.findDicItemsByTypeCode(
				Constant.UNIT_PROPERTY, null);
		typeList = new ArrayList<DictionaryItemBean>();
		for(DictionaryItem iterDictionaryItem: typeDictionaryItemList){
			typeList.add(ModelToBean.dictionaryItemToDictionaryItemBean(iterDictionaryItem));
		}
		return SUCCESS;
	}

	/**
	 * 保存单位
	 * 
	 * @param unitBean
	 *            单位Bean
	 * @return "success" 返回 flag、msg、unitBean; 成功时flag为"true"
	 *         失败时flag为"false"；msg 返回失败时的提示信息
	 */
	public String addUnit() {
		if (!unitService.isDistinctUnitName(unitBean.getUnitName())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!unitService.isDistinctUnitCode(unitBean.getUnitNum())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		Unit unit = BeanToModel.unitBeanToUnit(unitBean);
		Date currentTime = new Date();
		unit.setUpdatedTime(currentTime);
		Unit unitTemp = (Unit) organizationServiceImpl
				.findOrganizationById(unitBean.getParUnitNum());
		unitService.createUnit(unit, unitTemp.getId());
		unitBean.setId(unit.getId());
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 删除单位
	 * 
	 * @param unitBean
	 *            单位bean
	 * @return "success" 返回 flag、msg 成功时flag为"true"
	 *         失败时flag为"false"；msg为返回的提示信息
	 */
	public String deleteUnit() {
		try {
			this.setFlag(Boolean.FALSE.toString());
			List<Unit> subUnitList = unitService.findSubUnitsByUnitId(unitBean
					.getId());
			if(subUnitList != null && subUnitList.size() > 1){
				this.setMsg("单位包含下属单位,不允许删除");
				return SUCCESS;
			}
			List<Person> personList = personService
					.findPersonsByUnitAndPersonName(unitBean.getId(), null);
			if (personList != null && !personList.isEmpty()){
				this.setMsg("此单位已分配人员,不允许删除");
				return SUCCESS;
			}
			unitService.deleteUnit(unitBean.getId());
			this.setFlag(Boolean.TRUE.toString());
		} catch (Exception e) {
			LOGGER.debug("已有外键关联，不允许删除", e);
			this.setMsg("此单位已被关联，不允许删除");
			this.setFlag(Boolean.FALSE.toString());
		}
		return SUCCESS;
	}
	/**
	 * 修改单位
	 * 
	 * @param unitBean
	 *            单位Bean
	 * @return "success" 返回 flag、msg 成功时flag为"true" 失败时flag为"false"；msg
	 *         返回失败时的提示信息
	 */
	public String modifyUnit() {
		if (!unitService.isDistinctUnitName(unitBean.getUnitName() , unitBean.getId())) {
			this.setFlag("false");
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!unitService.isDistinctUnitCode(unitBean.getUnitNum(), unitBean.getId()) ) {
			this.setFlag("false");
			this.setMsg("编码重复");
			return SUCCESS;
		}
		Unit unit = BeanToModel.unitBeanToUnit(unitBean);
		unit.setUpdatedTime(new Date());
		Unit unitTemp = (Unit) organizationServiceImpl
				.findOrganizationById(unitBean.getParUnitId());
		if(unitTemp == null){
			unitService.updateUnit(unit, null);
		}else{
			unitService.updateUnit(unit, unitTemp.getId());
		}
		return SUCCESS;
	}

	/**
	 * 停用单位
	 * 
	 * @param unitBean   单位Bean
	 * @return "success"
	 */
	public String disableControl() {
		Unit unitTemp = unitService.findUnitByCode(unitBean.getUnitNum());
		unitService.updateUnitState(unitTemp.getId(), unitBean.getStatus());
		return SUCCESS;
	}

	/**
	 * 启用单位
	 *
	 * @param unitBean
	 *            单位Bean
	 * @return "success"
	 */
	public String enableControl() {
		Unit unitTemp = unitService.findUnitByCode(unitBean.getUnitNum());
		unitService.updateUnitState(unitTemp.getId(), unitBean.getStatus());
		return SUCCESS;
	}

	/**
	 * 构造当前单位的子单位树
	 * 
	 * @param orgId 单位id
	 * @return
	 */
	private void initSubUnitTree(String orgId) {
		String hql = " from Unit where superOrg.id = ? order by code";
		List<Unit> unitTempList = unitService.findAllByParams(hql,
				new Object[] { orgId });
		for (Unit unit : unitTempList) {
			ZTreeBean ztb = new ZTreeBean();
			ztb.setId(unit.getId());
			ztb.setParentId(unit.getSuperOrg() == null ? null : unit
					.getSuperOrg().getId());
			ztb.setName(unit.getShortName());
			List<Unit> sunUnitList = unitService.findSubUnitsByUnitId(unit
					.getId());
			if (sunUnitList != null && sunUnitList.size() > 1) {
				ztb.setIsParent(Constant.UNIT_ISPAR_TRUE);
			} else {
				ztb.setIsParent(Constant.UNIT_ISPAR_FALSE);
			}
			ztb.setIcon(UNIT_LOGO);
			unitTree.add(ztb);
		}
	}
	public List<ZTreeBean> getUnitTree() {
		return unitTree;
	}

	public void setUnitTree(List<ZTreeBean> unitTree) {
		this.unitTree = unitTree;
	}


	public UnitBean getUnitBean() {
		return unitBean;
	}

	public void setUnitBean(UnitBean unitBean) {
		this.unitBean = unitBean;
	}


	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public List<DictionaryItemBean> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DictionaryItemBean> typeList) {
		this.typeList = typeList;
	}
}
