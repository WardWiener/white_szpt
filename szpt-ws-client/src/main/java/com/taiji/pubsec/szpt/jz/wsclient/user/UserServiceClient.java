package com.taiji.pubsec.szpt.jz.wsclient.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IAccountService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.jz.wsclient.WebServiceClient;
import com.taiji.pubsec.szpt.jz.wsclient.util.JingZongBasicDataTransformation;
import com.taiji.pubsec.szpt.jz.wsclient.util.WebServiceDateFormat;

@Service("userServiceClient")
@Transactional(rollbackFor = Exception.class)
public class UserServiceClient {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceClient.class);

	@Value("${org_user.userservice.wsdl}")
	private String wsUrl;
	@Value("${org_user.userservice.methodname.getUserlist}")
	private String wsMethod;
	@Value("${org_user.namespace}")
	private String wsNamespace;

	@Resource
	private IUnitService unitService;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private IAccountService accountService;
	

	/**
	 * 增量同步用户数据的同时同步账号数据，同步用户数据之前需要先同步组织机构数据
	 * @throws Exception
	 */
	public void syncWithIncrements() throws Exception {
		LOGGER.debug("ws parameters are : {}, {}, {}", new Object[] { wsUrl,
				wsMethod, wsNamespace });
		List<Unit> units = unitService.findAll();
		String latestTime = acquirePersonLatestUpdateTime();
		LOGGER.debug("last time : {}", latestTime);
		for (Unit unit : units) {
			int totalPage = 1;
			int currentPage = 0;
			do {
				List<Object> params = produceUserParameter(unit.getCode(), latestTime, currentPage);
				String result = WebServiceClient.getDateFromWs(getWsUrl(),
						getWsMethod(), getWsNamespace(), params);
				LOGGER.debug("ws return json:{}", result);
				JingZongUserResponse jingZongUserResponse = JingZongBasicDataTransformation.
						stringResponse2JingZongUserResponse(result);
				currentPage = jingZongUserResponse.getPageInfo().getCurrentPage();
				totalPage = jingZongUserResponse.getPageInfo().getTotalPage();
				currentPage++;
				saveOrUpdatePerson(jingZongUserResponse.getData());
			} while (currentPage < totalPage);
		}

	}

	
	
	private List<Object> produceUserParameter(String unitCode, String lastTime, int currentPage) {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.clear();
		parameterValue.add("");	//arg0  身份证号
		parameterValue.add("");	//arg1 隶属关系
		parameterValue.add(unitCode);	//arg2 单位编码
		parameterValue.add("");	//arg3 行政区划编码
		parameterValue.add("");	//arg4 警种类别编码
		parameterValue.add(""); //arg5 用户类别编码
		parameterValue.add("");	//arg6 机构类别
		parameterValue.add("");	//arg7 业务管辖上级
		parameterValue.add("");	//arg8  行政管辖上级
		parameterValue.add(StringUtils.defaultIfBlank(lastTime, ""));	//arg9  时间戳
		parameterValue.add(currentPage);	//arg10 查询页页码
		return parameterValue;
	}

	
	private String acquirePersonLatestUpdateTime() {
		String hql = "select max(updatedTime) from Person ";
		String latsatUpdatedTime = "";
		Date latestUpdateTime = (Date) personService.findByXql(hql, new HashMap<String, Object> ()).get(0);
		latsatUpdatedTime = WebServiceDateFormat.date2str(latestUpdateTime);
		return latsatUpdatedTime;
	}

	
	
	/**
	 * TODO
	 * 警综人员：职务、民族、政治面貌、学历、移动电话、办公电话
	 * @param jingZongUsers
	 */
	private void saveOrUpdatePerson(List<JingZongUser> jingZongUsers){
		for (JingZongUser jingZongUser: jingZongUsers) {
			boolean newData = false;
			String hql = "select p from Person as p where p.idNumber = :idnumber";
			Map<String, Object> xqlMap = new HashMap<String, Object>();
			xqlMap.put("idnumber", jingZongUser.getGmsfhm());
			List<Person> persons = personService.findAllByParams(hql, xqlMap);
			Person person;
			if(persons.isEmpty()) {
				person = new Person();
				newData = true;
			}else{
				person = persons.get(0);
			}
			person.setSex(JingZongBasicDataTransformation.
					JingZongUserRyxb2BusinessSystemSexCode(jingZongUser.getRyxb()));
			person.setName(jingZongUser.getZsxm());
			Unit unit = unitService.findUnitByCode(jingZongUser.getSsdw_gajgjgdm());
			person.setOrganization(unit);
			person.setCode(jingZongUser.getJybh());
			person.setIdNumber(jingZongUser.getGmsfhm());
			person.setStatus(JingZongBasicDataTransformation.
					JingZongUserYhzt2BusinessSystemStateCode(jingZongUser.getYhzt()));
			Long l = jingZongUser.getModifiedtime();
			if(l != null) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(l);
				person.setUpdatedTime(c.getTime());
			}
			if (newData) {
				personService.save(person);
			}else {
				personService.update(person);
			}
			saveOrUpdateAccount(jingZongUser);
		}
	}
	
	/**
	 * 账号登陆密码默认为登陆名称
	 * @param jingZongUser
	 */
	private void saveOrUpdateAccount(JingZongUser jingZongUser) {
		boolean newAccount = false;
		String hql = "select a from Account as a where a.person.idNumber = :idnumber";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("idnumber", jingZongUser.getGmsfhm());
		List<Account> accounts = accountService.findAllByParams(hql, xqlMap);
		Account account;
		if(accounts.isEmpty()) {
			account = new Account();
			newAccount = true;
		}else{
			account = accounts.get(0);
		}
		account.setAccountName(jingZongUser.getDlmc());
		account.setPassword(jingZongUser.getDlmc());
		account.setStatus(JingZongBasicDataTransformation.
				JingZongUserYhzt2BusinessSystemStateCode(jingZongUser.getYhzt()));
		Long startDate = jingZongUser.getYxqqsrq();
		if(startDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(startDate);
			account.setStartDate(c.getTime());
		}
		String hqlp = "select p from Person as p where p.idNumber = :idnumber";
		Map<String, Object> xqlMapp = new HashMap<String, Object>();
		xqlMapp.put("idnumber", jingZongUser.getGmsfhm());
		List<Person> persons = personService.findAllByParams(hqlp, xqlMapp);
		if(!persons.isEmpty()){
			account.setPerson(persons.get(0));
		}
		Long endDate = jingZongUser.getYxqjzrq();
		if (endDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(endDate);
			account.setEndDate(c.getTime());
		}
		Long updatedTime = jingZongUser.getModifiedtime();
		if (updatedTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(updatedTime);
			account.setUpdatedTime(c.getTime());
		}
		if(newAccount) {
			accountService.save(account);
		}else{
			accountService.update(account);
		}
	}


	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public String getWsMethod() {
		return wsMethod;
	}

	public void setWsMethod(String wsMethod) {
		this.wsMethod = wsMethod;
	}

	public String getWsNamespace() {
		return wsNamespace;
	}

	public void setWsNamespace(String wsNamespace) {
		this.wsNamespace = wsNamespace;
	}
}
