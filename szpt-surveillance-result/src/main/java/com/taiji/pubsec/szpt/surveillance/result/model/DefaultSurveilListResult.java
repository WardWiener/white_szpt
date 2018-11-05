package com.taiji.pubsec.szpt.surveillance.result.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentMetaImpl;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;

@Entity
@Table(name="t_surveillance_default_result", indexes = {
		@Index(name="surveillance_default_result_surveilListNum", columnList="surveilListNum")
})
public class DefaultSurveilListResult implements SurveilListResult{
	
	public DefaultSurveilListResult(){
		this.resultStatus = SurveillanceUtilConstant.DICT_XTZT_INIT;
	}
	
	public enum CLUE_TYPE{
		FLIGHT("飞机"),
		HOTEL("旅馆"),
		TRAIN("火车"),
		IMG("图片"),
		WIFI("wifi"),
		INTERNET_BAR("网吧");
		
		private String value ;
		
		private CLUE_TYPE(String value){
			this.value = value ;
		}

		public String getValue() {
			return value;
		}

		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    
    /**
     * 默认值为DefaultSurveilListResult的包名和类名
     */
    @Column(name="resultType")
    private String type = DefaultSurveilListResult.class.getName() ;
    
    /**
     * 业务端关联的线索的id，如果是飞机、旅馆、火车、网吧就是布控单id；如果是图片就是布控图片实体的id，如果是手机mac就是就是布控手机mac实体的id
     */
    @Column(name="clueId")
    private String clueId ;
    
    /**
     * 线索类型：常量CLUE_TYPE的值
     */
    @Column(name="clueType")
    private String clueType ;
    
    @Column(name = "catchContent")
    private String catchContent;    //捕捉内容
    
    @Column(name="resultStatus")
    private String resultStatus ; //状态：默认值为字典项初始化:init（字典类型系统状态:xtzt）
    
    @Column(name = "catchDetail", length = 1024)
    private String catchDetail ; //捕捉详情，json字符串
    
    @Column(name = "catchLongitude")
    private Double catchLongitude;  //捕捉经度

    @Column(name = "catchLatitude")
    private Double catchLatitude;   //捕捉维度
    
    @Column(name = "surveilListNum") 
    private String surveilListNum ; //布控单编码
    
    @Column(name = "catchObject") 
    private String catchObject ; //布控对象
	
    /**
     * 布控图片结果
     */
	@OneToOne(targetEntity = DefaultAttachmentMetaImpl.class)
	@JoinColumn(name="fjy_catchImgResult_id", unique=true)
	private AttachmentMeta catchImgResult ;
    
    @Column(name = "catchTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date catchTime; //捕捉时间
    

	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatchContent() {
		return catchContent;
	}

	public void setCatchContent(String catchContent) {
		this.catchContent = catchContent;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getCatchDetail() {
		return catchDetail;
	}

	public void setCatchDetail(String catchDetail) {
		this.catchDetail = catchDetail;
	}


	public Double getCatchLongitude() {
		return catchLongitude;
	}

	public void setCatchLongitude(Double catchLongitude) {
		this.catchLongitude = catchLongitude;
	}

	public Double getCatchLatitude() {
		return catchLatitude;
	}

	public void setCatchLatitude(Double catchLatitude) {
		this.catchLatitude = catchLatitude;
	}

	public String getSurveilListNum() {
		return surveilListNum;
	}

	public void setSurveilListNum(String surveilListNum) {
		this.surveilListNum = surveilListNum;
	}

	public String getCatchObject() {
		return catchObject;
	}

	public void setCatchObject(String catchObject) {
		this.catchObject = catchObject;
	}

	public AttachmentMeta getCatchImgResult() {
		return catchImgResult;
	}

	public void setCatchImgResult(AttachmentMeta catchImgResult) {
		this.catchImgResult = catchImgResult;
	}

	public Date getCatchTime() {
		return catchTime;
	}

	public void setCatchTime(Date catchTime) {
		this.catchTime = catchTime;
	}

	public String getClueId() {
		return clueId;
	}

	public void setClueId(String clueId) {
		this.clueId = clueId;
	}

	public String getClueType() {
		return clueType;
	}

	public void setClueType(String clueType) {
		this.clueType = clueType;
	}
	
}
