package com.taiji.pubsec.szpt.ptjc.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.ptjc.action.bean.PenalConstantBean;
import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;
import com.taiji.pubsec.szpt.ptjc.service.PenalConstantService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.ReturnMessageAction;

import net.sf.json.JSONObject;

@Controller("penalConstantAction")
@Scope("prototype")
public class PenalConstantAction extends ReturnMessageAction {

	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	@Resource
	PenalConstantService penalConstantService;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;

	/**
	 * 查询所有PenalConstant
	 * @return
	 */
	public String findAllPenalConstant() {
	
		List<OrderCell> orderCellCodeList = szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_DW.getValue()) ;
		
		if(null != orderCellCodeList && orderCellCodeList.size() > 0){
			List<String> unitCodeList = new ArrayList<String>();
			for (OrderCell orderCell : orderCellCodeList) {
				if(orderCell.getName().indexOf("派出所") != -1){
					unitCodeList.add(orderCell.getCode());
				}
			}
			for (Constant.STATISTICS_TIME type : Constant.STATISTICS_TIME.values()) {
				List<PenalConstantBean> beanList = new ArrayList<PenalConstantBean>();
				List<PenalConstant> pcList = penalConstantService.findPenalConstantByUnitCodeAndType(unitCodeList,type.name());
				for (OrderCell oc : orderCellCodeList) {
					if(oc.getName().indexOf("派出所") != -1){
						beanList.add(penalConstantChangePCB(oc,pcList));
					}
				}
				resultMap.put(type.name(), beanList);
			}
		}
		return SUCCESS;
	}

	/**
	 * 循环删除对应时间类型的PenalConstant后保存对应时间类型的PenalConstant
	 * @return
	 */
	public String saveAllPenalConstant() {
		Map<String, List<JSONObject>> map = (Map<String, List<JSONObject>>)JSONObject.fromObject(read());
		//重新插入对应时间类型的数据
		for (Constant.STATISTICS_TIME type : Constant.STATISTICS_TIME.values()) {	
			List<JSONObject> pbList = map.get(type.name());
			if(null != pbList && pbList.size() >0){
				//创建集合存放所有派出所code,拼service所需的数据类型
				List<String> pcsCodes = new ArrayList<String>();
				for (int i = 0;i < pbList.size();i++ ) {
					PenalConstantBean penalConstantBean = (PenalConstantBean) JSONObject.toBean(pbList.get(i), PenalConstantBean.class);
					pcsCodes.add(penalConstantBean.getPcsCode());
				}
				//删除对应时间类型的所有PenalConstant
				if(pcsCodes.size()>0){
					penalConstantService.deletePenalConstantByUnitCode(pcsCodes, type.name());
				}
				//储存对应时间类型的所有PenalConstant
				for (int i = 0;i < pbList.size();i++ ) {
					PenalConstantBean penalConstantBean = (PenalConstantBean) JSONObject.toBean(pbList.get(i), PenalConstantBean.class);
					List<PenalConstant> pcList = PenalConstantChangepcList(penalConstantBean,type.name());
					//循环保存PenalConstant
					for(PenalConstant pc :pcList){
						penalConstantService.savePenalConstant(pc);
					}
				}
			}
		}		
		return SUCCESS;
	}

	/**
	 * 工具方法
	 * PenalConstant集合转PenalConstantBean
	 * @param su 派出所信息
	 * @param pcList PenalConstant集合
	 * @return PenalConstantBean
	 */
	private PenalConstantBean penalConstantChangePCB(OrderCell oc,List<PenalConstant> pcList){

		PenalConstantBean pb = new PenalConstantBean();
		pb.setPcsCode(oc.getCode());
		pb.setPcsName(oc.getName());
		if (null != pcList && pcList.size() > 0) {
			for (PenalConstant p : pcList) {
				if (oc.getCode().equals(p.getUnitCode())) {
					if (Constant.STATISTICS_COLOR.RED.name().equals(p
							.getColor())) {
						pb.setRed(p.getRange().substring(0,p.getRange().indexOf("<=")));
					}
					if (Constant.STATISTICS_COLOR.YELLOW.name().equals( p
							.getColor())) {
						pb.setYellow(p.getRange().substring(0,p.getRange().indexOf("<=")));
					}
					if (Constant.STATISTICS_COLOR.BLUE.name().equals( p
							.getColor())) {
						pb.setBlue(p.getRange().substring(0,p.getRange().indexOf("<=")));
					}
					if (Constant.STATISTICS_COLOR.ORANGE.name().equals(p
							.getColor())) {
						pb.setOrange(p.getRange().substring(0,p.getRange().indexOf("<=")));
					}
				}
			}
		}
		if(null == pb.getBlue()){
			pb.setBlue("");
		}
		if(null == pb.getYellow()){
			pb.setYellow("");
		}
		if(null == pb.getOrange()){
			pb.setOrange("");
		}
		if(null == pb.getRed()){
			pb.setRed("");
		}
		return pb;
	}
	
	/**
	 * 工具方法
	 * PenalConstantBean转PenalConstant集合 
	 * @param pcb PenalConstantBean对象
	 * @param type 时间类型
	 * @return List<PenalConstant>
	 */
	private List<PenalConstant> PenalConstantChangepcList(PenalConstantBean pcb,String type){
		
		if(null != pcb ){
			List<PenalConstant> pcList = new ArrayList<PenalConstant>();
			if(null != pcb.getBlue() && !pcb.getBlue().equals("") && null != pcb.getYellow() && !pcb.getYellow().equals("")){
				PenalConstant pc = new PenalConstant();
				pc.setUnitCode(pcb.getPcsCode());
				pc.setUnitName(pcb.getPcsName());
				pc.setColor(Constant.STATISTICS_COLOR.BLUE.name());
				pc.setType(type);
				pc.setRange(pcb.getBlue()+"<=x<"+pcb.getYellow());
				pcList.add(pc);
			}
			if(null != pcb.getYellow() && !pcb.getYellow().equals("")  && null != pcb.getOrange() && !pcb.getOrange().equals("")){
				PenalConstant pc = new PenalConstant();
				pc.setUnitCode(pcb.getPcsCode());
				pc.setUnitName(pcb.getPcsName());
				pc.setColor(Constant.STATISTICS_COLOR.YELLOW.name());
				pc.setType(type);
				pc.setRange(pcb.getYellow()+"<=x<"+pcb.getOrange());
				pcList.add(pc);
			}
			if(null != pcb.getOrange() && !pcb.getOrange().equals("") && null != pcb.getRed() && !pcb.getRed().equals("")){
				PenalConstant pc = new PenalConstant();
				pc.setUnitCode(pcb.getPcsCode());
				pc.setUnitName(pcb.getPcsName());
				pc.setColor(Constant.STATISTICS_COLOR.ORANGE.name());
				pc.setType(type);
				pc.setRange(pcb.getOrange()+"<=x<"+pcb.getRed());
				pcList.add(pc);
			}
			if( null != pcb.getRed() && !pcb.getRed().equals("")){
				PenalConstant pc = new PenalConstant();
				pc.setUnitCode(pcb.getPcsCode());
				pc.setUnitName(pcb.getPcsName());
				pc.setColor(Constant.STATISTICS_COLOR.RED.name());
				pc.setType(type);
				pc.setRange(pcb.getRed()+"<=x");
				pcList.add(pc);
			}
			return pcList;
		}
		
		return null;
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
	
}
