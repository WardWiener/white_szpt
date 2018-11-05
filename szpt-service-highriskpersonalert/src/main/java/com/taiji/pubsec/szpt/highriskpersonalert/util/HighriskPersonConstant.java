package com.taiji.pubsec.szpt.highriskpersonalert.util;


/**
 * 高危人员常量了类
 * @author wangfx
 *
 */
public class HighriskPersonConstant {
	
	/**
	 * 人员轨迹类型：WIFI
	 */
	public static final String PERSON_TRACK_TYPE_WIFI = "WIFI";
	/**
	 * 人员轨迹类型：火车
	 */
	public static final String PERSON_TRACK_TYPE_TRAIN = "火车";
	
	/**
	 * 人员轨迹类型：飞机
	 */
	public static final String PERSON_TRACK_TYPE_AIRPLANE = "飞机";
	
	/**
	 * 人员轨迹类型：旅馆酒店
	 */
	public static final String PERSON_TRACK_TYPE_HOTEL = "旅馆酒店";
	
	/**
	 * 人员轨迹类型：网吧
	 */
	public static final String PERSON_TRACK_TYPE_INTERNETBAR = "网吧";
	
	/**
	 * 人员轨迹类型具体描述:火车到站
	 */
	public static final String PERSON_TRACK_DESCRIPTION_TRAIN_ENTER = "火车到站";
	
	/**
	 * 人员轨迹类型具体描述:火车出站
	 */
	public static final String PERSON_TRACK_DESCRIPTION_TRAIN_OUT = "火车出站";
	
	/**
	 * 人员轨迹类型具体描述:旅馆酒店
	 */
	public static final String PERSON_TRACK_DESCRIPTION_HOTEL = "旅馆酒店";
	
	/**
	 * 人员轨迹类型具体描述:网吧
	 */
	public static final String PERSON_TRACK_DESCRIPTION_INTERNETBAR = "网吧";
	
	/**
	 * 人员轨迹类型具体描述:飞机入港
	 */
	public static final String PERSON_TRACK_DESCRIPTION_AIRPLANE_ENTER = "飞机入港";
	
	/**
	 * 人员轨迹类型具体描述:飞机出港
	 */
	public static final String PERSON_TRACK_DESCRIPTION_AIRPLANE_OUT = "飞机出港";
	
	/**
	 * 人员轨迹类型具体描述:wifi
	 */
	public static final String PERSON_TRACK_DESCRIPTION_WIFI = "WIFI";
	
	/**
	 * 高危人员类型字典类型编码
	 */
	public static final String HIGHRISK_PERSON_TYPE_DIC_TYPE_CODE = "rylx";
	
	/**
	 *高危人员预警类型字段类型编码
	 */
	public static final String HIGHRISK_PERSON_WARNTYPE_CODE = "yjlx";
	
	/**
	 * 高危人员前科类型字典类型编码
	 */
	public static final String HIGHRISK_PERSON_CRIMINALRECORD_DIC_TYPE_CODE = "qklx";
	
	/**
	 * 高危人员性别字典类型编码
	 */
	public static final String HIGHRISK_PERSON_SEX_DIC_TYPE_CODE = "xb";
	
	/**
	 * 高危人员日常状态字典类型编码
	 */
	public static final String HIGHRISK_PERSON_STATUS_DIC_TYPE_CODE = "zt";
	
	/**
	 * 高危人员尿检结果字典类型编码
	 */
	public static final String HIGHRISK_PERSON_URINETEST_DIC_TYPE_CODE = "njjg";
	
	/**
	 * 高危人员处置情况字典类型编码
	 */
	public static final String HIGHRISK_PERSON_HANDLERESULT_DIC_TYPE_CODE = "czqk";
	
	/**
	 * 高危人员调整
	 */
	public static final String HIGHRISK_PERSON_ADJUSTMENT = "高危人员调整";
	
	/**
	 * 高危人员调整：新增
	 */
	public static final String HIGHRISK_PERSON_ADJUSTMENT_ADD = "新增";
	
	/**
	 * 高危人员调整：删除
	 */
	public static final String HIGHRISK_PERSON_ADJUSTMENT_DELETE = "删除";
	
	/**
	 * 人员类型调整
	 */
	public static final String PERSON_TYPE_ADJUSTMENT = "人员类型调整";
	
	/**
	 * 人员类型调整：新增
	 */
	public static final String PERSON_TYPE_ADJUSTMENT_ADD = "新增";
	
	/**
	 * 人员类型调整：删除
	 */
	public static final String PERSON_TYPE_ADJUSTMENT_DELETE = "删除";
	
	/**
	 * 当前城市
	 */
	public static String HIGHRISK_PERSON_TRACK_LOCALCITY = "贵阳"; 
	
	public HighriskPersonConstant(String str){
		HIGHRISK_PERSON_TRACK_LOCALCITY = str;
	}

}
