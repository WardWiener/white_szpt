package com.taiji.pubsec.szpt.instruction.action.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.util.Constant.XSFXTS_CONSTANT;

/**
 * 指令公用工具方法
 * 
 * @author WangLei
 *
 */
@Component
public class InsturctionCommonUtil {

	@Resource
	private IUnitService unitService;// 单位接口

	/**
	 * 根据指令接收主体类型和id查询接收主体名称
	 * 
	 * @return
	 */
	public Map<String, String> queryInstructionReceiveBodyNameByBodyTypeAndId(String type, String id){
		Map<String, String> map = null;
		if(XSFXTS_CONSTANT.UNIT_CLASS_NAME.getValue().equals(type)){
			Unit unit = unitService.findById(id);
			if(unit != null){
				map = new HashMap<String, String>();
				map.put("code", unit.getCode());
				map.put("name", unit.getShortName());
				map.put("id", unit.getId());
			}
		}
		return map;
	}
	
}
