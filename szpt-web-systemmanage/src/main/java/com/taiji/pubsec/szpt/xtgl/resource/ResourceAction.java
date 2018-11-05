package com.taiji.pubsec.szpt.xtgl.resource;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.PublicBean;
import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.businesscomponents.authority.service.AuthorityResourceServiceImpl;
import com.taiji.pubsec.businesscomponents.authority.service.IAuthorityResourceService;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;


/**
 * 资源管理
 * 
 * @author xinfan
 *
 */
@Controller("resourceAction")
@Scope("prototype")
public class ResourceAction extends PageCommonSysManageAction {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthorityResourceServiceImpl.class);

	@Resource
	private IAuthorityResourceService authorityResourceService;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	private List<ResourceBean> resourceBeanList;
	private List<DictionaryItem> dictionaryItemList;
	private List<String> types;
	private Pager<ResourceBean> resourceBeanPager;
	private ResourceBean resourceBean;
	/**
	 * 查找资源
	 * @param  resourceBean 资源Bean
	 * @param  start 页面开始项
	 * @param  length 页面包含记录数
	 * @return "success" resourceBeanList资源Bean的List对象 totalnum 返回的总的记录数
	 */
	public String queryResource() {
		resourceBeanPager = this.findResourceBeanByConditions(resourceBean,
				this.getStart(), this.getLength());
		this.setTotalNum(resourceBeanPager.getTotalNum());
		resourceBeanList = resourceBeanPager.getPageList();
		return SUCCESS;
	}

	/**
	 * 根据ID查找资源
	 * @param resourceBean资源Bean
	 * @return "success" resourceBean 资源Bean
	 */
	public String queryResourceById() {
		AuthorityResource authorityResource = authorityResourceService
				.findById(resourceBean.getId());
		resourceBean = ModelToBean.authorityResourceToBean(authorityResource);
		return SUCCESS;
	}

	/**
	 * 保存资源
	 * @param  resourceBean 资源Bean
	 * @return "success" 返回flag,msg 成功时flag为"true"，失败时flag为"false";msg为返回的提示信息
	 */
	public String saveResource() {
		try {
			AuthorityResource authorityResource = new AuthorityResource();
			BeanToModel.beanToAuthorityResource(resourceBean,authorityResource);
			authorityResource.setUpdatedTime(new Date());
			authorityResourceService.createResource(authorityResource);
			this.setFlag(Boolean.TRUE.toString());
			this.setMsg("保存成功");
		} catch (Exception e) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("保存失败");
			LOGGER.debug(e.getMessage() + "保存失败", e);
		}

		return SUCCESS;
	}

	/**
	 * 更新资源
	 * @param resourceBean 资源bean 
	 * @return  "success" 返回flag,msg 成功时flag为"true"，失败时flag为"false";msg为返回的提示信息
	 */
	public String updateResource() {
		try {
			AuthorityResource authorityResource = authorityResourceService.findById(resourceBean.getId());
		    BeanToModel.beanToAuthorityResource(resourceBean ,authorityResource);
			authorityResourceService.updateResource(authorityResource);
			this.setFlag(Boolean.TRUE.toString());
			this.setMsg("修改成功");
		} catch (Exception e) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("修改失败");
			LOGGER.debug(e.getMessage(), e);
		}
		return SUCCESS;
	}

	/**
	 * 删除资源
	 * @param idList 要删除的Id list对象
	 * @return "success" 返回flag,msg 成功删除时flag为"true"，失败时flag为"false";msg为返回的提示信息
	 */
	public String deleteResource() {
		try {
			for (PublicBean del : this.getIdList()) {
				authorityResourceService.deleteResource(del.getId());
			}
			this.setFlag(Boolean.TRUE.toString());
			this.setMsg("删除成功");
		} catch (Exception e) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("删除失败");
			LOGGER.debug(e.getMessage(), e);

		}
		return SUCCESS;
	}

	
	/**
	 * 资源名称唯一性验证
	 * @param resourceBean   资源bean 
	 * @return "success" 返回flag,msg 不重复时flag为"true"，重复时时flag为"false";msg为返回的提示信息
	 */
	public String veryResource() {
		if (!StringUtils.isEmpty(resourceBean.getId())) {
			if (!authorityResourceService.isDistinctAuthorityResourcesName(resourceBean.getResourceName() , resourceBean.getId())) {
				this.setFlag(Boolean.FALSE.toString());
				this.setMsg("名称重复");
			}
		} else if (!authorityResourceService.isDistinctAuthorityResourcesName(resourceBean.getResourceName(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
       }else{
    	   this.setFlag(Boolean.TRUE.toString());
       }
		return SUCCESS;
	}

	/**
	 * 不同的条件查找资源
	 * 
	 * @param resourceBean 资源Bean
	 * @param pageStart 页面开始的条数
	 * @param pageSize 页面的记录数
	 * @return  resourcePager 资源Bean的分页对象
	 */
	private Pager<ResourceBean> findResourceBeanByConditions(
			ResourceBean resourceBean, int pageStart, int pageSize) {
		Pager<ResourceBean>  resourcePager = new Pager<ResourceBean>();
		Pager<AuthorityResource> authorityResourceTemp = authorityResourceService.findAuthorityResourcesByNameAndType(resourceBean
				.getResourceName(), resourceBean.getType(), pageStart / pageSize, pageSize);
		resourcePager.setTotalNum(authorityResourceTemp.getTotalNum());
		for (int i = 0; i < authorityResourceTemp.getPageList().size(); i++) {
			ResourceBean resourceBeanTemp = ModelToBean
					.authorityResourceToBean(authorityResourceTemp
							.getPageList().get(i));
			DictionaryType  tmpDictioary = dictionaryTypeService.findDicTypeByCode(Constant.RESOURCE_TYPE);
			DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(tmpDictioary.getId(),resourceBeanTemp.getType(), null);
			if (di != null) {
				resourceBeanTemp.setType(di.getName());
			}
			resourcePager.getPageList().add(resourceBeanTemp);
		}
		return resourcePager;

	}

	public ResourceBean getResourceBean() {
		return resourceBean;
	}

	public void setResourceBean(ResourceBean resourceBean) {
		this.resourceBean = resourceBean;
	}

	public List<ResourceBean> getResourceBeanList() {
		return resourceBeanList;
	}

	public void setResourceBeanList(List<ResourceBean> resourceBeanList) {
		this.resourceBeanList = resourceBeanList;
	}

	public Pager<ResourceBean> getResourceBeanPager() {
		return resourceBeanPager;
	}

	public void setResourceBeanPager(Pager<ResourceBean> resourceBeanPager) {
		this.resourceBeanPager = resourceBeanPager;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<DictionaryItem> getDictionaryItemList() {
		return dictionaryItemList;
	}

	public void setDictionaryItemList(List<DictionaryItem> dictionaryItemList) {
		this.dictionaryItemList = dictionaryItemList;
	}
}
