package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 作案特点
 * @author huangcheng
 *
 */
@Entity
@Table(name="t_xsajfx_dbzatd")
public class CaseFeatureTag implements Comparable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id ;

	/**
	 * 作案特点编码
	 */
	@Column(name="zatd", length = 50)
	private String feature ;
	
	/**
	 * 打标基础表id
	 */
	@ManyToOne
	@JoinColumn(name="xsajfx_dbjc_id")
	@Index(name="index_t_xsajfx_dbjc_id")
	private CaseTag caseTag ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public CaseTag getCaseTag() {
		return caseTag;
	}

	public void setCaseTag(CaseTag caseTag) {
		this.caseTag = caseTag;
	}

	@Override
	public int compareTo(Object o) {
		return Integer.parseInt(this.feature) - Integer.parseInt(((CaseFeatureTag)o).getFeature());
	}

	
}
