package com.taiji.pubsec.szpt.caseanalysis.tag.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.complex.tools.web.action.ExportInfoReq;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseTagCompareBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.KnownAlikeCaseExcelBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.ArchivedFileBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CrimialCaseNoteBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalObjectBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalPersonBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ArchivedFile;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CrimialCaseNote;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ImpoundedObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.util.CommonBeanModelConverterUtil;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.ExcelUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zhzats.bean.CjFeedbackBean;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.service.FactJqService;
import com.taiji.pubsec.szpt.zhzats.util.ZhzatsModelBeanTransformUtil;

import net.sf.json.JSONObject;

/**
 * 案件Action
 * 
 * @author WangLei
 *
 */
@Controller("criminalBasicCaseAction")
@Scope("prototype")
public class CriminalBasicCaseAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CriminalBasicCaseAction.class);
	
	@Resource
	private CaseService caseService;// 案件接口
	
	@Resource
	private FactJqService factJqService;// jwzh警情接口
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人接口
	
	@Resource
	private ZhzatsModelBeanTransformUtil zhzatsModelBeanTransformUtil;// 公用的综合治安态势Bean和Model互转工具类
	
	@Resource
	private CommonBeanModelConverterUtil commonBeanModelConverterUtil;// 公用的Bean和Model互转工具类
	
	private String caseCode;// 案件编号
	
	private String caseName;// 案件名称
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用
	
	private ExportInfoReq exportInfoReq = new ExportInfoReq();// 导出Excel用
	
	/**
	 * 根据嫌疑人编号查询前科案件
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySuspectQkCaseBySuspectCode(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String suspectCode = (String)rqst.get("suspectCode");
		List<CriminalBasicCase> cbcs = caseService.findCaseBySuspect(suspectCode);
		
		List<CriminalBasicCaseBean> cbcbs = new ArrayList<CriminalBasicCaseBean>();
		for(CriminalBasicCase cbc : cbcs){
			CriminalBasicCaseBean cbcb = commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc);
			if(cbcb == null){
				continue;
			}
			cbcbs.add(cbcb);
		}
		resultMap.put("cbcbs", cbcbs);
		return SUCCESS;
	}
	
	/**
	 * 根据条件查询案件
	 * @return
	 */
	public String queryCaseByCondition(){
		Map<String, Object> rqst = new HashMap<String, Object>();
		rqst.put("caseCode", caseCode);
		rqst.put("caseName", caseName);
		Pager<CriminalBasicCase> pager = caseService.findCaseByConditions(rqst, this.getStart()/this.getLength(), this.getLength());
		
		List<CriminalBasicCaseBean> cbcbs = new ArrayList<CriminalBasicCaseBean>();
		if(pager == null || pager.getPageList().isEmpty()){
			resultMap.put("caseArray", cbcbs);
			resultMap.put("totalNum", 0);
			return SUCCESS;
		}
		
		for(CriminalBasicCase cbc : pager.getPageList()){
			CriminalBasicCaseBean cbcb = commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc);
			if(cbcb == null){
				continue;
			}
			cbcbs.add(cbcb);
		}
		resultMap.put("caseArray", cbcbs);
		resultMap.put("totalNum", pager.getTotalNum());
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询已知串并案列表
	 * 
	 * @return
	 */
	public String queryKnownCaseByCaseCode(){
		List<CriminalBasicCase> cbcs = caseService.findRelatedCase(caseCode);
		List<CriminalBasicCaseBean> cbcbs = new ArrayList<CriminalBasicCaseBean>();
		List<CaseTagCompareBean> ctcbs = new ArrayList<CaseTagCompareBean>();
		for(CriminalBasicCase cbc : cbcs){
			CaseTag ct = cbc.getCaseTag();
			CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(ct);
			CaseTagCompareBean ctcb = new CaseTagCompareBean();
			if(ctb == null){
				ctcb.setCaseCode(cbc.getCaseCode());
				ctcb.setCaseName(cbc.getCaseName());
				ctcb.setLevelName("");
				ctcb.setTypeName("");
				ctcb.setFirstSortName("");
				ctcb.setSecondSortName("");
				ctcb.setAddress("");
				ctcb.setCommunityName("");
				ctcb.setPlaceTypeName("");
				ctcb.setOccurPlaceName("");
				ctcb.setEntranceName("");
				ctcb.setExitName("");
				ctcb.setPeopleNumName("");
				ctcb.setFeatureNames("");
				ctcb.setCaseTimeStart(cbc.getCaseTimeStart()==null?null:cbc.getCaseTimeStart().getTime());
				ctcb.setCaseTimeEnd(cbc.getCaseTimeEnd()==null?null:cbc.getCaseTimeEnd().getTime());
			}else{
				BeanCopier copier = BeanCopier.create(ctb.getClass(), ctcb.getClass(), false);
				copier.copy(ctb, ctcb, null);
			}
			String handlePolice = cbc.getHandlePolice();
			ctcb.setHandlePolice(StringUtils.isBlank(handlePolice) || "null".equals(handlePolice) ? "" : handlePolice);
			ctcb.setCaseStateName(cbc.getCaseStateName());
			ctcb.setSuspectName(commonBeanModelConverterUtil.findSuspectNameByCaseCode(cbc.getCaseCode()));
			ctcbs.add(ctcb);
			
			CriminalBasicCaseBean cbcb = commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc);
			if(cbcb != null){
				cbcbs.add(cbcb);
			}
		}
		resultMap.put("cbcbs", cbcbs);
		resultMap.put("ctcbs", ctcbs);
		return SUCCESS;
	}
	
	/**
	 * 根据编号查询案件
	 * 
	 * @return
	 */
	public String queryCriminalBasicCaseByCode(){
		CriminalBasicCase cbc = caseService.findCaseByCode(caseCode);
		resultMap.put("cbcb", commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc));
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询警情
	 * 
	 * @return
	 */
	public String queryAlarmByCaseCode(){
		FactJq jq = caseService.findAlarmByCase(caseCode);
		resultMap.put("jq", jq);
		
		List<CjFeedbackBean> feedbacks = new ArrayList<CjFeedbackBean>();
		if(jq == null){
			resultMap.put("feedbacks", feedbacks);
			return SUCCESS;
		}
		
		//查询jwzh警情处警相关反馈
		List<CjFeedback> cjfs = factJqService.findCjFeedbackByJqId(jq.getId());
		if(cjfs == null){
			resultMap.put("feedbacks", feedbacks);
			return SUCCESS;
		}
		
		for(CjFeedback cjf : cjfs){
			CjFeedbackBean cfb = zhzatsModelBeanTransformUtil.CjFeedbackToCjFeedbackBean(cjf);
			if(cfb == null){
				continue;
			}
			feedbacks.add(cfb);
		}
		resultMap.put("feedbacks", feedbacks);
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询嫌疑人
	 * 
	 * @return
	 */
	public String queryCriminalPersonByCaseCode(){
		List<CriminalPerson> cps = caseService.findSuspectsByCase(caseCode);
		List<CriminalPersonBean> cpbs = new ArrayList<CriminalPersonBean>();
		for(CriminalPerson cp : cps){
			CriminalPersonBean cpb = criminalPersonToCriminalPersonBean(cp);
			if(cpb == null){
				continue;
			}
			cpbs.add(cpb);
		}
		resultMap.put("cpbs", cpbs);
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询问询笔录
	 * 
	 * @return
	 */
	public String queryCrimialCaseNoteByCaseCode(){
		List<CrimialCaseNote> ccns = caseService.findNotesByCase(caseCode);
		List<CrimialCaseNoteBean> ccnbs = new ArrayList<CrimialCaseNoteBean>();
		for(CrimialCaseNote ccn : ccns){
			CrimialCaseNoteBean ccnb = commonBeanModelConverterUtil.crimialCaseNoteToCrimialCaseNoteBean(ccn);
			if(ccnb == null){
				continue;
			}
			ccnbs.add(ccnb);
		}
		resultMap.put("ccnbs", ccnbs);
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询涉案物品
	 * 
	 * @return
	 */
	public String queryCriminalObjectByCaseCode(){
		List<CriminalObject> cos = caseService.findObjectsByCase(caseCode);
		List<CriminalObjectBean> cobs = new ArrayList<CriminalObjectBean>();
		for(CriminalObject co : cos){
			CriminalObjectBean cob = commonBeanModelConverterUtil.criminalObjectToCriminalObjectBean(co);
			if(cob == null){
				continue;
			}
			cobs.add(cob);
		}
		List<ImpoundedObject> ios = caseService.findImpoundedObjectsByCase(caseCode);
		for(ImpoundedObject io : ios){
			CriminalObjectBean cob = commonBeanModelConverterUtil.impoundedObjectToCriminalObjectBean(io);
			if(cob == null){
				continue;
			}
			cobs.add(cob);
		}
		resultMap.put("cobs", cobs);
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号查询卷宗文书
	 * 
	 * @return
	 */
	public String queryArchivedFileByCaseCode(){
		List<ArchivedFile> afs = caseService.findArchivedFileCase(caseCode);
		List<ArchivedFileBean> afbs = new ArrayList<ArchivedFileBean>();
		for(ArchivedFile af : afs){
			ArchivedFileBean afb = commonBeanModelConverterUtil.archivedFileToArchivedFileBean(af);
			if(afb == null){
				continue;
			}
			afbs.add(afb);
		}
		resultMap.put("afbs", afbs);
		return SUCCESS;
	}
	
	/**
	 * 已知串并案列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportExcelByKnownCaseList() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<KnownAlikeCaseExcelBean> kacebs = new ArrayList<KnownAlikeCaseExcelBean>();// 导出ExcelBean集合
		queryKnownCaseByCaseCode();
		
		int i = 1;
		for(Object obj : (List<Object>)resultMap.get("ctcbs")) {
			CaseTagCompareBean ctcb = (CaseTagCompareBean)obj;
			KnownAlikeCaseExcelBean kaceb = new KnownAlikeCaseExcelBean();
			BeanCopier copier = BeanCopier.create(ctcb.getClass(), kaceb.getClass(), false);
			copier.copy(ctcb, kaceb, null);
			kaceb.setNo(String.valueOf(i));
			kaceb.setCaseTimeStart(sdf.format(ctcb.getCaseTimeStart()));
			i++;
			kacebs.add(kaceb);
		}
		
		ExcelUtil<KnownAlikeCaseExcelBean> ex = new ExcelUtil<KnownAlikeCaseExcelBean>();
		String[] headers = { "序号", "案件编号", "案发时间", "办案民警", "案件类别", "案件性质", "二级案件性质",
				"作案特点", "选择处所", "作案时段", "作案人数", "作案进口", "作案出口", "案发社区"};
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ex.exportExcel(headers, kacebs, bos);
		byte[] bytes = bos.toByteArray();
		bos.close();
		ByteArrayInputStream ins=new ByteArrayInputStream(bytes);
		exportInfoReq.setIn(ins);
		exportInfoReq.setName(name);
		exportInfoReq.setLength(Long.valueOf(bytes.length));
		ins.close();
		return "done";
	}
	
	/**
	 * 涉案人员Model 转 Bean
	 * 
	 * @param cp
	 * @return
	 */
	private CriminalPersonBean criminalPersonToCriminalPersonBean(CriminalPerson cp){
		if(cp == null){
			return null;
		}
		CriminalPersonBean cpb = new CriminalPersonBean();
		BeanCopier copier = BeanCopier.create(cp.getClass(), cpb.getClass(), false);
		copier.copy(cp, cpb, null);
		cpb.setStaturest(cp.getStaturest() == null ? null : String.valueOf(cp.getStaturest()));
		cpb.setAvoirdupois(cp.getAvoirdupois() == null ? null : String.valueOf(cp.getAvoirdupois()));
		cpb.setFootSize(cp.getFootSize() == null ? null : String.valueOf(cp.getFootSize()));
		cpb.setShoesSize(cp.getShoesSize() == null ? null : String.valueOf(cp.getShoesSize()));
		cpb.setBirthday(DateFmtUtil.dateToLong(cp.getBirthday()));
		cpb.setInputTime(DateFmtUtil.dateToLong(cp.getInputTime()));
		cpb.setModifiedTime(DateFmtUtil.dateToLong(cp.getModifiedTime()));
		
		HighriskPerson hp = highriskPersonService.findByIdCode(cp.getIdcardNo());
		if(hp == null){
			cpb.setIsHighrisk("否");
		}else{
			cpb.setIsHighrisk("是");
		}
		return cpb;
	}
	

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public ExportInfoReq getExportInfoReq() {
		return exportInfoReq;
	}

	public void setExportInfoReq(ExportInfoReq exportInfoReq) {
		this.exportInfoReq = exportInfoReq;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	
}
