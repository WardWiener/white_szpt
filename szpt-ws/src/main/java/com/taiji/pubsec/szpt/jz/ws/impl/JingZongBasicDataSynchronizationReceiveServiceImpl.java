package com.taiji.pubsec.szpt.jz.ws.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IAccountService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.jz.ws.JingZongBasicDataSynchronizationReceiveService;
import com.taiji.pubsec.szpt.jz.ws.pojo.JingZongOrganizationPushDataJson;
import com.taiji.pubsec.szpt.jz.ws.pojo.JingZongUserPushDataJson;
import com.taiji.pubsec.szpt.jz.ws.util.DataSyncConstant;
import com.taiji.pubsec.szpt.jz.ws.util.JingZongBasicDataTransformation;

public class JingZongBasicDataSynchronizationReceiveServiceImpl implements
		JingZongBasicDataSynchronizationReceiveService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JingZongBasicDataSynchronizationReceiveServiceImpl.class);
	/**
	 * 存放返回数据
	 */
	private static Map<String, Object> resultMap = new HashMap<String, Object>();

	private static Gson gson = new GsonBuilder().create();
	private static JsonParser parser = new JsonParser();

	@Resource
	private IUnitService unitService;

	@Resource
	private IPersonService personService;

	@Resource
	private IAccountService accountService;

	@Override
	public String receiveDataFromBusService(String batchNo, String identify,
			String dataJson) {
		LOGGER.debug("------------进入服务------------");
		resultMap.clear();
		if (StringUtils.isBlank(batchNo)) {
			resultMap.put("isSucceed", false);
			resultMap.put("remark", "批次号不能为空");
			resultMap.put("batchNo", "");
		} else if (StringUtils.isBlank(dataJson)) {
			resultMap.put("isSucceed", false);
			resultMap.put("remark", "数据体不能为空");
			resultMap.put("batchNo", batchNo);
		} else if (StringUtils.isBlank(identify)) {
			resultMap.put("isSucceed", false);
			resultMap.put("remark", "标识号不能为空");
			resultMap.put("batchNo", batchNo);
		} else {
			try {
				JsonElement root = parser.parse(dataJson);
				JsonObject element = root.getAsJsonObject();
				JsonPrimitive methodJson = element.getAsJsonPrimitive("method");
				String method = methodJson.getAsString();
				if (DataSyncConstant.JINGZONG_BUSSERVICE_METHOD_IDENTIFY_USER
						.equals(method)) {
					List<JingZongUserPushDataJson> data = gson.fromJson(
							element.getAsJsonPrimitive("data"),
							new TypeToken<List<JingZongUserPushDataJson>>() {
							}.getType());
					for (JingZongUserPushDataJson jingZongUser : data) {
						saveOrUpdatePerson(jingZongUser);
						saveOrUpdateAccount(jingZongUser);
					}

				} else if (DataSyncConstant.JINGZONG_BUSSERVICE_METHOD_IDENTIFY_ORG
						.equals(method)) {
					List<JingZongOrganizationPushDataJson> data = gson
							.fromJson(
									element.getAsJsonPrimitive("data"),
									new TypeToken<List<JingZongOrganizationPushDataJson>>() {
									}.getType());
					for (JingZongOrganizationPushDataJson jingZongOrganization : data) {
						saveOrUpdateOrganization(jingZongOrganization);
					}

				} else {
					// TODO 其他推送数据
					LOGGER.info("未识别推送数据类型");
				}
				resultMap.put("isSucceed", true);
				resultMap.put("remark", "操作成功");
				resultMap.put("batchNo", batchNo);
			} catch (Exception e) {
				LOGGER.error("", e);;
				resultMap.put("isSucceed", false);
				resultMap.put("remark", "操作失败");
				resultMap.put("batchNo", batchNo);
			}
		}
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(resultMap);
		LOGGER.debug("处理结果：{}", response);
		return response;
	}

	private void saveOrUpdatePerson(JingZongUserPushDataJson jingZongUser) {
		boolean newData = false;
		Person person;
		if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_SAVE.equals(jingZongUser
				.getFLAG())) {
			newData = true;
			person = new Person();
		} else if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_UPDATE
				.equals(jingZongUser.getFLAG())) {
			String hql = "select p from Person as p where p.idNumber = :idnumber";
			Map<String, Object> xqlMap = new HashMap<String, Object>();
			xqlMap.put("idnumber", jingZongUser.getGMSFHM());
			List<Person> persons = personService.findAllByParams(hql, xqlMap);
			person = persons.get(0);
		} else {
			LOGGER.info("未知数据更新标识符");
			return;
		}
		person.setSex(JingZongBasicDataTransformation
				.JingZongUserRyxb2BusinessSystemSexCode(jingZongUser.getRYXB()));
		person.setName(jingZongUser.getZSXM());
		Unit unit = unitService.findUnitByCode(jingZongUser.getSSJG()
				.get("SSDW_GAJGJGDM").toString());
		person.setOrganization(unit);
		person.setCode(jingZongUser.getJYBH());
		person.setIdNumber(jingZongUser.getGMSFHM());
		person.setStatus(JingZongBasicDataTransformation
				.JingZongUserYhzt2BusinessSystemStateCode(jingZongUser
						.getYHZT()));
		Long l = jingZongUser.getMODIFIEDTIME();
		if (l != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(l);
			person.setUpdatedTime(c.getTime());
		}
		if (newData) {
			personService.save(person);
		} else {
			personService.update(person);
		}
	}

	private void saveOrUpdateAccount(JingZongUserPushDataJson jingZongUser) {
		boolean newAccount = false;
		Account account;
		if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_SAVE.equals(jingZongUser
				.getFLAG())) {
			account = new Account();
			newAccount = true;
		} else if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_UPDATE
				.equals(jingZongUser.getFLAG())) {
			String hql = "select a from Account as a where a.person.idNumber = :idnumber";
			Map<String, Object> xqlMap = new HashMap<String, Object>();
			xqlMap.put("idnumber", jingZongUser.getGMSFHM());
			List<Account> accounts = accountService
					.findAllByParams(hql, xqlMap);
			account = accounts.get(0);
		} else {
			LOGGER.info("未知数据更新标识");
			return;
		}
		account.setAccountName(jingZongUser.getDLMC());
		account.setPassword(jingZongUser.getDLMC());
		account.setStatus(JingZongBasicDataTransformation
				.JingZongUserYhzt2BusinessSystemStateCode(jingZongUser
						.getYHZT()));
		Long startDate = jingZongUser.getYXQQSRQ();
		if (startDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(startDate);
			account.setStartDate(c.getTime());
		}
		String hqlp = "select p from Person as p where p.idNumber = :idnumber";
		Map<String, Object> xqlMapp = new HashMap<String, Object>();
		xqlMapp.put("idnumber", jingZongUser.getGMSFHM());
		List<Person> persons = personService.findAllByParams(hqlp, xqlMapp);
		if (!persons.isEmpty()) {
			account.setPerson(persons.get(0));
		}
		Long endDate = jingZongUser.getYXQJZRQ();
		if (endDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(endDate);
			account.setEndDate(c.getTime());
		}
		Long updatedTime = jingZongUser.getMODIFIEDTIME();
		if (updatedTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(updatedTime);
			account.setUpdatedTime(c.getTime());
		}
		if (newAccount) {
			accountService.save(account);
		} else {
			accountService.update(account);
		}
	}

	private void saveOrUpdateOrganization(
			JingZongOrganizationPushDataJson jingZongOrganization) {
		boolean newData = false;
		Unit unit;
		if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_SAVE
				.equals(jingZongOrganization.getFLAG())) {
			unit = new Unit();
			newData = true;
			// 推送的数据中没有“机构树名称” 新增时不赋值
			unit.setShortName("");
		} else if (DataSyncConstant.JINGZONG_BUSSERVICE_FLAG_UPDATE
				.equals(jingZongOrganization.getFLAG())) {
			unit = unitService.findUnitByCode(jingZongOrganization.getJGDM());
			// 推送的数据中没有"机构树名称"，修改时不修改时
		} else {
			LOGGER.info("未知数据更新标识符");
			return;
		}
		unit.setType(null);
		unit.setCode(jingZongOrganization.getJGDM());
		unit.setName(jingZongOrganization.getJGMC());
		unit.setTelephone(jingZongOrganization.getLXDH());
		unit.setFax(jingZongOrganization.getDWCZ());
		unit.setAddress(jingZongOrganization.getXXDZ());
		unit.setRemark(null);
		unit.setStatus(JingZongBasicDataTransformation
				.JingZongOrganizationState2BusinessSystemStateCode(jingZongOrganization
						.getSTATE()));
		Long l = jingZongOrganization.getMODIFIEDTIME();
		if (l != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(l);
			unit.setUpdatedTime(c.getTime());
		}
		Unit parUnit = unitService.findUnitByCode(jingZongOrganization
				.getZXGXSJ());
		unit.setSuperOrg(parUnit);
		if (newData) {
			unitService.save(unit);
		} else {
			unitService.update(unit);
		}
	}
}
