package com.taiji.pubsec.szpt.jz.wsclient.authority;


/**
 * 警综授权用户信息
 * @author Administrator
 *
 */
public class JingZongAuthorization {

	/**
	 * 错误提示信息。无错误返回不会有该字段信息
	 */
	private String msg;
	
	/**
	 * 用户编号（身份证号码）
	 */
	private String gmsfhm;
	
	/**
	 * 真实姓名
	 */
	private String zsxm;
	
	/**
	 * 登录名称
	 */
	private String dlmc;
	
	/**
	 * 警员编号
	 */
	private String jybh;
	
	/**
	 * 组织机构名称
	 */
	private String zzjgmc;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public String getDlmc() {
		return dlmc;
	}

	public void setDlmc(String dlmc) {
		this.dlmc = dlmc;
	}

	public String getJybh() {
		return jybh;
	}

	public void setJybh(String jybh) {
		this.jybh = jybh;
	}

	public String getZzjgmc() {
		return zzjgmc;
	}

	public void setZzjgmc(String zzjgmc) {
		this.zzjgmc = zzjgmc;
	}
	
	
}
