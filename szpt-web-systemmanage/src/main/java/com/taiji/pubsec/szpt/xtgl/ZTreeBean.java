package com.taiji.pubsec.szpt.xtgl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ztree树的Bean
 * 
 * @author xinfan
 *
 */
public class ZTreeBean {
	private String id;
	private String parentId;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 节点名称。
	 */
	private String name;
	/**
	 * 记录 treeNode 节点是否为父节点。
	 */
	private String isParent;

	/**
	 * 记录 treeNode 节点的 展开 / 折叠 状态。
	 */
	private String open;
	/**
	 * 设置点击节点后在何处打开 url。[treeNode.url 存在时有效]
	 */
	private String target;
	/**
	 * 节点链接的目标 URL
	 */
	private String url;

	/**
	 * 最简单的 click 事件操作。相当于 onclick="..." 的内容。 如果操作较复杂，请使用 onClick 事件回调函数。
	 */
	private String click = "true";

	/**
	 * 节点自定义图标的 URL 路径。
	 */
	private String icon;
	/**
	 * 节点自定义图标的 className
	 */
	private String iconOpen;
	/**
	 * 父节点自定义折叠时图标的 URL 路径。
	 */
	private String iconClose;
	/**
	 * 节点自定义图标的 className
	 */
	private String iconSkin;

	/**
	 * 依赖 jquery.ztree.excheck 扩展 js ] 节点的 checkBox / radio 的
	 * 勾选状态。[setting.check.enable = true & treeNode.nocheck = false 时有效]
	 */
	private String checked;
	/**
	 * [ 依赖 jquery.ztree.excheck 扩展 js ] 设置节点的 checkbox / radio 是否禁用
	 * [setting.check.enable = true 时有效]
	 */
	private String chkDisabled;

	/**
	 * [ 依赖 jquery.ztree.excheck 扩展 js ] 设置节点是否隐藏 checkbox / radio
	 * [setting.check.enable = true 时有效]
	 */
	private String nocheck;
	/**
	 * [ 依赖 jquery.ztree.excheck 扩展 js ] 强制节点的 checkBox / radio 的
	 * 半勾选状态。[setting.check.enable = true & treeNode.nocheck = false 时有效]
	 */
	private String halfCheck;
	/**
	 * [ 依赖 jquery.ztree.exhide 扩展 js ]判断 treeNode 节点是否被隐藏。
	 */
	private String isHidden;
	private String isUnit;
	private String unitId;
	private List<ZTreeBean> children = new ArrayList<ZTreeBean>();

	private Map diyMap = new HashMap(0);

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public List<ZTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeBean> children) {
		this.children = children;
	}

	public Map getDiyMap() {
		return diyMap;
	}

	public void setDiyMap(Map diyMap) {
		this.diyMap = diyMap;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(String chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}

	public String getHalfCheck() {
		return halfCheck;
	}

	public void setHalfCheck(String halfCheck) {
		this.halfCheck = halfCheck;
	}

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	public String getIsUnit() {
		return isUnit;
	}

	public void setIsUnit(String isUnit) {
		this.isUnit = isUnit;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
