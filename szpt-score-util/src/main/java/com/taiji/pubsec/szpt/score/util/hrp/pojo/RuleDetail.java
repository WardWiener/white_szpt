package com.taiji.pubsec.szpt.score.util.hrp.pojo;

import java.io.Serializable;

public class RuleDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4810297981041415532L;
	
	public RuleDetail(String key, String value){
		this.key = key ;
		this.value = value ;
	}

	/**
	 * 以下三个常量是用来确定有哪些ScorePoint，值需要跟key的注释里的key的“-”左边的内容保持一致
	 * 并且SCORE_POINT_PERSON_TYPE、SCORE_POINT_TRIP_RECORD、SCORE_POINT_PLACE_RECORD还用来判断用什么特殊的Executor
	 */
	public static final String SCORE_POINT_PERSON_TYPE = "人员类别" ;
	public static final String SCORE_POINT_TRIP_RECORD = "近一月出行次数" ;
	public static final String SCORE_POINT_PLACE_RECORD = "场所属性" ;
	public static final String SCORE_POINT_INPORTANT_CONTROL_TYPE = "在控类型" ;
	public static final String SCORE_POINT_EMPLOYMENT_STATUS = "就业情况" ;
	public static final String SCORE_POINT_MARRIAGE_STATUS = "婚姻情况" ;
	public static final String SCORE_POINT_INCOME = "经济收入（月）" ;
	
	/**
	 * 以下是executor里获得业务数据以后赋予的标识，需要跟key里的值尽量保持一致
	 */
	public static final String SCORE_DETAIL_TYPE_ENTERTAINMENT_PLACE_TIMES = "场所属性-娱乐场所权重（近一年）" ;
	public static final String SCORE_DETAIL_TYPE_INTERNET_BAR_TIME = "场所属性-网吧权重（近一年）" ;
	public static final String SCORE_DETAIL_TYPE_HOTEL_TIME = "场所属性-酒店宾馆权重" ;
	
	/**
	 * 各项ScorePoint的评分项权重的key的"-"的右边的值：
	 * xxxx-评分项权重
	 */
	public static final String SCORE_POINT_WEIGHT = "评分项权重" ;
	/**
	 * 所有的key的"-"分隔符两边的内容必和各个字典项类型和字典项的名称严格一致（评分项权重和权重封顶等字典项里没有的内容除外）
	 * 所有的key
	 * 在控类型：
	 * 	在控类型-评分项权重
	 * 	在控类型-权重封顶
	 * 	在控类型-高危
	 * 	在控类型-关注
	 * 	在控类型-一般
	 * 	在控类型-无
	 * 人员类别：	
	 * 	人员类别-评分项权重
	 * 	人员类别-权重封顶
	 * 	人员类别-在逃人员
	 * 	人员类别-涉稳人员
	 * 	人员类别-涉恐人员
	 * 	人员类别-肇事肇祸精神病人
	 * 	人员类别-重点上访人员
	 * 	人员类别-违法犯罪青少年
	 * 	人员类别-艾滋病人
	 * 	人员类别-涉毒人员@吸毒人员
	 * 	人员类别-涉毒人员@制贩毒人员
	 * 	人员类别-刑事前科@危害国家安全案
	 * 	人员类别-刑事前科@危害公共安全案
	 * 	人员类别-刑事前科@破坏社会主义市场经济秩序案
	 * 	人员类别-刑事前科@侵犯公民人身权利、民主权利案
	 * 	人员类别-刑事前科@侵犯财产案
	 * 	人员类别-刑事前科@妨害社会管理案
	 * 	人员类别-刑事前科@危害国防利益案
	 * 	人员类别-刑事前科@渎职案
	 * 就业情况:
	 * 	就业情况-评分项权重
	 * 	就业情况-权重封顶
	 * 	就业情况-无业
	 *  就业情况-待业	
	 * 	就业情况-失业
	 * 	就业情况-就业
	 * 婚姻情况:
	 * 	婚姻情况-评分项权重
	 * 	婚姻情况-权重封顶
	 * 	婚姻情况-已婚
	 * 	婚姻情况-再婚
	 * 	婚姻情况-丧偶
	 * 	婚姻情况-未婚
	 * 	婚姻情况-离婚
	 * 	婚姻情况-未知
	 * 经济收入（月）:
	 * 	经济收入（月）-评分项权重
	 * 	经济收入（月）-权重封顶
	 * 	经济收入（月）-少于1000元
	 * 	经济收入（月）-1000~2000
	 * 	经济收入（月）-2000~5000
	 * 	经济收入（月）-5000以上
	 * 	经济收入（月）-空
	 * 近一月出行次数:
	 * 	近一月出行次数-评分项权重
	 * 	近一月出行次数-权重封顶
	 * 	近一月出行次数-=6次
	 * 	近一月出行次数-=5次
	 * 	近一月出行次数-=4次
	 * 	近一月出行次数-=3次
	 * 	近一月出行次数-=2次
	 * 	近一月出行次数-<=1次
	 * 场所属性:
	 * 	场所属性-评分项权重
	 * 	场所属性-权重封顶
	 * 	场所属性-娱乐场所权重（近一年）@>=20次
	 * 	场所属性-娱乐场所权重（近一年）@>2次，且<20次
	 * 	场所属性-娱乐场所权重（近一年）@<=2次
	 * 	场所属性-网吧权重（近一年）@>=240小时
	 * 	场所属性-网吧权重（近一年）@>56小时，且<240小时
	 * 	场所属性-网吧权重（近一年）@<=56小时
	 * 	场所属性-酒店宾馆权重（近一年）@>=20天
	 * 	场所属性-酒店宾馆权重（近一年）@>2天，且<20天
	 * 	场所属性-酒店宾馆权重（近一年）@<=2天
	 */
	private String key ;
	
	private Object value ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	
	
}
