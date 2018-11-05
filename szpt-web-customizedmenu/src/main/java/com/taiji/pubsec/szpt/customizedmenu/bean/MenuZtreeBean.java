package com.taiji.pubsec.szpt.customizedmenu.bean;

public class MenuZtreeBean {
	
	//id
	private String id;
	
	//父id
	private String pId;
	
	//是否可以打开
	private boolean nocheck;
	
	//名称
	private String name;
	
	//是否可以打开
	private boolean open;
	
	//是否选中
	private boolean checked;
	
	//是否可以被点击
	private boolean isclick;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isIsclick() {
		return isclick;
	}

	public void setIsclick(boolean isclick) {
		this.isclick = isclick;
	}
	
}