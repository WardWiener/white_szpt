package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * （刑事）延长拘留期限通知书 DOC_XS024_NEW
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_ycjlqxtzs_new")
public class DocYanChangJvLiuQiXianNew {
	
	@Id
	private String docid;	//文书编号					 							
	
	private String caseid;	//案件编号	
	
	private String inputpsnId;	//录入人id
	
	private String a1;	//单位名称(第一联)								
	
	private String a2;	//机关名称(第一联)				 			
	
	private String a3; //单位简称(第一联)								
	
	private String a4;	//字号(第一联)								
	
	private String a5;	//年份(第一联)							
	
	private String a6;	//文书号(第一联)							
	
	private String a7;	//案件名称(第一联)				 					

	private String a8;	//案件编号(第一联)										
	
	private String a9;	//犯罪嫌疑人(第一联)										
	
	private String a10;	//性别(第一联)												
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a11;	//出生日期(第一联)										
	
	private String a12;	//羁押处所(第一联)												
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a13;	//执行拘留时间(第一联)											
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a14;	//延长羁押期限至(第一联)										
	
	private String a16;	//批准人(第一联)												
	
	private String a15;	//延长原因(第一联)										
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a17;	//批准准时间(第一联)										
	
	private String a18;	//办案人A18(第一联)										
	
	private String a19;	//办案人A19(第一联)										
	
	private String a20;	//办案单位(第一联)										
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a21;	//填发时间(第一联)					
	
	private String a22;	//填发人(第一联)					
	
	private String b1;	//单位名称(第二联)								
	
	private String b2;	//机关名称(第二联)					
	
	private String b3;	//单位简称(第二联)					
	
	private String b4;	//字号(第二联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b13;	//于日期(第二联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b14;	//日期(第二联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b15;	//于日期B15(第二联)				
	
	private String b5;	//年份(第二联)													
	
	private String b6;	//文书号(第二联)														
	
	private String b7;	//XX看守所(第二联)				 

	private String b8;	//因XXX(第二联)					
	
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
	
	private String b9;	//第XX款(第二联)					
	
	private String b10;	//犯罪嫌疑人(第二联)				
	
	private String b11;	//性别(第二联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b12;	//出生日期(第二联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b16;	//日期(第二联)					
	
	private String c1;	//单位名称(第三联)													
	
	private String c2;	//机关名称(第三联) 				
	
	private String c3;	//单位简称(第三联) 				
	
	private String c4;	//字号(第三联) 					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c13;	//于XX (第三联)														
	
	private String c5;	//年份(第三联)														
	
	private String c6;	//文书号(第三联)															
	
	private String c7;	//XXX 看守所(第三联)				 
	
	private String c8;	//因XXX(第三联)					
	
	private String c9;	//第XX款(第三联)				
	
	private String c10;	//犯罪嫌疑人(第三联)				
	
	private String c11;	//性别(第三联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c12;	//出生日期(第三联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c14;	//从XX(第三联)					
	
	private String c15;	//至XX(第三联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c16;	//日期(第三联)					
	
	private String remrk;	//						

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

	public Date getA13() {
		return a13;
	}

	public void setA13(Date a13) {
		this.a13 = a13;
	}

	public Date getA14() {
		return a14;
	}

	public void setA14(Date a14) {
		this.a14 = a14;
	}

	public String getA16() {
		return a16;
	}

	public void setA16(String a16) {
		this.a16 = a16;
	}

	public String getA15() {
		return a15;
	}

	public void setA15(String a15) {
		this.a15 = a15;
	}

	public Date getA17() {
		return a17;
	}

	public void setA17(Date a17) {
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

	public Date getB13() {
		return b13;
	}

	public void setB13(Date b13) {
		this.b13 = b13;
	}

	public Date getB14() {
		return b14;
	}

	public void setB14(Date b14) {
		this.b14 = b14;
	}

	public Date getB15() {
		return b15;
	}

	public void setB15(Date b15) {
		this.b15 = b15;
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

	public String getB8() {
		return b8;
	}

	public void setB8(String b8) {
		this.b8 = b8;
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

	public String getB11() {
		return b11;
	}

	public void setB11(String b11) {
		this.b11 = b11;
	}

	public Date getB12() {
		return b12;
	}

	public void setB12(Date b12) {
		this.b12 = b12;
	}

	public Date getB16() {
		return b16;
	}

	public void setB16(Date b16) {
		this.b16 = b16;
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

	public Date getC13() {
		return c13;
	}

	public void setC13(Date c13) {
		this.c13 = c13;
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

	public String getC9() {
		return c9;
	}

	public void setC9(String c9) {
		this.c9 = c9;
	}

	public String getC10() {
		return c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
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

	public Date getC14() {
		return c14;
	}

	public void setC14(Date c14) {
		this.c14 = c14;
	}

	public String getC15() {
		return c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	public Date getC16() {
		return c16;
	}

	public void setC16(Date c16) {
		this.c16 = c16;
	}

	public String getRemrk() {
		return remrk;
	}

	public void setRemrk(String remrk) {
		this.remrk = remrk;
	}

	public String getInputpsnId() {
		return inputpsnId;
	}

	public void setInputpsnId(String inputpsnId) {
		this.inputpsnId = inputpsnId;
	}
	
	
}
