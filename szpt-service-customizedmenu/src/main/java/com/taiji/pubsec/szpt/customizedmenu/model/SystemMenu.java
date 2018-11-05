package com.taiji.pubsec.szpt.customizedmenu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;

/**
 * @author huangcheng
 * @version 1.0
 * @created 17-����-2017 11:26:36
 */
@Entity(name="com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu")
@Table(name="t_customizedmenu_xtcd")
public class SystemMenu {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 名称
	 */
	@Column(name="mc")
	private String name;
	/**
	 * 菜单对应的资源
	 */
	@ManyToOne
	@JoinColumn(name = "zy_id")
	private AuthorityResource authorityResource;
	
	/**
	 * 系统路径
	 */
	private String url;
	
	/**
	 * 类型
	 */
	@Column(name="sfzd")
	private String type;
	
	/**
	 * 父菜单
	 */
	@ManyToOne
	@JoinColumn(name = "f_id")
	private SystemMenu superSystemMenu;
	/**
	 * 子菜单集合
	 */
	@OneToMany(mappedBy = "superSystemMenu", cascade = CascadeType.REMOVE)
	private Set<SystemMenu> systemMenus = new HashSet<SystemMenu>();
	/**
	 * 描述
	 */
	@Column(name="ms")
	private String description;
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
	public AuthorityResource getAuthorityResource() {
		return authorityResource;
	}
	public void setAuthorityResource(AuthorityResource authorityResource) {
		this.authorityResource = authorityResource;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SystemMenu getSuperSystemMenu() {
		return superSystemMenu;
	}
	public void setSuperSystemMenu(SystemMenu superSystemMenu) {
		this.superSystemMenu = superSystemMenu;
	}
	public Set<SystemMenu> getSystemMenus() {
		return systemMenus;
	}
	public void setSystemMenus(Set<SystemMenu> systemMenus) {
		this.systemMenus = systemMenus;
	}
	
}