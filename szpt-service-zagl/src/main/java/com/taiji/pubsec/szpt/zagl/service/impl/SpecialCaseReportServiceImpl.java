package com.taiji.pubsec.szpt.zagl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseReport;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseReportService;

@Service("specialCaseReportService")
public class SpecialCaseReportServiceImpl implements SpecialCaseReportService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpecialCaseReportServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean addReport(SpecialCaseReport report) {
		try {
			dao.save(report);
		} catch (Exception e) {
			LOGGER.debug("添加专案报告失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteReport(String reportId) {
		try {
			dao.delete(SpecialCaseReport.class, reportId);
		} catch (Exception e) {
			LOGGER.debug("删除专案报告失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseReport> findReportByCase(String caseId, int pageNo, int pageSize) {
		String xql = "select r from SpecialCaseReport as r where r.specialCase.id = ?";
		return dao.findByPage(SpecialCaseReport.class, new Object[]{caseId}, xql, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseReport findReportId(String reportId) {
		return (SpecialCaseReport) dao.findById(SpecialCaseReport.class, reportId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateReport(SpecialCaseReport report) {
		try {
			dao.update(report);
		} catch (Exception e) {
			LOGGER.debug("更新专案报告失败", e);
			return false;
		}
		return true;
	}
	
}
