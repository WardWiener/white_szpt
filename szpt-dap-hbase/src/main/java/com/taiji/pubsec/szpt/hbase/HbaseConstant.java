package com.taiji.pubsec.szpt.hbase;

public class HbaseConstant {

	public static String getRowKeyOfTrainTicket(String idcard, String starttime, String trainnumber){
		return idcard+starttime+trainnumber ;
	}
	
	public static String getRowKeyOfWifiTrack(String mac, String entertime, String leavetime, String placecode){
		return mac+entertime+leavetime+placecode ;
	}
	
	public static String getRowKeyOfAccommodation(String idcard, String entertime, String leavetime, String hotelcode){
		return idcard+entertime+leavetime+hotelcode ;
	}
	
	public static String getRowKeyOfCybercafe(String idcard, String entertime, String leavetime, String cybercafecode){
		return idcard+entertime+leavetime+cybercafecode ;
	}
	
	public static String getRowKeyOfFlight(String idcard, String takeofftime, String arriveattime, String flightnumber){
		return idcard+takeofftime+arriveattime+flightnumber ;
	}
	
	public static String getRowKeyOfTenement(String idcard, String starttime, String endtime, String houseid){
		return idcard+starttime+endtime+houseid ;
	}
	
	//火车出行记录
	public enum TrainTicket{
		
		TABLE_NAME("trainticket"), //表名
		COLUMN_FAMILY("info"), //列族
		
		idcard("idcard"), //身份证号
		trainnumber("trainnumber"), //车次
		startstation("startstation"), //出发车站
		arriveatstation("arriveatstation"), //目的车站
		starttime("starttime"); //发车时间
		
		private String value ;
		
		private TrainTicket(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
	
	//WiFi表
	public enum WifiTrack{
		
		TABLE_NAME("wifitrack"), //表名
		COLUMN_FAMILY("stay"), //列族
		
		mac("mac"), //mac地址
		placecode("placecode"),//场所编号
		placename("placename"), //场所名称
		placeposition("placeposition"), //场所经纬度
		entertime("entertime"),//进入时间
		leavetime("leavetime"), //离开时间
		phonenumber("phonenumber"); //手机号
		
		private String value ;
		
		private WifiTrack(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
	
	//住宿记录
	public enum Accommodation{
		
		TABLE_NAME("accommodation"), //表名
		COLUMN_FAMILY("info"),
		
		idcard("idcard"), //身份证号
		hotelname("hotelname"), //旅馆名称
		hotelcode("hotelcode"), //旅馆编码
		hoteladdress("hoteladdress"), //旅馆地址
		entertime("entertime"), //进入时间
		leavetime("leavetime"),//离开时间
		roomnum("roomnum"); //房间号
		
		private String value ;
		
		private Accommodation(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
	
	//网吧记录
	public enum Cybercafe{
		
		TABLE_NAME("cybercafe"), //表名
		COLUMN_FAMILY("info"), 
		
		idcard("idcard"), //身份证号
		cybercafename("cybercafename"), //网吧名称
		cybercafecode("cybercafecode"),//网吧编码
		cybercafeaddress("cybercafeaddress"),//网吧地址
		entertime("entertime"), //进入时间
		leavetime("leavetime"), //离开时间
		terminalnum("terminalnum");//终端号
		
		private String value ;
		
		private Cybercafe(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
	
	//飞机出入港记录
	public enum Flight{
		
		TABLE_NAME("flight"), //
		COLUMN_FAMILY("info"),
		
		idcard("idcard"), //身份证号
		flightnumber("flightnumber"), //航班号
		takeoffairport("takeoffairport"), //起飞机场
		arriveatairport("arriveatairport"),//到达机场
		takeofftime("takeofftime"), //起飞时间
		arriveattime("arriveattime"), //到达时间
		seatnum("seatnum");//座位号
		
		private String value ;
		
		private Flight(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
	
	//租房记录
	public enum Tenement{
		
		TABLE_NAME("tenement"), //表名
		COLUMN_FAMILY("info"), //列族
		
		idcard("idcard"), //身份证号
		starttime("starttime"), //起租时间
		endtime("endtime"), //停租时间
		rentername("rentername"), //出租人姓名
		renteridcard("renteridcard"), //出租人身份证号
		remark("remark"), //承租情况
		houseid("houseid"); //房屋信息id
		
		private String value ;
		
		private Tenement(String value){
			this.value = value ;
		}
		
		public String getKey(){
			return this.name() ;
		}
		
		public String getValue(){
			return this.value ;
		}
	}
}
