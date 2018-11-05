package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 立案决定书
 * @author chengkai
 */
@Entity
@Table(name = "t_ajgl_doc_lajds_new")
public class DocLiAnJueDingShuNew {
	
	@Id
	private String docid;	//文书编号								
	
	private String caseid;	//案件编号	
	
	@Column(length = 100)
	private String a1;	//机关名称(第一联)					
	
	@Column(length = 100)
	private String a2;	//机关简称(第一联)			 	
	
	@Column(length = 100)
	private String a3; //单位简称(第一联)						
	
	@Column(length = 100)
	private String a4;	//字号(第一联)					
	
	@Column(length = 20)
	private String a5;	//年份(第一联)					
	
	@Column(length = 11)
	private Integer a6;	//文书号(第一联)					
	
	@Column(length = 100)
	private String a7;	//案件名称(第一联) 				
	
	@Column(length = 100)
	private String a8;	//案件编号(第一联)				
	
	@Column(length = 100)
	private String a9;	//犯罪嫌疑人(第一联)				
	
	@Column(length = 100)
	private String a10;	//犯罪嫌疑人性别(第一联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a11;	//犯罪嫌疑人出生日期(第一联)					
	
	private String a12;	//犯罪嫌疑人住址(第一联)					
	
	private String a13;	//犯罪嫌疑人单位及职业(第一联)					
	
	@Column(length = 100)
	private String a14;	//批准人(第一联)				
	
	@Column(length = 100)
	private String a17;	//办案人A17(第一联)					
	
	@Column(length = 100)
	private String a16;	//办案人A16(第一联)				
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a15;	//批准人时间(第一联)				
	
	@Column(length = 100)
	private String a18;	//办案单位(第一联)			
	
	@Column(length = 100)
	private String a20;	//填发人(第一联)			
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date a19;	//填发时间(第一联)			
	
	@Column(length = 100)
	private String b1;	//机关名称(第二联)					
	
	@Column(length = 100)
	private String b2;	//机关简称(第二联)			 	
	
	@Column(length = 100)
	private String b3;	//单位简称(第二联)			
	
	@Column(length = 100)
	private String b4;	//字号(第二联)				
	
	@Column(length = 50)
	private String b5;	//年份(第二联)					
	
	@Column(length = 11)
	private Integer b6;	//文书号(第二联)						
	
	private String b7;	//第X条(第二联)					
	
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
	
	private String b8;	//决定对XX案件(第二联)					
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date b9;	//日期(第二联)	
	
	private byte[] sphoto;	//省外立案决定书扫描件
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date swlasj;			//省外案件立案时间
	
	private byte[] sphoto1;	//

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

	public Integer getA6() {
		return a6;
	}

	public void setA6(Integer a6) {
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

	public String getA16() {
		return a16;
	}

	public void setA16(String a16) {
		this.a16 = a16;
	}

	public Date getA15() {
		return a15;
	}

	public void setA15(Date a15) {
		this.a15 = a15;
	}

	public String getA18() {
		return a18;
	}

	public void setA18(String a18) {
		this.a18 = a18;
	}

	public String getA20() {
		return a20;
	}

	public void setA20(String a20) {
		this.a20 = a20;
	}

	public Date getA19() {
		return a19;
	}

	public void setA19(Date a19) {
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

	public String getB5() {
		return b5;
	}

	public void setB5(String b5) {
		this.b5 = b5;
	}

	public Integer getB6() {
		return b6;
	}

	public void setB6(Integer b6) {
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

	public Date getB9() {
		return b9;
	}

	public void setB9(Date b9) {
		this.b9 = b9;
	}

	public Date getSwlasj() {
		return swlasj;
	}

	public void setSwlasj(Date swlasj) {
		this.swlasj = swlasj;
	}

	public byte[] getSphoto() {
		return sphoto;
	}

	public void setSphoto(byte[] sphoto) {
		this.sphoto = sphoto;
	}

	public byte[] getSphoto1() {
		return sphoto1;
	}

	public void setSphoto1(byte[] sphoto1) {
		this.sphoto1 = sphoto1;
	}
	
}
