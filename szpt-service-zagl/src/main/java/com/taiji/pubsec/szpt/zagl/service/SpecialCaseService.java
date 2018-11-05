package com.taiji.pubsec.szpt.zagl.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.CaseRelation;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPerson;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPersonRelation;


/**
 * 专案服务接口，增删改查
 * @author dixiaofeng
 * @version 1.0
 * @created 21-11月-2016 19:24:14
 */
public interface SpecialCaseService {

	/**
	 * 创建专案基本信息
	 * @param specialCase 专案信息
	 * @return 创建成功返回true；失败返回false
	 */
	public boolean createSpecialCase(SpecialCase specialCase);

	/**
	 * 根据专案id查询分页查询专案的涉案人员信息，按创建时间倒序
	 * @param caseId 专案id 
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回专案人员分页列表信息，按创建时间倒序
	 */
	public Pager<SpecialCaseInvolvedPerson> findInvolvedPersonByCase(String caseId, int pageNo, int pageSize);

	/**
	 * 根据专案id查询专案的涉案人员关系信息
	 * @param caseId 专案id
	 * @return 返回人员关系信息列表，没查找到返回null
	 */
	public List<SpecialCaseInvolvedPersonRelation> findInvolvedPersonRelationsByCase(String caseId);

	/**
	 * 按条件分页查询所有专案信息，按创建时间倒序
	 * @param conditions  查询条件conditions:
	 * <br>zabh:专案编号
	 * <br>zamc:专案名称
	 * <br>ajxz:专案性质
	 * <br>sjzaj:涉及子案件
	 * <br>jyaq:简要案情
	 * <br>zazcy:专家组成员
	 * @param personId 我的专案，人员id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回专案分页信息
	 */
	public Pager<SpecialCase> findSpecialCaseByConditions(Map<String,Object> conditions, String personId, int pageNo, int pageSize);

	/**
	 * 根据id查询专案信息
	 * @param id 专案id
	 * @return 返回专案信息，没找到返回null
	 */
	public SpecialCase findSpecialCaseById(String id);

	/**
	 * 更新涉案人员关系。
	 * @param caseId 专案id
	 * @param toUpdateRelations    新增/更新关系信息列表
	 * @param toDelRelations    删除的关系id列表
	 * @return 成功返回true；失败返回false
	 */
	public boolean updateInvolvedPersonRelations(String caseId, List<SpecialCaseInvolvedPersonRelation> toUpdateRelations, List<String> toDelRelations);

	/**
	 * 更新涉案人员信息
	 * @param caseId 专案id
	 * @param toUpdatedPersons 添加/更新涉案人员列表
	 * @param toDelPersons 删除人员id列表
	 * @return 成功返回true；失败返回false
	 */
	public boolean updateInvolvedPersons(String caseId, List<SpecialCaseInvolvedPerson> toUpdatedPersons, List<String> toDelPersons);

	/**
	 * 更新专案基本信息
	 * @param specialCase 专案信息
	 * @return 更新成功返回true；失败返回false
	 */
	public boolean updateSpecialCase(SpecialCase specialCase);
	
	/**
	 * 删除专案及关联的其他信息
	 * @param specialCaseId 专案id
	 * @return 删除成功，返回true；删除失败，返回false
	 */
	boolean delete(String specialCaseId);
	
	/**
	 * 置顶专案
	 * @param caseId 专案id
	 * @param stickPerson 置顶人
	 * @param stickTime 置顶时间
	 * @return 成功返回true，失败返回false
	 */
	boolean stickSpecialCase(String caseId, String stickPerson, Date stickTime);
	
	/**
	 * 取消置顶专案
	 * @param caseId 专案id
	 * @param personId 人员id
	 * @return 成功返回true，失败返回false
	 */
	boolean unstickSpecialCase(String caseId, String personId);
	
	/**
	 * 获取专案编号
	 * @return 返回专案编号
	 */
	public String acquireNum();
	
	/**
	 * 专案新增子案件
	 * @param caseRelation 专案子案件关系表
	 * @return
	 */
	public void addSonCase(CaseRelation caseRelation);
	
	/**
	 * 通过专案id和子案件编号删除子案件
	 * @param caseId 专案id
	 * @param sonCaseCode 子案件编号
	 * @return 删除成功，返回true；删除失败，返回false
	 */
	public boolean deleteSonCase(String caseId, String sonCaseCode);

}