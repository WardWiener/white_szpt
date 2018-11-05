package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 责令社区戒毒 社区康复决定书
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_zlsqjdsqkfjds_new")
public class DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew {
	
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
	
	@Column(length = 100)
	private String a3; //解强戒决字1
	
	@Column(length = 50)
	private String a4; //字1
	
	@Column(length = 50)
	private String a5; //号1
	
	@Column(length = 100)
	private String a6; //违法行为人
	
	@Column(length = 20)
	private String a7; //性别
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a8; //出生日期
	
	@Column(length = 100)
	private String a9; //身份证件种类及号码
	
	private String a10; //户籍所在地
	
	private String a11; //现住址
	
	private String a12; //工作单位
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a14; //强制隔离戒毒期限(开始)
	
	private String a20; //社区戒毒/社区康复地点
	
	@Column(length = 100)
	private String a13; //办案单位
	
	@Column(length = 100)
	private String a17; //承办人
	
	@Column(length = 100)
	private String a18; //批准人
	
	@Column(length = 100)
	private String a19; //填发人
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a25; //填发日期
	
	@Column(length = 100)
	private String b1; //公安机关2

	@Column(length = 100)
	private String b2; //X公
	
	@Column(length = 100)
	private String b3; //收教证字2
	
	@Column(length = 100)
	private String b4; //字2
	
	@Column(length = 11)
	private Integer b5; //号2
	
	private String b6; //被责令社区戒毒/社区康复人
	
	@Column(length = 100)
	private String b7; //性别
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b8; //出生日期
	
	private String b9; //身份证件种类及号码
	
	private String b22; //户籍所在地
	
	private String b26; //现住址

	private String b11; //工作单位
	
	@Column(length = 500)
	private String b10; //因XX
	
	@Column(length = 500)
	private String b13; //以上事实有
	
	@Column(length = 1000)
	private String b18; //《中华人民共和国禁毒法》第三十三条之规定
	
	@Column(length = 1000)
	private String b14; //《中华人民共和国禁毒法》第四十八条之规定
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b15; //延长强制隔离戒毒日期(开始)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b16; //延长强制隔离戒毒(结束)
	
	@Column(length = 100)
	private String b23; //社区康复执行地社区名称(单位)
	
	private String b24; //地址(单位)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b20; //公安机关印章日期
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b21; //被责令社区戒毒/社区康复人签名时间
	
	@Column(length = 100)
	private String a15; //单位简称1
	
	@Column(length = 100)
	private String b17; //单位简称2
	
	@Column(length = 100)
	private String a16; //承办人2
	
	@Column(length = 11)
	private String b27; //违法行为人年龄
	
	private String b28; //六十日内向XX单位名称
	
	private String b29; //三个月内依法向XX单位名称
	
	@Column(length = 20)
	private String b30; //年龄
	
	private String b31; //行为人接受社区康复/社区戒毒

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

	public String getA19() {
		return a19;
	}

	public void setA19(String a19) {
		this.a19 = a19;
	}

	public Date getA25() {
		return a25;
	}

	public void setA25(Date a25) {
		this.a25 = a25;
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

	public String getB22() {
		return b22;
	}

	public void setB22(String b22) {
		this.b22 = b22;
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

	public String getB18() {
		return b18;
	}

	public void setB18(String b18) {
		this.b18 = b18;
	}

	public String getB14() {
		return b14;
	}

	public void setB14(String b14) {
		this.b14 = b14;
	}

	public Date getB15() {
		return b15;
	}

	public void setB15(Date b15) {
		this.b15 = b15;
	}

	public Date getB16() {
		return b16;
	}

	public void setB16(Date b16) {
		this.b16 = b16;
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

	public Date getB20() {
		return b20;
	}

	public void setB20(Date b20) {
		this.b20 = b20;
	}

	public Date getB21() {
		return b21;
	}

	public void setB21(Date b21) {
		this.b21 = b21;
	}

	public String getA15() {
		return a15;
	}

	public void setA15(String a15) {
		this.a15 = a15;
	}

	public String getB17() {
		return b17;
	}

	public void setB17(String b17) {
		this.b17 = b17;
	}

	public String getA16() {
		return a16;
	}

	public void setA16(String a16) {
		this.a16 = a16;
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
	
	
	
}
