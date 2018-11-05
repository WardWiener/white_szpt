package com.taiji.pubsec.szpt.placemonitor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.action.bean.PlaceBasicInfoBean;
import com.taiji.pubsec.szpt.placemonitor.action.bean.PlaceSumInfoBean;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * wifi围栏命中信息Action
 * 
 * @author WL-PC
 *
 */
@Controller("wifiDeployControlAction")
@Scope("prototype")
public class WifiDeployControlAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	private String queryStr;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private String id;

	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	@Resource
	private IUnitService unitService;
	
	public String querySurveillanceByCondition(){
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		Integer pageNo=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("pageSize")));
		data.put("pageNo",pageNo);
		Pager<PlaceBasicInfo> pager = placeBasicInfoService.queryPlaceBasicInfoByCondition(data);
		resultMap.put("result", placeBasicInfoPagerTurnPBInfoBean(pager));
		return SUCCESS;
	}
	
	private Pager<PlaceBasicInfoBean> placeBasicInfoPagerTurnPBInfoBean(Pager<PlaceBasicInfo> pager){
		Pager<PlaceBasicInfoBean> pbInfopager = new Pager<PlaceBasicInfoBean>();
		List<PlaceBasicInfoBean> pbLst = new ArrayList<PlaceBasicInfoBean>();
		
		PlaceBasicInfoBean pbBean = null;
		Map<String,Object> map = new HashMap();
		map.put("type", "2");
		String hql = "select u from Unit as u where  u.type = :type";
		List<Unit> unitLst = unitService.findAllByParams(hql,map);
		for (PlaceBasicInfo pb : pager.getPageList()) {
			pbBean = new PlaceBasicInfoBean();
			pbBean.setId(pb.getId());
			pbBean.setInternetServicePlaceCode(pb.getInternetServicePlaceCode());
			pbBean.setInternetServicePlaceName(pb.getInternetServicePlaceName());
			pbBean.setDetailedAddress(pb.getDetailedAddress());
			pbBean.setIsLive(pb.getIsLive() == null ? "0":pb.getIsLive().toString());
			if( null != pb.getAreaDepartmentId()){
				for(Unit u : unitLst){
					if(u.getId().equals(pb.getAreaDepartmentId())){
						pbBean.setAreaDepartmentId(u.getShortName());
					}
				}
			}
			pbLst.add(pbBean);
		}
		pbInfopager.setPageList(pbLst);
		pbInfopager.setTotalNum(pager.getTotalNum());
		pbInfopager.setTotalNumber(pager.getTotalNumber());
		return pbInfopager;
	}
	
	public String findSurveillanceById(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String id = data.get("id").toString();
		Map<String,Object> map = new HashMap();
		map.put("type", "2");
		String hql = "select u from Unit as u where  u.type = :type";
		List<Unit> unitLst = unitService.findAllByParams(hql,map);
		PlaceBasicInfo placeBasicInfo = placeBasicInfoService.findById(id);
	
		resultMap.put("result", placeBasicInfo);
		resultMap.put("pcsLst", unitLst);
		
		return SUCCESS;
	}
	
	public String updateOrderCell(){
		Map<String, Object> data = JSONObject.fromObject(read());
		PlaceBasicInfo placeBasicInfo = placeBasicInfoService.findById(data.get("id").toString());
		placeBasicInfo.setAreaDepartmentId(data.get("pcsId").toString());
		placeBasicInfo.setIsLive(Integer.valueOf(data.get("isLive").toString()));
		placeBasicInfo.setLongitude(data.get("newLongitude").toString());
		placeBasicInfo.setLatitude(data.get("newLatitude").toString());
		placeBasicInfoService.updatePlaceBasicInfo(placeBasicInfo);
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
	
}
