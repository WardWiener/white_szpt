package com.taiji.pubsec.szpt.dagl.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.dagl.action.bean.AlarmInfoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.FileBean;
import com.taiji.pubsec.szpt.dagl.model.InterestedPerson;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.dagl.service.PersonSearchService;
import com.taiji.pubsec.szpt.dagl.service.YrydService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPersonAvatar;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.LoginInfoAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

import net.sf.json.JSONObject;

@Controller("test")
@Scope("prototype")
public class TestAction extends LoginInfoAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String queryStr;
	
	private String idCode;
	private String id;
	
	private String name;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private YrydService yrydService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;	
	
	public String insertImg(){
		return SUCCESS;
	}
	
	public String findImgAttachmentById(){
		List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType("130412199001233418", "image");
		List<String> imgLst = new ArrayList<>();
		for (Attachment att : attLst) {
			String base64Str = null;
			try {
				base64Str = Base64CryptFmtUtil.encode(FileFmtUtils.readByByteBuffer(
						att.getAttachmentMeta().getAttachmentCopies().get(0).getInputStream()), false);
			} catch (UnsupportedEncodingException e) {
				System.out.println("图片转字符串错误");
			}
			imgLst.add(base64Str);
		}
		resultMap.put("result", imgLst);
		return SUCCESS;
	}
	
	//保存图片附件
		public String saveImg(){
			Map<String, Object> rqst = JSONObject.fromObject(read());
			List<JSONObject> fileBeanLst = (List<JSONObject>) rqst.get("fileBeanLst");
			String idcode =  rqst.get("idcode").toString();
			for (int i = 0; i < fileBeanLst.size(); i++) {
				FileBean file = (FileBean) JSONObject.toBean(fileBeanLst.get(i), FileBean.class);
				Attachment att = attachmentCustomizedService.findById(file.getId());
				att.setTargetId(idcode);// id是业务对象的id
				att.setType(HighriskPersonAvatar.class.getName());// 业务对象类型
				attachmentCustomizedService.save(att);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
