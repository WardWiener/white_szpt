package com.taiji.pubsec.szpt.ajgl.bean;


/**
 * （刑事）取保候审决定书/执行通知书 DOC_XS010_NEW
 */
public class DocQuBaoHouShenNewBean {

	private String docid; // 文书编号

	private String caseid; // 案件编号

	private String remark; // 印章

	private String a1; // 单位名称 第一联

	private String a2; // 机关名称 第一联

	private String a3; // 单位简称 第一联

	private String a4; // 字号 第一联

	private String a5; // 年份 第一联

	private String a6; // 文书号 第一联

	private String a7; // 案件名称 第一联

	private String a8; // 案件编号 第一联

	private String a9; // 被取保候审人 第一联

	private String a10; // 被取保候审人性别 第一联

	private String a11; // 被取保候审出生日期(第一联

	private String a12; // 取保原因(第一联)

	private String a13; // 起算时间(第一联)

	private String a14; // 保证人(第一联)

	private String a16; // 保证人出生日期(第一联)

	private String a15; // 保证人性别(第一联)

	private String a17; // 住址(第一联)

	private String a18; // 单位(第一联)

	private String a19; // 保证金(第一联)

	private String a20; // 办案单位(第一联)

	private String a21; // 执行机关(第一联)

	private String a22; // 批准人(第一联)

	private String a23; // 批准时间(第一联)

	private String a24; // 填发时间(第一联)

	private String a25; // 填发人(第一联)

	private String b1; // 单位名称 第二联

	private String b2; // 机关名称 第二联

	private String b3; // 单位简称 第二联

	private String b4; // 字号 第二联

	private String b5; // 年份 第二联

	private String b6; // 文书号 第二联

	private String b7; // 犯罪嫌疑人名称(第二联)

	private String b8; // 性别(第二联)

	private String personid; // 人员编号

	private String unitid; // 单位编号

	private String multiid; // 多功能综合字段

	private String cssfont; // 字体

	private String inputpsn; // 录入人

	private String inputtime; // 录入时间

	private String modifiedpsn; // 修改人

	private String modifiedtime; // 修改时间

	private String districttime; // 入地市库时间

	private String provincetime; // 入省库时间

	private String tag; // 上报标识

	private String modifiedtag; // 修改标识

	private String deletag; // 删除标识

	private String lyds; //

	private String hck_rksj; //

	private String hck_gxsj; //

	private String b9; // 出生日期(第二联)

	private String b10; // 住址(第二联)

	private String b11; // 单位及职业(第二联)

	private String b12; // 联系方式(第二联)

	private String b13; // 我局正 侦查的XXX案(第二联)

	private String b14; // 犯罪嫌疑人B14(第二联)

	private String b15; // 第XX条(第二联)

	private String b16; // 第XX款(第二联)

	private String b17; // 期限从XX起(第二联)

	private String b18; // 证人(第二联)

	private String b19; // 交纳保证金(第二联)

	private String b20; // 日期(第二联)

	private String c1; // 年份(第三联)

	private String c2; // 机关名称(第三联)

	private String c3; // 单位简称(第三联)

	private String c4; // 字号(第三联)

	private String c5; // 年份(第三联)

	private String c6; // 文书号(第三联)

	private String c7; // 犯罪嫌疑人(第三联)

	private String c8; // 性别(第三联)

	private String c9; // 出生日期(第三联)

	private String c10; // 住址(第三联)

	private String c11; // 单位及职业(第三联)

	private String c12; // 联系方式(第三联)

	private String c13; // 我局正侦查XX案(第三联)

	private String c14; // 单犯罪嫌疑人C14(第三联)

	private String c15; // 第XXX条(第三联)

	private String c16; // 第XX款(第三联)

	private String c17; // 期限从XX起(第三联)

	private String c18; // 证人(第三联)

	private String c19; // 交纳保证金(第三联)

	private String c20; // 日期(第三联)

	private String d1; // 单位名称(第四联)

	private String d2; // 机关名称(第四联)

	private String d3; // 单位简称(第四联)

	private String d4; // 字号(第四联)

	private String d5; // 年份(第四联)

	private String d6; // 文书号(第四联)

	private String d7; // XX单位名称(第四联)

	private String d8; // 因XXX(第四联)

	private String d9; // 犯罪嫌疑人(第四联)

	private String d10; // 性别(第四联)

	private String d11; // 出生日期(第四联)

	private String d12; // 住址(第四联)

	private String d13; // 单位及职业(第四联)

	private String d14; // 取保期限从XX起(第四联)

	private String d15; // 保证人(第四联)

	private String d16; // 保证人(第四联)

	private String d17; // 日期(第四联)

	private String d18; // XX案(第四联)

	private String d19; // 联系方式(第四联)

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getA25() {
		return a25;
	}

	public void setA25(String a25) {
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

	public String getCssfont() {
		return cssfont;
	}

	public void setCssfont(String cssfont) {
		this.cssfont = cssfont;
	}

	public String getInputpsn() {
		return inputpsn;
	}

	public void setInputpsn(String inputpsn) {
		this.inputpsn = inputpsn;
	}

	public String getInputtime() {
		return inputtime;
	}

	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}

	public String getModifiedpsn() {
		return modifiedpsn;
	}

	public void setModifiedpsn(String modifiedpsn) {
		this.modifiedpsn = modifiedpsn;
	}

	public String getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getDistricttime() {
		return districttime;
	}

	public void setDistricttime(String districttime) {
		this.districttime = districttime;
	}

	public String getProvincetime() {
		return provincetime;
	}

	public void setProvincetime(String provincetime) {
		this.provincetime = provincetime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModifiedtag() {
		return modifiedtag;
	}

	public void setModifiedtag(String modifiedtag) {
		this.modifiedtag = modifiedtag;
	}

	public String getDeletag() {
		return deletag;
	}

	public void setDeletag(String deletag) {
		this.deletag = deletag;
	}

	public String getLyds() {
		return lyds;
	}

	public void setLyds(String lyds) {
		this.lyds = lyds;
	}

	public String getHck_rksj() {
		return hck_rksj;
	}

	public void setHck_rksj(String hck_rksj) {
		this.hck_rksj = hck_rksj;
	}

	public String getHck_gxsj() {
		return hck_gxsj;
	}

	public void setHck_gxsj(String hck_gxsj) {
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

	public String getC12() {
		return c12;
	}

	public void setC12(String c12) {
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

	public String getC15() {
		return c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	public String getC16() {
		return c16;
	}

	public void setC16(String c16) {
		this.c16 = c16;
	}

	public String getC17() {
		return c17;
	}

	public void setC17(String c17) {
		this.c17 = c17;
	}

	public String getC18() {
		return c18;
	}

	public void setC18(String c18) {
		this.c18 = c18;
	}

	public String getC19() {
		return c19;
	}

	public void setC19(String c19) {
		this.c19 = c19;
	}

	public String getC20() {
		return c20;
	}

	public void setC20(String c20) {
		this.c20 = c20;
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

	public String getD9() {
		return d9;
	}

	public void setD9(String d9) {
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

	public String getD12() {
		return d12;
	}

	public void setD12(String d12) {
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

	public String getD15() {
		return d15;
	}

	public void setD15(String d15) {
		this.d15 = d15;
	}

	public String getD16() {
		return d16;
	}

	public void setD16(String d16) {
		this.d16 = d16;
	}

	public String getD17() {
		return d17;
	}

	public void setD17(String d17) {
		this.d17 = d17;
	}

	public String getD18() {
		return d18;
	}

	public void setD18(String d18) {
		this.d18 = d18;
	}

	public String getD19() {
		return d19;
	}

	public void setD19(String d19) {
		this.d19 = d19;
	}
}
