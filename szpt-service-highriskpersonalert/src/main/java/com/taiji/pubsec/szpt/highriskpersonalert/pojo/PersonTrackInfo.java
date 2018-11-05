package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

/**
 * 人员轨迹信息
 * @author wangfx
 *
 */
public interface PersonTrackInfo {
	
	/**
	 * 轨迹描述
	 * @return
	 */
	String trackDescription();
	
	/**
	 * 轨迹类型：如旅馆酒店、火车、网吧、飞机。
	 * @return
	 */
	String trackType();
	
	/**
	 * 轨迹类型：如旅馆旅馆酒店、火车、网吧、飞机。
	 * <br>具体描述酒店、火车到站、火车出站、网吧、飞机出港、飞机入港。
	 * <br>具体描述根据配置文件中的当前位置判断出站入站之类
	 * @return
	 */
	String trackTypeDescription();
	
	String getId();
	
	/**
	 * 返回具体类型的model对象
	 * @return
	 */
	Object toObject();
	
	/**
	 * 如果是火车或者飞机，此处返回的是去贵阳的始发地或者离开贵阳的目的地
	 * @return
	 */
	String getAddress();
	
	/**
	 * 出现时间。
	 * 如果是火车或者飞机，此处返回的是到贵阳的或者离开贵阳的时间。
	 * 网吧和酒店返回的值入网时间和入驻时间
	 * @return
	 */
	Date getAppearTime();
	

}
