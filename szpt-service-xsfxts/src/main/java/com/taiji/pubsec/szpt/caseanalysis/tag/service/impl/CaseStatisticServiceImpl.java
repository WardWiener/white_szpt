package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.ResultBean;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.TagCountResultBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseStatisticService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("caseStatisticService")
public class CaseStatisticServiceImpl implements CaseStatisticService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IUnitService unitService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TagCountResultBean> countByTagOccurPlace(Map<String, Object> conditions) {
		List<TagCountResultBean> resultBeans = new ArrayList<TagCountResultBean>();
		StringBuilder xql = new StringBuilder("select distinct t.occurPlace from CaseTag as t ");
		StringBuilder xql1 = new StringBuilder("select count(t) from CaseTag as t ");
		StringBuilder xqlWhere = new StringBuilder("");
	 	StringBuilder xqlWhere1 = new StringBuilder("");
		StringBuilder xqlWhere2 = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		Map<String, Object> xqlMap1 = new HashMap<String, Object>();
		Map<String, Object> xqlMap2 = new HashMap<String, Object>();
		Date previousTime = new Date(((Date) conditions.get("fromDate")).getTime() - (((Date)conditions.get("toDate")).getTime() - ((Date)conditions.get("fromDate")).getTime()));
		
		if(ParamMapUtil.isNotBlank(conditions.get("region"))){
			Unit unit = unitService.findUnitByCode((String) conditions.get("region"));
			xqlWhere1.append(" , Community as c where c.code = t.community and c.unitId = :unitId ");
			xqlMap1.put("unitId", unit.getId());
			xqlWhere2.append(" , Community as c where c.code = t.community and c.unitId = :unitId ");
			xqlMap2.put("unitId", unit.getId());
		}else{
			xqlWhere1.append(" where 1 = 1 ");
			xqlWhere2.append(" where 1 = 1 ");
		}
		
		xqlWhere1.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap1.put("fromDate", conditions.get("fromDate"));
		xqlWhere1.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap1.put("toDate", conditions.get("toDate"));
		
		xqlWhere2.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap2.put("fromDate", previousTime);
		xqlWhere2.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap2.put("toDate", conditions.get("fromDate"));
	
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xqlWhere.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSort"))){
			xqlWhere.append(" and t.firstSort = :firstSort ");
			xqlMap.put("firstSort", conditions.get("firstSort"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSort"))){
			xqlWhere.append(" and t.secondSort = :secondSort ");
			xqlMap.put("secondSort", conditions.get("secondSort"));
		}
		
		xqlMap1.putAll(xqlMap);
		xqlMap2.putAll(xqlMap);
		List<String> occurPlaceList = dao.findAllByParams(String.class, xql.append(xqlWhere1).append(xqlWhere).toString(), xqlMap1);
		
		for(String occurPlace: occurPlaceList){
			TagCountResultBean resultBean = new TagCountResultBean();
			xqlWhere1.append(" and t.occurPlace = :occurPlace ");
			xqlMap1.put("occurPlace", occurPlace);
			xqlWhere2.append(" and t.occurPlace = :occurPlace ");
			xqlMap2.put("occurPlace", occurPlace);
			List<Long> objs1 = dao.findByXql(xql1.toString()+xqlWhere1.toString()+xqlWhere.toString(), xqlMap1);
			List<Long> objs2 = dao.findByXql(xql1.toString()+xqlWhere2.toString()+xqlWhere.toString(), xqlMap2);
			int number1 = 0;
			int number2 = 0;
			for(int i = 0; i < objs1.size(); i++){
				Long o = objs1.get(i);
				number1 = o.intValue();
			}
			for(int i = 0; i < objs2.size(); i++){
				Long o = objs2.get(i);
				number2 =  o.intValue();
			}
			String proportion = null;
			if(number2 == 0){
				proportion = "-";
			}else{
				proportion = String.valueOf(((double)number1 - (double)number2) / (double)number2 * 100);
			}
			
			resultBean.setNumber(number1);
			resultBean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_FACS.getValue(), occurPlace, null).getName());
			resultBean.setProportion(proportion);
			
			resultBeans.add(resultBean);
		}
		Collections.sort(resultBeans);
		return resultBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagCountResultBean> countByTagCaseFeature(Map<String, Object> conditions) {
		List<TagCountResultBean> resultBeans = new ArrayList<TagCountResultBean>();
		StringBuilder xql = new StringBuilder("select distinct f.feature from CaseFeatureTag as f, CaseTag as t ");
		StringBuilder xql1 = new StringBuilder("select count(f) from CaseFeatureTag as f, CaseTag as t ");
		StringBuilder xqlWhere = new StringBuilder("");
	 	StringBuilder xqlWhere1 = new StringBuilder("");
		StringBuilder xqlWhere2 = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		Map<String, Object> xqlMap1 = new HashMap<String, Object>();
		Map<String, Object> xqlMap2 = new HashMap<String, Object>();
		Date previousTime = new Date(((Date) conditions.get("fromDate")).getTime() - (((Date)conditions.get("toDate")).getTime() - ((Date)conditions.get("fromDate")).getTime()));
		
		if(ParamMapUtil.isNotBlank(conditions.get("region"))){
			Unit unit = unitService.findUnitByCode((String) conditions.get("region"));
			xqlWhere1.append(" , Community as c where c.code = t.community and f.caseTag.id = t.id and c.unitId = :unitId ");
			xqlMap1.put("unitId", unit.getId());
			xqlWhere2.append(" , Community as c where c.code = t.community and f.caseTag.id = t.id and c.unitId = :unitId ");
			xqlMap2.put("unitId", unit.getId());
		}else{
			xqlWhere1.append(" where f.caseTag.id = t.id and 1 = 1 ");
			xqlWhere2.append(" where f.caseTag.id = t.id and 1 = 1 ");
		}
		
		xqlWhere1.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap1.put("fromDate", conditions.get("fromDate"));
		xqlWhere1.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap1.put("toDate", conditions.get("toDate"));
		
		xqlWhere2.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap2.put("fromDate", previousTime);
		xqlWhere2.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap2.put("toDate", conditions.get("fromDate"));
		
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xqlWhere.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSort"))){
			xqlWhere.append(" and t.firstSort = :firstSort ");
			xqlMap.put("firstSort", conditions.get("firstSort"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSort"))){
			xqlWhere.append(" and t.secondSort = :secondSort ");
			xqlMap.put("secondSort", conditions.get("secondSort"));
		}
		
		xqlMap1.putAll(xqlMap);
		xqlMap2.putAll(xqlMap);
		List<String> featureList = dao.findAllByParams(String.class, xql.append(xqlWhere1).append(xqlWhere).toString(), xqlMap1);
		
		for(String feature: featureList){
			TagCountResultBean resultBean = new TagCountResultBean();
			xqlWhere1.append(" and f.feature = :feature ");
			xqlMap1.put("feature", feature);
			xqlWhere2.append(" and f.feature = :feature ");
			xqlMap2.put("feature", feature);
			List<Long> objs1 = dao.findByXql(xql1.toString()+xqlWhere1.toString()+xqlWhere.toString(), xqlMap1);
			List<Long> objs2 = dao.findByXql(xql1.toString()+xqlWhere2.toString()+xqlWhere.toString(), xqlMap2);
			int number1 = 0;
			int number2 = 0;
			for(int i = 0; i < objs1.size(); i++){
				Long o = objs1.get(i);
				number1 = o.intValue();
			}
			for(int i = 0; i < objs2.size(); i++){
				Long o = objs2.get(i);
				number2 =  o.intValue();
			}
			String proportion = null;
			if(number2 == 0){
				proportion = "-";
			}else{
				proportion = String.valueOf(((double)number1 - (double)number2) / (double)number2 * 100);
			}
			
			resultBean.setNumber(number1);
			resultBean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZATD.getValue(), feature, null).getName());
			resultBean.setProportion(proportion);
			
			resultBeans.add(resultBean);
		}
		Collections.sort(resultBeans);
		return resultBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagCountResultBean> countByTagCasePeriod(Map<String, Object> conditions) {
		List<TagCountResultBean> resultBeans = new ArrayList<TagCountResultBean>();
		StringBuilder xql = new StringBuilder("select distinct t.period from CaseTag as t ");
		StringBuilder xql1 = new StringBuilder("select count(t) from CaseTag as t ");
		StringBuilder xqlWhere = new StringBuilder("");
	 	StringBuilder xqlWhere1 = new StringBuilder("");
		StringBuilder xqlWhere2 = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		Map<String, Object> xqlMap1 = new HashMap<String, Object>();
		Map<String, Object> xqlMap2 = new HashMap<String, Object>();
		Date previousTime = new Date(((Date) conditions.get("fromDate")).getTime() - (((Date)conditions.get("toDate")).getTime() - ((Date)conditions.get("fromDate")).getTime()));
		
		if(ParamMapUtil.isNotBlank(conditions.get("region"))){
			Unit unit = unitService.findUnitByCode((String) conditions.get("region"));
			xqlWhere1.append(" , Community as c where c.code = t.community and c.unitId = :unitId ");
			xqlMap1.put("unitId", unit.getId());
			xqlWhere2.append(" , Community as c where c.code = t.community and c.unitId = :unitId ");
			xqlMap2.put("unitId", unit.getId());
		}else{
			xqlWhere1.append(" where 1 = 1 ");
			xqlWhere2.append(" where 1 = 1 ");
		}
		
		xqlWhere1.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap1.put("fromDate", conditions.get("fromDate"));
		xqlWhere1.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap1.put("toDate", conditions.get("toDate"));
		
		xqlWhere2.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap2.put("fromDate", previousTime);
		xqlWhere2.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap2.put("toDate", conditions.get("fromDate"));
		
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xqlWhere.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSort"))){
			xqlWhere.append(" and t.firstSort = :firstSort ");
			xqlMap.put("firstSort", conditions.get("firstSort"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSort"))){
			xqlWhere.append(" and t.secondSort = :secondSort ");
			xqlMap.put("secondSort", conditions.get("secondSort"));
		}
		
		xqlMap1.putAll(xqlMap);
		xqlMap2.putAll(xqlMap);
		List<String> periodList = dao.findAllByParams(String.class, xql.append(xqlWhere1).append(xqlWhere).toString(), xqlMap1);
		
		for(String period: periodList){
			TagCountResultBean resultBean = new TagCountResultBean();
			xqlWhere1.append(" and t.period = :period ");
			xqlMap1.put("period", period);
			xqlWhere2.append(" and t.period = :period ");
			xqlMap2.put("period", period);
			List<Long> objs1 = dao.findByXql(xql1.toString()+xqlWhere1.toString()+xqlWhere.toString(), xqlMap1);
			List<Long> objs2 = dao.findByXql(xql1.toString()+xqlWhere2.toString()+xqlWhere.toString(), xqlMap2);
			int number1 = 0;
			int number2 = 0;
			for(int i = 0; i < objs1.size(); i++){
				Long o = objs1.get(i);
				number1 = o.intValue();
			}
			for(int i = 0; i < objs2.size(); i++){
				Long o = objs2.get(i);
				number2 =  o.intValue();
			}
			String proportion = null;
			if(number2 == 0){
				proportion = "-";
			}else{
				proportion = String.valueOf(((double)number1 - (double)number2) / (double)number2 * 100);
			}
			
			resultBean.setNumber(number1);
			resultBean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZATIME.getValue(), period, null).getName());
			resultBean.setProportion(proportion);
			
			resultBeans.add(resultBean);
		}
		Collections.sort(resultBeans);
		return resultBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Integer>> countRipoffCaseByRegion(String region, Date fromDate, Date toDate) {
		List<Map<String, Integer>> results = new ArrayList<Map<String, Integer>>();
		StringBuilder xql = new StringBuilder("select count(t) from CaseTag as t, Community as c where c.code = t.community and 1 = 1 ");
		StringBuilder xqlWhere = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		xqlWhere.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap.put("fromDate", fromDate);
		xqlWhere.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap.put("toDate", toDate);
		
		xqlWhere.append(" and t.type = :type ");
		xqlMap.put("type", DICT.DICT_AJXZ_DQA.getValue());
		 
		if(ParamMapUtil.isNotBlank(region)){
			Unit unit = unitService.findUnitByCode(region);
			xqlWhere.append(" and c.unitId = :unitId ");
			xqlMap.put("unitId", unit.getId());
			List<Long> objs1 = dao.findByXql(xql.append(xqlWhere).toString(), xqlMap);
			int number = 0;
			for(int i = 0; i < objs1.size(); i++){
				Long o = objs1.get(i);
				number = o.intValue();
			}
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put(unitService.findUnitByCode(region).getShortName(), Integer.valueOf(number));
			results.add(result);
		}else{
			String xql1 = "select u from Unit as u where u.type = ?";
			List<Unit> units = dao.findAllByParams(Unit.class, xql1, new Object[]{DICT.DICT_DWLX_PCS.getValue()});
			for(Unit unit: units){
				Map<String, Object> xqlMap1 = new HashMap<String, Object>();
				String xqlWhere1 = " and c.unitId = :unitId ";
				xqlMap1.put("unitId", unit.getId());
				xqlMap1.putAll(xqlMap);
				List<Long> objs1 = dao.findByXql(xql.toString() + xqlWhere.toString() + xqlWhere1, xqlMap1);
				int number = 0;
				for(int i = 0; i < objs1.size(); i++){
					Long o = objs1.get(i);
					number = o.intValue();
				}
				Map<String, Integer> result = new HashMap<String, Integer>();
				result.put(unit.getShortName(), Integer.valueOf(number));
				results.add(result);
			}
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultBean> countByTagCasePeriod(String region, Date fromDate, Date toDate, int level, String[] codes) {
		List<ResultBean> resultBeans = new ArrayList<ResultBean>(); 
		StringBuilder xql = new StringBuilder("select distinct t.period from CaseTag as t, Community as c where c.code = t.community ");
		StringBuilder xql1 = new StringBuilder("select count(t) from CaseTag as t, Community as c where c.code = t.community ");
		StringBuilder xqlWhere = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		xqlWhere.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap.put("fromDate", fromDate);
		xqlWhere.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap.put("toDate", toDate);
		
//		List<String> periodList = dao.findAllByParams(String.class, xql.append(xqlWhere).toString() + " order by t.period", xqlMap);
		List<DictionaryItem> items = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZATIME.getValue(), null);
		List<String> periodList = new ArrayList<String>();
		for(DictionaryItem item: items){
			periodList.add(item.getCode());
		}
		
		if(level == 1){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
				}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String period: periodList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.period = :period ");
					xqlMap1.put("period", period);
					xqlWhere1.append(" and t.type = :type ");
					xqlMap1.put("type", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZATIME.getValue(), period, null).getName());
					resultBeans.add(bean);
				}
			}
		}else if(level == 2){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
				}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String period: periodList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.period = :period ");
					xqlMap1.put("period", period);
					xqlWhere1.append(" and t.firstSort = :firstSort ");
					xqlMap1.put("firstSort", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZATIME.getValue(), period, null).getName());
					resultBeans.add(bean);
				}
			}
		}else if(level == 3){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
				}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String period: periodList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.period = :period ");
					xqlMap1.put("period", period);
					xqlWhere1.append(" and t.secondSort = :secondSort ");
					xqlMap1.put("secondSort", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_ZATIME.getValue(), period, null).getName());
					resultBeans.add(bean);
				}
			}
		}
		
		return resultBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultBean> countByTagOccurPlace(String region, Date fromDate, Date toDate, int level, String[] codes) {
		List<ResultBean> resultBeans = new ArrayList<ResultBean>(); 
		StringBuilder xql = new StringBuilder("select distinct t.occurPlace from CaseTag as t, Community as c where c.code = t.community ");
		StringBuilder xql1 = new StringBuilder("select count(t) from CaseTag as t, Community as c where c.code = t.community ");
		StringBuilder xqlWhere = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		xqlWhere.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap.put("fromDate", fromDate);
		xqlWhere.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap.put("toDate", toDate);
		
//		List<String> occurPlaceList = dao.findAllByParams(String.class, xql.append(xqlWhere).toString() + " order by t.occurPlace", xqlMap);
		List<DictionaryItem> items = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_FACS.getValue(), null);
		List<String> occurPlaceList = new ArrayList<String>();
		for(DictionaryItem item: items){
			occurPlaceList.add(item.getCode());
		}
		
		if(level == 1){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
			}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String occurPlace: occurPlaceList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.occurPlace = :occurPlace ");
					xqlMap1.put("occurPlace", occurPlace);
					xqlWhere1.append(" and t.type = :type ");
					xqlMap1.put("type", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_FACS.getValue(), occurPlace, null).getName());
					resultBeans.add(bean);
				}
			}
		}else if(level == 2){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
			}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String occurPlace: occurPlaceList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.occurPlace = :occurPlace ");
					xqlMap1.put("occurPlace", occurPlace);
					xqlWhere1.append(" and t.firstSort = :firstSort ");
					xqlMap1.put("firstSort", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_FACS.getValue(), occurPlace, null).getName());
					resultBeans.add(bean);
				}
			}
		}else if(level == 3){
			if(region != null){
				Unit unit = unitService.findUnitByCode(region);
				xqlWhere.append(" and c.unitId = :unitId ");
				xqlMap.put("unitId", unit.getId());
			}
			for(String code: codes){
				String codeName = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), code, null).getName();
				for(String occurPlace: occurPlaceList){
					Map<String, Object> xqlMap1 = new HashMap<String, Object>();
					StringBuilder xqlWhere1 = new StringBuilder("");
					xqlWhere1.append(" and t.occurPlace = :occurPlace ");
					xqlMap1.put("occurPlace", occurPlace);
					xqlWhere1.append(" and t.secondSort = :secondSort ");
					xqlMap1.put("secondSort", code);
					xqlMap1.putAll(xqlMap);
					List<Long> objs1 = dao.findByXql(xql1.toString() + xqlWhere.toString() + xqlWhere1.toString(), xqlMap1);
					int number = 0;
					for(int i = 0; i < objs1.size(); i++){
						Long o = objs1.get(i);
						number = o.intValue();
					}
					
					ResultBean bean = new ResultBean();
					bean.setNumber(number);
					bean.setTagType(codeName);
					bean.setTagTypeCode(code);
					bean.setTagValue(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_FACS.getValue(), occurPlace, null).getName());
					resultBeans.add(bean);
				}
			}
		}
		
		return resultBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Integer>> countRobberyCaseByRegion(String region, Date fromDate, Date toDate) {
		List<Map<String, Integer>> results = new ArrayList<Map<String, Integer>>();
		StringBuilder xql = new StringBuilder("select count(t) from CaseTag as t , Community as c where c.code = t.community and 1 = 1 ");
		StringBuilder xqlWhere = new StringBuilder("");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		xqlWhere.append(" and t.basicCase.confirmDate >= :fromDate ");
		xqlMap.put("fromDate", fromDate);
		xqlWhere.append(" and t.basicCase.confirmDate < :toDate ");
		xqlMap.put("toDate", toDate);
		
		xqlWhere.append(" and (t.type = :type1 ");
		xqlMap.put("type1", DICT.DICT_AJXZ_QJA.getValue());
		xqlWhere.append(" or t.type = :type2 ");
		xqlMap.put("type2", DICT.DICT_AJXZ_QDA.getValue());
		xqlWhere.append(") ");
		 
		if(ParamMapUtil.isNotBlank(region)){
			Unit unit = unitService.findUnitByCode(region);
			xqlWhere.append(" and c.unitId = :unitId ");
			xqlMap.put("unitId", unit.getId());
			List<Long> objs1 = dao.findByXql(xql.append(xqlWhere).toString(), xqlMap);
			int number = 0;
			for(int i = 0; i < objs1.size(); i++){
				Long o = objs1.get(i);
				number = o.intValue();
			}
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put(unitService.findUnitByCode(region).getShortName(), Integer.valueOf(number));
			results.add(result);
		}else{
			String xql1 = "select u from Unit as u where u.type = ?";
			List<Unit> units = dao.findAllByParams(Unit.class, xql1, new Object[]{DICT.DICT_DWLX_PCS.getValue()});
			for(Unit unit: units){
				Map<String, Object> xqlMap1 = new HashMap<String, Object>();
				String xqlWhere1 = " and c.unitId = :unitId ";
				xqlMap1.put("unitId", unit.getId());
				xqlMap1.putAll(xqlMap);
				List<Long> objs1 = dao.findByXql(xql.toString() + xqlWhere.toString() + xqlWhere1, xqlMap1);
				int number = 0;
				for(int i = 0; i < objs1.size(); i++){
					Long o = objs1.get(i);
					number = o.intValue();
				}
				Map<String, Integer> result = new HashMap<String, Integer>();
				result.put(unit.getShortName(), Integer.valueOf(number));
				results.add(result);
			}
		}
		
		return results;
	}
}
