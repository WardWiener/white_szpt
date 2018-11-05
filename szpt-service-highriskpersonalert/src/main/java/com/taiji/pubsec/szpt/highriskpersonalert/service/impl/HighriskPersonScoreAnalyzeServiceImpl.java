package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResult;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResultDetail;
import com.taiji.pubsec.szpt.highriskpersonalert.service.HighriskPersonScoreAnalyzeService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
@Service("highriskPersonScoreAnalyzeService")
public class HighriskPersonScoreAnalyzeServiceImpl implements HighriskPersonScoreAnalyzeService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Autowired
	private SqlDao sqlDao ;
	
	@Resource
	private IHighriskPersonService highriskPersonService ;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Map<HighriskPerson, Double>> findHPersonScoreByPage(int pageNo, Integer pageSize, Double alertScore) {
		
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		
		String sql = "SELECT r.score, r.hrpId"
				+ " FROM t_scorecompute_hrpscore_result AS r " ;
		if(alertScore!=null){
			sql += " WHERE r.score>=:alertScore ";
			sqlMap.put("alertScore", alertScore);
		}
				
		sql += " ORDER BY r.score DESC" ;
		Pager<Object[]> sqlPager = this.sqlDao.findByPage(sql, sqlMap, null, pageNo*pageSize, pageSize) ;
		
		List<Map<HighriskPerson, Double>> list = new ArrayList<Map<HighriskPerson, Double>>() ;
		for(Object[] obj:sqlPager.getPageList()){
			Map<HighriskPerson, Double> rs = new HashMap<HighriskPerson, Double>() ;
			rs.put(highriskPersonService.findHighriskPersonById(obj[1].toString()), Double.valueOf(obj[0].toString()));
			list.add(rs) ;
		}
		
		Pager<Map<HighriskPerson, Double>> pager = new Pager<Map<HighriskPerson, Double>> () ;
		pager.setPageList(list);
		pager.setTotalNum(sqlPager.getTotalNum());

		return pager ;
	}

	@Override
	public HrpScoreResult findHPersonScoreDetail(String hrpId) {
		HrpScoreResult result = new HrpScoreResult() ;
		result.setHrpId(hrpId);
		result.setTotalScore(findTotalScore(hrpId));
		
		List<Object[]> detailList = findScoreResultDetail(hrpId);
		List<HrpScoreResultDetail> hrpScoreResultDetails = new ArrayList<HrpScoreResultDetail>();
		for(Object[] detail:detailList){
			HrpScoreResultDetail dt = new HrpScoreResultDetail() ;
			dt.setScore(Double.valueOf(detail[0].toString()));
			dt.setOtherResults(detail[1].toString());
			hrpScoreResultDetails.add(dt);
		}
		result.setHrpScoreResultDetails(hrpScoreResultDetails);
		return result;
	}
	
	private Double findTotalScore(String hrpId){
		String sql = "SELECT r.score FROM t_scorecompute_hrpscore_result AS r WHERE r.hrpId=:hrpId" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("hrpId", hrpId);
		Object object = this.sqlDao.findUnique(sql, sqlMap);
		if(object!=null){
			Double score = Double.valueOf(object.toString()) ;
			return score ;
		}else{
			return 0d ;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> findScoreResultDetail(String hrpId){
		String sql = "SELECT scr.scoreAfterWeight, rr.otherResults "
				+ " FROM t_scorecompute_hrpscore_rule_result AS rr join t_scorecompute_hrpscore_scorepointconfig_result as scr ON rr.scorepointid=scr.scorepointid "
				+ " WHERE rr.hrpId=:hrpId" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("hrpId", hrpId);
		List<Object[]> list = this.sqlDao.find(sql, sqlMap) ;
		return list;
	}
}
