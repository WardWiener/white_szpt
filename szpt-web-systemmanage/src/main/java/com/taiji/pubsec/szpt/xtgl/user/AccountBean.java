package com.taiji.pubsec.szpt.xtgl.user;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.taiji.pubsec.szpt.xtgl.person.PersonBean;
import com.taiji.pubsec.szpt.xtgl.role.RoleBean;
import com.taiji.pubsec.businesscomponents.organization.model.Person;

/**
 * 账户Bean
 * @author xinfan
 *
 */
public class AccountBean {
	private String id;
	/**
	 * 帐号名
	 */
	private String accountName;
	/**
	 * 对应人员的分页信息
	 */
	private int num;
	/**
	 * 人员姓名
	 */
	private String personName;
	/**
	 * 指挥单元Id
	 */
	private String orderCellId;
	
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 有效时间
	 */
	private Date startDate;
	/**
	 * 备注
	 */
	private String intro;
	/**
	 * 失效时间
	 */
	private Date endDate;
	/**
	 * 时间戳
	 */
	private Date updatedTime;
	/**
	 * 所属id
	 */
	private String unitId;
	/**
	 * 单位编码
	 */
	private String unitCode;
	/**
	 * 所属单位名称
	 */
	private String unitName;
	/**
	 * 人员
	 */
	private PersonBean personBean;
	/**
	 * 是否是管理员
	 */
	private String isAdmin;
	/**
	 * 人员权限
	 */
	public static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	private List<RoleBean> roleBeans;
	private String strStartDate;
	private String strUpdatedTime;
	private String strEndDate;
	private SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.strStartDate=startDate==null?"":
			DateFormatUtils.format(startDate, DATE_FORMAT);
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.strEndDate=endDate==null?"":
			DateFormatUtils.format(endDate, DATE_FORMAT);
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
		this.strUpdatedTime=updatedTime==null?"":
			DateFormatUtils.format(updatedTime,DATE_FORMAT);
	}
	public String getStrStartDate() {
		return strStartDate;
	}
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
		try {
			this.startDate=format.parse(strStartDate);
		} catch (ParseException e) {
			startDate=null;
		}
	}
	public String getStrUpdatedTime() {
		return strUpdatedTime;
	}
	public void setStrUpdatedTime(String strUpdatedTime) {
		this.strUpdatedTime = strUpdatedTime;
		try {
			this.updatedTime=format.parse(strUpdatedTime);
		} catch (ParseException e) {
			updatedTime=null;
		}
	}
	public String getStrEndDate() {
		return strEndDate;
	}
	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
		try {
			this.endDate=format.parse(strEndDate);
		} catch (ParseException e) {
			endDate=null;
		}
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<RoleBean> getRoleBeans() {
		return roleBeans;
	}
	public void setRoleBeans(List<RoleBean> roleBeans) {
		this.roleBeans = roleBeans;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getOrderCellId() {
		return orderCellId;
	}
	public void setOrderCellId(String orderCellId) {
		this.orderCellId = orderCellId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public PersonBean getPersonBean() {
		return personBean;
	}
	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}
	
}

