package com.taiji.pubsec.szpt.dagl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.ajgl.model.AlarmInfo;
import com.taiji.pubsec.szpt.ajgl.model.ArchivedFile;
import com.taiji.pubsec.szpt.ajgl.model.CaseExecutionProcess;
import com.taiji.pubsec.szpt.ajgl.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.model.CriminalObject;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalObjectService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalSuspectService;
import com.taiji.pubsec.szpt.ajgl.service.ISufferCaseRelationService;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;
import com.taiji.pubsec.szpt.dagl.action.bean.AlarmInfoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CaseExecutionProcessBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CaseSupectRelationBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CriminalBasicCaseDetailBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CriminalObjectBean;
import com.taiji.pubsec.szpt.dagl.action.bean.SufferCaseRelationBean;
import com.taiji.pubsec.szpt.dagl.action.util.CaseBeanCopierConverter;

/**
 * 案件查询Action
 * 
 * @author huangda
 *
 */
@Controller("yaydCaseDetailAction")
@Scope("prototype")
public class YaydCaseDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(YaydCaseDetailAction.class);
	@Resource
	private IUnitService unitService;
	@Resource
	private ICriminalCaseService criminalCaseService;
	@Resource
	private ICriminalSuspectService criminalSuspectService;
	@Resource
	private ISufferCaseRelationService sufferCaseRelationService;
	@Resource
	private ICriminalObjectService criminalObjectService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	private String caseCode;
	private CriminalBasicCaseDetailBean criminalBasicCaseDetailBean = new CriminalBasicCaseDetailBean();
	private List<OptionItemBean> writBeanLst = new ArrayList<OptionItemBean>();
	
	private String dataId;
	private CaseSupectRelationBean caseSupectRelationBean = new CaseSupectRelationBean();
	private SufferCaseRelationBean sufferCaseRelationBean = new SufferCaseRelationBean();
	private CriminalObjectBean criminalObjectBean = new CriminalObjectBean();
	
	private static void copyBean(Object source, Object target, String dateFmt) {
		BeanCopier copier = BeanCopier.create(source.getClass(),
				target.getClass(), true);
		CaseBeanCopierConverter bc = new CaseBeanCopierConverter(dateFmt);
		copier.copy(source, target, bc);
	}

	public String showCsrDetail() {
		CaseSupectRelation csr = criminalSuspectService.findCaseSupectRelationById(dataId);
		copyBean(csr, caseSupectRelationBean, null);
		DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ryzt", caseSupectRelationBean.getPersonState(), null);
		if(di != null){
			caseSupectRelationBean.setPersonState(di.getName());
		}
		if(csr.getPerson() != null){
			copyBean(csr.getPerson(), caseSupectRelationBean.getCriminalPerson(), null);
			//性别
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xb", caseSupectRelationBean.getCriminalPerson().getSex(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setSex(di.getName());
			}
			//出生地代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", caseSupectRelationBean.getCriminalPerson().getBirthCode(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setBirthCode(di.getName());
			}
			//户籍地代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", caseSupectRelationBean.getCriminalPerson().getDoor(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setDoor(di.getName());
			}
			//口音
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", caseSupectRelationBean.getCriminalPerson().getTone(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setTone(di.getName());
			}
			//现住址代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", caseSupectRelationBean.getCriminalPerson().getAddress(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setAddress(di.getName());
			}
		}else{
			copyBean(caseSupectRelationBean.getCriminalPerson(), caseSupectRelationBean.getCriminalPerson(), null);
		}
		return SUCCESS;
	}
	
	public String showScrDetail() {
		SufferCaseRelation scr = sufferCaseRelationService.findSufferCaseRelationById(dataId);
		copyBean(scr, sufferCaseRelationBean, null);
		if(scr.getPerson() != null){
			copyBean(scr.getPerson().getPersonId(), sufferCaseRelationBean.getCriminalPerson(), null);
			//性别
			DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xb", caseSupectRelationBean.getCriminalPerson().getSex(), null);
			if(di != null){
				caseSupectRelationBean.getCriminalPerson().setSex(di.getName());
			}
			//出生地代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", sufferCaseRelationBean.getCriminalPerson().getBirthCode(), null);
			if(di != null){
				sufferCaseRelationBean.getCriminalPerson().setBirthCode(di.getName());
			}
			//户籍地代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", sufferCaseRelationBean.getCriminalPerson().getDoor(), null);
			if(di != null){
				sufferCaseRelationBean.getCriminalPerson().setDoor(di.getName());
			}
			//口音
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", sufferCaseRelationBean.getCriminalPerson().getTone(), null);
			if(di != null){
				sufferCaseRelationBean.getCriminalPerson().setTone(di.getName());
			}
			//现住址代码
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", sufferCaseRelationBean.getCriminalPerson().getAddress(), null);
			if(di != null){
				sufferCaseRelationBean.getCriminalPerson().setAddress(di.getName());
			}
		}else{
			copyBean(sufferCaseRelationBean.getCriminalPerson(), sufferCaseRelationBean.getCriminalPerson(), null);
		}
		return SUCCESS;
	}
	
	public String showCoDetail() {
		CriminalObject co = criminalObjectService.findById(dataId);
		copyBean(co, criminalObjectBean, null);
		return SUCCESS;
	}
	
	public String caseDetail() {
		CriminalBasicCase cbc = criminalCaseService
				.findCriminalCaseByCaseId(caseCode);
		try {
			copyBean(cbc, criminalBasicCaseDetailBean, null);
			//状态
			DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ajzt", criminalBasicCaseDetailBean.getCaseState(), null);
			if(di != null){
				criminalBasicCaseDetailBean.setCaseState(di.getName());
			}
			//类别
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ajlb", criminalBasicCaseDetailBean.getCaseSort(), null);
			if(di != null){
				criminalBasicCaseDetailBean.setCaseSort(di.getName());
			}
			//性质
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ajxz", criminalBasicCaseDetailBean.getCaseKind(), null);
			if(di != null){
				criminalBasicCaseDetailBean.setCaseKind(di.getName());
			}
			//辖区
			Unit u = unitService.findUnitByCode(criminalBasicCaseDetailBean.getAlarmInfoObj().getPopedom());
			if(u != null){
				criminalBasicCaseDetailBean.getAlarmInfoObj().setPopedom(u.getShortName());
			}
			//发案社区
			di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ajsq", criminalBasicCaseDetailBean.getCommunity(), null);
			if(di != null){
				criminalBasicCaseDetailBean.setCommunity(di.getName());
			}
			//办理单位
			if(!"".equals(criminalBasicCaseDetailBean.getDqbldw())){
				criminalBasicCaseDetailBean.setDqbldw(unitService.findUnitByCode(criminalBasicCaseDetailBean.getDqbldw()).getShortName());
			}
			AlarmInfoBean aiBean = new AlarmInfoBean();
			AlarmInfo ai = cbc.getAlarmInfo();
			if(ai != null){
				copyBean(ai, aiBean, null);
			}else{
				copyBean(aiBean, aiBean, null);
			}
			criminalBasicCaseDetailBean.setAlarmInfoObj(aiBean);
			for (CriminalObject obj : cbc.getCriminalObjects()) {
				CriminalObjectBean temp = new CriminalObjectBean();
				copyBean(obj, temp, null);
				criminalBasicCaseDetailBean.getCriminalObjectLst().add(temp);
			}
			for (SufferCaseRelation obj : cbc.getSufferCaseRelations()) {
				SufferCaseRelationBean temp = new SufferCaseRelationBean();
				copyBean(obj, temp, null);
				if(obj.getPerson() != null){
					copyBean(obj.getPerson().getPersonId(), temp.getCriminalPerson(), null);
					//性别
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xb", temp.getCriminalPerson().getSex(), null);
					if(di != null){
						temp.getCriminalPerson().setSex(di.getName());
					}
					//出生地代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getBirthCode(), null);
					if(di != null){
						temp.getCriminalPerson().setBirthCode(di.getName());
					}
					//户籍地代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getDoor(), null);
					if(di != null){
						temp.getCriminalPerson().setDoor(di.getName());
					}
					//口音
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getTone(), null);
					if(di != null){
						temp.getCriminalPerson().setTone(di.getName());
					}
					//现住址代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getAddress(), null);
					if(di != null){
						temp.getCriminalPerson().setAddress(di.getName());
					}
				}else{
					copyBean(temp.getCriminalPerson(), temp.getCriminalPerson(), null);
				}
				
				criminalBasicCaseDetailBean.getSufferCaseRelationLst()
						.add(temp);
			}
			for (CaseSupectRelation obj : cbc.getCaseSupectRelations()) {
				CaseSupectRelationBean temp = new CaseSupectRelationBean();
				copyBean(obj, temp, null);
				di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("ryzt", temp.getPersonState(), null);
				if(di != null){
					temp.setPersonState(di.getName());
				}
				if(obj.getPerson() != null){
					copyBean(obj.getPerson(), temp.getCriminalPerson(), null);
					//性别
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xb", temp.getCriminalPerson().getSex(), null);
					if(di != null){
						temp.getCriminalPerson().setSex(di.getName());
					}
					//出生地代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getBirthCode(), null);
					if(di != null){
						temp.getCriminalPerson().setBirthCode(di.getName());
					}
					//户籍地代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getDoor(), null);
					if(di != null){
						temp.getCriminalPerson().setDoor(di.getName());
					}
					//口音
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getTone(), null);
					if(di != null){
						temp.getCriminalPerson().setTone(di.getName());
					}
					//现住址代码
					di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode("xzqh", temp.getCriminalPerson().getAddress(), null);
					if(di != null){
						temp.getCriminalPerson().setAddress(di.getName());
					}
				}else{
					copyBean(temp.getCriminalPerson(), temp.getCriminalPerson(), null);
				}
				criminalBasicCaseDetailBean.getCaseSupectRelationLst()
						.add(temp);
			}
			for (CaseExecutionProcess obj : cbc.getCaseExecutionProcesses()) {
				CaseExecutionProcessBean temp = new CaseExecutionProcessBean();
				copyBean(obj, temp, null);
				criminalBasicCaseDetailBean.getCaseExecutionProcessLst().add(
						temp);
			}
			//查询所有文书
			Set<ArchivedFile> afLst = cbc.getArchivedFiles();
			if(afLst != null){
				for(ArchivedFile af : afLst){
					OptionItemBean oib = new OptionItemBean();
					oib.setId(af.getId());
					oib.setName(af.getName());
					writBeanLst.add(oib);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return SUCCESS;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public CriminalBasicCaseDetailBean getCriminalBasicCaseDetailBean() {
		return criminalBasicCaseDetailBean;
	}

	public void setCriminalBasicCaseDetailBean(
			CriminalBasicCaseDetailBean criminalBasicCaseDetailBean) {
		this.criminalBasicCaseDetailBean = criminalBasicCaseDetailBean;
	}

	public List<OptionItemBean> getWritBeanLst() {
		return writBeanLst;
	}

	public void setWritBeanLst(List<OptionItemBean> writBeanLst) {
		this.writBeanLst = writBeanLst;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public CaseSupectRelationBean getCaseSupectRelationBean() {
		return caseSupectRelationBean;
	}

	public void setCaseSupectRelationBean(
			CaseSupectRelationBean caseSupectRelationBean) {
		this.caseSupectRelationBean = caseSupectRelationBean;
	}

	public SufferCaseRelationBean getSufferCaseRelationBean() {
		return sufferCaseRelationBean;
	}

	public void setSufferCaseRelationBean(
			SufferCaseRelationBean sufferCaseRelationBean) {
		this.sufferCaseRelationBean = sufferCaseRelationBean;
	}

	public CriminalObjectBean getCriminalObjectBean() {
		return criminalObjectBean;
	}

	public void setCriminalObjectBean(CriminalObjectBean criminalObjectBean) {
		this.criminalObjectBean = criminalObjectBean;
	}

}
