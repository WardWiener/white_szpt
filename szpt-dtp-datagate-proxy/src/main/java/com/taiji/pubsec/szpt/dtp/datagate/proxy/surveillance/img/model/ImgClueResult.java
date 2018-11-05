package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="t_dtp_datagate_img_clue_result")
public class ImgClueResult {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
    
    public static final String DTP_DG_IMG_CLUE_RESULT_UNREAD = "unread" ;
    public static final String DTP_DG_IMG_CLUE_RESULT_READ = "read" ;
    
    /**
     * 监控点编号
     */
	@Column(name="cameraCode")
    private String cameraCode ;
	
	/**
	 * 摄像头地址
	 */
	@Column(name="cameraAddr")
	private String cameraAddr ;
	
	/**
	 * 置信度
	 */
	@Column(name="confidenceLevel")
	private Double confidenceLevel ;
    
	/**
	 * 摄像头经度
	 */
	@Column(name="longitude")
    private Double longitude;
	
	/**
	 * 摄像头纬度
	 */
	@Column(name="latitude")
    private Double latitude; 	
    
	/**
	 * 预警时间
	 */
	@Column(name="catchDate")
	private Date catchDate ;
	
	/**
	 * 状态:未读unread, 已读read
	 */
	@Column(name="status", nullable=false)
	private String status ;
	
	/**
	 * 图片字节码
	 */
	@Column(name="img")
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="binary")
	private byte[] img ;
	
	@ManyToOne
	@JoinColumn(name="surveilList_id")
	private SurveilList surveilList ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Date getCatchDate() {
		return catchDate;
	}

	public void setCatchDate(Date catchDate) {
		this.catchDate = catchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SurveilList getSurveilList() {
		return surveilList;
	}

	public void setSurveilList(SurveilList surveilList) {
		this.surveilList = surveilList;
	}

	public String getCameraCode() {
		return cameraCode;
	}

	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}

	public String getCameraAddr() {
		return cameraAddr;
	}

	public void setCameraAddr(String cameraAddr) {
		this.cameraAddr = cameraAddr;
	}

	public Double getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(Double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	} 
	
	
}
