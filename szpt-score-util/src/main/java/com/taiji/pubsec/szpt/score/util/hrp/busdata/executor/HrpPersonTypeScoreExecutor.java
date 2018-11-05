package com.taiji.pubsec.szpt.score.util.hrp.busdata.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
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
public class HrpPersonTypeScoreExecutor extends AbstractHrpScoreExecutor{

	@Autowired
	private SqlDao sqlDao ;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	
	public static HrpPersonTypeScoreExecutor getScoreExecutor(String id){
		HrpPersonTypeScoreExecutor executor = SpringContextUtil.getApplicationContext().getBean(HrpPersonTypeScoreExecutor.class) ;
		return executor;
	}

	@Override
	public Object[] excute(Parameter... params) {
		return findAllPersonTypeInfo(getHrpId(params));
	}
	
	private Parameter[] findAllPersonTypeInfo(String hrpId){
		Parameter[] ryType = findHrpPersonTypeInfo(hrpId) ;
		Parameter[] qkType = findHrpCriminalRecordType(hrpId) ;
		
		Parameter[] result = ArrayUtils.addAll(ryType, qkType) ;
		
		DefaultParameter dp = new DefaultParameter(result, RuleDetail.SCORE_POINT_PERSON_TYPE) ;

		return new Parameter[]{dp} ;
	}
	
	@SuppressWarnings("unchecked")
	private Parameter[] findHrpPersonTypeInfo(String hrpId){
		
		String sql = "SELECT tp.rylx FROM t_gwry_gwrylx AS tp JOIN t_gwry_ryxx AS r ON tp.gwry_id=r.id WHERE r.id=:id" ;
		
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("id", hrpId);
		
		List<Object> list = this.sqlDao.find(sql, sqlMap) ;
		
		Parameter[] result = new Parameter[list.size()] ;

		for(int i=0; i<list.size(); i++){
			String itemName = generateDictItemName(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.HRP_ZDLX_PERSON_TYPE, list.get(i).toString(), null)) ;
			result[i] = new DefaultParameter(itemName, RuleDetail.SCORE_POINT_PERSON_TYPE) ;
		}
		
		return result ;
	}
	
	private Parameter[] findHrpCriminalRecordType(String hrpId){
		//String sql = "SELECT qklx.qklx FROM t_gwry_gwryqklx as qklx JOIN t_gwry_gwrylx as rylx ON qklx.gwrylx_id=rylx.id JOIN t_gwry_ryxx as ry ON rylx.gwry_id=ry.id WHERE ry.id=:id" ;
		return new Parameter[]{} ;
	}
}
