package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;



/**
 * 人员布控
 */
public class PersonExecuteControlBean {

    private String id;

    private String num; //编号

    private String idCardNo;    //身份证号

    private String personName;  //姓名

    private String sex; //性别
    
    private String sexName;

    private String residenceAddress;    //户籍地址

    private Long startTime; //布控开始时间

    private Long endTime;   //布控结束时间

    private String status;  //数据字典：启用；失效
    
    private String statusName;

    private String note;

    private String lastModifyPerson;    //最新修改人姓名

    private Long lastModifyTime;    //最新修改时间
    
    private String operateStatus;
    
    private String operateStatusName;
    
    private String approvePeople;
    
    private String approveContent;
    
    private Long approveTime;
    
    private String localAddressDetail;
    
    private String ethnicGroup;
    
    private String phone;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLastModifyPerson() {
        return lastModifyPerson;
    }

    public void setLastModifyPerson(String lastModifyPerson) {
        this.lastModifyPerson = lastModifyPerson;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getOperateStatusName() {
		return operateStatusName;
	}

	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
	}

	public String getApprovePeople() {
		return approvePeople;
	}

	public void setApprovePeople(String approvePeople) {
		this.approvePeople = approvePeople;
	}

	public String getApproveContent() {
		return approveContent;
	}

	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}

	public Long getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Long approveTime) {
		this.approveTime = approveTime;
	}

	public String getLocalAddressDetail() {
		return localAddressDetail;
	}

	public void setLocalAddressDetail(String localAddressDetail) {
		this.localAddressDetail = localAddressDetail;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
