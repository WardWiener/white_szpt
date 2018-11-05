package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * 案件打标
 * 
 * @author huangcheng
 *
 */
@Entity
@Table(name="t_xsajfx_dbjc")
public class CaseTag {
	//以下常量供积分模板及框架使用，条件查询字段名和此不同，按接口说明执行
	public static final String KEY_CASECODE = "key_caseCode";
	public static final String KEY_CASETYPE = "key_type";
	public static final String KEY_CASESORTFIRST = "key_firstSorts";
	public static final String KEY_CASESORTSECOND = "key_secondSorts";
	public static final String KEY_CASEFEATURE = "key_features";
	public static final String KEY_CASEPLACE = "key_occurPlace";
	public static final String KEY_CASEPERIOD = "key_period";
	public static final String KEY_CASEPERSONCOUNT = "key_peopleNum";
	public static final String KEY_CASEENTRANCE = "key_entrance";
	public static final String KEY_CASEEXIT = "key_exit";
	public static final String KEY_CASECOMMUNITY = "key_communities";
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 案件编号
	 */
	@OneToOne
	@JoinColumn(name="xsajfx_ajjbxx_id", unique = true)
	private CriminalBasicCase basicCase ;
	
	/**
	 * 案件级别编码
	 * 
	 */
	@Column(name="ajjb", length = 50)
	private String level ;
	
	/**
	 * 案件类别编码
	 * 
	 */
	@Column(name="ajlb", length = 50)
	private String type ;
	
	/**
	 * 案件性质一级编码
	 * 
	 */
	@Column(name="ajxzyj", length = 50)
	private String firstSort ;
	
	/**
	 * 案件性质二级编码
	 */
	@Column(name="ajxzej", length = 50)
	private String secondSort ;
	
	/**
	 * 发案地点经度
	 */
	@Column(name="fajd", length = 50)
	private String longitude;
	
	/**
	 * 发案地点维度
	 */
	@Column(name="fawd", length = 50)
	private String latitude;
	
	/**
	 * 发案地点地址
	 */
	@Column(name="fadz", length = 500)
	private String address ;
	
	/**
	 * 发案村区编码
	 */
	@Column(name="facq", length = 50)
	private String community ;
	
	/**
	 * 涉案场所类型编码
	 */
	@Column(name="sacslx", length = 50)
	private String placeType;
	
	/**
	 * 涉案场所名称
	 */
	@Column(name="sacsmc", length = 200)
	private String placeName;
	
	/**
	 * 发案处所编码
	 */
	@Column(name="facs", length = 50)
	private String occurPlace ;
	
	/**
	 * 作案入口编码
	 */
	@Column(name="zark", length = 50)
	private String entrance ;
	
	/**
	 * 作案出口编码
	 */
	@Column(name="zack", length = 50)
	private String exit ;
	
	/**
	 * 作案人数编码
	 */
	@Column(name="zars", length = 50)
	private String peopleNum ;
	
	/**
	 * 作案时段，字典项
	 * 一天分为8份
	 */
	@Column(name="zasd", length = 50)
	public String period ;
	
	/**
	 * 作案特点
	 */
	@OneToMany(mappedBy = "caseTag")
	private Set<CaseFeatureTag> features = new HashSet<CaseFeatureTag>();
	
	/**
	 * 创建人id
	 */
	@Column(name="cjr_id", length = 50)
	private String createPersonId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 更新人id
	 */
	@Column(name="gxr_id", length = 50)
	private String updatePersonId;
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;


	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstSort() {
		return firstSort;
	}

	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}

	public String getSecondSort() {
		return secondSort;
	}

	public void setSecondSort(String secondSort) {
		this.secondSort = secondSort;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getOccurPlace() {
		return occurPlace;
	}

	public void setOccurPlace(String occurPlace) {
		this.occurPlace = occurPlace;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getExit() {
		return exit;
	}

	public void setExit(String exit) {
		this.exit = exit;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public Set<CaseFeatureTag> getFeatures() {
		return features;
	}

	public void setFeatures(Set<CaseFeatureTag> features) {
		this.features = features;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdatePersonId() {
		return updatePersonId;
	}

	public void setUpdatePersonId(String updatePersonId) {
		this.updatePersonId = updatePersonId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
