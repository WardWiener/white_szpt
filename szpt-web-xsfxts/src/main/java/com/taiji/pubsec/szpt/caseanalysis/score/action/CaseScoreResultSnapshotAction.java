package com.taiji.pubsec.szpt.caseanalysis.score.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseAnalysisCompareBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.InfoSnapshotBean;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 串并案打分结果快照Action
 * 
 * @author WangLei
 *
 */
@Controller("caseScoreResultSnapshotAction")
@Scope("prototype")
public class CaseScoreResultSnapshotAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用

	/**
	 * 添加快照
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createSnapshot(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		InfoSnapshot is = new InfoSnapshot();
		is.setTargetId((String)rqst.get("mainCaseCode"));
		is.setType(CriminalBasicCase.class.getName());
		is.setCreatedDate(new Date());
		is.setCreatePerson(this.findCurrentPerson().getName());
		is.setSnapshot(rqst.get("cacbs").toString());
		is.setCode(INFO_SNAPSHOT_MODULE.XSAJFX_CBA.getValue());
		resultMap.put("flag", infoSnapshotService.saveInfoSnapshot(is));
		
		return SUCCESS;
	}
	
	/**
	 * 根据id查询快照
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySnapshotById(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		InfoSnapshot is = infoSnapshotService.findInfoSnapshotById((String)rqst.get("sanpshotId"));
		resultMap.put("csrsb", infoSnapshotToInfoSnapshotBean(is));
		resultMap.put("snapshot", jsonToPossibleCaseCompareData(is.getSnapshot()));
		
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询快照列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySnapshotListByCaseCode(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<InfoSnapshot> isList = infoSnapshotService.findInfoSnapshotByTargetIdAndType((String)rqst.get("mainCaseCode"), CriminalBasicCase.class.getName());
		List<InfoSnapshotBean> csrsbs = new ArrayList<InfoSnapshotBean>();
		for(InfoSnapshot is : isList){
			InfoSnapshotBean isb = infoSnapshotToInfoSnapshotBean(is);
			if(isb == null || !(INFO_SNAPSHOT_MODULE.XSAJFX_CBA.getValue().equals(isb.getCode()))){
				continue;
			}
			csrsbs.add(isb);
		}
		resultMap.put("csrsbs", csrsbs);
		
		return SUCCESS;
	}
	
	/**
	 * json字符串 转 主案件和可能的串并案数据集合
	 * 
	 * @return
	 */
	private List<CaseAnalysisCompareBean> jsonToPossibleCaseCompareData(String json){
		List<CaseAnalysisCompareBean> cacbs = new ArrayList<CaseAnalysisCompareBean>();
		JSONArray cacbJsons = JSONArray.fromObject(json);
		CaseAnalysisCompareBean[] cacbArray = (CaseAnalysisCompareBean[])JSONArray.toArray(cacbJsons, CaseAnalysisCompareBean.class);
		cacbs = Arrays.asList(cacbArray);
		return cacbs;
	}
	
	/**
	 * 快照Model 转 Bean
	 * 
	 * @return
	 */
	private InfoSnapshotBean infoSnapshotToInfoSnapshotBean(InfoSnapshot is){
		if(is == null){
			return null;
		}
		InfoSnapshotBean csrsb = new InfoSnapshotBean();
		BeanCopier copier = BeanCopier.create(is.getClass(), csrsb.getClass(), false);
		copier.copy(is, csrsb, null);
		csrsb.setCreatedDate(is.getCreatedDate().getTime());
		return csrsb;
	}
	

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
}
