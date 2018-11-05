package com.taiji.pubsec.szpt.placemonitor.action.util;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;

/**
 * model和bean互转类
 * @author WL-PC
 *
 */
@Component
public class ModelBeanTransformUtilInPlaceMonitor{
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	/**
	 * wifi围栏命中信息model转bean
	 * 
	 * @param model wifi围栏命中信息model
	 * @return bean wifi围栏命中信息bean
	 */
	public WifiPlaceInAndOutInfoBean wifiPlaceInAndOutInfoToWifiPlaceInAndOutInfoBean(WifiPlaceInAndOutInfo model) {
		if(model == null){
			return null;
		}
		WifiPlaceInAndOutInfoBean bean = new WifiPlaceInAndOutInfoBean();
		BeanUtils.copyProperties(model, bean);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bean.setLeaveTime(sdf.format(model.getLeaveTime()));
		bean.setEnterTime(sdf.format(model.getEnterTime()));
		return bean;
	}
	/**
	 * 通过字典项id查字典项名称
	 * 
	 * @param dictionaryItemId 字典项id
	 * @return 字典项名称
	 */
	public String findDictionaryItemNameById(String dictionaryItemId) {
		if (dictionaryItemId != null && !dictionaryItemId.isEmpty()) {
			DictionaryItem item = dictionaryItemService.findById(dictionaryItemId);
			if (item != null) {
				return item.getName();
			}
		}
		return "";
	}
	
}
