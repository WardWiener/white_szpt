package com.taiji.pubsec.szpt.dagl.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.dagl.action.bean.SnapshotBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.LoginInfoAction;

import net.sf.json.JSONObject;

@Controller("yrydSnapshot")
@Scope("prototype")
public class YrydSnapshotAction extends LoginInfoAction {
	
	private static final long serialVersionUID = 1L;
	
	private String idCode;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private InfoSnapshotService infoSnapshotService;
	
	public String findTrackSnapshotLst(){
		Map<String, Object> parameters = JSONObject.fromObject(read());
		String idcard = String.valueOf(parameters.get("idCode"));
		JSONObject obj = new JSONObject();
		List<InfoSnapshot> infoSnapshotLst = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(idcard, HighriskPerson.class.getName(), INFO_SNAPSHOT_MODULE.GWR_GJFX.getValue());
		List<SnapshotBean> snapshotBeanLst = new ArrayList<SnapshotBean>();
		if(null != infoSnapshotLst && infoSnapshotLst.size() > 0){
			for(InfoSnapshot infoSnapshot : infoSnapshotLst){
				SnapshotBean snapshotBean = InfoSnapshotTrunSnapshotBean(infoSnapshot);
				snapshotBeanLst.add(snapshotBean);
			}
		}
		resultMap.put("result", snapshotBeanLst);
		return SUCCESS;
	}
	
	//布控快照
	public void findControlkSnapshotInfo (){
		JSONObject obj = new JSONObject();
		InfoSnapshot infoSnapshot = infoSnapshotService.findInfoSnapshotListOneByTypeAndTargetId(idCode, PersonExecuteControl.class.getName());
		JSONObject data = JSONObject.fromObject(infoSnapshot.getSnapshot());
		obj.put("result", data);
		this.outJSON(obj.toString()) ;
	}
	
	//实时wifi轨迹快照
	public String findWifiLocusSnapshotInfo (){
		List<InfoSnapshot> infoSnapshots = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(idCode, HighriskPerson.class.getName(), INFO_SNAPSHOT_MODULE.CSJKFX_SSWIFI.getValue());
		List<SnapshotBean> snapshotBeanLst = new ArrayList<SnapshotBean>();
		if(null != infoSnapshots && infoSnapshots.size() > 0){
			for(InfoSnapshot infoSnapshot : infoSnapshots){
				SnapshotBean snapshotBean = InfoSnapshotTrunSnapshotBean(infoSnapshot);
				snapshotBeanLst.add(snapshotBean);
			}
		}
		resultMap.put("result", snapshotBeanLst);
		return SUCCESS;
	}
	
	//wifi轨迹快照集合
	public String findWifiLocusSnapshotLst (){
		Map<String, Object> parameters = JSONObject.fromObject(read());
		String idcard = String.valueOf(parameters.get("idCode"));
		JSONObject obj = new JSONObject();
		List<InfoSnapshot> infoSnapshotLst = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(idcard, HighriskPerson.class.getName(), INFO_SNAPSHOT_MODULE.CSJKFX_WIFIGJ.getValue());
		List<SnapshotBean> snapshotBeanLst = new ArrayList<SnapshotBean>();
		if(null != infoSnapshotLst && infoSnapshotLst.size() > 0){
			for(InfoSnapshot infoSnapshot : infoSnapshotLst){
				SnapshotBean snapshotBean = InfoSnapshotTrunSnapshotBean(infoSnapshot);
				snapshotBeanLst.add(snapshotBean);
			}
		}
		resultMap.put("result", snapshotBeanLst);
		return SUCCESS;
	}
	
	private SnapshotBean InfoSnapshotTrunSnapshotBean(InfoSnapshot infoSnapshot){
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SnapshotBean snapshotBean = new SnapshotBean();
		snapshotBean.setCode(null == infoSnapshot.getCode() ? "" : infoSnapshot.getCode());
		snapshotBean.setId(null == infoSnapshot.getId() ? "" : infoSnapshot.getId());
		snapshotBean.setIntro(null == infoSnapshot.getIntro() ? "" : infoSnapshot.getIntro());
		snapshotBean.setTargetId(null == infoSnapshot.getTargetId() ? "" : infoSnapshot.getTargetId());
		snapshotBean.setType(null == infoSnapshot.getType() ? "" : infoSnapshot.getType());
		snapshotBean.setCreatePerson(null == infoSnapshot.getCreatePerson() ? "" : infoSnapshot.getCreatePerson());
		snapshotBean.setCreatedDate(null == infoSnapshot.getCreatedDate() ? "" : dt.format(infoSnapshot.getCreatedDate()));
		snapshotBean.setSnapshot(null == infoSnapshot.getSnapshot() ? "" : infoSnapshot.getSnapshot());
		return snapshotBean;
	}
	
	public void findSnapshotById(){
		Map<String, Object> parameters = JSONObject.fromObject(read());
		String id = String.valueOf(parameters.get("id"));
		JSONObject obj = new JSONObject();
		InfoSnapshot infoSnapshot = infoSnapshotService.findInfoSnapshotById(id);
		JSONObject data = JSONObject.fromObject(infoSnapshot.getSnapshot());
		obj.put("result", data);
		obj.put("intro", infoSnapshot.getIntro());
		this.outJSON(obj.toString()) ;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
