package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.bean.AjBean;
import com.taiji.pubsec.szpt.bean.AlarmInfo;

/**
 * 高危人分析接口
 * @author huangcheng
 *
 */
public interface HighriskPersonAnalyzeService {

	/**
	 * 按照人员类型和时段来查询高危人统计信息
	 * 时段请参照
	 * com.taiji.pubsec.szpt.util.Constant中的枚举类型SHI_DUAN
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param peopleTypeCodes 人员类型编码
	 * @return AlarmInfo的集合，name(时段名称)、nameCode人员类型code、nameAdd1(人员类型名称peopleType)、 count：出现次数
	 */
	public List<AlarmInfo> findHPersonsCountByPeopleTypeByDayPart(Date startDay, Date endDay, List<String> peopleTypeCodes) ;
	
	/**
	 * 查询所有网吧的高危人统计信息
	 * @param placeCodes 社区警务的发案处所编码
	 * @param peopleTypeCodes 人员类型编码
	 * @return AlarmInfo的集合:nameCode人员类型code、count：出现次数
	 */
	public List<AlarmInfo> findHPersonsCountByPeopleTypeForInternetBar(Date startDay, Date endDay, List<String> peopleTypeCodes) ;
	
	/**
	 * 查询所有旅馆的高危人统计信息
	 * @param placeCodes 社区警务的发案处所编码
	 * @param peopleTypeCodes 人员类型编码
	 * @return AlarmInfo的集合:nameCode人员类型code、count：出现次数
	 */
	public List<AlarmInfo> findHPersonsCountByPeopleTypeForHotel(Date startDay, Date endDay, List<String> peopleTypeCodes) ;
	
	/**
	 * 查询高危人牵涉的案件。通过省份证号和
	 * 涉案人员信息表（t_xsajfx_sary）和
	 * 嫌疑人关系表（t_xsajfx_xyrgx）
	 * 查询高危人和案件的关系。优先使用打标信息中的经度和纬度，没有再使用案件基本信息中的经度和维度。
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param identy 身份证号
	 * @return AjBean的集合
	 */
	public List<AjBean> findHPersonAj(Date startDay, Date endDay, String identy) ;
	
//	/**
//	 * 查询高危人员在不同类型场所出现次数
//	 * @param startDay 开始时间
//	 * @param endDay 结束时间
//	 * @param placeCodes 场所编码，固定网吧和旅馆两类
//	 * @param peopleTypeCodes 人员类型编码
//	 * @return AlarmInfo的集合，name(场所类型名称，只有网吧和旅馆)、nameCode人员类型code、nameAdd1(人员类型名称peopleType)、 count：出现次数
//	 */
//	public List<AlarmInfo> findHPersonsCountByPeopleTypeByPlaceCodes (Date startDay, Date endDay, List<String> placeCodes, List<String> peopleTypeCodes);
}
