package com.taiji.pubsec.szpt.zhzats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.bean.yanpanListBean;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.model.JqAnalyze;
import com.taiji.pubsec.szpt.zhzats.model.JqVideo;
import com.taiji.pubsec.szpt.zhzats.service.FactJqService;
import com.taiji.pubsec.szpt.zhzats.service.JqAnalyzeService;
import com.taiji.pubsec.szpt.zhzats.service.YanpanService;

import net.sf.json.JSONObject;


@Controller("yanpanAction")
@Scope("prototype")
public class YanpanAction extends BaseAction{	
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(YanpanAction.class);
	
	private String queryStr ;
	private int start;
	private int length;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	@Resource
	private YanpanService yanpanService;

	@Resource
	private FactJqService factJqService;
	
	@Resource
	private JqAnalyzeService jqAnalyzeService;

	@Resource
	private IInstructionService instructionService;
	
	/**
	 * 刑事警情研判查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String xsjqYanpanQuery(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Pager<yanpanListBean> pager=yanpanService.findXsjqByPage(data,start/length, length);
		resultMap.put("list", pager.getPageList());
		resultMap.put("totalNum",pager.getTotalNum());
		return SUCCESS;
	}

	/**
	 * 警情基本信息和刑侦接警反馈记录
	 * @return
	 */
	public String jqBasicMessageQuery(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String jqId=queryStr;
			FactJq jq = factJqService.findFactJqByCode(queryStr);
			if(null != jq){
				jqId = jq.getId();
			}
			FactJq factJq=factJqService.findFactJqById(jqId);
			JqAnalyze jqAnalyze=jqAnalyzeService.findJqAnalyzeById(jqId);
			resultMap.put("factJq",factJq);
			resultMap.put("jqAnalyze",jqAnalyze);
		}
		return SUCCESS;
	}
	
	/**
	 * 反馈情况
	 * @return
	 */
	public String feedbackCaseQuery(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String jqId=queryStr;
			List<CjFeedback> cjFeedback=factJqService.findCjFeedbackByJqId(jqId);
			
			resultMap.put("list",cjFeedback);
		}
		return SUCCESS;
	}
	
	/**
	 * 警情处置记录
	 * @return	
		 */
//	public String jqDisposeRecordQuery(){
//		
//		resultMap.put("list","list");
//		return SUCCESS;
//	}
		
	/**
	 * 警情相关关视频
	 * @return
	 */
	public String jqVideoQuery(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String jqCode=queryStr;
			List<JqVideo> list = yanpanService.queryJqVideo(jqCode);
			resultMap.put("list", list);
		}
		return SUCCESS;
	}
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
