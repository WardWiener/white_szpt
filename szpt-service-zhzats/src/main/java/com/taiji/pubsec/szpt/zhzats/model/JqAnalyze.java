package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
/**
 * 警情分析研判
 * @author white
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_jqfxyp")
public class JqAnalyze {

	@Id
	private String id ;
	
	/**
	 * 警情id
	 */
	@Column(name="zhzats_fact_jq_id")
	@Index(name="index_t_zhzats_jwzh_jqfxyp_zhzats_fact_jq_id")
	private String jqId ;
	
	/**
	 * 受害物品特征
	 */
	@Column(name="shwptz")
	private String materialChara ;
	
	/**
	 * 受侵害方式
	 */
	@Column(name="sqhfs")
	private String harmedWay ;
	
	/**
	 * 逃穿方式
	 */
	@Column(name="tcfs")
	private String runWay ;
	
	/**
	 * 逃穿方向
	 */
	@Column(name="tcfx")
	private String runDirect ;
	
	/**
	 * 嫌疑人发型
	 */
	@Column(name="xyrfx")
	private String suspectHair ;
	
	/**
	 * 嫌疑人肤色
	 */
	@Column(name="xyrfs")
	private String suspectSkin ;
	
	/**
	 * 嫌疑人年龄
	 */
	@Column(name="xyrnl")
	private String suspectAge ;
	
	/**
	 * 嫌疑人其他特征
	 */
	@Column(name="xyrqttz")
	private String suspectOtherChara; 
	
	/**
	 * 嫌疑人身高
	 */
	@Column(name="xyrsg")
	private String suspectHeight ;
	
	/**
	 * 嫌疑人随身物品特征
	 */
	@Column(name="xyrsswptz")
	private String suspectCarryItemChara ;
	
	/**
	 * 嫌疑人体型
	 */
	@Column(name="xyrtx")
	private String suspectBody ;
	
	/**
	 * 嫌疑人性别
	 */
	@Column(name="xyrxb")
	private String suspectSex;
	
	/**
	 * 嫌疑人衣着特征
	 */
	@Column(name="xyrxztz")
	private String suspectClothChara ;
	
	/**
	 * 嫌疑人作案方式
	 */
	@Column(name="xyrzafs")
	private String suspectCrimeWay ;
	
	/**
	 * 嫌疑人作案工具
	 */
	@Column(name="xyrzagj")
	private String suspectCrimeTool ;
	
	/**
	 * 周边摄像头
	 */
	@Column(name="xyrzbsxt")
	private String cameras ;
	
	/**
	 * 时间戳
	 */
	@Column(name="sjc")
	private Date timestamp ;
	
	/**
	 * 受害人姓名
	 */
	@Column(name="shrxm")
	private String victimName ;
	
	/**
	 * 受害人身份证号
	 */
	@Column(name="shrsfzh")
	private String victimId ;
	
	/**
	 * 是否驾乘车辆
	 */
	@Column(name="sfjccl")
	private String isDriving ;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJqId() {
		return jqId;
	}

	public void setJqId(String jqId) {
		this.jqId = jqId;
	}

	public String getMaterialChara() {
		return materialChara;
	}

	public void setMaterialChara(String materialChara) {
		this.materialChara = materialChara;
	}

	public String getHarmedWay() {
		return harmedWay;
	}

	public void setHarmedWay(String harmedWay) {
		this.harmedWay = harmedWay;
	}

	public String getRunWay() {
		return runWay;
	}

	public void setRunWay(String runWay) {
		this.runWay = runWay;
	}

	public String getRunDirect() {
		return runDirect;
	}

	public void setRunDirect(String runDirect) {
		this.runDirect = runDirect;
	}

	public String getSuspectHair() {
		return suspectHair;
	}

	public void setSuspectHair(String suspectHair) {
		this.suspectHair = suspectHair;
	}

	public String getSuspectSkin() {
		return suspectSkin;
	}

	public void setSuspectSkin(String suspectSkin) {
		this.suspectSkin = suspectSkin;
	}

	public String getSuspectAge() {
		return suspectAge;
	}

	public void setSuspectAge(String suspectAge) {
		this.suspectAge = suspectAge;
	}

	public String getSuspectOtherChara() {
		return suspectOtherChara;
	}

	public void setSuspectOtherChara(String suspectOtherChara) {
		this.suspectOtherChara = suspectOtherChara;
	}

	public String getSuspectHeight() {
		return suspectHeight;
	}

	public void setSuspectHeight(String suspectHeight) {
		this.suspectHeight = suspectHeight;
	}

	public String getSuspectCarryItemChara() {
		return suspectCarryItemChara;
	}

	public void setSuspectCarryItemChara(String suspectCarryItemChara) {
		this.suspectCarryItemChara = suspectCarryItemChara;
	}

	public String getSuspectBody() {
		return suspectBody;
	}

	public void setSuspectBody(String suspectBody) {
		this.suspectBody = suspectBody;
	}

	public String getSuspectSex() {
		return suspectSex;
	}

	public void setSuspectSex(String suspectSex) {
		this.suspectSex = suspectSex;
	}

	public String getSuspectClothChara() {
		return suspectClothChara;
	}

	public void setSuspectClothChara(String suspectClothChara) {
		this.suspectClothChara = suspectClothChara;
	}

	public String getSuspectCrimeWay() {
		return suspectCrimeWay;
	}

	public void setSuspectCrimeWay(String suspectCrimeWay) {
		this.suspectCrimeWay = suspectCrimeWay;
	}

	public String getSuspectCrimeTool() {
		return suspectCrimeTool;
	}

	public void setSuspectCrimeTool(String suspectCrimeTool) {
		this.suspectCrimeTool = suspectCrimeTool;
	}

	public String getCameras() {
		return cameras;
	}

	public void setCameras(String cameras) {
		this.cameras = cameras;
	}

	public String getVictimName() {
		return victimName;
	}

	public String getVictimId() {
		return victimId;
	}

	public String getIsDriving() {
		return isDriving;
	}

	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}

	public void setVictimId(String victimId) {
		this.victimId = victimId;
	}

	public void setIsDriving(String isDriving) {
		this.isDriving = isDriving;
	}
	
	
}
