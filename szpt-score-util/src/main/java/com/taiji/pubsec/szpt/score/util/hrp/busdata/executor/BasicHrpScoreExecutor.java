package com.taiji.pubsec.szpt.score.util.hrp.busdata.executor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;

@Service
public class BasicHrpScoreExecutor extends AbstractHrpScoreExecutor{

	@Autowired
	private SqlDao sqlDao ;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;


	
	public static BasicHrpScoreExecutor getScoreExecutor(String id){
		BasicHrpScoreExecutor executor = SpringContextUtil.getApplicationContext().getBean(BasicHrpScoreExecutor.class) ;
		return executor;
	}

	@Override
	public Object[] excute(Parameter... params) {
		String hrpId = getHrpId(params) ;
		Parameter[] result = findHrpInfo(hrpId) ;
		return result ;
	}
	
	private Parameter[] findHrpInfo(String hrpId){
		String sql = "SELECT h.id AS id, "
				+ " h.zklx AS personInControlType, "
				+ " h.jyqk AS employmentStatus, "
				+ " h.hyqk AS marriageStatus, "
				+ " h.sr AS income "
				+ " FROM t_gwry_ryxx as h "
				+ " WHERE h.id=:id" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("id", hrpId);
		
		Object[] obj = (Object[])this.sqlDao.findUnique(sql, sqlMap) ;
		
		Parameter[] result = new Parameter[obj.length] ;

		Object o = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.HRP_ZDLX_EMPLOYMENT_STATUS, obj[2].toString(), null) ;
		
		result[0] = new DefaultParameter(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.HRP_ZDLX_INPORTANT_CONTROL_TYPE, obj[1].toString(), null).getName(), RuleDetail.SCORE_POINT_INPORTANT_CONTROL_TYPE) ;
		result[1] = new DefaultParameter(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.HRP_ZDLX_EMPLOYMENT_STATUS, obj[2].toString(), null).getName(), RuleDetail.SCORE_POINT_EMPLOYMENT_STATUS) ;
		result[2] = new DefaultParameter(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.HRP_ZDLX_MARRIAGE_STATUS, obj[3].toString(), null).getName(), RuleDetail.SCORE_POINT_MARRIAGE_STATUS) ;
		result[3] = new DefaultParameter(obj[4], RuleDetail.SCORE_POINT_INCOME) ;

		return result ;
	}


}
