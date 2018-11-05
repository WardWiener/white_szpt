package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;

/**
 * 高危人员 
 * @author huangda
 * @version 1.0
 */
@Entity
@Table(name = "t_gwry_txxx")
public class HighriskPersonAvatar implements Serializable{

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	/**
	 * 姓名
	 */
	@Column(name = "xm")
	private String name;

	@Column(name = "sfzh")
	private String idcode;
	
	/**
	 * 附件
	 */
	@Transient
	private List<Attachment> attachs = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public List<Attachment> getAttachs() {
		IAttachmentCustomizedService attachmentCustomizedService = (IAttachmentCustomizedService) SpringContextUtil.getBean("attachmentCustomizedService");
		return attachmentCustomizedService.findByTargetIdAndType(id, HighriskPerson.class.getName());
	}
}