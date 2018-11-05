package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 查封/扣押决定书 DOC_XS049_NEW
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_kyjds_new")
public class DocKouYaJueDingNew {
	
	@Id
	private String docid;	//文书编号					 							
	
	private String caseid;	//案件编号								
	
	private String a1;	//单位名称编号(第一联)						
	
	private String a2;	//机关简称（第一联）				
	
	private String a3; //单位简称 第一联						
	
	private String a4;	//字号 第一联									
	
	private String a5;	//年份 第一联							
	
	private String a6;	//文书号 第一联							
	
	private String a7;	//案件名称 第一联				 					

	private String a8;	//案件编号 第一联										
	
	private String a9;	//犯罪嫌疑人(第一联)										
	
	private String a11;	//年龄(第一联)			
	
	private String a10;	//性别(第一联)										
	
	private String a12;	//被查封/扣押单位(第一联)											
	
	private String a13;	//查封/扣押原因 (第一联)											
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a15;	//批准时间(第一联)				
	
	private String a16;	//办案人A16(第一联)											
	
	private String a14;	//批准人(第一联)									
	
	private String a17;	//办案人A17(第一联)								
	
	private String a18;	//办案单位(第一联)										
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a19;	//填发时间(第一联)								
	
	private String a20;	//填发人(第一联)										
	
	private String b1;	//单位名称 第二联								
	
	private String b2;	//机关简称（第二联）					
	
	private String b3;	//案别(第二联)				
	
	private String b4;	//字号(第二联)					
	
	private String b5;	//年份(第二联)													
	
	private String b6;	//文书号(第二联)														
	
	private String b7;	//姓名或单位名称(第二联)				 
	
	private String b8;	//性别(第二联) 						
	
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
	
	private String b9;	//年龄(第二联)									
	
	private String b10;	//身份证件种类及号码(第二联)							
	
	private String b11;	//代表人(第二联)						
	
	private String b12;	//地址及联系方式(第二联)							
	
	private String b13;	//因XXX(第二联)						
	
	private String b14;	//第XX条(第二联)						
	
	private String b15;	//编号B15				
	
	private String b16;	//编号B16					
	
	private String b17;	//编号B17					
	
	private String b18;	//编号B18					
	
	private String b19;	//编号B19				
	
	private String b20;	//编号B20					
	
	private String b21;	//编号B21					
	
	private String b22;	//名称B22					
	
	private String b23;	//名称B23					
	
	private String b24;	//名称B24					
	
	private String b25;	//名称B25					
	
	private String b26;	//名称B26					
	
	private String b27;	//名称B27					
	
	private String b28;	//名称B28					
	
	private String b29;	//规格B29					
	
	private String b30;	//规格B30					
	
	private String b31;	//规格B31					
	
	private String b32;	//规格B32					
	
	private String b33;	//规格B33					
	
	private String b34;	//规格B34					
	
	private String b35;	//规格B35					
	
	private String b36;	//数量B36					
	
	private String b37;	//数量B37					
	
	private String b38;	//数量B38					
	
	private String b39;	//数量B39					
	
	private String b40;	//数量B40					
	
	private String b41;	//数量B41					
	
	private String b42;	//数量B42					
	
	private String b43;	//特征B43				
	
	private String b44;	//特征B44				
	
	private String b45;	//特征B45				
	
	private String b46;	//特征B46				
	
	private String b47;	//特征B47				
	
	private String b48;	//特征B48				
	
	private String b49;	//特征B49				

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

	public String getA11() {
		return a11;
	}

	public void setA11(String a11) {
		this.a11 = a11;
	}

	public String getA10() {
		return a10;
	}

	public void setA10(String a10) {
		this.a10 = a10;
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

	public String getA18() {
		return a18;
	}

	public void setA18(String a18) {
		this.a18 = a18;
	}

	public Date getA19() {
		return a19;
	}

	public void setA19(Date a19) {
		this.a19 = a19;
	}

	public String getA20() {
		return a20;
	}

	public void setA20(String a20) {
		this.a20 = a20;
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

	public String getB12() {
		return b12;
	}

	public void setB12(String b12) {
		this.b12 = b12;
	}

	public String getB13() {
		return b13;
	}

	public void setB13(String b13) {
		this.b13 = b13;
	}

	public String getB14() {
		return b14;
	}

	public void setB14(String b14) {
		this.b14 = b14;
	}

	public String getB15() {
		return b15;
	}

	public void setB15(String b15) {
		this.b15 = b15;
	}

	public String getB16() {
		return b16;
	}

	public void setB16(String b16) {
		this.b16 = b16;
	}

	public String getB17() {
		return b17;
	}

	public void setB17(String b17) {
		this.b17 = b17;
	}

	public String getB18() {
		return b18;
	}

	public void setB18(String b18) {
		this.b18 = b18;
	}

	public String getB19() {
		return b19;
	}

	public void setB19(String b19) {
		this.b19 = b19;
	}

	public String getB20() {
		return b20;
	}

	public void setB20(String b20) {
		this.b20 = b20;
	}

	public String getB21() {
		return b21;
	}

	public void setB21(String b21) {
		this.b21 = b21;
	}

	public String getB22() {
		return b22;
	}

	public void setB22(String b22) {
		this.b22 = b22;
	}

	public String getB23() {
		return b23;
	}

	public void setB23(String b23) {
		this.b23 = b23;
	}

	public String getB24() {
		return b24;
	}

	public void setB24(String b24) {
		this.b24 = b24;
	}

	public String getB25() {
		return b25;
	}

	public void setB25(String b25) {
		this.b25 = b25;
	}

	public String getB26() {
		return b26;
	}

	public void setB26(String b26) {
		this.b26 = b26;
	}

	public String getB27() {
		return b27;
	}

	public void setB27(String b27) {
		this.b27 = b27;
	}

	public String getB28() {
		return b28;
	}

	public void setB28(String b28) {
		this.b28 = b28;
	}

	public String getB29() {
		return b29;
	}

	public void setB29(String b29) {
		this.b29 = b29;
	}

	public String getB30() {
		return b30;
	}

	public void setB30(String b30) {
		this.b30 = b30;
	}

	public String getB31() {
		return b31;
	}

	public void setB31(String b31) {
		this.b31 = b31;
	}

	public String getB32() {
		return b32;
	}

	public void setB32(String b32) {
		this.b32 = b32;
	}

	public String getB33() {
		return b33;
	}

	public void setB33(String b33) {
		this.b33 = b33;
	}

	public String getB34() {
		return b34;
	}

	public void setB34(String b34) {
		this.b34 = b34;
	}

	public String getB35() {
		return b35;
	}

	public void setB35(String b35) {
		this.b35 = b35;
	}

	public String getB36() {
		return b36;
	}

	public void setB36(String b36) {
		this.b36 = b36;
	}

	public String getB37() {
		return b37;
	}

	public void setB37(String b37) {
		this.b37 = b37;
	}

	public String getB38() {
		return b38;
	}

	public void setB38(String b38) {
		this.b38 = b38;
	}

	public String getB39() {
		return b39;
	}

	public void setB39(String b39) {
		this.b39 = b39;
	}

	public String getB40() {
		return b40;
	}

	public void setB40(String b40) {
		this.b40 = b40;
	}

	public String getB41() {
		return b41;
	}

	public void setB41(String b41) {
		this.b41 = b41;
	}

	public String getB42() {
		return b42;
	}

	public void setB42(String b42) {
		this.b42 = b42;
	}

	public String getB43() {
		return b43;
	}

	public void setB43(String b43) {
		this.b43 = b43;
	}

	public String getB44() {
		return b44;
	}

	public void setB44(String b44) {
		this.b44 = b44;
	}

	public String getB45() {
		return b45;
	}

	public void setB45(String b45) {
		this.b45 = b45;
	}

	public String getB46() {
		return b46;
	}

	public void setB46(String b46) {
		this.b46 = b46;
	}

	public String getB47() {
		return b47;
	}

	public void setB47(String b47) {
		this.b47 = b47;
	}

	public String getB48() {
		return b48;
	}

	public void setB48(String b48) {
		this.b48 = b48;
	}

	public String getB49() {
		return b49;
	}

	public void setB49(String b49) {
		this.b49 = b49;
	}
	
	
}
