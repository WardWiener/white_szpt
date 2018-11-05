package com.taiji.pubsec.szpt.highRiskPersonAlert.action.util;

import com.taiji.pubsec.szpt.util.ConstantEnum;
import com.taiji.pubsec.szpt.util.ConstantEnumUtil;
import com.taiji.pubsec.szpt.util.Constant.SHI_DUAN;

/**
 * 常量类
 * @author WL-PC
 *
 */
public class Constant {

	//性别-字典类型
	public static final String XB = "xb";
	
	//处置情况-字典类型
	public static final String CZQK = "czqk";
	
	//预警类型-字典类型
	public static final String YJLX = "yjlx";
	
	//预警类型-字典项-红色
	public static final String YJLX_HONG_SE = "0";
	
	//预警类型-字典项-橙色
	public static final String YJLX_CHENG_SE = "1";
	
	//预警类型-字典项-黄色
	public static final String YJLX_HUANG_SE = "2";
	
	//预警类型-字典项-白色
	public static final String YJLX_BAI_SE = "3";
	
	//预警类型-字典项-蓝色
	public static final String YJLX_LAN_SE = "4";
	
	//预警类型-字典项-其他
	public static final String YJLX_QI_TA = "5";
	
	//人员类别-字典类型
	public static final String RYLX = "rylx";
	
	//人员类别-字典类型id
	public static final String RYLXID = "0000000013";
	
	//有无-字典类型
	public static final String YW = "yw";
	
	//尿检结果-字典类型
	public static final String NJJG = "njjg";
	
	//人员类别标签-字典类型
	public static final String RYLBBQ = "rylbbq";
	
	//预警记录状态-字典类型
	public static final String YJJLZT = "yjjlzt";
	
	//预警记录状态-字典项-未处理
	public static final String YJJLZT_WCL = "0";
	
	//预警记录状态-字典项-已处理
	public static final String YJJLZT_YCL = "2";
		
	//预警记录状态-字典项-忽略
	public static final String YJJLZT_HL = "1";
	
	//人员类型-刑事前科
	public static final String RYLX_XSQK = "017";
	
	//人员类型-抢劫
	public static final String RYLX_QJ = "017004000";
	
	//人员类型-抢夺
	public static final String RYLX_QD = "017004003";
	
	//人员类型-盗窃
	public static final String RYLX_DQ = "017004001";
	
	//人员类型-诈骗
	public static final String RYLX_ZP = "017004002";
	
	//人员类型-其他
	public static final String RYLX_QT = "QITA";
	
	//人员类型-涉恐
	public static final String RYLX_SK = "000";
	
	//人员类型-涉稳
	public static final String RYLX_SW = "001";
	
	//人员类型-涉毒
	public static final String RYLX_SD = "003";
	
	//人员类型-肇事肇祸精神病
	public static final String RYLX_JSB = "004";
	
	//人员类型-重点上访
	public static final String RYLX_ZDSF = "005";
	
	//人员类型-在逃
	public static final String RYLX_ZT = "002";
	
	//人员类型-违法犯罪青少年
	public static final String RYLX_QSN = "015";
	
	//人员类型-艾滋病人
	public static final String RYLX_AZBR = "016";
	
	//前科类型
	public static final String QKLX = "qklx";
	
	//启用
	public static final String ENABLED = "1";
	
	//停用
	public static final String DISABLED = "0";
	
	//在控类型-字典类型
	public static final String ZKLX = "zklx";
	
	//婚姻情况-字典类型
	public static final String HYQK = "hyqk";

	//就业情况-字典类型
	public static final String JYQK = "jyqk";
	
	//职业-字典类型
	public static final String ZY = "zy";
	
	//状态-字典类型
	public static final String ZT = "zt";
	
	//操作状态-字典类型
	public static final String CZZT = "czzt";
	
	//操作状态-新增
	public static final String CZZT_XZ = "0000000033000";
	
	//操作状态-待审批
	public static final String CZZT_DSP = "0000000033001";
	
	//操作状态-审批通过
	public static final String CZZT_SPTG = "0000000033002";
	
	//操作状态-审批驳回
	public static final String CZZT_SPBH = "0000000033003";
	
	//案件类别-字典类型
	public static final String AJLB = "ajlb";
	
	//学历-字典类型
	public static final String XL = "xl";
	
	//民族-字典类型
	public static final String MZ = "mz";
	
	public enum XB_XB implements ConstantEnum{
		
		NAN("男", "1"), 
		NV("女", "2"), 
		WEIZHI("未知", "0");

		private String key ;
		private String value ;
		
		private XB_XB(String key, String value){
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
	
}
