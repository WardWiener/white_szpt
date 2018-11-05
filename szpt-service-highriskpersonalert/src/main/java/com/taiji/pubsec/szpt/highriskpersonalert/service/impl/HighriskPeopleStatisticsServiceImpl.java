package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfoTwoValue;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPeopleStatisticsService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;

@Service("highriskPeopleStatisticsService")
public class HighriskPeopleStatisticsServiceImpl implements IHighriskPeopleStatisticsService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Resource
	private SqlDao sqlDao ;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IUnitService unitService;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticsInfo> highriskPeopleTypeStatistics() {
		String xql = "select new " + StatisticsInfo.class.getName() + "(SUBSTRING(hpt.peopleType,1,3), count(hpt.id)) from HighriskPeopleType as hpt group by SUBSTRING(hpt.peopleType,1,3) order by SUBSTRING(hpt.peopleType,1,3)";
		List<StatisticsInfo> statisticsInfos = this.dao.findAllByParams(HighriskPeopleType.class, xql, new HashMap<String, Object>());
		if(!statisticsInfos.isEmpty()) {
			/*高危人员类型数据字典由系统管理维护*/
			DictionaryType dicType = this.dictionaryTypeService.findDicTypeByCode(HighriskPersonConstant.HIGHRISK_PERSON_TYPE_DIC_TYPE_CODE);	
			for(StatisticsInfo si : statisticsInfos) {
				/*高危人员类型数据字典项由系统管理维护*/
				if (null != dicType) {
					DictionaryItem dicItem = this.dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(dicType.getId(), si.getName(), null);
					if(null != dicItem) {
						si.setName(dicItem.getName()); 
					}
				}
			}
		}
		
		String hql = "select count(h.id) from HighriskPerson as h where h.highriskPeopleTypes.size = 0";
		String num = this.dao.findByXql(hql, new HashMap<String, Object>()).get(0).toString();
		StatisticsInfo statisticsInfo = new StatisticsInfo("未标识", num);
		statisticsInfos.add(statisticsInfo);
		return statisticsInfos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonCheckInfo> statisticsCheckPeopleInfo(Date timeStart, Date timeEnd) {
		String hql = "select p from PersonCheckInfo as p where p.interrogatDate between :timeStart and :timeEnd";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("timeStart", timeStart);
		xqlMap.put("timeEnd", timeEnd);
		return this.dao.findAllByParams(PersonCheckInfo.class, hql, xqlMap);
	}

	@Override
	public List<StatisticsInfo> fiveColorPeopleStatisticsByUnit(Date timeStart,
			Date timeEnd) {
		StringBuilder xqlSelect  = new StringBuilder("select new  " + StatisticsInfo.class.getName() + "(h.creatorDepartmentId, count(h.id)) from HighriskPerson as h where 1=1 and warnType is not null ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if (timeStart != null) {
			xqlSelect.append(" and h.createdTime >= :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if (timeEnd != null) {
			xqlSelect.append(" and h.createdTime <= :timeEnd");
			xqlMap.put("timeEnd", timeEnd);

		}
		xqlSelect.append(" group by h.creatorDepartmentId");
		
		List<StatisticsInfo> list = this.dao.findByXql(xqlSelect.toString(), xqlMap);
		for (StatisticsInfo statisticsInfo : list ) {
			statisticsInfo.setName(unitService.findById(statisticsInfo.getName()).getShortName());
		}
		return list;
	}
	
	@Override
	public List<StatisticsInfoTwoValue> zaiKongPeopleStatistics(Date timeStart, Date timeEnd, List<String> pcsCodes) {
		
		if(timeStart == null) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr = sdf.format(date);
			String todayStrStart = todayStr + " 00:00:00";
			Date todayStart = null;
			try {
				todayStart = sdf.parse(todayStrStart);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			timeStart = todayStart;  //默认值
		}
		StringBuilder xqlSelectOld  = new StringBuilder("select h.cjrdw_id, count(*) from t_gwry_ryxx as h, t_og_zzjg as og where og.id = h.cjrdw_id ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlSelectOld.append(" and h.cjsj < :timeStart");
		xqlMap.put("timeStart", timeStart);
		if (null != pcsCodes && pcsCodes.size() > 0) {
			xqlSelectOld.append(" and og.bm in (:key)");
			xqlMap.put("key", pcsCodes);
		}
		
		xqlSelectOld.append(" group by h.cjrdw_id");
		List<Object[]> oldResult = sqlDao.find(xqlSelectOld.toString(), xqlMap);
		Map<String, StatisticsInfoTwoValue> statMap = new HashMap<String, StatisticsInfoTwoValue>();
		for(Object[] oldstat : oldResult) {
			statMap.put(oldstat[0].toString(), new StatisticsInfoTwoValue(oldstat[0].toString(), oldstat[1].toString(), "0"));
		}
		
		StringBuilder xqlSelectAdd  = new StringBuilder("select h.cjrdw_id, count(*) from t_gwry_ryxx as h, t_og_zzjg as og where og.id = h.cjrdw_id ");
		Map<String, Object> xqlMap1 = new HashMap<String, Object>();
		xqlSelectAdd.append(" and h.cjsj >= :timeStart");
		xqlMap1.put("timeStart", timeStart);
		if (null != timeEnd) {
			xqlSelectAdd.append(" and h.cjsj < :endDay ");
			xqlMap1.put("endDay", timeEnd);
		}
		if (null != pcsCodes && pcsCodes.size() > 0) {
			xqlSelectAdd.append(" and og.bm in (:key)");
			xqlMap1.put("key", pcsCodes);
		}
		xqlSelectAdd.append(" group by h.cjrdw_id");
		List<Object[]> newResult = sqlDao.find(xqlSelectAdd.toString(), xqlMap1);
		for(Object[] newstat : newResult) {
			String unitId = newstat[0].toString();
			if(statMap.containsKey(unitId)) {
				StatisticsInfoTwoValue statinfo = statMap.get(unitId);
				statinfo.setValue_two(newstat[1].toString());
			} else {
				statMap.put(unitId, new StatisticsInfoTwoValue(unitId, "0", newstat[1].toString()));
			}
		}
		
//		List<StatisticsInfoTwoValue> result = new ArrayList<StatisticsInfoTwoValue>();
//		for (StatisticsInfo statisticsInfo : list) {
//			StatisticsInfoTwoValue statisticsInfoTwoValue = new StatisticsInfoTwoValue(statisticsInfo.getName(), statisticsInfo.getValue(), "");
//			for (StatisticsInfo statisticsInfo1 : list1) {
//				if (statisticsInfo.getName().equals(statisticsInfo1.getName())) {
//					statisticsInfoTwoValue.setValue_two(statisticsInfo1.getValue());
//					break;
//				}
//			}
//			result.add(statisticsInfoTwoValue);
//			
//		}
		
		for (StatisticsInfoTwoValue statisticsInfoTwoValue : statMap.values() ) {
			String name = statisticsInfoTwoValue.getName();
			if(!StringUtils.isEmpty(name)){
				statisticsInfoTwoValue.setName(unitService.findById(statisticsInfoTwoValue.getName()).getShortName());
			}
		} 
		List<StatisticsInfoTwoValue> result = new ArrayList<StatisticsInfoTwoValue>();
		result.addAll(statMap.values());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonCheckInfo> statisticsCheckPeopleInfoByPsersonIdcode(String idcode) {
		String hql = "select p from PersonCheckInfo as p where p.idNum = :idNum";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("idNum", idcode);
		return this.dao.findAllByParams(PersonCheckInfo.class, hql, xqlMap);
	}

}
