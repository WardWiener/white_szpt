package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 证据保全决定书（无证据保全清单） DOC_ZJBQJDS_NEW
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_zjbqjds_new")
public class DocZhengJvBaoQuanJueDingNew {
	
	@Id
	private String docid;	//文书编号					 							
	
	private String caseid;	//案件编号	
	
	private String inputpsnId;	//录入人id
	
	@Column(length = 100)
	private String a1;	//机关名称(第二联)						
	
	@Column(length = 100)
	private String a2;	//单位名称(第二联)				
	
	@Column(length = 100)
	private String a3; //单位简称(第二联)						
	
	@Column(length = 50)
	private String a4;	//字号(第二联)									
	
	@Column(length = 10)
	private String a5;	//年份(第二联)							
	
	@Column(length = 50)
	private String a6;	//文书号(第二联)							
	
	@Column(length = 2000)
	private String a7;	//当事人简介				 					

	private String a8;	//现住址及联系方式(第一联)								
	
	@Column(length = 2000)
	private String a9;	//因调查(第一联)									
	
	@Column(length = 50)
	private String a11;	//第XX条							
	
	private String a10;	//根据(第一联)										
	
	@Column(length = 50)
	private String a12;	//第XX款										
	
	@Column(length = 50)
	private String a13;	//第XX项											
	
	@Column(length = 50)
	private String a15;	//根据XX				
	
	@Column(length = 50)
	private String a16;	//扣留XX日											
	
	@Column(length = 10)
	private String a14;	//根据XX								
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a17;	//自XX起								
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a18;	//自XX起										
	
	@Column(length = 50)
	private String a19;	//查封XX日								
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a20;	//自XX起2				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a21;	//自XX止2				
	
	@Column(length = 100)
	private String a22;	//根据XX2			
	
	@Column(length = 100)
	private String a23;	//根据XX			
	
	@Column(length = 100)
	private String a24;	//保存XX日			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a25;	//自XX起3				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a26;	//自XX止3				
	
	private String a27;	//保存地点为XX			
	
	private String a28;	//根据XX3			
	
	private String a29;	//向XX申请行政复议			
	
	private String a30;	//向XX人民法院申请			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a31;	//印章日期				
	
	private String a32;	//决定对XX				
	
	private String a33;	//根据XX			
	
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
	
	@Column(length = 20)
	private String b1;	//编号A1					
	
	@Column(length = 100)
	private String b16;	//名称A16				
	
	@Column(length = 20)
	private String b31;	//数量A31				
	
	private String b46;	//特征A46				
	
	@Column(length = 2000)
	private String b61;	//备注A61				
	
	@Column(length = 20)
	private String b2;	//编号A2					
	
	@Column(length = 100)
	private String b17;	//名称A17				
	
	@Column(length = 20)
	private String b32;	//数量A32				
	
	private String b47;	//特征A47				
	
	@Column(length = 2000)
	private String b62;	//备注A62				
	
	@Column(length = 20)
	private String b3;	//编号A3					
	
	@Column(length = 100)
	private String b18;	//名称A18				
	
	@Column(length = 20)
	private String b33;	//数量A33				
	
	private String b48;	//特征A48				
	
	@Column(length = 2000)
	private String b63;	//备注A63				
	
	@Column(length = 20)
	private String b4;	//编号A4					
	
	@Column(length = 100)
	private String b19;	//名称A19				
	
	@Column(length = 20)
	private String b34;	//数量A34				
	
	private String b49;	//特征A49				
	
	@Column(length = 2000)
	private String b64;	//备注A64				
	
	@Column(length = 20)
	private String b5;	//编号A5					
	
	@Column(length = 100)
	private String b20;	//名称A20				
	
	@Column(length = 20)
	private String b35;	//数量A35				
	
	private String b50;	//特征A50				
	
	@Column(length = 2000)
	private String b65;	//备注A65				
	
	@Column(length = 2000)
	private String b71;	//备注A71				
	
	@Column(length = 20)
	private String b12;	//编号A12					
	
	@Column(length = 100)
	private String b27;	//名称A27				
	
	@Column(length = 20)
	private String b42;	//数量A42				
	
	private String b57;	//特征A57				
	
	@Column(length = 2000)
	private String b72;	//备注A72				
	
	@Column(length = 20)
	private String b13;	//编号A13					
	
	@Column(length = 100)
	private String b28;	//名称A28				
	
	@Column(length = 20)
	private String b43;	//数量A43				
	
	private String b58;	//特征A58				
	
	@Column(length = 2000)
	private String b73;	//备注A73				
	
	@Column(length = 20)
	private String b14;	//编号A14					
	
	@Column(length = 100)
	private String b29;	//名称A29				
	
	@Column(length = 20)
	private String b44;	//数量A44				
	
	private String b59;	//特征A59				
	
	@Column(length = 2000)
	private String b74;	//备注A74				
	
	@Column(length = 20)
	private String b15;	//编号A15					
	
	@Column(length = 100)
	private String b30;	//名称A30				
	
	@Column(length = 20)
	private String b45;	//数量A45				
	
	private String b60;	//特征A60				
	
	@Column(length = 2000)
	private String b75;	//备注A75				
	
	@Column(length = 50)
	private String b76;	//提交人				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b77;	//持有人日期			
	
	@Column(length = 50)
	private String b78;	//保管人				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b79;	//保管人日期			
	
	@Column(length = 50)
	private String b80;	//受案民警				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b81;	//受案民警日期			
	
	@Column(length = 20)
	private String b6;	//数量A36			
	
	@Column(length = 100)
	private String b21;	//名称A21			
	
	@Column(length = 20)
	private String b36;	//编号1			
	
	private String b51;	//特征A51			
	
	@Column(length = 2000)
	private String b66;	//备注A66			
	
	@Column(length = 20)
	private String b7;	//编号A7			
	
	@Column(length = 100)
	private String b22;	//名称A22			
	
	@Column(length = 20)
	private String b37;	//数量A37			
	
	private String b52;	//特征A52			
	
	@Column(length = 2000)
	private String b67;	//备注A67			
	
	@Column(length = 20)
	private String b8;	//编号A8			
	
	@Column(length = 100)
	private String b23;	//名称A23			
	
	@Column(length = 20)
	private String b38;	//数量A38			
	
	private String b53;	//特征A53			
	
	@Column(length = 2000)
	private String b68;	//备注A68			
	
	@Column(length = 20)
	private String b9;	//编号A9			
	
	@Column(length = 100)
	private String b24;	//名称A24			
	
	@Column(length = 20)
	private String b39;	//数量A39			
	
	private String b54;	//特征A54			
	
	@Column(length = 2000)
	private String b69;	//备注A69			
	
	@Column(length = 20)
	private String b10;	//编号A10			
	
	@Column(length = 100)
	private String b25;	//名称A25			
	
	@Column(length = 20)
	private String b40;	//数量A40			
	
	private String b55;	//特征A55			
	
	@Column(length = 2000)
	private String b70;	//备注A70			
	
	@Column(length = 20)
	private String b11;	//编号A11			
	
	@Column(length = 100)
	private String b26;	//名称A26			
	
	@Column(length = 20)
	private String b41;	//数量A41			
	
	private String b56;	//特征A56			

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

	public String getA14() {
		return a14;
	}

	public void setA14(String a14) {
		this.a14 = a14;
	}

	public Date getA17() {
		return a17;
	}

	public void setA17(Date a17) {
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

	public Date getA20() {
		return a20;
	}

	public void setA20(Date a20) {
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

	public Date getA25() {
		return a25;
	}

	public void setA25(Date a25) {
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

	public String getA29() {
		return a29;
	}

	public void setA29(String a29) {
		this.a29 = a29;
	}

	public String getA30() {
		return a30;
	}

	public void setA30(String a30) {
		this.a30 = a30;
	}

	public Date getA31() {
		return a31;
	}

	public void setA31(Date a31) {
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

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getB16() {
		return b16;
	}

	public void setB16(String b16) {
		this.b16 = b16;
	}

	public String getB31() {
		return b31;
	}

	public void setB31(String b31) {
		this.b31 = b31;
	}

	public String getB46() {
		return b46;
	}

	public void setB46(String b46) {
		this.b46 = b46;
	}

	public String getB61() {
		return b61;
	}

	public void setB61(String b61) {
		this.b61 = b61;
	}

	public String getB2() {
		return b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
	}

	public String getB17() {
		return b17;
	}

	public void setB17(String b17) {
		this.b17 = b17;
	}

	public String getB32() {
		return b32;
	}

	public void setB32(String b32) {
		this.b32 = b32;
	}

	public String getB47() {
		return b47;
	}

	public void setB47(String b47) {
		this.b47 = b47;
	}

	public String getB62() {
		return b62;
	}

	public void setB62(String b62) {
		this.b62 = b62;
	}

	public String getB3() {
		return b3;
	}

	public void setB3(String b3) {
		this.b3 = b3;
	}

	public String getB18() {
		return b18;
	}

	public void setB18(String b18) {
		this.b18 = b18;
	}

	public String getB33() {
		return b33;
	}

	public void setB33(String b33) {
		this.b33 = b33;
	}

	public String getB48() {
		return b48;
	}

	public void setB48(String b48) {
		this.b48 = b48;
	}

	public String getB63() {
		return b63;
	}

	public void setB63(String b63) {
		this.b63 = b63;
	}

	public String getB4() {
		return b4;
	}

	public void setB4(String b4) {
		this.b4 = b4;
	}

	public String getB19() {
		return b19;
	}

	public void setB19(String b19) {
		this.b19 = b19;
	}

	public String getB34() {
		return b34;
	}

	public void setB34(String b34) {
		this.b34 = b34;
	}

	public String getB49() {
		return b49;
	}

	public void setB49(String b49) {
		this.b49 = b49;
	}

	public String getB64() {
		return b64;
	}

	public void setB64(String b64) {
		this.b64 = b64;
	}

	public String getB5() {
		return b5;
	}

	public void setB5(String b5) {
		this.b5 = b5;
	}

	public String getB20() {
		return b20;
	}

	public void setB20(String b20) {
		this.b20 = b20;
	}

	public String getB35() {
		return b35;
	}

	public void setB35(String b35) {
		this.b35 = b35;
	}

	public String getB50() {
		return b50;
	}

	public void setB50(String b50) {
		this.b50 = b50;
	}

	public String getB65() {
		return b65;
	}

	public void setB65(String b65) {
		this.b65 = b65;
	}

	public String getB71() {
		return b71;
	}

	public void setB71(String b71) {
		this.b71 = b71;
	}

	public String getB12() {
		return b12;
	}

	public void setB12(String b12) {
		this.b12 = b12;
	}

	public String getB27() {
		return b27;
	}

	public void setB27(String b27) {
		this.b27 = b27;
	}

	public String getB42() {
		return b42;
	}

	public void setB42(String b42) {
		this.b42 = b42;
	}

	public String getB57() {
		return b57;
	}

	public void setB57(String b57) {
		this.b57 = b57;
	}

	public String getB72() {
		return b72;
	}

	public void setB72(String b72) {
		this.b72 = b72;
	}

	public String getB13() {
		return b13;
	}

	public void setB13(String b13) {
		this.b13 = b13;
	}

	public String getB28() {
		return b28;
	}

	public void setB28(String b28) {
		this.b28 = b28;
	}

	public String getB43() {
		return b43;
	}

	public void setB43(String b43) {
		this.b43 = b43;
	}

	public String getB58() {
		return b58;
	}

	public void setB58(String b58) {
		this.b58 = b58;
	}

	public String getB73() {
		return b73;
	}

	public void setB73(String b73) {
		this.b73 = b73;
	}

	public String getB14() {
		return b14;
	}

	public void setB14(String b14) {
		this.b14 = b14;
	}

	public String getB29() {
		return b29;
	}

	public void setB29(String b29) {
		this.b29 = b29;
	}

	public String getB44() {
		return b44;
	}

	public void setB44(String b44) {
		this.b44 = b44;
	}

	public String getB59() {
		return b59;
	}

	public void setB59(String b59) {
		this.b59 = b59;
	}

	public String getB74() {
		return b74;
	}

	public void setB74(String b74) {
		this.b74 = b74;
	}

	public String getB15() {
		return b15;
	}

	public void setB15(String b15) {
		this.b15 = b15;
	}

	public String getB30() {
		return b30;
	}

	public void setB30(String b30) {
		this.b30 = b30;
	}

	public String getB45() {
		return b45;
	}

	public void setB45(String b45) {
		this.b45 = b45;
	}

	public String getB60() {
		return b60;
	}

	public void setB60(String b60) {
		this.b60 = b60;
	}

	public String getB75() {
		return b75;
	}

	public void setB75(String b75) {
		this.b75 = b75;
	}

	public String getB76() {
		return b76;
	}

	public void setB76(String b76) {
		this.b76 = b76;
	}

	public Date getB77() {
		return b77;
	}

	public void setB77(Date b77) {
		this.b77 = b77;
	}

	public String getB78() {
		return b78;
	}

	public void setB78(String b78) {
		this.b78 = b78;
	}

	public Date getB79() {
		return b79;
	}

	public void setB79(Date b79) {
		this.b79 = b79;
	}

	public String getB80() {
		return b80;
	}

	public void setB80(String b80) {
		this.b80 = b80;
	}

	public Date getB81() {
		return b81;
	}

	public void setB81(Date b81) {
		this.b81 = b81;
	}

	public String getB6() {
		return b6;
	}

	public void setB6(String b6) {
		this.b6 = b6;
	}

	public String getB21() {
		return b21;
	}

	public void setB21(String b21) {
		this.b21 = b21;
	}

	public String getB36() {
		return b36;
	}

	public void setB36(String b36) {
		this.b36 = b36;
	}

	public String getB51() {
		return b51;
	}

	public void setB51(String b51) {
		this.b51 = b51;
	}

	public String getB66() {
		return b66;
	}

	public void setB66(String b66) {
		this.b66 = b66;
	}

	public String getB7() {
		return b7;
	}

	public void setB7(String b7) {
		this.b7 = b7;
	}

	public String getB22() {
		return b22;
	}

	public void setB22(String b22) {
		this.b22 = b22;
	}

	public String getB37() {
		return b37;
	}

	public void setB37(String b37) {
		this.b37 = b37;
	}

	public String getB52() {
		return b52;
	}

	public void setB52(String b52) {
		this.b52 = b52;
	}

	public String getB67() {
		return b67;
	}

	public void setB67(String b67) {
		this.b67 = b67;
	}

	public String getB8() {
		return b8;
	}

	public void setB8(String b8) {
		this.b8 = b8;
	}

	public String getB23() {
		return b23;
	}

	public void setB23(String b23) {
		this.b23 = b23;
	}

	public String getB38() {
		return b38;
	}

	public void setB38(String b38) {
		this.b38 = b38;
	}

	public String getB53() {
		return b53;
	}

	public void setB53(String b53) {
		this.b53 = b53;
	}

	public String getB68() {
		return b68;
	}

	public void setB68(String b68) {
		this.b68 = b68;
	}

	public String getB9() {
		return b9;
	}

	public void setB9(String b9) {
		this.b9 = b9;
	}

	public String getB24() {
		return b24;
	}

	public void setB24(String b24) {
		this.b24 = b24;
	}

	public String getB39() {
		return b39;
	}

	public void setB39(String b39) {
		this.b39 = b39;
	}

	public String getB54() {
		return b54;
	}

	public void setB54(String b54) {
		this.b54 = b54;
	}

	public String getB69() {
		return b69;
	}

	public void setB69(String b69) {
		this.b69 = b69;
	}

	public String getB10() {
		return b10;
	}

	public void setB10(String b10) {
		this.b10 = b10;
	}

	public String getB25() {
		return b25;
	}

	public void setB25(String b25) {
		this.b25 = b25;
	}

	public String getB40() {
		return b40;
	}

	public void setB40(String b40) {
		this.b40 = b40;
	}

	public String getB55() {
		return b55;
	}

	public void setB55(String b55) {
		this.b55 = b55;
	}

	public String getB70() {
		return b70;
	}

	public void setB70(String b70) {
		this.b70 = b70;
	}

	public String getB11() {
		return b11;
	}

	public void setB11(String b11) {
		this.b11 = b11;
	}

	public String getB26() {
		return b26;
	}

	public void setB26(String b26) {
		this.b26 = b26;
	}

	public String getB41() {
		return b41;
	}

	public void setB41(String b41) {
		this.b41 = b41;
	}

	public String getB56() {
		return b56;
	}

	public void setB56(String b56) {
		this.b56 = b56;
	}

	public String getInputpsnId() {
		return inputpsnId;
	}

	public void setInputpsnId(String inputpsnId) {
		this.inputpsnId = inputpsnId;
	}
	
	
	
	
}
