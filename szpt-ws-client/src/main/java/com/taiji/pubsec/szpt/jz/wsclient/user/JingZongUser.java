package com.taiji.pubsec.szpt.jz.wsclient.user;

import java.util.Map;

/**
 * 警综用户
 * @author Administrator
 *
 */
public class JingZongUser {
	
	/**
	 * 用户编号（身份证号号码）
	 */
	private String gmsfhm;
	
	/**
	 * 登陆名称
	 */
	private String dlmc;
	
	/**
	 * 警员编号
	 */
	private String jybh;
	
	/**
	 * 真实姓名
	 */
	private String zsxm;
	
	/**
	 * 人员性别
	 */
	private String ryxb;
	
	/**
	 * 人员性别（中文名称）
	 */
	private String ryxb_name;
	
	/**
	 * 出生日期
	 */
	private Long csrq; 
	
	
	/**
	 * 所属单位_公安机关机构代码
	 */
	private String ssdw_gajgjgdm;
	
	/**
	 * 所属单位_公安机关名称
	 */
	private String ssdw_gajgmc;
	
	/**
	 * 隶属关系
	 */
	private String lsgx;
	
	/**
	 * 隶属关系（中文名称）
	 */
	private String lsgx_name;
	
	/**
	 * 联系电话
	 */
	private String lxdh;
	
	/**
	 * 电子邮件
	 */
	private String dzyj;
	
	/**
	 * 警种代码
	 */
	private String jzdm;
	
	/**
	 * 警种代码（中文名称）
	 */
	private String jzdm_name;
	
	/**
	 * 用户分类
	 */
	private String yhfl;
	
	/**
	 * 用户分类（中文名称）
	 */
	private String yhfl_name;
	
	/**
	 * 用户状态
	 */
	private String yhzt;
	
	/**
	 * 用户状态（中文名称）
	 */
	private String yhzt_name;
	
	/**
	 * 有效期起始日期
	 */
	private Long yxqqsrq;
	
	/**
	 * 有效期截止日期
	 */
	private Long yxqjzrq;

	/**
	 * 最后一次登陆ip地址
	 */
	private String ipdz;
	
	/**
	 * 登陆次数
	 */
	private int logintime;
	
	/**
	 * 最后一次登陆时间
	 */
	private Long lastlogindate;
	
	/**
	 * 是否启用pki
	 */
	private String ispki;
	
	/**
	 * 是否启用pki（中文名称）
	 */
	private String ispki_name;
	
	/**
	 * 创建时间
	 */
	private Long inputtime;
	
	/**
	 * 修改时间
	 */
	private Long modifiedtime;
	
	/**
	 * 安全级别
	 */
	private String aqjb;
	
	/**
	 * 安全级别（中文名称）
	 */
	private String aqjb_name;
	
	/**
	 * 编号
	 */
	private String id;
	
	/**
	 * @return the gmsfhm
	 */
	public String getGmsfhm() {
		return gmsfhm;
	}

	/**
	 * @return the dlmc
	 */
	public String getDlmc() {
		return dlmc;
	}

	/**
	 * @return the jybh
	 */
	public String getJybh() {
		return jybh;
	}

	/**
	 * @return the zsxm
	 */
	public String getZsxm() {
		return zsxm;
	}

	/**
	 * @return the ryxb
	 */
	public String getRyxb() {
		return ryxb;
	}

	/**
	 * @return the ryxb_name
	 */
	public String getRyxb_name() {
		return ryxb_name;
	}

	/**
	 * @return the csrq
	 */
	public Long getCsrq() {
		return csrq == null ? null : csrq.longValue();
	}

	/**
	 * @return the ssdw_gajgjgdm
	 */
	public String getSsdw_gajgjgdm() {
		return ssdw_gajgjgdm;
	}

	/**
	 * @return the ssdw_gajgmc
	 */
	public String getSsdw_gajgmc() {
		return ssdw_gajgmc;
	}

	/**
	 * @return the lsgx
	 */
	public String getLsgx() {
		return lsgx;
	}

	/**
	 * @return the lsgx_name
	 */
	public String getLsgx_name() {
		return lsgx_name;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @return the dzyj
	 */
	public String getDzyj() {
		return dzyj;
	}

	/**
	 * @return the jzdm
	 */
	public String getJzdm() {
		return jzdm;
	}

	/**
	 * @return the jzdm_name
	 */
	public String getJzdm_name() {
		return jzdm_name;
	}

	/**
	 * @return the yhfl
	 */
	public String getYhfl() {
		return yhfl;
	}

	/**
	 * @return the yhfl_name
	 */
	public String getYhfl_name() {
		return yhfl_name;
	}

	/**
	 * @return the yhzt
	 */
	public String getYhzt() {
		return yhzt;
	}

	/**
	 * @return the yhzt_name
	 */
	public String getYhzt_name() {
		return yhzt_name;
	}

	/**
	 * @return the yxqqsrq
	 */
	public Long getYxqqsrq() {
		return yxqqsrq == null ? null : yxqqsrq.longValue();
	}

	/**
	 * @return the yxqjzrq
	 */
	public Long getYxqjzrq() {
		return yxqjzrq == null ? null : yxqjzrq.longValue();
	}

	/**
	 * @return the ipdz
	 */
	public String getIpdz() {
		return ipdz;
	}

	/**
	 * @return the logintime
	 */
	public int getLogintime() {
		return logintime;
	}

	/**
	 * @return the lastlogindate
	 */
	public Long getLastlogindate() {
		return lastlogindate == null ? null : lastlogindate.longValue();
	}

	/**
	 * @return the ispki
	 */
	public String getIspki() {
		return ispki;
	}

	/**
	 * @return the ispki_name
	 */
	public String getIspki_name() {
		return ispki_name;
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

	
	
}
