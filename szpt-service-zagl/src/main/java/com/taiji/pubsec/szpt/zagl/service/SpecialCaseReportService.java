package com.taiji.pubsec.szpt.zagl.service;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseReport;


/**
 * 专案报告服务接口，增删改查功能
 * @author chengkai
 */
public interface SpecialCaseReportService {

	/**
	 * 添加报告信息
	 * @param report 专案报告
	 * @return 成功返回true；失败返回false
	 */
	public boolean addReport(SpecialCaseReport report);

	/**
	 * 删除报告信息
	 * @param reportId 报告id
	 * @return 成功返回true；失败返回false
	 */
	public boolean deleteReport(String reportId);

	/**
	 * 分页查询专案所有报告，按创建时间倒序。
	 * @param caseId    专案id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回报告信息列表，没有返回空
	 */
	public Pager<SpecialCaseReport> findReportByCase(String caseId, int pageNo, int pageSize);

	/**
	 * 按id查询报告。
	 * @param reportId 报告id
	 * @return 返回专案报告，没查询到返回null
	 */
	public SpecialCaseReport findReportId(String reportId);

	/**
	 * 更新报告信息。
	 * @param report 专案报告
	 * @return 成功返回true；失败返回false
	 */
	public boolean updateReport(SpecialCaseReport report);

}