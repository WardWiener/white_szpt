package com.taiji.pubsec.szpt.jz.ws.pojo;

/**
 * 警综组织机构
 * @author Administrator
 *
 */
public class JingZongOrganization {
	
	/**
	 * 机构代码
	 */
	private String jgdm;
	
	/**
	 * 机构名称
	 */
	private String jgmc;
	
	/**
	 * 机构树名称
	 */
	private String jgsmc;
	
	/**
	 * 机构简称
	 */
	private String jgjc;
	
	/**
	 * 机构别名
	 */
	private String jgbm;
	
	/**
	 * 行政区划
	 */
	private String xzqh;
	
	/**
	 * 行政区划（中文名称）
	 */
	private String xzqh_name;
	
	/**
	 * 单位警种
	 */
	private String ssjz;
	
	/**
	 * 单位警种（中文名称）
	 */
	private String ssjz_name;
	
	/**
	 * 机构类别
	 */
	private String jglb;
	
	/**
	 * 机构类别（中文名称）
	 */
	private String jglb_name;
	
	/**
	 * 详细地址
	 */
	private String xxdz;
	
	/**
	 * 单位联系电话
	 */
	private String lxdh;
	
	/**
	 * 单位传真
	 */
	private String dwcz;
	
	/**
	 * 单位邮编
	 */
	private String dwyb;
	
	/**
	 * 经度
	 */
	private String dwdzjd;
	
	/**
	 * 纬度
	 */
	private String dwdzwd;
	
	/**
	 * 业务管辖上级
	 */
	private String ywgxsj;

	/**
	 * 业务管辖上级（中文名称）
	 */
	private String ywgxsj_name;
	
	/**
	 * 行政管辖上级
	 */
	private String zxgxsj;
	
	/**
	 * 行政管辖上级（中文名称）
	 */
	private String zxgxsj_name;
	
	/**
	 * 单位状态
	 */
	private String state;
	
	/**
	 * 单位状态（中文名称）
	 */
	private String state_name;
	
	/**
	 * 创建时间
	 */
	private Number inputtime;
	
	/**
	 * 修改时间
	 */
	private Number modifiedtime;
	
	/**
	 * 审批状态
	 */
	private String spzt;
	
	/**
	 * 审批状态（中文名称）
	 */
	private String spzt_name;
	
	/**
	 * 安全级别
	 */
	private String aqjb;
	
	/**
	 * 安全级别（中文名称）
	 */
	private String aqjb_name;
	
	/**
	 * 原始机构编号
	 */
	private String id;
	
	/**
	 * 管辖范围
	 */
	private String gxfw;

	/**
	 * @return the jgdm
	 */
	public String getJgdm() {
		return jgdm;
	}

	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}

	/**
	 * @return the jgsmc
	 */
	public String getJgsmc() {
		return jgsmc;
	}

	/**
	 * @return the jgjc
	 */
	public String getJgjc() {
		return jgjc;
	}

	/**
	 * @return the jgbm
	 */
	public String getJgbm() {
		return jgbm;
	}

	/**
	 * @return the xzqh
	 */
	public String getXzqh() {
		return xzqh;
	}

	/**
	 * @return the xzqh_name
	 */
	public String getXzqh_name() {
		return xzqh_name;
	}

	/**
	 * @return the ssjz
	 */
	public String getSsjz() {
		return ssjz;
	}

	/**
	 * @return the ssjz_name
	 */
	public String getSsjz_name() {
		return ssjz_name;
	}

	/**
	 * @return the jglb
	 */
	public String getJglb() {
		return jglb;
	}

	/**
	 * @return the jglb_name
	 */
	public String getJglb_name() {
		return jglb_name;
	}

	/**
	 * @return the xxdz
	 */
	public String getXxdz() {
		return xxdz;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @return the dwcz
	 */
	public String getDwcz() {
		return dwcz;
	}

	/**
	 * @return the dwyb
	 */
	public String getDwyb() {
		return dwyb;
	}

	/**
	 * @return the dwdzjd
	 */
	public String getDwdzjd() {
		return dwdzjd;
	}

	/**
	 * @return the dwdzwd
	 */
	public String getDwdzwd() {
		return dwdzwd;
	}

	/**
	 * @return the ywgxsj
	 */
	public String getYwgxsj() {
		return ywgxsj;
	}

	/**
	 * @return the ywgxsj_name
	 */
	public String getYwgxsj_name() {
		return ywgxsj_name;
	}

	/**
	 * @return the zxgxsj
	 */
	public String getZxgxsj() {
		return zxgxsj;
	}

	/**
	 * @return the zxgxsj_name
	 */
	public String getZxgxsj_name() {
		return zxgxsj_name;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the state_name
	 */
	public String getState_name() {
		return state_name;
	}

	/**
	 * @return the inputtime
	 */
	public Long getInputtime() {
		return inputtime == null ? null : inputtime.longValue();
	}

	/**
	 * @return the modifiedtime
	 */
	public Long getModifiedtime() {
		return modifiedtime == null ? null : modifiedtime.longValue();
	}

	/**
	 * @return the spzt
	 */
	public String getSpzt() {
		return spzt;
	}

	/**
	 * @return the spzt_name
	 */
	public String getSpzt_name() {
		return spzt_name;
	}

	/**
	 * @return the aqjb
	 */
	public String getAqjb() {
		return aqjb;
	}

	/**
	 * @return the aqjb_name
	 */
	public String getAqjb_name() {
		return aqjb_name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the gxfw
	 */
	public String getGxfw() {
		return gxfw;
	}
	
	
}
