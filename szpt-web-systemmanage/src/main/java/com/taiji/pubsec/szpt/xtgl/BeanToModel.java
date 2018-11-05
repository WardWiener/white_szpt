package com.taiji.pubsec.szpt.xtgl;


import java.util.Date;

import com.taiji.pubsec.szpt.xtgl.authority.AuthorityBean;
import com.taiji.pubsec.szpt.xtgl.department.DepartmentBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryItemBean;
import com.taiji.pubsec.szpt.xtgl.dictionary.DictionaryTypeBean;
import com.taiji.pubsec.szpt.xtgl.person.PersonBean;
import com.taiji.pubsec.szpt.xtgl.resource.ResourceBean;
import com.taiji.pubsec.szpt.xtgl.role.RoleBean;
import com.taiji.pubsec.szpt.xtgl.unit.UnitBean;
import com.taiji.pubsec.szpt.xtgl.user.AccountBean;
import com.taiji.pubsec.businesscomponents.authority.model.Authority;
import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.businesscomponents.authority.model.Role;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Department;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;

/**
 * 将Bean转化为model
 * 
 * @author xinfan
 *
 */
public class BeanToModel {

	/**
	 * 私有构造方法 防止被实例化
	 *
	 */
	private BeanToModel() {

	}

	/**
	 * 单位bean到model的映射
	 * 
	 * @param unitBean
	 * @return Unit
	 */
	public static Unit unitBeanToUnit(UnitBean unitBean) {
		String comp = "";
		Unit unitTem = new Unit();
		unitTem.setAddress(unitBean.getAddr());
		unitTem.setCode(unitBean.getUnitNum());
		unitTem.setFax(unitBean.getFax());
		unitTem.setId(comp.equals(unitBean.getId()) ? null : unitBean.getId());
		unitTem.setName(unitBean.getUnitName());
		unitTem.setRemark(unitBean.getInto());
		unitTem.setShortName(unitBean.getShortName());
		unitTem.setStatus(unitBean.getStatus());
		unitTem.setTelephone(unitBean.getTel());
		unitTem.setType(unitBean.getType());
		return unitTem;
	}

	/**
	 * 字典类型Bean转字典类型Model
	 * @param dictionaryTypeBean
	 * @return DictionaryType
	 */
	public static DictionaryType dictionaryBeanToDictionaryModel(
			DictionaryTypeBean dictionaryTypeBean) {
		DictionaryType dictionaryType = new DictionaryType();
		dictionaryType.setId(dictionaryTypeBean.getId());
		dictionaryType.setName(dictionaryTypeBean.getName());
		dictionaryType.setCode(dictionaryTypeBean.getCode());
		dictionaryType.setClassifier(dictionaryTypeBean.getClassifier());
		dictionaryType.setState(dictionaryTypeBean.getState());
		dictionaryType.setUpdatedTime(dictionaryTypeBean.getUpdatedTime());
		dictionaryType.setDescription(dictionaryTypeBean.getDescription());
		if (dictionaryTypeBean.getDictionaryItemBeanLists() != null) {
			for (DictionaryItemBean dictionaryItemBeanTemp : dictionaryTypeBean
					.getDictionaryItemBeanLists()) {
				DictionaryItem dictionaryItemType = dictionaryItemBeanToDictionaryItemModel(dictionaryItemBeanTemp);
				dictionaryType.getDicItems().add(dictionaryItemType);
			}
		}
		return dictionaryType;
	}

	/**
	 * 字典项Bean转为字典model
	 * 
	 * @param dictionaryItemBean
	 * @return DictionaryItem
	 */
	public static DictionaryItem dictionaryItemBeanToDictionaryItemModel(
			DictionaryItemBean dictionaryItemBean) {
		DictionaryItem dictionaryItem = new DictionaryItem();
		dictionaryItem.setCode(dictionaryItemBean.getCode());
		dictionaryItem.setDescription(dictionaryItemBean.getDescription());
		dictionaryItem.setState(dictionaryItemBean.getState());
		DictionaryType dictionaryType = new DictionaryType();
		dictionaryType.setId(dictionaryItemBean.getDicTypeId());
		dictionaryItem.setDicType(dictionaryType);
		dictionaryItem.setId(dictionaryItemBean.getId());
		dictionaryItem.setName(dictionaryItemBean.getName());
		dictionaryItem.setNumber(dictionaryItemBean.getNumber());
		DictionaryItem dictionaryItemParentId = new DictionaryItem();
		dictionaryItemParentId.setId(dictionaryItemBean.getParentItemId());
		dictionaryItem.setParentItem(dictionaryItemParentId);
		dictionaryItem.setUpdatedTime(new Date());
		return dictionaryItem;
	}

	/**
	 * 权限bean到权限Model的映射
	 * @param authorityBean
	 * @return Authority
	 */
	public static void authorityBeanToAuthority(AuthorityBean authorityBean,Authority authority) {
		authority.setAuthorityCode(authorityBean.getAuthorityCode());
		authority.setAuthorityName(authorityBean.getAuthorityName());
		authority.setAuthorityType(authorityBean.getAuthorityType());
		authority.setId(authorityBean.getId());
		authority.getResources().clear();
	}

	/**
	 * 资源Bean到资源model的转化
	 * @param resourceBean
	 * @param authorityResource
	 */
	public static void beanToAuthorityResource(
			ResourceBean resourceBean,AuthorityResource authorityResource) {
		authorityResource.setResourceName(resourceBean.getResourceName());
		authorityResource.setResourceUrl(resourceBean.getResourceUrl());
		authorityResource.setUpdatedTime(new Date());
		authorityResource.setResourceType(resourceBean.getType());
	}

	/**
	 * 将角色Bean转化为角色model
	 * 
	 * @param roleBean
	 * @return Role
	 */
	public static Role roleBeanToRole(RoleBean roleBean) {
		Role role = new Role();
		role.setId(roleBean.getId());
		role.setRoleName(roleBean.getRoleName());
		role.setRoleCode(roleBean.getRoleCode());
		role.setStatus(roleBean.getStatus());
		role.getAuthorities().clear();
		for (AuthorityBean authorityBeanTemp : roleBean.getAuthorityBeanBeans()) {
			Authority authorityTemp = new Authority();
			BeanToModel.authorityBeanToAuthority(authorityBeanTemp,authorityTemp);
			role.getAuthorities().add(authorityTemp);
		}
		return role;
	}

	/**
	 * 账户bean转化为账户model
	 * 
	 * @param accountBean
	 * @return Account
	 */
	public static Account accountBeanAccount(AccountBean accountBean) {
		Account account = new Account();
		account.setAccountName(accountBean.getAccountName());
		account.setEndDate(accountBean.getEndDate());
		account.setId(accountBean.getId());
		account.setIntro(accountBean.getIntro());
		account.setPassword(accountBean.getPassword());
		account.setPerson(BeanToModel.personBeanToPerson(accountBean.getPersonBean()));
		account.setStartDate(accountBean.getStartDate());
		account.setStatus(accountBean.getStatus());
		account.setUpdatedTime(accountBean.getUpdatedTime());
		return account;
	}

	/**
	 * 人员Bean转化为人员model
	 * 
	 * @param personBean
	 * @return Person
	 */
	public static Person personBeanToPerson(PersonBean personBean) {
		Person person = new Person();
		person.setCode(personBean.getPersonCode());
		person.setDegree(personBean.getDiploma());
		person.setId(personBean.getPersonId());
		person.setIdNumber(personBean.getStatusCardNo());
		person.setName(personBean.getName());
		person.setNationality(personBean.getNationality());
		person.setOfficePhone(personBean.getOfficePhone());
		person.setPoliticalStatus(personBean.getPoliticsType());
		person.setPosition(personBean.getJob());
		person.setSex(personBean.getSex());
		person.setStatus(personBean.getStatus());
		person.setTelephone(personBean.getMovePhone());
		person.setCode(personBean.getPoliceNo());
		Date date = new Date();
		person.setUpdatedTime(date);
		return person;
	}

	/**
	 * 部门bean到model的映射
	 * 
	 * @param departmentBean
	 * @return Department
	 */
	public static Department departmentBeanToDepartment(
			DepartmentBean departmentBean) {
		Department department = new Department();
		String comp = "";
		department.setAddress(departmentBean.getAddr());
		department.setCode(departmentBean.getDepartmentNum());
		department.setFax(departmentBean.getFax());
		department.setId(comp.equals(departmentBean.getId()) ? null
				: departmentBean.getId());
		department.setName(departmentBean.getDepartmentName());
		department.setRemark(departmentBean.getInto());
		department.setShortName(departmentBean.getShortName());
		department.setStatus(departmentBean.getStatus());
		department.setTelephone(departmentBean.getTel());
		return department;
	}

}
