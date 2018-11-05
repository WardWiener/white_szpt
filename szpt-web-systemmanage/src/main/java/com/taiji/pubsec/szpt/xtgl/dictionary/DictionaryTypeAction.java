package com.taiji.pubsec.szpt.xtgl.dictionary;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;


/**
 * 字典类型管理
 * 
 * @author xinfan
 *
 */
@Controller("dictionaryTypeAction")
@Scope("prototype")
public class DictionaryTypeAction extends PageCommonSysManageAction {
	private static final long serialVersionUID = 1L;
	private static final String STOP_LOGO = "../images/xtgl_icon/ztree-icon_ty.png";
	private static final String START_LOGO = "../images/xtgl_icon/ztree-icon_qy.png";
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	private Pager<DictionaryTypeBean> dictionaryTypeBeanPager;
	private DictionaryTypeBean dictionaryTypeBean;// 字典类型对象
	private DictionaryItemBean dicItemBean; // 字典项对象
	private List<DictionaryItemBean> dictionaryItemList;
	private List<ZTreeBean> ztreeBeanList; // 字典项树
	private List<DictionaryTypeBean> dictionaryTypeBeanList;
	private List<String> dicTypeClassifierList;// 字典类型划分List
	private String id;// 字典类型id
	private String searchingTypeId; // 选定字典项节点时的字典类型id
	private String classifer; // 字典类型划分
	private String dictionaryTypeName; // 字典类型名称
	private String itemId; // 字典项Id
	private String itemPreId;// 上一个字典项Id
	private String itemNexId;// 下一个字典项id
	private String itemParentId;// 父字典项Id

	/**
	 * 查找字典类型
	 * 
	 * @param dictionaryTypeName 字典类型名称
	 * @param classifer  字典类型划分
	 * @return "success"; 成功则返回dictionaryTypeBeanPager 字典类型的分页对象
	 */
	public String findDictionaryType() {
		Map<String, String> queryParams = new HashMap<String, String>();
		String queryAll = "全部";
		if (!StringUtils.isBlank(dictionaryTypeName)) {
			queryParams.put("name", dictionaryTypeName);
		}
		if (!StringUtils.isBlank(classifer) && !queryAll.equals(classifer)) {
			queryParams.put("classifier", classifer);
		}
		Pager<DictionaryType> dictionaryTypePagerTemp = dictionaryTypeService
				.findDictionaryTypesByParams(queryParams, this.getStart()
						/ this.getLength(), this.getLength());
		dictionaryTypeBeanPager = new Pager<DictionaryTypeBean>();
		this.setTotalNum(dictionaryTypePagerTemp.getTotalNum());
		for (DictionaryType dictionaryTypeTemp : dictionaryTypePagerTemp
				.getPageList()) {
			DictionaryTypeBean dictionaryTypeBeanTemp = ModelToBean
					.dictionaryTypeToDictionaryTypeBean(dictionaryTypeTemp);
			dictionaryTypeBeanPager.getPageList().add(dictionaryTypeBeanTemp);
		}
		return SUCCESS;
	}

	/**
	 * 新增字典项时 初始化状态下拉列表
	 * 
	 * @return "success"; 成功时返回dictionaryItemList 字典项List对象
	 */
	public String initDictItemStatus() {
		dictionaryItemList = new ArrayList<DictionaryItemBean>();
		DictionaryType dictionaryTypeOfStatus = dictionaryTypeService
				.findDicTypeByCode(Constant.STATUS);
		List<DictionaryItem> dictionaryItems = dictionaryTypeOfStatus
				.getDicItems();
		for (DictionaryItem dictionaryItemTemp : dictionaryItems) {
			DictionaryItemBean dictionaryItemBeanTemp = ModelToBean
					.dictionaryItemToDictionaryItemBean(dictionaryItemTemp);
			dictionaryItemList.add(dictionaryItemBeanTemp);
		}
		return SUCCESS;
	}

	/**
	 * 保存字典类型
	 * 
	 * @param:dictionaryTypeBean 字典类型对应的Bean
	 * @return "success" 返回flag msg 成功时flag为"true" 失败时flag为"false"; msg为错误提示信息
	 */
	public String saveDictionaryType() {
		if (!dictionaryTypeService.isDistinctDicTypeCode(
				dictionaryTypeBean.getCode(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		if (!dictionaryTypeService.isDistinctDicTypeName(
				dictionaryTypeBean.getName(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		DictionaryType dictionaryType = BeanToModel
				.dictionaryBeanToDictionaryModel(dictionaryTypeBean);
		dictionaryType.setUpdatedTime(new Date());
		dictionaryType.setState(Constant.STATUS_START);
		dictionaryTypeService.createDicType(dictionaryType);
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 更新字典类型 param:dictionaryTypeBean
	 * 
	 * @return "success" 返回 flag,msg. 成功时flag为"true"
	 *         失败时flag为"false";msg为失败时的提示信息
	 */
	public String updateDictionaryType() {
		if (!dictionaryTypeService.isDistinctDicTypeName(
				dictionaryTypeBean.getName(), dictionaryTypeBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!dictionaryTypeService.isDistinctDicTypeCode(
				dictionaryTypeBean.getCode(), dictionaryTypeBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		DictionaryType newDictionaryType = dictionaryTypeService
				.findById(dictionaryTypeBean.getId());
		newDictionaryType.setName(dictionaryTypeBean.getName());
		newDictionaryType.setCode(dictionaryTypeBean.getCode());
		newDictionaryType.setClassifier(dictionaryTypeBean.getClassifier());
		newDictionaryType.setDescription(dictionaryTypeBean.getDescription());
		dictionaryTypeService.updateDicType(newDictionaryType);
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 初始化字典类型划分下拉列表
	 * 
	 * @return "success" 成功时返回dicTypeClassifierList 字典类型划分的List对象
	 */
	@SuppressWarnings("unchecked")
	public String initDicTypeClassifer() {
		String xql = "select distinct d.classifier from DictionaryType as d ";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		dicTypeClassifierList = this.dictionaryTypeService.findByXql(xql,
				xqlMap);
		return SUCCESS;
	}

	/**
	 * 初始化字典项树
	 * 
	 * @param searchingTypeId
	 *            字典类型Id
	 * @return "success" ztreeBeanList 字典项对应的树
	 */
	public String initDictionaryItemTree() {
		List<DictionaryItem> dictionaryItems = dictionaryItemService
				.findDicItemsByType(searchingTypeId, null);
		if (dictionaryItems != null) {
			ztreeBeanList = this.constructDictItemsTree(dictionaryItems);
		}
		return SUCCESS;
	}

	/**
	 * 根据字典项父节点Id查找所有子节点字典项
	 * 
	 * @param id 父节点id
	 * @return "success" ztreeBeanList 字典项对应的树
	 */
	public String findAllSubDictItemsByParId() {
		List<DictionaryItem> dictionaryItems = dictionaryItemService
				.findDicItemsByParent(id, null);
		if (dictionaryItems != null) {
			ztreeBeanList = this.constructDictItemsTree(dictionaryItems);
		}
		return SUCCESS;
	}

	/**
	 * 保存新增的字典项
	 * 
	 * @param dicItemBean
	 * @return "success" 返回flag,msg 成功时flag为"true" 失败时为"false"；msg为失败时的提示信息
	 */
	public String saveItem() {
		if (!dictionaryItemService.isDistinctDicItemName(dicItemBean.getName(),
				dicItemBean.getDicTypeId(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!dictionaryItemService.isDistinctDicItemCode(dicItemBean.getCode(), dicItemBean.getDicTypeId(), null)) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		DictionaryItem newDicItem = BeanToModel
				.dictionaryItemBeanToDictionaryItemModel(dicItemBean);
		int order = 0;
		if (StringUtils.isEmpty(dicItemBean.getParentItemId())) {
			order = dictionaryItemService.getMaxOrderByDicType(dicItemBean
					.getDicTypeId());
		} else {
			order = dictionaryItemService.getMaxOrderByDicItem(dicItemBean
					.getParentItemId());
		}
		newDicItem.setNumber(order + 1);
		dictionaryItemService.createDicItem(newDicItem,
				dicItemBean.getDicTypeId(), dicItemBean.getParentItemId());
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 启用/停用字典项
	 * 
	 * @param dicItemBean 字典项Bean
	 * @return "success"
	 */
	public String updateDictionaryItemStatus() {
		DictionaryItem di = dictionaryItemService.findById(dicItemBean.getId());
		di.setState(dicItemBean.getState());
		dictionaryItemService.updateDicItem(di, dicItemBean.getDicTypeId(),
				null);
		return SUCCESS;

	}

	/**
	 * 更新数据字典项
	 * 
	 * @param: dicItemBean 字典项对象
	 * @return "success" 返回flag、msg 成功时flag为"true"，失败时flag为"false"；msg为失败时的提示信息
	 */
	public String updateItem() {
		if (!dictionaryItemService.isDistinctDicItemName(dicItemBean.getName(),
				dicItemBean.getDicTypeId(), dicItemBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!dictionaryItemService.isDistinctDicItemCode(dicItemBean.getCode(), dicItemBean.getDicTypeId(), dicItemBean.getId())){
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		DictionaryItem di = dictionaryItemService.findById(dicItemBean.getId());
		di.setName(dicItemBean.getName());
		di.setCode(dicItemBean.getCode());
		di.setDescription(dicItemBean.getDescription());
		di.setState(dicItemBean.getState());
		dictionaryItemService.updateDicItem(di, dicItemBean.getDicTypeId(),
				null);
		this.setFlag(Boolean.TRUE.toString());
		return SUCCESS;
	}

	/**
	 * 点击查看数据字典项
	 * 
	 * @param:itemId
	 * @return "success",成功时返回dicItemBean 字典项Bean
	 */
	public String lookOverItem() {
		DictionaryItem dictionaryItem = dictionaryItemService.findById(itemId);
		if (dictionaryItem != null) {
			dicItemBean = ModelToBean
					.dictionaryItemToDictionaryItemBean(dictionaryItem);
			DictionaryType statuDictioaryType = dictionaryTypeService.findDicTypeByCode(Constant.STATUS);
			DictionaryItem di = dictionaryItemService
					.findDictionaryItemByDicTypeAndItemCode(statuDictioaryType.getId(), dictionaryItem.getState(), null);
			dicItemBean.setState(di.getName());
		}
		return SUCCESS;
	}

	/**
	 * 点击数据字典项上移
	 * 
	 * @param itemId
	 *            字典项Id
	 * @param itemPreId
	 *            上一级字典项Id
	 * @return "success"
	 */
	public String moveUpDictionaryItem() {
		dictionaryItemService.exchangeOrder(itemId, itemPreId);
		return SUCCESS;
	}

	/**
	 * 数据字典项下移
	 * 
	 * @param itemId
	 *            字典项Id
	 * @param itemNexId
	 *            下一级字典项Id
	 * @return "success"
	 */
	public String moveDownDictionaryItem() {
		dictionaryItemService.exchangeOrder(itemId, itemNexId);
		return SUCCESS;
	}

	/**
	 * 构造字典项树
	 * 
	 * @param dictionaryItems
	 *            字典项List对象
	 * @return List<ZTreeBean> 字典项树
	 */
	private List<ZTreeBean> constructDictItemsTree(
			List<DictionaryItem> dictionaryItems) {
		List<ZTreeBean> newZtreeBeanList = new ArrayList<ZTreeBean>();
		for (DictionaryItem dicItemTemp : dictionaryItems) {
			ZTreeBean bean = new ZTreeBean();
			bean.setId(dicItemTemp.getId());
			if (dicItemTemp.getParentItem() != null) {
				bean.setpId(dicItemTemp.getParentItem().getId());
			}
			if (dicItemTemp.getSubItems() != null
					&& !dicItemTemp.getSubItems().isEmpty()) {
				bean.setIsParent(Boolean.TRUE.toString());
			} else {
				bean.setIsParent(Boolean.FALSE.toString());
			}
			bean.setName(dicItemTemp.getName());
			bean.setOpen(Boolean.FALSE.toString());
			if (dicItemTemp.getState().equals(Constant.STATUS_STOP)) {
				bean.setIcon(STOP_LOGO);
			} else {
				bean.setIcon(START_LOGO);
			}
			newZtreeBeanList.add(bean);
		}
		return newZtreeBeanList;
	}
	
	/**
	 * 页面跳转
	 * @return
	 */
	public String toDictionaryType(){
		//页面跳转
		return SUCCESS;
	}

	public String getClassifer() {
		return classifer;
	}

	public void setClassifer(String classifer) {
		this.classifer = classifer;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemParentId() {
		return itemParentId;
	}

	public void setItemParentId(String itemParentId) {
		this.itemParentId = itemParentId;
	}

	public List<DictionaryItemBean> getDictionaryItemList() {
		return dictionaryItemList;
	}

	public void setDictionaryItemList(
			List<DictionaryItemBean> dictionaryItemList) {
		this.dictionaryItemList = dictionaryItemList;
	}

	public String getDictionaryTypeName() {
		return dictionaryTypeName;
	}

	public void setDictionaryTypeName(String dictionaryTypeName) {
		this.dictionaryTypeName = dictionaryTypeName;
	}

	public DictionaryTypeBean getDictionaryTypeBean() {
		return dictionaryTypeBean;
	}

	public void setDictionaryTypeBean(DictionaryTypeBean dictionaryTypeBean) {
		this.dictionaryTypeBean = dictionaryTypeBean;
	}

	public String getSearchingTypeId() {
		return searchingTypeId;
	}

	public void setSearchingTypeId(String searchingTypeId) {
		this.searchingTypeId = searchingTypeId;
	}

	public String getItemPreId() {
		return itemPreId;
	}

	public void setItemPreId(String itemPreId) {
		this.itemPreId = itemPreId;
	}

	public String getItemNexId() {
		return itemNexId;
	}

	public void setItemNexId(String itemNexId) {
		this.itemNexId = itemNexId;
	}

	public List<DictionaryTypeBean> getDictionaryTypeBeanList() {
		return dictionaryTypeBeanList;
	}

	public void setDictionaryTypeBeanList(
			List<DictionaryTypeBean> dictionaryTypeBeanList) {
		this.dictionaryTypeBeanList = dictionaryTypeBeanList;
	}

	public Pager<DictionaryTypeBean> getDictionaryTypeBeanPager() {
		return dictionaryTypeBeanPager;
	}

	public void setDictionaryTypeBeanPager(
			Pager<DictionaryTypeBean> dictionaryTypeBeanPager) {
		this.dictionaryTypeBeanPager = dictionaryTypeBeanPager;
	}

	public List<ZTreeBean> getZtreeBeanList() {
		return ztreeBeanList;
	}

	public void setZtreeBeanList(List<ZTreeBean> ztreeBeanList) {
		this.ztreeBeanList = ztreeBeanList;
	}

	public DictionaryItemBean getDicItemBean() {
		return dicItemBean;
	}

	public void setDicItemBean(DictionaryItemBean dicItemBean) {
		this.dicItemBean = dicItemBean;
	}

	public List<String> getDicTypeClassifierList() {
		return dicTypeClassifierList;
	}

	public void setDicTypeClassifierList(List<String> dicTypeClassifierList) {
		this.dicTypeClassifierList = dicTypeClassifierList;
	}
}
