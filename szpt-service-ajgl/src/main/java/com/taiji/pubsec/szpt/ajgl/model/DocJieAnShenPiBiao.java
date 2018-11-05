package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * (行政)结案审批表 XZ_SP_072
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_jaspb")
public class DocJieAnShenPiBiao {

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
	
	@Column(length = 2000)
	private String personnid;	//
	
	@Column(length = 80)
	private String a101; 	//机关简称(第一联)
	
	@Column(length = 80)
	private String a102; 	//单位简称(第一联)
	
	@Column(length = 80)
	private String a1; 	//年份(第一联)

	@Column(length = 1)
	private Integer a2;	//文书号(第一联) 
	
	@Column(length = 200)
	private String a3; 	//案由(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a4;	//发案时间(第一联) 
	
	@Column(length = 80)
	private String a5; 	//案件文号(第一联)
	
	@Column(length = 100)
	private String a6; 	//姓名(第一联)
	
	@Column(length = 10)
	private String a7; 	//性别(第一联)
	
	@Column(length = 10)
	private String a8; 	//民族(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a9;	//出生日期(第一联)
	
	@Column(length = 50)
	private String a10; 	//文化程度(第一联)
	
	@Column(length = 50)
	private String a11; 	//身份证件种类及号码(第一联)
	
	private String a12; 	//现住址(第一联)
	
	private String a13; 	//户籍所在地(第一联)
	
	private String a14; 	//工作单位(第一联)
	
	@Column(length = 2000)
	private String a15;	//违法犯罪记录(第一联)
	
	@Column(length = 80)
	private String a16; 	//名称(第一联)
	
	@Column(length = 50)
	private String a17; 	//法定代表人(第一联)
	
	private String a18; 	//地址(第一联)
	
	@Column(length = 800)
	private String a19; 	//同案其他人(第一联)
	
	@Column(length = 2000)
	private String a20;	//违法事实及证据(第一联)
	
	@Column(length = 2000)
	private String a21;	//承办人意见(第二联)
	
	@Column(length = 50)
	private String a22;	//承办人(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a23;	//日期(第二联)
	
	private String a24;	//承办单位意见(第二联)
	
	@Column(length = 50)
	private String a25;	//负责人1(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a26;	//日期1(第二联)
	
	@Column(length = 1000)
	private String a27;	//审核部门意见(第二联)
	
	@Column(length = 50)
	private String a28;	//负责人2(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a29;	//日期2(第二联)
	
	@Column(length = 1000)
	private String a30;	//领导审批意见(第二联)
	
	@Column(length = 50)
	private String a31;	//领导(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a32;	//日期3(第二联)
	
	@Column(length = 30)
	private String a33;	//承办人1
	
	@Column(length = 16)
	private String a34;	//部门负责人2
	
	@Column(length = 20)
	private String a103;	//文书简称(第一联)
	
	@Column(length = 1000)
	private String a38;	//法制科员民警意见
	
	@Column(length = 16)
	private String a39;	//法制科民警
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a40;	//法制科民警审核日期
	
	@Column(length = 2000)
	private String a41;	//市级法制员审批意见
	
	@Column(length = 16)
	private String a42;	//市级法制员签字
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a43;	//市级法制员签字日期

	@Column(length = 2000)
	private String a35;	//科所队法制员意见
	
	@Column(length = 16)
	private String a36;	//科所队法制员签字
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a37;	//科所队法制员签字日期

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

	public String getPersonnid() {
		return personnid;
	}

	public void setPersonnid(String personnid) {
		this.personnid = personnid;
	}

	public String getA101() {
		return a101;
	}

	public void setA101(String a101) {
		this.a101 = a101;
	}

	public String getA102() {
		return a102;
	}

	public void setA102(String a102) {
		this.a102 = a102;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public Integer getA2() {
		return a2;
	}

	public void setA2(Integer a2) {
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

	public Date getA9() {
		return a9;
	}

	public void setA9(Date a9) {
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

	public String getA12() {
		return a12;
	}

	public void setA12(String a12) {
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

	public String getA20() {
		return a20;
	}

	public void setA20(String a20) {
		this.a20 = a20;
	}

	public String getA21() {
		return a21;
	}

	public void setA21(String a21) {
		this.a21 = a21;
	}

	public String getA22() {
		return a22;
	}

	public void setA22(String a22) {
		this.a22 = a22;
	}

	public Date getA23() {
		return a23;
	}

	public void setA23(Date a23) {
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

	public Date getA26() {
		return a26;
	}

	public void setA26(Date a26) {
		this.a26 = a26;
	}

	public String getA27() {
		return a27;
	}

	public void setA27(String a27) {
		this.a27 = a27;
	}

	public String getA28() {
		return a28;
	}

	public void setA28(String a28) {
		this.a28 = a28;
	}

	public Date getA29() {
		return a29;
	}

	public void setA29(Date a29) {
		this.a29 = a29;
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

	public Date getA32() {
		return a32;
	}

	public void setA32(Date a32) {
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

	public String getA103() {
		return a103;
	}

	public void setA103(String a103) {
		this.a103 = a103;
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

	public Date getA40() {
		return a40;
	}

	public void setA40(Date a40) {
		this.a40 = a40;
	}

	public String getA41() {
		return a41;
	}

	public void setA41(String a41) {
		this.a41 = a41;
	}

	public String getA42() {
		return a42;
	}

	public void setA42(String a42) {
		this.a42 = a42;
	}

	public Date getA43() {
		return a43;
	}

	public void setA43(Date a43) {
		this.a43 = a43;
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

	public Date getA37() {
		return a37;
	}

	public void setA37(Date a37) {
		this.a37 = a37;
	}
	
	
}
