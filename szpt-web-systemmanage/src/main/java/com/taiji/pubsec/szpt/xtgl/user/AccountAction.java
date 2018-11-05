package com.taiji.pubsec.szpt.xtgl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang.StringUtils;

import com.taiji.pubsec.persistence.dao.Pager;
//import com.taiji.pubsec.szpt.jz.wsclient.authority.AuthorityServiceClient;
import com.taiji.pubsec.szpt.security.GuiYangJingZongCustomizedUsernamePasswordAuthenticationFilter;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.ZTreeBean;
import com.taiji.pubsec.szpt.xtgl.person.PersonBean;
import com.taiji.pubsec.szpt.xtgl.role.RoleBean;
import com.taiji.pubsec.businesscomponents.authority.model.Role;
import com.taiji.pubsec.businesscomponents.authority.model.RoleSubject;
import com.taiji.pubsec.businesscomponents.authority.service.IRoleService;
import com.taiji.pubsec.businesscomponents.authority.service.IRoleSubjectService;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IAccountService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.sql.SQLTool;

/**
 * 账户Action
 * 
 * @author xinfan
 *
 */
@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends PageCommonSysManageAction {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountAction.class);

	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 15;
	@Resource
	private IRoleService roleServiceImpl;
	@Resource
	private IAccountService accountService;
	@Resource
	private IRoleSubjectService roleSubjectServiceImpl;
	@Resource
	private IDictionaryTypeService dictionaryTypeServiceImpl;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IUnitService unitServiceImpl;
	@Resource
	private IPersonService personServiceImpl;
	private List<AccountBean> accountList;
	private List<DictionaryItem> stateList;
	private List<PersonBean> personBeans;// 人员信息
	private List<ZTreeBean> allRoles = new ArrayList<ZTreeBean>();// 所有角色ztree
	private List<ZTreeBean> accountRoles = new ArrayList<ZTreeBean>();
	private Pager<AccountBean> accountBeanPager = new Pager<AccountBean>();
	private List<ZTreeBean> cellTree = new ArrayList<ZTreeBean>();// 指挥单元ztree

	public List<ZTreeBean> getCellTree() {
		return cellTree;
	}

	public void setCellTree(List<ZTreeBean> cellTree) {
		this.cellTree = cellTree;
	}

	private AccountBean accountBean;// 帐号信息
	private Account account;
	private String id;
	private String ids;// 多个id拼接成的字符串，以逗号分隔
	private String password;

//	@Resource
//	private AuthorityServiceClient authServiceClient;

	/**
	 * 查询账户
	 * 
	 * @param accountBean
	 *            账户Bean
	 * @param start
	 *            每页开始的条数
	 * @param length
	 *            每页显示的记录数
	 * @return accountBeanPager 账户Bean的分页对象
	 */
	public String queryAccountByConditions() {
		accountBeanPager = findAllAccountByCondictions(accountBean,
				this.getStart(), this.getLength());
		return SUCCESS;
	}

	/**
	 * 初始化人员树
	 * 
	 * @param accountBean
	 * @return "success" 成功时返回 cellTree 人员树
	 */
	public String initPersonTree() {
		int num = accountBean.getNum();
		num--;
		List<PersonBean> personLst = findNoAccountPerson(accountBean);
		if (personLst != null) {
			this.setTotalNum(personLst.size() / DEFAULT_PAGE_SIZE
					+ ((personLst.size() % DEFAULT_PAGE_SIZE > 0) ? 1 : 0));
			for (int i = num * DEFAULT_PAGE_SIZE; i < ((num + 1)
					* DEFAULT_PAGE_SIZE < personLst.size() ? (num + 1)
					* DEFAULT_PAGE_SIZE : personLst.size()); i++) {
				PersonBean temp = personLst.get(i);
				ZTreeBean ztbTemp = new ZTreeBean();
				ztbTemp.setId(temp.getPersonId());
				ztbTemp.setParentId(null);
				ztbTemp.setName(temp.getName());
				ztbTemp.setIcon("../images/xtgl_icon/ztree-icon_ry.png");
				ztbTemp.setIsParent(Boolean.FALSE.toString());
				cellTree.add(ztbTemp);
			}
		}
		return SUCCESS;
	}

	/**
	 * 保存账户
	 * 
	 * @param accountBean
	 *            账户bean
	 * @return "success" 返回flag msg; 成功时flag为true,失败时flag为false；msg为返回的提示信息
	 */
	public String saveAccount() {
		accountBean.setId(null);
		String xqlname = "from Account where accountName=?";
		Account accountTemp = accountService.findByParams(xqlname,
				new Object[] { accountBean.getAccountName() });
		if (accountTemp != null) {
			this.setMsg("名称重复");
			this.setFlag(Boolean.FALSE.toString());
			return SUCCESS;
		}
		Account acc = BeanToModel.accountBeanAccount(accountBean);
		acc.setUpdatedTime(new Date());
		if (accountBean.getPersonBean() != null) {
			Person person = personServiceImpl.findById(accountBean
					.getPersonBean().getPersonId());
			acc.setPerson(person);
		}
		accountService.createAccount(acc, accountBean.getPersonBean()
				.getPersonId());
		List<Role> roles = new ArrayList<Role>();
		if (accountBean.getRoleBeans() != null) {
			for (RoleBean roleBeantemp : accountBean.getRoleBeans()) {
				Role roleTemp = BeanToModel.roleBeanToRole(roleBeantemp);
				roles.add(roleTemp);
			}
			RoleSubject roleSubject = new RoleSubject();
			roleSubject.setControllSubjectId(acc.getId());
			roleSubject.setType(Account.class.getName());
			roleSubjectServiceImpl.createRoleSubject(roleSubject, roles);
//			try {
//				for (Role role : roles) {
//					authServiceClient.authorizeUser(acc.getPerson()
//							.getIdNumber(), role.getRoleCode());
//				}
//			} catch (Exception e) {
//				LOGGER.error("警综平台授权失败：用户{}-{}", acc.getPerson().getIdNumber(),
//						acc.getPerson().getName());
//				LOGGER.error("", e);
//			}
		}
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 重置密码
	 * 
	 * @param ids为多个id拼接成的字符串
	 *            ，以逗号分隔
	 * 
	 * @return "success"
	 */
	public String resetPassWord() {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			Account accountTemp = accountService.findById(idArray[i]);
			accountTemp.setPassword(password);
			accountService.updateAccount(accountTemp, accountTemp.getPerson()
					.getId());
		}
		return SUCCESS;
	}

	/**
	 * 更新账户
	 * 
	 * @param accountBean
	 *            账户bean
	 * @param subjectType
	 *            角色主体类型
	 * @return "success" 返回flag msg; 成功时flag为true,失败时flag为false；msg为返回的提示信息
	 */
	public String updateAccount() {
		String xqlname = "from Account where accountName = ?";
		Account accountTempName = accountService.findByParams(xqlname,
				new Object[] { accountBean.getAccountName() });
		if (accountTempName != null
				&& !(accountTempName.getId().equals(accountBean.getId()))) {
			this.setMsg("名称重复");
			this.setFlag(Boolean.FALSE.toString());
			return SUCCESS;
		}
		Account accountTemp = BeanToModel.accountBeanAccount(accountBean);
		if (accountBean.getPersonBean() != null) {
			Person person = personServiceImpl.findById(accountBean
					.getPersonBean().getPersonId());
			accountTemp.setPerson(person);
		}
		accountService.updateAccount(accountTemp, accountBean.getPersonBean()
				.getPersonId());
		RoleSubject roleSubject = roleSubjectServiceImpl
				.findRoleSubjectBySubjectIdAndType(accountTemp.getId(),
						Account.class.getName());
		List<Role> roleLists = new ArrayList<Role>();
		if (accountBean.getRoleBeans() != null) {
			for (RoleBean roleBeantemp : accountBean.getRoleBeans()) {
				Role roleTemp = BeanToModel.roleBeanToRole(roleBeantemp);
				roleLists.add(roleTemp);
			}
		}
		if (roleSubject != null) {
			List<Role> oldRoles = roleSubjectServiceImpl.findRolesBySubjectIdAndType(accountTemp.getId(),
					Account.class.getName(), null);
			roleSubjectServiceImpl.updateRoleSubject(roleSubject, roleLists);
//			try {
//				for(Role role : oldRoles) {
//					authServiceClient.revokeUserAuthorization(accountTemp.getPerson().getIdNumber(), role.getRoleCode());
//				}
//				for (Role role : roleLists) {
//					authServiceClient.authorizeUser(accountTemp.getPerson()
//							.getIdNumber(), role.getRoleCode());
//				}
//			} catch (Exception e) {
//				LOGGER.error("警综平台取消授权失败：用户{}-{}", accountTemp.getPerson().getIdNumber(),
//						accountTemp.getPerson().getName());
//				LOGGER.error("", e);
//			}
			
			
		} else {
			RoleSubject roleSubject1 = new RoleSubject();
			roleSubject1.setControllSubjectId(accountTemp.getId());
			roleSubject1.setType(Account.class.getName());
			roleSubjectServiceImpl.createRoleSubject(roleSubject1, roleLists);
//			try {
//				for (Role role : roleLists) {
//					authServiceClient.authorizeUser(accountTemp.getPerson()
//							.getIdNumber(), role.getRoleCode());
//				}
//			} catch (Exception e) {
//				LOGGER.error("警综平台授权失败：用户{}-{}", accountTemp.getPerson().getIdNumber(),
//						accountTemp.getPerson().getName());
//				LOGGER.error("", e);
//			}
		}
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 角色树
	 * 
	 * @param id
	 *            账户id
	 * @return accountRoles 用户所拥有的角色
	 */
	public String roleTree() {
		if (!StringUtils.isEmpty(id)) {
			accountBean = this.findAccountBeanById(id);// 根据id查询帐号信息
			// 添加帐号角色Ztree(普通角色以及单位角色)
			if (accountBean.getRoleBeans() != null
					&& accountBean.getRoleBeans().size() > 0) {
				for (RoleBean roleBean : accountBean.getRoleBeans()) {
					ZTreeBean bean = new ZTreeBean();
					bean.setId(roleBean.getRoleCode() == null ? "" : roleBean
							.getRoleCode());
					bean.setParentId(null);
					bean.setName(roleBean.getRoleName());
					accountRoles.add(bean);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 删除账户
	 * 
	 * @param ids要删除的id
	 * @return "success" 返回flag; 成功时flag为true
	 */
	public String delete() {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			RoleSubject delRoleSubject = roleSubjectServiceImpl
					.findRoleSubjectBySubjectIdAndType(idArray[i],
							Account.class.getName());
			if (delRoleSubject != null) {
				roleSubjectServiceImpl
						.deleteRoleSubject(delRoleSubject.getId());
			}
			accountService.deleteAccount(idArray[i]);
		}
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 停用账户
	 * 
	 * @param 账户id
	 * @return flag 成功返回true
	 */
	public String stop() {
		Account accountTemp = accountService.findById(id);
		accountTemp.setStatus(Constant.STATUS_STOP);
		accountService.updateAccount(accountTemp, accountTemp.getPerson()
				.getId());
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 启用账户
	 * 
	 * @param 账户id
	 * @return falg 成功时返回true
	 */
	public String startAccount() {
		Account accountTemp = accountService.findById(id);
		accountTemp.setStatus(Constant.STATUS_START);
		accountService.updateAccount(accountTemp, accountTemp.getPerson()
				.getId());
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 
	 * @param accountBean
	 *            .id 账户id
	 * @return accountBean 账户Bean allRoles所有角色 accountRoles本用户分配的角色
	 */
	public String initAddPageInfo() {
		String idTemp = accountBean == null ? "" : accountBean.getId();
		if (!StringUtils.isEmpty(idTemp)) {
			accountBean = findAccountBeanById(accountBean.getId());
			for (RoleBean roleBean : accountBean.getRoleBeans()) {
				ZTreeBean bean = new ZTreeBean();
				bean.setId(roleBean.getId());
				bean.setParentId(null);
				bean.setName(roleBean.getRoleName());
				accountRoles.add(bean); // 普通角色
			}
		}
		List<Role> allRollList = roleServiceImpl.findAll();
		List<RoleBean> allroleBeanLists = new ArrayList<RoleBean>();
		for (Role role : allRollList) {
			RoleBean roleBeanTemp = ModelToBean.roleToRoleBean(role);
			allroleBeanLists.add(roleBeanTemp);
		}
		for (RoleBean roleBean : allroleBeanLists) {
			ZTreeBean bean = new ZTreeBean();
			bean.setId(roleBean.getId());
			bean.setParentId(null);
			bean.setName(roleBean.getRoleName());
			allRoles.add(bean);
		}
		return SUCCESS;
	}

	/****************************************************************************************************************
	 * 私有方法
	 * 
	 ****************************************************************************************************************/
	/**
	 * 根据不同条件查询账户
	 * 
	 * @param queryBean
	 *            accountBean对象
	 * @param pageStart
	 *            开始项
	 * @param pageSize
	 *            每页展示的条数
	 * @return Pager<AccountBean> accountBean的分页对象
	 */
	private Pager<AccountBean> findAllAccountByCondictions(
			AccountBean queryBean, int pageStart, int pageSize) {
		Pager<AccountBean> result = new Pager<AccountBean>();
		if (queryBean == null || StringUtils.isEmpty(queryBean.getUnitId())) {
			if (queryBean == null) {
				queryBean = new AccountBean();
			}
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			if (userMap.get("org") != null) {
				Map<String, String> mOrg = (Map<String, String>) userMap
						.get("org");
				String currentId = mOrg.get("id");
				queryBean.setUnitId(currentId);
			}
		}
		List<Object> param = new ArrayList<Object>();
		String hql = initSql(param, queryBean);
		Pager<Account> pager = accountService.findByPage(hql, param.toArray(),
				pageStart / pageSize, pageSize);
		result.setTotalNum(pager.getTotalNum());
		List<AccountBean> accountBeans = new ArrayList<AccountBean>();
		for (int i = 0; i < pager.getPageList().size(); i++) {
			AccountBean accountBeanTemp = constructAccountBean(pager
					.getPageList().get(i));
			accountBeans.add(accountBeanTemp);
		}
		result.setPageList(accountBeans);
		return result;
	}

	/**
	 * 构造sql
	 * 
	 * @param param
	 *            参数List
	 * @param queryBean
	 *            要查询的accountBean
	 * @return hql sql语句
	 */
	private String initSql(List<Object> param, AccountBean queryAccountBean) {
		String hql = " from Account  where 1 = 1 ";// 条件部分
		if (!StringUtils.isEmpty(queryAccountBean.getUnitId())) {
			hql = hql
					+ " and  (person.organization.id=? or person.organization.superOrg.id=?) ";
			param.add(queryAccountBean.getUnitId());
			param.add(queryAccountBean.getUnitId());
		}

		// 帐号名
		if (!StringUtils.isEmpty(queryAccountBean.getAccountName())) {
			hql += " and accountName like ?";
			hql = SQLTool.SQLAddEscape(hql);
			param.add("%"
					+ SQLTool.SQLSpecialChTranfer(queryAccountBean
							.getAccountName()) + "%");
		}
		if (!StringUtils.isEmpty(queryAccountBean.getStatus())) {
			hql += " and status = ?";
			param.add(queryAccountBean.getStatus());
		}
		// 开始时间
		if (queryAccountBean.getStartDate() != null) {
			hql = hql + " and startDate <= ?";
			param.add(queryAccountBean.getStartDate());
		}
		// 结束时间
		if (queryAccountBean.getEndDate() != null) {
			hql = hql + " and endDate >= ?";
			param.add(queryAccountBean.getEndDate());
		}
		return hql;
	}

	/**
	 * 查找没有分配用户的人员
	 * 
	 * @param accountBean
	 *            账户bean
	 * @return personBeanTemps 人员List对象
	 */
	private List<PersonBean> findNoAccountPerson(AccountBean accountBean) {
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		String currentId = "";
		List<PersonBean> personBeanTemps = new ArrayList<PersonBean>();
		if (userMap.get("org") != null) {
			Map<String, String> mOrg = (Map<String, String>) userMap.get("org");
			currentId = mOrg.get("id");
		} else {
			currentId = accountBean.getUnitId();
		}
		personBeanTemps = findAllPersonByUnitId(accountBean.getUnitId(),
				accountBean.getPersonName());

		return personBeanTemps;
	}

	/**
	 * 根据ID查找账户
	 * 
	 * @param id
	 *            账户id
	 * @return accountBeanTemp 账户Bean
	 */
	private AccountBean findAccountBeanById(String id) {
		Account accountTemp = accountService.findById(id);
		AccountBean accountBeanTemp = constructAccountBean(accountTemp);
		List<Role> roleLists = roleSubjectServiceImpl
				.findRolesBySubjectIdAndType(accountTemp.getId(),
						Account.class.getName(), null);
		List<RoleBean> roleBeans = new ArrayList<RoleBean>();
		for (Role role : roleLists) {
			RoleBean roleBeanTemp = ModelToBean.roleToRoleBean(role);
			roleBeans.add(roleBeanTemp);
		}
		accountBeanTemp.setRoleBeans(roleBeans);
		return accountBeanTemp;
	}

	/**
	 * 根据单位Id查找单位下所有人
	 * 
	 * @param unitId
	 *            单位id
	 * @param name
	 *            人名
	 * @return personBeanTemps 人员bean的List对象
	 */
	private List<PersonBean> findAllPersonByUnitId(String unitId, String name) {
		List<PersonBean> personBeanTemps = new ArrayList<PersonBean>();
		List<Unit> unitListTemps = unitServiceImpl.findSubUnitsByUnitId(unitId);
		if (!unitListTemps.isEmpty()) {
			for (Unit unitTemp : unitListTemps) {
				List<String> paramSub = new ArrayList<String>();
				String hqlsub = "from Person p where p.id not in (select person from Account) and p.name!='虚拟人员' ";// 条件部分
				hqlsub = hqlsub + " and organization.id=?";
				paramSub.add(unitTemp.getId());
				if (!StringUtils.isEmpty(name)) {
					hqlsub = hqlsub + "and name like ? ";
					hqlsub = SQLTool.SQLAddEscape(hqlsub);
					paramSub.add("%" + SQLTool.SQLSpecialChTranfer(name) + "%");
				}
				List<Person> personList = personServiceImpl.findAllByParams(
						hqlsub, paramSub.toArray());
				for (Person personTemp : personList) {
					PersonBean personBeanTemp = ModelToBean
							.personToPersonBean(personTemp);
					personBeanTemps.add(personBeanTemp);
				}
			}
		}
		return personBeanTemps;
	}

	/**
	 * 构造AccountBean
	 * 
	 * @param account
	 *            实体对象
	 * @return accountBeanTemp 返回AccountBean对象
	 */
	private AccountBean constructAccountBean(Account account) {
		AccountBean accountBeanTemp = new AccountBean();
		if (account == null) {
			return accountBeanTemp;
		}
		accountBeanTemp = ModelToBean.accountToAccountBean(account);

		if (account.getPerson() != null) {

			accountBeanTemp.setUnitId(account.getPerson().getOrganization()
					.getId());
			accountBeanTemp.setUnitCode(account.getPerson().getOrganization()
					.getCode());
			accountBeanTemp.setUnitName(account.getPerson().getOrganization()
					.getShortName());
		}
		if (!StringUtils.isEmpty(account.getStatus())) {
			DictionaryType statueDictionaryType = dictionaryTypeServiceImpl
					.findDicTypeByCode(Constant.STATUS);
			DictionaryItem item = dictionaryItemService
					.findDictionaryItemByDicTypeAndItemCode(
							statueDictionaryType.getId(), account.getStatus(),
							null);
			accountBeanTemp.setStatus(item == null ? "" : item.getName());
		}
		return accountBeanTemp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountBean getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(AccountBean accountBean) {
		this.accountBean = accountBean;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<AccountBean> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountBean> accountList) {
		this.accountList = accountList;
	}

	public List<DictionaryItem> getStateList() {
		return stateList;
	}

	public void setStateList(List<DictionaryItem> stateList) {
		this.stateList = stateList;
	}

	public List<PersonBean> getPersonBeans() {
		return personBeans;
	}

	public void setPersonBeans(List<PersonBean> personBeans) {
		this.personBeans = personBeans;
	}

	public List<ZTreeBean> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<ZTreeBean> allRoles) {
		this.allRoles = allRoles;
	}

	public List<ZTreeBean> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(List<ZTreeBean> accountRoles) {
		this.accountRoles = accountRoles;
	}

	public Pager<AccountBean> getAccountBeanPager() {
		return accountBeanPager;
	}

	public void setAccountBeanPager(Pager<AccountBean> accountBeanPager) {
		this.accountBeanPager = accountBeanPager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
