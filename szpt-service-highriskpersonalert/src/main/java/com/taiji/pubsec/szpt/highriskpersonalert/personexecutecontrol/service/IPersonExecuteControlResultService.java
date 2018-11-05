package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;

/**
 * 人员布控结果查询接口
 */
public interface IPersonExecuteControlResultService{
	
	/**
	 * 查询人员布控结果分页信息
	 * @param personExecuteControlNum 布控单编码
	 * @param pageNo 页号
	 * @param pageSize 步长
	 * @return 分页结果
	 */
	public Pager<DefaultSurveilListResult> findPersonExecuteControlResultByPage(String personExecuteControlNum, int pageNo, int pageSize);
	
	/**
	 * 
	 */
	/**
	 * 更改结果的状态
	 * @param id 结果id
	 * @param status 要更改的状态
	 * 布控组件默认生成的结果的状态为默认值为字典项初始化:init（字典类型系统状态:xtzt）
	 * 业务端需要修改成
	 * 确认：状态：启用1
	 * 忽略：状态：停用0
	 * @return 操作成功返回true
	 */
	public boolean saveExecuteControlResultStatus(String id, String status) ;
	
	/**
	 * 更改结果的状态
	 * @param id 结果id
	 * @param status 要更改的状态
	 * 布控组件默认生成的结果的状态为默认值为字典项初始化:init（字典类型系统状态:xtzt）
	 * 业务端需要修改成
	 * 确认：状态：启用1
	 * 忽略：状态：停用0
	 * @return 操作成功返回true
	 */
	public boolean deleteExecuteControlResultStatus(String id) ;

	/**
	 * 通过布控结果id查询布控结果
	 * @param resultId 布控结果id
	 * @return 返回布控结果
	 */
	public DefaultSurveilListResult findById(String resultId);
}
