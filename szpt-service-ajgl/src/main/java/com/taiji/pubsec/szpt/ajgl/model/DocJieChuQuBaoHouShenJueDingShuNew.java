package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 解除取保候审决定书/通知书 doc_xs018_new
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_jcqbhsjds_new")
public class DocJieChuQuBaoHouShenJueDingShuNew {
	
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
	
	private String a1;	//单位名称(第一联)
	
	private String a2;	//机关名称(第一联)
	
	private String a3;	//单位简称(第一联)
	
	private String a4;	//字号(第一联)
	
	private String a5;	//年份(第一联)
	
	private String a6;	//文书号(第一联)
	
	private String a7;	//案件名称(第一联)
	
	private String a8;	//案件编号(第一联)
	
	private String a9;	//被取保候审人(第一联)
	
	private String a10;	//被取保候审人性别(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a11;	//被取保候审人出生日期(第一联)
	
	private String a12;	//住址(第一联)
	
	private String a13;	//取保方式(第一联)
	
	private String a14;	//执行机关(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a15;	//取保候审决定时间(第一联)
	
	private String a16;	//解除原因(第一联)
	
	private String a17;	//批准人(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a18;	//批准时间(第一联)
	
	private String a19;	//办案人A19(第一联)
	
	private String a20;	//办案单位(第一联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a21;	//填发时间(第一联)
	
	private String a22;	//填发人(第一联)
	
	private String b1;	//单位名称(第二联)
	
	private String b2;	//机关名称(第二联)
	
	private String b3;	//单位简称(第二联)
	
	private String b4;	//字号(第二联)
	
	private String b5;	//年份(第二联)
	
	private String b6;	//文书号(第二联)
	
	private String b7;	//XX名称(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b8;	//于日期(第二联)
	
	private String b9;	//犯罪嫌疑人(第二联)
	
	private String b10;	//现因XXX(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b11;	//日期(第二联)
	
	private String c1;	//单位名称(第三联)
	
	private String c2;	//机关名称(第三联)
	
	private String c3;	//单位简称(第三联)
	
	private String c4;	//字号(第三联)
	
	private String c5;	//年份(第三联)
	
	private String c6;	//文书号(第三联)
	
	private String c7;	//被取保候审人(第三联)
	
	private String c8;	//被取保候审人性别(第三联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c9;	//被取保候审人出生日期(第三联)
	
	private String c11;	//单位及职业(第三联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c12;	//于日期(第三联)
	
	private String c13;	//犯罪嫌疑人(第三联)
	
	private String c14;	//现因XXX(第三联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c15;	//日期(第三联)
	
	private String d1;	//单位名称(第四联)
	
	private String d2;	//机关名称(第四联)
	
	private String d3;	//单位简称(第四联)
	
	private String d4;	//字号(第四联)
	
	private String d5;	//年份(第四联)
	
	private String d6;	//文书号(第四联)
	
	private String d7;	//被取保候审人(第四联)
	
	private String d8;	//被取保候审人性别(第四联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date d9;	//被取保候审人出生日期(第四联)

	private String d10;	//住址(第四联)
	
	private String d11;	//单位及职业(第四联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date d12;	//于日期(第四联)
	
	private String d13;	//犯罪嫌疑人(第四联)
	
	private String d14;	//现因XXX(第四联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date d15;	//日期(第四联)
	
	private String e1;	//单位名称(第五联)
	
	private String e2;	//机关名称(第五联)
	
	private String e3;	//单位简称(第五联)
	
	private String e4;	//字号(第五联)
	
	private String e5;	//年份(第五联)
	
	private String e6;	//文书号(第五联)
	
	private String e7;	//XXX名称第五联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date e8;	//于日期(第五联)
	
	private String e9;	//犯罪嫌疑人(第五联)
	
	private String e10;	//犯罪嫌疑人性别(第五联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date e11;	//犯罪嫌疑人出生日期(第五联)
	
	private String e12;	//住址(第五联)
	
	private String e13;	//现因XXX(第五联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date e14;	//日期(第五联)
	
	private String a23;	//办案人2
	
	private String b12;	//被取保候审人性别(第二联)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b13;	//被取保候审人出生日期(第二联)
	
	private String b14;	//被取保候审人住址(第二联)
	
	private String c10;	//住址(第三联)

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

	public String getA4() {
		return a4;
	}

	public void setA4(String a4) {
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

	public Date getA11() {
		return a11;
	}

	public void setA11(Date a11) {
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

	public Date getA15() {
		return a15;
	}

	public void setA15(Date a15) {
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

	public Date getA18() {
		return a18;
	}

	public void setA18(Date a18) {
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

	public Date getA21() {
		return a21;
	}

	public void setA21(Date a21) {
		this.a21 = a21;
	}

	public String getA22() {
		return a22;
	}

	public void setA22(String a22) {
		this.a22 = a22;
	}

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getB2() {
		return b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
	}

	public String getB3() {
		return b3;
	}

	public void setB3(String b3) {
		this.b3 = b3;
	}

	public String getB4() {
		return b4;
	}

	public void setB4(String b4) {
		this.b4 = b4;
	}

	public String getB5() {
		return b5;
	}

	public void setB5(String b5) {
		this.b5 = b5;
	}

	public String getB6() {
		return b6;
	}

	public void setB6(String b6) {
		this.b6 = b6;
	}

	public String getB7() {
		return b7;
	}

	public void setB7(String b7) {
		this.b7 = b7;
	}

	public Date getB8() {
		return b8;
	}

	public void setB8(Date b8) {
		this.b8 = b8;
	}

	public String getB9() {
		return b9;
	}

	public void setB9(String b9) {
		this.b9 = b9;
	}

	public String getB10() {
		return b10;
	}

	public void setB10(String b10) {
		this.b10 = b10;
	}

	public Date getB11() {
		return b11;
	}

	public void setB11(Date b11) {
		this.b11 = b11;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	public String getC8() {
		return c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	public Date getC9() {
		return c9;
	}

	public void setC9(Date c9) {
		this.c9 = c9;
	}

	public String getC11() {
		return c11;
	}

	public void setC11(String c11) {
		this.c11 = c11;
	}

	public Date getC12() {
		return c12;
	}

	public void setC12(Date c12) {
		this.c12 = c12;
	}

	public String getC13() {
		return c13;
	}

	public void setC13(String c13) {
		this.c13 = c13;
	}

	public String getC14() {
		return c14;
	}

	public void setC14(String c14) {
		this.c14 = c14;
	}

	public Date getC15() {
		return c15;
	}

	public void setC15(Date c15) {
		this.c15 = c15;
	}

	public String getD1() {
		return d1;
	}

	public void setD1(String d1) {
		this.d1 = d1;
	}

	public String getD2() {
		return d2;
	}

	public void setD2(String d2) {
		this.d2 = d2;
	}

	public String getD3() {
		return d3;
	}

	public void setD3(String d3) {
		this.d3 = d3;
	}

	public String getD4() {
		return d4;
	}

	public void setD4(String d4) {
		this.d4 = d4;
	}

	public String getD5() {
		return d5;
	}

	public void setD5(String d5) {
		this.d5 = d5;
	}

	public String getD6() {
		return d6;
	}

	public void setD6(String d6) {
		this.d6 = d6;
	}

	public String getD7() {
		return d7;
	}

	public void setD7(String d7) {
		this.d7 = d7;
	}

	public String getD8() {
		return d8;
	}

	public void setD8(String d8) {
		this.d8 = d8;
	}

	public Date getD9() {
		return d9;
	}

	public void setD9(Date d9) {
		this.d9 = d9;
	}

	public String getD10() {
		return d10;
	}

	public void setD10(String d10) {
		this.d10 = d10;
	}

	public String getD11() {
		return d11;
	}

	public void setD11(String d11) {
		this.d11 = d11;
	}

	public Date getD12() {
		return d12;
	}

	public void setD12(Date d12) {
		this.d12 = d12;
	}

	public String getD13() {
		return d13;
	}

	public void setD13(String d13) {
		this.d13 = d13;
	}

	public String getD14() {
		return d14;
	}

	public void setD14(String d14) {
		this.d14 = d14;
	}

	public Date getD15() {
		return d15;
	}

	public void setD15(Date d15) {
		this.d15 = d15;
	}

	public String getE1() {
		return e1;
	}

	public void setE1(String e1) {
		this.e1 = e1;
	}

	public String getE2() {
		return e2;
	}

	public void setE2(String e2) {
		this.e2 = e2;
	}

	public String getE3() {
		return e3;
	}

	public void setE3(String e3) {
		this.e3 = e3;
	}

	public String getE4() {
		return e4;
	}

	public void setE4(String e4) {
		this.e4 = e4;
	}

	public String getE5() {
		return e5;
	}

	public void setE5(String e5) {
		this.e5 = e5;
	}

	public String getE6() {
		return e6;
	}

	public void setE6(String e6) {
		this.e6 = e6;
	}

	public String getE7() {
		return e7;
	}

	public void setE7(String e7) {
		this.e7 = e7;
	}

	public Date getE8() {
		return e8;
	}

	public void setE8(Date e8) {
		this.e8 = e8;
	}

	public String getE9() {
		return e9;
	}

	public void setE9(String e9) {
		this.e9 = e9;
	}

	public String getE10() {
		return e10;
	}

	public void setE10(String e10) {
		this.e10 = e10;
	}

	public Date getE11() {
		return e11;
	}

	public void setE11(Date e11) {
		this.e11 = e11;
	}

	public String getE12() {
		return e12;
	}

	public void setE12(String e12) {
		this.e12 = e12;
	}

	public String getE13() {
		return e13;
	}

	public void setE13(String e13) {
		this.e13 = e13;
	}

	public Date getE14() {
		return e14;
	}

	public void setE14(Date e14) {
		this.e14 = e14;
	}

	public String getA23() {
		return a23;
	}

	public void setA23(String a23) {
		this.a23 = a23;
	}

	public String getB12() {
		return b12;
	}

	public void setB12(String b12) {
		this.b12 = b12;
	}

	public Date getB13() {
		return b13;
	}

	public void setB13(Date b13) {
		this.b13 = b13;
	}

	public String getB14() {
		return b14;
	}

	public void setB14(String b14) {
		this.b14 = b14;
	}

	public String getC10() {
		return c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
	}
	
	
	
}
