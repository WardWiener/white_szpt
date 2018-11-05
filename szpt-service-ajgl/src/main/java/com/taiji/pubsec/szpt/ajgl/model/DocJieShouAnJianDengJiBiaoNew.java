package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 接受案件登记表
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_jsajdjb_new")
public class DocJieShouAnJianDengJiBiaoNew {
	
	@Id
	private String docid;	//文书编号								
	
	private String caseid;	//案件编号	
	
	private String personid;	//人员编号			
	
	private String unitid;	//单位编号					
	
	@Column(length = 2000)
	private String multiid;	//多功能综合字段			
	
	private String inputpsn;	//录入人			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;	//录入时间						
	
	private String modifiedpsn;	//修改人				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedtime;	//修改时间			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date districttime;	//入地市库时间			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date provincetime;	//入省库时间			
	
	private String deletag;	//删除标识				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date hck_rksj;			//			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date hck_gxsj;		//				
	
	@Column(length = 100)
	private String a1;	//编号
	
	@Column(length = 100)
	private String a2;	//姓名
	
	@Column(length = 10)
	private String a3;	//性别
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a4;		//出生日期
	
	@Column(length = 100)
	private String a5;	//联系方式
	
	@Column(length = 100)
	private String a6;	//身份证号码
	
	private String a7;		//工作单位
	
	private String a8;	//现住址
	
	private String a9;	//案件来源
	
	@Column(length = 100)
	private String a10;	//接警人1
	
	@Column(length = 100)
	private String a11;	//接警人2
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a12;	//作案人姓名3(第一联)
	
	private String a13;	//接警地点
	
	private String a14;	//备注
	
	private String a15;	//告知法律责任
	
	@Column(length = 2000)
	private String a16;	//简要案情
	
	private String a17;	//授受证据情况
	
	private String a18;	//A18
	
	@Column(length = 100)
	private String a19;	//办案人
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a20;	//受案登记时间
	
	private String a21;	//部门领导签章
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a22;	//受案审批时间
	
	@Column(length = 100)
	private String a23;	//移送单位
	
	@Column(length = 100)
	private String a24;	//承办人
	
	private String a25;	//填表单位(公章)
	
	@Column(length = 100)
	private String a26;	//联系方式
	
	private String a27;	//部门领导意见
	
	private String a30;	//移送单位A30
	
	@Column(length = 100)
	private String a31;	//承办人A31
	
	@Column(length = 100)
	private String a32;	//联系方式A32
	
	@Column(length = 100)
	private String a33;	//单位名称
	
	@Column(length = 100)
	private String a34;	//单位简称
	
	@Column(length = 100)
	private String a35;	//字
	
	@Column(length = 100)
	private String a36;	//年份
	
	@Column(length = 100)
	private String a37;	//号
	
	@Column(length = 100)
	private String a38;	//移送单位X
	
	@Column(length = 100)
	private String a39;	//其他
	
	@Column(length = 100)
	private String a40;	//身份证件种类
	
	@Column(length = 100)
	private String a41;	//受案民警2
	
	@Column(length = 100)
	private String a45;	//领导审批
	
	@Column(length = 100)
	private String a46;	//办案部门负责人

	@Temporal(TemporalType.TIMESTAMP)
	private Date a47;	//办案部门负责人签章日期

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getMultiid() {
		return multiid;
	}

	public void setMultiid(String multiid) {
		this.multiid = multiid;
	}

	public String getInputpsn() {
		return inputpsn;
	}

	public void setInputpsn(String inputpsn) {
		this.inputpsn = inputpsn;
	}

	public Date getInputtime() {
		return inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getModifiedpsn() {
		return modifiedpsn;
	}

	public void setModifiedpsn(String modifiedpsn) {
		this.modifiedpsn = modifiedpsn;
	}

	public Date getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public Date getDistricttime() {
		return districttime;
	}

	public void setDistricttime(Date districttime) {
		this.districttime = districttime;
	}

	public Date getProvincetime() {
		return provincetime;
	}

	public void setProvincetime(Date provincetime) {
		this.provincetime = provincetime;
	}

	public String getDeletag() {
		return deletag;
	}

	public void setDeletag(String deletag) {
		this.deletag = deletag;
	}

	public Date getHck_rksj() {
		return hck_rksj;
	}

	public void setHck_rksj(Date hck_rksj) {
		this.hck_rksj = hck_rksj;
	}

	public Date getHck_gxsj() {
		return hck_gxsj;
	}

	public void setHck_gxsj(Date hck_gxsj) {
		this.hck_gxsj = hck_gxsj;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public Date getA4() {
		return a4;
	}

	public void setA4(Date a4) {
		this.a4 = a4;
	}

	public String getA5() {
		return a5;
	}

	public void setA5(String a5) {
		this.a5 = a5;
	}

	public String getA6() {
		return a6;
	}

	public void setA6(String a6) {
		this.a6 = a6;
	}

	public String getA7() {
		return a7;
	}

	public void setA7(String a7) {
		this.a7 = a7;
	}

	public String getA8() {
		return a8;
	}

	public void setA8(String a8) {
		this.a8 = a8;
	}

	public String getA9() {
		return a9;
	}

	public void setA9(String a9) {
		this.a9 = a9;
	}

	public String getA10() {
		return a10;
	}

	public void setA10(String a10) {
		this.a10 = a10;
	}

	public String getA11() {
		return a11;
	}

	public void setA11(String a11) {
		this.a11 = a11;
	}

	public Date getA12() {
		return a12;
	}

	public void setA12(Date a12) {
		this.a12 = a12;
	}

	public String getA13() {
		return a13;
	}

	public void setA13(String a13) {
		this.a13 = a13;
	}

	public String getA14() {
		return a14;
	}

	public void setA14(String a14) {
		this.a14 = a14;
	}

	public String getA15() {
		return a15;
	}

	public void setA15(String a15) {
		this.a15 = a15;
	}

	public String getA16() {
		return a16;
	}

	public void setA16(String a16) {
		this.a16 = a16;
	}

	public String getA17() {
		return a17;
	}

	public void setA17(String a17) {
		this.a17 = a17;
	}

	public String getA18() {
		return a18;
	}

	public void setA18(String a18) {
		this.a18 = a18;
	}

	public String getA19() {
		return a19;
	}

	public void setA19(String a19) {
		this.a19 = a19;
	}

	public Date getA20() {
		return a20;
	}

	public void setA20(Date a20) {
		this.a20 = a20;
	}

	public String getA21() {
		return a21;
	}

	public void setA21(String a21) {
		this.a21 = a21;
	}

	public Date getA22() {
		return a22;
	}

	public void setA22(Date a22) {
		this.a22 = a22;
	}

	public String getA23() {
		return a23;
	}

	public void setA23(String a23) {
		this.a23 = a23;
	}

	public String getA24() {
		return a24;
	}

	public void setA24(String a24) {
		this.a24 = a24;
	}

	public String getA25() {
		return a25;
	}

	public void setA25(String a25) {
		this.a25 = a25;
	}

	public String getA26() {
		return a26;
	}

	public void setA26(String a26) {
		this.a26 = a26;
	}

	public String getA27() {
		return a27;
	}

	public void setA27(String a27) {
		this.a27 = a27;
	}

	public String getA30() {
		return a30;
	}

	public void setA30(String a30) {
		this.a30 = a30;
	}

	public String getA31() {
		return a31;
	}

	public void setA31(String a31) {
		this.a31 = a31;
	}

	public String getA32() {
		return a32;
	}

	public void setA32(String a32) {
		this.a32 = a32;
	}

	public String getA33() {
		return a33;
	}

	public void setA33(String a33) {
		this.a33 = a33;
	}

	public String getA34() {
		return a34;
	}

	public void setA34(String a34) {
		this.a34 = a34;
	}

	public String getA35() {
		return a35;
	}

	public void setA35(String a35) {
		this.a35 = a35;
	}

	public String getA36() {
		return a36;
	}

	public void setA36(String a36) {
		this.a36 = a36;
	}

	public String getA37() {
		return a37;
	}

	public void setA37(String a37) {
		this.a37 = a37;
	}

	public String getA38() {
		return a38;
	}

	public void setA38(String a38) {
		this.a38 = a38;
	}

	public String getA39() {
		return a39;
	}

	public void setA39(String a39) {
		this.a39 = a39;
	}

	public String getA40() {
		return a40;
	}

	public void setA40(String a40) {
		this.a40 = a40;
	}

	public String getA41() {
		return a41;
	}

	public void setA41(String a41) {
		this.a41 = a41;
	}

	public String getA45() {
		return a45;
	}

	public void setA45(String a45) {
		this.a45 = a45;
	}

	public String getA46() {
		return a46;
	}

	public void setA46(String a46) {
		this.a46 = a46;
	}

	public Date getA47() {
		return a47;
	}

	public void setA47(Date a47) {
		this.a47 = a47;
	}
	
	
}
