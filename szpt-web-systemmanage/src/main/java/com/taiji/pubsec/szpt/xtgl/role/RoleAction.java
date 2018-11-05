package com.taiji.pubsec.szpt.xtgl.role;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.PublicBean;
import com.taiji.pubsec.szpt.xtgl.ZTreeBean;
import com.taiji.pubsec.businesscomponents.authority.model.Authority;
import com.taiji.pubsec.businesscomponents.authority.model.Role;
import com.taiji.pubsec.businesscomponents.authority.service.IAuthorityService;
import com.taiji.pubsec.businesscomponents.authority.service.IRoleService;
import com.taiji.pubsec.businesscomponents.authority.service.IRoleSubjectRelationService;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.service.UnitServiceImpl;


/**
 * 角色管理
 * 
 * @author xinfan
 *
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends PageCommonSysManageAction {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnitServiceImpl.class);
	@Resource
	private IRoleService roleServiceImpl;
	@Resource
	private IRoleSubjectRelationService roleSubjectRelationService;
	@Resource
	private IAuthorityService authorityServiceImpl;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	private List<PublicBean> idList;
	private List<RoleBean> roleBeanList;
	private List<ZTreeBean> authorityZTreeBean;
	private RoleBean roleBean;
	private String id;
	private String roleName;
	private String status;

	/**
	 * 查找角色
	 *
	 * @param roleBean
	 * @return "success" 成功时返回roleBeanList 角色Bean的List对象 totalNum 总的记录条数
	 */
	public String queryRole() {
		Pager<RoleBean> roleBeanPager = new Pager<RoleBean>();
		Pager<Role> rolePager = roleServiceImpl.findRolesByNameAndStatus(roleBean.getRoleName(), roleBean.getStatus(),  this.getStart() / this.getLength(), this.getLength());
		roleBeanPager.setTotalNum(rolePager.getTotalNum());
		this.setTotalNum(rolePager.getTotalNum());
		roleBeanList = new ArrayList<RoleBean>();
		DictionaryType statuDictionaryType = dictionaryTypeService.findDicTypeByCode(Constant.STATUS);
		for (Role roleTemp : rolePager.getPageList()) {
			RoleBean roleBeanTemp = ModelToBean.roleToRoleBean(roleTemp);
			DictionaryItem dictionaryItem = dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(statuDictionaryType.getId(), roleBeanTemp.getStatus(), null);
			if(dictionaryItem != null){
				roleBeanTemp.setStatus(dictionaryItem.getName());
			}
			roleBeanList.add(roleBeanTemp);
		}
		return SUCCESS;
	}

	/**
	 * 启用/停用角色状态
	 * 
	 * @param idList
	 *            id的list对象
	 * 
	 * @return "success"
	 */
	public String modifyRoleStatus() {
		for (PublicBean r : idList) {
			Role roleTemp = roleServiceImpl.findById(r.getId());
			roleTemp.setStatus(status);
			roleServiceImpl.updateRole(roleTemp, roleTemp.getAuthorities());
		}
		return SUCCESS;
	}

	/**
	 * 保存角色
	 * 
	 * @param roleBean
	 *            角色Bean
	 * 
	 * @return "success" 返回flag,msg 成功时flag为"true" ，失败时flag为"false";msg为返回的提示信息
	 */
	public String saveRole() {
		if (!roleServiceImpl.isDistinctRoleName(roleBean.getRoleName(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("角色已存在");
			return SUCCESS;
		}
		if (!roleServiceImpl.isDistinctRoleCode(roleBean.getRoleCode() , null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		Role roleAdd = BeanToModel.roleBeanToRole(roleBean);
		roleAdd.setUpdatedTime(new Date());
		roleServiceImpl.createRole(roleAdd, roleAdd.getAuthorities());
		this.setFlag(Boolean.TRUE.toString());
		roleBean.setId(roleAdd.getId());
		return SUCCESS;
	}

	/**
	 * 按Id查找角色 ,同时将此角色的权限信息构造成树
	 * 
	 * @param:Id 角色id
	 * @return "success" roleBean 角色Bean authorityZTreeBean 权限树对象
	 */
	public String queryRoleById() {
		Role roleTemp = roleServiceImpl.findById(id);
		roleBean = ModelToBean.roleToRoleBean(roleTemp);
		DictionaryType statuDictionaryType = dictionaryTypeService.findDicTypeByCode(Constant.STATUS);
		DictionaryItem dictionaryItem = dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(statuDictionaryType.getId(), roleBean.getStatus(), null);
		roleBean.setStatus(dictionaryItem.getName());
		authorityZTreeBean = this.constructAuthorityZtreeBean(roleTemp
				.getAuthorities());
		return SUCCESS;
	}

	/**
	 * 更新角色
	 * 
	 * @param roleBean
	 *            角色bean
	 * 
	 * @return "success" 返回flag msg 成功时flag为"true" 失败时flag为"false"; msg为返回的提示信息
	 */
	public String updateRole() {
		if (!roleServiceImpl.isDistinctRoleName(roleBean.getRoleName() , roleBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("角色已存在");
			return SUCCESS;
		}
		if (!roleServiceImpl.isDistinctRoleCode(roleBean.getRoleCode() , roleBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		Role roleTemp = BeanToModel.roleBeanToRole(roleBean);
		roleTemp.setUpdatedTime(new Date());
		roleServiceImpl.updateRole(roleTemp, roleTemp.getAuthorities());
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 删除角色
	 * 
	 * @param idList
	 *            角色id List
	 * @return "success" 返回flag msg 成功时flag为"true" 失败时flag为"false"; msg为返回的提示信息
	 */
	public String deleteRole() {
		List<String> delRoleName = new ArrayList<String>();
		List<String> unDelRoleName = new ArrayList<String>();
		try{
			for (PublicBean p : idList) {
				Role roleTemp = roleServiceImpl.findById(p.getId());
				if (Constant.STATUS_START.equals(roleTemp.getStatus())) {
					unDelRoleName.add(roleTemp.getRoleName());
				}else{
					delRoleName.add(roleTemp.getRoleName());
					roleServiceImpl.deleteRole(p.getId());
				}
			}
			if(!delRoleName.isEmpty()){
				this.setMsg(StringUtils.join(delRoleName.toArray(),"、")+"成功删除。");
			}
			if(!unDelRoleName.isEmpty()){
				String tmpMsg = this.getMsg()==null?"":this.getMsg();
				this.setMsg(tmpMsg+StringUtils.join(unDelRoleName.toArray(),"、")+"状态为启用不可删除");
			}
			this.setFlag(Boolean.TRUE.toString());
		}catch(Exception e){
			LOGGER.debug("已有外键关联，不允许删除", e);
			this.setMsg("此角色已被关联，不允许删除");
			this.setFlag(Boolean.FALSE.toString());
		}
		return SUCCESS;
	}

	/**
	 * 初始化权限树
	 * 
	 * @return "success" authorityZTreeBean 权限树Bean
	 */
	public String queryAuthorityZTree() {
		List<Authority> authoritys = authorityServiceImpl.findAll();
		authorityZTreeBean = this.constructAuthorityZtreeBean(authoritys);
		return SUCCESS;
	}

	/**
	 * 将权限实体List转化为树形结构
	 * 
	 * @param authorityLists
	 *            权限的List 对象
	 * @return authorityZTreeBean 权限树形对象
	 */
	private List<ZTreeBean> constructAuthorityZtreeBean(
			List<Authority> authorityLists) {
		List<ZTreeBean> tempAuthorityZTreeBean = new ArrayList<ZTreeBean>();
		for (Authority authorityTemp : authorityLists) {
			ZTreeBean ztbTemp = new ZTreeBean();
			ztbTemp.setId(authorityTemp.getId());
			ztbTemp.setName(authorityTemp.getAuthorityName());
			ztbTemp.setIsParent(Boolean.FALSE.toString());
			ztbTemp.setParentId(null);
			tempAuthorityZTreeBean.add(ztbTemp);
		}
		return tempAuthorityZTreeBean;
	}

	public RoleBean getRoleBean() {
		return roleBean;
	}

	public void setRoleBean(RoleBean roleBean) {
		this.roleBean = roleBean;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RoleBean> getRoleBeanList() {
		return roleBeanList;
	}

	public void setRoleBeanList(List<RoleBean> roleBeanList) {
		this.roleBeanList = roleBeanList;
	}

	public List<PublicBean> getIdList() {
		return idList;
	}

	public void setIdList(List<PublicBean> idList) {
		this.idList = idList;
	}

	public List<ZTreeBean> getAuthorityZTreeBean() {
		return authorityZTreeBean;
	}

	public void setAuthorityZTreeBean(List<ZTreeBean> authorityZTreeBean) {
		this.authorityZTreeBean = authorityZTreeBean;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
