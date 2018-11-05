package com.taiji.pubsec.szpt.xtgl.authority;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.taiji.pubsec.businesscomponents.authority.model.Authority;
import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.businesscomponents.authority.service.IAuthorityResourceService;
import com.taiji.pubsec.businesscomponents.authority.service.IAuthorityService;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.PublicBean;
import com.taiji.pubsec.szpt.xtgl.ZTreeBean;
import com.taiji.pubsec.szpt.xtgl.resource.ResourceBean;


/**
 * 实现权限的管理
 * 
 * @author xinfan
 *
 */
@Controller("authorityAction")
@Scope("prototype")
public class AuthorityAction extends PageCommonSysManageAction {
	@Resource
	private IAuthorityService authorityServiceImpl;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IAuthorityResourceService authorityResourceServiceImpl;
	private List<AuthorityBean> authorityBeanList;
	private List<ZTreeBean> authorityZTreeBean;
	private List<ZTreeBean> availabeAuthorityZTreeBean;
	private List<ResourceBean> authorityResourceList;
	private List<DictionaryItem> dictionaryItemLists;
	private Pager<AuthorityBean> authorityBeanpageList;

	private AuthorityBean authorityBean;
	private String id;
	/**
	 * 查找所有的资源
	 * @return "success" List<ResourceBean> 资源Bean的list对象
	 */
	public String findAllResource() {
		List<AuthorityResource> authorityResources = authorityResourceServiceImpl
				.findAll();
		authorityResourceList = new ArrayList<ResourceBean>();
		for (int i = 0; i < authorityResources.size(); i++) {
			ResourceBean resourceBeanTemp = ModelToBean
					.authorityResourceToBean(authorityResources.get(i));
			authorityResourceList.add(resourceBeanTemp);
		}
		authorityZTreeBean = this.constructAuthorityZtreeBean(authorityResourceList);
		return SUCCESS;
	}

	/**
	 * 新添加权限
	 * @param authorityBean 权限Bean
	 * @return "success"  返回flag、msg 成功时flag为"true" 失败时flag为"false";msg为返回的提示信息
	 */
	public String addAuthority() {
		if(!authorityServiceImpl.isDistinctAuthorityName(authorityBean.getAuthorityName(), null)){
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("权限已存在");
			return SUCCESS;
		}
		if (!authorityServiceImpl.isDistinctAuthorityCode(authorityBean.getAuthorityCode(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码已存在");
			return SUCCESS;
		}
		Authority newAuthority = new Authority();
				BeanToModel.authorityBeanToAuthority(authorityBean,newAuthority);
		newAuthority.setUpdatedTime(new Date());
		List<AuthorityResource> newAuthorityResourceList = new ArrayList<AuthorityResource>();
		for (ResourceBean resourceBeanTemp : authorityBean
				.getResourceLibBeans()) {
			newAuthorityResourceList.add(authorityResourceServiceImpl.findById(resourceBeanTemp.getId()));
		}
		authorityServiceImpl.createAuthority(newAuthority,newAuthorityResourceList);
		this.setFlag(Boolean.TRUE.toString());
		authorityBean.setId(newAuthority.getId());
		return SUCCESS;
	}
	/**
	 * 更新权限
	 * @param authorityBean 权限Bean
	 * @return "success"  返回flag、msg 成功时flag为"true" 失败时flag为"false";msg为返回的提示信息
	 */
	public String updateAuthority() {
		if(!authorityServiceImpl.isDistinctAuthorityName(authorityBean.getAuthorityName(), authorityBean.getId())){
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("权限已存在");
			return SUCCESS;
		}
		if (!authorityServiceImpl.isDistinctAuthorityCode( authorityBean.getAuthorityCode() ,  authorityBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码已经存在");
			return SUCCESS;
		}
		Authority authority = authorityServiceImpl.findById(authorityBean.getId());
		BeanToModel.authorityBeanToAuthority(authorityBean,authority);
		authority.setUpdatedTime(new Date());
		List<AuthorityResource> newAuthorityResourceList = new ArrayList<AuthorityResource>();
		for (ResourceBean resourceBeanTemp : authorityBean
				.getResourceLibBeans()) {
			newAuthorityResourceList.add(authorityResourceServiceImpl.findById(resourceBeanTemp.getId()));
		}
		authorityServiceImpl.updateAuthority(authority,
				newAuthorityResourceList);
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}
	/**
	 * 按Id查找权限 同时返回所有资源
	 * @param id 权限Id
	 * @return "success"  authorityResourceList 资源的list对象  authorityBean 权限Bean
	 */
	public String findAuthorityById() {
		Authority authority = authorityServiceImpl.findById(id);
		authorityBean = ModelToBean.authorityToAuthorityBean(authority);
		List<AuthorityResource> authorityResources = authorityResourceServiceImpl.findAll();
		authorityResourceList = new ArrayList<ResourceBean>();
		for (int i = 0; i < authorityResources.size(); i++) {
			ResourceBean resourceBeanTemp = ModelToBean
					.authorityResourceToBean(authorityResources.get(i));
			authorityResourceList.add(resourceBeanTemp);
		}
		availabeAuthorityZTreeBean = this.constructAuthorityZtreeBean(authorityBean.getResourceLibBeans());
		authorityZTreeBean = this.constructAuthorityZtreeBean(authorityResourceList);
		return SUCCESS;
	}

	/**
	 * 查找所有权限
	 * @param authorityBean 权限Bean
	 * @return "success" 成功时返回authorityBeanList 权限Bean的list对象 ；totalnum返回的总的记录数
	 */
	public String queryAllAuthority() {
			Pager<Authority> authorityListTemp = authorityServiceImpl.findAuthorityByNameAndCode(authorityBean
								.getAuthorityName(), authorityBean
								.getAuthorityCode(),  this.getStart() / this.getLength(),  this.getLength());
			this.setTotalNum(authorityListTemp.getTotalNum());
			authorityBeanpageList = new Pager<AuthorityBean>();
			for (Authority authorityTemp : authorityListTemp.getPageList()) {
				AuthorityBean authorityBeanTemp = ModelToBean
						.authorityToAuthorityBean(authorityTemp);
				authorityBeanpageList.getPageList().add(authorityBeanTemp);
			}
			authorityBeanList = authorityBeanpageList.getPageList();
		return SUCCESS;
	}

	/**
	 * 删除权限 
	 * @param id的List对象
	 * @return "success" 返回flag 成功时flag为true
	 */
	public String deleteAuthority() {
		for (PublicBean p : this.getIdList()) {
			authorityServiceImpl.deleteAuthority(p.getId());
		}
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}
	/**
	 * 将资源实体List转化为树形结构
	 * 
	 * @param authorityResourceList
	 *            资源的List 对象
	 * @return authorityZTreeBean 权限树形对象
	 */
	private List<ZTreeBean> constructAuthorityZtreeBean(
			List<ResourceBean> resourceBeanList) {
		List<ZTreeBean> tempAuthorityZTreeBean = new ArrayList<ZTreeBean>();
		for (ResourceBean  resourceTemp:  resourceBeanList) {
			ZTreeBean ztbTemp = new ZTreeBean();
			ztbTemp.setId(resourceTemp.getId());
			ztbTemp.setName(resourceTemp.getResourceName());
			ztbTemp.setIsParent(Boolean.FALSE.toString());
			ztbTemp.setParentId(null);
			tempAuthorityZTreeBean.add(ztbTemp);
		}
		return tempAuthorityZTreeBean;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AuthorityBean> getAuthorityBeanList() {
		return authorityBeanList;
	}

	public void setAuthorityBeanList(List<AuthorityBean> authorityBeanList) {
		this.authorityBeanList = authorityBeanList;
	}

	public AuthorityBean getAuthorityBean() {
		return authorityBean;
	}

	public void setAuthorityBean(AuthorityBean authorityBean) {
		this.authorityBean = authorityBean;
	}

	public List<ZTreeBean> getAuthorityZTreeBean() {
		return authorityZTreeBean;
	}

	public void setAuthorityZTreeBean(List<ZTreeBean> authorityZTreeBean) {
		this.authorityZTreeBean = authorityZTreeBean;
	}

	public List<ResourceBean> getAuthorityResourceList() {
		return authorityResourceList;
	}

	public void setAuthorityResourceList(
			List<ResourceBean> authorityResourceList) {
		this.authorityResourceList = authorityResourceList;
	}

	public List<DictionaryItem> getDictionaryItemLists() {
		return dictionaryItemLists;
	}

	public void setDictionaryItemLists(List<DictionaryItem> dictionaryItemLists) {
		this.dictionaryItemLists = dictionaryItemLists;
	}

	public Pager<AuthorityBean> getAuthorityBeanpageList() {
		return authorityBeanpageList;
	}

	public void setAuthorityBeanpageList(
			Pager<AuthorityBean> authorityBeanpageList) {
		this.authorityBeanpageList = authorityBeanpageList;
	}
	public List<ZTreeBean> getAvailabeAuthorityZTreeBean() {
		return availabeAuthorityZTreeBean;
	}

	public void setAvailabeAuthorityZTreeBean(
			List<ZTreeBean> availabeAuthorityZTreeBean) {
		this.availabeAuthorityZTreeBean = availabeAuthorityZTreeBean;
	}

}
