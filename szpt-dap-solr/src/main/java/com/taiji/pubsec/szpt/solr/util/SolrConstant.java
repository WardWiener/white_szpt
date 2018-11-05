package com.taiji.pubsec.szpt.solr.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SolrConstant {
	
	public static final String COLLECTION_POPULATION = "population" ; //人员
	
	public static final String COLLECTION_CASE = "case" ; //案件
	
	public static final String COLLECTION_PLACE = "place" ; //场所
	
	public static final String COLLECTION_INSTRUCTION = "instruction" ; // 指令
	
	public static final String COLLECTION_ALERT_SITUATION = "alertsituation" ; //警情
	
	public static final String COLLECTION_WIFI_TRACK = "wifitrack" ; //wifi轨迹
	
	public static final String COLLECTION_ACCOMMODATION = "accommodation" ;//住宿信息
	
	public static final String COLLECTION_CYBERCAFE = "cybercafe" ; // 网吧信息
	
	public static final String COLLECTION_FLIGHT = "flight" ; //航班信息
	
	public static final String COLLECTION_TRAIN_TICKET = "trainticket" ; //火车票
	
	public static final String COLLECTION_WIFI_POINT = "wifipoint" ; //wifi监控点
	
	public static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	//wifi监控点
	public enum WifiPoint {
		id, //场所编号
		name,// 名称
		address, //地址
		position;//场所经纬度
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//火车票
	public enum TrainTicket {
		id, // 值为 idcard + starttime+ trainnumber
		idcard, //身份证号
		trainnumber, //车次
		startstation, //出发车站
		arriveatstation, //目的车站
		starttime, //发车时间
		persontypecode, //人员类别编码
		criminaltypecode, //前科类型编码
		text; //输入查询内容
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//航班
	public enum Flight {
		id,// 值是idcard + flightnumber + takeofftime
		idcard, //身份证号
		flightnumber, //航班号
		takeoffairport, //起飞机场
		arriveatairport, //到达机场
		takeofftime, //起飞时间
		arriveattime, //到达时间
		persontypecode, //人员类别编码
		criminaltypecode, //前科类型编码
		seatnum, //座位号
		text; //输入查询内容
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//网吧
	public enum Cybercafe {
		id, //值为 idcard + cybercafename +entertime+leavetime
		idcard,//身份证号
		cybercafecode, //网吧编码
		cybercafename, //网吧名称
		cybercafeaddress, //网吧地址
		entertime, //进入时间
		leavetime, //离开时间
		persontypecode, //人员类别编码
		criminaltypecode, //前科类型编码
		terminalnum, //终端号
		text; //输入查询内容
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//住宿
	public enum Accommodation {
		id, //值为 idcard+entertime+leavetime+hotelcode
		idcard, //身份证号
		hotelcode,//旅馆编码
		hotelname, //旅馆名称
		hoteladdress, //旅馆地址
		entertime, // 进入时间
		leavetime, //离开时间
		persontypecode, //人员类别编码
		criminaltypecode, //前科类型编码
		roomnum, //房间号
		text; // 输入的查询条件
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//wifi轨迹
	public enum WifiTrack {
		id, // 值为 mac+placecode+entertime+leavetime
		mac, //mac地址
		placecode,//场所编码
		placename,//场所名称
		entertime,//进入时间
		leavetime,//离开时间
		period,//停留时间
		phonenumber,//手机号
		placeposition,//场所经纬度
		persontypecode,//人员类别编码
		criminaltypecode,//前科类型编码
		tag; //是否有五色预警类型值(fivecolor)
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//案件
	public enum Case {
		id, //案件编号
		name, //案件名称
		content,//案件内容
		crimetime, //案发时间
		occurtime_start,//接口拼接查询条件的传入值，不是solr的field  案发时间开始
		occurtime_end,//接口拼接查询条件的传入值，不是solr的field  案发时间结束
		property, //类别
		address, //案发地点
		state, //案件状态
		handler, //办案人
		text; //输入查询内容
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//人员
	public enum Population {
		id,//身份证号
		idcard,//身份证号
		name,//姓名
		oldname,//曾用名
		gender,//性别
		type,//类型（是否高危人）
		collecttime,//采集时间
		collecttime_start,//接口拼接查询条件的传入值，不是solr的field
		collecttime_end,//接口拼接查询条件的传入值，不是solr的field
		alertlevel,//预警级别
		nation,//民族
		birthday,//出生日期
		birthaddress,//出生地
		culture,//文化程度
		marry,//婚姻状况
		occupation,//职业
		phone,//联系电话
		householder,//户主姓名
		relation,//与户主关系
		address,//户籍地址
		persontypecode,//人员类别编码
		criminaltypecode,//前科类型编码
		text;
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//警情
	public enum AlertSituation {
		id, //警情编号
		name,//警情名称
		content,//警情内容
		type, //警情类别名称
		region, //辖区名称
		community, //村居名称
		answertime, //接警时间
		answertime_start, //接口拼接查询条件的传入值，不是solr的field 接警时间开始
		answertime_end, //接口拼接查询条件的传入值，不是solr的field  接警时间结束
		occurtime, //发生时间
		occurtime_start,//接口拼接查询条件的传入值，不是solr的field  发生时间开始
		occurtime_end,//接口拼接查询条件的传入值，不是solr的field  发生时间结束
		occuraddress, //发生地点
		level, //紧急程度名称
		source, //警情来源名称
		state, //警情状态名称
		text; //输入的查询条件
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//场所
	public enum Place {
		id, //场所编号
		name, //名称
		address,//地址
		collecttime, //采集时间
		collecttime_start,//接口拼接查询条件的传入值，不是solr的field 采集时间开始
		collecttime_end,//接口拼接查询条件的传入值，不是solr的field  采集时间结束
		type,  //类别
		alertlevel; //预警级别
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
	
	//指令
	public enum Instruction {
		id, //指令编号
		type, //类型名称
		content, //内容
		createtime,//创建时间
		createtime_start, //接口拼接查询条件的传入值，不是solr的field 创建时间开始
		createtime_end, //接口拼接查询条件的传入值，不是solr的field 创建时间结束
		askfeedbacktime, //要求反馈时间
		askfeedbacktime_start, //接口拼接查询条件的传入值，不是solr的field 要求反馈时间开始
		askfeedbacktime_end, //接口拼接查询条件的传入值，不是solr的field 要求反馈时间结束
		receiveunit, //接收单位名称
		sendunit, //发送单位名称
		reletedcontent, //关联业务数据描述
		text; //输入的查询条件
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.name() ;
		}
	}
}
