package com.taiji.pubsec.szpt.jz.ws.pojo;

import java.util.Map;

/**
 * 警综推送用户结构
 * @author Administrator
 *
 */
public class JingZongUserPushDataJson {
	
	/**
	 * 用户编号
	 */
	private String GMSFHM;
	
	/**
	 * 登陆名称
	 */
	private String DLMC;
	
	/**
	 * 警员编号
	 */
	private String JYBH;
	
	/**
	 * 真实姓名
	 */
	private String ZSXM;
	
	/**
	 * 人员性别
	 */
	private String RYXB;
	
	/**
	 * 出生日期
	 */
	private Long CSRQ;
	
	/**
	 * 联系电话
	 */
	private String LXDH;
	
	/**
	 * 电子邮件
	 */
	private String DZYJ;
	
	/**
	 * 警种代码
	 */
	private String JZDM;
	
	/**
	 * 用户分类
	 */
	private String YHFL;
	
	/**
	 * 用户状态
	 */
	private String YHZT;
	
	/**
	 * 有效期起始日期
	 */
	private Long YXQQSRQ; 
	
	/**
	 * 有效期截止日期
	 */
	private Long YXQJZRQ;
	
	/**
	 * 最后一次登陆ip地址
	 */
	private String IPDZ;
	
	/**
	 * 登陆次数
	 */
	private int LOGINTIME;
	
	/**
	 * 最后一次登陆时间
	 */
	private Long LASTLOGINDATE;
	
	/**
	 * 是否启用PKI
	 */
	private String ISPKI;
	
	/**
	 * 创建时间
	 */
	private Long INPUTTIME;
	
	/**
	 * 修改时间
	 */
	private Long MODIFIEDTIME;
	
	/**
	 * 安全级别
	 */
	private String AQJB;
	
	/**
	 * 编号
	 */
	private String ID;
	
	/**
	 * 变更内容表示符
	 * 新增： save
	 * 更新：update
	 */
	private String FLAG;
	
	private Map<String, Object> SSJG;

	/**
	 * @return the gMSFHM
	 */
	public String getGMSFHM() {
		return GMSFHM;
	}

	/**
	 * @return the dLMC
	 */
	public String getDLMC() {
		return DLMC;
	}

	/**
	 * @return the jYBH
	 */
	public String getJYBH() {
		return JYBH;
	}

	/**
	 * @return the zSXM
	 */
	public String getZSXM() {
		return ZSXM;
	}

	/**
	 * @return the rYXB
	 */
	public String getRYXB() {
		return RYXB;
	}

	/**
	 * @return the cSRQ
	 */
	public Long getCSRQ() {
		return CSRQ;
	}

	/**
	 * @return the lXDH
	 */
	public String getLXDH() {
		return LXDH;
	}

	/**
	 * @return the dZYJ
	 */
	public String getDZYJ() {
		return DZYJ;
	}

	/**
	 * @return the jZDM
	 */
	public String getJZDM() {
		return JZDM;
	}

	/**
	 * @return the yHFL
	 */
	public String getYHFL() {
		return YHFL;
	}

	/**
	 * @return the yHZT
	 */
	public String getYHZT() {
		return YHZT;
	}

	/**
	 * @return the yXQQSRQ
	 */
	public Long getYXQQSRQ() {
		return YXQQSRQ;
	}

	/**
	 * @return the yXQJZRQ
	 */
	public Long getYXQJZRQ() {
		return YXQJZRQ;
	}

	/**
	 * @return the iPDZ
	 */
	public String getIPDZ() {
		return IPDZ;
	}

	/**
	 * @return the lOGINTIME
	 */
	public int getLOGINTIME() {
		return LOGINTIME;
	}

	/**
	 * @return the lASTLOGINDATE
	 */
	public Long getLASTLOGINDATE() {
		return LASTLOGINDATE;
	}

	/**
	 * @return the iSPKI
	 */
	public String getISPKI() {
		return ISPKI;
	}

	/**
	 * @return the iNPUTTIME
	 */
	public Long getINPUTTIME() {
		return INPUTTIME;
	}

	/**
	 * @return the mODIFIEDTIME
	 */
	public Long getMODIFIEDTIME() {
		return MODIFIEDTIME;
	}

	/**
	 * @return the aQJB
	 */
	public String getAQJB() {
		return AQJB;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the fLAG
	 */
	public String getFLAG() {
		return FLAG;
	}

	/**
	 * @return the sSJG
	 */
	public Map<String, Object> getSSJG() {
		return SSJG;
	}
	
	

}
