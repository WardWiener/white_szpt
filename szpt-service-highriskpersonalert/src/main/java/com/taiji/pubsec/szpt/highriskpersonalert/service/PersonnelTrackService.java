package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;

/**
 * 人员轨迹接口
 * @author wangfx
 *
 */
public interface PersonnelTrackService {
	
	/**
	 * 获取人员的轨迹 。获取火车、飞机、网吧、旅馆酒店的轨迹，并按照appearTime倒序 
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @param idcode 身份证号
	 * @return 人员轨迹信息列表
	 */
	List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode);

}
