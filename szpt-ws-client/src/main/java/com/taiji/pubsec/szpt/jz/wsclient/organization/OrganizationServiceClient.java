package com.taiji.pubsec.szpt.jz.wsclient.organization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.jz.wsclient.WebServiceClient;
import com.taiji.pubsec.szpt.jz.wsclient.util.DataSyncConstant;
import com.taiji.pubsec.szpt.jz.wsclient.util.JingZongBasicDataTransformation;
import com.taiji.pubsec.szpt.jz.wsclient.util.WebServiceDateFormat;

@Service("orgServiceClient")
@Transactional(rollbackFor = Exception.class)
public class OrganizationServiceClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationServiceClient.class);

	@Value("${org_user.orgservice.wsdl}")
	private String wsUrl;
	@Value("${org_user.orgservice.methodname}")
	private String wsMethod;
	@Value("${org_user.namespace}")
	private String wsNamespace;

	@Resource
	private IUnitService unitService;

	/**
	 * 增量同步组织机构数据
	 * @throws Exception
	 */
	public void syncWithIncrements() throws Exception {
		LOGGER.debug("ws parameters are : {}, {}, {}", new Object[] { wsUrl,
				wsMethod, wsNamespace });
		int totalPage = 1;
		int currentPage = 0;
		String latestTime = acquireOrganizationLatestUpdateTime();
		LOGGER.debug("last time : {}", latestTime);
		List<JingZongOrganization> jingZongOrganizations = new ArrayList<>();
		do {
			List<Object> params = produceOrganizationParameter(latestTime, currentPage);
			String result = WebServiceClient.getDateFromWs(getWsUrl(),
					getWsMethod(), getWsNamespace(), params);
			LOGGER.debug("ws return json:{}", result);
			JingZongOrganizationResponse jingZongOrganizationResponse = JingZongBasicDataTransformation
					.stringResponse2JingZongOrganizationResponse(result);
			jingZongOrganizations
					.addAll(jingZongOrganizationResponse.getData());
			currentPage = jingZongOrganizationResponse.getPageInfo()
					.getCurrentPage();
			totalPage = jingZongOrganizationResponse.getPageInfo()
					.getTotalPage();
			currentPage++;
		} while (currentPage < totalPage);
		saveOrUpdateOrganization(jingZongOrganizations);
	}

	private List<Object> produceOrganizationParameter(String latestTime,
			int currentPage) {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.clear();
		parameterValue.add(""); // arg0 机构编码
		parameterValue.add(DataSyncConstant.JINGKAIQU_QUHUADAIMA); // arg1
																	// 行政区划编码
		parameterValue.add(""); // arg2 警钟类别编码
		parameterValue.add(""); // arg3 机构类别
		parameterValue.add(""); // arg4 业务管辖上级
		parameterValue.add(""); // arg5 行政管辖上级
		parameterValue.add(StringUtils.defaultIfBlank(latestTime, "")); // arg6 时间戳
		parameterValue.add(currentPage); // arg7 页码
		return parameterValue;
	}

	private String acquireOrganizationLatestUpdateTime() {
		String hql = "select max(updatedTime) from Unit";
		String latestTime = "";
		Date date = (Date) unitService.findByXql(hql,
				new HashMap<String, Object>()).get(0);
		latestTime = WebServiceDateFormat.date2str(date);
		return latestTime;
	}

	private void saveOrUpdateOrganization(
			List<JingZongOrganization> jingZongOrganizations) {

		Collections.sort(jingZongOrganizations,
				new Comparator<JingZongOrganization>() {

					@Override
					public int compare(JingZongOrganization o1,
							JingZongOrganization o2) {
						return o1.getJgdm().compareTo(o2.getJgdm());
					}
				});

		for (JingZongOrganization jingZongOrganization : jingZongOrganizations) {
			boolean newData = false;
			Unit unit = unitService.findUnitByCode(jingZongOrganization
					.getJgdm());
			if (unit == null) {
				unit = new Unit();
				newData = true;
			}
			unit.setType(null);
			unit.setCode(jingZongOrganization.getJgdm());
			unit.setName(jingZongOrganization.getJgmc());
			unit.setShortName(jingZongOrganization.getJgsmc());
			unit.setTelephone(jingZongOrganization.getLxdh());
			unit.setFax(jingZongOrganization.getDwcz());
			unit.setAddress(jingZongOrganization.getXxdz());
			unit.setRemark(null);
			unit.setStatus(JingZongBasicDataTransformation
					.JingZongOrganizationState2BusinessSystemStateCode(jingZongOrganization
							.getState()));
			Long l = jingZongOrganization.getModifiedtime();
			if (l != null) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(l);
				unit.setUpdatedTime(c.getTime());
			}
			Unit parUnit = unitService.findUnitByCode(jingZongOrganization
					.getZxgxsj());
			unit.setSuperOrg(parUnit);
			if (newData) {
				unitService.save(unit);
			} else {
				unitService.update(unit);
			}

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
