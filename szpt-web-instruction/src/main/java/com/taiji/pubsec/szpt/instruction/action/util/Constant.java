package com.taiji.pubsec.szpt.instruction.action.util;

import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;

/**
 * 常量类
 * @author WL-PC
 *
 */
public class Constant {

	/**
	 * 指令类型-字典类型
	 */
	public static final String ZLLX = "zllx";
	/**
	 * 指令状态-字典类型
	 */
	public static final String ZLZT = "zlzt";
	/**
	 * 指令状态-待签收
	 */
	public static final String ZLZT_DQS = "0000000012001";
	/**
	 * 指令状态-已签收
	 */
	public static final String ZLZT_YQS = "0000000012002";
	/**
	 * 指令状态-已反馈
	 */
	public static final String ZLZT_YFK = "0000000012003";
	
	/**
	 * 指令类型-人员盘查
	 */
	public static final String ZLLX_RYPC = "0000000011005001";
	/**
	 * 指令类型-车辆盘查
	 */
	public static final String ZLLX_CLPC = "0000000011005002";
	/**
	 * 指令类型-情报核实指令
	 */
	public static final String ZLLX_QBHSZL = "0000000011006";
	/**
	 * 指令类型-研判结果推送指令
	 */
	public static final String ZLLX_YPJGTSZL = "0000000011007";
	/**
	 * 启用
	 */
	public static final String ENABLED = "1";
	
	/**
	 * 高危人员class name
	 */
	public static final String CZDXLX_GWRY = HighriskPerson.class.toString().substring(6);
}
