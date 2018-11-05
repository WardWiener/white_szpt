package com.taiji.pubsec.szpt.util;

public class Constant {
	
	private static String SZPT_HOST_PORT ;
	
	public Constant(String SZPT_HOST_PORT){
		Constant.SZPT_HOST_PORT = SZPT_HOST_PORT ;
	}
	
	public static String SZPT_HOST_PORT(){
		return Constant.SZPT_HOST_PORT ;
	}
	
	public enum SORT_TYPE implements ConstantEnum{
		ASC("ASC"),
		DESC("DESC");
		
		private String value ;
		
		private SORT_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(SORT_TYPE.values()) ;
		}
	}
	
	public enum STATISTICS_TYPE implements ConstantEnum{
		XLZG("巡逻主格"),
		PCSXQ("派出所辖区"),
		SQ("社区");
		
		private String value ;
		
		private STATISTICS_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(STATISTICS_TYPE.values()) ;
		}
	}
	
	public enum STATISTICS_TOTAL implements ConstantEnum{
		XLZG_TOTAL("合计"),
		PCSXQ_TOTAL("合计"),
		SQ_TOTAL("合计");
		
		private String value ;
		
		private STATISTICS_TOTAL(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(STATISTICS_TOTAL.values()) ;
		}
	}
	
	public enum STATISTICS_TIME implements ConstantEnum{
		YEAR("年"),
		MONTH("月"),
		WEEK("周"),
		DAY("日");
		private String value ;
		
		private STATISTICS_TIME(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(STATISTICS_TIME.values()) ;
		}
	}
	
	public enum STATISTICS_COLOR implements ConstantEnum{
		BLUE("蓝"),
		YELLOW("黄"),
		ORANGE("橙"),
		RED("红"),
		ADVICE("建议");
		private String value ;
		
		private STATISTICS_COLOR(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(STATISTICS_COLOR.values()) ;
		}
	}
	
	public enum PCS_THRESHOLD_TYPE implements ConstantEnum{
		
		DAY("DAY"),
		WEEK("WEEK"),
		MONTH("MONTH"),
		YEAR("YEAR");
		
		private String value ;
		
		private PCS_THRESHOLD_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		

		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(PCS_THRESHOLD_TYPE.values()) ;
		}
	}
	
	public enum PCS_FENBU_THRESHOLD_TYPE implements ConstantEnum{
		
		FENBU_DAY("FENBU_DAY"),
		FENBU_WEEK("FENBU_WEEK"),
		FENBU_MONTH("FENBU_MONTH"),
		FENBU_YEAR("FENBU_YEAR");
		
		private String value ;
		
		private PCS_FENBU_THRESHOLD_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(PCS_FENBU_THRESHOLD_TYPE.values()) ;
		}
	}
	
	public enum DICT implements ConstantEnum{
		DICT_XSQK("014"),//刑事前科
		DICT_TYPE_YJLX("yjlx"),//预警类型
		DICT_YJLX_RED("0"),//红色
		DICT_YJLX_ORANGE("1"),//橙色
		DICT_YJLX_YELLOW("2"),//黄色
		DICT_YJLX_WHITE("3"),//白色
		DICT_YJLX_BLUE("4"),//蓝色
		DICT_YJLX_OTHER("5"),//其他
		DICT_TYPE_RYLX("rylx"),//人员类型
		DICT_TYPE_QKLX("qklx"),//前科类型
		DICT_TYPE_SACS("xsajfx-sacs"),//涉案场所
		DICT_TYPE_AJDBZT("xsajfx-ajdbzt"),//案件打标状态
		DICT_TYPE_ZACRK("xsajfx-zacrk"),//作案进口
		DICT_TYPE_ZARK("xsajfx-zark"),//作案进口
		DICT_TYPE_ZACK("xsajfx-zack"),//作案出口
		DICT_TYPE_ZARS("xsajfx-zars"),//作案人数
		DICT_TYPE_ZATIME("xsajfx-zasd"),//作案时间段
		DICT_TYPE_ZATD("xsajfx-zatd"),//作案特点
		DICT_TYPE_AJJB("xsajfx-ajjb"),//案件级别
		DICT_TYPE_FACS("xsajfx-afcs"),//发案处所
		DICT_TYPE_AJXZ("xsajfx-ajxz"),//案件性质(案件类别)
		DICT_TYPE_AJXZID("eb1bc3be-bdb8-192a-5afa-6470574d1603"),//案件性质id(案件类别id)
		DICT_TYPE_TJYS("tjys"),//统计颜色
		DICT_TYPE_BGLX("bglx"),//报告类型
		DICT_TYPE_ZAXZ("zaxz"),//专案管理--专案性质
		DICT_TYPE_V3AJXZ("xsajfx-v3ajxz"),//V3案件性质
		DICT_TYPE_V3AJLB("xsajfx-v3ajlb"),//V3案件类别
		DICT_TYPE_ZT("zt"),//状态
		DICT_TYPE_XTZT("xtzt"),//系统状态
		DICT_TYPE_ZW("zw"),//职务
		DICT_TYPE_XB("xb"),//性别
		DICT_TYPE_SF("sf"),//是否
		DICT_TYPE_ZAZLLX("zazllx"),//专案资料类型
		DICT_TYPE_ZT_ID("0000000002"),//状态id
		DICT_ENABLED("1"),//启用
		DICT_DISENABLED("0"),//停用
		DICT_XTZT_INIT("init"),//初始化
		DICT_YES("1"),//是
		DICT_NO("0"),//否
		DICT_AJXZ_DAOQIE("09020100"),
		DICT_AJXZ_YBDQA("001007"),// 一般盗窃
		DICT_AJXZ_QTDQA("001008"),// 其它盗窃
		DICT_DWLX_PCS("2"),// 单位类型：派出所
		DICT_AJXZ_DQA("001"),// 案件性质：盗窃案
		DICT_AJXZ_QJA("002"),// 案件性质：抢劫案
		DICT_AJXZ_QDA("003"),// 案件性质：抢夺案
		DICT_TAG_YES("002"),//打标状态：已打标
		DICT_TAG_NO	("001"),//打标状态：未打标
		DICT_ROLE_CODE_BUREAULEADER("zagl000401"),//专案角色id：局领导
		DICT_ROLE_CODE_GROUPLEADER("zagl000402"),//专案角色id：组长
		DICT_ROLE_CODE_PENDRAGON("zagl000403"),//专案角色id：副组长
		DICT_ZLLX("zllx"),//指令类型
		DICT_ZLLX_LDZL("0000000011005"),//落地指令
		DICT_ZLLX_LDZL_RYPC("0000000011005001"),//人员盘查
		DICT_ZLLX_YPZL("0000000011004"),//研判指令
		DICT_ZLLX_RYYPZL("0000000011004001"),//人员研判指令
		DICT_JQLX_CODE_XSJQ("01"),//刑事类警情
		DICT_ZLZT("zlzt"),//指令状态
		DICT_ZLZT_DQS("0000000012001"),//指令状态待签收
		DICT_ZLZT_YQS("0000000012002"),//指令状态已签收
		DICT_ZLZT_YFK("0000000012003");//指令状态已反馈
		
		private String value ;
		
		private DICT(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(DICT.values()) ;
		}
	}
	
	public enum ROLE_TYPE implements ConstantEnum{
		ROLE_TYPE_YZ("0"),  //预置
		ROLE_TYPE_ZDY("1"); //自定义
		
		private String value ;
		
		private ROLE_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(ROLE_TYPE.values()) ;
		}
	}
	
	
	public enum JQLX_CODE implements ConstantEnum{
		
		XING_SHI("01"),
		ZHI_AN("02"),
		QI_TA("99");
		
		private String value ;
		
		private JQLX_CODE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(JQLX_CODE.values()) ;
		}
	}

	public enum SHI_DUAN implements ConstantEnum{
		
		RANGE1("0~3", "0~2"), 
		RANGE2("3~6", "3~5"), 
		RANGE3("6~9", "6~8"), 
		RANGE4("9~12", "9~11"), 
		RANGE5("12~15", "12~14"),
		RANGE6("15~18", "15~17"),
		RANGE7("18~21", "18~20"),
		RANGE8("21~24", "21~23");
		
		private String key ;
		private String value ;
		
		private SHI_DUAN(String key, String value){
			this.key = key ;
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {
			return key;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByKeyAndValue(SHI_DUAN.values()) ;
		}
	}
	
	public enum XSFXTS_CONSTANT implements ConstantEnum{
	
		CRIMINALBASICCASE_CLASS_NAME("com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase"),// 案件信息model类路径
		UNIT_CLASS_NAME("com.taiji.pubsec.businesscomponents.organization.model.Unit"),//单位model类路径
		FACTJQ_CLASS_NAME("com.taiji.pubsec.szpt.zhzats.model.FactJq");//警情信息model类路径
		
		private String value ;
		
		private XSFXTS_CONSTANT(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(XSFXTS_CONSTANT.values()) ;
		}
	}
	
	/**
	 * 快照使用模块
	 */
	public enum INFO_SNAPSHOT_MODULE implements ConstantEnum{
		
		XSAJFX_CBA("AJXSFX_CBA"),// 刑事案件分析模块，串并案保存的积分分析快照
		XSAJFX_XYRMACFX("XSAJFX_XYRMACFX"),// 刑事案件分析模块，嫌疑人mac地址分析快照
		CSJKFX_SSWIFI("CSJKFX_SSWIFI"),// 场所监控分析模块，实时wifi分析快照
		CSJKFX_WIFIGJ("CSJKFX_WIFIGJ"),// 场所监控分析模块，wifi轨迹分析快照
		GWR_GJFX("GWR_GJFX");// 高危人群分析预警模块，轨迹分析快照
		
		private String value ;
		
		private INFO_SNAPSHOT_MODULE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(INFO_SNAPSHOT_MODULE.values()) ;
		}
	}
	
	/**
	 * 单位编码
	 */
	public enum UNIT_CODE implements ConstantEnum{
		
		UNIT_CODE_QZZX("520199310000"),// 情指中心
		UNIT_CODE_JKFJ("520199000000");// 经开分局
		
		private String value ;
		
		private UNIT_CODE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(UNIT_CODE.values()) ;
		}
	}
	
	/**
	 * 菜单类型
	 */
	public enum MENU_TYPE implements ConstantEnum{
		
		MENU_TYPE_MR("0"),// 系统预定义
		MENU_TYPE_ZDY("1");// 自定义菜单
		
		private String value ;
		
		private MENU_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(UNIT_CODE.values()) ;
		}
	}
}
