package com.taiji.pubsec.szpt.xtgl;




import com.taiji.pubsec.szpt.xtgl.authority.AuthorityBean;
import com.taiji.pubsec.szpt.xtgl.department.DepartmentBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryItemBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryTypeBean;
import com.taiji.pubsec.szpt.xtgl.person.PersonBean;
import com.taiji.pubsec.szpt.xtgl.resource.ResourceBean;
import com.taiji.pubsec.szpt.xtgl.role.RoleBean;
import com.taiji.pubsec.szpt.xtgl.unit.UnitBean;
import com.taiji.pubsec.szpt.xtgl.user.AccountBean;
import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Department;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.authority.model.Authority;
import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.businesscomponents.authority.model.Role;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;

/**
 * 将Model转化成Bean
 * 
 * @author xinfan
 *
 */
public class ModelToBean {
	

	/**
	 * 私有构造方法防止创建实例
	 */
	private ModelToBean() {

	}
	
	/**
	 * 单位Model到单位Bean
	 * 
	 * @param unit
	 * @return UnitBean
	 */
	public static UnitBean unitToUnitBean(Unit unit) {
		UnitBean unitBean = new UnitBean();
		unitBean.setAddr(unit.getAddress());
		unitBean.setFax(unit.getFax());
		unitBean.setId(unit.getId());
		unitBean.setInto(unit.getRemark());
		if (unit.getSuperOrg() != null) {
			unitBean.setParUnitNum(unit.getSuperOrg().getCode());
			unitBean.setParUnitId(unit.getSuperOrg().getId());
			unitBean.setParUnitName(unit.getSuperOrg().getName());
		}
		unitBean.setProp(unit.getType());
		unitBean.setShortName(unit.getShortName());
		unitBean.setTel(unit.getTelephone());
		unitBean.setUnitCode(unit.getCode());
		unitBean.setUnitName(unit.getName());
		unitBean.setUnitNum(unit.getCode());
		unitBean.setStatus(unit.getStatus());
		unitBean.setType(unit.getType());
		return unitBean;
	}

	/**
	 * 权限Model到权限Bean
	 * 
	 * @param authority
	 * @return AuthorityBean
	 */
	public static AuthorityBean authorityToAuthorityBean(Authority authority) {
		AuthorityBean authorityBean = new AuthorityBean();
		authorityBean.setAuthorityCode(authority.getAuthorityCode());
		authorityBean.setAuthorityName(authority.getAuthorityName());
		authorityBean.setAuthorityType(authority.getAuthorityType());
		authorityBean.setId(authority.getId());
		for (AuthorityResource authorityResourceTemp : authority.getResources()) {
			ResourceBean resourceBeanTemp = ModelToBean
					.authorityResourceToBean(authorityResourceTemp);
			authorityBean.getResourceLibBeans().add(resourceBeanTemp);
		}
		return authorityBean;
	}

	/**
	 * 字典类型Modeldao字典类型Bean
	 * 
	 * @param dictionaryType
	 * @return DictionaryTypeBean
	 */
	public static DictionaryTypeBean dictionaryTypeToDictionaryTypeBean(
			DictionaryType dictionaryType) {
		DictionaryTypeBean dictionaryTypeBean = new DictionaryTypeBean();
		dictionaryTypeBean.setId(dictionaryType.getId());
		dictionaryTypeBean.setCode(dictionaryType.getCode());
		dictionaryTypeBean.setName(dictionaryType.getName());
		dictionaryTypeBean.setState(dictionaryType.getState());
		dictionaryTypeBean.setClassifier(dictionaryType.getClassifier());
		dictionaryTypeBean.setDescription(dictionaryType.getDescription());
		dictionaryTypeBean.setUpdatedTime(dictionaryType.getUpdatedTime());
		return dictionaryTypeBean;
	}

	/**
	 * 字典项model到字典项bean
	 * 
	 * @param dictionaryItem
	 * @return DictionaryItemBean
	 */
	public static DictionaryItemBean dictionaryItemToDictionaryItemBean(
			DictionaryItem dictionaryItem) {
		DictionaryItemBean dictionaryItemBean = new DictionaryItemBean();
		dictionaryItemBean.setCode(dictionaryItem.getCode());
		dictionaryItemBean.setDescription(dictionaryItem.getDescription());
		dictionaryItemBean.setDicTypeId(dictionaryItem.getDicType().getId());
		dictionaryItemBean.setId(dictionaryItem.getId());
		dictionaryItemBean.setName(dictionaryItem.getName());
		dictionaryItemBean.setNumber(dictionaryItem.getNumber());
		if (dictionaryItem.getParentItem() != null) {
			dictionaryItemBean.setParentItemId(dictionaryItem.getParentItem()
					.getId());
		}
		dictionaryItemBean.setUpdatedTime(dictionaryItem.getUpdatedTime());
		return dictionaryItemBean;
	}

	/**
	 * 资源Model到资源Bean
	 * 
	 * @param authorityResource
	 * @return ResourceBean
	 */
	public static ResourceBean authorityResourceToBean(
			AuthorityResource authorityResource) {
		ResourceBean resourceBean = new ResourceBean();
		resourceBean.setId(authorityResource.getId());
		resourceBean.setResourceName(authorityResource.getResourceName());
		resourceBean.setResourceUrl(authorityResource.getResourceUrl());
		resourceBean.setType(authorityResource.getResourceType());
		return resourceBean;
	}

	/**
	 * 角色Model到角色Bean
	 * 
	 * @param role
	 * @return RoleBean
	 */
	public static RoleBean roleToRoleBean(Role role) {
		RoleBean roleBean = new RoleBean();
		roleBean.setId(role.getId());
		roleBean.setRoleCode(role.getRoleCode());
		roleBean.setRoleName(role.getRoleName());
		roleBean.setStatus(role.getStatus());
		for (Authority authorityTemp : role.getAuthorities()) {
			AuthorityBean authorityBeanTemp = ModelToBean
					.authorityToAuthorityBean(authorityTemp);
			roleBean.getAuthorityBeanBeans().add(authorityBeanTemp);
		}
		return roleBean;
	}

	/**
	 * 用户Model到用户bean
	 * 
	 * @param account
	 * @return AccountBean
	 */
	public static  AccountBean accountToAccountBean(Account account) {
		AccountBean accountBean = new AccountBean();
		accountBean.setAccountName(account.getAccountName());
		accountBean.setEndDate(account.getEndDate());
		accountBean.setId(account.getId());
		accountBean.setIntro(account.getIntro());
		accountBean.setPassword(account.getPassword());
		accountBean.setPersonBean(ModelToBean.personToPersonBean(account.getPerson()));
		accountBean.setStartDate(account.getStartDate());
		accountBean.setStatus(account.getStatus());
		accountBean.setUpdatedTime(account.getUpdatedTime());
		return accountBean;
	}

	/**
	 * 人员Model到人员Bean
	 * 
	 * @param person
	 * @return PersonBean
	 */
	public static PersonBean personToPersonBean(Person person) {
		PersonBean personBean = new PersonBean();
		personBean.setDiploma(person.getDegree());
		personBean.setJob(person.getPosition());
		personBean.setMovePhone(person.getTelephone());
		personBean.setName(person.getName());
		personBean.setNationality(person.getNationality());
		personBean.setOfficePhone(person.getOfficePhone());
		personBean.setPersonCode(person.getCode());
		personBean.setPoliceNo(person.getCode());
		personBean.setPersonId(person.getId());
		personBean.setPoliticsType(person.getPoliticalStatus());
		personBean.setSex(person.getSex());
		personBean.setStatus(person.getStatus());
		personBean.setStatusCardNo(person.getIdNumber());
		personBean.setUnitCode(person.getOrganization().getCode());
		personBean.setUnitFullName(person.getOrganization().getName());
		personBean.setShortUnitName(person.getOrganization().getShortName());
		personBean.setUnitId(person.getOrganization().getId());
		return personBean;
	}

	/**
	 * model-departemnt到部门bean的映射
	 * 
	 * @param department
	 * @return DepartmentBean
	 */
	public static DepartmentBean departmentToDepartmentBean(
			Department department) {
		DepartmentBean departmentBean = new DepartmentBean();
		departmentBean.setAddr(department.getAddress());
		departmentBean.setFax(department.getFax());
		departmentBean.setId(department.getId());
		departmentBean.setInto(department.getRemark());
		if (department.getSuperOrg() != null) {
			departmentBean.setParDepartmentNum(department.getSuperOrg()
					.getCode());
			departmentBean.setParDepartmentId(department.getSuperOrg().getId());
			departmentBean.setParDepartmentName(department.getSuperOrg()
					.getName());
		}
		departmentBean.setShortName(department.getShortName());
		departmentBean.setTel(department.getTelephone());
		departmentBean.setDepartmentCode(department.getCode());
		departmentBean.setDepartmentName(department.getName());
		departmentBean.setDepartmentNum(department.getCode());
		departmentBean.setStatus(department.getStatus());
		return departmentBean;
	}
}
