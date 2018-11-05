package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.bean.CaseInfoServiceBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ArchivedFile;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CrimialCaseNote;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ImpoundedObject;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;

/**
 * 案件相关信息查询服务接口
 * 
 * @author dixiaofeng
 */
public interface CaseService {

	/**
	 * 按条件分页查询案件基本信息。从案件基本信息表查询。
	 * 
	 * @param params    查询参数值对
	 * "caseCode",      	  "案件编号"
	 *	"caseName",      	  "案件名称"
	 *	"caseAddress",   	  "案发地点"
	 *	"caseSort",      	  "案件类别"
	 *	"caseKind",      	  "案件性质"
	 *	"regionCode",      	  "案发辖区"
	 *	"communityCode",      "案发社区"
	 *	"discoverTimeStart",  "发现时间"
	 *	"discoverTimeEnd",    "截止时间"
	 *	"tagState",           "打标状态"
	 *	"polices",			  "办案民警"	
	 *	"handlingUnit",		  "办案单位"
	 *	"ifSolved",           "是否破案"
	 *
	 * @param pageNo    页号
	 * @param pageSize    每页条数
	 */
	public Pager<CriminalBasicCase> findCaseByConditions(Map<String,Object> params, int pageNo, int pageSize);

	/**
	 * 按案件编码查询案件基本信息。
	 * 
	 * @param caseCode    案件编码
	 */
	public CriminalBasicCase findCaseByCode(String caseCode);

	/**
	 * 按案件编码查询关联的警情信息。
	 * 
	 * @param caseCode    案件编码
	 */
	public FactJq findAlarmByCase(String caseCode);

	/**
	 * 按案件编码查询关联的警情信息问询笔录。
	 * 
	 * @param caseCode    案件编码
	 */
	public List<CrimialCaseNote> findNotesByCase(String caseCode);

	/**
	 * 按案件编码查询涉案嫌疑人。
	 * 
	 * @param caseCode    案件编码
	 */
	public List<CriminalPerson> findSuspectsByCase(String caseCode);

	/**
	 * 按案件编码查询涉案物品。
	 * 
	 * @param caseCode    案件编码
	 */
	public List<CriminalObject> findObjectsByCase(String caseCode);
	
	/**
	 * 按案件编码查询案件管理涉案物品。
	 * 
	 * @param caseCode    案件编码
	 */
	public List<ImpoundedObject> findImpoundedObjectsByCase(String caseCode);

	/**
	 * 查找嫌疑人涉及的所有案件信息。
	 * 
	 * @param suspectCode    嫌疑人编码
	 */
	public List<CriminalBasicCase> findCaseBySuspect(String suspectCode);
	
	/**
	 * 查找嫌疑人涉及的所有案件信息。
	 * 
	 * @param suspectIdcard    嫌疑人身份证号
	 */
	public List<CriminalBasicCase> findCaseBySuspectIdcard(String suspectIdcard);

	/**
	 * 查询案件的串并案信息。
	 * 
	 * @param caseCode
	 */
	public List<CriminalBasicCase> findRelatedCase(String caseCode);
	
	/**
	 * 查询案件的相关文书。
	 * 
	 * @param caseCode
	 */
	public List<ArchivedFile> findArchivedFileCase(String caseCode);
	
	/**
	 * 通过条件查询案件经纬度信息
	 * @param conditions 查询条件，包括：
	 * <br>caseTimeStart:发案时间开始
	 * <br>caseTimeEnd:发案时间结束
	 * <br>pcsList:派出所id的String 数组
	 * @return 返回案件bean的list
	 */
	List<CaseInfoServiceBean> findCaseInfoByConditions(Date caseTimeStart, Date caseTimeEnd, String[] pcsCodes);

}