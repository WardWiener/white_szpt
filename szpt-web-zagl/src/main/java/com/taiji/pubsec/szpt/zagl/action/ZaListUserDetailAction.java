package com.taiji.pubsec.szpt.zagl.action;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.action.bean.FileBean;
import com.taiji.pubsec.szpt.zagl.action.bean.ZaxqTableTdBean;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialTypeService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;
import com.taiji.pubsec.weboffice.iweboffice2000.service.IWebOffice2000Service;

@Controller("zaListUserDetailAction")
@Scope("prototype")
public class ZaListUserDetailAction extends PageCommonAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ZaListUserDetailAction.class);

	private String queryStr;
	
	private String fileId;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private SpecialCaseMaterialService specialCaseMaterialService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private SpecialCaseService specialCaseService;
	
	@Resource
	private IWebOffice2000Service webOffice2000Service;
	
	//attachmentCustomizedService
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	
	@Resource
	private SpecialCaseMaterialTypeService  specialCaseMaterialTypeService;
	/**
	 * 查询所有资料类型
	 * @return
	 */
	public String findAllZazllx(){
		List<DictionaryItem> list = this.dictionaryItemService.findAllSubDictionaryItemsByTypeCode(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
		DictionaryItem dictionaryItem = null;
		int size = list.size();
		for(int i = 0 ;i < size-1 ;i++){
	 		for(int j = size-1;j > i ;j--){
	 			if(Integer.parseInt(list.get(i).getCode()) > Integer.parseInt(list.get(j).getCode())){
	 				dictionaryItem = list.get(i);
	 				list.set(i,list.get(j));
	 				list.set(j,dictionaryItem);
	 			};
	 		}
	 	}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
		resultMap.put("result", list);
		return SUCCESS;
	}
	/**
	 * 查询所有正在使用的专案类型
	 * @return
	 */
	public String findUseZazllx(){
		
		List<DictionaryItem> list = specialCaseMaterialTypeService.findTypesByState(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
		
		DictionaryItem dictionaryItem = null;
		int size = list.size();
		for(int i = 0 ;i < size-1 ;i++){
	 		for(int j = size-1;j > i ;j--){
	 			if(Integer.parseInt(list.get(i).getCode()) > Integer.parseInt(list.get(j).getCode())){
	 				dictionaryItem = list.get(i);
	 				list.set(i,list.get(j));
	 				list.set(j,dictionaryItem);
	 			};
	 		}
	 	}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
		resultMap.put("result", list);
		return SUCCESS;
	}
	
	/**
	 * 查询专案相关资料
	 * @return
	 */
	public String searchZazl(){
		
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		
		Integer start=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("length")));
		data.put("start",start);
		
		String caseId = null;
		if(ParamMapUtil.isNotBlank(data.get("caseId"))){
			caseId = data.get("caseId").toString();
		}
		Pager<SpecialCaseMaterial> pager = specialCaseMaterialService.findMaterialByCase(caseId, data,start,Integer.valueOf(data.get("length").toString()));
		List<SpecialCaseMaterial> scmLst = pager.getPageList();
		List<ZaxqTableTdBean> tableInfoLst = new ArrayList<ZaxqTableTdBean>();
		for (SpecialCaseMaterial scm : scmLst) {
			ZaxqTableTdBean ztb = new ZaxqTableTdBean();
			ztb.setFileName(scm.getCreatedName());
			List<DictionaryItem> list = this.dictionaryItemService.findAllSubDictionaryItemsByTypeCode(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
			for (DictionaryItem dictionaryItem : list) {
				if(dictionaryItem.getCode().equals(scm.getType())){
					ztb.setFileType(dictionaryItem.getName());
				}
			}			
			ztb.setFileUploadPerson(scm.getCreatePerson().getName());
			List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(scm.getId(), SpecialCaseMaterial.class.getName());
			for (Attachment att : attLst) {				
				ztb.setFileSize((att.getSize()/1024) + ""); //附件大小
			}		
			ztb.setId(scm.getId());
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
			ztb.setUploadTime(df.format(scm.getCreatedTime()));
			tableInfoLst.add(ztb);
		}
		Pager<ZaxqTableTdBean> tablePager = new Pager<ZaxqTableTdBean>();
		tablePager.setPageList(tableInfoLst);
		tablePager.setTotalNum(pager.getTotalNum());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+caseId+"',type='"+String.valueOf(data.get("zazllxCode"))+"'");
		resultMap.put("result", tablePager);
		return SUCCESS;
	}
		
	/**
	 * 覆盖专案资料
	 * @return
	 */
	public String coverZllx(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String fileType = null;
		if(ParamMapUtil.isNotBlank(rqst.get("zllx"))){
			fileType = rqst.get("zllx").toString();
		}
		String caseId = null;
		if(ParamMapUtil.isNotBlank(rqst.get("zaId"))){
			caseId = rqst.get("zaId").toString();
		}
		List<String> fileNames = JSONArray.fromObject(rqst.get("fileNames")) ;
		boolean result = false; 
		String fileNamesStr = "";
		for (String fileName : fileNames) {
			fileNamesStr +="'"+fileNamesStr+"',";
			SpecialCaseMaterial specialCase = specialCaseMaterialService.findMaterialByConditions(fileName, fileType, caseId);
			if(null != specialCase){
				List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(specialCase.getId(), SpecialCaseMaterial.class.getName());
				for (Attachment att : attLst) {
					attachmentCustomizedService.delete(att);
				}
				result = specialCaseMaterialService.deleteMaterial(specialCase.getId());
			}
		}
		if(result){
			resultMap.put("result", "true");
		}else{
			resultMap.put("result", "false");
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+caseId+"',createdName in ("+fileNamesStr.substring(0, fileNamesStr.length()-1)+"),type='"+fileType+"");
		return SUCCESS;
	}
	
	
	public String attachmentId() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String fileId = null;
		if(ParamMapUtil.isNotBlank(rqst.get("fileId"))){
			fileId = rqst.get("fileId").toString();
			List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(fileId, SpecialCaseMaterial.class.getName());
			for (Attachment att : attLst) {
				resultMap.put("result", att.getId());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 图片根据资料取出附件
	 */
	public String findImgAttachmentById(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String fileId = null;
		if(ParamMapUtil.isNotBlank(rqst.get("fileId"))){
			fileId = rqst.get("fileId").toString();
			List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(fileId, SpecialCaseMaterial.class.getName());
			for (Attachment att : attLst) {
				
				String base64Str = null;
				try {
					base64Str = Base64CryptFmtUtil.encode(FileFmtUtils.readByByteBuffer(att.getAttachmentMeta().getAttachmentCopies().get(0).getInputStream()), false);
				} catch (UnsupportedEncodingException e) {
					LOGGER.debug("出库单查询照片附件转Base64异常",e);
				}
				resultMap.put("result", base64Str);
			}
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"targetId='"+fileId+"',type='"+SpecialCaseMaterial.class.getName()+"'");
		return SUCCESS;
	}
	
	/**
	 * 基本资料类型根据Id资料取出附件
	 */
	public String findAttachmentById() {
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String fileId = null;
		FileBean fileObjBean = new FileBean();
		String wordName = "";
		if(ParamMapUtil.isNotBlank(rqst.get("fileId"))){
			fileId = rqst.get("fileId").toString();
			List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(fileId, SpecialCaseMaterial.class.getName());
			if (attLst != null) {
				Attachment am = attLst.get(0);
				fileObjBean = new FileBean();
				fileObjBean.setId(am.getId());
				fileObjBean.setName(am.getName());
				fileObjBean.setSize(am.getSize().toString());
				InputStream fromDoc = am.getAttachmentMeta()
						.getAttachmentCopies().get(0).getInputStream();
				String docType = am.getName().substring(
						am.getName().lastIndexOf("."), am.getName().length());
				try {
					wordName = webOffice2000Service.copyDoc(fromDoc, docType);
				} catch (Exception e) {
					wordName = "";
					LOGGER.debug("产生异常：", e);
				}
			}
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("fileName", wordName);
		result.put("file", fileObjBean);
		resultMap.put("result",result);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"targetId='"+fileId+"',type='"+SpecialCaseMaterial.class.getName()+"'");
		return SUCCESS;
	}

	
	/**
	 * 检测上传的文件是否已上传
	 * @return
	 */
	public String findZazlByFileNameAndZaIdAndZllx(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String fileType = null;
		if(ParamMapUtil.isNotBlank(rqst.get("zllx"))){
			fileType = rqst.get("zllx").toString();
		}
		String caseId = null;
		if(ParamMapUtil.isNotBlank(rqst.get("zaId"))){
			caseId = rqst.get("zaId").toString();
		}
		List<String> fileNames = JSONArray.fromObject(rqst.get("fileNames")) ;
		String result = "";
		String fileNamesStr = "";
		for (String fileName : fileNames) {
			fileNamesStr +="'"+fileNamesStr+"',";
			SpecialCaseMaterial specialCase = specialCaseMaterialService.findMaterialByConditions(fileName, fileType, caseId);
			if(specialCase != null){
				result += "\""+fileName+"\"重复,覆盖还是取消 <br>";
			}
		}
		if( !"".equals(result)){
			resultMap.put("result", result);
			return SUCCESS;
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+caseId+"',createdName in ("+fileNamesStr.substring(0, fileNamesStr.length()-1)+"),type='"+fileType+"");

		resultMap.put("result", null);
		return SUCCESS;
	}
	
	/**
	 * 保存资料附件
	 * 
	 */
	public String saveStorageOut(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String zllx = rqst.get("zllx").toString();
		String zaId = rqst.get("zaId").toString();
		List<JSONObject> fileBeanLst = (List<JSONObject>) rqst.get("fileBeanLst");
		boolean result = false;
		if( null != fileBeanLst){
			for (int i = 0 ;i<fileBeanLst.size();i++ ) {
				FileBean file = (FileBean) JSONObject.toBean(fileBeanLst.get(i), FileBean.class);
				SpecialCaseMaterial scm = new SpecialCaseMaterial();
				scm.setCreatePerson(this.findCurrentPerson());
				SpecialCase za = specialCaseService.findSpecialCaseById(zaId);
				//SpecialCase za = specialCaseService.findSpecialCaseById(zaId);
				scm.setSpecialCase(za);
				scm.setType(zllx);
				scm.setCreatedTime(new Date());
				scm.setCreatedName(file.getName());
				result = specialCaseMaterialService.addMaterial(scm);
				Attachment att = attachmentCustomizedService.findById(file.getId());
				att.setTargetId(scm.getId());//id是业务对象的id
				att.setType(SpecialCaseMaterial.class.getName());//业务对象类型
				attachmentCustomizedService.save(att);
			}
		}
		if(result){
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
			resultMap.put("result", "true");
		}else{
			resultMap.put("result", "false");
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案",null);
		}
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	protected Person findCurrentPerson(){
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		Map<String, String> mPerson = new HashMap<String, String>(0) ;
		if(userMap.get("person")!=null){
			mPerson = (Map<String, String>)userMap.get("person");
		}
		return personService.findById(mPerson.get("id"));
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
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
