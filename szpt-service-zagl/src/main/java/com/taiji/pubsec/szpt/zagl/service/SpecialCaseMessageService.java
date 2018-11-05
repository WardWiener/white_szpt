package com.taiji.pubsec.szpt.zagl.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessage;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessageStickRecord;

/**
 * 专案留言服务接口,提供添加、删除、置顶、取消置顶、查询功能
 * @author dixiaofeng
 */
public interface SpecialCaseMessageService {

	/**
	 * 添加留言
	 * @param message 专案留言
	 * @return 成功返回true；失败返回false
	 */
	public boolean addMessage(SpecialCaseMessage message);

	/**
	 * 删除留言。
	 * @param mid    留言id
	 * @return 删除成功返回true；失败返回false
	 */
	public boolean deleteMessage(String mid);

	/**
	 * 按条件分页查询留言记录，不包含置顶记录，按留言时间倒序
	 * @param conditions 查询条件conditions，包括：
	 * <br>department:所在部门
	 * <br>createTimeStart:留言日期开始
	 * <br>createTimeEnd:留言日期结束
	 * <br>caseId:专案id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回留言记录分页信息
	 */
	public Pager<SpecialCaseMessage> findMessageByConditions(Map<String, Object> conditions, int pageNo, int pageSize);

	/**
	 * 按人员分页查询发布的留言。
	 * @param personId 人员id
	 * @param caseId 专案id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回留言记录分页信息
	 */
	public Pager<SpecialCaseMessage> findMessgeByPerson(String personId, String caseId,  int pageNo, int pageSize);

	/**
	 * 查找用户的所有置顶留言，包括系统预设（局领导）和用户设置的留言，按留言时间倒序排列。
	 * @param personId 人员id
	 * @param caseId 专案id
	 * @return 返回留言置顶记录list，若置顶记录无id，为领导留言；若置顶记录有id，为用户置顶留言
	 */
	public List<SpecialCaseMessageStickRecord> findStickyMessage(String personId, String caseId);

	/**
	 * 置顶留言。
	 * @param messageId 留言id
	 * @param personId 置顶人id
	 * @param stickTime 置顶时间
	 * @return 成功返回true；失败返回false
	 */
	public boolean stickMessage(String messageId, String personId, Date stickTime);
	
	/**
	 * 取消置顶留言
	 * @param messageId 留言id
	 * @param personId 置顶人id
	 * @return 成功返回true；失败返回false
	 */
	public boolean unstickMessage(String messageId, String personId);
	
	/**
	 * 通过id查看留言
	 * @param messageId 留言id
	 * @return 返回留言信息,查找不到返回null
	 */
	public SpecialCaseMessage findById(String messageId);
	
	/**
	 * 通过人员id和留言id判断该人员是否已置顶该留言
	 * @param personId 人员id 
	 * @param messageId 留言id
	 * @return 已置顶，返回true；未置顶 ，返回false
	 */
	public boolean findStickRecordByPersonIdAndMessageId(String personId, String messageId);
	
	/**
	 * 按条件分组查询留言信息
	 * @param conditions 查询条件conditions，包括：
	 * <br>departments:所属部门
	 * <br>createTimeStart:留言日期开始
	 * <br>createTimeEnd:留言日期结束
	 * <br>caseId:专案id
	 * <br>flag:是否分组，boolean类型，true表示分组，false表示不分组
	 * @return 返回留言信息，若未分组，map里有一个key是“all”，查询到全部留言；若分组，map里的key是各单位的名称
	 */
	public Map<String, List<SpecialCaseMessage>> findMessageByCondition(Map<String , Object> conditions);
	
	/**
	 * 通过角色id和专案id查询相应的留言
	 * @param roleId 角色id
	 * @param caseId 专案id
	 * @return 返回留言list
	 */
	List<SpecialCaseMessage> findMessagesByRoleIdAndCaseId(String roleId, String caseId);
	
}