package com.taiji.pubsec.szpt.instruction.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

/**
 * 指令接口
 * @author wangfx
 *
 */
public interface IInstructionService {
	
	/**
	 * 根据关联主题id查询指令反馈信息 
	 * @param RelatedObjectId 关联主题id
	  * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 指令反馈信息分页
	 */
	Pager<InstructionReceiveSubjectFeedback> findInstructionsByRelatedObjectIdByPage(String RelatedObjectId, int pageNo, int pageSize);
	
	/**
	 * 根据创建人id查询指令分页 
	 * @param createPeopleId 创建人id
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 指令信息分页
	 */
	Pager<Instruction> findInstructionsByPersonIdByPage(String createPeopleId, int pageNo, int pageSize);
	
	/**
	 * 根据条件查找指令接收主体反馈
	 * @param feedbackId 接收主体反馈id
	 * @return
	 */
	public InstructionReceiveSubjectFeedback findFeedbackById(String feedbackId) ;
	
	/**
	 * 根据条件查询指令
	 * @param paramMap 查询条件map
	 * </br>:content 指令内容（模糊匹配）
	 * </br>:createTimeStart 创建时间开始
	 * </br>:createTimeEnd  创建时间结束
	 * </br>:type 指令类型
	 * </br>:receiveUnitId 接收单位id
	 * </br>:requireFeedbackTimeStart 要求反馈时间开始
	 * </br>:requireFeedbackTimeEnd 要求反馈时间结束
	 * </br>:loginUnitId 当前登陆单位（发送单位）
	 * </br>:relatedObjectId 关联主体id
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 指令信息分页
	 */
	Pager<Instruction> findInstructionsByPage(Map<String, Object> paramMap, int pageNo, int pageSize);
	
	/**
	 * 根据条件查找指令(接收单位使用)
	 * @param content 指令内容
	 * @param paramMap 查询条件map
	 * </br>:createTimeStart 发送时间开始
	 * </br>:createTimeEnd  发送间结束
	 * </br>:status 指令状态
	 * </br>:type 指令类型
	 * </br>:loginUnitId 当前登陆单位（发送单位）
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 指令信息分页
	 */
	Pager<InstructionReceiveSubject> findInstructionsByPageOfReceiveDepartment(String content, Map<String, Object> paramMap, int pageNo, int pageSize);

	/**
	 * 添加指令
	 * @param instruction 指令
	 * @return id
	 */
	String createInstruction(Instruction instruction);
	
	/**
	 * 已有指令下发
	 * @param instructionReceiveSubject
	 * </br>instructionReceiveSubject.instruction 指令
	 * @return 指令接收主题id
	 */
	String addInstructionReceiveSubject(InstructionReceiveSubject instructionReceiveSubject);
	
	/**
	 * 保存操作记录
	 * @param operationRecord 操作记录
	 * @return id
	 */
	String saveOperationRecord(OperationRecord operationRecord);
	
	/**
	 * 根基主键查询指令
	 * @param instructionId 指令id
	 * @return 指令
	 */
	Instruction findInstructionById(String instructionId);
	
	/**
	 * 根据主键查询指令接收主题
	 * @param instructionReceiveSubjectId 指令接收主题id
	 * @return 指令接收主题
	 */
	InstructionReceiveSubject findInstructionReceiveSubjectById(String instructionReceiveSubjectId);
	
	/**
	 * 根据主键和操作单位查找相关的操作记录
	 * unitIDs为空时查询所有的
	 * @param instructionId 指令id
	 * @param unitIds
	 * @return 操作记录列表
	 */
	List<OperationRecord> findOperationRecordByInstruction(String instructionId, List<String> unitIds);
	
	/**
	 * 反馈
	 * @param instructionReceiveSubjectFeedback 指令反馈信息
	 * @param InstructionReceiveSubjectId 指令接收主题id
	 */
	String feedbackInstruction(InstructionReceiveSubjectFeedback instructionReceiveSubjectFeedback, String InstructionReceiveSubjectId);
	
	/**
	 * 签收
	 * @param instructionReceiveSubjectSign 指令签收信息
	 * @param InstructionReceiveSubjectId 指令接收主题id
	 */
	void signInstruction(InstructionReceiveSubjectSign instructionReceiveSubjectSign, String InstructionReceiveSubjectId);	
	
	/**
	 * 通过用户名和时间查询新的已办(或待办)的指令消息
	 * @param accountName 用户名
	 * @param queryTime 查询时间
	 * @param flag 为“0”，表示查询待办的指令；为“1”，表示查询已办的指令
	 * @return 返回指令list
	 */
	List<Instruction> findNewInstructionByConditions(String accountName, Date queryTime, String flag);
	
	/**
	 * 通过用户名和时间查询相应数量的已有的已办(或待办)的指令消息 
	 * @param accountName 用户名
	 * @param queryTime 查询时间
	 * @param size 查询个数
	 * @param flag 为“0”，表示查询待办的指令；为“1”，表示查询已办的指令
	 * @return 返回Map<Boolean,List<Instruction>>，boolean标识还有没有更多的指令消息，为true：表示还有；为false：表示没有。list是查询得到的指令消息
	 */
	Map<Boolean,List<Instruction>> findOldInstructionByConditions(String accountName, Date queryTime, int size, String flag);
	
	/**
	 * 通过指令id和单位id查询相应的指令接受主题
	 * @param instructionId 指令id
	 * @param unitId 单位id
	 * @return 指令接受主题
	 */
	InstructionReceiveSubject findReceiveSubjectByInstructionIdAndUnitId(String instructionId, String unitId);
	
	/**
	 * 通过指令id和反馈人id查询反馈信息list
	 * @param instructionId 指令id
	 * @param feedBackPersonId 反馈人id
	 * @return 返回反馈信息list，按反馈时间倒序排列
	 */
	List<InstructionReceiveSubjectFeedback> findFeedBacksByInstructionIdAndPersonId(String instructionId, String feedBackPersonId);
	
	/**
	 * 通过指令id和单位id查询签收信息
	 * @param instructionId 指令id
	 * @param unitId 单位id
	 * @return 查到返回true，查不到返回false
	 */
	boolean findInstructionSignByInstructionIdAndUnitId(String instructionId, String unitId);
	
	/**
	 * 指令类型相关的内容查询指令列表
	 * 
	 * @param typeContent 指令类型相关的内容
	 * @return 返回指令list
	 */
	public List<Instruction> findInstructionByTypeContent(String typeContent);
	
	/**
	 * 关联主题id查询指令列表
	 * @param relatedObjectId 关联主题id
	 * @return
	 */
	public List<Instruction> findInstructionByRelatedObjectId(String relatedObjectId);
	
	/**
	 * 关联主题id查询反馈信息list
	 * @param relatedObjectId
	 * @return
	 */
	public List<InstructionReceiveSubjectFeedback> findFeedBacksByInstructionRelatedObjectId(String relatedObjectId);
	
	/**
	 * 关联主体id和指令类型查询指令列表
	 * @param relatedObjectId 关联主题id
	 * @param type 指令类型
	 * @return
	 */
	public List<Instruction> findInstructionByRelatedObjectIdAndType(String relatedObjectId, String type);
}
