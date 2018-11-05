package com.taiji.pubsec.szpt.customizedmenu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author huangcheng
 * @version 1.0
 * @created 16-����-2017 18:09:23
 */
@Entity(name="com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu")
@Table(name="t_customizedmenu_zdycd")
public class CustomizedMenu {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	/**
	 * 关联的菜单
	 */
	@OneToOne
	@JoinColumn(name = "yj_id")
	private SystemMenu systemMenu;
	
	/**
	 * 目标id
	 */
	@Column(name="mb_id")
	private String targetId;
	/**
	 * 类型
	 */
	@Column(name="lx")
	private String type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SystemMenu getSystemMenu() {
		return systemMenu;
	}
	public void setSystemMenu(SystemMenu systemMenu) {
		this.systemMenu = systemMenu;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}