package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.persistence.service.BaseService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonMobileInfo;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

/**
 * 人员布控查询接口
 * Created by wangfx on 2016/10/13.
 */
public interface IPersonExecuteControlService extends BaseService<PersonExecuteControl, String> {
	
	public PersonExecuteControl findById(String id) ;
	
	/**
	 * 根据查询条件查询人员布控信息，按最新修改时间倒序
	 * @param paramMap 查询条件
	 * <br>:code 布控单编号
	 * <br>:timeStart 布控时间开始
	 * <br>:timeEnd 布控时间结束
	 * <br>:personName 布控人姓名
	 * <br>:personIdcode 布控人员身份证号
	 * <br>:note 备注
	 * <br>:isShowInvalid 是否显示失效布控单（false时，传入布控单状态status--正常code）
	 * <br>:status 布控单状态
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 人员布控信息列表
	 */
	Pager<PersonExecuteControl> findPersonExecuteControlByCondition(Map<String, Object> paramMap, int pageNo, int pageSize);

	/**
	 * 新增人员布控信息，同时新增人员布控移动端信息
	 * @param pec 人员布控信息
	 * @param pmmil 人员布控的移动端信息
	 */
	void savePersonExecuteControl(PersonExecuteControl pec, List<PersonMobileInfo> pmmil);

	/**
	 * 
	 * 保存操作记录
	 * @param operationRecord
	 * @return
	 */
	String saveOperationRecord(OperationRecord operationRecord);

	/**
	 * 查询操作记录
	 * @param personExecuteControlId 布控单据id
	 * @return
	 */
	List<OperationRecord> findOperationRecordByPersonExecuteControl(
			String personExecuteControlId);
	/**
	 * 获取最新布控单编号
	 * @return
	 */
	String acquireNum();
	
	public void updatePersonExecuteControlStatus(PersonExecuteControl entity) ;
}
