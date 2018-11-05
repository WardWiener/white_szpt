package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * (行政)公安行政处罚决定书 DOC_XZ0021_NEW
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_gaxzcfjds_new")
public class DocGongAnXingZhengChuFaNew {
	
	@Id
	private String docid;	//文书编号					 							
	
	private String caseid;	//案件编号								
	
	@Column(length = 100)
	private String a1;	//公安机关1						
	
	@Column(length = 100)
	private String a2;	//X公1							
	
	@Column(length = 100)
	private String a3; //决字1						
	
	@Column(length = 100)
	private String a4;	//字1									
	
	@Column(length = 11)
	private Integer a5;	//文书号							
	
	@Column(length = 2000)
	private String a6;	//违法行为人基本信息							
	
	@Column(length = 100)
	private String a7;	//单位简称				 					

	@Column(length = 100)
	private String a8;	//处罚种类								
	
	@Column(length = 2000)
	private String a9;	//现查明原因									
	
	@Column(length = 2000)
	private String a11;	//XX证据证实							
	
	private String a14;	//根据								
	
	@Column(length = 2000)
	private String a17;	//处罚内容								
	
	private String a18;	//执行方式和期限										
	
	@Column(length = 100)
	private String a19;	//向XXX申请							
	
	@Column(length = 100)
	private String a20;	//依法向XX单位申请			
	
	@Column(length = 100)
	private String a21;	//XX分清单			
	
	@Column(length = 100)
	private String a22;	//XX分清单		
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a23;	//公安机关印章日期			
	
	@Column(length = 2000)
	private String a24;	//公安机关印章		
	
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
	private String b1;	//公安机关2					
	
	@Column(length = 50)
	private String b2;	//X公2					
	
	@Column(length = 50)
	private String b3;	//决字2				
	
	@Column(length = 50)
	private String b4;	//字2				
	
	@Column(length = 50)
	private String b5;	//号2				
	
	@Column(length = 100)
	private String b7;	//机关简称2					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b10;	//公安机关名称、印章及决定日期		
	
	@Column(length = 100)
	private String c1;	//公安机关名称3			
	
	@Column(length = 100)
	private String c1_1;	//编号1			
	
	@Column(length = 100)
	private String c2_1;	//名称1			
	
	@Column(length = 50)
	private String c3_1;	//数量1			
	
	@Column(length = 100)
	private String c4_1;	//特征1			
	
	@Column(length = 2000)
	private String c5_1;	//备注1			
	
	@Column(length = 100)
	private String c1_2;	//编号2			
	
	@Column(length = 100)
	private String c2_2;	//名称2			
	
	@Column(length = 50)
	private String c3_2;	//数量2			
	
	@Column(length = 100)
	private String c4_2;	//特征2			
	
	@Column(length = 2000)
	private String c5_2;	//备注2			
	
	@Column(length = 100)
	private String c1_3;	//编号3			
	
	@Column(length = 100)
	private String c2_3;	//名称3			
	
	@Column(length = 50)
	private String c3_3;	//数量3			
	
	@Column(length = 100)
	private String c4_3;	//特征3			
	
	@Column(length = 2000)
	private String c5_3;	//备注3			
	
	@Column(length = 100)
	private String c1_4;	//编号4			
	
	@Column(length = 100)
	private String c2_4;	//名称4			
	
	@Column(length = 50)
	private String c3_4;	//数量4			
	
	@Column(length = 100)
	private String c4_4;	//特征4			
	
	@Column(length = 2000)
	private String c5_4;	//备注4			
	
	@Column(length = 100)
	private String c1_5;	//编号5			
	
	@Column(length = 100)
	private String c2_5;	//名称5			
	
	@Column(length = 50)
	private String c3_5;	//数量5			
	
	@Column(length = 100)
	private String c4_5;	//特征5			
	
	@Column(length = 2000)
	private String c5_5;	//备注5			
	
	@Column(length = 100)
	private String c1_6;	//编号6			
	
	@Column(length = 100)
	private String c2_6;	//名称6			
	
	@Column(length = 50)
	private String c3_6;	//数量6			
	
	@Column(length = 100)
	private String c4_6;	//特征6			
	
	@Column(length = 2000)
	private String c5_6;	//备注6			
	
	@Column(length = 100)
	private String c1_7;	//编号7			
	
	@Column(length = 100)
	private String c2_7;	//名称7			
	
	@Column(length = 50)
	private String c3_7;	//数量7			
	
	@Column(length = 100)
	private String c4_7;	//特征7			
	
	@Column(length = 2000)
	private String c5_7;	//备注7			
	
	@Column(length = 100)
	private String c1_8;	//编号8			
	
	@Column(length = 100)
	private String c2_8;	//名称8			
	
	@Column(length = 50)
	private String c3_8;	//数量8			
	
	@Column(length = 100)
	private String c4_8;	//特征8			
	
	@Column(length = 2000)
	private String c5_8;	//备注8			
	
	@Column(length = 100)
	private String c1_9;	//编号9			
	
	@Column(length = 100)
	private String c2_9;	//名称9			
	
	@Column(length = 50)
	private String c3_9;	//数量9			
	
	@Column(length = 100)
	private String c4_9;	//特征9			
	
	@Column(length = 2000)
	private String c5_9;	//备注9			
	
	@Column(length = 100)
	private String c1_10;	//编号10			
	
	@Column(length = 100)
	private String c2_10;	//名称10			
	
	@Column(length = 50)
	private String c3_10;	//数量10			
	
	@Column(length = 100)
	private String c4_10;	//特征10			
	
	@Column(length = 2000)
	private String c5_10;	//备注10			
	
	@Column(length = 100)
	private String c1_11;	//编号11			
	
	@Column(length = 100)
	private String c2_11;	//名称11			
	
	@Column(length = 50)
	private String c3_11;	//数量11			
	
	@Column(length = 100)
	private String c4_11;	//特征11			
	
	@Column(length = 2000)
	private String c5_11;	//备注11			
	
	@Column(length = 100)
	private String c1_12;	//编号12			
	
	@Column(length = 100)
	private String c2_12;	//名称12			
	
	@Column(length = 50)
	private String c3_12;	//数量12			
	
	@Column(length = 100)
	private String c4_12;	//特征12			
	
	@Column(length = 2000)
	private String c5_12;	//备注12			
	
	@Column(length = 100)
	private String c1_13;	//编号13			
	
	@Column(length = 100)
	private String c2_13;	//名称13			
	
	@Column(length = 50)
	private String c3_13;	//数量13			
	
	@Column(length = 100)
	private String c4_13;	//特征13			
	
	@Column(length = 2000)
	private String c5_13;	//备注13			
	
	@Column(length = 100)
	private String c1_14;	//编号14			
	
	@Column(length = 100)
	private String c2_14;	//名称14			
	
	@Column(length = 50)
	private String c3_14;	//数量14			
	
	@Column(length = 100)
	private String c4_14;	//特征14			
	
	@Column(length = 2000)
	private String c5_14;	//备注14			
	
	@Column(length = 100)
	private String c1_15;	//编号15			
	
	@Column(length = 100)
	private String c2_15;	//名称15			
	
	@Column(length = 50)
	private String c3_15;	//数量15			
	
	@Column(length = 100)
	private String c4_15;	//特征15			
	
	@Column(length = 2000)
	private String c5_15;	//备注15			
	
	private String c6_1;	//被处罚人			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c6_2;	//被处罚人日期			
	
	private String c7_1;	//保管人			
	
	private String c8_1;	//办案民警			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c8_2;	//受案民警日期			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date c7_2;	//保管人日期			
	
	@Column(length = 100)
	private String b11;	//单位简称2			
	
	@Column(length = 100)
	private String a25;	//办案单位			

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

	public Integer getA5() {
		return a5;
	}

	public void setA5(Integer a5) {
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

	public String getB7() {
		return b7;
	}

	public void setB7(String b7) {
		this.b7 = b7;
	}

	public Date getB10() {
		return b10;
	}

	public void setB10(Date b10) {
		this.b10 = b10;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC1_1() {
		return c1_1;
	}

	public void setC1_1(String c1_1) {
		this.c1_1 = c1_1;
	}

	public String getC2_1() {
		return c2_1;
	}

	public void setC2_1(String c2_1) {
		this.c2_1 = c2_1;
	}

	public String getC3_1() {
		return c3_1;
	}

	public void setC3_1(String c3_1) {
		this.c3_1 = c3_1;
	}

	public String getC4_1() {
		return c4_1;
	}

	public void setC4_1(String c4_1) {
		this.c4_1 = c4_1;
	}

	public String getC5_1() {
		return c5_1;
	}

	public void setC5_1(String c5_1) {
		this.c5_1 = c5_1;
	}

	public String getC1_2() {
		return c1_2;
	}

	public void setC1_2(String c1_2) {
		this.c1_2 = c1_2;
	}

	public String getC2_2() {
		return c2_2;
	}

	public void setC2_2(String c2_2) {
		this.c2_2 = c2_2;
	}

	public String getC3_2() {
		return c3_2;
	}

	public void setC3_2(String c3_2) {
		this.c3_2 = c3_2;
	}

	public String getC4_2() {
		return c4_2;
	}

	public void setC4_2(String c4_2) {
		this.c4_2 = c4_2;
	}

	public String getC5_2() {
		return c5_2;
	}

	public void setC5_2(String c5_2) {
		this.c5_2 = c5_2;
	}

	public String getC1_3() {
		return c1_3;
	}

	public void setC1_3(String c1_3) {
		this.c1_3 = c1_3;
	}

	public String getC2_3() {
		return c2_3;
	}

	public void setC2_3(String c2_3) {
		this.c2_3 = c2_3;
	}

	public String getC3_3() {
		return c3_3;
	}

	public void setC3_3(String c3_3) {
		this.c3_3 = c3_3;
	}

	public String getC4_3() {
		return c4_3;
	}

	public void setC4_3(String c4_3) {
		this.c4_3 = c4_3;
	}

	public String getC5_3() {
		return c5_3;
	}

	public void setC5_3(String c5_3) {
		this.c5_3 = c5_3;
	}

	public String getC1_4() {
		return c1_4;
	}

	public void setC1_4(String c1_4) {
		this.c1_4 = c1_4;
	}

	public String getC2_4() {
		return c2_4;
	}

	public void setC2_4(String c2_4) {
		this.c2_4 = c2_4;
	}

	public String getC3_4() {
		return c3_4;
	}

	public void setC3_4(String c3_4) {
		this.c3_4 = c3_4;
	}

	public String getC4_4() {
		return c4_4;
	}

	public void setC4_4(String c4_4) {
		this.c4_4 = c4_4;
	}

	public String getC5_4() {
		return c5_4;
	}

	public void setC5_4(String c5_4) {
		this.c5_4 = c5_4;
	}

	public String getC1_5() {
		return c1_5;
	}

	public void setC1_5(String c1_5) {
		this.c1_5 = c1_5;
	}

	public String getC2_5() {
		return c2_5;
	}

	public void setC2_5(String c2_5) {
		this.c2_5 = c2_5;
	}

	public String getC3_5() {
		return c3_5;
	}

	public void setC3_5(String c3_5) {
		this.c3_5 = c3_5;
	}

	public String getC4_5() {
		return c4_5;
	}

	public void setC4_5(String c4_5) {
		this.c4_5 = c4_5;
	}

	public String getC5_5() {
		return c5_5;
	}

	public void setC5_5(String c5_5) {
		this.c5_5 = c5_5;
	}

	public String getC1_6() {
		return c1_6;
	}

	public void setC1_6(String c1_6) {
		this.c1_6 = c1_6;
	}

	public String getC2_6() {
		return c2_6;
	}

	public void setC2_6(String c2_6) {
		this.c2_6 = c2_6;
	}

	public String getC3_6() {
		return c3_6;
	}

	public void setC3_6(String c3_6) {
		this.c3_6 = c3_6;
	}

	public String getC4_6() {
		return c4_6;
	}

	public void setC4_6(String c4_6) {
		this.c4_6 = c4_6;
	}

	public String getC5_6() {
		return c5_6;
	}

	public void setC5_6(String c5_6) {
		this.c5_6 = c5_6;
	}

	public String getC1_7() {
		return c1_7;
	}

	public void setC1_7(String c1_7) {
		this.c1_7 = c1_7;
	}

	public String getC2_7() {
		return c2_7;
	}

	public void setC2_7(String c2_7) {
		this.c2_7 = c2_7;
	}

	public String getC3_7() {
		return c3_7;
	}

	public void setC3_7(String c3_7) {
		this.c3_7 = c3_7;
	}

	public String getC4_7() {
		return c4_7;
	}

	public void setC4_7(String c4_7) {
		this.c4_7 = c4_7;
	}

	public String getC5_7() {
		return c5_7;
	}

	public void setC5_7(String c5_7) {
		this.c5_7 = c5_7;
	}

	public String getC1_8() {
		return c1_8;
	}

	public void setC1_8(String c1_8) {
		this.c1_8 = c1_8;
	}

	public String getC2_8() {
		return c2_8;
	}

	public void setC2_8(String c2_8) {
		this.c2_8 = c2_8;
	}

	public String getC3_8() {
		return c3_8;
	}

	public void setC3_8(String c3_8) {
		this.c3_8 = c3_8;
	}

	public String getC4_8() {
		return c4_8;
	}

	public void setC4_8(String c4_8) {
		this.c4_8 = c4_8;
	}

	public String getC5_8() {
		return c5_8;
	}

	public void setC5_8(String c5_8) {
		this.c5_8 = c5_8;
	}

	public String getC1_9() {
		return c1_9;
	}

	public void setC1_9(String c1_9) {
		this.c1_9 = c1_9;
	}

	public String getC2_9() {
		return c2_9;
	}

	public void setC2_9(String c2_9) {
		this.c2_9 = c2_9;
	}

	public String getC3_9() {
		return c3_9;
	}

	public void setC3_9(String c3_9) {
		this.c3_9 = c3_9;
	}

	public String getC4_9() {
		return c4_9;
	}

	public void setC4_9(String c4_9) {
		this.c4_9 = c4_9;
	}

	public String getC5_9() {
		return c5_9;
	}

	public void setC5_9(String c5_9) {
		this.c5_9 = c5_9;
	}

	public String getC1_10() {
		return c1_10;
	}

	public void setC1_10(String c1_10) {
		this.c1_10 = c1_10;
	}

	public String getC2_10() {
		return c2_10;
	}

	public void setC2_10(String c2_10) {
		this.c2_10 = c2_10;
	}

	public String getC3_10() {
		return c3_10;
	}

	public void setC3_10(String c3_10) {
		this.c3_10 = c3_10;
	}

	public String getC4_10() {
		return c4_10;
	}

	public void setC4_10(String c4_10) {
		this.c4_10 = c4_10;
	}

	public String getC5_10() {
		return c5_10;
	}

	public void setC5_10(String c5_10) {
		this.c5_10 = c5_10;
	}

	public String getC1_11() {
		return c1_11;
	}

	public void setC1_11(String c1_11) {
		this.c1_11 = c1_11;
	}

	public String getC2_11() {
		return c2_11;
	}

	public void setC2_11(String c2_11) {
		this.c2_11 = c2_11;
	}

	public String getC3_11() {
		return c3_11;
	}

	public void setC3_11(String c3_11) {
		this.c3_11 = c3_11;
	}

	public String getC4_11() {
		return c4_11;
	}

	public void setC4_11(String c4_11) {
		this.c4_11 = c4_11;
	}

	public String getC5_11() {
		return c5_11;
	}

	public void setC5_11(String c5_11) {
		this.c5_11 = c5_11;
	}

	public String getC1_12() {
		return c1_12;
	}

	public void setC1_12(String c1_12) {
		this.c1_12 = c1_12;
	}

	public String getC2_12() {
		return c2_12;
	}

	public void setC2_12(String c2_12) {
		this.c2_12 = c2_12;
	}

	public String getC3_12() {
		return c3_12;
	}

	public void setC3_12(String c3_12) {
		this.c3_12 = c3_12;
	}

	public String getC4_12() {
		return c4_12;
	}

	public void setC4_12(String c4_12) {
		this.c4_12 = c4_12;
	}

	public String getC5_12() {
		return c5_12;
	}

	public void setC5_12(String c5_12) {
		this.c5_12 = c5_12;
	}

	public String getC1_13() {
		return c1_13;
	}

	public void setC1_13(String c1_13) {
		this.c1_13 = c1_13;
	}

	public String getC2_13() {
		return c2_13;
	}

	public void setC2_13(String c2_13) {
		this.c2_13 = c2_13;
	}

	public String getC3_13() {
		return c3_13;
	}

	public void setC3_13(String c3_13) {
		this.c3_13 = c3_13;
	}

	public String getC4_13() {
		return c4_13;
	}

	public void setC4_13(String c4_13) {
		this.c4_13 = c4_13;
	}

	public String getC5_13() {
		return c5_13;
	}

	public void setC5_13(String c5_13) {
		this.c5_13 = c5_13;
	}

	public String getC1_14() {
		return c1_14;
	}

	public void setC1_14(String c1_14) {
		this.c1_14 = c1_14;
	}

	public String getC2_14() {
		return c2_14;
	}

	public void setC2_14(String c2_14) {
		this.c2_14 = c2_14;
	}

	public String getC3_14() {
		return c3_14;
	}

	public void setC3_14(String c3_14) {
		this.c3_14 = c3_14;
	}

	public String getC4_14() {
		return c4_14;
	}

	public void setC4_14(String c4_14) {
		this.c4_14 = c4_14;
	}

	public String getC5_14() {
		return c5_14;
	}

	public void setC5_14(String c5_14) {
		this.c5_14 = c5_14;
	}

	public String getC1_15() {
		return c1_15;
	}

	public void setC1_15(String c1_15) {
		this.c1_15 = c1_15;
	}

	public String getC2_15() {
		return c2_15;
	}

	public void setC2_15(String c2_15) {
		this.c2_15 = c2_15;
	}

	public String getC3_15() {
		return c3_15;
	}

	public void setC3_15(String c3_15) {
		this.c3_15 = c3_15;
	}

	public String getC4_15() {
		return c4_15;
	}

	public void setC4_15(String c4_15) {
		this.c4_15 = c4_15;
	}

	public String getC5_15() {
		return c5_15;
	}

	public void setC5_15(String c5_15) {
		this.c5_15 = c5_15;
	}

	public String getC6_1() {
		return c6_1;
	}

	public void setC6_1(String c6_1) {
		this.c6_1 = c6_1;
	}

	public Date getC6_2() {
		return c6_2;
	}

	public void setC6_2(Date c6_2) {
		this.c6_2 = c6_2;
	}

	public String getC7_1() {
		return c7_1;
	}

	public void setC7_1(String c7_1) {
		this.c7_1 = c7_1;
	}

	public String getC8_1() {
		return c8_1;
	}

	public void setC8_1(String c8_1) {
		this.c8_1 = c8_1;
	}

	public Date getC8_2() {
		return c8_2;
	}

	public void setC8_2(Date c8_2) {
		this.c8_2 = c8_2;
	}

	public Date getC7_2() {
		return c7_2;
	}

	public void setC7_2(Date c7_2) {
		this.c7_2 = c7_2;
	}

	public String getB11() {
		return b11;
	}

	public void setB11(String b11) {
		this.b11 = b11;
	}

	public String getA25() {
		return a25;
	}

	public void setA25(String a25) {
		this.a25 = a25;
	}
	
	
	
}
