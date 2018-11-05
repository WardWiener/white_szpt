package com.taiji.pubsec.szpt.xtgl.person;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.taiji.pubsec.persistence.dao.Pager;
//import com.taiji.pubsec.szpt.jz.wsclient.user.UserServiceClient;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IAccountService;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.sql.SQLTool;


/**
 * 人员管理
 * 
 * @author xinfan
 *
 */
@Controller("personManageAction")
@Scope("prototype")
public class PersonManageAction extends PageCommonSysManageAction {
	private static final long serialVersionUID = 1L;
	private static final String INVENTEND_PERSON = "虚拟人员";
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonManageAction.class);
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IDictionaryTypeService dictionaryTypeServiceImpl;
	@Resource
	private IPersonService personService;
	@Resource
	private IOrganizationService organizationServiceImpl;
	@Resource 
	private IAccountService accountService;
	
//	@Resource
//	private UserServiceClient userServiceClient;
	
	private List<PersonBean> personAdminBeanList;// 人员对象list
	private List<String> personIdList;// 需删除的人员idlist
	private Pager<PersonBean> personPager;// 后台返回的人员列表对象
	private PersonBean personBean;
	private PersonAdminPageBean personAdminPageBean;// 人员管理页面初始化对象
	private NewPersonPageBean newPersonPageBean;// 新建人员页面初始化对象
	private String orgId;// 组织机构id
	private String orgCode;// 组织机构code
	private Boolean mobility;
	
//	/**
//	 * 同步警综用户
//	 * 
//	 * @return
//	 */
//	public String synchronousUser(){
//		String flag = "false";
//		try {
//			userServiceClient.syncWithIncrements();
//			flag = "true";
//		} catch (Exception e) {
//			flag = "false";
//			LOGGER.debug("同步人员异常",e);
//		}
//		this.setFlag(flag);
//		return SUCCESS;
//	}
	
	/**
	 * 初始化人员管理
	 * @return "success" personAdminPageBean 人员管理bean
	 */
	public String initPersonManagePage() {
		List<BasePersonData> sexList = initPersonStatusList(Constant.SEX);
		List<BasePersonData> statusList = initPersonStatusList(Constant.WORK_STATUS);
		personAdminPageBean = new PersonAdminPageBean();
		personAdminPageBean.setSexList(sexList);
		personAdminPageBean.setStatusList(statusList);
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		String currentId = "";
		if (userMap.get("org") != null) {
			Map<String, String> mOrg = (Map<String, String>) userMap.get("org");
			orgCode = mOrg.get("code");
			currentId = mOrg.get("id");
			personAdminPageBean.setUnitCode(orgCode);
			personAdminPageBean.setUnitId(currentId);
		}
		return SUCCESS;
	}

	/**
	 * 查找人员
	 * 
	 * @param personBean
	 *            人员对象Bean
	 * @param start
	 *            开始项
	 * @param length
	 *            页面展示记录的数
	 * @return "success" List<PersonBean> personAdminBeanList 人员的list对象
	 */
	public String searchPersonList() {
		personPager = this.findAllPersonByConditions(personBean, this.getStart(),
				this.getLength());
		this.setTotalNum(personPager.getTotalNum());
		personAdminBeanList = personPager.getPageList();
		return SUCCESS;
	}

	/**
	 * 保存人员
	 * @param personBean
	 *            人员bean
	 * @return "success" 返回flag、msg 成功时flag为"true",失败时flag为"false",msg为提示信息
	 */
	public String savePerson() {
		if (!personService.isDistinctPersonCode(personBean
				.getPoliceNo(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("警号重复");
			return SUCCESS;
		}
		Person newPerson = BeanToModel.personBeanToPerson(personBean);
		Organization org = organizationServiceImpl
				.findOrganizationById(personBean.getUnitId());
		newPerson.setId(null);
		personService.createPerson(newPerson, org.getId());
		personBean.setPersonId(newPerson.getId());
		this.setFlag(Boolean.TRUE.toString());
		this.setMsg("保存成功");
		return SUCCESS;
	}

	/**
	 * 更新人员
	 *
	 * @param personBean 人员Bean
	 * @return "success" 返回flag、msg
	 *         成功时flag为"true",msg为"提示信息"，失败时flag为null、msg为null
	 */
	public String updatePerson() {
		if (!personService.isDistinctPersonCode(personBean
				.getPoliceNo(), personBean.getPersonId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("警号重复");
			return SUCCESS;
		}
		Person newPerson = null;
		if(mobility){
			newPerson = personService.findById(personBean.getPersonId());
		}else{
			newPerson = BeanToModel.personBeanToPerson(personBean);
		}
		Organization org = organizationServiceImpl
				.findOrganizationById(personBean.getUnitId());
		personService.updatePerson(newPerson, org.getId());
		this.setFlag(Boolean.TRUE.toString());
		this.setMsg("修改成功");
		return SUCCESS;
	}
	/**
	 * 删除人员
	 * @param personIdList
	 *            删除的Id List
	 * @return "success"
	 */
	public String deletePerson() {
		for (int i = 0; i < personIdList.size(); i++) {
			Person isPersonDelete = personService.findById(personIdList.get(i));
			if(isPersonDelete.getAccount() == null){
				personService.deletePerson(personIdList.get(i));
			}else{
				this.setMsg(isPersonDelete.getName()+"已分配账户,不可删除");
				this.setFlag(Boolean.FALSE.toString());
			}
		}
		return SUCCESS;
	}
	/**
	 * 初始化新增人员页面的列表对象
	 * 
	 * @return "success" newPersonPageBean 新增人员列表对象
	 */
	public String initNewPersonPage() {
		List<BasePersonData> sexList = this.initPersonStatusList(Constant.SEX);
		// 初始化在职状态
		List<BasePersonData> statusList = this
				.initPersonStatusList(Constant.WORK_STATUS);
		// 初始化职务
		List<BasePersonData> jobList = this.initPersonStatusList(Constant.JOB);
		// 初始化民族
		List<BasePersonData> nationalityList = this
				.initPersonStatusList(Constant.NATIONALITY);
		// 初始化政治面貌
		List<BasePersonData> politicsTypeList = this
				.initPersonStatusList(Constant.POLITICSTYPE);
		// 初始化学历
		List<BasePersonData> diplomaList = this
				.initPersonStatusList(Constant.DIPLOMA);
		newPersonPageBean = new NewPersonPageBean();
		newPersonPageBean.setSexList(sexList);
		newPersonPageBean.setStatusList(statusList);
		newPersonPageBean.setJobList(jobList);
		newPersonPageBean.setNationalityList(nationalityList);
		newPersonPageBean.setPoliticsTypeList(politicsTypeList);
		newPersonPageBean.setDiplomaList(diplomaList);
		return SUCCESS;
	}

	/**
	 * 检查人员是否已分配账户
	 * @param personBean 人员Bean
	 * @return "success" 返回flag  人员已分配返回false 否则返回true
	 */
	public String checkPersonHasAccount() {
		Person checkPersonAccount = personService.findById(personBean.getPersonId());
		if (checkPersonAccount == null) {
			this.setFlag(Boolean.TRUE.toString());
			return SUCCESS;
		} else {
			if (checkPersonAccount.getAccount() == null) {
				this.setFlag(Boolean.TRUE.toString());
				return SUCCESS;
			} else {
				this.setFlag(Boolean.FALSE.toString());
				this.setMsg("人员已分配");
				return SUCCESS;
			}
		}

	}

	/********************************************************************************************************************
	 * 以下是私有的查询方法
	 * 
	 * ******************************************************************************************************************/
	/**
	 * 初始化人员的各个下拉列表
	 * 
	 * @param type
	 *            要初始化的类型
	 * @return List<BasePersonData> 人员基本信息List对象
	 */
	private List<BasePersonData> initPersonStatusList(String type) {
		List<DictionaryItem> dictionaryItemTempList = dictionaryItemService
				.findDicItemsByTypeCode(type, null);
		List<BasePersonData> newBaseDateList = new ArrayList<BasePersonData>();
		for (DictionaryItem di : dictionaryItemTempList) {
			BasePersonData newBasePersonData = new BasePersonData();
			newBasePersonData.setCode(di.getCode());
			newBasePersonData.setName(di.getName());
			newBaseDateList.add(newBasePersonData);
		}
		return newBaseDateList;
	}

	/**
	 * 根据不同条件查询本人员
	 * 
	 * @param personBean
	 *            人员Bean
	 * @param pageStart
	 *            页面开始项
	 * @param pageSize
	 *            页面展示条数
	 * @return Pager<PersonBean> 人员的分页对象
	 */
	private Pager<PersonBean> findAllPersonByConditions(PersonBean personBean,
			int pageStart, int pageSize) {
		if (StringUtils.isEmpty(personBean.getUnitCode())) {
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			if (userMap.get("org") != null) {
				Map<String, String> mOrg = (Map<String, String>) userMap.get("org");
				orgCode = mOrg.get("code");
				personBean.setUnitCode(orgCode);
			}
		}
		String hql = "from Person  where 1 = 1 and name != ?";
		List<Object> param = new ArrayList<Object>();
		param.add(INVENTEND_PERSON);
		if (!StringUtils.isEmpty(personBean.getUnitCode())) {
			Organization org = organizationServiceImpl
					.findOrganizationByCode(personBean.getUnitCode());
			hql = hql
					+ " and (organization.id = ? or organization.superOrg.id=?)";
			param.add(org.getId());
			param.add(org.getId());
		}
		if (!StringUtils.isEmpty(personBean.getName())) {
			hql += " and name like ?";
			hql = SQLTool.SQLAddEscape(hql);
			param.add("%" + SQLTool.SQLSpecialChTranfer(personBean.getName())
					+ "%");
		}
		if (!StringUtils.isEmpty(personBean.getSex())) {
			hql += " and sex = ?";
			param.add(personBean.getSex());
		}
		if (!StringUtils.isEmpty(personBean.getStatus())) {
			hql += " and status = ?";
			param.add(personBean.getStatus());
		}
		hql += " order by code";
		Pager<PersonBean> result = new Pager<PersonBean>();
		int pageNo = pageStart / pageSize;
		Pager<Person> personPagerTemp = personService.findByPage(hql,
				param.toArray(), pageNo, pageSize);
		result.setTotalNum(personPagerTemp.getTotalNum());
		List<PersonBean> personBeans = new ArrayList<PersonBean>();
		for (int i = 0; i < personPagerTemp.getPageList().size(); i++) {
			Person personTemp = personPagerTemp.getPageList().get(i);
			PersonBean personBeanTemp = initPersonBean(personTemp);
			personBeans.add(personBeanTemp);
		}
		result.setPageList(personBeans);
		personPager = result;
		return result;
	}

	/**
	 * 初始化人员Bean 将对应的字典项编码转化为字典项名称
	 * @param personTemp
	 * @return PersonBean
	 */
	private PersonBean initPersonBean(Person personTemp) {
		PersonBean personBeanTemp = ModelToBean.personToPersonBean(personTemp);
		DictionaryType tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.WORK_STATUS);
		DictionaryItem dictStatus = dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(), personBeanTemp.getStatus(),null);
		               
		tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.SEX);
		DictionaryItem dictSex = dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(),personBeanTemp.getSex(),null);
		
		tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.DIPLOMA);
		DictionaryItem dictDiploma = dictionaryItemService
				.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(),personBeanTemp.getDiploma(),null);
		
		tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.NATIONALITY);
		DictionaryItem dictNationality = dictionaryItemService
				.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(),personBeanTemp.getNationality(),null);
		
		tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.POLITICSTYPE);
		DictionaryItem dictPoliticsType = dictionaryItemService
				.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(),personBeanTemp.getPoliticsType(),null);
		
		tmpDictionary = dictionaryTypeServiceImpl.findDicTypeByCode(Constant.JOB);
		DictionaryItem diJob = dictionaryItemService
				.findDictionaryItemByDicTypeAndItemCode(tmpDictionary.getId(),personBeanTemp.getJob(),null);
		
		personBeanTemp.setDiploma(dictDiploma == null ? "" : dictDiploma.getName());
		personBeanTemp.setNationality(null == dictNationality ? "" : dictNationality.getName()); 
		personBeanTemp.setPoliticsType(dictPoliticsType == null ? "" : dictPoliticsType.getName());
		if (dictStatus != null) {
			personBeanTemp.setStatus(dictStatus.getName());
		}
		personBeanTemp.setSex(dictSex == null ? "" : dictSex.getName());
		personBeanTemp.setJob(diJob == null ? "" : diJob.getName());
	
		return personBeanTemp;
	}

	public Pager<PersonBean> getPersonPager() {
		return personPager;
	}

	public void setPersonPager(Pager<PersonBean> personPager) {
		this.personPager = personPager;
	}

	public PersonAdminPageBean getPersonAdminPageBean() {
		return personAdminPageBean;
	}

	public void setPersonAdminPageBean(PersonAdminPageBean personAdminPageBean) {
		this.personAdminPageBean = personAdminPageBean;
	}

	public NewPersonPageBean getNewPersonPageBean() {
		return newPersonPageBean;
	}

	public void setNewPersonPageBean(NewPersonPageBean newPersonPageBean) {
		this.newPersonPageBean = newPersonPageBean;
	}

	public List<PersonBean> getPersonAdminBeanList() {
		return personAdminBeanList;
	}

	public void setPersonAdminBeanList(List<PersonBean> personAdminBeanList) {
		this.personAdminBeanList = personAdminBeanList;
	}

	public PersonBean getPersonBean() {
		return personBean;
	}

	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}

	public List<String> getPersonIdList() {
		return personIdList;
	}

	public void setPersonIdList(List<String> personIdList) {
		this.personIdList = personIdList;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Boolean getMobility() {
		return mobility;
	}

	public void setMobility(Boolean mobility) {
		this.mobility = mobility;
	}
}
