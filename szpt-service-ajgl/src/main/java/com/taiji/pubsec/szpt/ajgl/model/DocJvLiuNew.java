package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * （刑事）拘留证 DOC_XS023_NEW
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_jlz_new")
public class DocJvLiuNew {
	
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
	
	private String a12;	//住址(第一联)					
	
	private String a13;	//单位及职业(第一联)					
	
	private String a14;	//单位名称(第一联)				
	
	private String a17;	//执行人A17(第一联)					
	
	private String a15;	//批准人(第一联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a16;	//批准时间(第一联)				
	
	private String a18;	//执行人A18(第一联)			
	
	private String a19;	//办案单位(第一联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a20;	//填发时间(第一联)			
	
	private String a21;	//填发人(第一联)				
	
	private String b1;	//单位名称(第二联)					
	
	private String b2;	//机关名称(第二联)			 	
	
	private String b3;	//单位简称(第二联)			
	
	private String b4;	//字号(第二联)				
	
	private String b13;	//送XXXX(第二联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b14;	//日期(第二联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b15;	//于日期B15(第二联)			
	
	private String b16;	//被拘留人(第二联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b17;	//于日期B17(第二联)			
	
	private String b18;	//被拘留人B18(第二联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b19;	//于日期B19(第二联)			
	
	private String b5;	//年份(第二联)					
	
	private String b6;	//文书号(第二联)						
	
	private String b7;	//第XX条(第二联)					
	
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
	
	private String b8;	//人员(第二联)					
	
	private String b9;	//犯罪嫌疑人(第二联)			
	
	private String b10;	//执行人A18(第一联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b11;	//出生日期(第二联)			
	
	private String b12;	//住址(第二联)					
	
	private String c1;	//单位名称(第三联)					
	
	private String c2;	//机关名称(第三联)			 
	
	private String c3;	//单位简称(第三联)			
	
	private String c4;	//字号(第三联)				
	
	private String c13;	//送XXXX(第三联)					
	
	private String c5;	//年份(第三联)						
	
	private String c6;	//文书号(第三联)						
	
	private String c7;	//文书号(第三联)						
	
	private String c8;	//人员(第三联)				
	
	private String c9;	//犯罪嫌疑人(第三联)			
	
	private String c17;	//是否属于律师会见需经许可的案件(第三联)			
	
	private String c12;	//住址(第三联)						
	
	private String c10;	//性别(第三联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c11;	//出生日期(第三联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c14;	//日期(第三联)				
	
	private String c15;	//涉嫌罪名(第三联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c16;	//执行时间(第三联)			
	
	private String checkbox2;	//否(第三联)		
	
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

	public String getA17() {
		return a17;
	}

	public void setA17(String a17) {
		this.a17 = a17;
	}

	public String getA15() {
		return a15;
	}

	public void setA15(String a15) {
		this.a15 = a15;
	}

	public Date getA16() {
		return a16;
	}

	public void setA16(Date a16) {
		this.a16 = a16;
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

	public String getB13() {
		return b13;
	}

	public void setB13(String b13) {
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

	public String getB16() {
		return b16;
	}

	public void setB16(String b16) {
		this.b16 = b16;
	}

	public Date getB17() {
		return b17;
	}

	public void setB17(Date b17) {
		this.b17 = b17;
	}

	public String getB18() {
		return b18;
	}

	public void setB18(String b18) {
		this.b18 = b18;
	}

	public Date getB19() {
		return b19;
	}

	public void setB19(Date b19) {
		this.b19 = b19;
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

	public String getB8() {
		return b8;
	}

	public void setB8(String b8) {
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

	public String getB12() {
		return b12;
	}

	public void setB12(String b12) {
		this.b12 = b12;
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

	public String getC13() {
		return c13;
	}

	public void setC13(String c13) {
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

	public String getC17() {
		return c17;
	}

	public void setC17(String c17) {
		this.c17 = c17;
	}

	public String getC12() {
		return c12;
	}

	public void setC12(String c12) {
		this.c12 = c12;
	}

	public String getC10() {
		return c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
	}

	public Date getC11() {
		return c11;
	}

	public void setC11(Date c11) {
		this.c11 = c11;
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

	public String getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(String checkbox2) {
		this.checkbox2 = checkbox2;
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
