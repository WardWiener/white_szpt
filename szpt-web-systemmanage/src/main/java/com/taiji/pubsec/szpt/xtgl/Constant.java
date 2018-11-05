package com.taiji.pubsec.szpt.xtgl;



/**
 * 自定义的常量类
 * 
 * @author xinfan
 *
 */
public class Constant {

	// 单位树节点是否为为父节点，否
	public static final String UNIT_ISPAR_FALSE = "False";
	// 单位树节点是否为为父节点，是
	public static final String UNIT_ISPAR_TRUE = "True";
	// 单位所在的级别 部级 省级 市级 区县 其他
	public static final String UNIT_LEVEL = "ssjb";
	// 单位所在状态 启用 或 停用
	public static final String STATUS = "zt";
	public static final String STATUS_STOP = "0";
	public static final String STATUS_START = "1";
	// 资源类型
	public static final String RESOURCE_TYPE = "zylx";
	// 实体属性 是单位 还是 部门
	public static final String UNIT_PROPERTY = "dwsx";
	// 角色启用
	public static final String ROLESTATUS_START = "启用";
	// 角色停用
	public static final String ROLESTATUS_STOP = "停用";
	// 人员性别
	public static final String SEX = "xb";
	// 人員在职状态
	public static final String WORK_STATUS = "zzzt";
	// 人员初始化职务
	public static final String JOB = "zw";
	// 人员民族
	public static final String NATIONALITY = "mz";
	// 人员政治面貌
	public static final String POLITICSTYPE = "zzmm";
	// 人员学历
	public static final String DIPLOMA = "xl";
	// 人员名称
	public static final String PERSON_NAME = "虚拟人员";
	// 初始化单位页面
	public static final String ORGANIZATION_INIT = "init";
	//分局
	public static final String UNITCODE_SUBSTATION = "520199000000";
	//时间格式化格式
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	//社区编码
	public static final String SQBM = "sq";

	private Constant() {
	}
}
