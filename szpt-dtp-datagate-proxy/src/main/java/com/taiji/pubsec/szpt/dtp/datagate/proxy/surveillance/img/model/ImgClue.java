package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="t_dtp_datagate_img_clue")
public class ImgClue {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
    
	/**
	 * 布控单多端布控图片实体的id
	 */
    @Column(name="imgClueId")
	private String imgClueId ;
    
    @Column(name="createDate", nullable = false)
    private Date createDate ;
    
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

	public String getImgClueId() {
		return imgClueId;
	}

	public void setImgClueId(String imgClueId) {
		this.imgClueId = imgClueId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public SurveilList getSurveilList() {
		return surveilList;
	}

	public void setSurveilList(SurveilList surveilList) {
		this.surveilList = surveilList;
	}
}
