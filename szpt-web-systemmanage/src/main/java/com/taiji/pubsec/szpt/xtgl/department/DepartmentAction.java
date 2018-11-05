package com.taiji.pubsec.szpt.xtgl.department;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.xtgl.BeanToModel;
import com.taiji.pubsec.szpt.xtgl.Constant;
import com.taiji.pubsec.szpt.xtgl.ModelToBean;
import com.taiji.pubsec.szpt.xtgl.PageCommonSysManageAction;
import com.taiji.pubsec.szpt.xtgl.ZTreeBean;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Department;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IDepartmentService;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.organization.service.UnitServiceImpl;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;


/**
 * 部门
 * 
 * @author xinfan
 *
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends PageCommonSysManageAction {
	private static final long serialVersionUID = 1L;
	private static final String UNIT_LOGO = "../images/xtgl_icon/ztree-icon_dw.png";
	private static final String DEP_LOGO = "../images/xtgl_icon/ztree-icon_bm.png";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnitServiceImpl.class);
	@Resource(name = "departmentService")
	private IDepartmentService departmentService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource(name = "unitService")
	private IUnitService unitService;
	@Resource
	private IOrganizationService organizationServiceImpl;
	@Resource
	private IPersonService personService;
	private List<ZTreeBean> departmentTree;
	private DepartmentBean departmentBean;
	private String unitId;

	/**
	 * 初始化部门树
	 * 
	 * @param unitId 单位Id
	 *            
	 * @return "success" List<ZTreeBean> 部门树
	 */
	public String initDepartmentTree() {
		departmentTree = new ArrayList<ZTreeBean>();
		String currentUnittId = "";
		if (unitId == null) {
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			Map<String, String> mOrg = (Map<String, String>) userMap.get("org");
			if (mOrg != null) {
				ZTreeBean ztbTemp = new ZTreeBean();
				currentUnittId = mOrg.get("id");
				ztbTemp.setId(currentUnittId);
				ztbTemp.setParentId(null);
				ztbTemp.setName(mOrg.get("shortName"));
				ztbTemp.setCode(mOrg.get("code"));
				ztbTemp.setOpen("true");
				ztbTemp.setIsParent(Constant.UNIT_ISPAR_TRUE);
				ztbTemp.setIcon(UNIT_LOGO);
				departmentTree.add(ztbTemp);
			}
		} else {
			currentUnittId = unitId;
		}
		initSubUnitTree(departmentTree, currentUnittId);
		initSubDeparTree(departmentTree, currentUnittId);
		return SUCCESS;
	}

	/**
	 * 此方法包含两种情况 1 查找当前单位的第一个部门, 2 根据Id查找部门
	 * @param departmentBean 部门bean
	 * @return "success" 成功时返回 departmentBean 部门Bean
	 */
	public String findDeparmentById() {
		if (departmentBean.getId().equals(Constant.ORGANIZATION_INIT)) {
			MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
			Map<String, Object> userMap = user.getUserMap();
			Map<String, String> mOrg = (Map<String, String>) userMap.get("org");
			if (userMap.get("org") != null) {
				String hql = "from Department where superOrg.id = ? order by code";
				List<Department> departmentTempList = departmentService
						.findAllByParams(hql, new Object[] { mOrg.get("id") });
				if (!departmentTempList.isEmpty()) {
					departmentBean = ModelToBean
							.departmentToDepartmentBean(departmentTempList
									.get(0));
					this.setFlag(Boolean.TRUE.toString());
				} else {
					this.setFlag(Boolean.FALSE.toString());
					return SUCCESS;
				}
			}
		} else {
			Department department = departmentService.findById(departmentBean
					.getId());
			if (department == null) {
				this.setFlag(Boolean.FALSE.toString());
				return SUCCESS;
			}
			departmentBean = ModelToBean.departmentToDepartmentBean(department);
			this.setFlag(Boolean.TRUE.toString());
		}
		return SUCCESS;
	}


	/**
	 * 保存部门
	 * 
	 * @Param departmentBean 部门Bean
	 * @return  "success" 返回 flag、msg、departmentBean; 成功时flag为"true"
	 *         失败时flag为"false"；msg 返回失败时的提示信息
	 */
	public String addDeparment() {
		if (!departmentService.isDistinctDepartmentCode(departmentBean
				.getDepartmentNum())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;

		}
		if (!departmentService.isDistinctDepartmentName(departmentBean
				.getDepartmentName())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		Department department = BeanToModel
				.departmentBeanToDepartment(departmentBean);
		department.setUpdatedTime(new Date());
		Organization departmentTemp = organizationServiceImpl
				.findOrganizationById(departmentBean.getParDepartmentId());
		departmentService.createDepartment(department, departmentTemp.getId());
		departmentBean.setId(department.getId());
		return SUCCESS;
	}

	/**
	 * 删除部门
	 * 
	 * @param departmentBean
	 *            部门Id
	 * @return "success" 返回 flag、msg 成功时flag为"true"
	 *         失败时flag为"false"；msg为返回失败时的提示信息
	 */
	public String deleteDepartment() {
		try {
			this.setFlag(Boolean.FALSE.toString());
			List<Department> subDeparmentList = departmentService.findSubDepartmentsByDepartmentId(departmentBean
			.getId());
			//subDeparmentList包含此部门本身以及其所有下属部门
			if(subDeparmentList != null && subDeparmentList.size() > 1){
				this.setMsg("此部门包含下属部门,不允许删除");
				return SUCCESS;
			}
			List<Person> personList = personService.findPersonsByUnitAndPersonName(departmentBean.getId(), null);
			if (personList != null && !personList.isEmpty()){
				this.setMsg("此部门已分配人员,不允许删除");
				return SUCCESS;
			}
			departmentService.deleteDepartment(departmentBean.getId());
			this.setFlag(Boolean.TRUE.toString());
		} catch (Exception e) {
			LOGGER.debug("已有外键关联，不允许删除", e);
			this.setMsg("此单位已被关联，不允许删除");
			this.setFlag(Boolean.FALSE.toString());
		}
		return SUCCESS;
	}

	/**
	 * 修改部门
	 * 
	 * @param departmentBean
	 *            部门Bean
	 * @return "success" 返回 flag、msg 成功时flag为"true"
	 *         失败时flag为"false"；msg为返回失败时的提示信息
	 */
	public String modifyDepartment() {
		if (!departmentService.isDistinctDepartmentName(departmentBean.getDepartmentName(), departmentBean.getId())
				) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("名称重复");
			return SUCCESS;
		}
		if (!departmentService.isDistinctDepartmentCode(departmentBean
				.getDepartmentNum(), departmentBean.getId())) {
			this.setFlag(Boolean.FALSE.toString());
			this.setMsg("编码重复");
			return SUCCESS;
		}
		Department department = BeanToModel
				.departmentBeanToDepartment(departmentBean);
		department.setUpdatedTime(new Date());
		Organization departmentTemp = organizationServiceImpl
				.findOrganizationById(departmentBean.getParDepartmentId());
		departmentService.updateDepartment(department, departmentTemp.getId());
		return SUCCESS;
	}

	/**
	 * 停用部门
	 * 
	 * @param departmentBean
	 *            部门Bean
	 * @return "success"
	 */
	public String disableControl() {
		Department departmentTemp = departmentService
				.findDepartmentByCode(departmentBean.getDepartmentCode());
		departmentService.updateDepartmentState(departmentTemp.getId(),
				departmentBean.getStatus());
		return SUCCESS;
	}

	/**
	 * 启用部门
	 * 
	 * @param departmentBean
	 *            部门Bean
	 * @return "success"
	 */

	public String enableControl() {
		Department departmentTemp = departmentService
				.findDepartmentByCode(departmentBean.getDepartmentCode());
		departmentService.updateDepartmentState(departmentTemp.getId(),
				departmentBean.getStatus());
		return SUCCESS;
	}

	/**
	 * 初始化子单位树
	 * 
	 * @param departmentTreeTemp
	 *            部门树
	 * @param orgId
	 *            部门Id
	 * @return
	 */
	private void initSubUnitTree(List<ZTreeBean> departmentTreeTemp,
			String orgId) {
		String hqlunit = " from Unit where superOrg.id = ? order by code";
		List <Unit> unitList = unitService.findAllByParams(hqlunit, new Object[] { orgId });
		for (Unit unit : unitList) {
			ZTreeBean ztb = new ZTreeBean();
			ztb.setId(unit.getId());
			ztb.setParentId(unit.getSuperOrg() == null ? null : unit
					.getSuperOrg().getId());
			ztb.setName(unit.getShortName());
			ztb.setCode(unit.getCode());
			ztb.setIsParent(unit.getSubOrgs().isEmpty() ? Constant.UNIT_ISPAR_FALSE
					: Constant.UNIT_ISPAR_TRUE);
			ztb.setIsUnit("unit");
			ztb.setIcon(UNIT_LOGO);
			departmentTreeTemp.add(ztb);
		}
	}

	/**
	 * 初始化子部门
	 * 
	 * @param departmentTreeTemp
	 *            部门树
	 * @param orgId
	 *            部门Id
	 * @return
	 */
	private void initSubDeparTree(List<ZTreeBean> departmentTreeTemp,
			String orgId) {
		String hql = " from Department where superOrg.id = ? order by code";
		List<Department> departmentList = departmentService.findAllByParams(hql,
				new Object[] { orgId });
		for (Department department : departmentList) {
			ZTreeBean ztb = new ZTreeBean();
			ztb.setId(department.getId());
			ztb.setParentId(department.getSuperOrg() == null ? null
					: department.getSuperOrg().getId());
			ztb.setName(department.getShortName());
			ztb.setCode(department.getCode());
			ztb.setIsParent(department.getSubOrgs().isEmpty() ? Constant.UNIT_ISPAR_FALSE
					: Constant.UNIT_ISPAR_TRUE);
			ztb.setIsUnit("department");
			ztb.setIcon(DEP_LOGO);
			departmentTreeTemp.add(ztb);
		}
	}

	public List<ZTreeBean> getDepartmentTree() {
		return departmentTree;
	}

	public void setDepartmentTree(List<ZTreeBean> departmentTree) {
		this.departmentTree = departmentTree;
	}


	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}

	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}

	

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}
