package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 解除社区戒毒 社区康复通知书
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_jcsqjdsqkftzs_new")
public class DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew {
	
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
	private String a1; //公安机关1
	
	@Column(length = 100)
	private String a2; //X公1
	
	@Column(length = 50)
	private String a3; //社解通字1
	
	@Column(length = 50)
	private String a4; //字1
	
	@Column(length = 50)
	private String a5; //号1
	
	@Column(length = 100)
	private String a6; //被强制隔离戒毒人姓名
	
	@Column(length = 20)
	private String a7; //性别
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a8; //出生日期
	
	private String a9; //身份证件种类及号码
	
	private String a10; //户籍所在地
	
	private String a11; //现住址
	
	private String a12; //办案单位
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a14; //社区戒毒/社区康复期限
	
	private String a20; //社区康复地点
	
	@Column(length = 100)
	private String a13; //单位简称1
	
	@Column(length = 100)
	private String a17; //承办人
	
	@Column(length = 100)
	private String a18; //批准人
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a172; //填发日期
	
	@Column(length = 100)
	private String a19; //填发人
	
	@Column(length = 100)
	private String b1; //公安机关2

	@Column(length = 100)
	private String b2; //X公
	
	@Column(length = 100)
	private String b3; //社解通字2
	
	@Column(length = 50)
	private String b4; //字2
	
	@Column(length = 11)
	private Integer b5; //号2
	
	@Column(length = 100)
	private String b6; //被责令接受社区戒毒人
	
	@Column(length = 50)
	private String b7; //性别
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b8; //出生日期
	
	private String b9; //身份证件种类及号码
	
	private String b26; //现住址

	private String b11; //工作单位
	
	private String b12; //因XX
	
	private String b10; //户籍所在地
	
	@Column(length = 100)
	private String b13; //第X条
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b18; //社区康复(结束)
	
	@Column(length = 100)
	private String b14; //第X款
	
	private String b15; //被XX
	
	private String b16; //社区康复X年
	
	private String b19; //决定书文号
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b20; //社区康复执行地公安机关印章日期
	
	@Column(length = 100)
	private String b21; //单位简称2
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a15; //出生日期（第一联）
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b17; //社区康复(开始)
	
	@Column(length = 100)
	private String a16; //承办人2
	
	@Column(length = 20)
	private String a30; //年龄
	
	private String a31; //工作单位
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a32; //社区戒毒/社区康复期限止
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a33; //日期
	
	private String a23; //社区戒毒/康复决定书文号
	
	private String a38; //社区戒毒/社区康复通知书
	
	private String a34; //社区戒毒
	
	private String a35; //社区康复
	
	private String a36; //社区戒毒1
	
	private String a37; //社区康复1

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

	public Date getA8() {
		return a8;
	}

	public void setA8(Date a8) {
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

	public String getA12() {
		return a12;
	}

	public void setA12(String a12) {
		this.a12 = a12;
	}

	public Date getA14() {
		return a14;
	}

	public void setA14(Date a14) {
		this.a14 = a14;
	}

	public String getA20() {
		return a20;
	}

	public void setA20(String a20) {
		this.a20 = a20;
	}

	public String getA13() {
		return a13;
	}

	public void setA13(String a13) {
		this.a13 = a13;
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

	public Date getA172() {
		return a172;
	}

	public void setA172(Date a172) {
		this.a172 = a172;
	}

	public String getA19() {
		return a19;
	}

	public void setA19(String a19) {
		this.a19 = a19;
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

	public Integer getB5() {
		return b5;
	}

	public void setB5(Integer b5) {
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

	public String getB26() {
		return b26;
	}

	public void setB26(String b26) {
		this.b26 = b26;
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

	public String getB10() {
		return b10;
	}

	public void setB10(String b10) {
		this.b10 = b10;
	}

	public String getB13() {
		return b13;
	}

	public void setB13(String b13) {
		this.b13 = b13;
	}

	public Date getB18() {
		return b18;
	}

	public void setB18(Date b18) {
		this.b18 = b18;
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

	public String getB19() {
		return b19;
	}

	public void setB19(String b19) {
		this.b19 = b19;
	}

	public Date getB20() {
		return b20;
	}

	public void setB20(Date b20) {
		this.b20 = b20;
	}

	public String getB21() {
		return b21;
	}

	public void setB21(String b21) {
		this.b21 = b21;
	}

	public Date getA15() {
		return a15;
	}

	public void setA15(Date a15) {
		this.a15 = a15;
	}

	public Date getB17() {
		return b17;
	}

	public void setB17(Date b17) {
		this.b17 = b17;
	}

	public String getA16() {
		return a16;
	}

	public void setA16(String a16) {
		this.a16 = a16;
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

	public Date getA33() {
		return a33;
	}

	public void setA33(Date a33) {
		this.a33 = a33;
	}

	public String getA23() {
		return a23;
	}

	public void setA23(String a23) {
		this.a23 = a23;
	}

	public String getA38() {
		return a38;
	}

	public void setA38(String a38) {
		this.a38 = a38;
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
	
	
}
