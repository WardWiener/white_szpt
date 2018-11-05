package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 警情视频
 * @author zhangjn
 *
 */

@Entity
@Table(name="t_zhzats_jwzh_jqsp")
public class JqVideo {
	
	@Id
	private String id ;
	
	/**
	 * 警情编码
	 */
	@Column(name="jqbm")
	private String jqCode ;
	
	/**
	 * 文件id
	 */
	@Column(name="wjid")
	private String fileId ;
	
	/**
	 * 文件名称
	 */
	@Column(name="wjmc")
	private String fileName ;
	
	/**
	 * 文件类型，1-图片 2-视频 3-音频
	 */
	@Column(name="wjlx", length=10)
	private String fileType ;

	/**
	 * 播放地址
	 */
	@Column(name="bfdz")
	private String playAdd ;
	
	/**
	 * 预览地址
	 */
	@Column(name="yldz")
	private String showAdd ;

	/**
	 * 录制人警号
	 */
	@Column(name="lzrjh", length=50)
	private String producerNum ;

	/**
	 * 录制人姓名
	 */
	@Column(name="lzrxm", length=50)
	private String producerName ;
	
	/**
	 * 录制人单位编码
	 */
	@Column(name="lzrdw", length=50)
	private String producerDwCode ;
	
	/**
	 * 开始时间
	 */
	@Column(name="kssj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate ;
	
	/**
	 * 结束时间
	 */
	@Column(name="jssj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate ;
	
	/**
	 * 录制时长
	 */
	@Column(name = "lzsc")
	private String videoLength;

	/**
	 * 保存时长
	 */
	@Column(name = "bcsc")
	private String saveVideoLength;
	
	/**
	 * 文件大小,单位为字节
	 */
	@Column(name = "wjdx")
	private Integer fileSize;
	
	/**
	 * 文件是否有效,0表示有效,1表示无效
	 */
	@Column(name="zt", length=1)
	private String status;
	
	/**
	 * 数据来源，jwzh代表警务指挥，zfjly代表执法记录仪
	 */
	@Column(name="ly")
	private String source;
	
	/**
	 * 更新时间
	 */
	@Column(name="gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPlayAdd() {
		return playAdd;
	}

	public void setPlayAdd(String playAdd) {
		this.playAdd = playAdd;
	}

	public String getShowAdd() {
		return showAdd;
	}

	public void setShowAdd(String showAdd) {
		this.showAdd = showAdd;
	}

	public String getProducerNum() {
		return producerNum;
	}

	public void setProducerNum(String producerNum) {
		this.producerNum = producerNum;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerDwCode() {
		return producerDwCode;
	}

	public void setProducerDwCode(String producerDwCode) {
		this.producerDwCode = producerDwCode;
	}

	public String getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(String videoLength) {
		this.videoLength = videoLength;
	}

	public String getSaveVideoLength() {
		return saveVideoLength;
	}

	public void setSaveVideoLength(String saveVideoLength) {
		this.saveVideoLength = saveVideoLength;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getJqCode() {
		return jqCode;
	}

	public void setJqCode(String jqCode) {
		this.jqCode = jqCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
