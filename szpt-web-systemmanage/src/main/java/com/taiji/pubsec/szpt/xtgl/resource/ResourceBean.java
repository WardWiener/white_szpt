package com.taiji.pubsec.szpt.xtgl.resource;


/**
 * 资源Bean
 * 
 * @author xinfan
 *
 */
public class ResourceBean {
	private String id;
	private String type;// 类型,字典项Id
	private String resourceName;// 资源名称
	private String resourceUrl;// 资源url

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
}
