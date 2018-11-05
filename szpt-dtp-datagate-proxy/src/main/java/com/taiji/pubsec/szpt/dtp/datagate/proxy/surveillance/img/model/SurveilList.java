package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_dtp_datagate_surveil_list", indexes = {
		@Index(name="dtp_datagate_surveil_list_surveilListCode", columnList="surveilListCode")
})
public class SurveilList {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
	
    @Column(name="surveilListCode")
	private String surveilListCode ;
	
    /**
     * 布控开始时间
     */
    @Column(name="startDate")
    private Date startDate ;
    
    /**
     * 布控结束时间
     */
    @Column(name="endDate")
    private Date endDate ;
    
    /**
     * 状态：1启用；0停用
     */
    @Column(name="status")
    private String status ;
    
    /**
     * 身份证号
     */
    @Column(name="identy")
    private String identy ;
    
    /**
     * 姓名
     */
    @Column(name="name")
    private String name ;
    
    /**
     * 性别
     */
    @Column(name="sex")
    private String sex ;
    
    @Column(name="createDate", nullable = false)
    private Date createDate ;
    
	@OneToMany(mappedBy="surveilList")
	private Set<ImgClue> imgClues = new HashSet<ImgClue>() ;
	
	@OneToMany(mappedBy="surveilList")
	private Set<ImgClueResult> imgClueResults = new HashSet<ImgClueResult>() ;
	
	@Transient
	public List<ImgClue> getImgCluesAsArrayList(){
		List<ImgClue> list = new ArrayList<ImgClue>() ;
		list.addAll(this.getImgClues()) ;
		return list;
	}
	
	@Transient
	public List<ImgClueResult> getImgClueResultsAsArrayList(){
		List<ImgClueResult> list = new ArrayList<ImgClueResult>() ;
		list.addAll(this.getImgClueResults()) ;
		return list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSurveilListCode() {
		return surveilListCode;
	}

	public void setSurveilListCode(String surveilListCode) {
		this.surveilListCode = surveilListCode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdenty() {
		return identy;
	}

	public void setIdenty(String identy) {
		this.identy = identy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<ImgClue> getImgClues() {
		return imgClues;
	}

	public void setImgClues(Set<ImgClue> imgClues) {
		this.imgClues = imgClues;
	}

	public Set<ImgClueResult> getImgClueResults() {
		return imgClueResults;
	}

	public void setImgClueResults(Set<ImgClueResult> imgClueResults) {
		this.imgClueResults = imgClueResults;
	}
	
	
}
