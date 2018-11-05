package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

/**
 * 高危人员查询接口
 * @author wangfx
 *
 */
public interface IHighriskPersonService {
	
	/**
	 * 根据条件分页查找重点人员信息，条件包括姓名（模糊匹配）、身份证号（模糊匹配）、预警类型、人员类型
	 * @param paramMap 查询条件map
	 * <br>：name 姓名（模糊匹配）
	 * <br>：idcode 身份证号（模糊匹配）
	 * <br>：createTime 创建时间
	 * <br>：warningTypeList 预警类型列表
	 * <br>：personTypeList 人员类型列表
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 高危人员分页信息
	 */
	Pager<HighriskPerson> findByCondition(Map<String, Object> paramMap, int pageNo, int pageSize);
	
	/**
	 * 创建高危人员
	 * @param highriskPerson 高危人员
	 */
	void createHighriskPerson(HighriskPerson highriskPerson);

	/**
	 * 更新高危人员
	 * @param highriskPerson 高危人员
	 */
	void updateHighriskPerson(HighriskPerson highriskPerson);
	
	/**
	 * 根据预警类型统计
	 * @return 统计结果
	 */
	List<StatisticsInfo> sumupHighriskPersonByWarnType();
	
	/**
	 * 查找最新创建的5个高危人信息，创建时间倒序。
	 * @return 高危人员列表
	 */
	List<HighriskPerson> findCreatedHighriskLately();
	
	/**
	 * 根据身份证号查询高危人信息。
	 * @param idCode 身份证号
	 * @return 高危人员
	 */
	HighriskPerson findByIdCode(String idCode);
	
	/**
	 * 根据id查询高危人员
	 * @param id
	 * @return 高危人员
	 */
	HighriskPerson findHighriskPersonById(String id);
	
	/**
	 * 根据mac地址查询高危人信息。
	 * @param mac mac地址
	 * @return 高危人员
	 */
	HighriskPerson findByMac(String mac);
	
	/**
	 * 根据手机号查询高危人信息。
	 * @param telephone 手机号码
	 * @return 高危人员
	 */
	HighriskPerson findByMobile(String telephone);
	
	/**
	 * 根据手机号查询对应的mac地址。
	 * @param telephone 手机号码
	 * @return 高危人员
	 */
	List<String> findMacByMobile(String telephone);
	
	/**
	 * 根据条件查找mac地址，条件可以是身份证号、手机号或mac地址。前台根据字符串模式匹配判断是哪种条件，参数map中对应设置不同的key值。
	 * @param paramMap 查询条件map
	 * <br>idcode 身份证号
	 * <br>telephone 手机号
	 * <br>mac mac地址
	 * @return mac地址列表
	 */
	List<String> findMacsByCondition(Map<String, String> paramMap);
	
	/**
	 * 改为人员打标
	 * @param paramMap 参数map
	 * <br>:highriskPersonId 高危人员id
	 * <br>:highriskPersonNickname 昵称
	 * <br>:mobilePhoneInfoList 终端设备信息
	 * <br>:highriskPersonTypeList 高危人员类型
	 * <li>highriskPersonTypeList.highriskCriminalRecords 高危人员前科类型
	 */
	void createHighriskPersonMark(Map<String, Object> paramMap);
	
	/**
	 * 手机端使用新增高危人员
	 * @param paramMap 参数map
	 * <br>:highriskPerson 高危人员
	 * <br>：mobilePhoneInfoList 终端设备信息
	 * <br>：highriskPeopleTypeList 高危人员类型
	 * <li>highriskPersonTypeList.highriskCriminalRecords 高危人员前科类型
	 */
	void createHighriskPersonForMobile(Map<String, Object> paramMap);
	
	/**
	 * 手机端更新高危人员
	 * @param paramMap 参数map
	 * <br>:highriskPerson 高危人员
	 * <br>：mobilePhoneInfoList 终端设备信息
	 * <br>：highriskPeopleTypeList 高危人员类型
	 * <li>highriskPersonTypeList.highriskCriminalRecords 高危人员前科类型
	 */
	void updateHighriskPersonForMobile(Map<String, Object> paramMap);

	/**
	 * 
	 * 保存操作记录
	 * @param operationRecord
	 * @return
	 */
	String saveOperationRecord(OperationRecord operationRecord);

	/**
	 * 查询操作记录
	 * @param highriskPersonId 高危人id
	 * @return
	 */
	List<OperationRecord> findOperationRecordByHighrishPeople(
			String highriskPersonId);
	
	/**
	 * 通过身份证号查询高危人员信息
	 * @param idCardNo 身份证号
	 * @return 返回高危人员信息，查不到返回null
	 */
	HighriskPerson findHighriskPersonByIdCardNo(String idCardNo);
}
