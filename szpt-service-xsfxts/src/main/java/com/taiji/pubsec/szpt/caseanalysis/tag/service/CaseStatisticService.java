package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.ResultBean;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.TagCountResultBean;

/**
 * 案件统计服务接口
 * 
 * @author dixiaofeng
 */
public interface CaseStatisticService {

	/**
	 * 按照辖区、发案时间段、类型、性质查询打标发案处所值对应的总数。从案件基本信息表和打标基础表中查询。
	 * 返回<打标值，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param conditions
	 * region：辖区；fromDate：起始时间；toDate：结束时间；type：类别；firstSort：一级性质；secondSort：二级性质
	 */
	public List<TagCountResultBean> countByTagOccurPlace(Map<String,Object> conditions);

	/**
	 * 按照辖区、时间段、类别、性质查询打标作案特点值对应的总数。从案件基本信息表、打标基础表中、打标作案特点表查询。
	 * 返回<作案特点值，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param conditions
	 * region：辖区；fromDate：起始时间；toDate：结束时间；type：类别；firstSort：一级性质；secondSort：二级性质
	 */
	public List<TagCountResultBean> countByTagCaseFeature(Map<String,Object> conditions);

	/**
	 * 按照辖区、时间段、类别、性质查询打标作案时段值对应的总数。从案件基本信息表和打标基础表中查询。
	 * 返回<作案时段值，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param conditions
	 * region：辖区；fromDate：起始时间；toDate：结束时间；type：类别；firstSort：一级性质；secondSort：二级性质
	 */
	public List<TagCountResultBean> countByTagCasePeriod(Map<String,Object> conditions);

	/**
	 * 按辖区和时间段查询盗窃案件数量。从案件基本信息表和打标基础表中查询。
	 * 返回<辖区名称，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param region    辖区编码，null表示查所有辖区
	 * @param fromDate    开始时间
	 * @param toDate    结束时间
	 */
	public List<Map<String,Integer>> countRipoffCaseByRegion(String region, Date fromDate, Date toDate);

	/**
	 * 按照辖区、发案时间段、打标类型（三级）查询发案时段值对应的总数。从案件基本信息表、打标基础表中查询。
	 * 返回<打标类型，时间段值，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param region    辖区编码，null表示查所有辖区
	 * @param fromDate    起始时间
	 * @param toDate    结束时间
	 * @param level    类型级别，1查打标类型字段，2查打标性质一级字典，3查打标性质二级字段
	 * @param codes    类型、一级性质或二级性质编码数组
	 */
	public List<ResultBean> countByTagCasePeriod(String region, Date fromDate, Date toDate, int level, String[] codes);

	/**
	 * 按照辖区、发案时间段、打标类型（三级）查询发案处所值对应的总数。从案件基本信息表、打标基础表中查询。
	 * 返回<打标类型，处所值，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param region    辖区编码，null表示查所有辖区
	 * @param fromDate    起始时间
	 * @param toDate    结束时间
	 * @param level    类型级别，1查打标类型字段，2查打标性质一级字典，3查打标性质二级字段
	 * @param codes    类型、一级性质或二级性质编码数组
	 */
	public List<ResultBean> countByTagOccurPlace(String region, Date fromDate, Date toDate, int level, String[] codes);

	/**
	 * 按辖区和时间段查询抢劫和抢夺案件数量。从案件基本信息表和打标基础表中查询。
	 * 返回<辖区名称，数量>值对列表，按数量从大到小排列。
	 * 
	 * @param region    辖区编码，，null表示查所有辖区
	 * @param fromDate    开始时间
	 * @param toDate    结束时间
	 */
	public List<Map<String,Integer>> countRobberyCaseByRegion(String region, Date fromDate, Date toDate);

}